import java.util.*;
public class Shuffle{
    //1.Improved slowshuffle
    //-------------------------------------------------------

    public static int[] improvedSlowShuffle(int N) {
        Random rand = new Random();

        int[] shuffled = new int[N];
        boolean[] isNotPresent = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            isNotPresent[i] = true;
        }
        int i = -1;

        while (i < N - 2) {
            int r = rand.nextInt(N) + 1;

            if (isNotPresent[r]) {
                i++;
                shuffled[i] = r;
                isNotPresent[r] = false;
            }
        }
        for (int num = 1; num <= N; num++) {
            if (isNotPresent[num]) {
                shuffled[N - 1] = num;
            }
        }
        return shuffled;
    }

        //2. Shufflebiased
        public static int[] shuffleBiased(int N){
            Random rand = new Random();

            int[] shuffled = new int[N];

            for (int i = 0; i < N; i++) {
                shuffled[i] = i + 1;
            }
            for (int i = 0; i < N; i++){
                int r = rand.nextInt(N);
                int temp = shuffled[i];
                shuffled[i] = shuffled[r];
                shuffled[r] = temp;
            }

            return shuffled;
        }

            //3.Shuffle Unbiased
        public static int[] shuffleUnbiased(int N) {
            Random rand = new Random();

            int[] B = new int[N];

            for (int i = 0; i < N; i++) {
                B[i] = i + 1;
            }
            int b = 0;

            while (b < N) {
                int r = rand.nextInt(N - b) + b;
                int temp = B[b];
                B[b] = B[r];
                B[r] = temp;
                b++;
            }
            return B;

        }  //proving the difference between methods
            public static void proof(String name, int N){
                Map<String, Integer> D = new HashMap<>();

                for (int i = 0; i < 6000; i++){
                    int[] value;

                    if(name.equals("biased")){
                        value = shuffleBiased(N);
                    } else {
                        value = shuffleUnbiased(N);
                    }
                    String key = "";
                    for (int num : value){
                        key += num;
                    }
                    if (!D.containsKey(key)){
                        D.put(key, 1);

                    }else{
                        D.put(key, D.get(key)+1) ;
                    }
                }

                System.out.println(name + "value:");
                for (String key : D.keySet()) {
                    System.out.println(key + D.get(key));

                }
                System.out.println();
            }
            public static void main (String[] args){
        proof("biased", 3);
        proof("unbiased", 3);
    }
}