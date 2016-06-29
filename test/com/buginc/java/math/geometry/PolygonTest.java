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
        for (int i = 0; i < test1.length; i++) assert Number.equals(test1[i], outline.get(i), 1e-6);
        Vector[] test2 = {new Vector(8, 3), new Vector(8, 9), new Vector(2, 9), new Vector(2, 3)};
        List<Vector> points = polygon.points();
        for (int i = 0; i < test2.length; i++) assert Number.equals(test2[i], points.get(i), 1e-6);
    }

    @Test
    public void rtt() {
        Polygon polygon = Polygon.polygon(new double[][]{{2, 3}, {6, 3}, {6, 10}, {2, 10}});
        List<Vector> points = polygon.points();
        Vector[] test = {new Vector(2, 3), new Vector(6, 3), new Vector(6, 10), new Vector(2, 10)};
        for (int i = 0; i < test.length; i++) assert Number.equals(test[i], points.get(i), 1e-6);
        points = polygon.rtt(new Vector(4, 3), Math.PI * 3 / 2).points();
        test = new Vector[]{new Vector(4, 5), new Vector(4, 1), new Vector(11, 1), new Vector(11, 5)};
        for (int i = 0; i < test.length; i++) assert Number.equals(test[i], points.get(i), 1e-6);
    }

    @Test
    public void ins() {
        Polygon polygon = Polygon.polygon(new double[][]{{2, 3}, {6, 3}, {6, 10}, {2, 10}});
        for (int i = 2; i <= 6; i++) {
            for (int j = 3; j <= 10; j++) {
                assert polygon.ins(new Vector(i, j));
            }
        }
        for (int i = 7; i <= 10; i++) {
            for (int j = 3; j <= 10; j++) {
                assert !polygon.ins(new Vector(i, j));
            }
        }
    }
}