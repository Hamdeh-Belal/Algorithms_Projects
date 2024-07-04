/**
 * 
 */
package huffmanCoding;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Belal Hamdeh
 *
 */
public class HuffmanCodeDeComp {

	private BST_De<DNode> tree;
	private ArrayList<HuffTableDe> table;
	private File files;
	private File newFile;
	private String Header;
	private int fileTotalSize;
	private int headerSize;
	private int dataSizeFile;
	private int originalFileSize;

	String dataHuff;
	String fileExtenetions;

	public HuffmanCodeDeComp(File file) {
		Header = "";
		dataHuff = "";
		this.files = file;
		tree = new BST_De<>();
		table = new ArrayList<>();
		fileTotalSize = 0;
		headerSize = 0;
		dataSizeFile = 0;
		originalFileSize = 0;
		readFile();
		reBuildTree(Header);
		fillTable();
		writeFile();
	}

	public String getInfo() {
		String dataa = "";
		if (files != null && newFile != null) {
			dataa += "Compressed Size: " + files.length() + "\n";
			dataa += "Original Size: " + newFile.length() + "\n";
			long originalFileSize = newFile.length(); // Size of original file in bytes
			long compressedFileSize = files.length(); // Size of compressed file in bytes

			double compressionPercentage = ((double) (compressedFileSize - originalFileSize)
					/ (double) originalFileSize) * 100.0;

			// Add the compression percentage result into the string variable
			dataa += "Compression percentage: " + String.format("%.2f%%", compressionPercentage);

		}
		return dataa;
	}

	/**
	 * @return the table
	 */
	public ArrayList<HuffTableDe> getTable() {
		return table;
	}

	/**
	 * @return the files
	 */
	public File getFiles() {
		return files;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return Header;
	}

	private boolean readFile() {
		if (files != null) {
			byte[] buffer = new byte[8];

			try {
				DataInputStream inputs = new DataInputStream(
						new FileInputStream(files.getPath().replace("\\", "\\\\")));
				StringBuilder FileExtenetion = new StringBuilder();

				/*
				 * READ EXTENTETION
				 */
				for (int k = 0; k < 4; k++) {
					char c = (char) inputs.readChar();

					if (c == '0')
						break;

					FileExtenetion.append(c);
				}

				fileExtenetions = FileExtenetion.toString();

				/*
				 * READ INFO
				 */

				int info = 2;
				while (inputs.available() != 0 && info > 0) {
					// read the info from the file
					if (info == 2) {
						fileTotalSize = inputs.readInt();
						headerSize = inputs.readInt();

						info--;
						buffer = new byte[4];
						continue;
					}
					if (info == 1) {
						originalFileSize = inputs.readInt();

						dataSizeFile = inputs.readInt();

						info--;
						break;
					}

				}
				/*
				 * READ HEADER
				 */
				int headerCount = headerSize;
				StringBuilder headerData = new StringBuilder();
				while (inputs.available() != 0 && headerCount > 0) {
					int bufferSize = Math.min(8, headerCount); // Determine the size of the buffer to read
					buffer = new byte[bufferSize];
					// read the info from the file
					int bytesRead = inputs.read(buffer, 0, bufferSize);
					headerCount -= bytesRead;
					// Convert each byte to its binary representation
					for (int i = 0; i < bytesRead; i++) {
						headerData.append(
								String.format("%8s", Integer.toBinaryString(buffer[i] & 0xFF)).replace(' ', '0'));
						// Append the byte to the StringBuilder

					}
				}

				Header = headerData.toString();

				/*
				 * READ DATA
				 */
				int dataCounter = dataSizeFile;
				StringBuilder dataFile = new StringBuilder();
				while (inputs.available() != 0 && dataCounter > 0) {
					int bufferSize = Math.min(8, dataCounter); // Determine the size of the buffer to read
					buffer = new byte[bufferSize];
					// read the info from the file
					int bytesRead = inputs.read(buffer, 0, bufferSize); // int bytesRead==> which indicates the number
																		// of bytes actually
					dataCounter -= bytesRead;

					for (byte b : buffer) {

						dataFile.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
						// Append the byte to the StringBuilder
					}
				}
				dataHuff = dataFile.toString();

				inputs.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {// handle the end of the file --> you must do it
				e.printStackTrace();
			}

		}
		return false;
	}

	private void reBuildTree(String inputString) {
		LinkedStack<DNode> stack = new LinkedStack<>();
		/*
		 * We walk in the String inputString that has the tree of Huffman code in post
		 * order trav. if we start with 1 that mean the following 8 bit will be the data
		 * for the uncompress file so we walk 9 steps if we found 0 that mean have a non
		 * leaf node if we found a 0 and the stack has only one element that mean its
		 * the root
		 */
		/*
		 * 1 -> push the next byte to the stack as node 0 -> pop two nodes and make
		 * subtree then push the subtree (first pop to the right second pop to the left)
		 */
		for (int i = 0; i < inputString.length();) {
			if (inputString.charAt(i) == '1') {

				int step = Math.min((i + 9), inputString.length());
				String data = inputString.substring(i + 1, step);

				stack.push(new DNode((byte) (Integer.parseInt(data, 2) & 0xFF)));
				table.add(new HuffTableDe((byte) (Integer.parseInt(data, 2) & 0xFF)));
				i += (step - (i)); // 9 = (1= bit for data) +(8= byte for the data)

				continue;

			} else if (inputString.charAt(i) == '0' && stack.length() == 1) {
				tree.setRoot(stack.pop().getData());
				break;
			} else if (inputString.charAt(i) == '0' && stack.length() > 1) {
				DNode right = stack.pop().getData();
				DNode left = stack.pop().getData();
				DNode subTree = new DNode();
				subTree.setRight(right);
				subTree.setLeft(left);
				stack.push(subTree);
				i++;

			}
		}

	}

	/*
	 * in this method we use find method this method trav the tree by freq and if it
	 * go left it add 0 to the string and if it go right it add 1 to the string
	 * finally when it find the node it return the string after that we converge the
	 * string to byte then we add it to tree array list
	 */
	private void fillTable() {
		for (int i = 0; i < table.size(); i++) {
			String huff = tree.findHuffmanCode(table.get(i).getData());
			if (huff != null) {
				table.get(i).setHuffmanCode(huff);

			}
		}
	}

	private void writeFile() {
		String str = files.getPath().replace("\\", "\\\\");

		int indexDot = str.indexOf('.');
		String name = str.substring(0, indexDot);
//		name = name+"2";
		
		byte[] newData = tree.getAllData(dataHuff);

		try {
			newFile = new File(name + "." + fileExtenetions);
			DataOutputStream outputs = new DataOutputStream(new FileOutputStream(newFile));
			
			outputs.write(newData);

			// Closing the stream
			outputs.flush();
			outputs.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
