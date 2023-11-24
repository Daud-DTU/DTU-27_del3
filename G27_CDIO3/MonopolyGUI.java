package G27_CDIO3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class MonopolyGUI extends JFrame {
    private ArrayList<String> figurer;
    private String[] spillersFigurer;
    private int antalSpillere;
    private int currentSpillerIndex;
    private int startSaldo;

    private JLabel currentPlayerLabel;
    private JLabel diceResultLabel;
    private JButton rollDiceButton;

    public MonopolyGUI() {
        initGUI();
        antalSpillere = promptForNumberOfPlayers();
        if (antalSpillere < 2 || antalSpillere > 4) {
            JOptionPane.showMessageDialog(this, "Ugyldigt antal spillere. Vælg mellem 2 og 4.");
            System.exit(0);
        }
        figurer = new ArrayList<>();
        figurer.add("Bilen");
        figurer.add("Skibet");
        figurer.add("Hunden");
        figurer.add("Katten");
        spillersFigurer = new String[antalSpillere];
        startSaldo = determineStartSaldo();
        currentPlayerLabel.setText("Yngste spiller vælger først! - Spiller 1, vælg din figur:");
        currentSpillerIndex = 0;
    }

    private void initGUI() {
        currentPlayerLabel = new JLabel();
        diceResultLabel = new JLabel();
        rollDiceButton = new JButton("Kast Terning");
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRollDice();
            }
        });
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(currentPlayerLabel);
        panel.add(diceResultLabel);
        panel.add(rollDiceButton);
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private int promptForNumberOfPlayers() {
        return Integer.parseInt(JOptionPane.showInputDialog("Indtast antallet af spillere (2-4):"));
    }

    private int determineStartSaldo() {
        if (antalSpillere == 2) {
            return 20;
        } else if (antalSpillere == 3) {
            return 18;
        } else {
            return 16;
        }
    }

    private void handleRollDice() {
        Random random = new Random();
        int diceResult = random.nextInt(6) + 1;
        diceResultLabel.setText(spillersFigurer[currentSpillerIndex] + " slog " + diceResult);
        startSaldo += 2;
        currentPlayerLabel.setText("Spiller " + (currentSpillerIndex + 1) + " har valgt " +
                spillersFigurer[currentSpillerIndex] + " og har en saldo på " + startSaldo + "M");
        currentSpillerIndex++;
        if (currentSpillerIndex >= antalSpillere) {
            currentSpillerIndex = 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MonopolyGUI());
    }
}
