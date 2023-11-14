package G27_CDIO3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class MonopolyGUI extends JFrame {
    private ArrayList<String> figurer;
    private String[] spilleresFigurer;
    private int antalSpillere;
    private int currentSpillerIndex;

    private JLabel label;
    private JComboBox<String> figurComboBox;

    public MonopolyGUI() {
        initGUI();
    }

    public MonopolyGUI(CountDownLatch latch) {
    }

    private void initGUI() {
        figurer = new ArrayList<>();
        figurer.add("Bilen");
        figurer.add("Skibet");
        figurer.add("Hunden");
        figurer.add("Katten");

        label = new JLabel("Velkommen til Monopoly JR");
        figurComboBox = new JComboBox<>();

        JButton startButton = new JButton("Start Spillet");
        startButton.addActionListener(e -> startSpillet());

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(label);
        panel.add(figurComboBox);
        panel.add(startButton);

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startSpillet() {
        antalSpillere = Integer.parseInt(JOptionPane.showInputDialog("Indtast antallet af spillere (2-4):"));

        if (antalSpillere < 2 || antalSpillere > 4) {
            JOptionPane.showMessageDialog(this, "Ugyldigt antal spillere. Vælg venligst mellem 2 og 4.");
            return;
        }

        spilleresFigurer = new String[antalSpillere];
        currentSpillerIndex = 0;

        label.setText("Vælg din figur, Spiller " + (currentSpillerIndex + 1));

        figurComboBox.setModel(new DefaultComboBoxModel<>(figurer.toArray(new String[0])));
        figurComboBox.addActionListener(e -> {
            String valgtFigur = (String) figurComboBox.getSelectedItem();
            spilleresFigurer[currentSpillerIndex] = valgtFigur;

            currentSpillerIndex++;

            if (currentSpillerIndex < antalSpillere) {
                label.setText("Vælg din figur, Spiller " + (currentSpillerIndex + 1));
            } else {
                // Luk GUI-vinduet, når alle spillere har valgt deres figurer
                dispose();
                // Start dit spil eller udfør yderligere handlinger her
                visValgteFigurer();
            }
        });

        // Opdaterer figurerComboBox for at fjerne den valgte figur
        figurer.remove(figurComboBox.getSelectedIndex());
    }

    private void visValgteFigurer() {
        // Vis dine valgte figurer og udfør yderligere handlinger her
        System.out.println("Så har I alle valgt figurer:");
        for (int i = 0; i < antalSpillere; i++) {
            System.out.println("Spiller " + (i + 1) + " har valgt " + spilleresFigurer[i]);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MonopolyGUI());
    }
}
