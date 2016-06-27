package com.buginc.java.math.geometry;

import com.buginc.java.math.Number;

import org.junit.Test;

public class VectorTest {
    @Test
    public void ang() throws Exception {
        Vector a = new Vector(0, 1);
        Vector b = new Vector(1, 1);
        assert (Number.equals(a.ang(a), 0, 1e-6));
        assert (Number.equals(b.ang(b), 0, 1e-6));
        assert (Number.equals(a.ang(b), Math.PI / 4, 1e-6));
        assert (Number.equals(b.ang(a), Math.PI * 7 / 4, 1e-6));
    }
}