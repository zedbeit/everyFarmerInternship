import java.util.ArrayList;
import java.util.List;

public class MyStack<E>{
    List<E> list = new ArrayList<E>();

    public void push(E item){
        list.add(item);
    }

    public void pop() {
        Object[] obj = list.toArray();
        int len = obj.length;
        int index = len - 1;
        list.remove(index);
    }

    public void clear(){
        list.removeAll(list);
    }

}
