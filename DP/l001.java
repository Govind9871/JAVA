import java.util.LinkedList;
public class l001{
    public static void main(String[] args) {
        solve();
    }

    public static void print_1d( int[] arr ){
        for( int i = 0; i < arr.length; i++ ){
            System.out.print( arr[i] + " ");
        }
        System.out.println();
    }
    public static void print_2d( int[][] arr ){
        for( int[] ar : arr ){
            print_1d(ar);
        }
    }

    public static int fibo( int n ){
        if( n <= 1) return n;
        int a = fibo( n - 1);
        int b = fibo( n- 2 );
        return a + b;
    }

    public static int fibo_memo( int n, int[] dp ){
        if( n <= 1) return n;
        if( dp[n] != 0 ) return dp[n];
        int a = fibo( n - 1);
        int b = fibo( n- 2 );
        dp[n] = a + b;
        return dp[n];
    }

    public static int fibo_dp( int n  ){
        int[] dp = new int[n+1];
        for( int i = 0; i <= n ; i++ ){
            if( i <= 1 ){
                dp[i] = i;
                continue;
            }
            int a = dp[i-1];
            int b = dp[i-2];
            dp[i] = a + b;
        }
        return dp[n];
    }

    public static int fibo_opti( int n ){
        int a = 0, b = 1;
        for( int i = 2; i <= n; i++ ){
            int sum = a + b;
            a = b;
            b = sum; 
        }
        return b;
    }

    public static void basic(){
        int n = 5;
        int[] dp = new int[n+1];
        System.out.println( fibo( n ) );// recursion
        System.out.println( fibo_memo( n, dp ) );// memorisation
        System.out.println( fibo_dp( n ) );
        System.out.println( fibo_opti( n ) );
    }

    public static int getAllmazePath( int sr, int sc, int er, int ec ){
        if( sr == er && sc == ec ) return 1;
        int count = 0;
        if( sc + 1 <= ec ) count += getAllmazePath( sr, sc + 1, er, ec );
        if( sr + 1 <= er && sc + 1 <= ec ) count += getAllmazePath( sr + 1, sc + 1, er, ec );
        if( sr + 1 <= er ) count += getAllmazePath( sr + 1, sc, er, ec );
        return count;
    }

    public static int getAllmazePath_memo( int sr, int sc, int er, int ec , int[][] dp ){
        if( sr == er && sc == ec ) return dp[sr][sc] = 1;
        if( dp[sr][sc] != 0 ) return dp[sr][sc];
        int count = 0;
        if( sc + 1 <= ec ) count += getAllmazePath_memo( sr, sc + 1, er, ec , dp);
        if( sr + 1 <= er && sc + 1 <= ec ) count += getAllmazePath_memo( sr + 1, sc + 1, er, ec , dp);
        if( sr + 1 <= er ) count += getAllmazePath_memo( sr + 1, sc, er, ec , dp);
        dp[sr][sc] = count;
        return count;
    }

    public static int getAllmazePath_dp( int sr, int sc, int er, int ec ){
        int[][] dp = new int[er + 1][ec + 1];

        for( int i = er; i >= sr; i-- ){
            for( int j = ec; j>= sc; j-- ){
                if( i == er && j == ec ) dp[i][j] = 1;
                else if( i == er ) dp[i][j] = dp[i][j+1];
                else if( j == ec ) dp[i][j] = dp[i+1][j];
                else dp[i][j] = dp[i+1][j] + dp[i+1][j+1] + dp[i][j+1];
            }
        }
        print_2d( dp );
        return dp[0][0];
    }

    public static void AllmazePath(){
        int n = 4;
        int m = 4;
        int[][] dp = new int[n][m];
        System.out.println( getAllmazePath(0, 0, n -1, m -1) );// recursion
        System.out.println( getAllmazePath_memo( 0, 0, n-1, m-1, dp) );
        print_2d( dp ); 
        System.out.println( getAllmazePath_dp( 0, 0, n-1, m-1) );
    }

    public static int getAllBoardPath( int si, int ei ){
        if( si == ei ) return 1; 
        int count = 0;
        for( int i = 1; i <= 6 ; i++ ){
            if( i + si <= ei ) count += getAllBoardPath( si + i, ei );
            else break;
        }
        return count;
    }

    public static int getAllBoardPath_memo( int si, int ei , int[] dp ){
        if( si == ei ) return dp[si] = 1; 
        if( dp[si] != 0 ) return dp[si];
        int count = 0;
        for( int i = 1; i <= 6 && si + i <= ei ; i++ ){
            count += getAllBoardPath_memo( si + i, ei , dp );
        }
        return dp[si] = count;
    }

    public static int getAllBoardPath_dp( int si , int ei ){
        LinkedList<Integer> li = new LinkedList<>();// optimize method
        for( int i = ei ; i >= 0 ; i--){
            if( i == ei  || i == ei - 1){
                li.addFirst( 1 );
                continue;
            }
            if( li.size() < 7 ){
                int val = li.getFirst();
                li.addFirst( val * 2);
            }else {
                int val1 = li.getFirst();
                int val2 = li.getLast();
                li.addFirst( (val1 * 2) - val2 );
                li.removeLast();
            }
        }
        return li.getFirst(); 
    }

    public static void AllBoardPath(){
        int n = 10;
        int[] dp = new int[n+1];
        System.out.println( getAllBoardPath( 0, 10 ) );
        System.out.println( getAllBoardPath_memo( 0, 10 , dp ) );
        print_1d( dp );
        System.out.println( getAllBoardPath_dp(0, 10 ) );
    }

    public static void solve(){
        //basic();
        //AllmazePath();
        AllBoardPath();
    }
}