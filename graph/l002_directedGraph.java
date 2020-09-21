public class l002_directedGraph{
    static int N = 7;
    static ArrayList<Integer>[] graph = new ArrayList[N];

    public static void addEdge(int u,int v){
        graph[u].add(v);
    }

    public static void display(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i < N;i++){
            sb.append(i + " -> ");
            for(int e: graph[i]){
                sb.append(e +",");
            }
            sb.append('\n');
        }

        sb.append('\n');
        System.out.println(sb.toString());
    }

    public static void topoDFS( int src, boolean[] vis, ArrayList<Integer> ans ){
        vis[src] = true;
        for(int e : graph[src] ){
            if(!vis[e] ){
                topoDFS( e , vis, ans );
            }
        }
        ans.add( src );
    }

    public static void topologicalOrder(){
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();

        for( int i = 0; i < N; i++ ){
            if( !vis[i] ) topoDFS( i, vis, ans );
        }
    }

    // kahn's algo for cycle detection
    
    public static void kahnTopologicalOrder(){
        int[] indegree = new int[N];
        for(int i = 0; i < N;i++){
            for( int e : graph[i] ) indegree[e]++;
        }

        ArrayDeque<Integer> que = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();
        
        for(int i = 0; i < N; i++) if( indegree[i] == 0 ) que.add( i );

        while( que.size() != 0 ){
            int vtx = que.remove();
            ans.add( vtx );
            for( int e : graph[vtx] ){
                if( --indegree[e] == 0 ){
                    que.add( e );
                }
            }
        }

        if( ans.size() != N ) System.out.println("Cycle: ");
        else System.out.println( ans );
    }

    // leetcode 200 numsof island
    int[] par;
    public int findPar(int u){
        if(par[u] == u) return u;
        return par[u] = findPar(par[u]);
    }

    public int numIslands(char[][] grid) {
        if(grid.length==0 || grid[0].length==0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        
        par = new int[n*m];
        int count=0;
        for(int i=0;i<n*m;i++){
            par[i] = i;
            if(grid[i/m][i%m]=='1') count++;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == '1'){
                    int p1 = findPar(i*m+j);
                    if(j+1 < m && grid[i][j+1] == '1'){
                        int p2 = findPar(i*m+j+1);
                        if(p1 != p2){
                            par[p2] = p1;
                            count--;
                        }
                    }

                    if(i+1 < n && grid[i+1][j] == '1'){
                        int p2 = findPar((i+1)*m+j);
                        if(p1 != p2){
                            par[p2] = p1;
                            count--;
                        }
                    }
                }
            }
        }
        return count;
    }
}