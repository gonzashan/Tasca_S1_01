package n3exercici1;

import java.text.DecimalFormat;
import java.util.*;

public class N3exercici1 {
    // Declaración de variables
    public static final int NTABS = 7;
    public static final String ANSI_RESET = "\u001B[0m", ANSI_YELLOW = "\u001B[33m", ANSI_BLUE = "\u001B[32m", ANSI_RED = "\u001B[31m";
    public static boolean loadData = false, second_row = false, switcher = false;
    public static boolean optTagsSelected = false;
    public static char[][] controlInputArray = new char[][]{
              {'s', 'n'}
            , {'1', '2', '3', '4', '5', '6', '7', '8', 's', 'r', 'c', 'n', 't'}
            , {'f', 'b', 't', '1', 'm', 's'}
            , {'1', '2', 's'}};
    private static String messageError = "Ups!. Algo ha ido mal.";
    static Scanner sc = new Scanner(System.in);

    private static final ArrayList<Redactor> redactors = new ArrayList<>();
    private static final List<Noticies> noticiesArrayList = new ArrayList<>();

    private static String option;


    /*+-+-+-+-+-+-+-+ +-+-+-+ +-+-+-+-+-+-+ +-+-+-+-+-+-+
     |M|e|t|h|o|d|s| |f|o|r| |s|c|r|e|e|n| |o|u|t|p|u|t|
    +-+-+-+-+-+-+-+ +-+-+-+ +-+-+-+-+-+-+ +-+-+-+-+-+-+*/
    public static void flushingScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }

    public static String fillString(String str, int size) {
        // homemade method for justified text at right side
        char[] a = str.toCharArray();
        int longName = a.length;
        StringBuilder out = new StringBuilder();


        for (int i = 0; i < size; i++) {

            if (i < longName) out.append(a[i]);
            else out.append(' ');
        }

        return out.toString();
    }

    public static void pressEnterToContinue() {
        System.out.print("\t\tPrem Enter per continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static String tabulate() {
        return "\t".repeat(NTABS);
    }

    public static String printSymbol(char e, int nTimes) {
        return String.valueOf(e).repeat(nTimes);
    }

    public static void progressBar(char e, int nTimes) {
        System.out.print(tabulate() + ' ');
        for (int i = 0; i < nTimes; i++) {
            timeDelay(51 - (i));
            System.out.printf("%c", e);
        }
    }

    public static void printHeadSection(String s) {
        System.out.println("\n" + tabulate() + ANSI_YELLOW + printSymbol('─', 52) + ANSI_RESET);
        System.out.println(tabulate() + s + " *** " + ANSI_RESET + "");
        System.out.println(tabulate() + ANSI_YELLOW + printSymbol('─', 52) + ANSI_RESET);
    }

    public static void printClassOfTagsNewsMenu() {
        System.out.println(tabulate() + " FUTBOL  -  BASQUET  -  TENIS -  FORMULA1  -  MOTOS");
        System.out.println(tabulate() + "  [F]         [B]        [T]       [1]         [M]");
        System.out.println(tabulate() + ANSI_BLUE + printSymbol('-', 52));
        System.out.println(tabulate() + "S - Sortir" + ANSI_RESET);
    }

    public static void printKindOfTagsNewsMenu() {
        System.out.println(tabulate() + "          -  PREUS  -  -  PUNTS  -  ");
        System.out.println(tabulate() + "              [1]          [2]       ");
        System.out.println(tabulate() + ANSI_BLUE + printSymbol('-', 52));
        System.out.println(tabulate() + "S - Sortir" + ANSI_RESET);
    }

    public static void timeDelay(int sleep) {
    // Sleeping a while
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void printGetListFromJournalist() {

        DecimalFormat formatea = new DecimalFormat("###,###.##");

        System.out.println(ANSI_YELLOW + "\n\n\n\t\tLLISTAT DE REDACTORS " + printSymbol('-', 59));
        System.out.printf("\t\t%s %5s %35s %15s %15s", "ID", "NOM", "DNI", " SOU", "ARTICLES\n");
        System.out.println(ANSI_YELLOW + "\t\t" + printSymbol('-', 80) + ANSI_RESET);

        if (redactors.size() > 0) {
            int i = 1;
            for (Redactor redactor : redactors) {
                System.out.printf(ANSI_YELLOW + "\t%5d" + ANSI_RESET + "\t%5s", i, (fillString(redactor.getName(), 35)));
                System.out.printf("\t%5s %10s %10d\n", fillString(redactor.getDni(), 9),
                        formatea.format(redactor.getSalary()),
                        redactor.getNoticies().size());
                i++;
            }
            System.out.println(ANSI_YELLOW + "\t\t" + printSymbol('-', 80) + ANSI_RESET);
        } else {
            System.out.println("\t\tNo hi ha registres\n ".toUpperCase());
        }
        pressEnterToContinue();
    }

    public static  int printFoundJournalistInSearch(ArrayList<Integer> journalistFoundedArr) {
    // Method print on console records founded with similar contains searching string
    // and returns an Integer from the journalist choose.
        // journalistFoundedArr comes from method call 'getIdsJournalist'
        if (journalistFoundedArr.size() > 1) {

            System.out.println(ANSI_YELLOW + "\n\n" + tabulate() + "Hi han " +
                    journalistFoundedArr.size() + " registres similars " + printSymbol('-', 24));
            System.out.printf(tabulate() + "%5s %35s", "NOM", "DNI\n");
            System.out.println(ANSI_YELLOW + tabulate() + printSymbol('-', 52) + ANSI_RESET);

            for (Integer elem : journalistFoundedArr) {

                System.out.printf(tabulate()
                                + "%5s %15s\n", fillString(redactors.get(elem).getName(), 34),
                        fillString(redactors.get(elem).getDni(), 10));
            }

            System.out.println(ANSI_YELLOW + tabulate() + printSymbol('-', 52) + ANSI_RESET);
            second_row = true;
            writingANews();

        } else if (journalistFoundedArr.size() == 1) {
            second_row = false;
            return journalistFoundedArr.get(0);

        } else {
            second_row = false;
            System.out.println(tabulate() + ANSI_RED + "NO EXISTEIX AQUEST REGISTRE!!\n" + ANSI_RESET);
        }

        return -1;
    }

    public static void printHeadSectionNews(String string, int orderBy, int optTag) {

        System.out.println(ANSI_YELLOW + "\n\n\n\t\tLLISTAT DE NOTICIES " + string);
        System.out.println("\t\t" + printSymbol('-', 95));
        if (orderBy == 1) {
            System.out.printf("\t\t%s %5s %15s %35s %5s %20s", "ID", " TITULAR", "  TEXT"
                    , "PREU" , " SECCIÓ", "AUTOR\n");
        } else {
            System.out.printf("\t\t%s %5s %15s %35s %5s %20s", "ID", " TITULAR", "  TEXT"
                    , switcher ? (  optTag == 1 ? "PREU" : "PUNTS" ) : "     ", " SECCIÓ", "AUTOR\n");
        }
        System.out.println(ANSI_YELLOW + "\t\t" + printSymbol('-', 95) + ANSI_RESET);
    }

    public static void printingListOfNews(int orderBy, int optTag) {

        printHeadSectionNews((orderBy == 1 ? "PER REDACTORS" : "PER ÚLTIMAS ENTRADAS"), orderBy,optTag);

        if (redactors.size() > 0 && noticiesArrayList.size() > 0) {

            if (orderBy == 1) {
                printNewsByJournalist();
            } else {
                printNewsByEntrance();
            }
            System.out.println(ANSI_YELLOW + "\t\t" + printSymbol('-', 95) + ANSI_RESET);
        } else {
            System.out.println("\t\tNo hi ha registres\n ".toUpperCase());
        }
        pressEnterToContinue();
    }

    public static void printNewsByJournalist() {

        DecimalFormat formatea = new DecimalFormat("###,###.##");
        int i = 0;
        for (Redactor redactor : redactors) {

            for (Noticies noticies : redactor.getNoticies()) {

                System.out.printf(ANSI_YELLOW + "\t%5d" + ANSI_RESET + "\t%5s" +
                                (noticies.getTitular().length() > 14 ? "..." : "   ")
                        , noticies.getId()
                        , fillString(noticies.getTitular(), 14));

                System.out.printf("\t%5s" + (noticies.getText().length() > 24 ? "..." : "   ") + "%10s %10s %20s\n"
                        , fillString(noticies.getText(), 25)
                        , noticies.calcularPreuNoticia(2, 1)
                        , fillString(noticies.getClass().getSimpleName(), 8)
                        , noticies.getNameJour());
                i++;
            }
            if (redactor.getNoticies().size() > 0)
                System.out.println("\t\t" + printSymbol('-', 95));
        }
        System.out.println(ANSI_YELLOW + tabulate() + tabulate() + tabulate() + "\t\t" + i + " registros");
    }

    public static void printNewsByEntrance() {

        // Print the news by last recorded
        Collections.reverse(noticiesArrayList);

        int i = noticiesArrayList.size();

        for (Noticies noticia : noticiesArrayList) {
            String headLine = noticia.getTitular();
            String titleNews = noticia.getText();
            String nameJour = noticia.getNameJour();
            String kindNews = noticia.getClass().getSimpleName();
            String amountNews = noticia.calcularPreuNoticia(2, optTagsSelected ? 2 :1);
            amountNews = switcher ? amountNews : "";

            System.out.printf(ANSI_YELLOW + "\t%5d" + ANSI_RESET + "\t%5s" + (headLine.length() > 14 ? "..." : "   ")
                    , i, fillString(headLine, 14));
            System.out.printf("\t%5s" + (titleNews.length() > 24 ? "..." : "   ") + "%10s %10s %20s \n"
                    , fillString(titleNews, 25)
                    , amountNews
                    , fillString(kindNews, 8)
                    , nameJour);
            i--;
        }
        Collections.reverse(noticiesArrayList);
    }

 /* +-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+
 |M|e|t|h|o|d|s| |r|e|t|u|r|n|i|n|g| |v|a|l|u|e|s|
 +-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+-+-+ +-+-+-+-+-+-+ */



    // Method creates an Integer Array where stores index when match Redactor with String searching
    public static int getIdsJournalist(String searching) {

        String journalistConcatenate;
        ArrayList<Integer> journalistFound = new ArrayList<>();
        int i = 0;
        for (Redactor redactor : redactors) {
            journalistConcatenate = redactor.getName().concat(redactor.getDni()).toLowerCase();
            if (journalistConcatenate.contains((searching.toLowerCase()))) {
                journalistFound.add(i);
            }
            i++;
        }
        return printFoundJournalistInSearch(journalistFound);
    }

    private static int[] findJournalistNews(int idNoticia) {
        int i = 0, j = 0;
        int[] results = new int[2];
        boolean encontrado = false;
        int sizeRedactores = redactors.size();
        int sizeNoticias = 0;

        while (i < sizeRedactores && !encontrado) {
            j = 0;
            sizeNoticias = redactors.get(i).getNoticies().size();
            while (j < sizeNoticias && !encontrado) {
                if (idNoticia == redactors.get(i).getNoticies().get(j).getId()) {
                    encontrado = true;
                    results[0] = i;
                    results[1] = j;
                    return results;

                }
                j++;
            }
            i++;
        }
        return results;
    }


/*   _   _   _   _   _   _     _   _   _   _   _   _     _   _   _   _     _   _   _   _
 ( P  U  B  L  I  C ) ( S | T | A | T | I | C ) ( V | O | I | D ) ( M | A | I | N )
*/


    public static void main(String[] args) {


        do {
            System.out.println();
            System.out.println(tabulate() + "╔══════════════════════════════════════════════════╗");
            System.out.println(tabulate() + "║  DIARI ESPORTIU  Florentino **                   ║");
            System.out.println(tabulate() + "╠══════════════════════════════════════════════════╣");
            System.out.println(tabulate() + "║                                                  ║");
            System.out.println(tabulate() + "║    [ " + ANSI_YELLOW + "1" + ANSI_RESET + " ] - Introduir redactor                    ║");
            System.out.println(tabulate() + "║    [ " + ANSI_YELLOW + "2" + ANSI_RESET + " ] - Eliminar redactor                     ║");
            System.out.println(tabulate() + "║    [ " + ANSI_YELLOW + "3" + ANSI_RESET + " ] - Introduir notícia a redactor          ║");
            System.out.println(tabulate() + "║    [ " + ANSI_YELLOW + "4" + ANSI_RESET + " ] - Eliminar notícia                      ║");
            System.out.println(tabulate() + "║    [ " + ANSI_YELLOW + "5" + ANSI_RESET + " ] - Mostrar totes les notícies / redactor ║");
            System.out.println(tabulate() + "║    [ " + ANSI_YELLOW + "6" + ANSI_RESET + " ] - Calcular preu notícia                 ║");
            System.out.println(tabulate() + "║    [ " + ANSI_YELLOW + "7" + ANSI_RESET + " ] - Calcular puntuació de la notícia      ║");
            System.out.println(tabulate() + "║    [ " + ANSI_YELLOW + "8" + ANSI_RESET + " ] - Crear etiquetas de notícias           ║");
            System.out.println(tabulate() + "║                                                  ║");
            System.out.println(tabulate() + "║    [ " + ANSI_YELLOW + "S" + ANSI_RESET + " ] - Sortir                                ║");
            System.out.println(tabulate() + "║                                                  ║");
            System.out.println(tabulate() + "║ " + ANSI_YELLOW + fillString((loadData ? "" : "   [ C ] Carregar dadas d'exemples"), 37) + ANSI_RESET + "            ║");
            System.out.println(tabulate() + "║      " + ANSI_BLUE + "R" + ANSI_RESET + "  -  Redactors                             ║");
            System.out.println(tabulate() + "╚══════════════════════════════════════════════════╝\n");
            //  System.out.println(tabulate() + fillString((edificis.size() + " Registros"), 11) + ANSI_BLUE + "\t\t\t\t\tSoftware Æ¾®\n\n" + ANSI_RESET);

            option = inputData("SELECCIONA OPCIÓ: ", 1);
            switchControlMainMenu(option.toLowerCase());
        } while (!option.equalsIgnoreCase("s"));

    }
/* +-+-+-+-+-+-+ +-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+
 |S|w|i|t|c|h| |C|o|n|t|r|o|l| |M|e|t|h|o|d|s|
 +-+-+-+-+-+-+ +-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+ */

    public static void switchControlMainMenu(String option) {

        switch (option) {

            case "1":
                // New journalist
                newJournalist();
                timeDelay(1000);
                break;
            case "2":
                // Delete journalist
                deleteJournalist();
                timeDelay(1000);
                break;
            case "3":
                // Create a news
                writingANews();
                break;
            case "4":
                // Delete news
                deleteNews();
                break;
            case "5":
                // Print news by Journalist
                printingListOfNews(1, 1);
                flushingScreen();
                break;
            case "6":
                // Print News details: price
                switcher = true;
                newsNewsDetails(1);
                switcher = false;
                break;
            case "7":
                // Print News details: points
                switcher = true;
                optTagsSelected = true;
                newsNewsDetails(2);
                switcher = false;
                optTagsSelected = false;
                break;
            case "8":
                // Create tags to match with news's text and get it price and points
                createTags();
                flushingScreen();
                break;
            case "c":
                if (!loadData) {
                    loadData = true;
                    loadSamples();
                }
                break;
            case "n": // Lletra secreta per llistar Redactors
                printingListOfNews(2, 1);
                flushingScreen();
                break;
            case "r": // Lletra secreta per llistar Redactors
                printGetListFromJournalist();
                flushingScreen();
                break;
            case "t": // Lletra secreta per llistar Redactors
                getTags();
                flushingScreen();
                break;
            case "s":
                System.out.println("Visca el Madrid!");
                sc.close();
                break;
            default:
                System.out.println(messageError);
        }
    }

    public static void switchNewsMenu(String choice, int idJournalist) {

        switch (choice) {

            case "f":
                // Creating football news
                printHeadSection("Escrivim sobre fútbol");
                Futbol futbol = new Futbol(inputData("TITULAR: ", 8), 300L);
                futbol.setText(inputData("TEXT: ", 8));
                // Setting the news inside the Journalist array news
                redactors.get(idJournalist).setNoticies(futbol);
                // Setting the journalist's name inside his/her own news
                futbol.setNameJour(redactors.get(idJournalist).getName());
                // Adding news to an Arraylist to get it for entrance list
                noticiesArrayList.add(futbol);
                timeDelay(800);
                break;
            case "b":
                // Creating basket news
                printHeadSection("Escrivim sobre basquet");
                Basquet basquet = new Basquet(inputData("TITULAR: ", 8));
                basquet.setText(inputData("TEXT: ", 8));
                // Setting the news inside the Journalist array news
                redactors.get(idJournalist).setNoticies(basquet);
                // Setting the journalist's name inside his/her own news
                basquet.setNameJour(redactors.get(idJournalist).getName());
                noticiesArrayList.add(basquet);
                timeDelay(800);
                break;
            case "t":
                // Creating tennis news
                printHeadSection("Escrivim sobre tenis");
                Tenis tenis = new Tenis(inputData("TITULAR: ", 8));
                tenis.setText(inputData("TEXT: ", 8));
                // Setting the news inside the Journalist array news
                redactors.get(idJournalist).setNoticies(tenis);
                // Setting the journalist's name inside his/her own news
                tenis.setNameJour(redactors.get(idJournalist).getName());
                noticiesArrayList.add(tenis);
                timeDelay(800);
                break;
            case "1":
                // Creating Fórmula1 news
                printHeadSection("Escrivim sobre Fórmula1");
                Formula1 formula1 = new Formula1(inputData("TITULAR: ", 8));
                formula1.setText(inputData("TEXT: ", 8));
                redactors.get(idJournalist).setNoticies(formula1);
                formula1.setNameJour(redactors.get(idJournalist).getName());
                noticiesArrayList.add(formula1);
                timeDelay(800);
                break;
            case "m":
                // Creating motorbikes news
                printHeadSection("Escrivim sobre motos");
                Motos motos = new Motos(inputData("TITULAR: ", 8));
                motos.setText(inputData("TEXT: ", 8));
                redactors.get(idJournalist).setNoticies(motos);
                motos.setNameJour(redactors.get(idJournalist).getName());
                noticiesArrayList.add(motos);
                timeDelay(800);
                break;
            case "ss":
                System.out.println("volvemos");
                break;

        }
    }

    public static void switchTagsMenu(String choice, String kindOfTagS) {
        if (!kindOfTagS.equals("s")) {

            int classType = (new String(controlInputArray[2]).indexOf(choice)) + 1;
            String[] tagName = new String[]{"", "futbol", "basquet", "tenis", "Fórmula1", "motociclisme"};
            int kindOfTag = Integer.parseInt(kindOfTagS);

            // Creating tags as a price as a points
            printHeadSection("Escrivim etiqueta de " + tagName[classType]);
            TagsNews tagsFootball = new TagsNews(
                    inputData("DENOMINACIÓ: ", 8)
                    , Long.parseLong(inputData((kindOfTag == 1 ? "PREU" : "PUNTS") + " DE LA NOTÍCIA: ", 6))
                    , classType
                    , kindOfTag
            );
        }
    }

/* +-+-+-+-+-+-+-+ +-+-+-+-+ +-+-+-+-+ +-+-+-+-+-+
 |M|e|t|h|o|d|s| |f|r|o|m| |M|a|i|n| |M|e|n|u|s|
 +-+-+-+-+-+-+-+ +-+-+-+-+ +-+-+-+-+ +-+-+-+-+-+*/

    // Creating a new journalist
    public static void newJournalist() {

        printHeadSection("Donar d'alta redactor");
        System.out.println(tabulate() + "El index del nuevo redactor será: ");
        redactors.add(new Redactor(
                inputData("Nom del redactor: ", 8),
                inputData("Número d'DNI: ", 8)));
    }

    // Deleting journalist
    public static void deleteJournalist() {

        printHeadSection("Donar de baixa redactor");

        if (redactors.size() > 0) {
            printGetListFromJournalist();
            int indexToDelete;
            do {
                indexToDelete = Integer.parseInt(inputData("ID DEL REDACTOR A ESBORRAR: ", 5)) - 1;

                if (indexToDelete >= redactors.size()) {
                    System.out.println(tabulate()
                            + ANSI_RED
                            + "no existeix aquest ID de redactor!\n".toUpperCase() + ANSI_RESET);
                    timeDelay(800);
                }

            } while (indexToDelete >= redactors.size());

            String deteleOption = inputData("S'esborrarà un registre. ¿Estàs segur? (S) SI - (N) NO : ", 0);
            if (deteleOption.equalsIgnoreCase("s")) {

                redactors.remove(indexToDelete);
            }
        } else {
            System.out.println(tabulate() + "NO EXISTEIXEN REGISTRES!!");
        }
        flushingScreen();
    }

    // Deleting news
    public static void deleteNews() {

        printHeadSection("Borrar notícia");

        if (redactors.size() > 0 && noticiesArrayList.size() > 0) {
            printingListOfNews(2, 1);

            int indexToDelete;

            do {
                indexToDelete = Integer.parseInt(inputData("\n\t\tID NOTÍCIA A ESBORRAR ( 0 -> Sortir): ", 5));
                if ((indexToDelete - 1) >= noticiesArrayList.size()) {
                    System.out.println(tabulate()
                            + ANSI_RED
                            + "no existeix aquest ID de notícia!\n".toUpperCase() + ANSI_RESET);
                    timeDelay(800);
                }

            } while ((indexToDelete - 1) >= noticiesArrayList.size());

            if (indexToDelete > 0) {

                String deteleOption = inputData("\n\t\tS'esborrarà un registre. ¿Estàs segur? (S) SI - (N) NO : ", 0);
                if (deteleOption.equalsIgnoreCase("s")) {
                    Collections.reverse(noticiesArrayList);

                    int idNoticia = noticiesArrayList.get(noticiesArrayList.size() - indexToDelete).getId();

                    int[] positions = findJournalistNews(idNoticia);
                    redactors.get(positions[0]).getNoticies().remove(positions[1]);
                    noticiesArrayList.remove(noticiesArrayList.size() - indexToDelete);
                    Collections.reverse(noticiesArrayList);
                    printHeadSection("Notícia esborrada".toLowerCase());
                }
            }
        } else {
            System.out.println(tabulate() + "NO EXISTEIXEN REGISTRES!!");
        }
        timeDelay(900);
        flushingScreen();
    }

    // Creating news
    public static void writingANews() {

        if (!second_row) {
            printHeadSection("Avans d'escriure una notícia");
        }
        System.out.println(tabulate() + ANSI_BLUE + "* - Sortir" + ANSI_RESET);
        System.out.println(tabulate() + printSymbol('-', 52) + ANSI_RESET + "\n" );

        String searching = inputData("Introduir 'DNI' o 'Nom' del redactor: ".toUpperCase(), 8);

        if (!searching.equals("*")) {

            int indexJournalist = getIdsJournalist(searching);

            if (indexJournalist != -1) {
                printHeadSection(redactors.get(indexJournalist).getName() + " escriurà notícia sobre ");
                printClassOfTagsNewsMenu();
                switchNewsMenu(inputData("Seleccionar secció: ".toUpperCase(), 2), indexJournalist);
            }
        } else {
            timeDelay(400);
            flushingScreen();
        }
    }

    // Showing news cost details
    public static void newsNewsDetails(int optTag) {

        printHeadSection("Detalls: " + (optTag == 1? "cost" : "punts") + " de la notícia");

        if (redactors.size() > 0 && noticiesArrayList.size() > 0) {

            printingListOfNews(2,optTag);

            int indexToDetails;
            do {
                indexToDetails = Integer.parseInt(inputData("\n\t\tID NOTÍCIA A DETALLAR ( 0 -> Sortir): ", 5));
                if ((indexToDetails - 1) >= noticiesArrayList.size()) {
                    System.out.println(tabulate()
                            + ANSI_RED
                            + "no existeix aquest ID de notícia!\n".toUpperCase() + ANSI_RESET);
                    timeDelay(800);
                }

            } while ((indexToDetails - 1) >= noticiesArrayList.size());

            if (indexToDetails > 0) {
                Collections.reverse(noticiesArrayList);
                int[] positions = findJournalistNews(indexToDetails);
                int redactor    = positions[0];
                int noticia     = positions[1];

                System.out.println(ANSI_YELLOW + "\t\t" + printSymbol('-', 95) + ANSI_RESET);
                System.out.println("\t\tTITULAR: " + ANSI_BLUE + redactors.get(redactor).getNoticies().get(noticia).getTitular());
                System.out.println(ANSI_RESET + "\t\tTEXT: " + ANSI_BLUE
                        + redactors.get(redactor).getNoticies().get(noticia).getText() + ANSI_RESET);
                System.out.println(ANSI_YELLOW + "\t\t" + printSymbol('-', 95) + ANSI_RESET);
                // Calling calcularPreuNoticia(int e) at their own subClass
                redactors.get(redactor).getNoticies().get(noticia).calcularPreuNoticia(1, optTag);

                Collections.reverse(noticiesArrayList);
                System.out.println("\n\n");
                pressEnterToContinue();
            }
        } else {
            System.out.println(tabulate() + "NO EXISTEIXEN REGISTRES!!");
        }
        timeDelay(900);
        flushingScreen();

    }

    public static void createTags() {

        printHeadSection("Crear nova etiqueta per noticies");
        printClassOfTagsNewsMenu();

            String choice = inputData("Seleccionar esport: ".toUpperCase(), 2);
            if(!choice.equalsIgnoreCase("s")){
                printHeadSection("Selecionar la naturaleza de l'etiqueta");
                printKindOfTagsNewsMenu();
                String kindOfTag = inputData("Tipo d'etiqueta: ".toUpperCase(), 3);
                switchTagsMenu(choice, kindOfTag);
            }
    }

    public static void getTags(){

        Futbol futbo  = new Futbol("hola",1L);
        Basquet basqu = new Basquet("holaB");
        Tenis tenis   = new Tenis("holat");
        Formula1 fora1= new Formula1("holaf");
        Motos motos   = new Motos("holam");
        System.out.print(futbo.calcularPreuNoticia(3,3));
        System.out.print(basqu.calcularPreuNoticia(3,3));
        System.out.print(tenis.calcularPreuNoticia(3,3));
        System.out.print(fora1.calcularPreuNoticia(3,3));
        System.out.print(motos.calcularPreuNoticia(3,3));

        pressEnterToContinue();
    }
/* +-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+ +-+-+-+
 |L|o|a|d|i|n|g| |s|a|m|p|l|e|s| |.|.|.|
 +-+-+-+-+-+-+-+ +-+-+-+-+-+-+-+ +-+-+-+*/

    public static void loadSamples() {
        // putting data as samples at the redactor ArrayList and noticiesArraList
        Redactor redactor = new Redactor("Pepe Domingo Castaño", "09804445M");
        redactors.add(redactor);
        Redactor redactor2 = new Redactor("Manolo Lama", "12345678P");
        redactors.add(redactor2);
        Redactor redactor3 = new Redactor("Paco González", "89948643p");
        redactors.add(redactor3);
        Redactor redactor4 = new Redactor("Josep Bou", "43434343O");
        redactors.add(redactor4);

        Futbol futbol = new Futbol("Titular Inicial", 300L);
        //futbol.setPreu(300);
        futbol.setNameJour(redactors.get(3).getName());
        futbol.setText("¿Quién metió un gol?. Ferran Torres con el Barça y el bueno de Benzema con el Madrid la Lliga de Champions.");
        redactors.get(3).setNoticies(futbol);
        noticiesArrayList.add(futbol);

        // FUTBOL
        Futbol futbol2 = new Futbol("Barça S.A.", 300L);
        futbol2.setNameJour(redactors.get(1).getName());
        futbol2.setText("El Barça, después de 15 años, vuelve de Segunda División tras la dimisión de Piqué.");
        redactors.get(1).setNoticies(futbol2);
        noticiesArrayList.add(futbol2);

        Futbol futbol3 = new Futbol("Tormenta culé", 300L);
        futbol3.setNameJour(redactors.get(0).getName());
        futbol3.setText("El Barça baja a Segunda División perdiendo en Madrid contra el Real Madrid.");
        redactors.get(0).setNoticies(futbol3);
        noticiesArrayList.add(futbol3);


        // BASQUET
        Basquet basquet = new Basquet("La cesta blaugrana");
        basquet.setPreu(250L);
        basquet.setNameJour(redactors.get(1).getName());
        basquet.setText("El Barça regresa a la ACB tras la dimisión de Piqué y después de 25 años.");
        redactors.get(1).setNoticies(basquet);
        noticiesArrayList.add(basquet);


        Basquet basquet1 = new Basquet("5 años seguidos");
        basquet1.setPreu(250L);
        basquet1.setNameJour(redactors.get(2).getName());
        basquet1.setText("Cómo en Madrid gana las cosas en la Eurolliga. Florentino soborna a todos kiski con rublos");
        redactors.get(2).setNoticies(basquet1);
        noticiesArrayList.add(basquet1);

        Motos motos1 = new Motos("Llueve sobre mojado en Montmeló");
        motos1.setNameJour(redactors.get(3).getName());
        motos1.setText("Ducati no mueve los caballos lo suficiente ni con Valentino Rossi.");
        redactors.get(3).setNoticies(motos1);
        noticiesArrayList.add(motos1);


        // Tennis
        Tenis tenis = new Tenis("Tsunami Alcaraz");
        tenis.setNameJour(redactors.get(1).getName());
        tenis.setText("Barrido del murciano Alcaraz en Madrid este domingo. Djokovic y Nadal se compran un puesto de frankfurt en Wimbledon");
        redactors.get(1).setNoticies(tenis);
        noticiesArrayList.add(tenis);


        // Formula1
        Formula1 formula1 = new Formula1("Llueve sobre mojado en Montmeló");
        formula1.setNameJour(redactors.get(1).getName());
        formula1.setText("Fernando Alonso se toma unas sidras mientras Ferrari se hace unos raviolis.");
        redactors.get(1).setNoticies(formula1);
        noticiesArrayList.add(formula1);

        // Motos
        Motos motos = new Motos("Spa ha sido un estrés");
        motos.setNameJour(redactors.get(2).getName());
        motos.setText("La Honda se dan un paseo por el circuito sin Márquez esta vez en la pista.");
        redactors.get(2).setNoticies(motos);
        noticiesArrayList.add(motos);

        System.out.println("\n" + tabulate() + "Carregant dadas");
        progressBar('▒', 50);
        flushingScreen();
    }

/* +-+-+-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+ +-+-+-+-+-+ +-+-+-+-+
 |M|e|t|h|o|d|s| |t|o| |c|o|n|t|r|o|l| |i|n|p|u|t| |d|a|t|a|
 +-+-+-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+ +-+-+-+-+-+ +-+-+-+-+*/

    public static String inputData(String string, int control) {

    // inputData( String: 'Message', Integer: 'Busca en la variable controlInputArray[]
    //                                          los tipos y valores aceptados en la entrada'
        String check;
        boolean aux = true;
        do {
            System.out.print(tabulate() + string);
            check = sc.nextLine();

            if (!ifIsBlankEmpty(check)) {

                if (control >= 0 && control <= 3) { // (0)(1)(2) comprueba los arrays de control arriba declarados
                    char option = check.toLowerCase().charAt(0);
                    aux = ifCharIsInControlArray(option, control);

                    if (!aux) {
                        messageError = getArrayCharsAccepted(control);
                    }

                } else if (control == 5) { // (5) comprobar si 'check' es valor ENTERO positivo

                    aux = ifIsInteger(check);

                    if (aux) {
                        return check;
                    } else {
                        messageError = "Introdueix un valor enter positivo".toUpperCase();
                    }

                } else if (control == 6) { // (5) comprobar si 'check' es valor DOUBLE positivo
                    try {
                        // caso que el usuario introduzca el carácter "," en lugar de "." en el número
                        check = check.replace(",", ".");
                        if (Double.parseDouble(check) > 0) {
                            return check;
                        } else {
                            messageError = "Introdueix valor positivo".toUpperCase();
                        }
                    } catch (NumberFormatException e) {
                        messageError = "Introdueix valor vàlid".toUpperCase();
                        aux = false;
                    }

                } else if (control == 8) { // (8) comprobar si 'check' es valor STRING
                    aux = true;
                    return check;

                } else {
                    aux = false;
                }
            }

            if (!aux) {
                System.out.println(tabulate() + ANSI_RED + messageError + ANSI_RESET);
                timeDelay(700);
            }

        } while (ifIsBlankEmpty(check) || !aux);

        return check;
    }

    public static String getArrayCharsAccepted(int arrayBlock) {
        // Construimos el mensaje de para que el usuario introduzca elementos válidos
        String chainOf = "Introdueix ALGUNA D'aquestes OPCIONS: ".toUpperCase();
        for (char elem : controlInputArray[arrayBlock]) {
            new StringBuilder(chainOf += ANSI_RESET + "[" + ANSI_YELLOW +
                    String.valueOf(elem).toUpperCase() + ANSI_RESET + "] ");
        }
        return chainOf;
    }
    // Boolean methods for checking variables
    public static boolean ifCharIsInControlArray(char c, int arrayBlock) {

        for (char elem : controlInputArray[arrayBlock]) {
            // Controlar si tenemos el valor introducido
            // registrado en los array char de control
            if (elem == c) return true;
        }
        return false;
    }

    public static boolean ifIsInteger(String check) {
        return check.matches("-?\\d+") && Integer.parseInt(check) >= 0;
    }

    public static boolean ifIsBlankEmpty(String check) {
        return check.isBlank() || check.isEmpty();
    }
}
