package com.example.kmeans.core;
import com.example.kmeans.entity.Clusterocp;
import com.example.kmeans.entity.Pointocp;
import com.example.kmeans.metric.Metric;
import java.util.ArrayList;
import java.util.List;
/**
 * @杨晓语
 */
public class KMeans {
    private final int k;
    private final int maxIterations;
    private final Metric distanceMetric; 
    public KMeans(int k, int maxIterations, Metric distanceMetric) {
        if(distanceMetric == null) {
            throw new IllegalArgumentException("距离度量策略不能为 null !");
        }
        this.k = k;
        this.maxIterations = maxIterations;
        this.distanceMetric = distanceMetric;
    }
    public List<Clusterocp> fit(List<Pointocp> points){
        if(points == null || points.size() < k){
            throw new IllegalArgumentException("数据点数量必须大于等于 K !");
        }
        List<Clusterocp> clusters = new ArrayList<>();
        for(int i = 0; i < k; i++){
            clusters.add(new Clusterocp(i, points.get(i)));
        }
        boolean changed = true;
        int iter = 0;
        while (changed && iter < maxIterations){
            for(Clusterocp c : clusters){
                c.clearPoints();
            }
            for(Pointocp p : points){
                Clusterocp nearest = findNearestCluster(p, clusters);
                nearest.addPoint(p);
            }
            changed = false;
            for(Clusterocp c : clusters){
                if(c.updateCentroid()){
                    changed = true;
                }
            }
            iter++;
        }
        return clusters;
    }
    private Clusterocp findNearestCluster(Pointocp p, List<Clusterocp> clusters){
        Clusterocp nearest = clusters.get(0);
        double minDist = distanceMetric.calculate(p, nearest.getCentroid());
        for(int i = 1; i < clusters.size(); i++){
            Clusterocp c = clusters.get(i);
            double dist = distanceMetric.calculate(p, c.getCentroid());
            if(dist < minDist){
                minDist = dist;
                nearest = c;
            }
        }
        return nearest;
    }
}

