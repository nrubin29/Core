package me.nrubin29.citrine.npc;

import me.nrubin29.citrine.util.ImageUtil;

import javax.swing.*;

public enum Sprite {

    PLAYER;

    private ImageIcon downStatic, downMoving, leftStatic, leftMoving, rightStatic, rightMoving, upStatic, upMoving;

    Sprite() {
        downStatic = ImageUtil.getImage("sprites/" + name().toLowerCase() + "/down_static");
        downMoving = ImageUtil.getImage("sprites/" + name().toLowerCase() + "/down_moving");

        leftStatic = ImageUtil.getImage("sprites/" + name().toLowerCase() + "/left_static");
        leftMoving = ImageUtil.getImage("sprites/" + name().toLowerCase() + "/left_moving");

        rightStatic = ImageUtil.getImage("sprites/" + name().toLowerCase() + "/right_static");
        rightMoving = ImageUtil.getImage("sprites/" + name().toLowerCase() + "/right_moving");

        upStatic = ImageUtil.getImage("sprites/" + name().toLowerCase() + "/up_static");
        upMoving = ImageUtil.getImage("sprites/" + name().toLowerCase() + "/up_moving");
    }

    public ImageIcon getDownStatic() {
        return downStatic;
    }

    public ImageIcon getDownMoving() {
        return downMoving;
    }

    public ImageIcon getLeftStatic() {
        return leftStatic;
    }

    public ImageIcon getLeftMoving() {
        return leftMoving;
    }

    public ImageIcon getRightStatic() {
        return rightStatic;
    }

    public ImageIcon getRightMoving() {
        return rightMoving;
    }

    public ImageIcon getUpStatic() {
        return upStatic;
    }

    public ImageIcon getUpMoving() {
        return upMoving;
    }
}