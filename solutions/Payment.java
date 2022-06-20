import java.util.*;

public class Payment implements Comparable<Payment>{

    private Long timespent;
    private Integer rank;
    private String txn_id;

    public Payment(Long timespent, int rank, String txn_id) {
        this.timespent = timespent;
        this.rank = rank;
        this.txn_id = txn_id;
    }


    @Override
    public String toString() {
        return (this.txn_id);
    }

    static PriorityQueue<Payment> tran = new PriorityQueue<Payment>();


    @Override
    public int compareTo(Payment o) {
        if(this.timespent.compareTo(o.timespent)==0) {
            return this.rank.compareTo(o.rank);
        }
        return this.timespent.compareTo(o.timespent);
    }

    public static void main(String[] args) {

        int a = 1;
        Long start = Long.valueOf(0);
        Long totalelapsed = Long.valueOf(0);
        Long timespent = Long.valueOf(0);
        Long elapsed = Long.valueOf(0);
        int rank = 0;
        boolean condition = true;

        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            try {

                String data = in.nextLine();

                if (data.equalsIgnoreCase("EXIT")) {
                    break;
                } else if (data.equalsIgnoreCase("REBOOT")) {
                    tran.clear();
                }
                else if (elapsed >= 1000) {
                    condition = true;
                    totalelapsed += Long.valueOf(1000);
                    elapsed = Long.valueOf(0);
                    //only 100 transaction got cleared
                    for (int j = 0; j < 100; j++) {
                        System.out.print( tran.poll() + " ");
                    }
                    System.out.println("");
                }
                else{
                    String[] arr = data.split("\\s",3);

                    //for start of each 1000 milis
                    while (condition) {
                        start = Long.valueOf(arr[0]);
                        double tempstart = Math.round(start / 1000d) * 1000d;
                        start = (long) tempstart;   //to round off the long value
                        condition = false;
                    }

                    Long timenow = Long.valueOf(arr[0]);
                    elapsed = timenow - start;

                    //PriorityQueue according to count time
                    Long temptime = Long.valueOf(1000) - (timenow - start);
                    if(arr[2].equalsIgnoreCase("BRONZE")) {
                        timespent = totalelapsed  -  temptime;
                        rank = 0;
                    }else if(arr[2].equalsIgnoreCase("SILVER")){
                        timespent = totalelapsed  -  temptime - Long.valueOf(1000);
                        rank = 1;
                    }else if(arr[2].equalsIgnoreCase("GOLD")){
                        timespent = totalelapsed  -  temptime - Long.valueOf(2000);
                        rank = 2;
                    }else if(arr[2].equalsIgnoreCase("PLATINUM")){
                        timespent = totalelapsed  -  temptime - Long.valueOf(3000);
                        rank = 3;
                    }
                    tran.add(new Payment(timespent,rank,arr[1]));
                    if (elapsed >= 1000) {
                        condition = true;
                        totalelapsed += Long.valueOf(1000);
                        elapsed = Long.valueOf(0);
                        //only 100 transaction got cleared
                        String result= "";
                        for (int j = 0; j < 100; j++) {
                            result += tran.poll() + " ";
                        }
                        System.out.println(result.trim());
                    }
                }
            } catch (InputMismatchException e) {
                return;
            } catch (NumberFormatException e) {
                return;
            } catch (ArrayIndexOutOfBoundsException e) {
                return;
            }
        }
    }
}
