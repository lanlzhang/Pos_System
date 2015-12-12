package com.thoughtworks.pos;

public class HalfManager {

    public int halfPrice(final String barcode){
        for (String item: ShopData.SECONG_HALF_DATA){
            if (barcode.equals(item))
                return 1;
        }
        return 0;
    }
}
