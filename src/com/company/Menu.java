package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);

    public void startMenu() {
        System.out.println("Choose an Option From the Following" +
                "\n1. Add Game" + "\n2. Remove Game" + "\n3. View Available Games" +
                "\n4. Check Out Game" + "\n5. Check In Game" + "\n6. View Checkout Games" + "\n7. Exit");

        try {
            switch (input.nextInt()) {
                case 1:
                    //addGame();
                    break;
                case 2:
                    //removeGame();
                    break;
                case 3:
                    //viewGames();
                    break;
                case 4:
                    //checkOut();
                    break;
                case 5:
                    //checkIn();
                    break;
                case 6:
                    //viewCheckOuts();
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Entry! Please Choose a Number From 1 to 7!");
                    input.nextLine();
                    startMenu();
                    break;
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid Entry with Wrong Type! Please Try Again!");
            input.nextLine();
            startMenu();
        }
    }

    }
