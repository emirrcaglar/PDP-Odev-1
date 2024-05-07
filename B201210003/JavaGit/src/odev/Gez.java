package odev;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
*
* @author Emir Caglar Demirci - caglar.demirci@ogr.sakarya.edu.tr
* @since 3 nisan 2024
* <p>
* Klasor(ler)de gezinme isleminin yapildigi siniftir.
* </p>
*/

public class Gez {

    public static void main(String directory) throws IOException {

        try (Stream<Path> paths = Files.walk(Paths.get(directory), 10)) {
            System.out.println("***************************\n\n");
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".java")) // Dosya ismi filtreleme
                    .forEach(path -> {
                        System.out.println("Islenen dosya: " + path);
                        DosyaOku.Oku(path.toString()); // Her dosya icin Oku metodunu cagir
                        try {
                            // 0,25 saniye delay
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }                        
                        System.out.println("\n---------------------\n");
                    });
            System.out.println("***************************\n");
            System.out.println("B201210003 - Emir Caglar Demirci");
        }
    }
}
