package com.company.simulator;

import com.company.Helper;
import com.company.model.combinatorial.Decoder;
import com.company.model.gates.Wire;

import java.util.ArrayList;
import java.util.Scanner;

public class DECSimulator {
    private static ArrayList<Wire> controls;
    private static Scanner sc = new Scanner(System.in);

    public static void simulateDec(){
        System.out.println("Welcome to Decoder Simulator 2018");
        System.out.println("-------------------------------------------");
        while(true){
            System.out.println();
            System.out.print("Enter number of control inputs (max: 5):");
            controls = Helper.initWires(sc.nextInt());
            Decoder d = new Decoder(controls);
            runDecoder(d);
            System.out.print("0 to quit or any number for a new decoder: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void runDecoder(Decoder d) {
        while(true){
            System.out.println();
            System.out.print("Enter controls (Max " + (Helper.powerOfTwo(controls.size()) - 1)  + "): ");
            Helper.intToWires(sc.nextInt(), controls);
            System.out.println("Result: " + Helper.wiresToInt(d.getOutWires()));
            System.out.println();
            System.out.print("0 to to back or any number to set new controls: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }
}
