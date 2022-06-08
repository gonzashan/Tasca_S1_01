package n3exercici1;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class Formula1 extends Noticies {

    private long preu;
    private static final long punts = 10L;
    private static LinkedHashMap<String,Long> formula1Tags   = new LinkedHashMap<>();
    private static LinkedHashMap<String,Long> formulalPoints = new LinkedHashMap<>();


    static {
        formula1Tags.put("Ferrari", 175L);
        formula1Tags.put("McClaren", 200L);
        formula1Tags.put("Fernando Alonso", 265L);
        formula1Tags.put("Lewis Hamilton", 200L);
        formula1Tags.put("Michael Schumacher", 70L);

        formulalPoints.put("Ferrari", 7L);
        formulalPoints.put("McClaren", 2L);
        formulalPoints.put("Fernando Alonso", 6L);
        formulalPoints.put("Lewis Hamilton", 2L);
        formulalPoints.put("Michael Schumacher", 9L);
    }
    // Constructor for Formula1 News
    public Formula1(String titular) {
        super(titular, 100, Formula1.punts);
    }

    //Constructor for Tags and Points News
    public Formula1(long valueTag, String tagName, int kindOfTag) {
        if (kindOfTag == 1) {
            formula1Tags.put(tagName, valueTag);
        } else {
            formulalPoints.put(tagName, valueTag);
        }
    }

    // Setters
    public void setPreu(long preu) {
        this.preu = preu;
    }

    @Override
    public String calcularPreuNoticia(int printed, int kindOfTag) {
        // printed != 1 returns only amount of the news
        DecimalFormat formatea = new DecimalFormat("###,###.##");

        long valueForTag =  kindOfTag == 1 ? this.getPreu() : this.getPunts();

        if (printed == 1) {
            System.out.printf("\t\t%20s = %5d\n", (kindOfTag == 1 ? "Precio inicial" : "Punts inicials")
                    , kindOfTag == 1 ? this.getPreu() : this.getPunts());
        }

        if(kindOfTag==3){
            System.out.println(Redactor.ANSI_BLUE + "\n\t\tETIQUETAS FÓRMMULA1");
            System.out.println("\t\t-----------------------------------------------------------------------------------------" + Redactor.ANSI_RESET);
            System.out.println("\t\tx Preu "+ formula1Tags + "\n");

            System.out.println("\t\tx Punts "+ formulalPoints);
            System.out.println(Redactor.ANSI_BLUE +"\t\t-----------------------------------------------------------------------------------------\n" +Redactor.ANSI_RESET);
        }

        for (Map.Entry<String, Long> it
                : (kindOfTag == 1 ? formula1Tags.entrySet() : formulalPoints.entrySet()) ) {

            if (this.getText().toLowerCase().contains(it.getKey().toLowerCase())) {
                valueForTag += it.getValue();
                if (printed == 1) {
                    System.out.printf("\t\t%20s = %5d\n", it.getKey(), it.getValue());
                }

            }
        }

        if (printed == 1) {
            System.out.println(Redactor.ANSI_BLUE + "\t\t----------------------------" + Redactor.ANSI_RESET);
            System.out.printf("%28s = %5s\n", "TOTAL", formatea.format(valueForTag));
        }
        this.setPreu(valueForTag);
        return printed == 1 ? "" : kindOfTag == 3 ? "" :formatea.format(this.preu);
    }



}