package com.company;

import com.company.model.gates.Wire;
import com.company.model.memory.MemoryChip;
import com.company.simulator.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {

    private static Random rng = new Random(System.nanoTime());

    private static Scanner sc = new Scanner(System.in);

    private static void displayArithmeticOptions(){
        while(true){
            System.out.println();
            System.out.println("0: Shifter");
            System.out.println();
            System.out.print("Choose component: ");
            int choice = Helper.getValidChoice(0, 0);
            System.out.println();
            int gates =  Helper.getGateIdCounter();
            switch(choice){
                case 0:
                    SSimulator.simulateS();
                    break;
            }
            gates = Helper.getGateIdCounter() - gates;
            System.out.println("Amount of gates used: " + gates);
            System.out.print("0 to go back to main menu or any number to try another component: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void displayCombinatorialOptions(){
        while(true){
            System.out.println();
            System.out.println("0: Comparator");
            System.out.println("1: Decoder");
            System.out.println("2: Multiplexer");
            System.out.println();
            System.out.print("Choose component: ");
            int choice = Helper.getValidChoice(0, 2);
            System.out.println();
            int gates =  Helper.getGateIdCounter();
            switch(choice){
                case 0:
                    CMPSimulator.simulateCMP();
                    break;
                case 1:
                    DECSimulator.simulateDec();
                    break;
                case 2:
                    MPSimulator.simulateMP();
                    break;
            }
            gates = Helper.getGateIdCounter() - gates;
            System.out.println("Amount of gates used: " + gates);
            System.out.print("0 to go back to main menu or any number to try another component: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void displayMemoryOptions(){
        while(true){
            System.out.println();
            System.out.println("0: Clocked D Latch");
            System.out.println("1: 32-bit Register");
            System.out.println("2: Memory Chip");
            System.out.println();
            System.out.print("Choose component: ");
            int choice = Helper.getValidChoice(0, 2);
            System.out.println();
            int gates =  Helper.getGateIdCounter();
            switch(choice){
                case 0:
                    CDLSimulator.simulateCDL();
                    break;
                case 1:
                    RSimulator.simulateR();
                    break;
                case 2:
                    MCSimulator.simulateMC();
                    break;
            }
            gates = Helper.getGateIdCounter() - gates;
            System.out.println("Amount of gates used: " + gates);
            System.out.print("0 to go back to main menu or any number to try another component: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void displayGeneralOptions(){
        while(true){
            System.out.println();
            System.out.println("0: ALU");
            System.out.println();
            System.out.print("Choose component: ");
            int choice = Helper.getValidChoice(0, 0);
            System.out.println();
            int gates =  Helper.getGateIdCounter();
            switch(choice){
                case 0:
                    ALUSimulator.simulateALU();
                    break;
            }
            gates = Helper.getGateIdCounter() - gates;
            System.out.println("Amount of gates used: " + gates);
            System.out.print("0 to go back to main menu or any number to try another component: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }

    private static void runMainProgram(){
        while(true){
            System.out.println();
            System.out.println("0: Arithmetic");
            System.out.println("1: Combinatorial");
            System.out.println("2: Memory");
            System.out.println("3: General");
            System.out.println();
            System.out.print("Choose the type of component to simulate: ");
            int choice = Helper.getValidChoice(0, 3);
            switch(choice){
                case 0:
                    displayArithmeticOptions();
                    break;
                case 1:
                    displayCombinatorialOptions();
                    break;
                case 2:
                    displayMemoryOptions();
                    break;
                case 3:
                    displayGeneralOptions();
            }
            System.out.print("0 to quit the program or any number to continue: ");
            if(sc.nextInt() == 0){
                break;
            }
        }
    }
    public static void main(String[] args) {
        runMainProgram();
        //testMC();
    }

    private static void testMC() {
        ArrayList<Wire> inputs = Helper.initWires(8);
        ArrayList<Wire> address = Helper.initWires(15);
        ArrayList<Wire> controls = Helper.initWires(3);
        System.out.println("Initializing MC...");
        MemoryChip mc = new MemoryChip(inputs, address, controls);
        System.out.println("Initialized MC");
        for (int i = 0; i < 256; i++){
            controls.get(2).setState(false);
            Helper.intToWires(i, inputs);
            Helper.intToWires(i*3, address);
            controls.get(0).setState(true);
            controls.get(1).setState(false);
            controls.get(0).setState(false);
        }
        for (int i = 0; i < 256*3; i++){
            controls.get(1).setState(true);
            Helper.intToWires(i, address);
            controls.get(0).setState(true);
            controls.get(2).setState(true);
            System.out.println(i + ": " + Helper.wiresToInt(mc.getOut()));
            controls.get(0).setState(false);
        }
    }
}
