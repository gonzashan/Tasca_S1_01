package n3exercici1;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class Futbol extends Noticies {

    private long preu;
    private static final long punts = 10L;
    private static LinkedHashMap<String, Long> futbolTags   = new LinkedHashMap<>();
    private static LinkedHashMap<String, Long> futbolPoints = new LinkedHashMap<>();



    static {
        futbolTags.put("Lliga de Champions", 100L);
        futbolTags.put("Barça", 50L);
        futbolTags.put("Madrid", 200L);
        futbolTags.put("Benzema", 50L);
        futbolTags.put("Ferran Torres", 50L);

        futbolPoints.put("Lliga de Champions", 2L);
        futbolPoints.put("Barça", 1L);
        futbolPoints.put("Madrid", 1L);
        futbolPoints.put("Benzema", 2L);
        futbolPoints.put("Ferran Torres", 1L);
    }

    // Constructor of news for Football News
    public Futbol(String titular, long preu_inicial) {
        super(titular, preu_inicial, Futbol.punts);
    }

    //Constructor for Tags and Points News
    public Futbol(long valueTag, String tagName, int kindOfTag) {
        if (kindOfTag == 1) {
            futbolTags.put(tagName, valueTag);
        } else {
            futbolPoints.put(tagName, valueTag);
        }
    }


    // Setters
    public void setPreu(long preu) {
        this.preu = preu;
    }

    public static LinkedHashMap<String, Long> getFutbolTags() {
        return futbolTags;
    }

    // Methods
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
            System.out.println(Redactor.ANSI_BLUE + "\n\t\tETIQUETAS FUTBOL");
            System.out.println("\t\t-----------------------------------------------------------------------------------------" + Redactor.ANSI_RESET);
            System.out.println("\t\tx Preu "+ futbolTags + "\n");

            System.out.println("\t\tx Punts "+ futbolPoints);
            System.out.println(Redactor.ANSI_BLUE +"\t\t-----------------------------------------------------------------------------------------\n" +Redactor.ANSI_RESET);
        }

        for (Map.Entry<String, Long> it
                : (kindOfTag == 1 ? futbolTags.entrySet() : futbolPoints.entrySet()) ) {

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
