package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;

public class HalfPromotion {
    public double apply(CartItem item, double price) {
        int qua=item.getQuantity()/2;
        return price*item.getQuantity()-price/2*qua;
    }
}
