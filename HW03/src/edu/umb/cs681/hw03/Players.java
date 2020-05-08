package edu.umb.cs681.hw03;

import java.util.List;

public class Players {

	int minutesPlayed, fieldGoals, goalAttempts ;

	public Players(int minutesPlayed, int fieldGoals, int goalAttempts) {
		this.minutesPlayed = minutesPlayed;
		this.fieldGoals = fieldGoals;
		this.goalAttempts = goalAttempts;
	}

	@Override
	public String toString() {
		return "Players [minutesPlayed=" + minutesPlayed + ", fieldGoals=" + fieldGoals + ", goalAttempts="
				+ goalAttempts + "]";
	}

	public int getMinutesPlayed() {
		return minutesPlayed;
	}

	public int getFieldGoals() {
		return fieldGoals;
	}

	public float getGoalAttempts() {
		return goalAttempts;
	}

	
}
