import java.util.ArrayList;
import java.util.Stack;
public class GNtree{
    public static void main(String[] args) {
        solve();
    }

    public static class Node{
        int data;
        ArrayList<Node> childs = new ArrayList<>();
        Node( int data , ArrayList<Node> child ){
            this.data = data;
            this.childs = child;
        }
    } 

    public static Node constructor( int[] arr ){
        Stack<Node> st = new Stack<>();
        int idx = 0;
        st.push( new Node( arr[idx++], new ArrayList<Node>() ) );
        while( idx < arr.length - 1 ){
            if( arr[idx] != -1 ){
                Node node = new Node( arr[idx++], new ArrayList<Node>() );
                st.push( node );
            }else {
                Node top = st.pop();
                st.peek().childs.add( top );
                idx++;
            }
        }
        return st.peek();
    }

    public static void display( Node node ){

        StringBuilder sb = new StringBuilder();
        sb.append( node.data + " -> "); 
        for( Node child : node.childs ) sb.append( child.data + ", ");
        System.out.println( sb + ".");

        for( Node child : node.childs ) display( child );
    }

    public static int height( Node node ){
        int h = - 1;

        for( Node child : node.childs ) h = Math.max( h, height( child ) );

        return h + 1;
    }

    public static int size( Node node ){
        int size = 0;

        for( Node child : node.childs ) size += size( child );

        return size + 1;
    }

    public static int maximum( Node node ){
        int max = node.data;

        for( Node child : node.childs ) max = Math.max( max, maximum( child ) );

        return max;
    }

    public static int minimum( Node node ){
        int min = node.data;

        for( Node child : node.childs ) min = Math.min( min  ,minimum(child) );

        return min;
    }
    
    // LCA of generic tree *--------------------------------------------------------------------------------------------------------------------
    public static void LCAofGNtree( Node node , int p, int q){
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();
        rootToNodepath( node, p, path1 );
        rootToNodepath( node, q, path2 );
        int i = path1.size() - 1;
        int j = path2.size() - 1;
        Node prev = null;
        while( i >=0 && j >= 0 ){
            if( path1.get(i) != path2.get(j) ) break;
            prev = path1.get( i );   
            i--;
            j--;
        }
        System.out.println( prev.data );
    }

    public static boolean rootToNodepath( Node node, int data , ArrayList<Node> path ){
        
        if( node.data == data ){
            path.add( node );
            return true;
        } 
        boolean res = false;
        for( Node child : node.childs ){
            res = res || rootToNodepath(child, data , path );

        }
        if(res) path.add( node );
        return res; 
    }
    // zigzag traversal of generic tree 
    public static void ZigZag( Node node ){
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();

        st1.push( node );
        boolean flag = true;
        while( st1.size() != 0 ){
            int size = st1.size();

            while( size-->0 ){
                Node top = st1.pop();
                System.out.print( top.data + " ");
                if( flag ){
                    for( Node child : top.childs ) st2.push( child );
                }else {
                    for( int i = top.childs.size() -1; i >= 0; i-- ){
                        st2.push( top.childs.get(i) );
                    }
                }
            }

            flag = !flag;
            st1 =st2;
            st2 = new Stack<Node>(); 
            System.out.println();
        }
    }

    // is tree is a mirror tree 

    public static Node linearization( Node node ){
        if( node.childs.size() == 0 ) return node;
        int n  = node.childs.size();

        Node otail = linearization( node.childs.get( n -1 ) );

        for( int i = n-2; i >=0 ; i-- ){
            Node tail = linearization( node.childs.get( i ) );

            tail.childs.add( node.childs.get(i + 1) );

            node.childs.remove( node.childs.size() - 1);
        }

        return otail;

    }

    public static void solve() {
        int arr[] = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1, 150, -1, -1, -1,-1};
        Node root = constructor( arr );

        //display( root );
        //LCAofGNtree( root, 50, 60 );
        //ZigZag( root );
        linearization( root );
        display( root );
    }
}