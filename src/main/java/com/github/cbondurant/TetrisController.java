package com.github.cbondurant;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisController  implements KeyListener, ActionListener{

    private TetrisModel model;
    private Timer dropTimer;
    private static int dropDelay = 50; 

    public TetrisController(TetrisModel model){
        this.model = model;
        this.dropTimer = new Timer(dropDelay, this);
        this.dropTimer.start();
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
        case KeyEvent.VK_DOWN:
        
            break;
        case KeyEvent.VK_RIGHT:
            model.shiftRight();
            break;
        case KeyEvent.VK_LEFT:
            model.shiftLeft();
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

    }

    @Override
    // Used for timer events
    public void actionPerformed(ActionEvent e) {
        model.drop();
    }
    
    // Required for the interface but not used.
    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}