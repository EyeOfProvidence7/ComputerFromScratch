package com.company.model.memory;

import com.company.Helper;
import com.company.model.gates.Wire;

import java.util.ArrayList;

public class Register {
    private ArrayList<Wire> out;

    public Register(ArrayList<Wire> inputs, Wire clk){
        try{
            checkArraySize(inputs);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        out = new ArrayList<>();
        for(int i = 0; i< Helper.BITS_32; i++){
            ClockedDLatch cdl = new ClockedDLatch(inputs.get(i), clk);
            out.add(cdl.getQ());
        }
    }

    private void checkArraySize( ArrayList<Wire> A) throws IllegalArgumentException {
        if (A.size() != Helper.BITS_32){
            throw new IllegalArgumentException("Wrong array sizes for inputs to Register");
        }
    }

    public ArrayList<Wire> getOut() {
        return out;
    }
}
