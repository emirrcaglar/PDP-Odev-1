package odev;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
*
* @author Emir Caglar Demirci - caglar.demirci@ogr.sakarya.edu.tr
* @since 3 nisan 2024
* <p>
* Siniflarin okundugu ve analiz edildigi siniftir.
* </p>
*/

public class DosyaOku {

    public static void Oku(String dosyaYolu) {
        File myObj = new File(dosyaYolu);
        System.out.println("Sinif: " + myObj.getName());     			// SINIF ADI
        try {
            Scanner sc = new Scanner(myObj);
            int lineCount = 0;
            int javadocCount = 0;
            int commentCount = 0;
            int functionCount = 0;
            int emptyLineCount = 0;
            boolean inJavadoc = false;
            boolean inComment = false;
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lineCount++;
                line = line.trim();
                
                /* JAVADOC */
                if(line.startsWith("/**")) {
                    inJavadoc = true;
                }
                if(inJavadoc) {
                    javadocCount++;
                }
                if(inJavadoc && line.endsWith("*/")) {
                    inJavadoc = false;
                }
                /* JAVADOC */
                /* YORUM SAYISI */
                if(line.startsWith("//")) {
                	commentCount++;
                }
                if(line.startsWith("/*") && line.startsWith("/**") == false) {
                    inComment = true;
                }
                if(inComment) {
                    commentCount++;
                }
                if(inComment && line.endsWith("*/")) {
                	inComment = false;
                }
                /* YORUM SAYISI */              
                /* FONKSIYON */
                if((line.contains("(") && line.contains(")")) && (line.contains("public")
                		|| line.contains("private") || line.contains("protected"))) {
                	functionCount++;
                }
                /* FONKSIYON */
                /* BOS SATIR */
                if(line.isEmpty()) {
                	emptyLineCount++;
                }            
                /* BOS SATIR */
            }
            
            int codeCount = lineCount - javadocCount - commentCount - emptyLineCount;
            
            // YG=[(Javadoc_Satır_Sayısı + Diğer_yorumlar_satır_sayısı)*0.8]/Fonksiyon_Sayisi
            // YH= (Kod_satir_sayisi/Fonksiyon_Sayisi)*0.3
            // Yorum Sapma Yüzdesinin Hesabı: [(100*YG)/YH]-100
            double YG = ((javadocCount + commentCount) * 0.8) / functionCount;
            double YH = (codeCount / (double)functionCount) * 0.3;
            double YorumSapmaYuzdesi = ((100 * YG) / YH) - 100;
            
            System.out.println("Javadoc Satir Sayisi: " + javadocCount); // JAVADOC
            System.out.println("Yorum Sayisi: " + commentCount);		 // YORUM SAYISI
            System.out.println("Kod Satir Sayisi: " + codeCount);        // KOD SATIR SAYISI
            System.out.println("LOC: " + lineCount);           			 // LOC  
            System.out.println("Fonksiyon Sayisi: " + functionCount);	 // FONKSIYON SAYISI

            if (Double.isNaN(YorumSapmaYuzdesi) || Double.isInfinite(YorumSapmaYuzdesi)) {
                System.out.println("Yorum Sapma Yuzdesi: hesaplanamaz... ");
            }else{
                System.out.println("Yorum Sapma Yuzdesi: % " + YorumSapmaYuzdesi);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Bir Hata Olustu.");
            e.printStackTrace();
        }
    }
}
