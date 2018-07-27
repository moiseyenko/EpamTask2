package executor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.MyCharSequence;
import parser.Parser;

public class Util {
	
	public static StringBuilder readFile (String path) {
		StringBuilder filetext = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(new File(path)))){
			String line;
			while((line = br.readLine()) != null) {
				filetext.append(line.trim()+"\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filetext;
	}
	
	public static MyCharSequence firstConsonantWordRemove(int wordSize, StringBuilder filetext){
		
		MyCharSequence words = Parser.parseSentence(filetext.toString());
		List<MyCharSequence> wordList = words.getList();
		List<MyCharSequence> elementForRemoveList = new ArrayList<>();
		for(MyCharSequence element:wordList) {
			
			if((element.getList().size()-1==wordSize)) {
				System.out.println(element.getCharSequence());
				if(element.getCharSequence().matches("[^aeiouy][^\\.?!;:,]*")) {
					System.out.println("!!!!!! "+element.getCharSequence());
					elementForRemoveList.add(element);
				}
			}
		}
		wordList.removeAll(elementForRemoveList);
		words.setList(wordList);
		return words;
	}
	
}
