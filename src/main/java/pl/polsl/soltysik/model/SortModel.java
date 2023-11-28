package pl.polsl.soltysik.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interfejs dla modelu danych do posortowania
 */
public interface SortModel<T> {
    List<T> getData();

    void setData(List<T> data);

    void swap(int index1, int index2);
}