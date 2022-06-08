package n3exercici1;


public abstract class Noticies {

    private String titular;
    private String text;
    private long preu;
    private long puntacio;
    private static int id = 1;
    private int idNoticia = 0;
    private String nameJour;


    // Constructor
    public Noticies(String titular, long preu_inicial, long punts) {
        this.titular   = titular;
        this.text      = "";
        this.preu      = preu_inicial;
        this.puntacio  = punts;
        this.idNoticia = Noticies.id++;
    }

    protected Noticies() {
    }


    // Getters
    public long getPreu() {
        return preu;
    }

    public long getPunts() {
        return puntacio;
    }

    public String getTitular() {
        return this.titular;
    }

    public String getText() {
        return text;
    }

    public String getNameJour(){
        return this.nameJour;
    }

    public int getId(){
        return this.idNoticia;
    }


    // Setters
    public void setText(String text) {
        this.text = text;
    }

    public void setNameJour(String nameJour){
        this.nameJour = nameJour;
    }

// Methods
    public abstract String calcularPreuNoticia(int printed, int kindOfTag);

    @Override
    public String toString() {
        return "Noticie[" +
                "titular='" + titular + '\'' +
                ", text='" + text + '\'' +
                ", preu=" + preu +
                ", puntacio=" + puntacio + "]\n";

    }
}
