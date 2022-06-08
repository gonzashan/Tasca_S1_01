package n1exercici2;

public class Cotxe {

    public static final String ANSI_RESET = "\u001B[0m", ANSI_YELLOW = "\u001B[33m", ANSI_GREEN = "\u001B[32m", ANSI_RED = "\u001B[31m", ANSI_BLUE = "\u001B[36m";

    static final String MARCA = "";
    static String model;
    final int POTENCIA = 0;

    public static void frenar() {
        System.out.println(ANSI_BLUE + "El vehicle està frenant" + ANSI_RESET);
    }

    public void accelerar() {
        System.out.println(ANSI_YELLOW + "El vehicle està accelerant" + ANSI_RESET);
    }
}
