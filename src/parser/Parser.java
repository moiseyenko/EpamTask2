package parser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Char;
import entity.CharSeqComponent;
import entity.MyCharSequence;

public class Parser {
	
	static final  String PARAGRAPH = "\\S+.*";
	static final  String SENTENCE = "[^.\\.?!]*[\\.?!]\n*";
	static final  String WORD = "([^(\\s)]*)[\\p{Blank}[\\.\n*][?\n*][!\n*]]";
	static final  String CHAR = ".{1}";
	
	
	public static MyCharSequence parseText(StringBuilder text){
		
		CharSeqComponent textComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(PARAGRAPH);
	    Matcher matcher = pattern.matcher(text);
	    
	    while (matcher.find()) {
		      String element = matcher.group();
		      textComponent.addElement(parseParagrahp(element+"\n"));
	    }
		return textComponent;
	}


	public static MyCharSequence parseParagrahp(String text) {
		
		CharSeqComponent paragraphComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(SENTENCE);
	    Matcher matcher = pattern.matcher(text);
	    
	    while (matcher.find()) {
		      String element = matcher.group();
		      paragraphComponent.addElement(parseSentence(element));
		    }
		
		return paragraphComponent;
	}


	public static MyCharSequence parseSentence (String text) {
		
		CharSeqComponent sentenceComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(WORD);
	    Matcher matcher = pattern.matcher(text);
	    
	    while (matcher.find()) {
		      String element = matcher.group();
		      sentenceComponent.addElement(parseWord(element));
		    }
		
		return sentenceComponent;
	}
	
	public static MyCharSequence parseWord (String text) {
		
		CharSeqComponent wordComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(CHAR, Pattern.DOTALL);
	    Matcher matcher = pattern.matcher(text);
	    
	    while (matcher.find()) {
		      String element = matcher.group();
		      wordComponent.addElement(new Char(element));
		    }
		
		return wordComponent;
	}
	
	
}
