package org.skypro.skyshop.search;

import java.util.Comparator;

public class SearchComparator implements Comparator<Searchable> {

    @Override
    public int compare(Searchable a, Searchable b) {
        int lengthCompare = Integer.compare(
                b.getName().length(),
                a.getName().length()
        );
        if (lengthCompare != 0) {
            return lengthCompare;
        }
        return a.getName().compareTo(b.getName());
    }
}
