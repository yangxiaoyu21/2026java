package task2;
public class Student implements Comparable<Student> {
    private String name;
    private int id;
    private double score;
    private double height;
    public Student(String name, int id, double score, double height) {
        this.name = name;
        this.id = id;
        this.score = score;
        this.height = height;
    }
    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public double getScore() {
        return score;
    }
    public double getHeight() {
        return height;
    }
    @Override
    public String toString() {
        return String.format("[%d] %s: 成绩%.1f, 身高%.1fcm", id, name, score, height);
    }
}
