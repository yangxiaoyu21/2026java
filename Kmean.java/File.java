import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class PointFileReader{
    private String filePath;
    public PointFileReader(String filePath){
        this.filePath=filePath;
    }
    public List<Point>readPoints()throws FileNotFoundException{
        List<Point>points=new ArrayList<>();
        Scanner scanner=new Scanner(new File(filePath));
        int count=0;
        while(scanner.hasNextDouble()){
            double x=scanner.nextDouble();
            double y=scanner.nextDouble();
            points.add(new Point(x,y,"p"+count++));
        }
        scanner.close();
        return points;
    }
}class ClusterReportWriter{
    private String outputPath;
    public ClusterReportWriter(String outputPath){
        this.outputPath=outputPath;
    }
    public void writeReport(List<Cluster>clusters)
           throws FileNotFoundException{
        PrintWriter writer=new PrintWriter(outputPath);
        writer.println("=============================");
        writer.println(" K-Means面向对象聚类分析报表");
        writer.println("==============================");
        for(Cluster cluster:clusters){
            writer.printf("簇#%d|点数:%d%n",
                cluster.getId(),
                cluster.getPoints().size());
            writer.printf("质心:(%.3f,%.3f)%n",
                cluster.getCentroid().getX(),
                cluster.getCentroid().getY());
            for(Point p:cluster.getPoints()){
                writer.println(" "+p);
            }
            writer.println();
        }
        writer.close();
           }
}