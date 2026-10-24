package com.example.kmeans.Io;
import com.example.kmeans.entity.Pointocp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class PointFileReader {
    public static List<Pointocp>readPoints(String path)throws FileNotFoundException{
        List<Pointocp>points=new ArrayList<>();
        Scanner sc=new Scanner(new File(path));
        while(sc.hasNextDouble()){
            double x=sc.nextDouble();
            double y=sc.nextDouble();
            points.add(new Pointocp(x,y));
        }
        sc.close();
        return points;
    }
}
