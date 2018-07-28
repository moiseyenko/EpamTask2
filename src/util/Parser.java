package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.CharSeqLief;
import entity.CharSeqComponent;

public class Parser {

	private static Logger LOG = LogManager.getLogger(Parser.class);

	// Divide text by paragraphs and if there are, by listings
	public static CharSeqComponent parseText(StringBuilder text) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start parseText with text: " + text.toString());
		}
		CharSeqComponent textComponent = new CharSeqComponent();
		Pattern patternParagraph = Pattern.compile(Constants.PARAGRAPH);
		Matcher matcherParagraph = patternParagraph.matcher(text);
		Pattern patternListing = Pattern.compile(Constants.LISTING);
		Matcher matcherListing = patternListing.matcher(text);
		while (matcherParagraph.find()) {
			String elementParagraph = matcherParagraph.group();
			if (elementParagraph.startsWith(Constants.START_LISTING)) {
				int startPosition = matcherParagraph.start();
				if (matcherListing.find(startPosition)) {
					String elementListing = matcherListing.group();
					textComponent.addElement(new CharSeqLief(elementListing + Constants.NEW_LINE));
					int elementListingLength = elementListing.length();
					int endListingPosition = startPosition + elementListingLength - 1;
					matcherParagraph.find(endListingPosition);
				}
				continue;
			}
			textComponent.addElement(parseParagrahp(elementParagraph));
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("End parseText with textComponent size: " + textComponent.getList().size());
		}
		return textComponent;
	}

	// Divide paragraph by sentences
	public static CharSeqComponent parseParagrahp(String text) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start parseParagrahp with paragraph: " + text);
		}
		CharSeqComponent paragraphComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(Constants.SENTENCE);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String element = matcher.group();
			paragraphComponent.addElement(parseSentence(element));
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("End parseParagrahp with paragraphComponent size: " + paragraphComponent.getList().size());
		}
		return paragraphComponent;
	}

	// Divide sentence by words
	public static CharSeqComponent parseSentence(String text) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start parseSentence with sentence: " + text);
		}
		CharSeqComponent sentenceComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(Constants.WORD);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String element = matcher.group();
			sentenceComponent.addElement(parseWord(element));
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("End parseSentence with sentenceComponent size: " + sentenceComponent.getList().size());
		}
		return sentenceComponent;
	}

	// Divide word by chars
	public static CharSeqComponent parseWord(String text) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start parseWord with word: " + text);
		}
		CharSeqComponent wordComponent = new CharSeqComponent();
		Pattern pattern = Pattern.compile(Constants.CHAR);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String element = matcher.group();
			wordComponent.addElement(new CharSeqLief(element));
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("End parseWord with wordComponent size: " + wordComponent.getList().size());
		}
		return wordComponent;
	}

}
