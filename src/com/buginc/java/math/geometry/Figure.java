package com.buginc.java.math.geometry;

public abstract class Figure {

    private final Vector position;

    private final Vector dimension;

    private Vector center = null;

    public Figure(Vector position, Vector dimension) {
        this.position = position;
        this.dimension = dimension;
    }

    public final Vector position() {
        return position;
    }

    public final Vector dimension() {
        return dimension;
    }

    public final double x() {
        return position().x();
    }

    public final double y() {
        return position().y();
    }

    public final double width() {
        return dimension().x();
    }

    public final double height() {
        return dimension().y();
    }

    public final int intX() {
        return position().intX();
    }

    public final int intY() {
        return position().intY();
    }

    public final int intWidth() {
        return dimension().intX();
    }

    public final int intHeight() {
        return dimension().intY();
    }

    public final boolean higher(double y) {
        return y() + height() > y;
    }

    public final boolean lower(double y) {
        return y() < y;
    }

    public final boolean lefter(double x) {
        return x() < x;
    }

    public final boolean righter(double x) {
        return x() + width() > x;
    }

    /**
     * @return the round rectangle of figure
     */
    public final Rectangle rectangle() {
        return new Rectangle(position(), dimension());
    }

    /**
     * @return a center of figure
     */
    public final Vector cnt() {
        if (center != null) return center;
        center = center();
        return center;
    }

    protected Vector center() {
        return position().add(dimension().dec(2.0));
    }

    /**
     * @return figure moved into the position 'position'
     */
    public abstract Figure mve(Vector position);

    /**
     * @return a figure of an increased 'a' times relative to the position
     */
    public abstract Figure inc(double a);

    /**
     * @return a rectangle of an decreased 'a' times relative to the position
     */
    public abstract Figure dec(double a);

    /**
     * @return the radius of a circle around the right figure with a side of 'a'
     */
    public static double rca(double a, int angles) {
        return a / (2.0 * Math.sin(Math.PI / angles));
    }

    /**
     * @return the radius of the inscribed circle in the right figure with a side of 'a'
     */
    public static double rci(double a, int angles) {
        return a / (2.0 * Math.tan(Math.PI / angles));
    }

    /**
     * @return the length of the sides of the right figure inscribed in a circle of radius 'radius'
     */
    public static double lfi(double radius, int angles) {
        return radius * (2.0 * Math.sin(Math.PI / angles));
    }

    /**
     * @return the length of the sides of the right figure around in a circle of radius 'radius'
     */
    public static double lfa(double radius, int angles) {
        return radius * (2.0 * Math.tan(Math.PI / angles));
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    public abstract Figure clone();
}
