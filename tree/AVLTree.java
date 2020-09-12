public class AVLTree{
    public static void main(String[] args) {
        solve();
    }

    public static class Node{
        int data;
        Node left;
        Node right;
        int height = 0;
        int bal = 0;
        Node( int data ){
            this.data = data;
        }
        Node( int data, Node left, Node right ){
            this.data = data;
            this.left = left;
            this.right = right;
        }
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

    public static void updateHeightBal( Node node ){
        int lh = -1;
        int rh = -1;
        if( node.left != null) lh = node.left.height;
        if( node.right != null) rh = node.right.height;

        node.height = Math.max( lh, rh ) + 1;
        node.bal = lh - rh ;
        return;
    }

    public static Node leftRotation( Node A ){
        Node B = A.right;
        Node Bleft = B.left;
        
        B.left = A;
        A.right = Bleft;

        updateHeightBal( A );
        updateHeightBal( B );
        return B;   
    }

    public static Node rightRotation( Node A ){
        Node B = A.left;
        Node Bright = B.right;
        
        B.right = A;
        A.left = Bright;

        updateHeightBal( A );
        updateHeightBal( B );
        return B;   
    }
    // main AVL function
    public static Node getRotation( Node node ){
        updateHeightBal( node );
        if( node.bal == 2 ){// ll or lr 
            if( node.left.bal == 1 ){
                return rightRotation( node );// ll
            }else{
                node.left = leftRotation( node.left );
                return rightRotation( node );
            }
        }
        if( node.bal == -2 ){// rr or rl
            if( node.right.bal == -1 ){
                return leftRotation( node );// rr
            }else{
                node.right = rightRotation( node.left );
                return leftRotation( node );
            }
        }

        return node;
    }

    public static int minimum(Node node){
        Node curr = node;

        while(curr.left != null)
            curr=curr.left;

        return curr.data;
    }

    public static Node addNode(Node node,int data){
        if(node == null) return new Node(data);
        if(data < node.data) node.left = addNode(node.left,data);
        else node.right = addNode(node.right,data);
        node = getRotation( node );
        return node;
    }

    public static Node removeData(Node node,int data){
        if(node == null) return null;

        if(data < node.data) node.left = removeData(node.left,data);
        else if(data < node.data) node.right = removeData(node.right,data);
        else{
            if(node.left == null || node.right == null) return  node.left != null? node.left: node.right;

            int minEle = minimum(node.right);
            node.data = minEle;

            node.right = removeData(node.right,minEle);
        }
        node = getRotation( node );
        return node;
    }

    public static void solve() {
        Node root = null;

        for( int i = 1; i <= 18; i++ ) {
            root = addNode( root, i*10 );
            display( root );

            System.out.println("======================================================================================");
        }    

    }
}