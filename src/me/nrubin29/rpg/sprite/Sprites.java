package me.nrubin29.rpg.sprite;

public enum Sprites {

    PLAYER("Player");

    private Class<?> c;
    
    Sprites(String name) {
    	try {
    		c = Class.forName("me.nrubin29.rpg.sprite.sprites." + name);
    	}
    	catch (Exception e) { e.printStackTrace(); }
    }
    
    public Sprite newInstance() {
    	try {
    		return (Sprite) c.newInstance();
    	}
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
}