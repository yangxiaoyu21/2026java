package task2;
import java.util.Comparator;
public class ScoreDescComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1,Student s2){
    return Double.compare(s2.getScore(),s1.getScore());
    }
}
