package com.github.cbondurant;

import javax.swing.JPanel;

import com.github.cbondurant.input.KeyHandler;

import javax.swing.*;
import java.awt.BorderLayout;

/**
 * Hello world!
 */
public final class App implements Runnable {
    public void run() {
        createAndShowGUI();
    }

    private static void createAndShowGUI(){
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TetrisModel model = new TetrisModel();
        TetrisView view = new TetrisView(model);
        HUDView hud = new HUDView(model);
        KeyHandler kh = new KeyHandler();
        TetrisController controller = new TetrisController(model, new JPanel []{view, hud}, kh);
        frame.add(view);
        frame.getContentPane().add(hud, BorderLayout.EAST);
        frame.addKeyListener(kh);
        frame.pack();
        frame.setVisible(true);

        // Fork timer thread into seperate loop as to not halt other event handlers.
        Thread loop = new Thread(){
            public void run(){
                controller.mainLoop();
            }
        };
        loop.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new App());
    }
}
