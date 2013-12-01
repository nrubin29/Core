package me.nrubin29.rpg.core.entity;

public class Player extends Entity {

    private String name;

	public Player(String name, int x, int y) {
		super("Player", x, y);
        this.name = name;
	}

    public String getName() {
        return name;
    }

    public void interact() { /* No need to do anything. */ }
}