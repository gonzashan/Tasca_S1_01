package n3exercici1;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class Basquet extends Noticies {

    private long preu;
    private static final long punts = 10L;
    private static LinkedHashMap<String,Long> basquetTags   = new LinkedHashMap<>();
    private static LinkedHashMap<String,Long> basquetPoints = new LinkedHashMap<>();

    static {
        basquetTags.put("Eurolliga", 75L);
        basquetTags.put("Barça", 75L);
        basquetTags.put("Madrid", 75L);
        basquetTags.put("Gasol", 50L);
        basquetTags.put("Rudy", 50L);

        basquetPoints.put("Eurolliga", 5L);
        basquetPoints.put("Barça", 5L);
        basquetPoints.put("Madrid", 7L);
        basquetPoints.put("Gasol", 5L);
        basquetPoints.put("Rudy", 9L);


    }
    // Constructor for Basket News
    public Basquet(String titular) {
        super(titular, 250, Basquet.punts);
    }

    //Constructor for Tags and Points News
    public Basquet(long valueTag, String tagName, int kindOfTag) {
        if (kindOfTag == 1) {
            basquetTags.put(tagName, valueTag);
        } else {
            basquetPoints.put(tagName, valueTag);
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
            System.out.println(Redactor.ANSI_BLUE + "\n\t\tETIQUETAS BASQUET");
            System.out.println("\t\t-----------------------------------------------------------------------------------------" + Redactor.ANSI_RESET);
            System.out.println("\t\tx Preu "+ basquetTags + "\n");

            System.out.println("\t\tx Punts "+ basquetPoints);
            System.out.println(Redactor.ANSI_BLUE +"\t\t-----------------------------------------------------------------------------------------\n" +Redactor.ANSI_RESET);
        }

        for (Map.Entry<String, Long> it
                : (kindOfTag == 1 ? basquetTags.entrySet() : basquetPoints.entrySet()) ) {

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
