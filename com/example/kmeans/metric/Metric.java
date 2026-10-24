package com.example.kmeans.metric;
import com.example.kmeans.entity.Pointocp;
/**
 * @杨晓语
 * Metric
 */
public interface Metric{
    /** 
    *@param p1
    *@param p2
    *@return
    */
    double calculate(Pointocp p1,Pointocp p2);
}
