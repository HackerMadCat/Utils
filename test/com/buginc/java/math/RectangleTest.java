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
        Rectangle rect3 = new Rectangle(6, -1, 6, 4);
        assert (Number.equals(rect1.dst(rect3), 3.0, 1e-6));
        Rectangle rect4 = new Rectangle(13, 7, 6, 4);
        assert (Number.equals(rect1.dst(rect4), 2.0, 1e-6));
        Rectangle rect5 = new Rectangle(15, 5, 6, 4);
        assert (Number.equals(rect1.dst(rect5), 4.0, 1e-6));
        Rectangle rect6 = new Rectangle(15, -1, 6, 4);
        assert (Number.equals(rect1.dst(rect6), 5.0, 1e-6));
    }

    @Test
    public void cmb() {
        Rectangle rect1 = new Rectangle(1, 6, 10, 6);
        Rectangle rect2 = new Rectangle(4, 1, 6, 4);
        assert (rect1.cmb(rect2).equals(new Rectangle(1, 1, 10, 11)));
        Rectangle rect3 = new Rectangle(4, 3, 6, 4);
        assert (rect1.cmb(rect3).equals(new Rectangle(1, 3, 10, 9)));
        Rectangle rect4 = new Rectangle(0, 0, 0, 0);
        assert (rect1.cmb(rect4).equals(new Rectangle(0, 0, 11, 12)));
    }
}