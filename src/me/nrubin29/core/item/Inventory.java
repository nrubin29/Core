package me.nrubin29.core.item;

import java.util.ArrayList;

public class Inventory {

	private Inventory() {
		for (int i = 0; i < 5; i++) addItem(new Apple());
	}
	
	private static Inventory instance = new Inventory();
	
	public static Inventory getInstance() {
		return instance;
	}
	
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
}