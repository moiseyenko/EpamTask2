package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.CharSeqComponent;
import entity.CharSeq;

public class Helper {

	private final static Logger LOG = LogManager.getLogger(Helper.class);

	// Read file and return StringBuilder with text form file
	public static StringBuilder readFile(String path) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start readFile with path: " + path);
		}
		StringBuilder filetext = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
			String line;
			while ((line = br.readLine()) != null) {
				filetext.append(line.trim() + Constants.NEW_LINE);
			}
		} catch (FileNotFoundException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("File for parsing not found", e);
			}
		} catch (IOException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("Input or output exception", e);
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("End readFile with stringbuilder length : " + filetext.length());
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
	public static CharSeq firstConsonantWordRemove(int wordSize, StringBuilder filetext) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start firstConsonantWordRemove with wordSize: " + wordSize + " and filetext length: "
					+ filetext.length());
		}
		CharSeqComponent words = Parser.parseSentence(filetext.toString());
		List<CharSeq> wordList = words.getList();
		List<CharSeq> elementForRemoveList = new ArrayList<>();
		for (CharSeq element : wordList) {
			CharSeqComponent charSeqElement = (CharSeqComponent) element;
			if (charSeqElement.getList().size() - 1 == wordSize) {
				if (charSeqElement.getCharSequence().matches(Constants.CONSONANT_WORD)) {
					if (LOG.isDebugEnabled()) {
						LOG.debug(Constants.NEW_LINE + "!!" + charSeqElement.getCharSequence());
					}
					elementForRemoveList.add(charSeqElement);
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

}
