package n1exercici1;

public class Viola extends Instrument {
    public Viola() {
        super(Marimba.class.getSimpleName());
        System.out.println("\t\t\t\t(" + i + ") [constructor] de Viola");
        ++i;
    }

    public void tocar() {
        System.out.println("\t\t\t\t(" + i + ") Està sonant un instrument de corda " + this.vegadas + " vegadas.");
        ++i;
        ++this.vegadas;
    }

    public static void sinInstanciar() {
        System.out.println("\t\t\t\t(" + i + ") Qué bonito que me llamen sin instanciarme !!");
        ++i;
    }
}