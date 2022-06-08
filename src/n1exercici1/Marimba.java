package n1exercici1;

public class Marimba extends Instrument {
    public Marimba() {
        super(Marimba.class.getSimpleName());
        System.out.println("\t\t\t\t(" + i + ") La Marimba inicializa su bloque\u001b[31m 'non-static' \u001b[0m");
        ++i;
        System.out.println("\t\t\t\t(" + i + ") [constructor] de Marimba");
        ++i;
    }

    public void tocar() {
        System.out.println("\t\t\t\t(" + i + ") Està sonant un instrument de percussió " + this.vegadas + " vegadas.");
        ++this.vegadas;
        ++i;
    }

    static {
        System.out.println("\t\t\t\t(" + i + ") La Marimba inicializa su bloque\u001b[32m 'static' \u001b[0m");
        ++i;
    }
}