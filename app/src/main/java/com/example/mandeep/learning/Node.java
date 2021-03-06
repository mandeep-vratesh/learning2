package com.example.mandeep.learning;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by MANDEEP on 1/24/2017.
 */

public class Node{

    private Button button;
    private boolean active;

    public Node(Button button, boolean b) {
        this.setButton(button);
        this.setActive(b);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
