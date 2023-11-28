package pl.polsl.soltysik;

import pl.polsl.soltysik.model.SortModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Klasa SortController jest kontrolerem w wzorcu MVC oraz sortuje dane
 */
public class SortController {
    /**
     * Model danych
     */
    private SortModel<Integer> model;

    /**
     * Widok do wyświetlania danych i animacji sortowania
     */
    private SortView view;

    /**
     * Timer do kontrolowania szybkości animacji
     */
    private Timer timer;

    /**
     * Indeksy wykorzystywane w algorytmie sortowania
     */
    private int i, j;

    /**
     * Konstruktor inicjuje kontroler oraz zawiera sortowanie
     *
     * @param model model danych do sortowania
     * @param view  widok do wyświetlania danych i animacji sortowania
     */
    public SortController(SortModel<Integer> model, SortView view) {
        this.model = model;
        this.view = view;
        this.i = 0;
        this.j = 0;

        timer = new Timer(view.getAnimationSpeed(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Integer> data = model.getData();
                if (i < data.size() - 1) {
                    if (j < data.size() - i - 1) {
                        if (data.get(j) > data.get(j + 1)) {
                            model.swap(j, j + 1);
                        }
                        j++;
                    } else {
                        i++;
                        j = 0;
                    }
                    view.updateView(data);
                } else {
                    timer.stop();
                }
            }
        });
    }

    /**
     * Rozpoczęcie procesu sortowania
     */
    public void startSorting() {
        timer.start();
    }
}