import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Main {
    public static void main(String args[]) throws IOException {
        int n = (int) 2.5;
        //int maxElement = 0;
        int[] inputSizes = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};
        double[][] randomDataResults = new double[3][10];
        double[][] sortedDataResults = new double[3][10];
        double[][] reversedDataResults = new double[3][10];
        double[][] dataResultsOfSearchClass = new double[3][10];

        String file_path = args[0];
        BufferedReader thereader = null;
        try{
            thereader = new BufferedReader(new FileReader(file_path));
            thereader.readLine(); // başlığı atlıyorum.
            String line;
            //  500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, and
            //250000
            int[] flowdurations500 = new int[500];
            int[] flowdurations1000 = new int[1000];
            int[] flowdurations2000 = new int[2000];
            int[] flowdurations4000 = new int[4000];
            int[] flowdurations8000 = new int[8000];
            int[] flowdurations16000 = new int[16000];
            int[] flowdurations32000 = new int[32000];
            int[] flowdurations64000 = new int[64000];
            int[] flowdurations128000 = new int[128000];
            int[] flowdurations250000 = new int[250000];
            //System.out.println(thereader.readLine());
            int counter = 0;
            while((line = thereader.readLine()) != null){

                String[] data = line.split(",");
                int flowDuration = Integer.parseInt(data[6]);
                if(counter<500){flowdurations500[counter] = flowDuration;}
                if(counter<1000){flowdurations1000[counter] = flowDuration;}
                if(counter<2000){flowdurations2000[counter] = flowDuration;}
                if(counter<4000){flowdurations4000[counter] = flowDuration;}
                if(counter<8000){flowdurations8000[counter] = flowDuration;}
                if(counter<16000){flowdurations16000[counter] = flowDuration;}
                if(counter<32000){flowdurations32000[counter] = flowDuration;}
                if(counter<64000){flowdurations64000[counter] = flowDuration;}
                if(counter<128000){flowdurations128000[counter] = flowDuration;}
                if(counter<250000){flowdurations250000[counter] = flowDuration;}
                counter++;

               // System.out.println(flowDuration);
            }
            ArrayList<int[]> flowDurationLists = new ArrayList<>();
            flowDurationLists.add(flowdurations500);flowDurationLists.add(flowdurations1000);flowDurationLists.add(flowdurations2000);flowDurationLists.add(flowdurations4000);flowDurationLists.add(flowdurations8000);flowDurationLists.add(flowdurations16000);flowDurationLists.add(flowdurations32000);flowDurationLists.add(flowdurations64000);flowDurationLists.add(flowdurations128000);flowDurationLists.add(flowdurations250000);

            for(int i = 0; i < 10;i++){
                randomDataResults[0][i] =measureInsertionSortTime(flowDurationLists.get(i));
                randomDataResults[1][i] =measureMergeSortTime(flowDurationLists.get(i));
                randomDataResults[2][i] =measureCountingSortTime(flowDurationLists.get(i));
                int[] sortedData = Arrays.copyOf(flowDurationLists.get(i), flowDurationLists.get(i).length);
                Arrays.sort(sortedData);
                sortedDataResults[0][i] = measureInsertionSortTime(sortedData);
                sortedDataResults[1][i] = measureMergeSortTime(sortedData);
                sortedDataResults[2][i] = measureCountingSortTime(sortedData);
                int[] reversedData = reversed(sortedData);
                reversedDataResults[0][i] = measureInsertionSortTime(reversedData);
                reversedDataResults[1][i] = measureMergeSortTime(reversedData);
                reversedDataResults[2][i] = measureCountingSortTime(reversedData);
               dataResultsOfSearchClass[0][i] = measureLinearSearchTime(flowDurationLists.get(i));
               dataResultsOfSearchClass[1][i] = measureLinearSearchTime(sortedData);
                 dataResultsOfSearchClass[2][i] = measureBinarySearchTime(sortedData);
            }
            System.out.println("randomDataResults:");
            printArray(randomDataResults);

            System.out.println("\nsortedDataResults:");
            printArray(sortedDataResults);

            System.out.println("\nreversedDataResults:");
            printArray(reversedDataResults);

            System.out.println("\ndataResultsOfSearchClass:");
            printArray(dataResultsOfSearchClass);
           // System.out.println(randomDataResults[0][9]);
           // showAndSaveChart("Random Data Performance", inputSizes, randomDataResults,true);
            //showAndSaveChart("Merge Sort Performance", inputSizes, sortedDataResults,true);
           // showAndSaveChart("Sorted Data Performance", inputSizes, sortedDataResults,true);
            showAndSaveChart("Search Performance", inputSizes, dataResultsOfSearchClass,false);
           //showAndSaveChart("Reversed Data Performance", inputSizes, reversedDataResults,true);

        }

        catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(n);

    }
    public static void printArray(double[][] array) {
        for (double[] row : array) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis, boolean controller) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        if(controller){

            chart.addSeries("Insertion Sort", doubleX, yAxis[0]);
             chart.addSeries("Merge Sort", doubleX, yAxis[1]);
             chart.addSeries("Counting Sort", doubleX, yAxis[2]);}
        else{
            chart.setYAxisTitle("Time in Nanoseconds");
        chart.addSeries("Linear Search for Random Data", doubleX, yAxis[0]);
        chart.addSeries("Linear Search for Sorted Data", doubleX, yAxis[1]);
        chart.addSeries("Binary Search for Sorted Data", doubleX, yAxis[2]);}
       // chart.addSeries("Insertion Sort", doubleX, yAxis[0]);
       // chart.addSeries("Merge Sort", doubleX, yAxis[1]);
       // chart.addSeries("Counting Sort", doubleX, yAxis[2]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
    public static double measureLinearSearchTime(int[] data){
        Random randomGenerator = new Random();
        double time = 0;
        for(int i = 0; i<1000;i++){
            int randomIndex = randomGenerator.nextInt(data.length);
            int randomData = data[randomIndex];
            long startTime = System.nanoTime();
           searchclass.linearsearch(data,randomData);
            long endTime = System.nanoTime();
            time += (endTime - startTime) ;
        }
        return time/1000;

    }
    public static double measureBinarySearchTime(int[] data){
        Random randomGenerator = new Random();
        double time = 0;
        for(int i = 0; i<1000;i++){
            int randomData = randomGenerator.nextInt(data.length);
            long startTime = System.nanoTime();
            searchclass.binarysearch(data,randomData);
            long endTime = System.nanoTime();
            time += (endTime - startTime) ;
        }
        return time/1000;

    }
    public static double measureInsertionSortTime(int[] data) {
        double time = 0;
        for(int i = 0; i<10;i++){
            long startTime = System.nanoTime();
            sortclass.insertionsort(data);
            long endTime = System.nanoTime();
            time += (endTime - startTime) / 1_000_000.0;}

        return time/10;
    }
    public static double measureMergeSortTime(int[] data){
        double time = 0;
        for(int i = 0; i<10;i++){
            long startTime = System.nanoTime();
            sortclass.mergesort(data);
            long endTime = System.nanoTime();
        time += (endTime - startTime) / 1_000_000.0;}

        return time/10;
    }

    public static double measureCountingSortTime(int[] data){
        double time = 0;
        for(int i = 0; i<10;i++){
            long startTime = System.nanoTime();
            sortclass.countingSort(data,findMax(data));
            long endTime = System.nanoTime();
            time += (endTime - startTime) / 1_000_000.0;}

        return time/10;
    }
    public static int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    public static int[] reversed(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        for (int i = 0; i < array.length / 2; i++) {
            int temp = copy[i];
            copy[i] = copy[array.length - 1 - i];
            copy[array.length - 1 - i] = temp;
        }
        return copy;
    }

}
