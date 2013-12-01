package me.nrubin29.rpg.core.entity;

public class NPC extends Entity {

    public enum NPCType {
        BOY,
        GIRL;

        public String getType() {
            return name().toLowerCase();
        }
    }

    public NPC(NPCType type, int x, int y) {
        super(type.getType(), x, y);
    }

    public void interact() {
        // Do something.
    }
}