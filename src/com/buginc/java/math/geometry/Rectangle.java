package com.buginc.java.math.geometry;

//** ** Created by DeveloperHacker ** **//
//* https://github.com/DeveloperHacker *//

public class Rectangle extends Figure {

    public Rectangle(Vector position, Vector dimension) {
        super(position, dimension);
    }

    public Rectangle(double x, double y, double width, double height) {
        super(new Vector(x, y), new Vector(width, height));
    }

    public Rectangle(int x, int y, int width, int height) {
        super(new Vector(x, y), new Vector(width, height));
    }

    /**
     * @return the difference of the rectangles
     */
    public Rectangle dff(Rectangle rectangle) {
        double top = Math.max(rectangle.y(), y());
        double bottom = Math.min(rectangle.y() + rectangle.height(), y() + height());
        double left = Math.max(rectangle.x(), x());
        double right = Math.min(rectangle.x() + rectangle.width(), x() + width());
        return new Rectangle(left, top, right - left, bottom - top);
    }

    /**
     * @return the union of the rectangles
     */
    public Rectangle cmb(Rectangle rectangle) {
        double top = Math.min(rectangle.y(), y());
        double bottom = Math.max(rectangle.y() + rectangle.height(), y() + height());
        double left = Math.min(rectangle.x(), x());
        double right = Math.max(rectangle.x() + rectangle.width(), x() + width());
        return new Rectangle(left, top, right - left, bottom - top);
    }

    /**
     * @return the distance of the rectangles
     */
    public double dst(Rectangle rectangle) {
        Rectangle rect = this.dff(rectangle);
        Vector dist = new Vector(0, 0);
        if (rect.dimension().x() < 0) dist = dist.add(new Vector(-rect.dimension().x(), 0));
        if (rect.dimension().y() < 0) dist = dist.add(new Vector(0, -rect.dimension().y()));
        return dist.abs();
    }

    /**
     * @return the distance between the rectangle and ellipse
     */
    public double dst(Ellipse ellipse) {
        return ellipse.dst(this);
    }

    /**
     * @return true if the rectangles overlap
     */
    public boolean ict(Rectangle rectangle) {
        return !this.dff(rectangle).imr();
    }

    /**
     * @return true if the rectangle and the ellipse overlap
     */
    public boolean ict(Ellipse ellipse) {
        return this.dst(ellipse) == 0.0;
    }

    /**
     * @return a rectangle entry into imaginary subset
     */
    public boolean imr() {
        return width() <= 0 || height() <= 0;
    }

    @Override
    public Rectangle mve(Vector direct) {
        return new Rectangle(position().add(direct), dimension());
    }

    @Override
    public Rectangle inc(double a) {
        return new Rectangle(position(), dimension().inc(a));
    }

    @Override
    public Rectangle dec(double a) {
        return new Rectangle(position(), dimension().dec(a));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        if (position() != null ? !position().equals(rectangle.position()) : rectangle.position() != null) return false;
        return dimension() != null ? dimension().equals(rectangle.dimension()) : rectangle.dimension() == null;
    }

    @Override
    public int hashCode() {
        int result = position() != null ? position().hashCode() : 0;
        result = 31 * result + (dimension() != null ? dimension().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "(" + x() + "; " + y() + "; " + width() + "; " + height() + ")";
    }

    @Override
    public Figure clone() {
        return new Rectangle(position(), dimension());
    }
}
