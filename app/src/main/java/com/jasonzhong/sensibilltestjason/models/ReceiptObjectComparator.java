package com.jasonzhong.sensibilltestjason.models;

import java.util.Comparator;

/**
 * Created by jason.zhong on 31/03/2016.
 */
public class ReceiptObjectComparator implements Comparator<Receipt> {

    public int compare(Receipt obj1, Receipt obj2) {
        return obj1.getName().compareTo(obj2.getName());
    }
}
