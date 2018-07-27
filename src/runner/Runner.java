package runner;

import entity.CharSeq;
import executor.Util;
import parser.Parser;

public class Runner {
	
	public static void main(String[] args) {
		
		String path = "textEng.txt";
		StringBuilder filetext = Util.readFile(path);
		System.out.println(filetext);
		
		
		System.out.println("1)//////////////////////////////////////////////////////////////////");
		int wordSize = 5;
		CharSeq words = Util.firstConsonantWordRemove(wordSize, filetext);
		System.out.println(words.getCharSequence());
		
		System.out.println("2)//////////////////////////////////////////////////////////////////");
		filetext = new StringBuilder(Parser.parseText(filetext).getCharSequence());
		System.out.println(filetext);


	}
}
