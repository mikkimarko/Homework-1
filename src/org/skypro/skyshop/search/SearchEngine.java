package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;

    public SearchEngine(int size) {
        this.items = new Searchable[size];
    }

    public void add(Searchable searchable) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = searchable;
                return;
            }
        }
    }

    public Searchable[] search(String query) {
        Searchable[] result = new Searchable[5];
        int count = 0;

        for (Searchable item : items) {
            if (item == null) {
                continue;
            }

            if (item.getSearchTerm().contains(query)) {
                result[count] = item;
                count++;

                if (count == 5) {
                    break;
                }
            }
        }

        return result;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable best = null;
        int maxCount = 0;

        for (Searchable item : items) {
            if (item == null) continue;

            String term = item.getSearchTerm();
            int count = countOccurrences(term, search);

            if (count > maxCount) {
                maxCount = count;
                best = item;
            }
        }

        if (best == null) {
            throw new BestResultNotFound("Ничего не найдено для: " + search);
        }

        return best;
    }

    private int countOccurrences(String str, String sub) {
        int count = 0;
        int index = 0;

        while (true) {
            int found = str.indexOf(sub, index);
            if (found == -1) break;

            count++;
            index = found + sub.length();
        }

        return count;
    }
}
