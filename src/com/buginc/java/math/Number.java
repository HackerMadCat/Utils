package com.buginc.java.math;

import com.buginc.java.math.geometry.Vector;

import java.nio.ByteBuffer;

//** ** Created by DeveloperHacker ** **//
//* https://github.com/DeveloperHacker *//

public class Number {

    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }

    public static byte[] toByteArray(int value) {
        byte[] bytes = new byte[4];
        ByteBuffer.wrap(bytes).putInt(value);
        return bytes;
    }

    public static int toInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

    public static Byte[] boxing(byte[] arr) {
        Byte[] res = new Byte[arr.length];
        for (int i = 0; i < arr.length; i++) res[i] = arr[i];
        return res;
    }

    public static byte[] unboxing(Byte[] arr) {
        byte[] res = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) res[i] = arr[i];
        return res;
    }

    public static double round(double value, int dec) {
        double pow = Math.pow(10.0, (double) dec);
        return Math.round(value * pow) / pow;
    }

    public static boolean equals(double a, double b, double error) {
        return (a >= b - error && a <= b + error) && (b >= a - error && b <= a + error);
    }

    public static boolean equals(Vector a, Vector b, double error) {
        return Number.equals(a.x(), b.x(), error) && Number.equals(a.y(), b.y(), error);
    }
}
