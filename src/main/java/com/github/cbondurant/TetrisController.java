package com.github.cbondurant;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import java.awt.event.KeyAdapter;

public class TetrisController extends KeyAdapter{

    private TetrisModel model;
    private JPanel [] views;
    private boolean running;

    public TetrisController(TetrisModel model, JPanel [] views){
        this.model = model;
        this.views = views;
        this.running = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // These keybinds are defined based on the Tetris Guideline
        switch (e.getKeyCode()) {
        case KeyEvent.VK_SPACE:
            model.hardDrop();
            break;
        case KeyEvent.VK_C:
        case KeyEvent.VK_SHIFT:
            model.hold();
            break;
        case KeyEvent.VK_R:
            model.resetGame();
            break;
        case KeyEvent.VK_DOWN:
            model.drop();
            break;
        case KeyEvent.VK_RIGHT:
            model.shiftRight();
            break;
        case KeyEvent.VK_LEFT:
            model.shiftLeft();
            break;
        case KeyEvent.VK_UP:
        case KeyEvent.VK_X:
            model.rotateCW();
            break;
        case KeyEvent.VK_CONTROL:
        case KeyEvent.VK_Z:
            model.rotateCCW();
        default:
            break;
        }
        for(JPanel view: this.views){
            view.updateUI();
        }
    }

    public void mainLoop() {

        double prev = System.nanoTime();
        while (running){
            if (model.running){
                double start = System.nanoTime();
                double deltaTime = start - prev;
                model.onUpdate(deltaTime / 1E9);
                for (JPanel view: this.views){
                    view.updateUI();
                }
                prev = start;
            }
        }
    }
}