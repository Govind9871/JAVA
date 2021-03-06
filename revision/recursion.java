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

    // ********************************************************/

    public static String[] word = { ",;" , "abc" , "def" , "ghi" , "jkl" ,"mno", "pqrs" , "tu" , 
                                    "vwx" , "yz"};

    public static ArrayList<String> getKPC( String str ){

        if( str.length() == 0 ){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        char ch = str.charAt(0);
        String rs = str.substring(1);
        ArrayList<String> res = getKPC( rs );
        ArrayList<String> mres = new ArrayList<>();

        int idx = ch - '0';
        String S = word[idx];

        for(int i=0; i< S.length(); i++ ){
            char a = S.charAt(i);
            for( String val : res ){
                mres.add( a + val );
            }
        }

        return mres;

    }

    public static ArrayList<String> getStairPaths( int n ){
        if( n == 0 ){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        ArrayList<String> mres = new ArrayList<>();
        for( int step = 1; step <= 3 && n - step >= 0; step++ ){
            ArrayList<String> res = getStairPaths( n - step );
            for( String val : res ){
                mres.add( step + val );
            }

        }

        return mres;
    }

    public static ArrayList<String> getMazePaths( int sr, int sc, int dr, int dc){
        if( sr == dr && sc == dc ){
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        } 

        ArrayList<String> mlist = new ArrayList<>();

        for(int move = 1; move <= dc - sc; move++ ){
            ArrayList<String> res = getMazePaths( sr , sc + move, dr, dc );
            for( String val : res ){
                mlist.add( "H" + move + val );
            }
        }
        
        for(int move = 1; move <= dr - sr; move++ ){
            ArrayList<String> res = getMazePaths( sr + move , sc, dr, dc );
            for( String val : res ){
                mlist.add( "V" + move + val );
            }
        }

        for(int move = 1; move <= dc - sc && move <= dr - sr ; move++ ){
            ArrayList<String> res = getMazePaths( sr + move, sc + move, dr, dc );
            for( String val : res ){
                mlist.add( "D" + move + val );
            }
        }

        return mlist;
    }

    public static void printSubsequence( String str , String ans ){
        if( str.length() == 0 ){
            System.out.println( ans );
            return;
        }

        char ch = str.charAt(0);
        String ros = str.substring(1);
        printSubsequence( ros ,  ans  + ch );
        printSubsequence( ros , ans );
    }

    public static void printKPC( String str, String ans ){

        if( str.length() == 0 ) {
            System.out.println( ans );
            return;
        }

        char ch = str.charAt(0);
        int idx = ch - '0';
        String W = word[idx];

        for(int i=0; i<W.length();i++){
            printKPC( str.substring(1) , ans + W.charAt(i) );
        }
    }

    public static void printStairPaths( int n  , String psf ){
        if( n == 0 ){
            System.out.println( psf );
            return;
        }

        for( int step = 1; step<=3 && n - step >= 0 ; step++ ){
            printStairPaths( n - step , psf + step );
        }
    }

    public static void printMaxePaths(int sr, int sc, int dr, int dc, String psf  ){
        if( sr > dr || sc > dc ) return;
        
        if( sr == dr && sc == dc ) {
            System.out.println( psf );
            return;
        }

        printMaxePaths( sr, sc + 1, dr, dc, psf + "h");
        printMaxePaths( sr + 1, sc, dr, dc, psf + "v");
        
    }

    public static void printMazePathsJumps( int sr, int sc, int dr, int dc, String psf ){
        if( sr == dr && sc == dc ){
            System.out.println( psf );
        } 

        for(int move = 1; move <= dc - sc; move++ ){
            printMazePathsJumps( sr , sc + move, dr, dc , psf + "H" + move);
        }
        
        for(int move = 1; move <= dr - sr; move++ ){
            printMazePathsJumps( sr + move, sc, dr, dc , psf + "V" + move);
        }

        for(int move = 1; move <= dc - sc && move <= dr - sr ; move++ ){
            printMazePathsJumps( sr + move, sc + move, dr, dc , psf + "D" + move);
        }
    }

    public static void printPermutations( String str, String ssf ){
        if( str.length() == 0 ){
            System.out.println( ssf );
            return;
        }
        
        for(int i = 0; i < str.length(); i++ ){

            char ch = str.charAt(i);
            String left = str.substring(0,i);
            String right = str.substring(i + 1);

            printPermutations( left + right , ssf + ch ); 
        }

    }

    public static char[] alpha = { '0','a','b','c','d','e','f','g','h','i','j','k','l','m','n',
                                    'o','p','q','r','s','t','u','v','w','x','y','z'};


    public static void printEncodings( String str , String asf ){
        if( str.length() == 0 ){
            System.out.println( asf );
            return;
        }else if( str.length() == 1){
            int num = Integer.parseInt(str);
            if( num == 0 ){
                return;
            }
            char ch = (char)( 'a' + num - 1);
            System.out.println( asf + ch );   
        }else {
            char ch = str.charAt(0);
            String rs = str.substring(1);

            if( ch == '0') return;
            else {
                int num = ch - '0';
                char apla = (char)( 'a' + num - 1);
                printEncodings( rs , asf + apla );
            }

            String ch12 = str.substring(0,2);
            String rs12 = str.substring(2);

            int num12 = Integer.parseInt( ch12 );
            char apla12 = (char)('a' + num12 - 1);

            printEncodings( rs12 , asf + apla12 );
        }
    }

    /* Backtraking */

    public static void floodfill( int[][] maze, int sr, int sc, String asf ){
        if( sr < 0 || sc < 0 || sr == maze.length || sc == maze[0].length || maze[sr][sc] == 1 ){
            return;
        }

        if( sr == maze.length - 1 && sc == maze[0].length - 1 ){
            System.out.println( asf );
            return;
        }

        maze[sr][sc] = 1;
        floodfill( maze, sr - 1, sc, asf + 't' );// top
        floodfill( maze, sr, sc + 1, asf + 'r' );//right
        floodfill( maze, sr + 1, sc, asf + 'd' );//down
        floodfill( maze, sr, sc - 1, asf + 'l' );//left
        maze[sr][sc] = 0;
    }

    public static void printTargetSubsets(int[] arr, int idx, String set, int sos, int tar ){
        if( idx == arr.length || sos == tar ){
            if( sos == tar ){
                System.out.println( set + ".");
            }
            return;
        }
        if( sos == tar ){
            System.out.println( set + ".");
            return;
        }
        if( sos + arr[idx] <= tar ){
            printTargetSubsets( arr, idx + 1, set + arr[idx] + ",", sos + arr[idx], tar );
        }
        printTargetSubsets( arr, idx + 1, set, sos, tar );

    }

    public static int oneDqueenCombination( boolean[] box, int idx, int tnq, int qsf, String ans ){
        if( tnq == qsf ){
            System.out.println( ans );
            return 1;
        }

        int count = 0;
        for( int i = idx; i < box.length; i++ ){
            count+= oneDqueenCombination( box, i + 1, tnq, qsf + 1, ans + "b" + i + "q" + qsf + " ");
        }
        return count;
    }
    
    public static int oneDqueenPermutation( boolean[] box, int idx, int tnq, int qsf, String ans ){
        if( tnq == qsf ){
            System.out.println( ans );
            return 1;
        }

        int count = 0;
        for( int i = idx; i < box.length; i++ ){
            if( !box[i] ){
                box[i] = true;
                count+= oneDqueenPermutation( box, 0, tnq, qsf + 1, ans + "b" + i + "q" + qsf + " ");
                box[i] = false;
            }
        }
        return count;
    }

    public static int twoDqueenCombination( boolean[][] box, int idx, int tnq, int qsf, String ans ){
        if( tnq == qsf ){
            System.out.println( ans );
            return 1;
        }

        int count = 0;
        int n = box.length;
        int m = box[0].length;
        for( int i = idx; i < n * m; i++ ){
            int r = i / m;
            int c = i % m;
            count+= twoDqueenCombination( box, i + 1, tnq, qsf + 1, ans + "(" + r + "," + c + ") ");
        }
        return count;
    }

    public static int twoDqueenPermutation( boolean[][] box, int idx, int tnq, int qsf, String ans ){
        if( tnq == qsf ){
            System.out.println( ans );
            return 1;
        }

        int count = 0;
        
        int n = box.length;
        int m = box[0].length;
        for( int i = idx; i < n * m; i++ ){
            int r = i / m;
            int c = i % m;
            if( !box[r][c] ){
                box[r][c] = true;
                count+= twoDqueenPermutation( box, 0, tnq, qsf + 1, ans + "(" + r + "," + c + ") ");
                box[r][c] = false;
            }
        }
        return count;
    }

    // Nqueen*****************************************************************

    public static boolean isSafeToPlace( boolean[][] box, int r, int c ){
        int[][] dirC = { {-1,-1}, {-1,0}, {-1,1} };

        for(int i = 0; i < dirC.length; i++ ){
            for(int rad = 1; rad <= box.length; rad++ ){
                int x = r + dirC[i][0] * rad ;
                int y = c + dirC[i][1] * rad ;

                if( x >= 0 && y >= 0 && x < box.length && y < box[0].length && box[x][y] )
                return false; 
            }
        }

        return true;
    }

    public static void Nqueen(boolean[][] box, int row, int tnq, String ans ){
        if( tnq == 0 ){
            System.out.println( ans );
            return;
        }

        int m = box[0].length;
        for(int i = 0; i < m; i++ ){
            if( isSafeToPlace(box, row, i) ){
                box[row][i] = true;
                Nqueen( box, row + 1, tnq - 1, ans + "(" + row + "," + i + ") ");
                box[row][i] = false;
            }
        } 
    }

    // knight tour***********************************************************

    public static int knightTour( int[][] board, int r, int c , int move , int[][] Kdir ){
        if( move == board.length * board[0].length ){
            board[r][c] = move;
            displayBoard( board );
            return 1;
        }

        int count = 0;
        board[r][c] = move;
        for(int i = 0; i < Kdir.length; i++ ){
            int x = r + Kdir[i][0];
            int y = c + Kdir[i][1];
            if( x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == 0 ){
                count+= knightTour( board, x, y, move + 1, Kdir);
                board[x][y] = 0;
            }
        }

        return count;
    }

    public static void displayBoard( int[][] board){

        for(int[] row : board ){
            for(int elm : row ){
                System.out.print( elm + "   ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();

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
        //System.out.println( getAllSubsequence( "abc" ) );
        //System.out.println( getKPC( "87" ) );
        //System.out.println( getStairPaths(4) );
        //System.out.println( getMazePaths(0 , 0 , 2, 2 ) );
        //printSubsequence( "abc" , "");
        //printKPC( "786" , "");
        //printStairPaths( 4 , "");
        //printMaxePaths( 0 , 0 , 2, 2, "");
        //printMazePathsJumps( 0 , 0 , 3, 3, "");
        //printPermutations( "abcd" , "");
        //printEncodings( "12103" , "");
        int[][] maze = {{0, 1, 0},
                        {0, 0, 0},
                        {1, 0, 0}};
        //floodfill( maze , 0, 0, "");
        int[] a = {10 , 20, 30, 40 ,50 };
        //printTargetSubsets( a, 0, "", 0, 60 );
        boolean[][] box = new boolean[4][4];
        int tnq = 4;
        //System.out.println( oneDqueenCombination(box , 0, tnq , 0, "") );        
        //System.out.println( oneDqueenPermutation(box , 0, tnq , 0, "") );
        //System.out.println( twoDqueenCombination(box , 0, tnq , 0, "") );
        //System.out.println( twoDqueenPermutation(box , 0, tnq , 0, "") );
        //Nqueen( box, 0, tnq, "");
        int[][] Kdir = { {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1} };
        System.out.println( knightTour(new int[5][5], 2, 0, 1 , Kdir ) );
    }
}