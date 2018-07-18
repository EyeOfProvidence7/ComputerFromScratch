package com.company.model.memory;

import com.company.Helper;
import com.company.model.combinatorial.Decoder;
import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.MGate;
import com.company.model.gates.Wire;

import java.util.ArrayList;

//builds a memory chip from 1 or more memory banks
public class MemoryChip {
    public static final int SIZE_LIMIT_BANK = 10;
    public static final int MAX_CHIP_SIZE = 15;
    private ArrayList<Wire> out;

    public MemoryChip(ArrayList<Wire> dataIn, ArrayList<Wire> address, ArrayList<Wire> controls){
        try{
            checkArraySize(address);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        this.out = new ArrayList<>();
        if(address.size() <= 10){
            MemoryBank m = new MemoryBank(dataIn, address, controls);
            out = m.getOut();
        } else {
            constructLMC(dataIn, address, controls);
        }

    }

    private void constructLMC(ArrayList<Wire> dataIn, ArrayList<Wire> address, ArrayList<Wire> controls) {
        ArrayList<Wire> bankAddress = new ArrayList<>();
        ArrayList<Wire> bankWordAddress = new ArrayList<>();
        for(int i = 0; i < address.size(); i++){
            if(i < SIZE_LIMIT_BANK){
                bankWordAddress.add(address.get(i));
            } else {
                bankAddress.add(address.get(i));
            }
        }
        Decoder d = new Decoder(bankAddress);
        ArrayList<Wire> chipSelectLines = d.getOutWires();
        ArrayList<ArrayList<Wire>> bankOutWires = new ArrayList<>();
        for(int i = 0; i < chipSelectLines.size(); i++){
            Gate andSelect = new Gate(controls.get(0), chipSelectLines.get(i), GateType.AND, Helper.setID());
            ArrayList<Wire> bankControls = new ArrayList<>();
            bankControls.add(andSelect.getOut());
            bankControls.add(controls.get(1));
            bankControls.add(controls.get(2));
            MemoryBank mc = new MemoryBank(dataIn, bankWordAddress, bankControls);
            bankOutWires.add(mc.getOut());
        }
        for(int i = 0; i < bankOutWires.get(0).size(); i++){
            ArrayList<Wire> ORin = new ArrayList<>();
            for(int j = 0; j < bankOutWires.size(); j++){
                ORin.add(bankOutWires.get(j).get(i));
            }
            MGate OR = new MGate(ORin, GateType.OR);
            out.add(OR.getOut());
        }
    }

    private void checkArraySize( ArrayList<Wire> A) throws IllegalArgumentException {
        if (A.size() > MAX_CHIP_SIZE){
            throw new IllegalArgumentException("Memory bank too large.");
        }
    }


    public ArrayList<Wire> getOut() {
        return out;
    }
}
