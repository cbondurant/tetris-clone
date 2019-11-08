package com.github.cbondurant;
import javax.swing.*;

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
        TetrisController controller = new TetrisController(model, view);
        frame.add(view);
        frame.addKeyListener(controller);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new App());
    }
}
