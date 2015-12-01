package actor;

import java.util.ArrayList;

public class Party {

	private Player player;

	private ArrayList<Ally> partyMemebers;

	public Party(Player player, ArrayList<Ally> partyMembers) {
		this.player = player;
		this.partyMemebers = partyMembers;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Ally> getPartyMemebers() {
		return partyMemebers;
	}

	public void setPartyMemebers(ArrayList<Ally> partyMemebers) {
		this.partyMemebers = partyMemebers;
	}
}
