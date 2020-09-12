public class questions{
    public static void main(String[] args) {
        solve();
    }

    public static int maxGoldMine_memo(int[][] grid, int sr, int sc, int[][] dp ,int[][] dir ){
        if( sc == grid[0].length - 1 ){
            return dp[sr][sc] = grid[sr][sc];
        }
        //if( dp[sr][sc] != 0 ) return dp[sr][sc];
        for( int d = 0; d < 3; d++ ){
            int r = sr + dir[d][0];
            int c = sc + dir[d][1];

            if( r >= 0 && c >=0 && r < grid.length && c < grid[0].length ){
                dp[sr][sc] = Math.max( dp[sr][sc] , maxGoldMine_memo( grid, r, c, dp, dir ) );
            }
        }
        dp[sr][sc] += grid[sr][sc];
        return dp[sr][sc];
    }

    public static void maxGoldMine(){
        int n = 4;
        int[][] dp = new int[n][n];
        int[][] grid = {{10, 33, 13, 15},
                        {22, 21, 04, 1},
                        {5, 0, 2, 3},
                        {0, 6, 14, 2}};
        int[][] dir = {{-1,1}, {0,1}, {1,1} };
        int max = -(int)1e8;
        for( int i = 0; i < n; i++ ){
            max = Math.max( max , maxGoldMine_memo(grid, i, 0, dp , dir) );
        } 
        System.out.println(max);
    }

    public static void solve(){
        maxGoldMine();
    }
}