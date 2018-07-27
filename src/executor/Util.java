package executor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.CharSeqComponent;
import entity.CharSeq;
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
	
	public static CharSeq firstConsonantWordRemove(int wordSize, StringBuilder filetext){
		
		CharSeqComponent words = Parser.parseSentence(filetext.toString());
		List<CharSeq> wordList = words.getList();
		List<CharSeq> elementForRemoveList = new ArrayList<>();
		for(CharSeq element:wordList) {
			CharSeqComponent charSeqElement = (CharSeqComponent) element;
			if(charSeqElement.getList().size()-1==wordSize) {
				if(charSeqElement.getCharSequence().matches("[bcdfghjklmnpqrstvxzw][^\\.?!;:,]*")) {
					System.out.println("!!!!!! "+charSeqElement.getCharSequence());
					elementForRemoveList.add(charSeqElement);
				}
			}
		}
		wordList.removeAll(elementForRemoveList);
		words.setList(wordList);
		return words;
	}
	
}
