package pl.polsl.soltysik.model;

import pl.polsl.soltysik.exception.SortException;

import java.util.List;

/**
 * Klasa reprezentująca model danych do sortowania bąbelkowego
 * Implementuje interfejs SortModel dla typu Integer
 */

// - brak podziału MVC na pakiety (-1),
// - brak własnej klasy wyjątku (-1),
// - niedociągnięcia MVC (większość metody actionPerformed jest funkcjonalnością modelu) (-1).
public class BubbleSortModel implements SortModel<Integer> {
    /** Lista przechowująca dane do posortowania. */
    private List<Integer> data;

    /**
     * Konstruktor inicjalizujący model danymi wejściowymi
     *
     * @param data Lista danych do posortowania
     */
    public BubbleSortModel(List<Integer> data){
//        if(data == null) {
//            throw new SortException("Data cannot be null");
//        }
        this.data = data;
    }

    /**
     * Metoda zwracająca referencję do listy danych.
     *
     * @return Lista danych do posortowania
     */
    @Override
    public List<Integer> getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    @Override
    public void setData(List<Integer> data) {
        this.data = data;
    }
    
    /**
     * Metoda zamieniająca miejscami elementy o podanych indeksach w liście danych.
     *
     * @param index1 Indeks pierwszego elementu do zamiany
     * @param index2 Indeks drugiego elementu do zamiany
     */
    @Override
    public void swap(int index1, int index2) {
        Integer temp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, temp);
    }
}