package n3exercici1;

public class TagsNews {


    //Constructor with if conditional
    public TagsNews(String textTag, long valueTag, int classType, int kindOfTag) {

        if (classType == 1) {
                          //Constructor Futbol(long , String , int)
            new Futbol  (valueTag, textTag, kindOfTag);
        } else if (classType == 2) {
            new Basquet (valueTag, textTag, kindOfTag);
        } else if (classType == 3) {
            new Tenis   (valueTag, textTag, kindOfTag);
        } else if (classType == 4) {
            new Formula1(valueTag, textTag, kindOfTag);
        } else if (classType == 5) {
            new Motos   (valueTag, textTag, kindOfTag);
        }
    }

}
