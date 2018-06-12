package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    private Library myLibrary = new Library(this);

    public void startMenu() {
        System.out.println("Choose an Option From the Following" +
                "\n1. Add Game" + "\n2. Remove Game" + "\n3. View Available Games" +
                "\n4. Check Out Game" + "\n5. Check In Game" + "\n6. View Checkout Games" + "\n7. Exit");

        try {
            switch (input.nextInt()) {
                case 1:
                    //addGame();
                    input.nextLine();
                    System.out.println("You have chosen to add a game!");
                    myLibrary.addGame();
                    break;
                case 2:
                    myLibrary.removeGame();
                    break;
                case 3:
                    //viewGames();
                    input.nextLine();
                    myLibrary.viewGamesLibrary("in");
                    break;
                case 4:
                    // checkOut();
                    input.nextLine();
                    myLibrary.checkOut();
                    break;
                case 5:
                    input.nextLine();
                    myLibrary.checkIn();
                    break;
                case 6:
                    input.nextLine();
                    myLibrary.viewCheckOutGames("out");
                    break;
                case 7:
                    System.out.println("Thank You For Your Visit. See You Next Time!");
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
