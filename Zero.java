import  java.util.Scanner;

public class Zero {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.print("请输入找零总金额: ");
        double cash=input.nextDouble();
        int[]money={100,50,20,10,5,1};
        for(int i=0;i<money.length;i++){
            int count=(int)(cash/money[i]);
            System.out.println(money[i]+"元:"+count+"张");
            cash=cash-count*money[i];
        }
        System.out.printf("剩余零钱:%.2f元",cash);
        input.close();
    }
}
