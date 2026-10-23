import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
         int a=(int)(Math.random()*10);
         int b=(int)(Math.random()*10);
         int num=(int)(Math.random()*100%4);
         int suanshi=0;
         char sign=' ';
         switch(num){
            case 0:
                sign='+';
                suanshi=a+b;
                break;
            case 1:
                sign='-';
                for(int i=1;;i++){
                    a=(int)(Math.random()*10);
                    b=(int)(Math.random()*10);
                    i=i+1;
                    if(a>=b)
                    break;
                }
                suanshi=a-b;
                break;
            case 2:
                sign='*';
                suanshi=a*b;
                break;
            case 3:
                sign='/';
                for(int i=1;;i++){
                    a=(int)(Math.random()*10);
                    b=(int)(Math.random()*10);
                    i=i+1;
                    if(b!=0 && a%b==0){
                        break;
                    }
                }
                suanshi=a/b;
                break;
                }
                System.out.println(""+a+sign+b+'=');
                Scanner op=new Scanner(System.in);
                for(int i=0;;i++){
                    i+=i;
                    int ans=op.nextInt();
                    if(ans==suanshi){
                        System.out.println("RIGHT!");
                        break;
                    }else{
                        System.out.println("错误了，重新计算");
                    }
                }
         op.close();
    }
}
