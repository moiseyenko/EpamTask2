package util;

public interface Constants {

	String PATH_SRC = "textEng.txt";
	String PATH_RESULT = "result.txt";
	String PATH_COMPOSE = "compose.txt";
	String PARAGRAPH = "\\S+.*\n*";
	String LISTING = "/\\* #((?s).*?)}\n{2}";
	String START_LISTING = "/* #";
	String SENTENCE = "[^.\\.?!]*[\\.?!]\n*";
	String WORD = "([^(\\s)]*)[\\p{Blank}[\\.\n*][?\n*][!\n*]]";
	String CHAR = "(?s).{1}";
	String CONSONANT_WORD = "[bcdfghjklmnpqrstvxzw][^\\.?!;:,]*";
	String NEW_LINE = "\n";
	String START_PARAGRAPH = "!PARAGRAPH:";
}
