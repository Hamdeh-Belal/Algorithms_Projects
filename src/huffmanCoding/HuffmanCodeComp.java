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
public class HuffmanCodeComp {

	/*
	 * new array has 3 parameter (byte: the byte from input file,huffmanCode: the
	 * implementation of byte in huffman , freq: how many time this byte repeated
	 */
	private BST<TNode> tree;
	private ArrayList<HuffTable> table;
	private int[] freqTable;
	private File files;
	private String Header;
	private File newFile;

	/**
	 * 
	 */
	public HuffmanCodeComp(File file) {
		Header = "";
		this.files = file;
		tree = new BST<>();
		table = new ArrayList<>();
		freqTable = new int[256]; // 256 is the number of all byte Possible possibilities
		for (int i = 0; i < freqTable.length; i++) {
			freqTable[i] = 0;
		}
		readFile(); // read the file & make freq table for every byte
		buildHuffTree(); // build the huffman tree
		buildGeneralTable(); // make the table that has (byte , huffCode, freq)
		writeFile(); // read the file again and for each byte we write the equivalent huffman code of
						// it
	}

	public String getInfo() {
		String dataa = "";
		if (files != null && newFile != null) {
			dataa += "Original Size: " + (int) files.length() + "\n";
			dataa += "New Size: " + (int) newFile.length() + "\n";
			double originalFileSize = (int) files.length(); // Size of original file in bytes
			double compressedFileSize = (int) newFile.length(); // Size of compressed file in bytes

			double compressionPercentage = ((double) (originalFileSize - compressedFileSize)
					/ (double) originalFileSize) * 100.0;

			// Add the compression percentage result into the string variable
			dataa += "Compression percentage: " + String.format("%.2f%%", compressionPercentage);

		}
		return dataa;
	}

