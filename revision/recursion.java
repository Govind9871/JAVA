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
    public static void solve(){
        //pIncreasing(5);
        //pID( 5 );
        //System.out.println( FactNumber( 6 ) );
        //System.out.println( PowerLiner1( 5 , 5 ) );
        //printZigZag( 2 );
        TowerOfHanoi( 3 , 10 , 20 , 30 );
    }
}