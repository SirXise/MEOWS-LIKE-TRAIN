import java.util.*;

class Payment implements Comparable<Payment>{

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

    static PriorityQueue<Payment> tran = new PriorityQueue<>();


    @Override
    public int compareTo(Payment o) {
        if(this.timespent.compareTo(o.timespent)==0) {
            return this.rank.compareTo(o.rank);
        }
        return this.timespent.compareTo(o.timespent);
    }

    public static void main(String[] args) {

        Long start = 0L;
        Long totalelapsed = 0L;
        Long timespent = 0L;
        int rank = 0;
        boolean condition = true;

        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            try {

                String data = in.nextLine();
                data = data.trim();

                if (data.equalsIgnoreCase("EXIT")) {
                    break;
                } else if (data.equalsIgnoreCase("REBOOT")) {
                    tran.clear();
                } else{
                    String[] arr = data.split("\\s",3);

                    //for start of each 1000 milis
                    while (condition) {
                        start = Long.valueOf(arr[0]);
                        double tempstart = Math.round(start / 1000d) * 1000d;
                        start = (long) tempstart;   //to round off the long value
                        condition = false;
                    }

                    Long timenow = Long.valueOf(arr[0]);
                    Long elapsed = timenow - start;

                    //PriorityQueue according to count time
                    Long temptime = 1000L - (timenow - start);
                    if(arr[2].equalsIgnoreCase("BRONZE")) {
                        timespent = totalelapsed  -  temptime;
                        rank = 0;
                    }else if(arr[2].equalsIgnoreCase("SILVER")){
                        timespent = totalelapsed  -  temptime - 1000L;
                        rank = 1;
                    }else if(arr[2].equalsIgnoreCase("GOLD")){
                        timespent = totalelapsed  -  temptime - 2000L;
                        rank = 2;
                    }else if(arr[2].equalsIgnoreCase("PLATINUM")){
                        timespent = totalelapsed  -  temptime - 3000L;
                        rank = 3;
                    }
                    tran.add(new Payment(timespent,rank,arr[1]));
                    if (elapsed >= 1000) {
                        condition = true;
                        totalelapsed += 1000L;
                        //only 100 transaction got cleared
                        StringBuilder result= new StringBuilder();
                        int j = 0;
                        while (j < 100 && !tran.isEmpty()) {
                            result.append(tran.poll()).append(" ");
                            j++;
                        }
                        System.out.println(result.toString().trim());
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
