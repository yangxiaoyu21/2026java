package com.example.kmeans.core;
import com.example.kmeans.entity.Cluster;
import com.example.kmeans.entity.Point;
import com.example.kmeans.metric.DistanceMetric;
import java.util.ArrayList;
import java.util.List;
/**
 * @杨晓语
 */
public class KMeans {
    private final int k;
    private final int maxIterations;
    private final DistanceMetric distanceMetric; 
    public KMeans(int k, int maxIterations, DistanceMetric distanceMetric) {
        if(distanceMetric == null) {
            throw new IllegalArgumentException("距离度量策略不能为 null !");
        }
        this.k = k;
        this.maxIterations = maxIterations;
        this.distanceMetric = distanceMetric;
    }
    public List<Cluster> fit(List<Point> points){
        if(points == null || points.size() < k){
            throw new IllegalArgumentException("数据点数量必须大于等于 K !");
        }
        List<Cluster> clusters = new ArrayList<>();
        for(int i = 0; i < k; i++){
            clusters.add(new Cluster(i, points.get(i)));
        }
        boolean changed = true;
        int iter = 0;
        while (changed && iter < maxIterations){
            for(Cluster c : clusters){
                c.clearPoints();
            }
            for(Point p : points){
                Cluster nearest = findNearestCluster(p, clusters);
                nearest.addPoint(p);
            }
            changed = false;
            for(Cluster c : clusters){
                if(c.updateCentroid()){
                    changed = true;
                }
            }
            iter++;
        }
        return clusters;
    }
    private Cluster findNearestCluster(Point p, List<Cluster> clusters){
        Cluster nearest = clusters.get(0);
        double minDist = distanceMetric.calculate(p, nearest.getCentroid());
        for(int i = 1; i < clusters.size(); i++){
            Cluster c = clusters.get(i);
            double dist = distanceMetric.calculate(p, c.getCentroid());
            if(dist < minDist){
                minDist = dist;
                nearest = c;
            }
        }
        return nearest;
    }
}

