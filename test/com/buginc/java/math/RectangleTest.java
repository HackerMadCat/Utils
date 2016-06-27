package com.buginc.java.math;

import com.buginc.java.math.geometry.Rectangle;
import com.buginc.java.math.geometry.Vector;
import org.junit.Test;

public class RectangleTest {

    @Test
    public void dst() {
        Rectangle rect1 = new Rectangle(1, 6, 10, 6);
        Rectangle rect2 = new Rectangle(4, 1, 6, 4);
        assert (Number.equals(rect1.dst(rect2), 1.0, 1e-6));
        rect2 = rect2.mve(new Vector(6, -1));
        assert (Number.equals(rect1.dst(rect2), 3.0, 1e-6));
        rect2 = rect2.mve(new Vector(13, 7));
        assert (Number.equals(rect1.dst(rect2), 2.0, 1e-6));
        rect2 = rect2.mve(new Vector(15, 5));
        assert (Number.equals(rect1.dst(rect2), 4.0, 1e-6));
        rect2 = rect2.mve(new Vector(15, -1));
        assert (Number.equals(rect1.dst(rect2), 5.0, 1e-6));
    }

    @Test
    public void cmb() {
        Rectangle rect1 = new Rectangle(1, 6, 10, 6);
        Rectangle rect2 = new Rectangle(4, 1, 6, 4);
        assert (rect1.cmb(rect2).equals(new Rectangle(1, 1, 10, 11)));
        rect2 = rect2.mve(new Vector(4, 3));
        assert (rect1.cmb(rect2).equals(new Rectangle(1, 3, 10, 9)));
        rect2 =  new Rectangle(0, 0, 0, 0);
        assert (rect1.cmb(rect2).equals(new Rectangle(0, 0, 11, 12)));
    }
}