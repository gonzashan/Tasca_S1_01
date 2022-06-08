package n1exercici1;

public class Trompeta extends Instrument {
    public Trompeta() {
        super(Trompeta.class.getSimpleName());
        System.out.println("\t\t\t\t(" + i + ") [constructor] de Trompeta");
        ++i;
    }

    public void tocar() {
        System.out.println("\t\t\t\t(" + i + ") Està sonant un instrument de vent " + this.vegadas + " vegadas.");
        ++this.vegadas;
        ++i;
    }
}