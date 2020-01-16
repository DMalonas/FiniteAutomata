package finiteAutomata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtil {
	
	private int numOfStates = 0;
	private String[] actions;
	private int startingState = -1;

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
}
