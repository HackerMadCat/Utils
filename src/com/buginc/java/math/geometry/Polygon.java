package com.buginc.java.math.geometry;

import java.util.ArrayList;
import java.util.List;

//** ** Created by DeveloperHacker ** **//
//* https://github.com/DeveloperHacker *//

public class Polygon {

    private final Vector position;

    private final List<Vector> outline;

    public Polygon(Vector position, List<Vector> outline) {
        this.position = position;
        this.outline = new ArrayList<>(outline);
    }

    public static Polygon rectangle(Vector position, Vector dimension) {
        List<Vector> outline = new ArrayList<>();
        outline.add(new Vector(dimension.x(), 0.0));
        outline.add(new Vector(0.0, dimension.y()));
        outline.add(new Vector(-dimension.x(), 0.0));
        outline.add(new Vector(0.0, -dimension.y()));
        return new Polygon(position, outline);
    }

    public static Polygon rectangle(double x, double y, double width, double height) {
        List<Vector> outline = new ArrayList<>();
        outline.add(new Vector(width, 0.0));
        outline.add(new Vector(0.0, height));
        outline.add(new Vector(-width, 0.0));
        outline.add(new Vector(0.0, -height));
        return new Polygon(new Vector(x, y), outline);
    }

    public static Polygon rectangle(int x, int y, int width, int height) {
        List<Vector> outline = new ArrayList<>();
        outline.add(new Vector(width, 0));
        outline.add(new Vector(0, height));
        outline.add(new Vector(-width, 0));
        outline.add(new Vector(0, -height));
        return new Polygon(new Vector(x, y), outline);
    }

    public static Polygon rightFigure(Vector center, double radius, int qVertex, double theta) {
        List<Vector> outline = new ArrayList<>();
        Vector prevRadius = new Vector(0.0, -radius).rtt(theta);
        for (int i = 0; i < qVertex; i++) {
            Vector nextRadius = prevRadius.rtt(Math.PI * 2.0 / qVertex);
            outline.add(nextRadius.rem(prevRadius));
            prevRadius = nextRadius;
        }
        Vector cnt = new Vector(0, 0);
        int i = outline.size();
        for (Vector vector : outline) {
            cnt = cnt.add(vector.inc(i));
            --i;
        }
        cnt = cnt.dec(outline.size());
        return new Polygon(center.rem(cnt), outline);
    }

    public Vector cnt() {
        Vector center = new Vector(0, 0);
        int i = outline.size();
        for (Vector vector : outline) {
            center = center.add(vector.inc(i));
            --i;
        }
        return center.dec(outline.size());
    }

    public double x() {
        return position.x();
    }

    public double y() {
        return position.y();
    }

    public double width() {
        Vector prev = position;
        double min = prev.x();
        double max = prev.x();
        for (Vector vector : outline) {
            prev = prev.add(vector);
            if (prev.x() > max) max = prev.x();
            if (prev.x() < min) min = prev.x();
        }
        return Math.abs(max - min);
    }

    public double height() {
        Vector prev = position;
        double min = prev.y();
        double max = prev.y();
        for (Vector vector : outline) {
            prev = prev.add(vector);
            if (prev.y() > max) max = prev.y();
            if (prev.y() < min) min = prev.y();
        }
        return Math.abs(max - min);
    }

    public Vector position() {
        return position;
    }

    public Vector dimension() {
        return new Vector(width(), height());
    }

    public int intX() {
        return position.intX();
    }

    public int intY() {
        return position.intY();
    }

    public int intWidth() {
        return dimension().intX();
    }

    public int intHeight() {
        return dimension().intY();
    }

    /**
     * @return a polygon of an increased 'a' times relative to the origin
     */
    public Polygon inc(Vector origin, double a) {
        Vector position = origin.add(this.position.rem(origin).inc(a));
        List<Vector> outline = new ArrayList<>();
        for (Vector vector : this.outline) outline.add(vector.inc(a));
        return new Polygon(position, outline);
    }

    /**
     * @return a polygon of an decreased 'a' times relative to the origin
     */
    public Polygon dec(Vector origin, double a) {
        Vector position = origin.add(this.position.rem(origin).dec(a));
        List<Vector> outline = new ArrayList<>();
        for (Vector vector : this.outline) outline.add(vector.dec(a));
        return new Polygon(position, outline);
    }

    /**
     * @return a polygon turning relative to the origin at 'theta' radians
     */
    public Polygon rtt(Vector origin, double theta) {
        Vector position = (this.position.rem(origin)).rtt(theta).add(origin);
        List<Vector> outline = new ArrayList<>();
        for (Vector vector : outline) outline.add(vector.rtt(theta));
        return new Polygon(position, outline);
    }

    public List<Vector> outline() {
        return new ArrayList<>(outline);
    }

    public List<Vector> points() {
        List<Vector> points = new ArrayList<>();
        Vector prev = position;
        for (Vector vector : outline) {
            points.add(prev);
            prev = prev.add(vector);
        }
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polygon)) return false;
        Polygon polygon = (Polygon) o;
        if (position != null ? !position.equals(polygon.position) : polygon.position != null) return false;
        return outline.equals(polygon.outline);

    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + outline.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{ " + position + ", " + outline + "}";
    }
}
