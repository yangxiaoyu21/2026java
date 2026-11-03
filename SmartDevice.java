public abstract class SmartDevice {
    private String deviceName;
    public SmartDevice(String deviceName){
        this.deviceName=deviceName;
    }
    public String getDeviceName(){
        return deviceName;
    }
    public abstract void turnOn();
    public abstract void turnOff();
}
class SmartLight extends SmartDevice{
    public SmartLight(String name){
        super(name);
    }
    @Override 
    public void turnOn(){
        System.out.println("["+getDeviceName()+"]开启柔和照明");
    }
    @Override 
    public void turnOff(){
        System.out.println("["+getDeviceName()+"关闭灯光");
    }
    public void setBrightness(int level){
        System.out.println("->亮度调整为:"+level+"%");
    }
}
class SmartAC extends SmartDevice{
    public SmartAC(String name){
        super(name);
    }
    @Override 
    public void turnOn(){
        System.out.println("["+getDeviceName()+"]启动制冷模式");
    }
    @Override 
    public void turnOff(){
        System.out.println("["+getDeviceName()+"]关闭空调");
    }
    public void setTemperature(int temp){
        System.out.println("->温度设定为:"+temp+"°C");
    }
}
class SmartHomeHub{
    public void controlDevice(SmartDevice device){
        device.turnOn();
        if(device instanceof SmartLight light){
            light.setBrightness(80);
        }else if(device instanceof SmartAC ac){
            ac.setTemperature(26);
        }
    }
}
class SmartHomeTest{
    public static void main(String[]args){
        SmartHomeHub hub=new SmartHomeHub();
        hub.controlDevice(new SmartLight("客厅主灯"));
        System.out.println();
        hub.controlDevice(new SmartAC("卧室变频空调"));
    }
}
