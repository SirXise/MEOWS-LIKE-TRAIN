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

            int i=0;
            while (i < interval.length) {
                interval[i] = minimum;
                minimum += cutoffs_value;
                i++;
            };

            count[count.length-1] = 1;
            for (int data : datapoints) {
                int k =0;
                while (k < interval.length - 1) {
                    if (data >= interval[k] && data < interval[k + 1]) {
                        count[k]++;
                    }
                    k++;
                }
            }

            System.out.println(Arrays.toString(interval).replace("[", "").replace("]", "").replace(",", ""));
            System.out.println(Arrays.toString(count).replace("[", "").replace("]", "").replace(",", ""));
            c++;
        }
    }

}
