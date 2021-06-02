package freecoding;

public class MacWiFiAnimation {
    private static void playUpAndDown(int[] array, int repeat) throws InterruptedException{
        boolean forward = true;
        if(array.length == 0){return;}
        int i=0;
        int count = 0;
        while (true && count++ < repeat){
            Thread.sleep(500);
            System.out.println(array[i]);
            if (array.length == 1) continue;

            if (i == array.length-1){
                forward = false;
            }else if(i == 0){
                forward = true;
            }

            if(forward && i < array.length-1){
                i++;
            }else if (!forward && i > 0){
                i--;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        playUpAndDown(new int[]{1,2,3,4,5}, 3);
    }
}
