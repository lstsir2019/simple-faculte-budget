/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fst.commandeapiv4.common.util;

import java.math.BigDecimal;

/**
 *
 * @author AMINE
 */
public class NumberUtil {

    private static final String CHAINE_VIDE = "";

    public static Double toDouble(String value) {
        if (value == null || value.isEmpty()) {
            return 0D;
        } else {
            return Double.parseDouble(value);
        }
    }

    public static String toString(Double value) {
        if (value == null) {
            return CHAINE_VIDE;
        } else {
            return String.valueOf(value);
        }
    }

    public static Integer toInteger(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }
    
    public static Long toLong(String value){
        if (value == null || value.isEmpty()) {
            return 0L;
        } else {
            return Long.parseLong(value);
        }
    }
}
