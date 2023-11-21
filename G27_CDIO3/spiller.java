  import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class spiller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mulige figurer tilføjet til en ArrayList
        ArrayList<String> figurer = new ArrayList<>();
        figurer.add("Bilen");
        figurer.add("Skibet");
        figurer.add("Hunden");
        figurer.add("Katten");

        System.out.println("Velkommen til Monopoly JR. Indtast antallet af spillere (2-4): ");

        // Antallet af spillere skal være mellem 2 og 4
        int antalSpillere = scanner.nextInt();
        if (antalSpillere < 2 || antalSpillere > 4) {
            System.out.println("Ugyldigt antal spillere. Vælg mellem 2 og 4.");
            return;
        }

        // Oprettet en array til at tildele spillerne hver deres figur
        String[] spillersFigurer = new String[antalSpillere];


        System.out.println("Yngste spiller vælger først!");
        System.out.println();
        // Tillad spillere at vælge en figur
        for (int i = 0; i < antalSpillere; i++) {
            System.out.println("Spiller " + (i + 1) + ", vælg din figur:");
            for (int j = 0; j < figurer.size(); j++) {
                System.out.println((j + 1) + ". " + figurer.get(j));
            }
            int spillersValg = scanner.nextInt();
            if (spillersValg < 1 || spillersValg > figurer.size()) {
                System.out.println("Ugyldigt valg.");
                i--;
            } else {
                spillersFigurer[i] = figurer.get(spillersValg - 1);
                figurer.remove(spillersValg - 1); // Fjern valgte figurer så flere spillere ikke har den samme figur
            }
        }

        // Konto
        int[] konto = new int[antalSpillere];

        int startSaldo = 0;
        if (antalSpillere == 2) {
            startSaldo = 20;
        } else if (antalSpillere == 3) {
            startSaldo = 18;
        } else if (antalSpillere == 4) {
            startSaldo = 16;
        }

        // Vis til sidst hvilken figur hver spiller har valgt
        System.out.println("Spillere har valgt følgende figurer:");
        for (int i = 0; i < antalSpillere; i++) {
            System.out.println("Spiller " + (i + 1) + " har valgt " + spillersFigurer[i] + " og har en saldo på " + startSaldo + "M");
        }
        System.out.println("Tryk på Enter for at fortsætte");
        scanner.nextLine();
        scanner.nextLine();

        // Terningespil begynder
        ArrayList<String> spilleBræt = new ArrayList<>();
        // Indsæt spillebrættet fra din tidligere besked her
        spilleBræt.add("Start");
        spilleBræt.add("Burgerbaren (ejendom)");
        spilleBræt.add("Pizzahuset (ejendom)");
        spilleBræt.add("Chance");
        spilleBræt.add("Slikbutikken (ejendom)");
        spilleBræt.add("Iskiosken (ejendom)");
        spilleBræt.add("Fængsel (besøg)");
        spilleBræt.add("Museet (ejendom)");
        spilleBræt.add("Biblioteket (ejendom)");
        spilleBræt.add("Chance");
        spilleBræt.add("Skaterparken (ejendom)");
        spilleBræt.add("Swimmingpoolen (ejendom)");
        spilleBræt.add("Gratis parkering");
        spilleBræt.add("Spillehallen (ejendom)");
        spilleBræt.add("Biografen (ejendom)");
        spilleBræt.add("Chance");
        spilleBræt.add("Legetøjsbutikken (ejendom)");
        spilleBræt.add("Dyrehandlen (ejendom)");
        spilleBræt.add("Gå i fængsel (felt 6)");
        spilleBræt.add("Bowlinghallen (ejendom)");
        spilleBræt.add("Zoo (ejendom)");
        spilleBræt.add("Chance");
        spilleBræt.add("Vandlandet (ejendom)");
        spilleBræt.add("Strandpromenaden (ejendom)");

        Random tilfældig = new Random();
        int[] spillerensPosition = new int[antalSpillere]; // Startposition for hver spiller

        while (true) {
            for (int i = 0; i < antalSpillere; i++) {
                System.out.println(spillersFigurer[i] + ", tryk på Enter for at kaste terningerne.");
                scanner.nextLine();

                int kast = tilfældig.nextInt(6) + 1; // Kast terningen
                System.out.println(spillersFigurer[i] + " slog " + kast);

                int nyPosition = spillerensPosition[i] + kast;

                if (nyPosition >= spilleBræt.size()) {
                    // Hvis spilleren passerer brættets grænse, skal vi tage højde for det.
                    nyPosition -= spilleBræt.size();
                    System.out.println(spillersFigurer[i] + " passerer START og modtager 2M!");
                    startSaldo += 2; // Opdater spillerens saldo
                }

                System.out.println("Og lander på feltet " + spilleBræt.get(nyPosition));

                spillerensPosition[i] = nyPosition; // Opdater spillerens position

                // Implementer logik for at håndtere det felt, spilleren er landet på
                // F.eks. køb ejendom, betal husleje osv.

                // Implementer også en måde at afslutte spillet (fx når en spiller går fallit)
                System.out.println("Du har nu en saldo på: "+ startSaldo+"M");
            }
        }
    }
}