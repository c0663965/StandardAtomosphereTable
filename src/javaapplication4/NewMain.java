/* Exculsively written by Kihoon, Lee on 2017.08.05
    - ArrayList의 작동방식을 이해하기 위해 ArrayList를 하드코딩으로 흉내내보기 
    - 거의 완벽하게 흉내냈음. double이나 String으로 당근 확장가능
    - add()가 핵심로직으로 prev배열을 인스턴스 변수로 선언하는것이 핵심 */

package javaapplication4;
import java.util.Arrays;

public class NewMain {

    public static void main(String[] args) {

        LIST data = new LIST();
        
        for (int i=1;i<100;i++)
          data.add(i);
        
        data.add(100);
        
        data.show();
        
        System.out.println(data.sum());
        System.out.println(data.avg());
    }
}

class LIST {

    private int size = 0;

    private int[] prev = new int[size];

    LIST(){}
    
    public void add(int i) {  /**** 핵심로직+매우중요 *********/

        int next[] = new int[++size]; // 확장배열

        next[next.length - 1] = i; // 확장배열의 마지막 위치에 신규값을 저장

        System.arraycopy(prev, 0, next, 0, prev.length); //기존배열을 확장배열에 복사해 넣음. 

        this.prev = new int[size];  //기존배열(인스턴스변수)의 크기를 증가시켜 새로 생성시킴 

        System.arraycopy(next, 0, prev, 0, next.length); //확장배열을 기존배열에 다시 복사해 넣음. 

    }

    public void add(int i, int v) {
        int next[] = new int[++size];

        System.arraycopy(prev, 0, next, 0, prev.length);

        for (int p = prev.length - 1; p >= i; p--) {
            next[p + 1] = prev[p];
        }

        next[i] = v;

        this.prev = new int[size];  

        System.arraycopy(next, 0, prev, 0, next.length);

    }

    public void remove(int p) {

        int next[] = new int[--size];

        int k = 0;

        for (int i = 0; i < prev.length; i++) {
            if (i == p) {
                continue;
            }
            
            next[k++] = prev[i];
        }

        this.prev = new int[size]; 

        System.arraycopy(next, 0, prev, 0, next.length);
    }
    
    public int get(int i){
        return prev[i];
    }
    
    public void set(int i, int v){
        prev[i]=v;
    }
    
    public void sort(){
        Arrays.sort(prev);
    }
    
    public int sum(){
        int sum=0;
        for(int e: prev)
            sum+=e;
        return sum;
    }
    
    public double avg(){
        return sum()/(double)prev.length;
    }
    
    public void show(){
        System.out.println();
        for( int e:prev)
            System.out.println(e);
    }
    
    public int size(){
        return prev.length;
    }
}