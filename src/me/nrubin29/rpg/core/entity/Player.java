package me.nrubin29.rpg.core.entity;

public class Player extends Entity {

    private String name;

	public Player(String name) {
		super("Player");
        this.name = name;
	}

    public String getName() {
        return name;
    }
}