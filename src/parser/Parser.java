package parser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.CharSeqLief;
import entity.CharSeqComponent;

public class Parser {
	
	static final  String PARAGRAPH = "\\S+.*\n*";
	static final  String LISTING = "/\\* #((?s).*?)/###/";
	static final  String SENTENCE = "[^.\\.?!]*[\\.?!]\n*";
	static final  String WORD = "([^(\\s)]*)[\\p{Blank}[\\.\n*][?\n*][!\n*]]";
	static final  String CHAR = "(?s).{1}";
	
	
	public static CharSeqComponent parseText(StringBuilder text){
		
		CharSeqComponent textComponent = new CharSeqComponent();
		
		Pattern patternParagraph = Pattern.compile(PARAGRAPH);
	    Matcher matcherParagraph = patternParagraph.matcher(text);
		
	    Pattern patternListing = Pattern.compile(LISTING);
	    Matcher matcherListing = patternListing.matcher(text);
	    
	    while(matcherParagraph.find()) {
	    	String elementParagraph = matcherParagraph.group();
	    	if(elementParagraph.startsWith("/* #")) {
	    		int startPosition = matcherParagraph.start();
	    		if (matcherListing.find(startPosition)) {
	    			String elementListing = matcherListing.group();
	    			textComponent.addElement(new CharSeqLief(elementListing+"\n"));
	    			int elementListingLength = elementListing.length();
	    			matcherParagraph.find(startPosition+elementListingLength-1);
	    		}
	    		continue;
	    	}
	    	textComponent.addElement(Parser.parseParagrahp(elementParagraph));
	    }
		return textComponent;
	}


	public static CharSeqComponent parseParagrahp(String text) {
		
		CharSeqComponent paragraphComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(SENTENCE);
	    Matcher matcher = pattern.matcher(text);
	    
	    while (matcher.find()) {
		      String element = matcher.group();
		      paragraphComponent.addElement(parseSentence(element));
		    }
		
		return paragraphComponent;
	}


	public static CharSeqComponent parseSentence (String text) {
		
		CharSeqComponent sentenceComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(WORD);
	    Matcher matcher = pattern.matcher(text);
	    
	    while (matcher.find()) {
		      String element = matcher.group();
		      sentenceComponent.addElement(parseWord(element));
		    }
		
		return sentenceComponent;
	}
	
	public static CharSeqComponent parseWord (String text) {
		
		CharSeqComponent wordComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(CHAR);
	    Matcher matcher = pattern.matcher(text);
	    
	    while (matcher.find()) {
		      String element = matcher.group();
		      wordComponent.addElement(new CharSeqLief(element));
		    }
		
		return wordComponent;
	}
	
	
}
