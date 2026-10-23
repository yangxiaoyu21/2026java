public class Newpoint{
    private double x;
    private double y;
    private String id;
    public Newpoint(double x,double y){
        this(x,y,"");
    }
    public Newpoint(double x,double y,String id){
        this.x=x;
        this.y=y;
        this.id=id;
    }
    public double getX(){return x;}
    public double getY(){return y;}
    public String getId(){return id;}
    @Override 
    public String toString(){
        return String.format("%s(%.2f,%.2f)",
            id.isEmpty()?"Newpoint":id,x,y);
    }
}