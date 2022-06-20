import java.util.*;

public class Payment implements Comparable<Payment>{

    private Long time;
    private String txnId;
    private Integer rank;

    static PriorityQueue<Payment> meowsPQ = new PriorityQueue<>();

    public Payment(Long time, String txnId,int rank) {

        this.rank = rank;
        this.txnId = txnId;
        this.time = time;
    }

    @Override
    public int compareTo(Payment o) {
        if(this.time.compareTo(o.time)==0){
            return this.rank.compareTo(o.rank);
        }else return this.time.compareTo(o.time);
    }

    @Override
    public String toString() {
        return this.txnId;
    }

    public static void main(String[] args){
        Long epoch;
        String txnId, tier;

        Long Fepoch = 0L;
        Long elapsed = 0L;
        Long Tepoch;
        int rank;
        Long time;
        Long diff;

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            try{
                String data = in.nextLine();

                if (data.equalsIgnoreCase("EXIT")) {
                    break;
                } else if (data.equalsIgnoreCase("REBOOT")) {
                    meowsPQ.clear();
                } else{

                    String[] input = data.split("\\s",3);
                    epoch = Long.valueOf(input[0]);
                    txnId = input[1];
                    tier = input[2];

                    if(meowsPQ.isEmpty()){
                        Fepoch = (long) (Math.round(epoch/1000d)*1000d);
                        if (epoch<Fepoch){
                            Fepoch -= 1000L;
                        }
                    }

                    Tepoch = epoch;
                    diff = Tepoch - Fepoch;
                    time = elapsed - (1000L - diff);

                    if (tier.equalsIgnoreCase("PLATINUM")) {
                        time = time - 3000L;
                        rank = 3;
                    }
                    else if (tier.equalsIgnoreCase("GOLD")) {
                        time = time - 2000L;
                        rank = 2;
                    }
                    else if (tier.equalsIgnoreCase("SILVER")) {
                        time = time - 1000L;
                        rank = 1;
                    }
                    else {
                        time = time - 0L;
                        rank = 0;
                    }

                    Payment transactionObj = new Payment(time, txnId, rank);
                    meowsPQ.add(transactionObj);

                    if (diff >= 1000) {
                        String result = "";
                        int i = 0;
                        while(!meowsPQ.isEmpty() && i < 100){
                            result += meowsPQ.poll() + " ";
                            i++;
                        }
                        System.out.println(result);
                        Fepoch = (long) (Math.round(Tepoch/1000d)*1000d);
                        if (Tepoch<Fepoch){
                            Fepoch -= 1000L;
                        }
                        elapsed += 1000L;
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
