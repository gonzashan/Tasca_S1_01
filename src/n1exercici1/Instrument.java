package n1exercici1;
public abstract class Instrument {
    private String nom;
    private double preu;
    int vegadas = 1;
    static int i = 0;
    public static final String ANSI_RESET = "\u001b[0m";
    public static final String ANSI_YELLOW = "\u001b[33m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_BLUE = "\u001b[36m";

    public Instrument(String from) {
        System.out.println("\t\t\t\t(" + i + ")\u001b[33m\t\t\t*** Bloque incialización PADRE *** \u001b[31m 'non-static' \u001b[0m");
        ++i;
        System.out.println("\t\t\t\t(" + i + ") Soy el SUPER [constructor] 'Instrument' y vengo de la clase '" + from + "'");
        ++i;
    }

    public abstract void tocar();

    static {
        System.out.println("\nSECUENCIA DE CARGA\n\t\t\t\t(" + i + ") Soy el PADRE de los inicializadores. Soy un\u001b[32m 'static block'\u001b[0m");
        ++i;
    }
}
