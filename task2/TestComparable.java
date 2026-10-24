package task2;
import java.util.*;
public class TestComparable {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("张三", 2025002, 88.5, 175.0));
        list.add(new Student("李四", 2025001, 95.0, 168.0));
        list.add(new Student("王五", 2025003, 72.0, 180.0));
        list.add(new Student("赵六", 2025004, 91.0, 172.0));

        Collections.sort(list);
        System.out.println("按学号自然排序: " + list);

        list.sort(new ScoreDescComparator());
        System.out.println("按成绩降序: " + list);

        list.sort(new HeightAscComparator());
        System.out.println("按身高升序: " + list);

        list.sort((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()));
        System.out.println("按成绩降序(Lambda): " + list);
    }
}
