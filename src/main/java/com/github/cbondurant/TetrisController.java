package com.github.cbondurant;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.xml.crypto.dsig.keyinfo.KeyName;

import com.github.cbondurant.input.KeyHandler;


public class TetrisController{

    private TetrisModel model;
    private JPanel [] views;
    private boolean running;
    private KeyHandler keyHandler;
    private boolean moved;
    private double lrMoveDelay = 0.05d;
    private double lrFirstMoveDelay = 0.1d;
    private double lrMoveCt = 0;

    public TetrisController(TetrisModel model, JPanel [] views, KeyHandler keyHandler){
        this.model = model;
        this.views = views;
        this.keyHandler = keyHandler;
        this.running = true;
    }

    
    public void update(double deltaTime) {
        // These keybinds are defined based on the Tetris Guideline
        if (keyHandler.keyIsPressed(KeyEvent.VK_SPACE)){
            model.hardDrop();
        }
        if (keyHandler.keyIsPressed(KeyEvent.VK_C) || keyHandler.keyIsPressed(KeyEvent.VK_SHIFT)){
            model.hold();
        }
        if (keyHandler.keyIsPressed(KeyEvent.VK_R)){
            model.resetGame();
        }
        if (keyHandler.keyIsHeld(KeyEvent.VK_DOWN)){
            if(!moved){
                model.drop();
            }
            moved = !moved;
        }
        
        
        if (keyHandler.keyIsPressed(KeyEvent.VK_RIGHT)){
            model.shiftRight();
            this.lrMoveCt = -this.lrFirstMoveDelay;
        }
        if (keyHandler.keyIsPressed(KeyEvent.VK_LEFT)){
            model.shiftLeft();
            this.lrMoveCt = -this.lrFirstMoveDelay;
        }
        if (keyHandler.keyIsHeld(KeyEvent.VK_RIGHT) || keyHandler.keyIsHeld(KeyEvent.VK_LEFT)){
            this.lrMoveCt += deltaTime;
        }
        if (keyHandler.keyIsHeld(KeyEvent.VK_RIGHT)){
            if (this.lrMoveCt > lrMoveDelay){
                model.shiftRight();
                this.lrMoveCt = 0;
            }
        }
        if (keyHandler.keyIsHeld(KeyEvent.VK_LEFT)){
            if (this.lrMoveCt > lrMoveDelay){
                model.shiftLeft();
                this.lrMoveCt = 0;
            }
        }

        if (keyHandler.keyIsPressed(KeyEvent.VK_UP) || keyHandler.keyIsPressed(KeyEvent.VK_X)){
            model.rotateCW();
        }
        if (keyHandler.keyIsPressed(KeyEvent.VK_CONTROL) || keyHandler.keyIsPressed(KeyEvent.VK_Z)){
            model.rotateCCW();
        }
    }

    public void mainLoop() {

        double prev = System.nanoTime();
        while (running){
            if (model.running){
                double start = System.nanoTime();
                Double deltaTime = start - prev;
                update(deltaTime / 1E9);
                this.keyHandler.update();
                model.onUpdate(deltaTime / 1E9);
                for (JPanel view: this.views){
                    view.repaint();
                    view.revalidate();
                }
                while ( System.nanoTime() - prev < 1E9/ 60)
                {
                    Thread.yield();
                    try {Thread.sleep(1);} catch(Exception e) {} 
                }
                // System.out.println("Framerate = " + Double.valueOf(1e9/(System.nanoTime() - prev)).toString());
                prev = start;
            }
        }
    }
}