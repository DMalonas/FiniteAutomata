package finiteAutomata;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import finiteAutomata.Rule;

public class RunAutomato {
  
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		FileUtil input = new FileUtil();
		ArrayList<Rule> rules = input.createAllRules();
		int numOfStates = input.getNumOfStates();
		String[] actions = input.getActions();
		int currentState = input.getStartingState();
		AutomataMap aum= new AutomataMap(rules, numOfStates, actions, currentState);
		while (true) {
			String currentActions = aum.getPossibleActions(currentState, rules, numOfStates, actions);
        	System.out.print("State:   " + currentState + "\t|Action:  " + currentActions + "\n:: ");
        	currentState = aum.updateCurrentState(sc.next(), currentActions, currentState);
		}
    }
}
