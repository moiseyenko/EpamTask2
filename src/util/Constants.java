package util;

public interface Constants {

	String PATH = "textEng.txt";
	String PARAGRAPH = "\\S+.*\n*";
	String LISTING = "/\\* #((?s).*?)/###/";
	String START_LISTING = "/* #";
	String SENTENCE = "[^.\\.?!]*[\\.?!]\n*";
	String WORD = "([^(\\s)]*)[\\p{Blank}[\\.\n*][?\n*][!\n*]]";
	String CHAR = "(?s).{1}";
	String CONSONANT_WORD = "[bcdfghjklmnpqrstvxzw][^\\.?!;:,]*";
	String NEW_LINE = "\n";

}