	/**
	 * @return the table
	 */
	public ArrayList<HuffTable> getTable() {
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

	/**
	 * @return the newFile
	 */
	public File getNewFile() {
		return newFile;
	}

	/*
	 * This method read the file that comes in the parameter as 8 byte buffer and
	 * call cuntFreq method to increase the count of match values
	 */
	public boolean readFile() {
		if (files != null) {
			try {
				
				// buffer of 8 byte = 64 bit
				byte[] buffer = new byte[8];
				DataInputStream inputs = new DataInputStream(
						new FileInputStream(files.getPath().replace("\\", "\\\\")));
				while (inputs.available() != 0) { // inputs.available() != 0 this method tell you the end of the binary
					buffer = new byte[8]; // file
					int bufferSize = inputs.read(buffer);
					if (bufferSize != 8) {
						// make other buffer with size < 8
						byte[] smallBuffer = new byte[bufferSize];
						// copy the data
						for (int i = 0; i < smallBuffer.length; i++) {
							smallBuffer[i] = buffer[i];

						}
						// check the freq
						if (!countFreq(smallBuffer))
							break;
						// check the freq
					} else if (!countFreq(buffer))
						break;
				}
				inputs.close();

				return true;
			} catch (FileNotFoundException e) { // handle if the file not found
				e.printStackTrace();
			} catch (IOException e) {// handle the end of the file --> you must do it
				e.printStackTrace();
			}
		}
		return false;
	}

	/*
	 * This method get the 8 byte buffer then increment the cell of that byte in
	 * freqTable to get how many that byte repeated
	 */
	private boolean countFreq(byte[] buffer) {
		if (buffer != null) {
			for (int i = 0; i < buffer.length; i++) {
				Byte data = buffer[i];
				freqTable[data & 0xFF]++;
			}
			return true;
		}
		return false;
	}

	private void buildHuffTree() {
		int size = getTableSize(); // number of leaves = number of all nonzero freq bytes
		MinHeap<TNode> heap = new MinHeap<>(size); // Priority Queue
		// add all non zero element into the heap --> Insert
		for (int i = 0; i < freqTable.length; i++) {
			if (freqTable[i] != 0) {
				heap.add(new TNode((byte) i, freqTable[i])); // the sort depend on comperTo method
			}
		}
		// build the tree
		for (int i = 1; i < size; i++) {
			if (heap.getSize() > 1) {
				TNode z = new TNode();

				TNode x = heap.removeMin();
				TNode y = heap.removeMin();

				z.setLeft(x);
				z.setRight(y);

				z.setFreq(x.getFreq() + y.getFreq()); // now z is not a leaf and has no data and has the x+y freq

				heap.add(z);

			} else
				break;

		}
		if (!heap.isEmpty() && heap.getSize() == 1) {
			tree.setRoot(heap.removeMin());

			Header = tree.postOrderTrav();

		}

	}

//this method return the number of non zero element in the freq table
	private int getTableSize() {
		int count = 0;
		for (int i = 0; i < freqTable.length; i++) {
			if (freqTable[i] != 0) // we don't count the zero elements
				count++;
		}
		return count;
	}

	/*
	 * in this method we use find method this method trav the tree by freq and if it
	 * go left it add 0 to the string and if it go right it add 1 to the string
	 * finally when it find the node it return the string after that we converge the
	 * string to byte then we add it to tree array list
	 */
	public void buildGeneralTable() {
		for (int i = 0; i < freqTable.length; i++) {
			if (freqTable[i] != 0) {
				String huffStr = tree.findHuffmanCode((byte) i);
				if (!huffStr.isEmpty()) {
					table.add(new HuffTable((byte) i, huffStr, freqTable[i]));

				}
			}

		}
	}

	public boolean writeFile() {
		/*
		 * Header:
		 * 
		 * 
		 */
		String str = files.getPath().replace("\\", "\\\\");

		int indexDot = str.indexOf('.');
		String name = str.substring(0, indexDot);
		String oldExtenstion = str.substring(indexDot + 1); // now i cut the name(path) and the extension of it
		
		byte[] headerBytes = StringToArrByte(Header);
		byte[] dataBytes = getData();

		int headerSize = headerBytes.length;
		int originalFileSize = (int) files.length();
		// (4=total size) + (4= header size) +(4= original file size)
		int fileTotalSize = 8 + 4 + 4 + 4 + 4 + headerSize + dataBytes.length;

		try {

			DataOutputStream outputs = new DataOutputStream(new FileOutputStream(name + ".huf"));
			// write the information
			for (int i = 0; i < 4; i++) {
				if (oldExtenstion.length() == 3 && i == 3) {
					outputs.writeChar('0');

					break;
				}
				outputs.writeChar(oldExtenstion.charAt(i));

			}
			outputs.writeInt(fileTotalSize);

			outputs.writeInt(headerSize);

			outputs.writeInt(originalFileSize);

			outputs.writeInt(dataBytes.length);

			outputs.write(headerBytes);

			/*
			 * Data:
			 * 
			 * 
			 */

			outputs.write(dataBytes);
			// Closing the stream
			outputs.flush();
			outputs.close();

			newFile = new File(name + ".huf");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	private byte[] getData() {
		StringBuilder binaryStringBuilder = new StringBuilder();
		try {

			// buffer of 8 byte = 64 bit
			byte[] buffer = new byte[8];
			String path = files.getPath().replace("\\", "\\\\");
			DataInputStream inputs = new DataInputStream(new FileInputStream(path));
			while (inputs.available() != 0) { // inputs.available() != 0 this method tell you the end of the binary file
				int bufferSize = inputs.read(buffer);// this method return the number of byte that acutely read
				for (int j = 0; j < bufferSize; j++)
					for (int i = 0; i < table.size(); i++) { // search to equals
						if (table.get(i).getData().equals(buffer[j])) {
							binaryStringBuilder.append(table.get(i).getHuffmanCode());
							break;
						} else {
						}
					}
			}
			inputs.close();
			return StringToArrByte(binaryStringBuilder.toString());

		} catch (FileNotFoundException e) { // handle if the file not found
			e.printStackTrace();
		} catch (IOException e) {// handle the end of the file --> you must do it
			e.printStackTrace();
		}
		return null;

	}

	private byte[] StringToArrByte(String data) {
		byte[] byteArray = new byte[(data.length() + 7) / 8]; // this (string.length() + 7) / 8 to fill any missing bit
		for (int i = 0, j = 0; i < data.length() && j < byteArray.length;) {
			int step = Math.min((i + 8), data.length());
			String str = data.substring(i, step);
			i += (step - (i));

			byteArray[j] = (byte) (Integer.parseInt(str, 2) & 0xFF);
			j++;
			// starting cutting form 8 * number of byte end the cut by the next byte or
			// string length if the length is smaller
		}
		return byteArray;
	}
}
