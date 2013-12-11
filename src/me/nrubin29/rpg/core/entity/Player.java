package me.nrubin29.rpg.core.entity;

import java.awt.Point;

public class Player extends Entity {

    private String name;

	public Player(String name, Point spawn) {
		super("Player", spawn);
        this.name = name;
	}

    public String getName() {
        return name;
    }

    public void interact() { /* No need to do anything. */ }
}