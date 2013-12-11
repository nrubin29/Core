package me.nrubin29.citrine;

import me.nrubin29.citrine.map.Map;
import me.nrubin29.citrine.map.Tile;
import me.nrubin29.citrine.npc.Sprite;
import me.nrubin29.citrine.util.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
    NOTE: There are two ways to paint tiles (anything that doesn't need listeners).

    getGraphics().drawImage(t.getImage().getImage(), i * 16, j * 16, this); // This doesn't __quite__ work.

    - or -

    JLabel tile = new JLabel(t.getImage());
    tile.setBounds(i * 16, j * 16, t.getImage().getIconWidth(), t.getImage().getIconHeight());
    add(tile);
*/
public class GUI extends JLayeredPane {

    private JFrame rootFrame;

    public GUI(JFrame rootFrame) {
        this.rootFrame = rootFrame;
    }

    public void renderMap(final Map map) { // only final for test
        removeAll();

        for (int j = 0; j < 3; j++) { // j < Constants.TILES_PER_ROW
            for (int i = 0; i < 3; i++) { // i < Constants.TILES_PER_COLUMN
                Tile t = map.getTiles()[j][i];
                if (t != null) {
                    JLabel tile = new JLabel(t.getImage());
                    tile.setBounds(i * Constants.TILE_DIM, j * Constants.TILE_DIM, t.getImage().getIconWidth(), t.getImage().getIconHeight());
                    add(tile, 0);
                }
            }
        }

        final JLabel player = Player.getInstance().getLabel();
        player.setBounds(0, 0, 30, 30); //TODO: Fix Player Size
        add(player, 0);

        System.out.println(getComponentCountInLayer(0));
        System.out.println(getComponentCountInLayer(1));

        rootFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.setIcon(Sprite.PLAYER.getLeftMoving());

                    if (player.getX() <= 0) {
                        Timer t = new Timer(75, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                player.setIcon(Sprite.PLAYER.getLeftStatic());
                            }
                        });
                        t.setRepeats(false);
                        t.start();
                        return;
                    }

                    Timer t = new Timer(75, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            player.setLocation(player.getX() - Constants.TILE_DIM, player.getY());

                            player.setIcon(Sprite.PLAYER.getLeftStatic());
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                }

                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.setIcon(Sprite.PLAYER.getRightMoving());

                    System.out.println(player.getX());

                    if (player.getX() >= rootFrame.getSize().getWidth()) {
                        Timer t = new Timer(75, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                player.setIcon(Sprite.PLAYER.getRightStatic());
                            }
                        });
                        t.setRepeats(false);
                        t.start();
                        return;
                    }

                    Timer t = new Timer(75, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            player.setLocation(player.getX() + Constants.TILE_DIM, player.getY());

                            player.setIcon(Sprite.PLAYER.getRightStatic());
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                }

                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    player.setIcon(Sprite.PLAYER.getUpMoving());

                    Timer t = new Timer(75, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            player.setLocation(player.getX(), player.getY() - Constants.TILE_DIM);

                            player.setIcon(Sprite.PLAYER.getUpStatic());
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                }

                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    player.setIcon(Sprite.PLAYER.getDownMoving());

                    Timer t = new Timer(75, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            player.setLocation(player.getX(), player.getY() + Constants.TILE_DIM);

                            player.setIcon(Sprite.PLAYER.getDownStatic());
                        }
                    });
                    t.setRepeats(false);
                    t.start();
                }

                map.checkEvents(player.getLocation());
            }
        });
    }
}