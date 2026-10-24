package com.example.kmeans;
import com.example.kmeans.entity.Clusterocp;
import com.example.kmeans.entity.Pointocp;
import com.example.kmeans.core.KMeans;
import com.example.kmeans.Io.PointFileReader;
import com.example.kmeans.Io.ClusterReportWriter;
import com.example.kmeans.metric.*;
import java.io.FileNotFoundException;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        String inputPath = "input_points.txt";
        try {
            List<Pointocp> points = PointFileReader.readPoints(inputPath);
            System.out.println("成功读取数据点数量: " + points.size());
            int k = 2;
            int maxIter = 100;
            System.out.println("\n运行策略1: 欧式距离 (Euclidean)");
            Metric euclidean = new EuclideanDistance();
            KMeans kmeansEuclidean = new KMeans(k, maxIter, euclidean);
            List<Clusterocp> result1 = kmeansEuclidean.fit(points);
            printClusters(result1);
            ClusterReportWriter.writeReport(result1,"result_euclidean.txt");

            System.out.println("\n运行策略2: 曼哈顿距离 (Manhattan)");
            Metric manhattan = new Manhattan();
            KMeans kmeansManhattan = new KMeans(k, maxIter, manhattan);
            List<Clusterocp> result2 = kmeansManhattan.fit(points);
            printClusters(result2);
            ClusterReportWriter.writeReport(result2,"result_manhattan.txt");

            System.out.println("\n运行策略3: 切比雪夫距离 (Chebyshev)");
            Metric chebyshev = new Chebyshev();
            KMeans kmeansChebyshev = new KMeans(k, maxIter, chebyshev);
            List<Clusterocp> result3 = kmeansChebyshev.fit(points);
            printClusters(result3);
            ClusterReportWriter.writeReport(result3,"result_chebyshev.txt");

        } catch (FileNotFoundException e) {
            System.err.println("读取文件失败: " + e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void printClusters(List<Clusterocp> clusters){
        for (Clusterocp c : clusters) {
            System.out.printf("簇%d [质心: (%.2f, %.2f)] 包含 %d 个点:%n",
                    c.getId(),
                    c.getCentroid().getX(),
                    c.getCentroid().getY(),
                    c.getPoints().size());
            for (Pointocp p : c.getPoints()) {
                System.out.println("  -> " + p);
            }
        }
    }
}