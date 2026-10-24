package com.example.kmeans.entity;
import java.util.ArrayList;
import java.util.List;
/**
 * @杨晓语
 */
public class Clusterocp {
    private int id;
    private Pointocp centroid;
    private List<Pointocp>points;
    public Clusterocp(int id,Pointocp initialCentroid){
        this.id=id;
        this.centroid=initialCentroid;
        points=new ArrayList<>();
    }
    public void clearPoints(){
        points.clear();
    }
    public void addPoint(Pointocp p){
        points.add(p);
    }
    public boolean updateCentroid(){
        if(points.isEmpty()){
            return false;
        }
        double sumX=0,sumY=0;
        for(Pointocp p:points){
            sumX+=p.getX();
            sumY+=p.getY();
        }
        double newX=sumX/points.size();
        double newY=sumY/points.size();
        Pointocp newCentroid=new Pointocp(newX,newY);
        if(newCentroid.getX()==centroid.getX()&&newCentroid.getY()==centroid.getY()){
            return false;
        }
        this.centroid=newCentroid;
        return true;
    }
    public int getId(){
        return id;
    }
    public Pointocp getCentroid(){
        return centroid;
    }
    public List<Pointocp>getPoints(){
        return points;
    }
}
