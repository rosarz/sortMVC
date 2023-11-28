package pl.polsl.soltysik.gui;

import pl.polsl.soltysik.SortController;
import pl.polsl.soltysik.SortView;
import pl.polsl.soltysik.model.BubbleSortModel;
import pl.polsl.soltysik.model.SortModel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortGUI {
    private JPanel mainPanel;
    private JTextField inputField;
    private JButton sortButton;
    private JButton resetButton;
    private JTree dataTree;
    private DefaultMutableTreeNode rootNode;
    private SortModel<Integer> model;
    private SortView view;
    private SortController controller;

    public SortGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        inputField = new JTextField(20);
        sortButton = new JButton("Sortuj");
        resetButton = new JButton("Reset");

        model = new BubbleSortModel(new ArrayList<>());
        view = new SortView(new ArrayList<>());
        controller = new SortController(model, view);

        // Inicjalizacja korzenia drzewa
        rootNode = new DefaultMutableTreeNode("Drzewo danych");

        // Inicjalizacja drzewa z modelem
        dataTree = new JTree(rootNode);
        JScrollPane treeScrollPane = new JScrollPane(dataTree);


        // Przycisk Sortuj

        sortButton.setToolTipText("Wpisz liczby oddzielone przecinkami bez spacji np. 1,2,3,4,5 (SC: ALT+ENTER)");
        sortButton.setMnemonic(KeyEvent.VK_ENTER);
        sortButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = inputField.getText();
                    List<Integer> newData = Arrays.stream(input.split(","))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

                    model = new BubbleSortModel(newData);
                    view = new SortView(newData);
                    controller = new SortController(model, view);

                    mainPanel.remove(view);
                    mainPanel.add(view);

                    ((JFrame) SwingUtilities.getRoot(mainPanel)).pack();  // Pakowanie głównego okna
                    controller.startSorting();
                } catch (NumberFormatException ex) {
                    // Wyjątek dla niepoprawnego formatu danych wejściowych
                    showAlertDialog("Błąd", "Wprowadź poprawne liczby oddzielone przecinkami.");
                } catch (Exception ex) {
                    // Inny ogólny wyjątek
                    showAlertDialog("Błąd", "Wystąpił błąd podczas sortowania.");
                }
            }
        });


        // Przycisk Reset
        resetButton.setToolTipText("Resetuje dane i wizualizację (SC: ALT+R)");
        resetButton.setMnemonic(KeyEvent.VK_D);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Wyczyszczenie danych i odświeżenie widoku
                    inputField.setText("");
                    model = new BubbleSortModel(new ArrayList<>());
                    view.clearVisualization(); // Nowa metoda do wyczyszczenia wizualizacji
                    controller = new SortController(model, view);

                    mainPanel.remove(view);
                    mainPanel.add(view);

                    ((JFrame) SwingUtilities.getRoot(mainPanel)).pack(); // Pakowanie głównego okna
                    // Brak startSorting(), ponieważ resetujemy do stanu początkowego
                } catch (Exception ex) {
                    // Inny ogólny wyjątek
                    showAlertDialog("Błąd", "Wystąpił błąd podczas resetowania.");
                }
            }
        });

        mainPanel.add(inputField);
        mainPanel.add(sortButton);
        mainPanel.add(resetButton);  // Dodany przycisk Reset
        mainPanel.add(treeScrollPane);
        mainPanel.add(Box.createVerticalStrut(10));  // Dodanie odstępu między przyciskami
        mainPanel.add(Box.createVerticalStrut(10));
    }
    private void showAlertDialog(String title, String message) {
        JOptionPane.showMessageDialog(mainPanel, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}