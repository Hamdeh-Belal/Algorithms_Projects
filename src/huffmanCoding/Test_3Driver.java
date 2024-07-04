package huffmanCoding;

import java.io.File;

public class Test_3Driver {

	public static void main(String[] args) {
		HuffmanCodeComp compH = new HuffmanCodeComp(new File("C:\\Users\\Belal Hamdeh\\Desktop\\Drag drop example.txt"));

		System.out.println("Comp is Done Info :\n"+compH.getInfo()+"\n");
		HuffmanCodeDeComp deCompH= new HuffmanCodeDeComp(new File("C:\\Users\\Belal Hamdeh\\Desktop\\Drag drop example.huf"));

		System.out.println("DeComp is Done Info : \n"+deCompH.getInfo()+"\n");

	
	}

}
