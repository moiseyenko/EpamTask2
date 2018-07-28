package runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.CharSeq;
import util.Constants;
import util.Helper;
import util.Parser;

public class Runner {

	private static final Logger LOG = LogManager.getLogger(Runner.class);

	public static void main(String[] args) {

		StringBuilder filetext = Helper.readFile(Constants.PATH);
		if (LOG.isInfoEnabled()) {
			LOG.info("\n" + filetext);
		}
		if (LOG.isInfoEnabled()) {
			LOG.info(Constants.NEW_LINE + "1)//////////////////////" + Constants.NEW_LINE + "Enter a word size");
		}
		int wordSize = Helper.readNumber();
		CharSeq words = Helper.firstConsonantWordRemove(wordSize, filetext);
		if (LOG.isInfoEnabled()) {
			LOG.info("\n" + words.getCharSequence());
		}
		if (LOG.isInfoEnabled()) {
			LOG.info(Constants.NEW_LINE + "2)//////////////////////");
		}
		filetext = new StringBuilder(Parser.parseText(filetext).getCharSequence());
		if (LOG.isInfoEnabled()) {
			LOG.info(Constants.NEW_LINE + filetext);
		}

	}
}
