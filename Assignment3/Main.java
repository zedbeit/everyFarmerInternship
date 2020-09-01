import java.util.Iterator;

public class Main {
    public static void main(String[] args){
        MyStack<Integer> stack = new MyStack<>();
        
        stack.push(4);
        stack.push(4);
        stack.push(6);
        stack.push(8);
        stack.push(10);

        stack.pop();
        stack.pop();

//        stack.clear();

//      To iterate over the list
        Iterator itr = stack.list.iterator();

        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
