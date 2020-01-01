package finiteAutomata;

import java.util.ArrayList;

public class AutomataMap {
	//private fields
	private ArrayList<Rule> rules;;
	private String[] actions;
	private int numOfStates;
	private int startingState ;

	public AutomataMap(ArrayList<Rule> rules, int numOfStates, String[] actions, int startingState) {
		this.rules = rules;
		this.actions = actions;
		this.numOfStates = numOfStates;
		this.startingState = startingState;
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public void setRules(ArrayList<Rule> rules) {
		this.rules = rules;
	}

	public String[] getActions() {
		return actions;
	}

	public void setActions(String[] actions) {
		this.actions = actions;
	}

	public int getNumOfStates() {
		return numOfStates;
	}

	public void setNumOfStates(int numOfStates) {
		this.numOfStates = numOfStates;
	}

	public int getStartingState() {
		return startingState;
	}

	public void setStartingState(int startingState) {
		this.startingState = startingState;
	}

	public String getPossibleActions(int currentState, ArrayList<Rule> rules, int numOfStates, String[] actions) {
		String actionsForCurrentState = "";
		for (int i = 0; i < numOfStates; i++) {
			if (currentState == i) {
		        for(Rule rule : rules) {
		        	if (rule.getCurrentState() -48 == currentState) {
		        		actionsForCurrentState += rule.getTransition() + " ";
		        	}
		        }
			}
		}
		return actionsForCurrentState;
	}

	public int updateCurrentState(String actionSelected, String currentActions, int currentState) {
		String action = actionSelected;
		for (int i = 0; i < actions.length; i++) {
			if (action.equals(actions[i])) {

				for (Rule rule : rules) {
					if ((rule.getCurrentState() - 48 == currentState)) {
						if (rule.getTransition().equals(action)) {
							currentState = rule.getFinalState() - 48;
							break;
						}
					}
				}
			}
		}
		return currentState;
	}

}
