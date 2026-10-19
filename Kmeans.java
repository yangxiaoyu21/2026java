import java.util.ArrayList;
import java.util.List;
public class Kmeans{
    private int k;
    private int maxIterations;
    private double tolerance;
    private List<Cluster> clusters;
    public Kmeans(int k,int maxIterations,double tolerance){
        this.k=k;
        this.maxIterations=maxIterations;
        this.tolerance=tolerance;
        this.clusters=new ArrayList<>();
    }
    public List<Cluster> fit(List<Point> dataPoints){
        if(dataPoints.size()<k){
            throw new IllegalArgumentException(
                "数据点数量不能小于K值!");
        }
        clusters.clear();
        for(int i=0;i<k;i++){
            clusters.add(new Cluster(i,dataPoints.get(i)));
        }
        for(int iter=0;iter<maxIterations;iter++){
            for(Cluster c:clusters){
                c.clearPoints();
            }
            for(Point p:dataPoints){
                Cluster nearest=findNearestCluster(p);
                nearest.addPoint(p);
            }
            double maxShift=0;
            for(Cluster c:clusters){
                maxShift=Math.max(maxShift,c.updateCentroid());
            }
            if(maxShift<tolerance){
                break;
            }
        }
        return clusters;
    }
    private Cluster findNearestCluster(Point p){
        Cluster nearest=clusters.get(0);
        double minDist=p.distanceTo(nearest.getCentroid());
        for(Cluster c:clusters){
            double d=p.distanceTo(c.getCentroid());
            if(d<minDist){
                minDist=d;
                nearest=c;
            }
        }
        return nearest;
    }
    public List<Cluster>getClusters(){return clusters;}
}