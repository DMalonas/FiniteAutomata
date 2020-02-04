package finiteAutomata;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import finiteAutomata.Rule;

public class RunAutomato {
  
	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
		FileUtil input = new FileUtil();
		ArrayList<Rule> rules = input.createAllRules();
		ArrayList<String[]> runs = input.getMoves();

		int numOfStates = input.getNumOfStates();
		String[] actions = input.getActions();
		String[] finalStates = input.getFinalStates();
		int currentState = input.getStartingState();
		
		AutomataMap aum;
		int mode = -1;
		do {
			System.out.print("Manual mode || File mode [0/1]: ");
			mode = sc.nextInt();
		} while((mode != 1) && (mode != 0));
		if (0 == mode) {
			aum= new AutomataMap(rules, numOfStates, actions, currentState, finalStates);
			while (true) {
				String currentActions = aum.getPossibleActions(currentState, rules, numOfStates, actions);
	        	System.out.print("State:   " + currentState + "\t|Action:  " + currentActions + "\n:: ");
	        	currentState = aum.updateCurrentState(sc.next(), currentActions, currentState);
	        	if (aum.isFinalState(currentState)) {
	        		int decision = -1;
	        		do {
	        			System.out.print("Reached final state " + currentState + ". Continue: [Yes == 0 / No == 1] ");
	        			decision = sc.nextInt();
	        		} while((decision != 1) && (decision != 0));
	        		if (1 == decision) {
	        			System.out.println("Exit program");
	        			break;
	        		}
	        	}
			}
		} else if (1 == mode) {
			ArrayList<AutomataMap> aList = new ArrayList<AutomataMap>();
			for (String[] e : runs) {
				int tempCurrentState = input.getStartingState();
				ArrayList<Rule> tempRules = input.createAllRules();
				int tempNumOfStates = input.getNumOfStates();
				String[] tempActions = input.getActions();
				aList.add(new AutomataMap(tempRules, tempNumOfStates, tempActions, tempCurrentState, finalStates));

				for (String st : e) {
					//System.out.print(st + " ");
					String currentActions = aList.get(0).getPossibleActions(tempCurrentState, tempRules, tempNumOfStates, tempActions);
		        	System.out.print("State:   " + tempCurrentState + "\t|Possible actions:  " + currentActions + 
		        			"\t|Action read from file: " + st + "\n");
		        	tempCurrentState = aList.get(0).updateCurrentState(st, currentActions, tempCurrentState);
				}
				if (aList.get(0).isFinalState(tempCurrentState)) {
					System.out.println("Input finished. Last state is " + tempCurrentState + " and it is  a final state (Success).");
				} else {
					System.out.println("Input finished. Last state is " + tempCurrentState + " and it is not a final state (Failure).");
				}
				aList.remove(0);
				System.out.println();
			}
		}	
    }
}
