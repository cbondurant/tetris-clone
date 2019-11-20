package com.github.cbondurant.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyHandler implements KeyListener {

    public static enum KeyState{
        Pressed,
        Released
    }

    private HashMap<Integer, KeyState> keyStatesCurrent, keyStatesPrevious;

    public KeyHandler(){
        this.keyStatesCurrent = new HashMap<Integer,KeyState>();
        this.keyStatesPrevious = new HashMap<Integer,KeyState>();
    }

    @Override // unneeded
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyStatesCurrent.put(e.getKeyCode(), KeyState.Pressed);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyStatesCurrent.put(e.getKeyCode(), KeyState.Released);
    }

    public boolean keyIsRelease(int keyCode){
        if (keyStatesPrevious.get(keyCode) != null){
            return keyStatesCurrent.get(keyCode) == KeyState.Released && keyStatesPrevious.get(keyCode) == KeyState.Pressed;
        }
        return false;
    }

    public boolean keyIsHeld(int keyCode){
        if (keyStatesCurrent.get(keyCode) != null){
            return keyStatesCurrent.get(keyCode) == KeyState.Pressed;
        }
        return false;
    }

    public boolean keyIsPressed(int keyCode){
        if (keyStatesPrevious.get(keyCode) != null){
            return keyStatesCurrent.get(keyCode) == KeyState.Pressed && keyStatesPrevious.get(keyCode) == KeyState.Released;
        }
        else if (keyStatesCurrent.get(keyCode) != null){
            return keyStatesCurrent.get(keyCode) == KeyState.Pressed;
        }
        return false;
    }

    public void update(){
        this.keyStatesPrevious = (HashMap<Integer,KeyState>)keyStatesCurrent.clone();
    }
}
