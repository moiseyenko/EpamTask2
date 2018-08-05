package util.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Composite;
import entity.Leaf;
import util.Constants;
import util.Helper;

public class BaseParser implements Parser {

	private static final Logger LOG = LogManager.getLogger(BaseParser.class);

	Parser next;
	String regex;

	public BaseParser() {
		if (LOG.isInfoEnabled()) {
			LOG.info("Regex is not defined!");
		}
	}

	public BaseParser(String regex) {
		this.regex = regex;
	}

	public void setNext(Parser next) {
		this.next = next;
	}

	@Override
	public Composite parse(String text) {

		if (LOG.isDebugEnabled()) {
			LOG.debug("Start parse with text: \n{}", text);
		}
		Composite Component = new Composite();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String element = matcher.group();
			Helper.writeText(element, Constants.PATH_RESULT);
			if (next == null) {
				Component.addElement(new Leaf(element));
			} else {
				Component.addElement(next.parse(element));
			}

		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("End parse with Component size: {}", Component.getList().size());
		}
		return Component;

	}

}
