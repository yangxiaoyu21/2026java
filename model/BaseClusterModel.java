package model;
import java.util.List;
import java.util.ArrayList;
/**
 * @杨晓语
 * BaseClusterModel
 */
public abstract class BaseClusterModel {
    protected int k;
    protected int maxIterations;
    protected List<double[]>dataPoints;
    protected double[][]centroids;
    public BaseClusterModel(int k,int maxIterations){
        if(k<=0){
            throw new IllegalArgumentException("簇数量k必须大于0");
        }
        this.k=k;
        this.maxIterations=maxIterations;
        this.dataPoints=new ArrayList<>();
    }
    public void loadData(List<double[]>points){
        this.dataPoints=new ArrayList<>(points);
        System.out.println("成功加载"+dataPoints.size()+"个数据点。");
    }
    protected abstract double computeDistance(double[]p1,double[]p2);
    public abstract void fit();
    public double[][]getCentroids(){
        return centroids;
    }
}
