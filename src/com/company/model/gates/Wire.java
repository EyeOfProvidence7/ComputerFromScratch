package com.company.model.gates;

import java.util.ArrayList;

public class Wire {
    boolean state;
    ArrayList<Gate> driving;

    public Wire(){
        state = false;
        driving = new ArrayList<>();
    }

    public void setState(boolean s){
        this.state = s;
        updateDriving();
    }

    public boolean getState(){
        return state;
    }

    public void pulse(){
        setState(true);
        setState(false);
    }

    public void addToDriving(Gate g){
        driving.add(g);
    }

    private void updateDriving(){
        driving.forEach(gate -> gate.act());
    }
}
