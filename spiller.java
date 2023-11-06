import java.util.ArrayList;
import java.util.Scanner;

public class spiller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mulige figure tilføjet til en arraylist
        ArrayList<String> figure = new ArrayList<>();
        figure.add("Bilen");
        figure.add("Skibet");
        figure.add("Hunden");
        figure.add("Katten");

        System.out.println("Velkommen til Monopoly JR, Indtast antallet af spiller. (2-4)");

        // Antallet af spillere skal være i mellem 2 og 4
        int antalletAfSpillere = scanner.nextInt();
        if (antalletAfSpillere < 2 || antalletAfSpillere > 4) {
            System.out.println("Invalid number of players. Please choose between 2 and 4.");
            return;
        }

        // Oprettet en array til at tildele spillerne hver deres figur
        String[] spillersMuligheder = new String[antalletAfSpillere];

        // Tillader spillerne at vælge en figur
        for (int i = 0; i < antalletAfSpillere; i++) {
            System.out.println("Spiller " + (i + 1) + ", vælg din figur,");
            for (int j = 0; j < figure.size(); j++) {
                System.out.println((j + 1) + ". " + figure.get(j));
            }
            int Spillersvalg = scanner.nextInt();
            if (Spillersvalg < 1 || Spillersvalg > figure.size()) {
                System.out.println("Ugyligt valg.");
                i--;
            } else {
                spillersMuligheder[i] = figure.get(Spillersvalg - 1);
                figure.remove(Spillersvalg - 1); // Fjerner valgte figure så flere spiller ikke har den samme figur
            }
        }

        // Viser til sidst hvilken figur hver spiller har valgt
        System.out.println("Så har i alle valgt figure::");
        for (int i = 0; i < antalletAfSpillere; i++) {
            System.out.println("Spiller " + (i + 1) + " har valgt " + spillersMuligheder[i]);
        }
        scanner.close();
    }
}
