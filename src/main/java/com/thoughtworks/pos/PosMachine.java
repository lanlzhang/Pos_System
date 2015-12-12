package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;
import com.thoughtworks.pos.domain.Item;

import java.util.List;

public final class PosMachine {
    private final List<Item> allItems;
    private final PromotionManager promotionManager;
    private final HalfManager halfManager;

    public PosMachine(final List<Item> allItems, PromotionManager promotionManager,HalfManager halfManager) {
        this.allItems = allItems;
        this.promotionManager = promotionManager;
        this.halfManager=halfManager;
    }

    public double calculate(final List<CartItem> cartItems) {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += calculateSubtotal(cartItem);
        }
        return total;
    }

    private double calculateSubtotal(final CartItem cartItem) {
        String barcode = cartItem.getBarcode();
        double originPrice = queryItemPrice(barcode);
        DiscountPromotion availablePromotion = promotionManager.getAvailablePromotion(barcode);
        HalfPromotion halfPromotion=new HalfPromotion();
        double subtotal;
        if (availablePromotion!=null){
            if (halfManager.halfPrice(barcode)==1){
                double price=availablePromotion.apply(cartItem,originPrice)/cartItem.getQuantity();
                subtotal=halfPromotion.apply(cartItem,price);
            }
            else {
                subtotal=availablePromotion.apply(cartItem,originPrice);
            }
        }
        else{
            if (halfManager.halfPrice(barcode)==1){
                subtotal=halfPromotion.apply(cartItem,originPrice);
            }
            else{
                subtotal=cartItem.getQuantity()*originPrice;
            }
        }
        return subtotal;
    }

    private double queryItemPrice(final String barcode) {
        for (Item item : allItems) {
            if (item.getBarcode().equals(barcode)) {
                return item.getPrice();
            }
        }

        throw new IllegalArgumentException("unknown item");
    }
}
