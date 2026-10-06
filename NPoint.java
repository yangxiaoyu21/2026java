import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NPoint {
         public static double distance(double[] p1,double[] p2) {
            double sum=0.0;
            for(int d=0;d<p1.length;d++){
                double diff=p1[d]-p2[d];
                sum+=diff*diff;
            }
            return Math.sqrt(sum);
         }
         public static double[][] initializeCentroids(double[][] points,int k){
            int dim=points[0].length;
            double[][] centroids = new double[k][dim];
            for(int i=0;i<k;i++){
                for(int j=0;j<dim;j++){
                    centroids[i][j]=points[i][j];
                }
            }
            return centroids;
         }
        public static int[] assignClusters(double[][] points,double[][] centroids){
            int[] assignments=new int[points.length];
            for(int i=0;i<points.length;i++){
                int minIndex=0;
                double minDist=distance(points[i],centroids[0]);
                for(int j=1;j<centroids.length;j++){
                    double dist=distance(points[i],centroids[j]);
                    if(dist<minDist){
                        minDist=dist;
                        minIndex=j;
                    }
                }
                assignments[i]=minIndex;
            }
            return assignments;
        }
            public static double[][] updateCentroids(double[][] points,int[] assignments,int k){
                int dim=points[0].length;
                double[][] newCentroids=new double[k][dim];
                int[] counts=new int[k];
                for(int i=0;i<points.length;i++){
                    int clusterIdx=assignments[i];
                    for(int d=0;d<dim;d++){
                        newCentroids[clusterIdx][d]+=points[i][d];
                    }
                    counts[clusterIdx]++;
            }
            for(int c=0;c<k;c++){
                if(counts[c]>0){
                    for(int d=0;d<dim;d++){
                        newCentroids[c][d]/=counts[c];
                    }
                }
            }
            return newCentroids;
        }
            public static boolean hasConverged(double[][] oldCentroids,double[][] newCentroids,double tolerance){
                for(int i=0;i<oldCentroids.length;i++){
                    if(distance(oldCentroids[i],newCentroids[i])>tolerance){
                        return false;
                    }
                }
                return true;
            }
            public static double[][] readPointsFromFile(String filename)throws FileNotFoundException{
                File file=new File(filename);
                Scanner scanner=new Scanner(file);
                List<double[]>list=new ArrayList<>();
                while(scanner.hasNextLine()){
                    String line=scanner.nextLine().trim();
                    if(line.isEmpty()) continue;
                    String[] parts=line.split("\\s+");
                    double[] point=new double[parts.length];
                    for(int i=0;i<parts.length;i++){
                        point[i]=Double.parseDouble(parts[i]);
                    }
                    list.add(point);
                }
                scanner.close();
                return list.toArray(new double[0][]);
            }
            public static void writeResultsToFile(String filename,double[][] points,int[] assignments,double[][] centroids)throws FileNotFoundException{
                PrintWriter writer=new PrintWriter(filename);
                int k=centroids.length;
                int dim=points[0].length;
                writer.println("==k-means聚类分析结果==");
                writer.println("数据点总数: " + points.length + " | 聚类簇数 K = " + k + "\n");
                for (int i = 0; i < k; i++) {
                    writer.printf("【簇 %d】质心位置: ", i);
                    for (int d = 0; d < dim; d++) {
                        writer.printf("%.2f ", centroids[i][d]);
                    }
                    writer.println("\n 包含的点:");
                    int count = 0;
                    for (int j = 0; j < points.length; j++) {
                        if (assignments[j] == i) {
                            writer.printf(" 点%d: (", j);
                            for (int d = 0; d < dim; d++) {
                                writer.printf("%.2f", points[j][d]);
                                if (d != dim - 1) {
                                    writer.print(", ");
                                }
                            }
                            writer.println(")");
                            count++;
                        }
                    }
                    writer.println(" 簇大小: " + count + " 个点\n");
                }
                writer.close();
            }
            public class KMeansTeachingDemo {
                public static void main(String[] args) {
                    String inputFile  = "input_points.txt";
                    String outputFile = "cluster_results.txt";
                    int k=2;
                    try{                                   
                    double[][] points = readPointsFromFile(inputFile);
                    System.out.println("成功读取 " + points.length + " 个数据点。");
                    double[][] centroids = initializeCentroids(points, k);
                    int[] assignments = new int[points.length];
                    boolean converged = false;
                    int iter = 0, maxIter = 100;
                    while (!converged && iter < maxIter) {
                        assignments = assignClusters(points, centroids);           
                        double[][] newCentroids = updateCentroids(points, assignments, k);
                        converged = hasConverged(centroids, newCentroids, 1e-4);   
                        centroids = newCentroids;                                  
                        iter++;
                    }
                    System.out.println("K-Means 算法在第 " + iter + " 次迭代后收敛。");
                    writeResultsToFile(outputFile, points, assignments, centroids);
                    System.out.println("结果已写入：" + outputFile);
                } catch (FileNotFoundException e){
                    System.err.println("文件错误："+e.getMessage());
                }
            }
        }
}
