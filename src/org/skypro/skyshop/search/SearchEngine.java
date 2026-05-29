package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {

    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable searchable) {
        items.add(searchable);
    }

    public Set<Searchable> search(String query) {

        return items.stream()
                .filter(item -> item.getSearchTerm()
                        .toLowerCase()
                        .contains(query.toLowerCase()))
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(new SearchComparator())
                ));
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