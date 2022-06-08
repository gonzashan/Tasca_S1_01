package n1exercici2;

public class N1exercici2 {

    public static void main(String[] args) {

        // invocar el mètode estàtic
        System.out.println("\nInvocan el mètode estàtic");
        Cotxe.frenar();

        // invocar no estàtic
        System.out.println("\nInvocan el mètode no estàtic");
        Cotxe cotxe = new Cotxe();
        cotxe.accelerar();
    }
}