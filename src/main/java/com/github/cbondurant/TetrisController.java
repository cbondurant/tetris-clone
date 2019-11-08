package com.github.cbondurant;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisController extends KeyAdapter implements ActionListener{

    private TetrisModel model;
    private TetrisView view;
    private Timer dropTimer;
    private static int dropDelay = 500; 

    public TetrisController(TetrisModel model, TetrisView view){
        this.model = model;
        this.view = view;
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
    view.updateUI();
    }

    @Override
    // Used for timer events
    public void actionPerformed(ActionEvent e) {
        model.drop();
        view.updateUI();
    }
}