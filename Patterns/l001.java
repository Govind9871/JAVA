public class l001{
    public static void main(String[] args){
        solve();
    }

    public static void pattern1(int n ){
        if( n % 2 == 0 ) return;

        int a = n / 2;
        int b = n / 2;
        for( int i = 0; i < n; i++ ){
            if( i < n/2 ){
                for( int j = 0; j < n; j++ ){
                    if( j == 0 || j == n - 1 ){
                        System.out.print("*" + "  ");
                    }else{
                        System.out.print("   ");
                    }
                }
            }else {
                for( int k = 0; k < n; k++ ){
                    if( k == 0 || k == n - 1 || k == a || k == b ){
                        System.out.print("*" + "  ");
                    }else {
                        System.out.print("   ");
                    }
                    
                }
                a--;
                b++;
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void pattern1Best( int n ){

        for(int i = 1; i<=n; i++ ){
            for(int j = 1; j<=n ; j++ ){
                if( j==1 || j==n){
                    System.out.print( "*\t");
                } else if( i > n/2 && ( i == j || i + j == n + 1) ){
                    System.out.print( "*\t");
                }else System.out.print("\t");
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void numberPattern1( int n ){

        for(int i = 1; i <=n ; i++ ){
            for( int j = 1; j <= i; j++ ){
                System.out.print( j + "\t") ;
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void numberPattern1Reverse( int n ){

        for(int i = n; i >=1 ; i-- ){
            for( int j = 1; j <= i; j++ ){
                System.out.print( j + "\t") ;
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void numberPattern2( int n ){

        int a = 1;
        for(int i = 1; i <= n * 2 - 1 ; i++ ){
            if( i <= n ){
                for( int j = 1; j <= i; j++ ){
                    System.out.print( j + "\t");
                }
            }else {
                for( int j = 1; j <= n - a; j++ ){
                    System.out.print( j + "\t");
                }
                a++;
            }
            
            System.out.println();
            System.out.println();
        }
    }

    public static void printTriangle( int n ){

        for(int i = 1; i <= n; i++ ){

            for(int j = n - i; j >= 1; j-- ){
                System.out.print(" ");
            }
            for(int k = 1; k <= i; k++ ){
                System.out.print( "* ");
            }
            System.out.println();
        }
    }

    public static void printReversePyiramid( int n ){

        for( int i = 0; i < n; i++ ){

            for( int j = 0; j < i; j++ ){
                System.out.print(" ");
            }
            for( int x = 1; x <= n - i; x++ ){
                System.out.print( x + " ");
            }
            System.out.println();
        }
    }


    public static void solve(){
        //pattern1( 9 );
        //pattern1Best( 9 );
        //numberPattern1( 5 );
        //numberPattern1Reverse( 5 );
        //numberPattern2( 5 );
        //printTriangle( 7 );
        printReversePyiramid( 6 );
    }
}