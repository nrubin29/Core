package me.nrubin29.core.entity;

import java.awt.Point;

public class NPC extends Entity {

    public enum NPCType {
        BOY,
        GIRL;

        public String getType() {
            return name().toLowerCase();
        }
    }

    public NPC(NPCType type, Point spawn) {
        super(type.getType(), spawn);
    }

    public void interact() {
        // Do something.
    }
}