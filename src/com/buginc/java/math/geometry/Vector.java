package com.buginc.java.math.geometry;

import com.buginc.java.math.Number;

//** ** Created by DeveloperHacker ** **//
//* https://github.com/DeveloperHacker *//

public class Vector {

    private final double x;
    private final double y;

    private double abs = -1.0;
    private double sbs = -1.0;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the length of the vector in the square
     */
    public double sbs() {
        if (sbs == -1.0) sbs = x * x + y * y;
        return sbs;
    }

    /**
     * @return the length of the vector
     */
    public double abs() {
        if (abs == -1.0) abs = Math.sqrt(sbs());
        return abs;
    }

    /**
     * @return the normalized vector
     */
    public Vector nrm() {
        return this.dec(abs());
    }

    /**
     * @return a vector of an increased 'a' times
     */
    public Vector inc(double a) {
        return new Vector(x * a, y * a);
    }

    /**
     * @return a vector of an decreased 'a' times
     */
    public Vector dec(double a) {
        return new Vector(x / a, y / a);
    }


    /**
     * @return a vector turning at 'theta' radians
     */
    public Vector rtt(double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        return new Vector(x * cos - y * sin, x * sin + y * cos);
    }

    public Vector add(double num) {
        return new Vector(x + num, y + num);
    }

    /**
     * @return the sum of the vectors
     */
    public Vector add(Vector vector) {
        return new Vector(x + vector.x, y + vector.y);
    }

    public Vector rem(double num) {
        return new Vector(x - num, y - num);
    }

    /**
     * @return the difference of the vectors
     */
    public Vector rem(Vector vector) {
        return new Vector(x - vector.x, y - vector.y);
    }

    /**
     * @return the length of the vector product of vectors
     * sign of the result indicates the direction of the resulting vector
     */
    public double tms(Vector vector) {
        return x * vector.y - vector.x * y;
    }

    /**
     * @return a scalar product of vectors
     */
    public double sml(Vector vector) {
        return x * vector.x + y * vector.y;
    }

    /**
     * @return a vector directed in the opposite direction
     */
    public Vector rvs() {
        return new Vector(-x, -y);
    }

    /**
     * @return the cosine of the angle between the vectors
     */
    public double cos(Vector vector) {
        return sml(vector) / (abs() * vector.abs());
    }

    /**
     * @return a vector with a length equal to num
     */
    public Vector lng(double length) {
        return inc(length / abs());
    }

    /**
     * @return a maximum dimension
     */
    public double max() {
        return Math.max(x, y);
    }

    /**
     * @return a maximum dimension
     */
    public double min() {
        return Math.min(x, y);
    }

    public boolean equals(Vector vector, double error) {
        return Number.equals(x, vector.x, error) && Number.equals(y, vector.y, error);
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public int intX() {
        return (int) Math.round(x);
    }

    public int intY() {
        return (int) Math.round(y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        if (Double.compare(vector.x, x) != 0) return false;
        return Double.compare(vector.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    @Override
    public String toString() {
        return "(" + x + "; " + y + ')';
    }
}
