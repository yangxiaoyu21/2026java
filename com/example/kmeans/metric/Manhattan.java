package com.example.kmeans.metric;
import com.example.kmeans.entity.Pointocp;
/**
 * @杨晓语
 */
public class Manhattan implements Metric {
    @Override
    public double calculate(Pointocp p1, Pointocp p2) {
        double dx = Math.abs(p1.getX() - p2.getX());
        double dy = Math.abs(p1.getY() - p2.getY());
        return dx + dy;
    }
}

