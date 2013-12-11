package me.nrubin29.rpg.core.item;

public class Apple extends Item {

    public Apple() {
        super("Apple", "A small snack.");
    }

    public void use() {
        System.out.println("You ate an apple!");
    }
}