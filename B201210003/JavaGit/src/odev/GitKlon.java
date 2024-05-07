package odev;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
*
* @author Emir Caglar Demirci - caglar.demirci@ogr.sakarya.edu.tr
* @since 3 nisan 2024
* <p>
* BufferedReader ile ProcessBuilder kullanilarak Git Repo'sunun klonlandigi siniftir.
* </p>
*/

public class GitKlon {
    public static void Klonla(String directory) {
        try {
            // Dosyanin var olup olmadigini kontrol et
            if (Files.exists(Paths.get(directory))) {
                // Dosya varsa yeni bir klasor ismi olustur (dosya2, dosya3...)
                int counter = 2;
                String newDirectory;
                do {
                    newDirectory = directory + counter;
                    counter++;
                } while (Files.exists(Paths.get(newDirectory)));

                directory = newDirectory;
                System.out.println();
                System.out.println("Hedef klasor dolu. Repo, " + directory + " isimli klasore klonlaniyor...\n");
            }else{
                System.out.println("\n" + directory + " adinda klasor olusturuluyor...\n");
            }

            // GitHub repository URL'sini al
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Klonlamak istediginiz (.git uzantili) Repository URL'sini giriniz... \n\n");
            String repositoryUrl = reader.readLine();

            // Git komutu kullanarak klonla
            ProcessBuilder processBuilder = new ProcessBuilder("git", "clone", repositoryUrl, directory);
            Process process = processBuilder.start();

            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorStreamReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Ciktiyi yazdir
            String line;
            while ((line = inputStreamReader.readLine()) != null) {
                System.out.println(line);
            }

            while ((line = errorStreamReader.readLine()) != null) {
                System.out.println("Error: " + line);
            }

            int exitCode = process.waitFor();
            System.out.println("Process exited with code " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
