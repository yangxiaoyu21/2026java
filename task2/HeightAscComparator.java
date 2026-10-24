package task2;
import java.util.Comparator;
public class HeightAscComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s1.getHeight(), s2.getHeight());
    }
}

