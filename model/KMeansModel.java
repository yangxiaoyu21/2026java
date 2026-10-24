package model;
public class KMeansModel extends BaseClusterModel{
    public KMeansModel(int k,int maxIterations){
        super(k,maxIterations);
    }
    @Override 
    protected double computeDistance(double[]p1,double[]p2){
        double sum=0;
        for(int i=0;i<p1.length;i++){
            double diff=p1[i]-p2[i];
            sum+=diff*diff;
        }
        return Math.sqrt(sum);
    }
    @Override 
    public void fit(){
        if(dataPoints.isEmpty()){
            System.out.println("未加载数据，无法训练模型!");
            return;
        }
        System.out.println("正在运行K=Means聚类,K="+k+"...");
        centroids=new double[k][dataPoints.get(0).length];
        for(int i=0;i<k;i++){
            centroids[i]=dataPoints.get(i).clone();
        }
        int[]assign=new int[dataPoints.size()];
        for(int iter=0;iter<maxIterations;iter++){
            boolean changed=false;
            for(int i=0;i<dataPoints.size();i++){
                double minD=computeDistance(dataPoints.get(i),centroids[0]);
                int best=0;
                for(int c=1;c<k;c++){
                    double d=computeDistance(dataPoints.get(i),centroids[c]);
                    if(d<minD){
                        minD=d;
                        best=c;
                    }
                }
                if(assign[i]!=best){
                    assign[i]=best;
                    changed=true;
                }
            }
            int[]cnt=new int[k];
            double[][]sum=new double[k][dataPoints.get(0).length];
            for(int i=0;i<dataPoints.size();i++){
                int c=assign[i];
                cnt[c]++;
                for(int d=0;d<sum[c].length;d++){
                    sum[c][d]+=dataPoints.get(i)[d];
                }
            }
            for(int c=0;c<k;c++){
                if(cnt[c]==0)continue;
                for(int d=0;d<sum[c].length;d++){
                    centroids[c][d]=sum[c][d]/cnt[c];
                }
            }
            if(!changed)break;
        }
        System.out.println("K-Means聚类完成!");
    }
}
