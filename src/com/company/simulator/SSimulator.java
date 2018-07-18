package com.company.simulator;

import com.company.Helper;
import com.company.model.arithmetic.Shifter;
import com.company.model.gates.Wire;

import java.util.ArrayList;
import java.util.Scanner;

public class SSimulator {
    private static ArrayList<Wire> inputs = Helper.initWires(Helper.BITS_32);
    private static Wire control = new Wire();
    private static Shifter s = new Shifter(inputs, control);
    private static Scanner sc = new Scanner(System.in);

    public static void simulateS(){
        System.out.println("Welcome to  32 bit Shifter Simulator 2018!");
        System.out.println("-------------------------------------------");
        while(true){
            System.out.println();
            System.out.print("Enter number to shift: ");
            Helper.intToWires(sc.nextInt(), inputs);
            doSomeShifting();
            System.out.print("Press 0 to quit or any number for a new input: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void doSomeShifting() {
        while(true){
            System.out.print("Enter 1 to shift right or any other number to shift left: ");
            Helper.intToWire(sc.nextInt(), control);
            System.out.print("Before: ");
            Helper.printWireValuesBinary(inputs);
            System.out.print("After : ");
            Helper.printWireValuesBinary(s.getOutWires());
            System.out.print("Press 0 to change input or any number to continue shifting: ");
            if(sc.nextInt() == 0){
                break;
            }
            Helper.copyWireValues(s.getOutWires(), inputs);
        }
    }
}
