package ir.albino.client.utils;

import lombok.AllArgsConstructor;
import lombok.With;

@AllArgsConstructor
public class BoundingBox {
    public double x1;
    public double x2;
    public double y1;
    public double y2;
    @With
    public double z1;
    @With
    public double z2;

    public boolean contains(double x, double y, double z) {
        return checkX(x) && checkY(y) && checkZ(z);
    }

    private boolean checkX(double x) {
        double max = Math.max(x1, x2);
        double min = Math.min(x1, x2);
        return x <= max && x >= min;
    }

    private boolean checkY(double y) {
        double max = Math.max(y1, y2);
        double min = Math.min(y1, y2);
        return y <= max && y >= min;
    }

    private boolean checkZ(double z) {
        double max = Math.max(z1, z2);
        double min = Math.min(z1, z2);
        return z <= max && z >= min;
    }

    public boolean contains(double x, double y) {
        return this.contains(x, y, 0);
    }

}
