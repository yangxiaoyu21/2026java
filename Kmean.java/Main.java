import java.io.FileNotFoundException;
import java.util.List;
public class Main{
    public static void main(String[]args){
        String inputFile="input_points.txt";
        String outputFile="cluster_results.txt";
        try{
            PointFileReader reader=new PointFileReader(inputFile);
            List<Point>points=reader.readPoints();
            System.out.println("成功加载数据点:"+points.size()+"个。");
            Kmeans kmeansService=new Kmeans(2,100,1e-4);
            List<Cluster>resultClusters=kmeansService.fit(points);
            System.out.println("k-Means聚类计算完成!");
            ClusterReportWriter writer=new ClusterReportWriter(outputFile);
            writer.writeReport(resultClusters);
            System.out.println("结果已写入:"+outputFile);
        }catch(FileNotFoundException  e){
            System.err.println("文件异常:"+e.getMessage());
        }
    }
}