
import java.util.ArrayList;
import java.util.List;
public class Cluster{
    private int id;
    private Point centroid;
    private List<Point>points;
    public Cluster(int id,Point initialCentroid){
        this.id=id;
        this.centroid=new Point(
            initialCentroid.getX(),
            initialCentroid.getY());
        this.points=new ArrayList<>();
    }
    public void addPoint(Point p){
        this.points.add(p);
    }
    public void clearPoints(){
        this.points.clear();
    }
    public double updateCentroid(){
        if(points.isEmpty()){
            return 0.0;
        }
        double sumX=0;
        double sumY=0;
        for(Point p:points){
            sumX+=p.getX();
            sumY+=p.getY();
        }
        Point newCentroid=new Point(
            sumX/points.size(),
            sumY/points.size());
        double shiftDistance=this.centroid.distanceTo(newCentroid);
        this.centroid=newCentroid;
        return shiftDistance;
    }
    public int getId(){return id;}
    public Point getCentroid(){return centroid;}
    public List<Point> getPoints(){return points;}
}