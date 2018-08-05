package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Composite;
import entity.TextElement;
import util.parser.BaseParser;

public class Helper {

	private final static Logger LOG = LogManager.getLogger(Helper.class);

	// Read file and return StringBuilder with text form file
	public static StringBuilder readFile(String path) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start readFile with path: {}", path);
		}
		StringBuilder filetext = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
			String line;
			while ((line = br.readLine()) != null) {
				filetext.append(line.trim()).append(Constants.NEW_LINE);
			}
		} catch (FileNotFoundException e) {
			if (LOG.isInfoEnabled()) {
				LOG.info("File not found!!!");
			}
			if (LOG.isErrorEnabled()) {
				LOG.error("File for parsing not found", e);

			}
		} catch (IOException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Input or output exception", e);
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("End readFile with stringbuilder length : {}", filetext.length());
		}
		return filetext;
	}

	// Read number entered from the console
	public static int readNumber() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start readNumber");
		}
		int number = 0;
		try (Scanner sc = new Scanner(System.in)) {
			if (sc.hasNext()) {
				number = sc.nextInt();
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("End readNumber with number: " + number);
		}
		return number;
	}

	// Find and remove all words starting with a consonant letter
	public static TextElement firstConsonantWordRemove(int wordSize, StringBuilder filetext) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start firstConsonantWordRemove with wordSize: " + wordSize + " and filetext length: "
					+ filetext.length());
		}

		Composite words = new BaseParser(Constants.WORD).parse(filetext.toString());
		// Composite words = Parser.parseSentence(filetext.toString());
		List<TextElement> wordList = words.getList();
		List<TextElement> elementForRemoveList = new ArrayList<>();
		for (TextElement element : wordList) {
			String stringElement = element.getCharSequence();
			if (stringElement.length() - 1 == wordSize) {
				if (stringElement.matches(Constants.CONSONANT_WORD)) {
					if (LOG.isInfoEnabled()) {
						LOG.info("\n!! {}", stringElement);
					}
					elementForRemoveList.add(element);
				}
			}
		}
		wordList.removeAll(elementForRemoveList);
		if (LOG.isDebugEnabled()) {
			LOG.debug(elementForRemoveList.size() + " words will be remove");
		}
		words.setList(wordList);
		if (LOG.isDebugEnabled()) {
			LOG.debug("End firstConsonantWordRemove");
		}
		return words;
	}

	public static void writeText(String text, String path) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path), true))) {
			bw.write(text + "\n");
		} catch (IOException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error(
						"The file exists or does not exist but cannot be created, or cannot be opened for any other reason",
						e);
			}
		}
	}

	public static void ComposeText(StringBuilder text) {

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
					writeText(elementListing.substring(0, elementListing.length() - 1), Constants.PATH_COMPOSE);
					int elementListingLength = elementListing.length();
					int endListingPosition = startPosition + elementListingLength - 3;
					matcherParagraph.find(endListingPosition);
				}
				continue;
			} else if (elementParagraph.startsWith(Constants.START_PARAGRAPH)) {
				writeText(elementParagraph.substring(12, elementParagraph.length() - 2), Constants.PATH_COMPOSE);
			}
		}
	}

}
