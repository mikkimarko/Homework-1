package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {

    private final List<Searchable> items = new ArrayList<>();

    public void add(Searchable searchable) {
        items.add(searchable);
    }

    public List<Searchable> search(String query) {

        List<Searchable> result = new ArrayList<>();

        for (Searchable item : items) {
            if (item.getSearchTerm().contains(query)) {
                result.add(item);
            }
        }

        return result;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {

        Searchable best = null;
        int maxCount = 0;

        for (Searchable item : items) {

            String term = item.getSearchTerm();
            int count = countOccurrences(term, search);

            if (count > maxCount) {
                maxCount = count;
                best = item;
            }
        }

        if (best == null) {
            throw new BestResultNotFound("Не найдено результатов для запроса: " + search);
        }

        return best;
    }

    private int countOccurrences(String str, String sub) {

        int count = 0;
        int index = 0;

        while (true) {

            int found = str.indexOf(sub, index);

            if (found == -1) {
                break;
            }

            count++;
            index = found + sub.length();
        }

        return count;
    }

}