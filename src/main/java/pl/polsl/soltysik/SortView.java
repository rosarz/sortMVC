package pl.polsl.soltysik;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa SortView zawiera widok tablicy oraz animacje sortowanie
 */
public class SortView extends JPanel {
    /**
     * Tablica danych
     */
    private List<Integer> data;

    /**
     * Szerokość słupka w animacji
     */
    private final int barWidth = 20;

    /**
     * Szybkość animacji
     */
    private int animationSpeed = 500; // Prędkość animacji w ms

    /**
     * Konstruktor widoku
     *
     * @param data tablica danych
     */
    public SortView(List<Integer> data) {
        this.data = data;
        this.setPreferredSize(new Dimension(400, 100));
    }

    /**
     * Metoda rysuje komponent
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data != null) { // Sprawdzenie, czy data nie jest null
            int x = 10;
            int y = 80;
            for (int value : data) {
                g.setColor(Color.blue);
                g.fillRect(x, y - value, barWidth, value);
                g.setColor(Color.black);
                g.drawRect(x, y - value, barWidth, value);
                x += barWidth + 5;
            }
        }
    }

    /**
     * Aktualizuje widok i odświeża komponent
     *
     * @param newData nowe dane
     */
    public void updateView(List<Integer> newData) {
        data = newData;
        repaint();
    }

    /**
     * Pobiera szybkość animacji
     *
     * @return szybkość animacji
     */
    public int getAnimationSpeed() {
        return animationSpeed;
    }

   public List<Integer> getData() {
        return data;
    }

    /**
     * Czyści wizualizację danych
     */
    public void clearVisualization() {
        data = new ArrayList<>(); // Inicjalizacja jako pusta lista
        repaint();
    }
}
