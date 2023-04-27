package org.example;

import org.example.exception.IncorrectInputException;
import org.example.warrior.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Game game = new Game();
        Scanner in = new Scanner(System.in);
        int choice = 1;

        do {

            try {
                System.out.print("""
                    Enter 1 if you want to get information about KNIGHT 
                    Enter 2 if you want to get information about game configuration
                    Enter 3 if you want to supply army
                    Enter 4 if you want to !!!BATTLE!!!
                    Enter 5 if you want to restart game
                    Enter 6 to upload the configuration into file
                    Enter 7 to download the configuration from file
                    Enter 0 if you want to leave the game
                    Enter a number:
                    """);

                choice = in.nextInt();

                if (choice == 1) {
                    int warriorType = 0;
                    System.out.println("""
                        Enter 1 if you want to get information about KNIGHT
                        Enter 2 if you want to get information about DEFENDER
                        Enter 3 if you want to get information about MAGICIAN
                        Enter 4 if you want to get information about VAMPIRE
                        Enter 5 if you want to get information about BERSERK   
                        """);
                    System.out.print("Enter the warrior type, you want to get info about: ");
                    do {
                        try {
                            warriorType = in.nextInt();
                            if (warriorType == 1) {
                                Knight.printInfo();
                            }
                            else if (warriorType == 2) {
                                Defender.printInfo();
                            }
                            else if (warriorType == 3) {
                                Magician.printInfo();
                            }
                            else if (warriorType == 4) {
                                Vampire.printInfo();
                            }
                            else if (warriorType == 5) {
                                Berserk.printInfo();
                            }
                            else {
                                throw new IncorrectInputException("Incorrect input! Please, enter number between 1 and 5!");
                            }
                        }
                        catch (IncorrectInputException e) {
                                System.out.println(e.getMessage());
                        }
                    } while (warriorType < 1 || warriorType > 5);
                }
                else if (choice == 2) {
                    int army = 0;
                    do {
                        System.out.print("Enter the army, you want to get info about: ");
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

                else if (choice == 3) {
                    int army = 0, type = 0, quantity = 0;

                    do {
                        System.out.print("""
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

                else if (choice == 4) {
                    if (game.getArmy1().getTroops().isEmpty() && game.getArmy2().getTroops().isEmpty())
                        System.out.println("Draw!");
                    else if (game.Battle())
                        System.out.println("First army has won the battle!");
                    else
                        System.out.println("Second army has won the battle!");
                }

                else if (choice == 5) {
                    game.restart();
                    System.out.println("Game has been restarted!");
                }

                else if (choice == 6) {
                    String configPath;
                    System.out.println("Enter the name of the configuration you want to upload the game into: ");
                    Scanner pathScanner = new Scanner(System.in);
                    configPath = pathScanner.nextLine();
                    File directory = new File(configPath);
                    directory.mkdir();

                    try (ObjectOutputStream outputStream = new ObjectOutputStream(
                            new FileOutputStream(configPath + "\\_army_1.txt"))) {
                        outputStream.writeObject(game.getArmy1().getTroops());
                    }
                    catch (FileNotFoundException exception) {
                        System.out.println("File not found!");
                    }
                    catch (IOException exception) {
                        System.out.println("Exception occured while recording into the file");
                    }

                    try (ObjectOutputStream outputStream = new ObjectOutputStream(
                            new FileOutputStream(configPath + "\\_army_2.txt"))) {
                        outputStream.writeObject(game.getArmy2().getTroops());
                    }
                    catch (FileNotFoundException exception) {
                        System.out.println("File not found!");
                    }
                    catch (IOException exception) {
                        System.out.println("Exception occured while recording into the file");
                    }
                }

                else if (choice == 7) {
                    int confirm = 0;
                    String configPath;
                    do {
                        try {
                            System.out.println("""
                                    Your current game could be lost! 
                                    Do you want still to download game configuration from the file?
                                    Yes - 1
                                    No - 0
                                    """);
                            confirm = in.nextInt();
                            if (confirm == 1) {
                                Scanner pathScanner = new Scanner(System.in);
                                System.out.println("Enter the name of the configuration you want to upload the game into: ");
                                configPath = pathScanner.nextLine();
                                Path dir = Paths.get(configPath);
                                if (!Files.isDirectory(dir) || !Files.exists(dir)) {
                                    throw new IncorrectInputException("Game config with such name do not exist");
                                }
                                try (ObjectInputStream inputStream = new ObjectInputStream(
                                        new FileInputStream(configPath + "\\_army_1.txt"))) {
                                    game.getArmy1().setTroops((List<Warrior>)inputStream.readObject());
                                }
                                catch (FileNotFoundException e) {
                                    System.out.println("File not found!");
                                }
                                catch (IOException e) {
                                    System.out.println("Exception occurred while reading from the file");
                                } catch (ClassNotFoundException e) {
                                    System.out.println("Class not found");
                                }

                                try (ObjectInputStream inputStream = new ObjectInputStream(
                                        new FileInputStream(configPath + "\\_army_2.txt"))) {
                                    game.getArmy2().setTroops((List<Warrior>)inputStream.readObject());
                                }
                                catch (FileNotFoundException e) {
                                    System.out.println("File not found!");
                                }
                                catch (IOException e) {
                                    System.out.println("Exception occurred while reading from the file");
                                } catch (ClassNotFoundException e) {
                                    System.out.println("Class not found");
                                }
                            }
                            else if (confirm == 0) {
                            }
                            else {
                                throw new IncorrectInputException("You should enter 1 or 0!");
                            }
                        }
                        catch (IncorrectInputException exception) {
                            System.out.println(exception.getMessage());
                        }
                    } while (confirm != 1 && confirm != 0);
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
