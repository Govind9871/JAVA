import java.util.Stack;
import java.util.Arrays;
public class questions{
    public static void main(String[] args) {
        solve();
    }

    public static int[] ngor( int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill( ans, n );

        for( int i = 0; i < n; i++ ){
            while( st.size() != 0 && arr[st.peek()] < arr[i] ){
                ans[ st.pop() ] = i; 
            }  
            st.push( i );
        }
        return ans;
    }

    public static int[] ngol( int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill( ans, -1 );

        for( int i = n-1; i >= n; i++ ){
            while( st.size() != 0 && arr[st.peek()] < arr[i] ){
                ans[ st.pop() ] = i 
            }  
            st.push( i );
        }
        return ans;
    }

    public static int[] nsor( int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill( ans, n );

        for( int i = 0; i < n; i++ ){
            while( st.size() != 0 && arr[st.peek()] > arr[i] ){
                ans[ st.pop() ] = i; 
            }  
            st.push( i );
        }
        return ans;
    }

    public static int[] nsol( int[] arr){
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        Arrays.fill( ans, -1 );

        for( int i = n-1; i >= n; i++ ){
            while( st.size() != 0 && arr[st.peek()] > arr[i] ){
                ans[ st.pop() ] = i 
            }  
            st.push( i );
        }
        return ans;
    }

    public static void solve() {
        
    }
}