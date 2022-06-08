package n3exercici1;

import java.util.ArrayList;

public class Redactor {

    private String name;
    private final String dni;
    public static double salary = 1500;
    private ArrayList<Noticies> noticies;
    public static final String ANSI_RESET = "\u001B[0m", ANSI_YELLOW = "\u001B[33m", ANSI_BLUE = "\u001B[32m", ANSI_RED = "\u001B[31m";


    //Constructor
    public Redactor(String name, String dni) {
        this.name = name;
        this.dni  = dni;
        noticies = new ArrayList<>();
    }


    //Setters
    public void setNoticies(Noticies noticies) {
        this.noticies.add(noticies);
    }


    //Getters
    /*
   public static <E> Integer getLastElement(ArrayList<E> list) {

        if ((list != null) && (list.isEmpty() == false)) {
            int lastIdx = list.size() - 1;
            // E lastElement = list.get(lastIdx); return the Object
            return lastIdx;
        } else
            return null;
    }
    */
    public String getName() {
        return this.name;
    }

    public String getDni() {
        return this.dni;
    }

    public double getSalary() {
        return Redactor.salary;
    }

    public ArrayList<Noticies> getNoticies() {
        return noticies;
    }


    @Override
    public String toString() {
        return name + "\t\t" + dni ;
    }
}
