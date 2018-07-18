package com.company.simulator;

import com.company.Helper;
import com.company.model.gates.Wire;
import com.company.model.memory.MemoryChip;

import java.util.ArrayList;
import java.util.Scanner;

public class MCSimulator {
    private static ArrayList<Wire> inputs;
    private static ArrayList<Wire> address;
    private static ArrayList<Wire> controls = Helper.initWires(3);
    private static Scanner sc = new Scanner(System.in);

    public static void simulateMC(){
        System.out.println("Welcome to Memory Chip Simulator 2018!");
        System.out.println("-------------------------------------------");
        while(true){
            System.out.println();
            System.out.print("Enter word size (max 32 bit): ");
            inputs = Helper.initWires(Helper.getValidChoice(4, 32));
            System.out.print("Enter address bus size (max 18): ");
            address = Helper.initWires(Helper.getValidChoice(1, 18));
            MemoryChip mc = new MemoryChip(inputs, address, controls);
            readWriteInterface(mc);
            System.out.print("0 to quit or any number for a new memory chip: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void readWriteInterface(MemoryChip mc) {
        while(true){
            System.out.println();
            System.out.print("Enter 0 to read from memory, 1 to write: ");
            int choice = Helper.getValidChoice(0, 1);
            if(choice == 0){
                readProcedure(mc);
            } else {
                writeProcedure();
            }
            System.out.println();
            System.out.print("0 to go back or any number for a new instruction: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void writeProcedure(){
        controls.get(2).setState(false);
        System.out.print("Enter number N (max " + Helper.powerOfTwo(inputs.size()) + "): ");
        Helper.intToWires(sc.nextInt(), inputs);
        System.out.print("Enter address A to write to (max " + (Helper.powerOfTwo(address.size()) - 1) + "): ");
        Helper.intToWires(sc.nextInt(), address);
        controls.get(0).setState(true);
        controls.get(1).setState(false);
        System.out.println("Written to memory successfully.");
        controls.get(0).setState(false);
    }

    private static void readProcedure(MemoryChip mc){
        controls.get(1).setState(true);
        System.out.print("Enter address A to read from (max " + (Helper.powerOfTwo(address.size()) - 1) + "): ");
        Helper.intToWires(sc.nextInt(), address);
        controls.get(0).setState(true);
        controls.get(2).setState(true);
        System.out.println("Your result: " + Helper.wiresToInt(mc.getOut()));
        controls.get(0).setState(false);
    }
}
