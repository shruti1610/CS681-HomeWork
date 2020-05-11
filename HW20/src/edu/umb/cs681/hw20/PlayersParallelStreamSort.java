package edu.umb.cs681.hw20;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersParallelStreamSort {
public static void main(String[] args) {
	List<Players> players = getPlayers();
	
	System.out.println("Players List");
	players.forEach(System.out::println);
	System.out.println(" ");
	System.out.println("Minutes Played Sorted Using Parallel Stream ");
	printMinutesPlayedSorted(players);
	
	System.out.println(" ");
	System.out.println("Goal Attempts Sorted Using Parallel Stream");
	printGoalAttemptsSorted(players);
	
	System.out.println(" ");
	System.out.println("Field Goals Sorted Using Parallel Stream");
	printFieldGoalsSorted(players);
	
}

public static void printMinutesPlayedSorted(List<Players> players){
	List<Players> sortedList = players.stream().parallel()
			.sorted(Comparator.comparingDouble(Players::getMinutesPlayed))
			.collect(Collectors.toList());
 
        sortedList.forEach(System.out::println);
}

public static void printGoalAttemptsSorted(List<Players> players){
	List<Players> sortedList = players.stream().parallel()
			.sorted(Comparator.comparingDouble(Players::getGoalAttempts))
			.collect(Collectors.toList());
 
        sortedList.forEach(System.out::println);
}

public static void printFieldGoalsSorted(List<Players> players){
	List<Players> sortedList = players.stream().parallel()
			.sorted(Comparator.comparingInt(Players::getFieldGoals))
			.collect(Collectors.toList());
 
        sortedList.forEach(System.out::println);
}



public static List<Players> getPlayers(){
	List<Players> players = new ArrayList<Players>();
	Players player1 = new Players(2043, 240, 1113);
	Players player2 = new Players(478, 13, 420);
	Players player3 = new Players(1501, 148, 800);
	Players player4 = new Players(300, 20, 279);

	players.add(player1);
	players.add(player2);
	players.add(player3);
	players.add(player4);
	
	
	
	return players;
}
}
