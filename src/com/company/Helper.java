package com.company;

import com.company.model.gates.Gate;
import com.company.model.gates.GateType;
import com.company.model.gates.Wire;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Helper {
    public static final int BITS_32 = 32;
    public static final int ALU_CONTROL_NUM = 5; //technically 6 but the carryIn is considered separate to simplify programming
    public static Random rng = new Random(System.nanoTime());
    private static int GATE_ID_COUNTER = 0;

    public static ArrayList<Wire> initWires(int size){
        ArrayList<Wire> wires = new ArrayList<>();
        for(int i = 0; i < size; i++){
            wires.add(new Wire());
        }
        return wires;
    }

    public static void intToWires(int n, ArrayList<Wire> w ){
        for(int i = 0; i < w.size(); i++){
            w.get(i).setState((n & (1 << i)) != 0);
        }
    }

    public static int wiresToInt(ArrayList<Wire> w){
        int result = 0;
        for(int i = 0; i < w.size(); i++){
            result |= (w.get(i).getState() ? 1 : 0) << i;
        }
        return result;
    }

    public static void intToWire(int n, Wire w){
        if (n == 1){
            w.setState(true);
        }
    }

    public static void printWireValues(ArrayList<Wire> w) {
        for (int i = 0; i < w.size(); i++) {
            System.out.println(i + " = " + w.get(i).getState());
        }
    }

    public static void copyWireValues(ArrayList<Wire> w1, ArrayList<Wire> w2){
        ArrayList<Wire> copyW1 = initWires(w1.size());
        for(int i = 0; i < copyW1.size(); i++){
            copyW1.get(i).setState(w1.get(i).getState());
        }
        for(int i = 0; i < w1.size(); i++){
            w2.get(i).setState(copyW1.get(i).getState());
        }
    }

    public static void printWireValuesBinary(ArrayList<Wire> w) {
        System.out.print("In binary: ");
        for (int i = 0; i < w.size(); i++) {
            if(w.get(i).getState()){
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
        System.out.println();
    }

    public static ArrayList<Wire> getWireComplements(ArrayList<Wire> w) {
        ArrayList<Wire> complements = new ArrayList<>();
        w.forEach(wire -> {
            Gate g = new Gate(wire, new Wire(), GateType.NOT, Helper.setID());
            complements.add(g.getOut());
        });
        return complements;
    }

    public static ArrayList<Wire> getMultiplexerDecoderANDGateInputWires(ArrayList<Wire> controls, ArrayList<Wire> controlComplement, int i) {
        ArrayList<Wire> wires = new ArrayList<>();
        for(int j = 0; j < controls.size(); j++){
            int factor = Helper.powerOfTwo(j);
            if((i / factor) % 2 == 1){
                wires.add(controls.get(j));
            } else {
                wires.add(controlComplement.get(j));
            }
        }
        return wires;
    }

    // Allows only valid choices for a lower and upper bound
    public static int getValidChoice(int lowerB, int upperB) {
        Scanner in = new Scanner(System.in);
        int choice;
        while (true) {
            String input = in.nextLine();
            if (input.matches("^-?\\d{1,2}+$")) {  //This regex checks whether the string is a one or two digit number
                choice = Integer.parseInt(input);
                if (choice >= lowerB && choice < upperB + 1) {
                    break;
                }
            }
            System.out.println("Incorrect input, try again");
        }
        return choice;
    }

    public static int setID(){
        int id = GATE_ID_COUNTER;
        GATE_ID_COUNTER++;
        return id;
    }

    public static int getGateIdCounter() {
        return GATE_ID_COUNTER;
    }

    public static int powerOfTwo(int n){
        int result = 1;
        while(n > 0){
            result *= 2;
            n--;
        }
        return result;
    }
}
