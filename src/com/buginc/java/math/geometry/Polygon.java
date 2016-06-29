package com.buginc.java.math.geometry;

import java.util.*;

//** ** Created by DeveloperHacker ** **//
//* https://github.com/DeveloperHacker *//

public class Polygon extends Figure {

    private Boolean convex = null;
    private Vector center = null;

    private final List<Vector> outline;

    public Polygon(Vector position, List<Vector> outline) {
        super(position, dimension(outline));
        this.outline = new ArrayList<>(outline);
    }

    private static Vector dimension(List<Vector> outline) {
        Vector prev = new Vector(0, 0);
        double minX = prev.x();
        double maxX = prev.x();
        double minY = prev.y();
        double maxY = prev.y();
        for (Vector vector : outline) {
            prev = prev.add(vector);
            if (prev.x() > maxX) maxX = prev.x();
            if (prev.x() < minX) minX = prev.x();
            if (prev.y() > maxY) maxY = prev.y();
            if (prev.y() < minY) minY = prev.y();
        }
        return new Vector(maxX - minX, maxY - minY);
    }

    public static Polygon polygon(double[] xPtr, double[] yPtr, int qPtr) {
        if (xPtr.length != yPtr.length && yPtr.length != qPtr && qPtr < 1) throw new IllegalArgumentException();
        List<Vector> outline = new LinkedList<>();
        Vector pos = new Vector(xPtr[0], yPtr[0]);
        Vector prev = pos;
        for (int i = 1; i < qPtr; i++) {
            Vector cur = new Vector(xPtr[i], yPtr[i]);
            outline.add(cur.rem(prev));
            prev = cur;
        }
        outline.add(pos.rem(prev));
        return new Polygon(pos, outline);
    }

    public static Polygon polygon(Vector[] ptr) {
        if (ptr.length < 1) throw new IllegalArgumentException();
        List<Vector> outline = new LinkedList<>();
        Vector pos = ptr[0];
        Vector prev = pos;
        for (int i = 1; i < ptr.length; i++) {
            outline.add(ptr[i].rem(prev));
            prev = ptr[i];
        }
        outline.add(pos.rem(prev));
        return new Polygon(pos, outline);
    }

    public static Polygon polygon(double[][] ptr) {
        if (ptr.length < 1) throw new IllegalArgumentException();
        List<Vector> outline = new LinkedList<>();
        Vector pos = new Vector(ptr[0][0], ptr[0][1]);
        Vector prev = pos;
        for (int i = 1; i < ptr.length; i++) {
            if (ptr[i].length != 2) throw new IllegalArgumentException();
            Vector cur = new Vector(ptr[i][0], ptr[i][1]);
            outline.add(cur.rem(prev));
            prev = cur;
        }
        outline.add(pos.rem(prev));
        return new Polygon(pos, outline);
    }

    public static Polygon polygon(Vector position, Vector[] ptr) {
        return new Polygon(position, Arrays.asList(ptr));
    }

    public static Polygon polygon(Vector position, double[][] ptr) {
        List<Vector> outline = new LinkedList<>();
        for (double[] vector : ptr) {
            if (vector.length != 2) throw new IllegalArgumentException();
            outline.add(new Vector(vector[0], vector[0]));
        }
        return new Polygon(position, outline);
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

    /**
     * @return a polygon of an increased 'a' times relative to the origin
     */
    public Polygon inc(Vector origin, double a) {
        Vector position = origin.add(position().rem(origin).inc(a));
        List<Vector> outline = new ArrayList<>();
        for (Vector vector : this.outline) outline.add(vector.inc(a));
        return new Polygon(position, outline);
    }

    /**
     * @return a polygon of an decreased 'a' times relative to the origin
     */
    public Polygon dec(Vector origin, double a) {
        Vector position = origin.add(position().rem(origin).dec(a));
        List<Vector> outline = new ArrayList<>();
        for (Vector vector : this.outline) outline.add(vector.dec(a));
        return new Polygon(position, outline);
    }

    /**
     * @return a polygon turning relative to the origin at 'theta' radians
     */
    public Polygon rtt(Vector origin, double theta) {
        Vector position = (position().rem(origin)).rtt(theta).add(origin);
        List<Vector> outline = new ArrayList<>();
        for (Vector vector : this.outline) outline.add(vector.rtt(theta));
        return new Polygon(position, outline);
    }

    /**
     * @return true if the polygon is convex
     */
    public boolean cnv() {
        if (convex != null) return convex;
        if (outline.size() < 3) return false;
        double direct = outline.get(outline.size() - 1).tms(outline.get(0));
        for (int i = 1; i < outline.size(); ++i)
            if (0 > direct * outline.get(i - 1).tms(outline.get(i))) {
                convex = false;
                return false;
            }
        convex = true;
        return true;
    }

    /**
     * @return true if the point is inside the polygon
     */
    public boolean ins(Vector point) {
        Vector m = point.rem(position());
        double direct = 0.0;
        boolean init = false;
        for (Vector vector : outline) {
            double temp = vector.tms(m);
            m = m.rem(vector);
            if (temp != 0.0) {
                if (!init) init = true;
                else if (direct * temp < 0) return false;
                direct = temp;
            }
        }
        return true;
    }

    /**
     * @return true if the polygons overlap
     * if polygon is not convex then the result may be a random
     */
    public boolean inc(Polygon polygon) {
        Vector prev = this.position();
        for (Vector vector : this.outline) {
            prev = prev.add(vector);
            if (polygon.ins(prev)) return true;
        }
        prev = polygon.position();
        for (Vector vector : polygon.outline) {
            prev = prev.add(vector);
            if (this.ins(prev)) return true;
        }
        return false;
    }

    public List<Vector> outline() {
        return new ArrayList<>(outline);
    }

    public List<Vector> points() {
        List<Vector> points = new ArrayList<>();
        Vector prev = position();
        for (Vector vector : outline) {
            points.add(prev);
            prev = prev.add(vector);
        }
        return points;
    }

    @Override
    public Polygon mve(Vector position) {
        return new Polygon(position, outline);
    }

    @Override
    public Polygon inc(double a) {
        return inc(position(), a);
    }

    @Override
    public Polygon dec(double a) {
        return dec(position(), a);
    }

    @Override
    public Vector center() {
        Vector center = new Vector(0, 0);
        int i = outline.size();
        for (Vector vector : outline) {
            center = center.add(vector.inc(i));
            --i;
        }
        return center.dec(outline.size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polygon)) return false;
        Polygon polygon = (Polygon) o;
        if (position() != null ? !position().equals(polygon.position()) : polygon.position() != null) return false;
        return outline.equals(polygon.outline);

    }

    @Override
    public int hashCode() {
        int result = position() != null ? position().hashCode() : 0;
        result = 31 * result + outline.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{ " + position() + ", " + outline + "}";
    }

    @Override
    public Figure clone() {
        return new Polygon(position(), outline);
    }
}
