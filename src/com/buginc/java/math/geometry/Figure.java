package com.buginc.java.math.geometry;

public abstract class Figure {

    private final Vector position;

    private final Vector dimension;

    protected Figure(Vector position, Vector dimension) {
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

    /**
     * @return the round rectangle of figure
     */
    public final Rectangle rectangle() {
        return new Rectangle(position(), dimension());
    }

    /**
     * @return a center of figure
     */
    public Vector cnt() {
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
    public static double outRadius(double a, int angles) {
        return a / (2.0 * Math.sin(Math.PI / angles));
    }

    /**
     * @return the radius of the inscribed circle in the right figure with a side of 'a'
     */
    public static double inRadius(double a, int angles) {
        return a / (2.0 * Math.tan(Math.PI / angles));
    }

    /**
     * @return the dimension of a circle around the right figure with a side of 'a'
     */
    public static Vector dimOutCircle(double a, int angles) {
        double r = outRadius(a, angles);
        return new Vector(2 * r, 2 * r);
    }

    /**
     * @return the dimension of the inscribed circle in the right figure with a side of 'a'
     */
    public static Vector dimInCircle(double a, int angles) {
        double r = inRadius(a, angles);
        return new Vector(2 * r, 2 * r);
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();
}
