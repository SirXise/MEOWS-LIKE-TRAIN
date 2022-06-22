import java.util.*;
import java.lang.*;
/**
 *
 */
class Histogram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int NumCases = input.nextInt();
        input.nextLine();
        for (int i = 0; i < NumCases; i++) {
            int numOfDataPoints = input.nextInt();
            int numOfBins = input.nextInt();

            int[] dataPoints = new int[numOfDataPoints];
            for (int j = 0; j < numOfDataPoints; j++) {
                dataPoints[j] = input.nextInt();
            }

            int max = Arrays.stream(dataPoints).max().getAsInt();
            int min = Arrays.stream(dataPoints).min().getAsInt();

            int[] cutoffs = new int[numOfBins + 1];
            int[] binsCount = new int[numOfBins];
            int interval = (max - min) / numOfBins;
            for (int j = 0; j < numOfBins + 1; j++) {
                cutoffs[j] = min + (interval * j);
            }

            dataPointsLoop:
            for (int data : dataPoints) {
                for (int k = 0; k < cutoffs.length - 2; k++) {
                    if (data < cutoffs[k + 1]) {
                        binsCount[k]++;
                        continue dataPointsLoop;
                    }
                }
                if (data <= cutoffs[cutoffs.length - 1]) {
                    binsCount[numOfBins - 1]++;
                }
            }

            System.out.println(Arrays.toString(cutoffs).replace("[", "").replace("]", "").replace(",", ""));
            System.out.println(Arrays.toString(binsCount).replace("[", "").replace("]", "").replace(",", ""));
        }

    }
}
