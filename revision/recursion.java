import java.util.*;
public class recursion{
    public static void main(String[] args){
        solve();
    }
    
   public static void pDecreasing( int n){
        if( n == 0 ) return;

        System.out.println( n );
        pDecreasing( n - 1 );
   }

   public static void pIncreasing( int n ){
       if( n == 0 ) return;

       pIncreasing( n - 1);
       System.out.println( n );
   }

   public static void pID( int n ){
       if( n == 0 ) return;

        System.out.println( n );
        pID( n - 1);
        System.out.println( n );

   }

   public static int FactNumber( int n ){
       if( n == 1 ) return 1;
       int fact = FactNumber(n - 1);
       fact = fact * n;
       return fact;

   }

   public static int PowerLiner( int n , int x ){
       if( n == 1 ) return x;

       int res = PowerLiner( n - 1, x );
       res = res * x;
       
       return res;
   }

   public static int PowerLiner1( int n , int x ){
        if( n == 1 ) return x;

        int res = PowerLiner1( n/2, x ) * PowerLiner1(n/2 , x );
        if( n%2 != 0) res = res * x ;
    
        return res;
    }   
 

    public static void printZigZag( int n){
        if( n == 0 ) return;

        System.out.println( n );
        printZigZag( n - 1);
        System.out.println( n );
        printZigZag( n - 1);
        System.out.println( n );
    }

    public static void TowerOfHanoi( int n , int A, int B, int C){

        if( n == 0 ) return;

        TowerOfHanoi( n - 1 , A , C , B );
        System.out.println( n + "[" + A + " -> " + B + "]" );
        TowerOfHanoi( n - 1 , C , B , A ); 
    } 

    public static void displayArray( int[] arr, int idx ){
        if( idx == arr.length ) return;

        System.out.println( arr[idx] );
        displayArray( arr , idx + 1);
    }

    public static void displayArrayReverse( int[] arr, int idx ){
        if( idx == arr.length ) return;

        displayArrayReverse( arr , idx + 1);
        System.out.println( arr[idx] );
        
    }

    public static int maxOfArray( int[] arr , int idx ){// with index
        if( idx == arr.length ) return Integer.MIN_VALUE;

        int max = maxOfArray( arr, idx + 1);
        if( max < arr[idx] ){
            max = arr[idx];
        } 

        return max;
    }


    public static int maxOfArray1( int[] arr, int n ){

        if(  n == 1) return arr[0];

        int max = maxOfArray1( arr,  n - 1);
        
        return Math.max( max , arr[n - 1] );
    }

    public static int printFirstIndex( int[] arr, int idx, int x ){
        if( idx == arr.length ) return -1;

        int id = printFirstIndex( arr, idx + 1, x );
        if( arr[idx] == x ) id = idx;

        return id;
    }

    public static int printLastIndex( int[] arr , int idx, int x){
        if( idx == arr.length ) return -1;

        int id = printLastIndex( arr, idx + 1, x );
        if( arr[idx] == x && id == -1 ){
            id = idx;
        }
        return id;
    }

    public static int[] allIndices( int[] arr , int idx, int x, int fsf ){
        if( idx == arr.length ){
            int[] base = new int[fsf];
            return base;
        }

        int count = fsf;
        if( arr[idx] == x ) count++;
        int[] res = allIndices( arr, idx + 1, x, count );
        if( arr[idx] == x ){
            res[count - 1] = idx;
        }

        return res;

        //OR

        // if( idx == arr.length ) return new int[fsf];

        // int[] resa;

        // if( arr[idx] == x ){
        //     resa = allIndices( arr, idx + 1, x, fsf + 1);
        //     resa[fsf] = idx;
        // }else {
        //     resa = allIndices( arr, idx + 1, x, fsf );
        // }

        // return resa;
    }

    public static ArrayList<String> getAllSubsequence( String str ){
        if( str.length() == 0 ){
            ArrayList<String> base = new ArrayList<>();
            base.add( " " );
            return base; 
        }

        char ch = str.charAt(0);
        String rs = str.substring(1);
        ArrayList<String> res = getAllSubsequence( rs );
        ArrayList<String> mlist = new ArrayList<String>();

        for( String val : res ){
            mlist.add( val );
        }

        for( String val : res ){
            mlist.add( ch + val );
        }

        return mlist;

    }

    public static void solve(){
        //pIncreasing(5);
        //pID( 5 );
        //System.out.println( FactNumber( 6 ) );
        //System.out.println( PowerLiner1( 5 , 5 ) );
        //printZigZag( 2 );
        //TowerOfHanoi( 3 , 10 , 20 , 30 );
        int[] arr = { 1, 2, 2, 2, 5, 2, 2, 8, 9, 0};
        int n = arr.length;
        //displayArrayReverse( arr , 0 );
        //System.out.println( maxOfArray( arr, 0 ) );
        //System.out.println( maxOfArray1( arr, n ) );
        //System.out.println( printFirstIndex(  arr, 0, 2 ) );
        //System.out.println( printLastIndex( arr, 0 , 2 ) );
        System.out.println( getAllSubsequence( "abc" ) );
    }
}