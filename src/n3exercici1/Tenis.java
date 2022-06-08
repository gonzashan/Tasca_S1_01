package n3exercici1;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tenis extends Noticies {

    private long preu;
    private static final long punts = 10L;
    private static LinkedHashMap<String,Long> tenisTags   = new LinkedHashMap<>();
    private static LinkedHashMap<String,Long> tenisPoints = new LinkedHashMap<>();


    /*
    private long[] arrPreuNocticie = {75, 75, 75};
    private String[] arrNoticie = {
            "Eurolliga",
            "Barça",
            "Madrid"};

    */
    static {
        tenisTags.put("Ronald Ross", 375L);
        tenisTags.put("Wimbledon", 400L);
        tenisTags.put("Alcaraz", 265L);
        tenisTags.put("Nadal", 500L);
        tenisTags.put("Djokovic", 50L);

        tenisPoints.put("Ronald Ross", 5L);
        tenisPoints.put("Wimbledon", 4L);
        tenisPoints.put("Alcaraz", 6L);
        tenisPoints.put("Nadal", 5L);
        tenisPoints.put("Djokovic", 5L);
    }
    // Constructor for  Tennis News
    public Tenis(String titular) {
        super(titular, 250,Tenis.punts);
    }

    //Constructor for Tags and Points News
    public Tenis(long valueTag, String tagName, int kindOfTag) {
        if (kindOfTag == 1) {
            tenisTags.put(tagName, valueTag);
        } else {
            tenisPoints.put(tagName, valueTag);
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
            System.out.println(Redactor.ANSI_BLUE + "\n\t\tETIQUETAS TENIS");
            System.out.println("\t\t-----------------------------------------------------------------------------------------" + Redactor.ANSI_RESET);
            System.out.println("\t\tx Preu "+ tenisTags + "\n");

            System.out.println("\t\tx Punts "+ tenisPoints);
            System.out.println(Redactor.ANSI_BLUE +"\t\t-----------------------------------------------------------------------------------------\n" +Redactor.ANSI_RESET);
        }

        for (Map.Entry<String, Long> it
                : (kindOfTag == 1 ? tenisTags.entrySet() : tenisPoints.entrySet()) ) {

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