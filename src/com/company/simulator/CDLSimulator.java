package com.company.simulator;

import com.company.model.memory.ClockedDLatch;
import com.company.model.gates.Wire;

import java.util.Scanner;

public class CDLSimulator {
    private static Wire D = new Wire();
    private static Wire clock = new Wire();
    private static ClockedDLatch CDL = new ClockedDLatch(D, clock);
    private static Scanner sc  = new Scanner(System.in);
    private static int choice;

    public static void simulateCDL(){
        System.out.println("Welcome to Storing One Bit: The Game");
        System.out.println("-------------------------------------------");
        while(true){
            System.out.println();
            System.out.println("0: Set D to 0");
            System.out.println("1: Set D to 1");
            System.out.println("2: pulse the clock");
            System.out.println("3: quit");
            System.out.print("Your choice: ");
            choice = sc.nextInt();
            if(choice == 3){
                break;
            }
            performFunction();
            System.out.println();
            System.out.println("current Q-value: " + CDL.getQ().getState());
        }
    }

    public static void performFunction(){
        switch(choice){
            case 0:
                D.setState(false);
                break;
            case 1:
                D.setState(true);
                break;
            case 2:
                clock.pulse();
                break;
            default:
                System.out.println("You did not enter a number between 0, and 2, try again");
        }
    }
}
