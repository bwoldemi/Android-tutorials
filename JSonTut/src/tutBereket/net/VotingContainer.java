package tutBereket.net;

import java.util.Vector;

import android.util.Log;

public class VotingContainer {
	private Vector<Voting> votings = null;
	private boolean includeClosed = false;
	private boolean includeOpen = true; // Include only open votings by default.
	private boolean includeFuture = false;

	public VotingContainer() {
		votings = new Vector<Voting>();
	}

	public void add(Voting v) {
		boolean found = false;
		// Check if the Voting v is already in the container.
		// - For this, you need to use Voting.equals(), see note below.
		// If voting is found
		// merge (see discussion below the code), the
		// Voting v to the one already in the container.
		// Otherwise, if voting is not found
		// add the Voting v into the container.
	}

	public void remove(Voting v) {
		// Remove the voting from the vector.
	}

	public void clear() {
		// Remove all votings from the vector.
	}

	public int size() {
		int count = 0;
		// Count how many votings there are in the container,
		// considering the voting filtering. In a for loop, use
		// the method isIncludedByFilter to check if a voting
		// should be included in the count.
		return count;
	}

	private boolean isIncludedByFilter(Voting v) {
		// Determine if the voting is included by the filter.
		// Use the Voting methods isUpcoming(), isOpen(), and isClosed()
		// which you implemented in the View, Gestures and Intents exercise,
		// as well as the boolean member variables in this class
		// (includedClosed, etc.)
		// to determine if a voting is included in the current filter.
		// Return true if the voting is included by the filter(s), otherwise
		// false.
		return false;
	}

	public void applyFilter(boolean inclClosed, boolean inclOpen,
			boolean inclFuture) {
		includeClosed = inclClosed;
		includeOpen = inclOpen;
		includeFuture = inclFuture;
	}

	public Voting get(int index) {
		int includedInd = 0;
		// Go through all the votings and check if the voting
		// is included by the filter. If yes, and the index
		// parameter equals the counter in the for loop, this is
		// the requested object; return it. Otherwise, return null.
		return null;
	}

	public int getIndex(Voting voting) {
		int index = -1;
		// Gets the index of the specified voting object.
		// Go through all the votings (no need to consider the filter)
		// and if some voting object in the vector equals the parameter,
		// return its index in the vector.
		return index;
	}

	

}