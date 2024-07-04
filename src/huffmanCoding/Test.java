package huffmanCoding;

public class Test {

	public static void main(String[] args) {
		  String inputString = "1t1r1s0001w1g000";
	        StringBuilder binaryStringBuilder = new StringBuilder();

	        for (int i = 0; i < inputString.length(); i++) {
	            char c = inputString.charAt(i);
	            if (c == '1') {
	                binaryStringBuilder.append("1"); // Append 1 bit for '1'
	            } else {
	                // Convert other characters to their ASCII representation and append as 8-bit binary
	                String asciiBinary = Integer.toBinaryString(c);
	                // Ensure 8-bit representation
	                asciiBinary = String.format("%8s", asciiBinary).replace(' ', '0');
	                binaryStringBuilder.append(asciiBinary);
	            }
	        }

	        String binaryString = binaryStringBuilder.toString();

	        // Convert the binary string to bytes
	        byte[] byteArray = new byte[(binaryString.length() + 7) / 8];
	        for (int i = 0; i < byteArray.length; i++) {
	            byteArray[i] = (byte) Integer.parseInt(binaryString.substring(8 * i, Math.min(8 * (i + 1), binaryString.length())), 2);
	        }

	        // Printing the byte array
	        for (byte b : byteArray) {
	            System.out.print(b + " ");
	        }
	}
	private static String getExtentionCode(String ext) {
		String res="";
		 int[][] letters = new int[26][2];
		 
		 for (int i = 0; i < 26; i++) {
	            char letter = (char) ('a' + i); 
	            letters[i][0] = (byte) letter; 
	            letters[i][1] = i; 
	        }
		
		for(int i =0;i<ext.length();i++) {
			 for (int j = 0; j < 26; j++) {
				 if(letters[j][0]==ext.charAt(i))
					 res+=(byte)letters[j][1];
			 }
		}
		for (int i = 0; i < 26; i++) {
            char letter = (char) letters[i][0];
            int fiveBitRepresentation = letters[i][1];
            System.out.println(letter + ": " + letters[i][0] + ", " + fiveBitRepresentation );
        }
		System.out.println("\n--------------------------");
		return res;
	
		
	}
}
