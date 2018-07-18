package com.company.simulator;

import com.company.Helper;
import com.company.model.gates.Wire;
import com.company.model.memory.Register;

import java.util.ArrayList;
import java.util.Scanner;

public class RSimulator {
    private static ArrayList<Wire> inputs = Helper.initWires(Helper.BITS_32);
    private static Wire clk = new Wire();
    private static Register r = new Register(inputs, clk);
    private static Scanner sc = new Scanner(System.in);

    public static void simulateR(){
        System.out.println("Welcome to Multiplexer Simulator 2018!");
        System.out.println("-------------------------------------------");
        while(true){
            System.out.println();
            System.out.print("Enter the number to store to the register: ");
            Helper.intToWires(sc.nextInt(), inputs);
            clk.pulse();
            System.out.println("Number stored: " + Helper.wiresToInt(r.getOut()));
            System.out.println();
            System.out.print("0 to quit or any number to try again: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }
}
