public class Main {
    public static void main(String[] args) {
        DB dataBase = new DB("localhost", "3306", "parcheggio", "root", "");

        dataBase.insertData("AA123BC", "Alfa Romeo", "Quadrifoglio");
        dataBase.insertData("CC456NN", "Porsche", "911");
        System.out.println(dataBase.selectALL());
    }
}
