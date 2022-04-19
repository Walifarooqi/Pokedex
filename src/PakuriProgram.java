

import java.util.Scanner;

public class PakuriProgram {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int pakudexCapacity = 0; //initial pakudex capacity
        boolean menu = true; //menu loop variable
        boolean vCapacity = false; //exception loop variable
        int playerChoice; //input value of player
        String speciesName;


        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");

        //While loop to catch exceptions//
        while (!vCapacity) {

            System.out.print("Enter max capacity of the Pakudex: ");

            try {
                pakudexCapacity = Integer.parseInt(input.next());

                if (pakudexCapacity < 1) {
                    throw new Exception();
                } else {
                    vCapacity = true;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid size.");

            }
        }

        System.out.println("The Pakudex can hold " + pakudexCapacity + " species of Pakuri.");
        Pakudex speciesNum = new Pakudex(pakudexCapacity); //pakudex object created and can hold a specific number of pakuri

        //Main Menu
        while (menu) {
            System.out.println("\n" + "Pakudex Main Menu");
            System.out.println("-----------------");
            System.out.println("1. List Pakuri");
            System.out.println("2. Show Pakuri");
            System.out.println("3. Add Pakuri");
            System.out.println("4. Evolve Pakuri");
            System.out.println("5. Sort Pakuri");
            System.out.println("6. Exit");


            System.out.print("\n" + "What would you like to do? ");


            String[] list = speciesNum.getSpeciesArray(); //list of species names as an array

            try {
                playerChoice = Integer.parseInt(input.next());


                switch (playerChoice) {

                    case 1: //lists the critters in Pakudex
                        String[] numPakuri = speciesNum.getSpeciesArray();
                        if (numPakuri == null) {
                            System.out.println("No Pakuri in Pakudex yet!");
                            break;
                        } else {
                            System.out.println("Pakuri In Pakudex: ");
                            for (int i = 0; i < numPakuri.length; i++)
                                System.out.println(i + 1 + ". " + numPakuri[i]);
                        }
                        break;

                    case 2://Shows the stats of a specific critter of choice by the player
                        System.out.print("Enter the name of the species to display: ");
                        speciesName = input.next();

                        if (list == null) {
                            System.out.println("Error: No such Pakuri!");
                            break;
                        }
                        int[] stats = speciesNum.getStats(speciesName);

                        if (stats == null) {
                            System.out.println("Error: No such Pakuri!");
                            break;

                        }

                        for (int i = 0; i < list.length; i++) {
                            if (speciesName.equals(list[i]))
                                System.out.println("\n" + "Species: " + speciesName);
                            System.out.println("Attack: " + stats[0]);
                            System.out.println("Defense: " + stats[1]);
                            System.out.println("Speed: " + stats[2]);
                            break;
                        }
                        break;

                    case 3: //Adds Pakuri to Pakudex
                        int size = speciesNum.getSize();
                        if (size == pakudexCapacity) {
                            System.out.println("Error: Pakudex is full!");
                            continue;
                        }
                        System.out.print("Enter the name of the species to add: ");
                        speciesName = input.next();

                        boolean validPakuri = speciesNum.addPakuri(speciesName);
                        if (validPakuri) {

                            System.out.println("Pakuri species " + speciesName + " successfully added!");
                        } else {
                            System.out.println("Error: Pakudex already contains this species!");
                        }
                        break;

                    case 4://Evolves Pakuri
                        System.out.print("Enter the name of the species to evolve: ");
                        speciesName = input.next();

                        boolean returnVal = speciesNum.evolveSpecies(speciesName);

                        if (returnVal) {
                            System.out.println(speciesName + " has evolved!");
                        } else {
                            System.out.println("Error: No such Pakuri!");
                        }
                        break;

                    case 5://Sorts Pakuri
                        speciesNum.sortPakuri();
                        System.out.println("Pakuri have been sorted!");
                        break;

                    case 6://Exits PakuriProgram
                        System.out.println("Thanks for using Pakudex! Bye!");
                        menu = false;
                        break;


                    default://Warns players if an option between 1 and 6 is not entered
                        System.out.println("Unrecognized menu selection!");
                        break;

                }
            } catch (Exception e) {
                System.out.println("Unrecognized menu selection!");

            }


        }
    }
}

