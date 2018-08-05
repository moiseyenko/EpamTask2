package runner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.TextElement;
import util.Constants;
import util.Helper;
import util.parser.BaseParser;
import util.parser.ParagraphListingParser;

public class Runner {

	private static final Logger LOG = LogManager.getLogger(Runner.class);

	public static void main(String[] args) {

		try {
			Files.deleteIfExists(Paths.get(Constants.PATH_RESULT));
		} catch (IOException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("an I/O error occurs", e);
			}
		}
		StringBuilder filetext = Helper.readFile(Constants.PATH_SRC);
		if (LOG.isInfoEnabled()) {
			LOG.info("\n{}", filetext);
		}
		BaseParser paragraphListings = new ParagraphListingParser();
		BaseParser sentences = new BaseParser(Constants.SENTENCE);
		BaseParser words = new BaseParser(Constants.WORD);
		BaseParser chars = new BaseParser(Constants.CHAR);
		paragraphListings.setNext(sentences);
		sentences.setNext(words);
		words.setNext(chars);
		paragraphListings.parse(filetext.toString());
		try {
			Files.deleteIfExists(Paths.get(Constants.PATH_COMPOSE));
		} catch (IOException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error("an I/O error occurs", e);
			}
		}
		StringBuilder resultText = Helper.readFile(Constants.PATH_RESULT);
		Helper.ComposeText(resultText);

		if (LOG.isInfoEnabled()) {
			LOG.info("\n\nEnter a word size");
		}
		int wordSize = Helper.readNumber();
		TextElement processedWords = Helper.firstConsonantWordRemove(wordSize, filetext);
		if (LOG.isInfoEnabled()) {
			LOG.info("\n{}", processedWords.getCharSequence());
		}
	}
}
