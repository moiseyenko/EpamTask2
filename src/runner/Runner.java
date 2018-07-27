package runner;

import java.util.List;
import entity.MyCharSequence;
import executor.Util;
import parser.Listitng;
import parser.Parser;

public class Runner {
	
	public static void main(String[] args) {
		
		String path = "textEng.txt";
		StringBuilder filetext = Util.readFile(path);
		System.out.println(filetext);
		
		
		System.out.println("1)//////////////////////////////////////////////////////////////////");
		int wordSize = 5;
		MyCharSequence words = Util.firstConsonantWordRemove(wordSize, filetext);
		System.out.println(words.getCharSequence());
		
		System.out.println("2)//////////////////////////////////////////////////////////////////");
		List<String> listingList = Listitng.getListingList(filetext);
		filetext = Listitng.ReplaceExistingListings(filetext, listingList);
		System.out.println(filetext);
		  
		System.out.println("!!!!!!!!!!!");
		StringBuilder newFileText = new StringBuilder(Parser.parseText(filetext).getCharSequence());
		System.out.println(newFileText);
		
		newFileText = Listitng.recoveryExistingListings(newFileText, listingList);
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println(newFileText);


	}
}
