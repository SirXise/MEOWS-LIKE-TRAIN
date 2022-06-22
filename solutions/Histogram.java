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
            int sum = 0;
            int[] interval = new int[no_of_bins + 1];
            int[] count = new int[no_of_bins];

            String line = "";
            for (int j = 0; j < interval.length; j++) {
                if (j == 0) {
                    sum += minimum;
                    interval[j] = minimum;
                    line += minimum + " ";
                } else {
                    interval[j] = sum;
                    line += sum + " ";
                }
                sum += cutoffs_value;
            }

            line += "\n";

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

            for (int m = 0; m < count.length; m++) {
                line += count[m] + " ";
            }
            System.out.println(line);
            c++;
        }
    }

}
