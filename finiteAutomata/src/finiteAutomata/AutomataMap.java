package finiteAutomata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class AutomataMap {
	//private fields
	private ArrayList<Rule> rules;;
	//private String[] actions;
	private int numOfStates;
	private int startingState ;
	List<String> actions;
	

	public AutomataMap(ArrayList<Rule> rules, int numOfStates, String[] actions, int startingState) {
		this.rules = rules;
		this.actions = new ArrayList<String>();
		this.actions = Arrays.asList(actions);
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
		String actionsStr = String.join(" ", actions);
		String[] actionsReturn = actionsStr.split(" ");
		return actionsReturn;
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
//		for (int i = 0; i < numOfStates; i++) {
//			if (currentState == i) {
//		        for(Rule rule : rules) {
//		        	if (rule.getCurrentState() -48 == currentState) {
//		        		actionsForCurrentState += rule.getTransition() + " ";
//		        	}
//		        }
//			}
//		}
		final int currentStateForLambda = currentState;
		List<Rule> appropriateRules = rules.stream()
				.filter(r -> ((r.getCurrentState() - 48) == currentStateForLambda))
				.collect(Collectors.toCollection(() -> new ArrayList<Rule>()));
		
		List<String> actionsFromAppropriateRules = 
				appropriateRules.stream()
			    .map(Rule::getTransition)
			    .collect(Collectors.toList());
		
		actionsForCurrentState = actionsFromAppropriateRules.spliterator()
				.toString()
				.join(" ", actionsFromAppropriateRules);
		
		return actionsForCurrentState;
//		long count = allArtists.stream()
//				.filter(artist -> {                           
//					System.out.println(artist.getName());
//					return artist.isFrom("London");                       
//					})
//				.count();
//		String ac = appropriateRules.stream()
//				.filter(rule -> {
//						rule.getTransition();
//				})
//		List<SomeAttribute> attributes = original.stream().map(sc -> sc.getSomeAttribute()).collect(Collectors.toList());
		
//			              .flatMap(e->e.getFriends().stream())
//			              .collect(Collectors.toList());

//			    List<String> names = 
//			    personList.stream()
//			              .map(Person::getName)
//			              .collect(Collectors.toList());
	}

	public int updateCurrentState(String actionSelected, String currentActions, int currentState) {
		String action = actionSelected;
		final int currentStateForLambda = currentState;
//		for (int i = 0; i < actions.length; i++) {
//			if (action.equals(actions[i])) {
				List<Rule> rule = rules.stream()
					.filter(r -> (((r.getCurrentState() - 48) == currentStateForLambda) &&
							r.getTransition().equals(action) && actions.contains(action) ))
					.collect(Collectors.toCollection(() -> new ArrayList<Rule>()));
				if (!rule.isEmpty()) {
					currentState = rule.get(0).getFinalState() - 48;
				}
//				for (Rule rule : rules) {
//					if ((rule.getCurrentState() - 48 == currentState)) {
//						if (rule.getTransition().equals(action)) {
//							currentState = rule.getFinalState() - 48;
//							break;
//						}
//					}
//				}
//			}
//		}
		return currentState;
	}

}
