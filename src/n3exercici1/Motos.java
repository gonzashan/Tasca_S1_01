package n3exercici1;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class Motos extends Noticies {

    private long preu;
    private static final long punts = 10L;
    private static LinkedHashMap<String,Long> motosTags      = new LinkedHashMap<>();
    private static LinkedHashMap<String,Long> motosPoints    = new LinkedHashMap<>();


    static {
        motosTags.put("Respsol", 175L);
        motosTags.put("Ducati", 75L);
        motosTags.put("Honda", 150L);
        motosTags.put("Yamaha", 100L);
        motosTags.put("Márquez", 70L);

        motosPoints.put("Respsol", 175L);
        motosPoints.put("Ducati", 75L);
        motosPoints.put("Valentino", 150L);
        motosPoints.put("Yamaha", 100L);
        motosPoints.put("Márquez", 70L);
    }
    // Constructor for Motocicle News
    public Motos(String titular) {
        super(titular, 100, Motos.punts);
    }

    //Constructor for Tags and Points News
    public Motos(long valueTag, String tagName, int kindOfTag) {
        if (kindOfTag == 1) {
            motosTags.put(tagName, valueTag);
        } else {
            motosPoints.put(tagName, valueTag);
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
            System.out.println(Redactor.ANSI_BLUE + "\n\t\tETIQUETAS FUTBOL");
            System.out.println("\t\t-----------------------------------------------------------------------------------------" + Redactor.ANSI_RESET);
            System.out.println("\t\tx Preu "+ motosTags + "\n");

            System.out.println("\t\tx Punts "+ motosPoints);
            System.out.println(Redactor.ANSI_BLUE +"\t\t-----------------------------------------------------------------------------------------\n" +Redactor.ANSI_RESET);
        }

        for (Map.Entry<String, Long> it
                : (kindOfTag == 1 ? motosTags.entrySet() : motosPoints.entrySet()) ) {

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