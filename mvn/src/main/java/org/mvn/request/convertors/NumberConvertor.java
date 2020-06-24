package org.mvn.request.convertors;

public class NumberConvertor {
    public static Double convertToDouble(String strnum) {
        if(strnum ==null)return 00.00;
        if(isnumeric(strnum)){
            return Double.parseDouble(strnum);
        }
        return 00.00;
    }

    public static boolean isnumeric(String strnum) {
        if(strnum ==null)return false;
        return  strnum.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
