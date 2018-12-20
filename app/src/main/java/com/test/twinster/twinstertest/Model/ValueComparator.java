package com.test.twinster.twinstertest.Model;

import java.util.Comparator;

public class ValueComparator implements Comparator<Club> {
    public int compare(Club club, Club otherClub) {
        if (club.getValue() > otherClub.getValue()) {
            return -1;
        }
        if (club.getValue() < otherClub.getValue()) {
            return 1;
        }
        return 0;
    }
}

