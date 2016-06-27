package com.buginc.java.math.geometry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//** ** Created by DeveloperHacker ** **//
//* https://github.com/DeveloperHacker *//

public class Ellipse {

    private final Vector position;
    private final Vector dimension;

    public Ellipse(Rectangle rectangle) {
        this.position = rectangle.position();
        this.dimension = rectangle.dimension();
    }

    public Ellipse(Vector position, Vector dimension) {
        this.position = position;
        this.dimension = dimension;
    }

    public Ellipse(double x, double y, double width, double height) {
        this.position = new Vector(x, y);
        this.dimension = new Vector(width, height);
    }

    public Ellipse(int x, int y, int width, int height) {
        this.position = new Vector(x, y);
        this.dimension = new Vector(width, height);
    }

    /**
     * @return the displaced ellipse
     */
    public Ellipse mve(Vector position) {
        return new Ellipse(position, dimension);
    }

    /**
     * @return a ellipse of an increased 'a' times
     */
    public Ellipse inc(double a) {
        return new Ellipse(position, dimension.inc(a));
    }


    /**
     * @return a Ellipse of an decreased 'a' times
     */
    public Ellipse dec(double a) {
        return new Ellipse(position, dimension.dec(a));
    }

    /**
     * @return the distance from the center of the ellipse to its generator
     */
    public double rds(double cos) {
        double a = width() / 2;
        double b = height() / 2;
        double e = Math.sqrt(1 - b * b / (a * a));
        return b / Math.sqrt(1 - e * e * cos * cos);
    }

    /**
     * @return the distance of the ellipses
     */
    public double dst(Ellipse ellipse) {
        Vector direct = ellipse.cnt().rem(this.cnt());
        if (direct.abs() == 0.0) return 0.0;
        Vector norm = new Vector(1, 0);
        double cos = norm.cos(direct);
        double r1 = this.rds(cos);
        double r2 = ellipse.rds(cos);
        double dist = direct.abs() - r1 - r2;
        return (dist < 0) ? 0 : dist;
    }

    /**
     * @return the distance between the ellipse and rectangle
     */
    public double dst(Rectangle rect) {
        Rectangle dff = rect.dff(this.rectangle());
        if (!dff.imr()) return 0.0;
        Vector A = rect.position().rem(this.cnt());
        Vector B = rect.position().add(new Vector(rect.width(), 0)).rem(this.cnt());
        Vector C = rect.position().add(new Vector(0, rect.height())).rem(this.cnt());
        Vector D = rect.position().add(rect.dimension()).rem(this.cnt());
        Map<Double, Vector> map = new HashMap<>();
        map.put(A.abs(), A);
        map.put(B.abs(), B);
        map.put(C.abs(), C);
        map.put(D.abs(), D);
        Vector E = map.get(Collections.min(map.keySet()));
        double dist = dst(E);
        double h = Double.POSITIVE_INFINITY;
        Vector center = cnt();
        if (rect.y() < center.y() && rect.y() + rect.height() > center.y()) h = -dff.width();
        else if (rect.x() < center.x() && rect.x() + rect.width() > center.x()) h = -dff.height();
        return Math.min(dist, h);
    }

    /**
     * @return true if the ellipses overlap
     */
    public boolean ict(Ellipse ellipse) {
        return this.dst(ellipse) == 0.0;
    }

    /**
     * @return true if the ellipse and the rectangle overlap
     */
    public boolean ict(Rectangle rect) {
        return this.dst(rect) == 0.0;
    }

    /**
     * @return the distance between the ellipse and vector
     */
    public double dst(Vector vector) {
        Vector norm = new Vector(1, 0);
        double cos = norm.cos(vector);
        double dist = vector.abs() - rds(cos);
        return (dist < 0) ? 0.0 : dist;
    }

    /**
     * @return a center of ellipse
     */
    public Vector cnt() {
        return position.add(dimension.dec(2));
    }

    public Vector dimension() {
        return dimension;
    }

    public Vector position() {
        return position;
    }

    public double x() {
        return position.x();
    }

    public double y() {
        return position.y();
    }

    public double width() {
        return dimension.x();
    }

    public double height() {
        return dimension.y();
    }

    public int intX() {
        return position.intX();
    }

    public int intY() {
        return position.intY();
    }

    public int intWidth() {
        return dimension.intX();
    }

    public int intHeight() {
        return dimension.intY();
    }

    public Rectangle rectangle() {
        return new Rectangle(position, dimension);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ellipse)) return false;
        Ellipse ellipse = (Ellipse) o;
        if (position != null ? !position.equals(ellipse.position) : ellipse.position != null) return false;
        return dimension != null ? dimension.equals(ellipse.dimension) : ellipse.dimension == null;

    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (dimension != null ? dimension.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + x() + "; " + y() + "; " + width() + "; " + height() + ")";
    }
}
