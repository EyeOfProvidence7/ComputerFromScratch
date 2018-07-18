package com.company.simulator;

import com.company.Helper;
import com.company.model.combinatorial.Multiplexer;
import com.company.model.gates.Wire;

import java.util.ArrayList;
import java.util.Scanner;

public class MPSimulator {
    private static ArrayList<Wire> inputs;
    private static ArrayList<Wire> controls;
    private static Scanner sc = new Scanner(System.in);

    public static void simulateMP(){
        System.out.println("Welcome to Multiplexer Simulator 2018!");
        System.out.println("-------------------------------------------");
        while(true){
            System.out.println();
            System.out.print("Enter number of control inputs (max: 5): ");
            controls = Helper.initWires(sc.nextInt());
            inputs =  Helper.initWires(Helper.powerOfTwo(controls.size()));
            Multiplexer mp = new Multiplexer(inputs, controls);
            runMultiplexer(mp);
            System.out.print("0 to quit or any number for a new multiplexer: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void runMultiplexer(Multiplexer mp) {
        while(true){
            System.out.println();
            System.out.print("Enter input (Max " + (Helper.powerOfTwo(inputs.size()) - 1)  + "): ");
            Helper.intToWires(sc.nextInt(), inputs);
            Helper.printWireValuesBinary(inputs);
            playWithControls(mp);
            System.out.print("0 to go back or any number to set new inputs: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void playWithControls(Multiplexer mp) {
        while(true){
            System.out.print("Enter controls (Max " + (Helper.powerOfTwo(controls.size()) - 1)  + "): ");
            Helper.intToWires(sc.nextInt(), controls);
            Helper.printWireValuesBinary(controls);
            System.out.println("Result: " + mp.getOut().getState());
            System.out.println();
            System.out.print("0 to go back or any number to set new controls: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }
}
