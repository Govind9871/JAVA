public class Client{
    public static void main(String[] args) throws Exception{
        expert1();
    }

    public static int nextGreater( int[] arr ){
        Stack st = new Stack();
        int n = arr.length;
        int[] res = new int[n];

        for( int i = 0; i < n; i++ ){
            if( st.size() == 0 ){
                st.push( i );
            }else {
                while( arr[i] > arr[st.top()] ){
                    res[st.top()] = i;
                    st.pop();
                }            
                st.push( i );
            }
        }
        while( st.size() != 0 ) res[ st.pop() ] = n - 1;

    }


    public static void expert1() throws Exception {
        
        DStack st = new DStack();
        for( int i = 0; i < 10; i++ ){
            st.push( i + 1);
        }
        st.push( 100 );
        st.pop();
        System.out.println( st );
    }
}