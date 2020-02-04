package finiteAutomata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {
	
	private int numOfStates = 0;
	private String[] actions;
	private int startingState = -1;
	private String[] finalStates;
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String[]> getMoves() {
		Path myPath = Paths.get("input.txt");
		ArrayList<String[]> runs = new ArrayList<String[]>();
		
		try {
			Stream<String>lines = Files.lines(myPath);
			Spliterator<String> result = lines.collect(Collectors.toList()).spliterator();
			int current = 0;
			while (result.tryAdvance(line -> runs.add(line.split(" ")))) {
				current++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return runs;
	}

	public ArrayList<Rule> createAllRules() {		
		ArrayList<Rule> rules = new ArrayList<Rule>();
		Path myPath = Paths.get("dfa.txt");
        try {
        	 Stream<String>lines = Files.lines(myPath).skip(3);
        	 Spliterator<String> result = lines.collect(Collectors.toList()).spliterator();
        	 int current = 0;
        	 while (result.tryAdvance(line -> rules.add(new Rule(line.split(" "))))) {
        		 current++;
        	 }
			 numOfStates = Files.lines(myPath).map(character -> character.charAt(0) - 48).findFirst().get();
			 actions = Files.lines(myPath).skip(1).map(line -> line.split(" ")).findFirst().get();
			 finalStates = Files.lines(myPath).skip(3).map(line -> line.split(" ")).findFirst().get();
			 startingState = Files.lines(myPath).skip(2).map(character -> character.charAt(0) - 48).findFirst().get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return rules;
	}

/*		
			public ArrayList<Rule> createAllRules() {
		File file = new File("dfa.txt");
	
		ArrayList<Rule> rules = new ArrayList<Rule>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			int cnt = 0;
			while ((st = br.readLine()) != null) {
				if (0 == cnt) {
					numOfStates = st.charAt(0) - 48;
				}
				else if (1 == cnt) {
					actions = st.split(" ");
				}
				else if (2 == cnt) {
					startingState = st.charAt(0) - 48;
				} else if (3 == cnt) {
					finalStates = st.split(" ");
				} else {
					String[] str = st.split(" ");
					if (str.length == 3) {
						rules.add(new Rule(str));
					}
				}
				cnt++;
		  	}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return rules;
	}
*/		
		
		
		
		
		
		
		
		
		
		
		


	public int getNumOfStates() {
		return numOfStates;
	}

	public void setNumOfStates(int numOfStates) {
		this.numOfStates = numOfStates;
	}

	public String[] getActions() {
		return actions;
	}

	public void setActions(String[] actions) {
		this.actions = actions;
	}

	public int getStartingState() {
		return startingState;
	}

	public void setStartingState(int startingState) {
		this.startingState = startingState;
	}

	public String[] getFinalStates() {
		return finalStates;
	}
}
