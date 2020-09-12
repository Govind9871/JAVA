public class coinschange{
    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        int coins[] = { 2, 3, 5, 7 };
        int tar = 10;
        // System.out.println( coinInfinitePermutation( coins , 0, "", tar ) );
        // System.out.println( coinInfiniteCombination( coins, 0, "", tar ) );
         System.out.println( coinSingleCombination( coins, 0, "" , tar ));
        // System.out.println( coinSinglePermutation( coins, 0, "", tar ) );
        System.out.println( coinSingleCombination_Subs( coins, 0, "", tar ) );
    }

    public static int coinInfinitePermutation( int arr[] , int idx , String ans , int tar ) {
        if( tar == 0 ){
            System.out.println( ans );
            return 1;
        }
        int count = 0;
        for( int i = idx; i < arr.length; i++ ){
            if( tar - arr[i] >= 0 ){
                count += coinInfinitePermutation( arr, 0, ans + arr[i] , tar - arr[i] ); 
            }
        }
        return count;
    }

    public static int coinInfiniteCombination( int arr[] , int idx , String ans, int tar  ) {
        if( tar == 0 ){
            System.out.println( ans );
            return 1;
        }
        int count = 0;
        for( int i = idx; i < arr.length; i++ ){
            if( tar - arr[i] >= 0 ){
                count += coinInfiniteCombination( arr, i , ans + arr[i] , tar - arr[i] ); 
            }
        }
        return count;
    }

    public static int coinSinglePermutation( int arr[] , int idx , String ans , int tar
    ) {
        if( tar == 0 ){
            System.out.println( ans );
            return 1;
        }
        int count = 0;
        for( int i = idx; i < arr.length; i++ ){
            if( tar - arr[i] >= 0 && arr[i] > 0 ){
                int temp = arr[i];
                arr[i] = -arr[i];
                count += coinSinglePermutation( arr, 0, ans + temp , tar - temp ); 
                arr[i] = -arr[i];
            }
        }
        return count;
    }

    public static int coinSingleCombination( int arr[] , int idx , String ans , int tar) {
        if( tar == 0 ){
            System.out.println( ans );
            return 1;
        }
        int count = 0;
        for( int i = idx; i < arr.length; i++ ){
            if( tar - arr[i] >= 0 ){
                count += coinSingleCombination( arr, i + 1 , ans + arr[i] , tar - arr[i] ); 
            }
        }
        return count;
    }

    public static int coinSingleCombination_Subs( int[] arr, int idx, String ans, int tar ){
        if( tar == 0 || idx == arr.length){
            if(tar == 0 ){
                System.out.println( ans );
                return 1;
            }
            return 0;
        }

        int count = 0;
        if( tar - arr[idx] >= 0 ){
            count += coinSingleCombination_Subs( arr, idx + 1, ans + arr[idx] , tar - arr[idx] );
            count += coinSingleCombination_Subs( arr, idx + 1, ans , tar );
        }
        return count;
    }
}