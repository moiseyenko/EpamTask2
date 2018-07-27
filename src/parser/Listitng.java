package parser;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Listitng {
	
	public static List<String> getListingList (StringBuilder filetext){
		
		List<Integer> startIndexList = new ArrayList<>();
		String listingPattern = "/\\* #.*";
		Pattern pattern = Pattern.compile(listingPattern);
	    Matcher matcher = pattern.matcher(filetext);
	    
	    while (matcher.find()) {
		      String element = matcher.group();
		      int index = filetext.indexOf(element);
		      startIndexList.add(index); 
	    }
	   
	    List<String> rawListingList = new ArrayList<>();
	    String rawListing;
	    for(int i=0;i<startIndexList.size();i++) {
	    	if(i==startIndexList.size()-1) {
	    		rawListing = filetext.substring(startIndexList.get(i), filetext.length());
	    		rawListingList.add(rawListing);
	    	}else {
	    		rawListing = filetext.substring(startIndexList.get(i), startIndexList.get(i+1));
	    		rawListingList.add(rawListing);
	    	}
	    }
	    
	    List<Integer> endIndexList = new ArrayList<>();
	    for(String i:rawListingList) {
	    	int endIndex = i.lastIndexOf("}");
	    	endIndexList.add(endIndex);
	    }
	    
	    List<String> listingList = new ArrayList<>();
	    for(int i=0;i<rawListingList.size();i++) {
	    	String listing = rawListingList.get(i).substring(0, endIndexList.get(i)+1);
	    	listingList.add(listing);
	    }
	    return listingList;
	}
	
	public static StringBuilder ReplaceExistingListings (StringBuilder filetext, List<String> listingList) {
		
		if (listingList.size()>0) {
			for(int i=0;i<listingList.size();i++) {
				int start = filetext.indexOf(listingList.get(i));
				int end = start+listingList.get(i).length()+1;
				String replace = "/* # Listing "+i+" #.\n";
				filetext.replace(start, end, replace);
			}
		}
		return filetext;
	}
	
	public static StringBuilder recoveryExistingListings (StringBuilder newFileText, List<String> listingList) {
		if(listingList.size()>0) {
			for(int i=0;i<listingList.size();i++) {
				int start = newFileText.indexOf("/* # Listing "+i+" #.\n");
				int end = start+("/* # Listing "+i+" #.\n").length();
				String replace = listingList.get(i)+"\n";
				newFileText.replace(start, end, replace);
			}
		}
		return newFileText;
	}
	
	
	
	
	
	
}
