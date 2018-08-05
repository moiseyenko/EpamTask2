package util.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import entity.Composite;
import entity.Leaf;
import util.Constants;
import util.Helper;

public class ParagraphListingParser extends BaseParser {

	private static final Logger LOG = LogManager.getLogger(ParagraphListingParser.class);

	@Override
	public void setNext(Parser next) {
		super.setNext(next);
	}

	@Override
	public Composite parse(String text) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Start parseText with text: \n{}", text.toString());
		}
		Composite textComponent = new Composite();
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
					Helper.writeText(elementListing, Constants.PATH_RESULT);
					textComponent.addElement(new Leaf(elementListing));
					int elementListingLength = elementListing.length();
					int endListingPosition = startPosition + elementListingLength - 3;
					matcherParagraph.find(endListingPosition);
				}
				continue;
			}
			Helper.writeText("!PARAGRAPH: " + elementParagraph, Constants.PATH_RESULT);
			if (getNext() == null) {
				textComponent.addElement(new Leaf(elementParagraph));
			} else {
				textComponent.addElement(getNext().parse(elementParagraph));
			}
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("End parseText with textComponent size: {}", textComponent.getList().size());
		}
		return textComponent;
	}
}
