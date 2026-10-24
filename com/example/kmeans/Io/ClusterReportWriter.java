package com.example.kmeans.Io;
import com.example.kmeans.entity.Clusterocp;
import java.io.PrintWriter;
import java.util.List;
public class ClusterReportWriter {
    public static void writeReport(List<Clusterocp>clusters,String outputPath)throws Exception{
        PrintWriter pw=new PrintWriter(outputPath);
        for(Clusterocp c:clusters){
            pw.printf("簇%d 质心:%s%n",c.getId(),c.getCentroid());
            for(var p:c.getPoints()){
                pw.println("  ->"+p);
            }
        }
    }
}
