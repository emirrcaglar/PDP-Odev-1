package odev;
import java.io.IOException;

/**
*
* @author Emir Caglar Demirci - caglar.demirci@ogr.sakarya.edu.tr
* @since 3 nisan 2024
* <p>
* Olusturulan fonksiyonlarin cagrildigi main sinifidir.
* </p>
*/

public class Main {
    public static void main(String[] args) throws IOException{
        GitKlon.Klonla("githubRepo");
        Gez.main("githubRepo");	   
    }
}
