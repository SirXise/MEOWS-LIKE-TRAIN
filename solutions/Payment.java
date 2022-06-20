import java.util.*;

class Payment implements Comparable<Payment>{

    private Long time;
    private String txnId;
    private String tier;

    static PriorityQueue<Payment> tran = new PriorityQueue<>();

    public Payment(Long time, String txnId, String tier) {
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

        this.txnId = txnId;
        this.tier = tier;
    }

    public Long getTime() {return time;}

    public String getTxnId() {return txnId;}

    public String getTier() {return tier;}

    @Override
    public int compareTo(Payment o) {
        if(this.getTime().compareTo(o.time)==0){
            return this.getTier().compareTo(o.tier);
        }else return this.getTime().compareTo(o.time);
    }

    @Override
    public String toString() {
        return this.getTxnId();
    }

    public static void main(String[] args) {
        Long epoch;
        String txnId, tier;

        Long Fepoch = 0L;
        Long time = 0L;
        Long elapsed = 0L;
        Long diff = 0L;
        double TFepoch = 0;

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            try{
                String data = in.nextLine();

                if (data.equalsIgnoreCase("EXIT")) {
                    break;
                } else if (data.equalsIgnoreCase("REBOOT")) {
                    tran.clear();
                } else{

                    String[] input = data.split("\\s",3);
                    epoch = Long.valueOf(input[0]);
                    txnId = input[1];
                    tier = input[2];

                    if(!tran.isEmpty() && Fepoch != 0){
                        Long Tepoch = epoch;
                        diff = Tepoch - Fepoch;
                        time = diff - elapsed;
                    }else{
                        Fepoch = epoch;
                        TFepoch = Math.round(Fepoch/1000d)*1000d;
                        Fepoch = (long) TFepoch;
                    }

                    Payment transactionObj = new Payment(time, txnId, tier);
                    tran.add(transactionObj);

                    if (diff >= 1000) {
                        String result = "";
                        int i = 0;
                        while(!tran.isEmpty() && i < 100){
                            result += tran.poll() + " ";
                            i++;
                        }
                        System.out.println(result);
                        diff = 0L;
                        Fepoch = 0L;
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
