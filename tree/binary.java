import java.util.*;
import java.util.LinkedList.*;
class binary{
    public static void main(String[] args) {
        solve();
    }
     // Node class ----------------------------------------------------------------------------------------------
    public static class Node{
        int data = 0;
        Node left = null;
        Node right = null;

        Node( int data ){
            this.data = data;
        }
    }

    // constructor of binary tree---------------------------------------------------------------------------------
    static int idx = 0; 
    public static Node Constructor( int[] arr ) {
        if( arr[idx] == -1  || idx == arr.length ){
            idx++;
            return null;
        }

        Node node = new Node( arr[ idx++ ] );

        node.left = Constructor( arr );
        node.right = Constructor( arr );

        return node;

    }

    // display function of binary tree---------------------------------------------------------------------------------

    public static void display( Node node ){
        if( node == null ){
            return;
        }
        StringBuilder str = new StringBuilder();

        str.append( node.left != null ? node.left.data + "": '.');
        str.append( " <- " + node.data + " -> ");
        str.append( node.right != null ? node.right.data + "" : '.');
        System.out.println( str );
        
        display( node.left );
        display( node.right);
    }

    // size function of binary tree -----------------------------------------------------------------------------

    public static int size( Node node ){
        return node == null ? 0 : size( node.left) + size( node.right) + 1 ; 
    }

    // height  of binary tree -------------------------------------------------------------------------------------

    public static int height( Node node ){
        return node == null ? -1 : Math.max( height( node.left) , height( node.right ) ) + 1 ; 
    }

