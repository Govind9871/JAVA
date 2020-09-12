import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
public class BST{
    public static void main(String[] args) {
        solve();
    }

    public static class Node{
        int data;
        Node left;
        Node right;
        Node( int data ){
            this.data = data;
        }
        Node( int data, Node left, Node right ){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // Basic of BST ------------------------------***********************************************************************************************
    public static Node construct_BST( ArrayList<Integer> arr , int si, int ei ){
        if( si > ei ) return null;

        int mid = (si + ei)>>1;
        Node node = new Node( arr.get( mid ) );

        node.left = construct_BST( arr, si , mid - 1);
        node.right = construct_BST( arr, mid + 1, ei );

        return node;
    }

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

    public static int size( Node node ){
        return node == null ? 0 : size( node.left) + size( node.right ) + 1;
    }

    public static int height( Node node ){
        return node == null ? -1 : Math.max( height( node.left) , height( node.right) ) + 1;
    }

    public static boolean find( Node node , int tar ){ 
        Node current = node;
        while( current != null ){
            if( current.data == tar ) return true;
            else if( current.data > tar ) current = current.left;
            else current = current.right;
        }
        return false;
    }

    public static int Min( Node node ){
        Node curr = node;

        while( curr != null ){
            curr = curr.left;
        }
        return curr.data ;
    }

    public static int Max( Node node ){
        Node cur = node;

        while( cur != null ) cur = cur.right;

        return cur.data;
    }
 
    // preorder bst
    static int idx = 0;
    public static Node BSTUsingPreOrder(int[] arr,int lRange, int rRange){
        if(idx >= arr.length || arr[idx] < lRange  ||  arr[idx] > rRange) return null;

        Node node = new Node(arr[idx++]);

        node.left = BSTUsingPreOrder(arr,lRange, node.data);
        node.right = BSTUsingPreOrder(arr,node.data,rRange);

        return node;
    }

    public static int BSTPreOrderHeight(int[] arr,int lRange, int rRange){
        if(idx >= arr.length || arr[idx] < lRange  ||  arr[idx] > rRange) return -1;

        int ele = arr[idx++];

        int left = BSTPreOrderHeight(arr,lRange, ele);
        int right = BSTPreOrderHeight(arr,ele,rRange);

        return Math.max( left, right ) + 1;
    }

    public static void BSTUsingPreOrder(){
        int[] arr = {7,3,1,0,2,6,4,5,12,9,8,11,10,13,15,14};
        //display(BSTUsingPreOrder(arr,-(int)1e8,(int)1e8));
        //System.out.println(BSTPreOrderHeight(arr,-(int)1e8,(int)1e8));
    }


    // construct BST from levelorder arr ** GFG*************************************************************************************************

    public static class GFGPair{
        int Lr;
        int Rr;
        Node parent;
        boolean side = false ;
        GFGPair( int Lr, int Rr, Node node, boolean s ){
            this.Lr = Lr;
            this.Rr = Rr;
            parent = node;
            side = s;
        }
    }
    public static Node constructBST(int[] arr){
        //Write your code here and return the root of the constructed BST
        int idx = 0;
        LinkedList<GFGPair> que = new LinkedList<>();
        que.addLast( new GFGPair( -(int)1e8, (int)1e8, null, false ) );
        Node root = new Node( arr[idx++] ); 
        while( idx < arr.length  ){
            GFGPair  t = que.removeFirst();
            int Lr = t.Lr;
            int Rr = t.Rr;
            Node par = t.parent;
            boolean side = t.side;
            if( Lr == -(int)1e8 && Rr == (int)1e8 ){
                que.addLast( new GFGPair( Lr, root.data , root , true) );
                que.addLast( new GFGPair( root.data, Rr , root , false) );
            }else if( arr[idx] > Lr && arr[idx] < Rr ){
                    Node node = new Node( arr[idx++] );
                    que.addLast( new GFGPair( Lr, node.data , node , true) );
                    que.addLast( new GFGPair( node.data, Rr , node , false) );
                    if( side == true ){
                        par.left = node;
                    }else {
                        par.right = node;
                    }
            }
        }
        
        return root;  
    }

    // predececcor ansd succeccor of BST********************************************************************************************************
    // inorder
    public static void predSucc( Node node, int tar ){
        Node curr = node;
        Node pred = null;
        Node succ = null;
        while( curr != null ){
            if( curr.data == tar ){
                if( curr.left != null ){
                    pred = curr.left;
                    while( pred.right != null) pred = pred.right;
                }
                
                if( curr.right != null ){
                    succ = curr.right;
                    while( succ.left != null ) succ = succ.left;
                }

                break;

            }else if( curr.data > tar ){
                succ = curr;
                curr = curr.left;
            }else {
                pred = curr;
                curr = curr.right;
            }
        }

        System.out.println( pred.data + "  " + succ.data ) ;
        return;
    }

    //Leetcode 510.-*************************************************************************************************************************
    /*
    // Definition for a Node.
    class Node {
       public int val;
       public Node left;
       public Node right;
       public Node parent;
    };
    */

    // public Node inorderSuccessor(Node node) {
    //     Node curr = node;
    //     Node succ = null;
    //     if(curr.right!=null){
    //         succ = curr.right;
    //         while(succ.left != null) succ=succ.left;
            
    //         return succ;
    //     }
        
    //     Node prev = null;
    //     while(curr.parent!=null){
    //         prev = curr;
    //         curr = curr.parent;
    //         if(curr.left == prev) return curr;
    //     }
        
    //     return succ;
    // }

    // iterative traversal of all order ********************************************************************************************************

    public static class tpair{
        Node node = null;
        boolean self = false;
        boolean left = false;
        boolean right = false;

        tpair( Node node, boolean self, boolean left, boolean right ){
            this.node = node;
            this.self = self;
            this.left = left;
            this.right = right;
        }
    }

    public static void traversal_iterative( Node node ){// postorder
        Stack<tpair> st = new Stack<>();
        st.push( new tpair( node, false, false,false) );

        while( st.size() != 0 ){
            tpair top = st.peek();

            if( !top.left ){
                top.left = true;
                if( top.node.left != null ) st.push( new tpair( top.node.left , false, false,false ) );
            }else if( !top.self ){
                top.self = true;
                System.out.println( "inorder " + top.node.data );
            }else if( !top.right ){
                top.right = true;
                if( top.node.right != null ) st.push( new tpair( top.node.right, false,false, false ) );
            }else {
                st.pop();
            }
        }
    } 


    // diameter of a tree through iterative traversal 

    static int diameter = 0;

    public static class tdia{
        Node node = null;
        boolean left = false;
        boolean right = false;
        int lh = -1;
        int rh = -1;
        tdia( Node node, boolean left, boolean right , int lh, int rh  ){
            this.node = node;
            this.left = left;
            this.right = right;
            this.lh = lh;
            this.rh = rh;
        }
    }

    public static void iterative_dia( Node node ){
        
    }

    //************************************************************************************************************************ */

    public static void serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        LinkedList<Node> que = new LinkedList<>();
        
        que.addLast( root );
        
        while( que.size() != 0 ){
            int size = que.size();
            
            while( size-->0 ){
                Node top = que.removeFirst();
                
                if( top != null ){
                    sb.append( top.data + " ");
                    que.addLast( top.left );
                    que.addLast( top.right );
                }else {
                    sb.append( "null" + " ");
                }
            }
        }
        String[] arr = sb.toString().split(" ");
        for( int i = 0; i < arr.length; i++ ){
            System.out.println( arr[i] );
        }
        
    }

    //******************************************************************************************************************************************
    public static void solve() {
        
        ArrayList<Integer> arr = new ArrayList<>();
        for( int i = 1; i <= 5; i++) arr.add( i*10 );
        Node root = construct_BST( arr , 0, arr.size() - 1);
        //display( root );
        //BSTUsingPreOrder();
        //predSucc( root , 70);
        //traversal_iterative( root );
        serialize( root );
    }
}