package me.nrubin29.core.item;

import javax.swing.ImageIcon;

import me.nrubin29.core.entity.Interactable;
import me.nrubin29.core.misc.Image;
import me.nrubin29.core.util.ResourceUtil;

public abstract class Item implements Image, Interactable {

    private final String name, description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getImage() {
        return ResourceUtil.getImage("logo");
    }

    public ImageIcon getImage(int width, int height) {
        return ResourceUtil.getImage("logo", width, height);
    }

    public void interact() {
        Inventory.getInstance().addItem(this);
    }

    public abstract void use();
}