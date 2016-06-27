package com.buginc.java.math.geometry;

import com.buginc.java.math.Number;
import org.junit.Test;

import java.util.List;

public class PolygonTest {
    @Test
    public void rightFigure() throws Exception {
        Polygon polygon = Polygon.rightFigure(new Vector(5, 6), 3.0 * Math.sqrt(2), 4, Math.PI / 4);
        Vector[] test1 = {new Vector(0, 6), new Vector(-6, 0), new Vector(0, -6), new Vector(6, 0)};
        List<Vector> outline = polygon.outline();
        for (int i = 0; i < test1.length; i++) {
            assert (Number.equals(test1[i], outline.get(i), 1e-6));
        }
        Vector[] test2 = {new Vector(8, 3), new Vector(8, 9), new Vector(2, 9), new Vector(2, 3)};
        List<Vector> points = polygon.points();
        for (int i = 0; i < test2.length; i++) {
            assert (Number.equals(test2[i], points.get(i), 1e-6));
        }
    }

}