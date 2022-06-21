/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package histogram;


import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.lang.*;
/**
 *
 */
public class Histogram {
    public static void main(String[] args) {
        try {
            File file = new File("9.txt");
            Scanner input_data = new Scanner(file);
            String Line1 = input_data.nextLine();
            System.out.println(Line1);
            int numOfBin = Integer.parseInt(Line1.split(" ")[1]);
            String Line2 = input_data.nextLine();
            String[] strArr2 = Line2.split(" ");
            int[] N2 = new int[strArr2.length];
            int c = 0;
            while(c < N2.length){
                N2[c] = Integer.parseInt(strArr2[c]);
                c++;
            }

            int[] newArray = new int[numOfBin + 1];
            int cutoff = intervals(N2, numOfBin, newArray);
            Frequency(N2,numOfBin,newArray);


        } catch (Exception e) {
            System.out.println("Error in input file...");
        }
    }
    public static int max(int[] array){
            int max = 0;
            for(int i=0;i< array.length;i++){
                if(array[i]>max){
                    max = array[i];
                }
            }
            return max;
        }
        public static int min(int[] array){
            int min = array[0];
            for(int i=0;i< array.length; i++){
                if(array[i]<min){
                    min = array[i];
                }
            }
            return min;
        }

        public static int intervals(int[] N2, int numOfBin, int[] newArray){

            int min = min(N2);
            int max = max(N2);
            int cutoff = (max - min) / numOfBin;
            int interval = min;
            for (int i = 0; i <= numOfBin; i++) {
                newArray[i] = interval;
                interval += cutoff;

            }
            for (int inter: newArray) {
                System.out.print(inter + " ");
            }
            System.out.println();
            return cutoff;
        }

        public static void Frequency(int[] N2, int numOfBin, int[] newArray){
            int[] frequency = new int[numOfBin];
            frequency[frequency.length-1] = 1;
            for (int number: N2) {
                for (int i = 0; i < newArray.length-1; i++) {
                    if (number >= newArray[i] && number<newArray[i+1]){
                        frequency[i]++;
                    }
                }
            }
            for (int freq: frequency) {
                System.out.print(freq + " ");
            }
        }
    }

