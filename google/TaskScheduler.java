package google;

public class TaskScheduler {
    public static void main(String[] args) {
        String[][] givenSchedules = new String[][]{{"10:00","11:00"},{"14:00","16:00"},{"23:00","23:30"}};
        String[][] testSchedules = new String[][]{{"11:00","11:30"},{"12:00","15:00"},{"11:15","13:43"},{"17:00","18:40"}};
        boolean[] minuteSlots = new boolean[2400];

        for (String[] schedule: givenSchedules){
            int[] slot = parseSlot(schedule);
            fillSlots(slot, minuteSlots);
        }

        for (String[] schedule: testSchedules){
            int[] slot = parseSlot(schedule);
            if (canFit(slot, minuteSlots)){
                System.out.println(true);
                fillSlots(slot, minuteSlots);
            }else {
                System.out.println(false);
            }
        }
    }

    private static int[] parseSlot(String[] slot){
        int from = Integer.parseInt(slot[0].replace(":",""));
        int to   = Integer.parseInt(slot[1].replace(":",""));
        return new int[]{from,to};
    }

    private static boolean canFit(int[] incomingSlot, boolean[] occupiedSlots){
        for (int i=incomingSlot[0]; i < incomingSlot[1]; i++){
            if(i%100 > 59 && i%100 <99){continue;}
            if(occupiedSlots[i]){
                return false;
            }
        }
        return true;
    }

    private static void fillSlots(int[] matchedSlot, boolean[] occupiedSlots){
        for (int i=matchedSlot[0]; i < matchedSlot[1]; i++){
            occupiedSlots[i] = true;
        }
    }
}
