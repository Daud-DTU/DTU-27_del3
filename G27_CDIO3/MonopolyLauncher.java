package G27_CDIO3;
import javax.swing.*;

import java.util.concurrent.CountDownLatch;

public class MonopolyLauncher {
    public static void main(String[] args) {
        // Use CountDownLatch to synchronize the GUI and spiller
        CountDownLatch latch = new CountDownLatch(1);

        // Start GUI
        SwingUtilities.invokeLater(() -> {
            new MonopolyGUI(latch);
        });

        try {
            // Wait for the GUI to be closed
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start spiller
        spiller.main(args);
    }
}
