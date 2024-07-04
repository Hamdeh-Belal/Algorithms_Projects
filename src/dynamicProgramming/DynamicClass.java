package dynamicProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DynamicClass {
	private File file;
	private int[][] table;
	private int[][] costs;
	private String[] DataName;
	private int[] Fstar;
	private int[] Xstar;
	private int stage;
	private int[] startStage;

	/**
	 * 
	 */
	public DynamicClass(File file) {
		setFile(file);
		readFile();
	}

	public void readFile() {
		// array list of the file data
		ArrayList<String> data = new ArrayList<>();
		// size = number of citys || hotels
		int size = 0;
		// array has the src and dist. citys
		String startEnd[] = null;

		try {
			Scanner input = new Scanner(file);
			// get the size
			size = Integer.parseInt(input.nextLine().trim());
			// get the first city and last city
			String dataLine = input.nextLine();
			// split on ","
			startEnd = dataLine.split(",");
			// get all other data and store it in data arraylist
			while (input.hasNext()) {
				data.add(input.nextLine());
			}
			input.close();

		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("File Formate Error ");
			alert.setContentText("File Formate Error : please check File \n" + e.getMessage());
			alert.showAndWait();

		}
		// table array of n*n size has the costs in all cases of traveling
		table = new int[size][size];
		// cost array of n*n size has the costs of hotel and petrol between any two
		// hotels in different citys
		costs = new int[size][size];

		/*
		 * F* array of size n has the values of Function Fn*(s,Xn) where Fn(s,Xn)=
		 * costs(s,Xn)+ F*n-1(Xn) and F*(s) = Min(Fn(s,Xn) , in all Fn in this stage X*n
		 * = corresponding Xn s= stage
		 */
		Fstar = new int[size];
		// X* array of size n has the value of the index of city the has the F*
		Xstar = new int[size];
		// put initial value
		for (int i = 0; i < Fstar.length; i++) {
			Fstar[i] = 0;
			Xstar[i] = 0;
		}
		// DataName has all citys hotels name in all stages(city's)
		DataName = new String[size];

		// add the src and dist. (first and last city's)
		DataName[0] = startEnd[0].trim();
		DataName[DataName.length - 1] = startEnd[1].trim();

		// get all other city's
		for (int i = 1; i < data.size() || i < DataName.length - 1; i++) {
			DataName[i] = data.get(i).substring(0, data.get(i).indexOf(','));
		}
		// stage = number of stages in all travel
		stage = -1;
		String City = null;
		// array list that contain all hotels in stage's
		ArrayList<LinkedList<String>> stageNodes = new ArrayList<>();
		// list for all hotels in current city
		LinkedList<String> currStageNodes = new LinkedList<>();
		// Fill cost table
		for (int i = 0; i < data.size(); i++) {
			// split on space +,
			String[] line = data.get(i).split(", ");
			// the first item is the current hotels
			int firstNode = findIndex(DataName, line[0].trim());
			// get all hotels in the next city
			String hotelsNextCity[] = line[1].trim().substring(1, line[1].length() - 1).split(",");
			String firstNextHotel = hotelsNextCity[0];
			if (!firstNextHotel.equals(City)) {
				/*
				 * if it not like the prev first hotel then we are in the next city (stage)
				 */
				City = firstNextHotel;
				stage++;
				// new list for all next hotels
				currStageNodes = new LinkedList<>();
				stageNodes.add(currStageNodes);

			}
			// add all new hotels in next city
			currStageNodes.insertSorted(line[0].trim());

			for (int j = 1; j < line.length; j++) {
				String nextHotel_Cost = line[j].trim().substring(1, line[j].length() - 1);
				String[] node = nextHotel_Cost.split(",");
				int nextHotel = findIndex(DataName, node[0].trim());
				int petrolCost = (Integer.parseInt(node[1].trim()));
				int hotelCost = Integer.parseInt(node[2].trim());

				/*
				 * The cost form City to city is the petrol cost and the hotel cost
				 */
				costs[firstNode][nextHotel] = petrolCost + hotelCost;
			}

		}

		startStage = new int[stage + 1];
		// for all stages(city's)
		for (int i = stage; i >= 0; i--) {

			// get the last-1 stage hotels
			LinkedList<String> list = stageNodes.get(i);
			// the length of the list
			int length = list.lengthR1();
			// for all hotels in the stage
			for (int j = 0; j < length; j++) {
				String currHotel = list.deleteSorted(list.getHead().getData());
				// find the index of current hotel
				int index = findIndex(DataName, currHotel);

				if (j == 0 && startStage[i] == 0) {
					startStage[i] = index;
				}

				// new Array list for all costs to Travel to the next city
				ArrayList<DataTable> arrayOfData = new ArrayList<>();
				// get the costs form the current hotel to the next city ( which the prev stage)
				for (int k = 0; k < costs.length; k++) {
					if (costs[index][k] != 0) {

						int cost = costs[index][k] + Fstar[k];
						table[index][k] = cost;
						arrayOfData.add(new DataTable(cost, k));

					}
				}

				// get the min cost to the next city
				DataTable dTable = findMin(arrayOfData);
				// add the resule to F*
				Fstar[index] = dTable.getData();
				// add the index that has the min to X*
				Xstar[index] = dTable.getIndex();

			}
		}
	}

	public String getOptimalpath() {
		StringBuilder path = new StringBuilder();
		path.append("Cost: " + Fstar[0] + " " + " " + DataName[0] + "  -> ");

		int x = 0;
		for (int i = 0; i < stage; i++) {
			path.append(DataName[Xstar[x]] + "  -> ");
			x = Xstar[x];
		}
		path.append(DataName[DataName.length - 1]);

		return path.toString();
	}

	public String[] getAltPath() {
		StringBuilder path = new StringBuilder();
		String[] pathArr = new String[3];
		int pathArrIndex = 0;
		// number of Alternative solutions
		int altPath = 0;
		// index of optimal solution
		int optSol = Xstar[0];
		// index of hotel in stage
		int indexS = 0;
		// counter of index until the change in the path
		int indexSCounter = 0;
		// cost of Alternative solutions
//		int altCost = 0;
		// number of other solutions
		while (altPath < 3) {

			// array that has all other way but not the optimal way
			Integer[] otherWays = getOtherHotelInStage(indexS, optSol);
			for (int w = 0; w < otherWays.length; w++) {
				path = new StringBuilder();
				// if we reach the goal we stop
				if (altPath == 3) {
					break;
				}
				// if we have other way we continue
				if (otherWays != null && otherWays[w] != null) {
					path.append(" " + DataName[0] + "  -> ");
					// counter of step before the change
					int counter = 0;
					// if the change in the stage more than stage 0
					if (indexS > 0) {
						int xsw = 0;

						for (int i = 0; i < indexS; i++) {
							path.append(" " + DataName[Xstar[xsw]] + "  ->> ");
							xsw = Xstar[xsw];
							counter = i;

						}
					}
					path.append(" " + DataName[otherWays[w]] + "  -> ");
					// index of start way
					int y = otherWays[w];
					for (int i = counter; i < stage; i++) {
						path.append(DataName[Xstar[y]] + "  " + Xstar[y] + "  -> ");
						// new index form the array X*
						y = Xstar[y];
						// if we end the way we stop (reach last stage -1)
						if (y == (DataName.length - 2))
							break;

					}
					path.append(DataName[DataName.length - 1]);
					// increase the path counter
					altPath++;
					pathArr[pathArrIndex++] = path.toString();

				}
				// the new optimal solution index
				optSol = Xstar[optSol];
			}
			// increment the counter
			indexSCounter++;
			// if we need more solution the the new way will be in the hotel in the next
			// stage
			indexS = startStage[indexSCounter];
		}
		return pathArr;
	}

	/*
	 * In this method we get the other hotel in the city we can visit (NOT OPTEML)
	 */
	private Integer[] getOtherHotelInStage(int currHotel, int optHotel) {
		if (table != null) {
			ArrayList<Integer> otherHotel = new ArrayList<>();

			for (int j = 0; j < table[currHotel].length; j++) {
				if (table[currHotel][j] != 0 && j != optHotel) {
					otherHotel.add(j);
				}

			}
			Integer[] array = otherHotel.toArray(new Integer[0]);
			return array;
		}

		return null;
	}

	private DataTable findMin(ArrayList<DataTable> arrayOfData) {
		if (arrayOfData != null) {
			int min = Integer.MAX_VALUE;
			int index = 0;
			for (int i = arrayOfData.size() - 1; i >= 0; i--) {

				if (arrayOfData.get(i).getData() < min) {
					min = arrayOfData.get(i).getData();
					index = arrayOfData.get(i).getIndex();
				}

			}
			return new DataTable(min, index);
		}
		return null;
	}

	private int findIndex(String[] array, String data) {
		if (array != null && data != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i].equals(data))
					return i;
			}
		}
		return -1;
	}

	public int[][] getCostsTable() {
		return costs;
	}

	public int[][] getDpTable() {
		return table;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String[] getNames() {
		return DataName;

	}

}
