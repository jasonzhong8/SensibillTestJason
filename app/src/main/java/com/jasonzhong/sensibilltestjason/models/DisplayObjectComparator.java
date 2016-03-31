package com.jasonzhong.sensibilltestjason.models;

import java.util.Comparator;

/**
 * Created by jason.zhong on 31/03/2016.
 */
public class DisplayObjectComparator implements Comparator<Display> {

    public int compare(Display obj1, Display obj2) {
        return obj1.getName().compareTo(obj2.getName());
    }
}
