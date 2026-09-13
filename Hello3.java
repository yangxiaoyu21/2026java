public class Hello3 {
    public static void main(String[] args) {
        double finalPi=0.0,finalPi2=0.0;
        int j=0,k=0;
        for(int i=1;i<=11;i=i+2){
            double single=1.0/(double)i;
            single=single*Math.pow(-1,j);
            j++;
            finalPi=finalPi+single;
        }
        for(int i=1;i<=13;i=i+2){
            double single2=1.0/(double)i;
            single2=single2*Math.pow(-1,k);
            k++;
            finalPi2=finalPi2+single2;
        }
        finalPi=4*finalPi;
        finalPi2=4*finalPi2;
        System.out.println(finalPi);
        System.out.println(finalPi2);
    }
}
