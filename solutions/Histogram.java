import java.util.*;
/**
 *
 */
public class Histogram {
    public static void main(String[] args) {

        Scanner input_data = new Scanner(System.in);
        int no_of_cases = input_data.nextInt();

        int c = 0;
        while (c < no_of_cases) {
            int no_of_data = input_data.nextInt();
            int no_of_bins = input_data.nextInt();

            Integer[] datapoints = new Integer[no_of_data];
            int b = 0;
            while (b < no_of_data) {
                datapoints[b] = input_data.nextInt();
                b++;
            }

            int maximum = Collections.max(Arrays.asList(datapoints));
            int minimum = Collections.min(Arrays.asList(datapoints));

            int cutoffs_value = (maximum - minimum) / no_of_bins;
            int[] interval = new int[no_of_bins + 1];
            int[] count = new int[no_of_bins];

            String line = "";
            for (int i = 0; i < interval.length; i++) {
                interval[i] = minimum;
                minimum += cutoffs_value;
            }

            for(int cutoff:interval){
                line += cutoff + " ";
            }

            System.out.println(line);

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

            String line1="";
            for (int m = 0; m < count.length; m++) {
                line1 += count[m] + " ";
            }
            System.out.println(line1);
            c++;
        }
    }

}
