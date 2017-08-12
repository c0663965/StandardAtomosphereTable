
package KMP;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author khlee
 */
public class StandardAtmosphere {

    static List<List<Double>> ISAT = new ArrayList(); //International Standard Atmosphere Table (국제표준대기모델)

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String filePath = System.getProperty("user.dir") + "/src/data.txt";

        fileRead(filePath);

        for (double height = 0d; height < 84d; height += 0.5) {
            show(interp(height));
        }

    }

    public static void fileRead(String str) throws FileNotFoundException, IOException {
        
        FileReader f = new FileReader(str);
        Scanner scan = new Scanner(f);

        while (scan.hasNextLine()) {

            String strArr[] = scan.nextLine().trim().split(" ");

            Double r[] = new Double[strArr.length];

            int i = 0;

            for (String e : strArr) {
                r[i++] = Double.parseDouble(e);
            }

            ISAT.add(Arrays.asList(r)); //r의 데이터형이 double이 아니라 반드시 Double 이어야 함. 
        }

        f.close();
    }

    public static void show(List<List<Double>> data) {

        data.stream().map((r) -> {
            System.out.println("");
            return r;
        }).forEachOrdered((r) -> {
            r.forEach((e) -> {
                System.out.printf("%25.3f", e);
            });
        });

        System.out.println("");
    }

    public static double[] interp(double height) {  // Linear interpolation
        int index = 0;

        for (int i = 0; i < ISAT.size() - 1; i++) {
            if (ISAT.get(i).get(0) <= height && height < ISAT.get(i + 1).get(0)) {
                index = i;
                break;
            }
        }

        int len = ISAT.get(0).size();

        double y0[] = new double[len];
        double y2[] = new double[len];

        int j = 0;
        int k = 0;

        for (double e : ISAT.get(index)) {
            y0[j++] = e;
        }

        for (double e : ISAT.get(index + 1)) {
            y2[k++] = e;
        }

        double y1[] = new double[len];

        y1[0] = height;

        for (int p = 1; p < len; p++) {
            y1[p] = (y2[p] - y0[p]) / (y2[0] - y0[0]) * (height - y0[0]) + y0[p];
        }

        return y1;
    }

    public static void show(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%12.3f", Math.round(arr[i] * 1000d) / 1000d);
        }

        System.out.println("");
    }
}
