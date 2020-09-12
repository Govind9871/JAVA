public class Client{
    public static void main(String[] args) throws Exception {
        solve();
    }
    public static void solve() throws Exception{
        Dqueue que = new Dqueue();
        for( int i = 0; i < 10; i++ ) que.push( i + 1 );

        System.out.println( que );
        que.remove();
        System.out.println( que );
        que.push( 11 );
        System.out.println( que );
        que.push(12);
        que.push(13);
        System.out.println( que );
    }
}