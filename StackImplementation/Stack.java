public class Stack{
    private int[] st;
    private int size;
    private int tos;
    private int maxSize;

    public Stack(){
        setValues(10);
    }

    public Stack(int n ){
        setValues(n);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for( int i = this.tos; i>=0; i-- ){
            sb.append( this.st[i] );
            if( i != 0 ) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    protected void setValues( int n ) {
        this.st = new int[n];
        this.size = 0;
        this.tos = -1;
        this.maxSize = n;
    }

    public int capacity(){
        return this.maxSize;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }    
    
    protected void push_( int val ){
        this.st[++this.tos] = val;
        this.size++;
    }

    public void push( int val ) throws Exception {
        if( this.size == this.maxSize ){
            throw new Exception("StackIsFull");
        }
        push_( val );
    }

    protected int top_(){
        return this.st[this.tos];
    }

    public int top() throws Exception {
        if( this.size == 0 ){
            throw new Exception("StackIsEmpty");
        }
        return top_();
    }

    protected int pop_(){
        int rv = top_();
        this.st[ this.tos--] = 0;
        this.size--;
        return rv;
    }

    public int pop() throws Exception{
        if( this.size == 0 ){
            throw new Exception("StackIsEmpty");
        }
        return pop_();
    }
}