package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {

    private final List<Searchable> items = new ArrayList<>();

    public void add(Searchable searchable) {
        items.add(searchable);
    }

    public Map<String, Searchable> search(String query) {

        Map<String, Searchable> result = new TreeMap<>();

        for (Searchable item : items) {
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                result.put(item.getName(), item);
            }
        }

        return result;
    }


    public Searchable findBestMatch(String search) throws BestResultNotFound {

        Searchable best = null;
        int maxCount = 0;

        for (Searchable item : items) {

            String term = item.getSearchTerm().toLowerCase();
            int count = countOccurrences(term, search.toLowerCase());

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