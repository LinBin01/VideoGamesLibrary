package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;


public class Library {
    private List<Game> games = new ArrayList<>();
    private List<Game> checkOutGames = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
    private Scanner input = new Scanner(System.in);
    private Menu menu;

    public Library(Menu menu) {
        this.menu = menu;
    }

    public void addGame() {
        System.out.println("What is the title of your game?");
        String title = input.nextLine();
        System.out.println("What type of game is this?");
        String type = input.nextLine();
        System.out.println("What is the ID number?");
        int idNumber = input.nextInt();
        Game newGame = new Game(title, type, idNumber);
        games.add(newGame);
        System.out.println(newGame.getTitle() + " has been added to your library! This game can now be check out. \n");
        input.nextLine();
        menu.startMenu();
    }

    public void viewGamesLibrary(String location) {
        if (games.isEmpty()) {
            System.out.println("There is no game in the library");
        } else {
            int index = 1;
            for (Game library : games) {
                System.out.println(index++ + ": " + library.getTitle());
            }
        }
        if (location.equals("in")) {
            System.out.println();
            menu.startMenu();
        }
    }

    public void viewCheckOutGames(String location) {
        if (checkOutGames.isEmpty()) {
            System.out.println("There is no game at check out");
        } else {
            int index = 1;
            for (Game library : checkOutGames) {
                System.out.println(index++ + ": " + library.getTitle());
            }
        }
        if (location.equals("out")) {
            System.out.println();
            menu.startMenu();
        }
    }

    public void removeGame() {
        if (games.isEmpty()) {
            System.out.println("There are no games currently" + "\n");
        } else {
            System.out.println("Choose one from following to remove: ");
            System.out.println("Type whatever number associated with that game to remove");

            viewGamesLibrary("remove");

            try {
                int index = input.nextInt();
                if (index > games.size() || index <= 0) {
                    System.out.println("Invalid Entry! Please Type Again!");
                    input.nextLine();
                    removeGame();
                } else {
                    System.out.println(games.get(index - 1).getTitle() + " has been removed" + "\n");
                    games.remove(index - 1);
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid Entry with Wrong Type! Please enter Integers!");
                input.nextLine();
                removeGame();
            }
        }
        input.nextLine();
        menu.startMenu();
    }

    public void checkOut() {
        if (games.isEmpty()) {
            System.out.println("There are no games on your library. Add some games to check out!");
            menu.startMenu();
        } else {
            System.out.println("Here is a list of games available, Please choose from following to proceed check out");
            viewGamesLibrary("checkout");
            try {
                int index = input.nextInt();
                checkOutGame(index);
            } catch (InputMismatchException exception) {
                System.out.println("Invalid Input Type! Please Type In Integers!");
                input.nextLine();
                checkOut();
            }
        }
        input.nextLine();
        menu.startMenu();

    }

    public void checkOutGame(int index) {
        try {
            Game gameToCheckout = games.get(index - 1);

            // create dueDay at the future
            Calendar calendar = Calendar.getInstance();
            calendar.add(calendar.DAY_OF_YEAR, 7);
            String dueDay = dateFormat.format(calendar.getTime());
            // tell user their dueDay and set the dueDay for this game
            System.out.println(gameToCheckout.getTitle() + " is due at " + dueDay);
            gameToCheckout.setDueDay(dueDay);
            // remove from library and add it to the checkout list
            games.remove(index - 1);
            checkOutGames.add(gameToCheckout);
            System.out.println(gameToCheckout.getTitle() + " has been checked out successfully! \n");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Please Enter a Valid Number within the size! Try Again!");
            checkOut();
        }
    }

    public void checkIn() {
        if (checkOutGames.isEmpty()) {
            System.out.println("There are no games currently checked out");
            menu.startMenu();
        } else {
            System.out.println("What game are you going to check-in? ");
            viewCheckOutGames("check-in");
            try {
                int index = input.nextInt();
                checkInGame(index);
            } catch (InputMismatchException exception) {
                System.out.println("Invalid Input Type! Please Type In Integers!");
                input.nextLine();
                checkIn();
            }
        }
        input.nextLine();
        menu.startMenu();

    }

    public void checkInGame(int index) {
        try {
            Game gameToCheckIn = checkOutGames.get(index - 1);
            Calendar calendar = Calendar.getInstance();
            try {
                if (dateFormat.parse(dateFormat.format(calendar.getTime())).before(dateFormat.parse(gameToCheckIn.getDueDay()))) {
                    System.out.println("Thanks for Turning Your Game On Time!");
                } else {
                    System.out.println("Shame On You! You Were Late On Turning Your Game!!");
                }
            } catch (ParseException pe) {
                // we will leave empty since we really can't do anything if catches this exception
            }
            gameToCheckIn.setDueDay("");
            // remove from library and add it to the checkout list
            games.add(gameToCheckIn);
            checkOutGames.remove(index - 1);
            System.out.println(gameToCheckIn.getTitle() + " has been checked In successfully! \n");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Please Enter a Valid Number within the size! Try Again!");
            checkIn();
        }
    }
}