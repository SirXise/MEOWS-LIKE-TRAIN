import java.util.Scanner;
/**
 *
 */
public class Histogram {
    public static void main(String[] args) {
        
            Scanner input_data = new Scanner(System.in);
            int no_of_cases = input_data.nextInt();
            input_data.nextLine();
          //ArrayList<Integer> ans = new ArrayList<>();
            int c = 0;
            while(c < no_of_cases){
                int no_of_data = input_data.nextInt();
                int no_of_bins = input_data.nextInt();
                input_data.nextLine();
                
                int[] datapoints = new int[no_of_data];
                
                int b = 0;
                
                while(b < no_of_data){
                    datapoints[b] = input_data.nextInt();
                    b++;
                }
                
                int maximum = datapoints[0];
                int minimum = datapoints[0];
                
                for(int i = 0; i < datapoints.length; i++){
                    
                    if(datapoints[i] > maximum){
                        maximum = datapoints[i];
                    }
                    else if(datapoints[i] < minimum){
                        minimum = datapoints[i]; 
                    }
                }
                
                int cutoffs_value = (maximum - minimum)/no_of_bins;
                int sum = 0;
                int[] interval = new int[no_of_bins + 1];
                int[] count = new int[no_of_bins];
                
                for(int j = 0; j < interval.length; j++){
                    if(j ==0){
                        System.out.println(minimum + "");
                        sum += minimum;
                        interval[j] = minimum;
                        //ans.add(minimum);
                    }
                    else{
                        System.out.println(sum + "");
                        interval[j] = sum;
                        //ans.add(sum);
                    }
                    sum += cutoffs_value;
                }
                //ans.add(00)
                System.out.println();
                for (int k = 0; k < datapoints.length; k++) {
                for (int l = 0; l < interval.length - 1; l++) {
                    if (datapoints[k] >= interval[l] && datapoints[k] < interval[l + 1]) {
                        count[l]++;
                    }
                }
                if (datapoints[k] == interval[interval.length - 1]) {
                    count[interval.length - 2]++;
                }
            }
//            for (int i = 0; i < count.length; i++){
//                ans.add(count[i]);
//            }
//            ans.add(00);

            for (int m = 0; m < count.length; m++) {
                System.out.print(count[m] + " ");
            }
            System.out.println();
            c++;
        }

//        while(!ans.isEmpty()){
//            int num = ans.remove(0);
//            if(num != 0){
//                System.out.print(num + " ");
//            }
//            if(num == 0){
//                System.out.println();
//            }
//        }
            }

}
