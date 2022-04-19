import java.util.Arrays;
import java.util.stream.IntStream;

public class Pakudex {

    private String[] arr;
    private Pakuri[] pakuri;
    private  int capacity;
    private int size = 0;


    //Default Constructor//
    public Pakudex() {
        capacity = 20;
    }

    //Parameter Constructor: Initializes this object to contain exactly capacity objects when completely full //
    public Pakudex(int capacity) {
        arr = new String[capacity];
        pakuri = new Pakuri[capacity];
        this.capacity = capacity;
    }
    //Returns the number of critters currently being stored in the Pakudex
    public int getSize() {

        return this.size;

    }
    //get capacity for array
    public int getCapacity() {
        return arr.length;
    }
    //Returns a String array containing the species of the critters as ordered in the Pakudex; if there are no species added yet, this method should return null
    public String[] getSpeciesArray() {

        if (size < 1) {
            return null;
        }
        String[] arrPak = new String[size];

        for(int i = 0; i< arrPak.length; i++ ){
            arrPak[i] = pakuri[i].getSpecies();
        }
        return arrPak;
    }
//Returns  an  int  array  containing  the  attack,  defense,  and  speed  statistics  of  species  at  indices  0,  1,  and  2 respectively; if species is not in the Pakudex, returns null
    public int[] getStats(String species) {

        int[] speciesStats = new int[3];
        if (!pakuriExists(species)) {
            return null;
        } else
            for (int i = 0; i < size; i++) {
                if (pakuri[i].getSpecies().equals(species)) {
                    speciesStats[0] = pakuri[i].getAttack();
                    speciesStats[1] = pakuri[i].getDefense();
                    speciesStats[2] = pakuri[i].getSpeed();

                    return speciesStats;
                }
            }
        return null;
    }
    // Adds species to the Pakudex; if successful, return true, and false otherwise
    public boolean addPakuri(String species) {

        for(int i = 0; i < size; i++)
        {
            if(pakuri[i] != null)
            {
                String exists = pakuri[i].getSpecies();

                if(exists.contentEquals(species))
                {
                    return false;
                }
            }
        }
//If no exceptions, add species
        pakuri[size] = new Pakuri(species);
        size++;
        return true;
    }
//Attempts to evolve species within the Pakudex; if successful, return true, and false otherwise
    public boolean evolveSpecies(String species) {

        for (int i = 0; i < size; i++) {
            if (pakuri[i].getSpecies().equals(species)) {
                pakuri[i].evolve();
                return true;
            }
        }
        return false;
    }
    //Checks if the pakuri exists
    public boolean pakuriExists(String species) {

        for (int i = 0; i < size; i++) {
            if (pakuri[i].getSpecies().equals(species)) {
                return true;

            }
        }
        return false;
    }
    //Sorts the Pakuri objects in this Pakudex according to Java standard lexicographical ordering of species name
    public void sortPakuri() {

        for(int i = 0; i < size; i++) {

            for (int j = i + 1; j < size; j++) {

                if (pakuri[i].getSpecies().compareTo(pakuri[j].getSpecies()) > 0) {

                    Pakuri temp = pakuri[i];
                    pakuri[i] = pakuri[j];
                    pakuri[j] = temp;
                }

            }
        }

    }
}
