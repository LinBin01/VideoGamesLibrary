package com.company;

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
                    removeGame();
                } else {
                    System.out.println(games.get(index - 1).getTitle() + " has been removed" + "\n");
                    games.remove(index - 1);
                }
            } catch (InputMismatchException exception) {
                System.out.println("Invalid Entry with Wrong Type! Please enter Integers!");
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
            int index = input.nextInt();
            try {
                checkOutGame(index);
            } catch (InputMismatchException exception) {
                System.out.println("Invalid Input Type! Please Type In Integers!");
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

    public void checkInGame(int index) {
        viewCheckOutGames("in");

    }
}