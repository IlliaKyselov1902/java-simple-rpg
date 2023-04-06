package org.example;

import org.example.exception.IncorrectInputException;
import org.example.warrior.*;

import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Game game = new Game();
        Scanner in = new Scanner(System.in);
        int choice = 1;

        do {

            try {
                System.out.println("""
            Enter 1 if you want to get information about KNIGHT
            Enter 2 if you want to get information about DEFENDER
            Enter 3 if you want to get information about MAGICIAN
            Enter 4 if you want to get information about VAMPIRE
            Enter 5 if you want to get information about BERSERK    
            Enter 6 if you want to get information about game configuration
            Enter 7 if you want to supply army
            Enter 8 if you want to !!!BATTLE!!!
            Enter 9 if you want to restart game
            Enter 0 if you want to leave the game
            Enter a number:
            """);

                choice = in.nextInt();

                if (choice == 1) {
                    Knight.printInfo();
                }

                else if (choice == 2) {
                    Defender.printInfo();
                }

                else if (choice == 3) {
                    Magician.printInfo();
                }

                else if (choice == 4) {
                    Vampire.printInfo();
                }

                else if (choice == 5) {
                    Berserk.printInfo();
                }

                else if (choice == 6) {
                    int army = 0;
                    do {
                        System.out.println("Enter the army, you want to get info about: ");
                        try {
                            army = in.nextInt();
                            if (army == 1){
                                System.out.println("Info about first army: ");
                                game.displayStats(game.getArmy1());
                            }
                            else if (army == 2){
                                System.out.println("Info about second army: ");
                                game.displayStats(game.getArmy2());
                            }
                            else {
                                throw new IncorrectInputException("Incorrect input! Please, enter number 1 or 2!");
                            }
                        }
                        catch(IncorrectInputException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (army != 1 && army != 2);
                }

                else if (choice == 7) {
                    int army = 0, type = 0, quantity = 0;

                    do {
                        System.out.println("""
                    Enter 1 if you want to add KNIGHT
                    Enter 2 if you want to add DEFENDER
                    Enter 3 if you want to add MAGICIAN
                    Enter 4 if you want to add VAMPIRE
                    Enter 5 if you want to add BERSERK
                    Enter type of warrior: """);
                        try {
                            type = in.nextInt();
                            if (type > 5 || type < 1)
                                throw new IncorrectInputException("Incorrect input! Please, enter number between 1 and 5!");
                        }
                        catch (IncorrectInputException e){
                            System.out.println(e.getMessage());
                        }
                    } while (type < 1 || type > 5);

                    do {
                        try {
                            System.out.println("Enter quantity of warriors: ");
                            quantity = in.nextInt();
                            if (quantity <= 0)
                                throw new IncorrectInputException("Incorrect input! Quantity should be higher than 0!");
                        }
                        catch (IncorrectInputException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (quantity <= 0);

                    do {
                        try {
                            System.out.println("Enter the army, you want to supply: ");
                            army = in.nextInt();
                            if (army != 1 && army != 2)
                                throw new IncorrectInputException("Incorrect input! Please, enter number 1 or 2!");
                        }
                        catch (IncorrectInputException e) {
                            System.out.println(e.getMessage());
                        }
                    } while (army != 1 && army != 2);

                    if (army == 1)
                        game.getArmy1().addUnits(Army.resolveEnumeration(type), quantity);
                    else
                        game.getArmy2().addUnits(Army.resolveEnumeration(type), quantity);
                    System.out.println("Warriors has been successfully added to army!");
                }

                else if (choice == 8) {
                    if (game.getArmy1().getTroops().isEmpty() && game.getArmy2().getTroops().isEmpty())
                        System.out.println("Draw!");
                    else if (game.Battle())
                        System.out.println("First army has won the battle!");
                    else
                        System.out.println("Second army has won the battle!");
                }

                else if (choice == 9) {
                    game.restart();
                    System.out.println("Game has been restarted!");
                }

                else {
                    throw new IncorrectInputException("Invalid operation");
                }
            }
            catch (IncorrectInputException e) {
                System.out.println(e.getMessage());
            }


        } while (choice != 0);

        System.out.println("Escaping...");
        System.out.println("Thanks for playing");
    }
}
