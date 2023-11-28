package pl.polsl.soltysik;

import pl.polsl.soltysik.gui.SortGUI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SortMVC {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sortowanie bąbelkowe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SortGUI sortGUI = new SortGUI();
            frame.add(sortGUI.getMainPanel());

            frame.pack();
            frame.setVisible(true);
        });
    }
}