    // order of printing------------------------------------------------------------------------------------------
    public static void preOrder(Node node){
        if(node == null) return ;

        System.out.print(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void InOrder(Node node){
        if(node == null) return ;

        InOrder(node.left);
        System.out.print(node.data);
        InOrder(node.right);
    }

    public static void postOrder(Node node){
        if(node == null) return ;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data);
    }

    // diameter of binary  tree--------------------------------------------------------------------------------

    public static int diameter( Node node ){
        if( node == null ){
            return 0;
        }

        int ld = diameter( node.left );
        int rd = diameter( node.right );

        int lh = height( node.left );
        int rh = height( node.right );

        return Math.max( Math.max( ld , rd ), lh + rh + 2 );

    }

    public static int[] diameter_02( Node node ){ // return d and h at a time 
        if( node == null ) return new int[]{0 , -1};
        int[] lr = diameter_02( node.left );
        int[] rr = diameter_02( node.right );

        int dia = Math.max( Math.max( lr[0], rr[0] ), lr[1] + rr[1] + 2 );
        int height = Math.max( lr[1], rr[1] ) + 1;
        
        return new int[]{dia, height};
    }

    public static int diameter_03( Node node , int[] res ){
        if( node == null ) return -1;
        int lh = diameter_03( node.left , res );
        int rh = diameter_03( node.right , res );

        res[0] = Math.max( res[0], lh + rh + 2 );

        return Math.max( lh , rh ) + 1;
    }

    // iterative pre order traversal---------------------------------------------------------------------------------------------------------

    public static class Pair{
        Node node;
        int state;

        Pair( Node node, int state ){
            this.node = node;
            this.state = state;
        }
    }

    public static void IterativeTraversal( Node node ){
        Stack<Pair> st = new Stack<>();
        Pair rp = new Pair( node , 1 );
        st.push( rp );
        String preorder = "";
        String inorder = "";
        String postorder = "";
        while( st.size() > 0 ){
            Pair top = st.peek();
            if( top.state == 1){
                top.state++;
                preorder += top.node.data + " ";
                if( top.node.left != null ){
                    st.push( new Pair( top.node.left, 1 ) );
                }
            }else if( top.state == 2 ){
                top.state++;
                inorder += top.node.data +" ";
                if( top.node.right != null ){
                    st.push( new Pair( top.node.right , 1 ) ); 
                }
            }else{
                postorder += top.node.data + " ";
                st.pop();
            } 
        }

        System.out.println( preorder );
        System.out.println( inorder );
        System.out.println( postorder );
    }

    // level order printing of binary tree 

    public static void LevelOrder( Node node ){
        Queue< Node > que = new ArrayDeque<>();

        que.add( node );
        que.add( new Node( -1 ) );

        while( que.size() > 0 ){
            node = que.remove();

            if( node.data != -1 ){
                System.out.print( node.data + " ");
                if( node.left != null ){
                    que.add( node.left );
                }
                if( node.right != null ){
                    que.add( node.right );
                }
            }else {
                System.out.println();
                if( que.size() != 0 ){ 
                    que.add( new Node( -1 ) );
                }
            }
        }
    }

    // geegsforgeek  LeafToLeafSum -----------------------------------------------------------------------------

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
            this.right = right;
        }
    }

    static int LeafToleafans = -(int)1e8;
    public static int LeafToleafSum( TreeNode node ){
        if( node == null ) return -(int)1e8;

        if( node.left == null && node.right == null ){
            return node.val;
        }

        int lmax = LeafToleafSum( node.left );
        int rmax = LeafToleafSum( node.right );

        if( node.left != null && node.right != null ){
            LeafToleafans = Math.max( LeafToleafans, lmax + node.val + rmax );
        }

        return Math.max( lmax, rmax ) + node.val ; 
    }
    
    int maxPathSum( TreeNode root  ){
        LeafToleafSum( root );
        return LeafToleafans;   
    }

    // width of binary tree --------------------------------------------------------------------------------------------------------------

    public static void width( Node node, int level , int[] minMax ){
        if( node == null ) return;

        minMax[0] = Math.min( minMax[0] , level);
        minMax[1] = Math.max( minMax[1], level );
        width( node.left , level  - 1, minMax );
        width( node.right, level + 1, minMax );

    }

    // Kdown -------------------------------------------------------------------------------------------------------------------------------
    public static void Kdown( Node node , int k ){
        if(node == null ){
            return;
        }
        if( k == 0  ) System.out.println( node.data );

        Kdown( node.left , k - 1 );
        Kdown( node.right , k - 1);

        return;
    }

    // rootToNode path ---------------------------------------------------------------------------------------------------

    public static boolean rootToNodePath( Node node , int k, ArrayList<Node> path  ){
        if( node == null) return false;
        if( node.data == k ) {
            path.add( node );
            return true; 
        }

        boolean res = false;

        res = res || rootToNodePath( node.left, k , path );
        res = res || rootToNodePath( node.right, k, path );

        if(res){
            path.add( node );
            return true;
        }
        return false;
    }

    // k - distance ---------------------------------------------------------------------------------------------------------------------
    public static void Kdown( Node node ,Node block,  int k ){
        if(node == null  || node == block || k < 0 ){
            return;
        }
        if( k == 0  ) System.out.println( node.data );

        Kdown( node.left , block, k - 1 );
        Kdown( node.right , block, k - 1);

        return;
    }

    public static void Kdistance( Node node, int tar , int k){
        ArrayList<Node> path = new ArrayList<>();
        rootToNodePath( node, tar , path);
        Node prev = null;
        for( int i = 0 ; i < path.size(); i++ ){
            Kdown( path.get(i), prev, k - i );
            prev = path.get(i);
        }
    }

    // K - distance  better ---------------------------------------------------------------------------------------------------------------

    public static int Kdistance_better( Node node , int tar, int k ){
        if( node == null ) return - 1;

        if( node.data == tar ){
            Kdown( node, null, k );
            return 1;
        }

        int ld = Kdistance_better( node.left, tar, k );
        if( ld != -1 ){
            Kdown( node, node.left, k - ld );
            return ld +  1;
        }

        int rd = Kdistance_better( node.right , tar, k );
        if( rd != -1 ){
            Kdown( node, node.right, k - rd );
            return rd + 1;
        }
        
        return - 1;
    }

    // GeeksForGeek ** burn the binary tree ------------------------------------------------------------------------------------------------------

    public static void BurningNodes(Node node,int time,ArrayList<ArrayList<Integer>> ans){
        if(node == null) return;

        if(time == ans.size()) ans.add(new ArrayList<>());
        ans.get(time).add(node.data);

        BurningNodes(node.left, time + 1, ans);
        BurningNodes(node.right, time + 1, ans);
    }

    public static int burningOfTree_(Node node,int data,ArrayList<ArrayList<Integer>> ans)
    {
        if(node==null) return -1;

        if(node.data == data){
            BurningNodes(node,0,ans);
            return 1;
        }

        int ld = burningOfTree_(node.left, data, ans);
        if(ld != -1){
        
            if(ld == ans.size()) ans.add(new ArrayList<>());    
            ans.get(ld).add(node.data);
            BurningNodes(node.right,ld + 1,ans);
            return ld + 1;
        
        }

        int rd = burningOfTree_(node.right, data, ans);
        if(rd != -1){

            if(rd == ans.size()) ans.add(new ArrayList<>());    
            ans.get(rd).add(node.data);
            BurningNodes(node.left,rd + 1,ans);
            return rd + 1;
        
        }

        return -1;
    }


    // LCA - lowest common ancester 

    public static Node lowestCommonAncestor(Node root,int p, int q) {

        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();
        
        rootToNodePath(root,p,path1);
        rootToNodePath(root,q,path2);

        int i = path1.size()-1;
        int j = path2.size()-1;

        Node LCA = null;

        while(i >= 0 && j >= 0){
            if(path1.get(i)!=path2.get(j)) break;
            
            LCA = path1.get(i);
            i--;
            j--;
        }

        return LCA;
    }

    // level order traversal 

    public static void BFS( Node node ){
        LinkedList<Node> que = new LinkedList<>();

        que.addLast( node );

        int level = 0;
        while( que.size() != 0 ){
            int size = que.size();
            System.out.print( "level " + level  + " ");
            while( size != 0 ){
                Node top = que.removeFirst();
                System.out.print( top.data + " ");
                if( top.left != null )  que.addLast( top.left );
                if( top.right != null ) que.addLast( top.right );
                size--;
            }
            level++;
            System.out.println();
        } 
    }

    // left view of level order ------------------------------------------------------------------------------------------------------------------------

    public static void BFS_left( Node node ){
        LinkedList<Node> que = new LinkedList<>();

        que.addLast( node );

        int level = 0;
        while( que.size() != 0 ){
            int size = que.size();
            System.out.print( "level " + level  + " ");
            System.out.println( que.getFirst().data + " ");
            while( size != 0 ){
                Node top = que.removeFirst();
                if( top.left != null )  que.addLast( top.left );
                if( top.right != null ) que.addLast( top.right );
                size--;
            }
            level++;
            System.out.println();
        } 
    }

    // right view of level order ------------------------------------------------------------------------------------------------------------------

    public static void BFS_right( Node node ){
        LinkedList<Node> que = new LinkedList<>();

        que.addLast( node );

        int level = 0;
        while( que.size() != 0 ){
            int size = que.size();
            Node prev = null;
            System.out.print( "level " + level  + " ");
            
            while( size != 0 ){
                Node top = que.removeFirst();
                prev = top;
                if( top.left != null )  que.addLast( top.left );
                if( top.right != null ) que.addLast( top.right );
                size--;
            }
            System.out.println( prev.data + " ");
            level++;
            System.out.println();
        } 
    }

    // vertical order view  --------------------------------------------------------------------------------------------------------------------- 

    public static class vPair{
        Node node;
        int level;

        vPair( Node node, int level ){
            this.node = node;
            this.level = level;
        }
    }

    public static void BFS_VerticalOrder( Node node ){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        ArrayList<Node>[] arr = new ArrayList[ minMax[1] - minMax[0] + 1];
        
        for( int i = 0; i < arr.length; i++ )  arr[i] = new ArrayList<>();
        LinkedList<vPair> que = new LinkedList<>();

        que.addLast( new vPair( node, Math.abs( minMax[0] ) ) );

        while( que.size() != 0 ){
            int size = que.size();

            while( size-->0 ){
                vPair temp = que.removeFirst();
                Node tnode = temp.node;
                int level = temp.level;
                arr[level].add( tnode );
                if( tnode.left != null ) que.addLast( new vPair( tnode.left, level - 1) );
                if( tnode.right != null ) que.addLast( new vPair( tnode.right, level + 1 ) );
                size--;
            }
        }

        for( int i = 0; i < arr.length; i++ ){
            for( int j = 0; j < arr[i].size(); j++  ){
                System.out.print( arr[i].get(j).data + " ");
            }
            System.out.println();
        }
    }

    // left prefernce of vertical order 

    public static class bPair{
        Node node;
        int level;
        int height;

        bPair( Node node, int level , int height){
            this.node = node;
            this.level = level;
            this.height = height;
        }
    }

    public static void BFS_VerticalLeft( Node node ){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        bPair[] arr = new bPair[ minMax[1] - minMax[0] + 1];
        LinkedList<bPair> que = new LinkedList<>();
        que.addLast( new bPair( node, Math.abs( minMax[0] ) , 0 ) );

        while( que.size() != 0 ){
            int size = que.size();

            while( size-->0 ){
                bPair temp = que.removeFirst();
                
                Node tnode = temp.node;
                int level = temp.level;
                int height = temp.height;

                if( arr[level] == null ) arr[level] = temp;
                else if( arr[level].height < height ) arr[level] = temp;

                if( tnode.left != null ) que.addLast( new bPair( tnode.left, level - 1, height + 1) );
                if( tnode.right != null ) que.addLast( new bPair( tnode.right, level + 1 , height + 1 ) );
                size--;
            }
        }

        for( int i = 0; i < arr.length; i++ ){
            System.out.println( arr[i].node.data  );
        }
    } 
    
    // right preference of vertical order -------------------------------------------------------------------------------------------------------
    // it is also bottom view
    public static void BFS_VertivalRight( Node node ){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        int[] arr = new int[minMax[1] - minMax[0] + 1];

        LinkedList<vPair> que = new LinkedList<>();

        que.addLast( new vPair( node, Math.abs( minMax[0] ) ) );

        while( que.size() != 0 ){
            int size = que.size();

            while( size-->0 ){
                vPair temp = que.removeFirst();
                Node top = temp.node;
                int level = temp.level;

                arr[level] = top.data;

                if( top.left != null ) que.addLast( new vPair( top.left, level - 1) );
                if( top.right != null ) que.addLast( new vPair( top.right, level + 1) );
            }
        }

        for( int i = 0; i < arr.length; i++)  System.out.println( " level " + i + " " + arr[i] );

    }

    // https://practice.geeksforgeeks.org/problems/vertical-sum/1--------------------------------------------------------------------------

    public ArrayList <Integer> verticalSum(Node root) {
        // add your code here
        if( root == null) return new ArrayList<>();
        ArrayList<Integer> res = new ArrayList<>();
        
        int minMax[] = new int[2];
        width( root, 0, minMax );
        
        int arr[] = new int[ minMax[1] - minMax[0] + 1];
        
        LinkedList<vPair> que = new LinkedList<>();
        
        que.addLast( new vPair( root, Math.abs(minMax[0] ) ) );
        
        while( que.size() != 0 ){
            int size = que.size();
            
            while( size-->0 ){
                vPair t = que.removeFirst();
                Node top = t.node;
                int level = t.level;
                
                arr[level] += top.data;
                
                if( top.left != null ) que.addLast( new vPair( top.left, level - 1) );
                if( top.right != null ) que.addLast( new vPair( top.right, level + 1) );
            }
        }
        
        for( int i = 0; i < arr.length; i++ ) res.add( arr[i] );
        
        return res;
    }

    // top view of tree --------------------------------------------------------------------------------------------------------------------------

    public static void BFS_TopView( Node node ){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        Node[] arr = new Node[ minMax[1] - minMax[0] + 1];

        LinkedList<vPair> que = new LinkedList<>();
        que.addLast( new vPair( node, Math.abs( minMax[0] ) ) );

        while( que.size() != 0 ){
            int size = que.size();

            while( size-->0 ){
                vPair temp = que.removeFirst();
                Node tnode = temp.node;
                int level = temp.level;
                if( arr[level] == null ) arr[level] = tnode;
                if( tnode.left != null ) que.addLast( new vPair( tnode.left, level - 1) );
                if( tnode.right != null ) que.addLast( new vPair( tnode.right, level + 1 ) );
            }
        }

        for( int i = 0; i < arr.length; i++ ) System.out.println( "level ->" + i + " " + arr[i].data );
    }

    // diagonal view of binary tree -----------------------------------------------------------------------------------------------------------

    public static void BFS_DiagonalOrder( Node node ){
        int[] minMax = new int[2];
        width(node, 0, minMax);
        ArrayList<Integer>[] arr = new ArrayList[ 0 - minMax[0] + 1];
        
        for( int i = 0; i < arr.length; i++ )  arr[i] = new ArrayList<>();
        LinkedList<vPair> que = new LinkedList<>();

        que.addLast( new vPair( node, Math.abs( minMax[0] ) ) );

        while( que.size() != 0 ){
            int size = que.size();

            while( size-->0 ){
                vPair temp = que.removeFirst();
                Node tnode = temp.node;
                int level = temp.level;
                arr[level].add( tnode.data  );
                if( tnode.left != null ) que.addLast( new vPair( tnode.left, level - 1) );
                if( tnode.right != null ) que.addLast( new vPair( tnode.right, level ) );
                size--;
            }
        }

        for( int i = 0; i < arr.length; i++ ){
            for( int j = 0; j < arr[i].size(); j++  ){
                System.out.print( arr[i].get(j) + " ");
            }
            System.out.println();
        }
    }    

    // binary tree to cyclic double linkedlist ------------------------------------------------------------------------------------------------------
    static Node head = null;
    static Node prev = null;
    public static Node btreetoCdll( Node node ){
        btreetoCdll_( node );
        prev.right = head;
        head.left = prev;
        return head;
    }

    public static void btreetoCdll_( Node node ){
        if( node == null) return;

        btreetoCdll_( node.left );
        if( head == null) head = node;
        else{
            node.left = prev;
            prev.right = node;
        }
        prev = node;
        btreetoCdll_( node.right );
    }

    // predececcor and succeccor of binary tree ***************************************************************************************************

    public static int  getPred(Node node ) {
        Node curr = node;

        while( curr.right != null ){
            curr = curr.right;
        }
        return curr.data;
    }

    public static int  getSucc( Node node ) {
        Node curr = node;
        while( curr.left != null ){
            curr = curr.left;
        }
        return curr.data;
    }

    public static boolean PredSucc( Node node , int tar ){
        if( node == null) return false;

        if( node.data == tar ){
            int predececcor = getPred( node.left );
            int succeccor = getSucc( node.right );
            System.out.println( "Predececcor->" + predececcor  + " Succeccor->" + succeccor ); 
            return true;
        }

        if( PredSucc( node.left, tar ) ) return true;
        if( PredSucc( node.right ,tar )) return true;

        return false;
    }

    // improved solution of predececcor and succeccor *****************************************************************************************

    public static class allsolutionPair{
        int size = 0;
        int height = 0;
        boolean find = true;
        Node pred = null, succ = null, prev = null ;
        int ceil = (int)1e8 ;
        int floor = -(int)1e8 ;
    }

    public static void allsolution(Node node, int level, int data, allsolutionPair pair ) {
        if( node == null) return;
        pair.size++;
        pair.height = Math.max( pair.height, level );
        pair.find = node.data ==  data || pair.find ; 
        // pred and succ
        if( node.data == data && pair.pred == null ) pair.pred = pair.prev;
        if( pair.prev != null && pair.prev.data == data ) pair.succ = node;
        // ceil and floor
        if( node.data > data ) pair.ceil = Math.min( pair.ceil, node.data );
        if( node.data < data ) pair.floor = Math.max( pair.floor, node.data );

        pair.prev = node;
        allsolution( node.left, level + 1, data, pair );
        allsolution( node.right, level + 1, data, pair );
    }

    public static void displayPair(allsolutionPair pair ){
        System.out.println( pair.size);
        System.out.println( pair.height);
        System.out.println( pair.find);
        System.out.println( pair.ceil);
        System.out.println( pair.floor);
        System.out.println( pair.pred.data);
        System.out.println( pair.succ.data);
    }

    // iterative traversal of binary tree

    public static class tpair{
        Node node;
        boolean self = false;
        boolean left = false;
        boolean right = false;
        tpair( Node node , boolean self, boolean left, boolean right ){
            this.node = node;
            this.self = self;
            this.left = left;
            this.right = right;
        }
    }

    public static void traversal01( Node node ){// we can print inorder , preorder and postorder just by moveing the position of slefdone 
        Stack<tpair> st = new Stack<>();
        st.push( new tpair( node, false, false, false ) );

        while( st.size() != 0 ){
            tpair top = st.peek();
            if( !top.left ){
                if( top.node.left != null ) st.push( new tpair( top.node.left, false, false ,false ) );
                top.left = true;
            }else if( !top.self ){
                top.self = true;
                System.out.println( top.node.data );
            }else if( !top.right ){
                top.right = true;
                if( top.node.right != null ) st.push( new tpair( top.node.right, false, false, false) );
            }else {
                st.pop();
            }
        }
    }

    // Moriss traversal ----------------------------------------------------------------------------------------------------------------------

    


    public static void solve() {
        int[] arr={10,20,40,-1,-1,50,80,-1,-1,90,-1,-1,30,60,100,-1,-1,-1,70,110,-1,-1,120,-1,-1};
        Node root = Constructor( arr );
        display( root );
        // System.out.println( size( root ) );
        // System.out.println( height( root ) );
        // int[] res = new int[1];
        // diameter_03( root , res );
        // System.out.println( res[0] );
        // System.out.println( diameter( root ) );
        // int[] dia = diameter_02( root );
        // System.out.println( dia[0] );
        //IterativeTraversal( root );
        //LevelOrder( root );
        //Kdown( root, 2 );
        // int[] minMax = new int[2];
        // width( root , 0, minMax);
        // System.out.println( minMax[1] - minMax[0] + 1);
        //Kdistance( root , 30, 2 );
        //Kdistance_better( root, 30 , 2);
        //BFS_right( root );
        //BFS_VerticalOrder( root );
        //BFS_VerticalLeft( root );
        //BFS_VertivalRight( root );
        //BFS_TopView( root );
        //BFS_DiagonalOrder( root );
        //PredSucc( root, 20 );
        //allsolutionPair pair =  new allsolutionPair();
        //allsolution( root, 0, 30, pair );
        //displayPair( pair );
        //traversal01( root );
    }
}