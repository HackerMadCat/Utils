package com.buginc.java.math;

import com.buginc.java.math.geometry.Ellipse;
import com.buginc.java.math.geometry.Rectangle;
import com.buginc.java.math.geometry.Vector;
import org.junit.Test;

public class EllipseTest {

    @Test
    public void dst() {
        Ellipse e1 = new Ellipse(4, 15, 6, 4);
        assert (Number.equals(e1.dst(e1), 0.0, 1e-6));
        Ellipse e2 = new Ellipse(2, 8, 10, 4);
        assert (Number.equals(e1.dst(e2), 3.0, 1e-6));
        e2 = e2.mve(e2.position().add(new Vector(0, 3)));
        assert (Number.equals(e1.dst(e2), 0.0, 1e-6));
        e2 = e2.mve(e2.position().add(new Vector(0, 1)));
        assert (Number.equals(e1.dst(e2), 0.0, 1e-6));
        e2 = e2.mve(e2.position().add(new Vector(6, 1)));
        assert (Number.equals(e1.dst(e2), 0.0, 1e-6));
        e2 = e2.mve(e2.position().add(new Vector(3, 2)));
        assert (Number.equals(e1.dst(e2), 1.0, 1e-6));
        e1 = new Ellipse(8, 7, 4, 4);
        e2 = new Ellipse(4, 10, 6, 6);
        assert (Number.equals(e1.dst(e2), 0.0, 1e-6));
        e1 = e1.mve(e1.position().add(new Vector(3, -4)));
        assert (Number.equals(e1.dst(e2), 5.0, 1e-6));
    }

    @Test
    public void dst1() {
        Ellipse elp = new Ellipse(1, 6, 10, 6);
        Rectangle rect = new Rectangle(4, 1, 6, 4);
        assert (Number.equals(elp.dst(rect), 1.0, 1e-6));
        rect = rect.mve(new Vector(6, -1));
        assert (Number.equals(elp.dst(rect), 3.0, 1e-6));
        rect = rect.mve(new Vector(13, 7));
        assert (Number.equals(elp.dst(rect), 2.0, 1e-6));
        rect = rect.mve(new Vector(15, 5));
        assert (Number.equals(elp.dst(rect), 4.0, 1e-6));
        elp = new Ellipse(3, 6, 6, 6);
        rect = rect.mve(new Vector(10, 2));
        assert (Number.equals(elp.dst(rect), 2.0, 1e-6));
    }

    @Test
    public void rds() {
        Ellipse ellipse = new Ellipse(123456, 1, 10, 4);
        assert (Number.equals(ellipse.rds(1.0), 5.0, 1e-6));
        assert (Number.equals(ellipse.rds(0.0), 2.0, 1e-6));
        assert (Number.equals(ellipse.rds(-1.0), 5.0, 1e-6));
        ellipse = new Ellipse(10, -345, 6, 6);
        assert (Number.equals(ellipse.rds(1.0), 3.0, 1e-6));
        assert (Number.equals(ellipse.rds(0.0), 3.0, 1e-6));
        assert (Number.equals(ellipse.rds(-1.0), 3.0, 1e-6));
        assert (Number.equals(ellipse.rds(0.5), 3.0, 1e-6));
        assert (Number.equals(ellipse.rds(10.7), 3.0, 1e-6));
    }
}
