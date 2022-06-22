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

            int[] datapoints = new int[no_of_data];
            int b = 0;
            while (b < no_of_data) {
                datapoints[b] = input_data.nextInt();
                b++;
            }

            int maximum = Arrays.stream(datapoints).max().getAsInt();
            int minimum = Arrays.stream(datapoints).min().getAsInt();

            int cutoffs_value = (maximum - minimum) / no_of_bins;
            int[] interval = new int[no_of_bins + 1];
            int[] count = new int[no_of_bins];

            for (int i = 0; i < interval.length; i++) {
                interval[i] = minimum;
                minimum += cutoffs_value;
            };

            count[count.length-1] = 1;
            for (int data : datapoints) {
                for (int k = 0; k < interval.length - 1; k++) {
                    if (data >= interval[k] && data < interval[k + 1]) {
                        count[k]++;
                    }
                }
            }

            System.out.println(Arrays.toString(interval).replace("[", "").replace("]", "").replace(",", ""));
            System.out.println(Arrays.toString(count).replace("[", "").replace("]", "").replace(",", ""));
            c++;
        }
    }

}
