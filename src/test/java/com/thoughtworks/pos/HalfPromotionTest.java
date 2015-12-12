package com.thoughtworks.pos;

import com.thoughtworks.pos.domain.CartItem;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HalfPromotionTest {
    private HalfPromotion halfPromotion;

    @Before
    public void setUp(){halfPromotion=new HalfPromotion();}

    @Test
    public void should_return_original_prize_when_quantity_1(){
        double result=halfPromotion.apply(new CartItem("ITEM000001",1),40);
        assertThat(result,is(40d));
    }

    @Test
    public void should_return_half_prize_when_quantity_2(){
        double result=halfPromotion.apply(new CartItem("ITEM000001",2),40);
        assertThat(result,is(60d));
    }

    @Test
    public void should_not_return_half_prize_when_not_half_quantity_2(){
        double result=halfPromotion.apply(new CartItem("ITEM000003",2),50);
        assertThat(result,is(75d));
    }
}
