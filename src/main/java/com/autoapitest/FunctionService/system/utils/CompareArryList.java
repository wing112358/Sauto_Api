package com.autoapitest.FunctionService.system.utils;

import java.util.Collections;
import java.util.List;

public class CompareArryList {

    public static boolean compareIntList(List<Integer> a, List<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return false;
            }
        }
        return true;
    }


    }
