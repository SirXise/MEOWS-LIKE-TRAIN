import java.util.*;

public class Payment implements Comparable<Payment>{

    private Long time;
    private String txnId;
    private String tier;
    private Integer rank;

    static PriorityQueue<Payment> meowsPQ = new PriorityQueue<>();

    public Payment(Long time, String txnId, String tier,int rank) {
        if (tier.equalsIgnoreCase("PLATINUM")) {
            this.time = time - 3000;
        }
        else if (tier.equalsIgnoreCase("GOLD")) {
            this.time = time - 2000;
        }
        else if (tier.equalsIgnoreCase("SILVER")) {
            this.time = time - 1000;
        }
        else {
            this.time = time - 0;
        }
        this.rank = rank;
        this.txnId = txnId;
        this.tier = tier;
    }

    public Long getTime() {return time;}

    public String getTxnId() {return txnId;}

    public Integer getRank() {return rank;}

    @Override
    public int compareTo(Payment o) {
        if(this.getTime().compareTo(o.time)==0){
            return this.getRank().compareTo(o.rank);
        }else return this.getTime().compareTo(o.time);
    }

    @Override
    public String toString() {
        return this.getTxnId();
    }

    public static void main(String[] args){
        Long epoch;
        String txnId, tier;

        Long Fepoch = 0L;
        Long time = 0L;
        Long elapsed = 0L;
        Long diff = 0L;
        Long Tepoch = 0L;
        int rank = 0;

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

                    if (tier.equalsIgnoreCase("PLATINUM")) {
                        rank = 3;
                    }
                    else if (tier.equalsIgnoreCase("GOLD")) {
                        rank = 2;
                    }
                    else if (tier.equalsIgnoreCase("SILVER")) {
                        rank = 1;
                    }
                    else {
                        rank = 0;
                    }

                    if(!meowsPQ.isEmpty()){
                        Tepoch = epoch;
                        diff = Tepoch - Fepoch;
                        time = 1000L - diff - elapsed;
                    }else{
                        Fepoch = (long) (Math.round(epoch/1000d)*1000d);
                    }

                    Payment transactionObj = new Payment(time, txnId,tier, rank);
                    meowsPQ.add(transactionObj);

                    if (diff >= 1000) {
                        String result = "";
                        int i = 0;
                        while(!meowsPQ.isEmpty() && i < 100){
                            result += meowsPQ.poll() + " ";
                            i++;
                        }
                        System.out.println(result);
                        diff = 0L;
                        Fepoch = (long) (Math.round(Tepoch/1000d)*1000d);
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
