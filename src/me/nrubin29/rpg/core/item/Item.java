package me.nrubin29.rpg.core.item;

import me.nrubin29.rpg.core.data.DataManager;
import me.nrubin29.rpg.core.data.files.PlayerData;
import me.nrubin29.rpg.core.entity.Interactable;
import me.nrubin29.rpg.core.misc.Image;
import me.nrubin29.rpg.core.util.ImageUtil;

import javax.swing.*;

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
        return ImageUtil.getImage("logo");
    }

    public ImageIcon getImage(int width, int height) {
        return ImageUtil.getImage("logo", width, height);
    }

    public void interact() {
        //TODO: Remove from map?
        ((PlayerData) DataManager.getInstance().getConfigurationFile(PlayerData.class)).addItem(this);
    }

    public abstract void use();
}