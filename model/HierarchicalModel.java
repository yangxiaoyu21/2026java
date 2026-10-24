package model;
import java.util.ArrayList;
import java.util.List;
public class HierarchicalModel extends BaseClusterModel {
    public HierarchicalModel(int k,int maxIterations){
        super(k,maxIterations);
    }
    @Override 
    protected double computeDistance(double[]p1,double[]p2){
        double sum=0;
        for(int i=0;i<p1.length;i++){
            sum+=Math.abs(p1[i]-p2[i]);
        }
        return sum;
    }
    @Override 
    public void fit(){
        if(dataPoints.isEmpty()){
            System.out.println("未加载数据,无法训练模型!");
            return;
        }
        System.out.println("正在运行 层次聚类，目标K="+k+"...");
        List<List<double[]>>clusters=new ArrayList<>();
        for(double[]p:dataPoints){
            List<double[]>c=new ArrayList<>();
            c.add(p);
            clusters.add(c);
        }
        while(clusters.size()>k){
            double minDist=Double.MAX_VALUE;
            int a=0,b=1;
            for(int i=0;i<clusters.size();i++){
                for(int j=i+1;j<clusters.size();j++){
                    double d=clusterDistance(clusters.get(i),clusters.get(j));
                    if(d<minDist){
                        minDist=d;
                        a=i;
                        b=j;
                    }
                }
            }
            clusters.get(a).addAll(clusters.remove(b));
        }
        int dim=dataPoints.get(0).length;
        centroids=new double[k][dim];
        for(int c=0;c<clusters.size();c++){
            List<double[]>group=clusters.get(c);
            for(double[]p:group){
                for(int i=0;i<dim;i++){
                    centroids[c][i]+=p[i];
                }
            }
            for(int i=0;i<dim;i++){
                centroids[c][i]/=group.size();
            }
        }
        for(int c=0;c<clusters.size();c++){
            List<double[]>group=clusters.get(c);
            System.out.printf("簇%d 共%d个点,质心:",c,group.size());
            for(int i=0;i<dim;i++){
                System.out.printf("%.2f",centroids[c][i]);
            }
            System.out.println();
        }
        System.out.println("层次聚类完成!");
    }
    private double clusterDistance(List<double[]>c1,List<double[]>c2){
        double min=Double.MAX_VALUE;
        for(double[]p:c1){
            for(double[]q:c2){
                double d=computeDistance(p,q);
                if(d<min)min=d;
            }
        }
        return min;
    }
}

