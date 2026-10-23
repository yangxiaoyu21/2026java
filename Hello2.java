public class Hello2 {
    public static void main(String[] args) {
        System.out.println("a   a^2   a^3");
        for(int a=1;a<=4;a++){
            int a2=a*a;
            int a3=a*a*a;
            System.out.printf("%d   %d   %d\n",a,a2,a3);
        }
    }
}
