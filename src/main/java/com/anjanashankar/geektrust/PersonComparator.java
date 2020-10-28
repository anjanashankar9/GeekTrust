package com.anjanashankar.geektrust;

import java.util.Comparator;

/**
 * @Author Anjana Shankar
 * @Created 2020-10-28
 */
public class PersonComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Person p1 = (Person) o1;
        Person p2 = (Person) o2;

        if(p1.getSequenceId() == p2.getSequenceId())
            return 0;
        if (p1.getSequenceId() < p2.getSequenceId())
            return -1;
        return 1;
    }
}
