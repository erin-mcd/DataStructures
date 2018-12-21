import java.util.ArrayList;

public class Heap2 {

   /* private ArrayList<Integer> list;

    public Heap() {
        this.list = new ArrayList<Integer>();
    }

    public Heap(ArrayList<Integer> items) {

        this.list = items;
        buildHeap();
    }

    public void insert(int item) {

        list.add(item);
        int i = list.size() - 1;
        int parent = parent(i);

        //while parent isn't the item and the item is less than the parent
        while (parent != i && list.get(i) < list.get(parent)) {

            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

    public void buildHeap() {
        for (int i = list.size() / 2; i >= 0; i--) {
            makeHeap(i);
        }
    }

    public int remove(int item){
        list.remove(item);
        int i = list.size() - 1;
        int parent = parent(i);

        //while parent isn't the item and the item is less than the parent
        while (parent != i && list.get(i) < list.get(parent)) {

            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
        return 0;
    }

    public int extractRoot() {

        if (list.size() == 0) {

            throw new IllegalStateException("Heap is EMPTY");
        } else if (list.size() == 1) {

            int top = list.remove(0);
            return top;
        }

        //Make the biggest item replace what was removed
        int top = list.get(0);
        int lastItem = list.remove(list.size() - 1);
        list.set(0, lastItem);

        // reorder
        makeHeap(0);

        // return top root
        return top;
    }

   /* public void decreaseKey(int i, int key) {

        if (list.get(i) < key) {

            throw new IllegalArgumentException("Key is larger than the original key");
        }

        list.set(i, key);
        int parent = parent(i);

        // bubble-up until heap property is maintained
        while (i > 0 && list.get(parent) > list.get(i)) {

            swap(i, parent);
            i = parent;
            parent = parent(parent);
        }
    }

    private void makeHeap(int i) {

        int left = left(i);
        int right = right(i);
        int smallest = -1;

        // find the smallest key between current node and its children.
        if (left <= list.size() - 1 && list.get(left) < list.get(i)) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= list.size() - 1 && list.get(right) < list.get(smallest)) {
            smallest = right;
        }

        // if the smallest key is not the current key then bubble-down it.
        if (smallest != i) {

            swap(i, smallest);
            makeHeap(smallest);
        }
    }

  /*  public int getTop() {

        return list.get(0);
    }

    public boolean isEmpty() {

        return list.size() == 0;
    }

    public int getSize(){
        return list.size();
    }

    public int getItem(int i){
        return list.get(i);
    }
    private int right(int i) {
        //right == 2*root + 2
        return 2 * i + 2;
    }

    private int left(int i) {
        //left == 2*root + 1
        return 2 * i + 1;
    }

    private int parent(int i) {

        if (i % 2 == 1) {
            return i / 2;
        }

        return (i - 1) / 2;
    }

    private void swap(int i, int parent) {
        //get the parent
        int temp = list.get(parent);
        //make the parent i
        list.set(parent, list.get(i));
        //make i the original parent
        list.set(i, temp);
    }

    public String toString(){
        String result = "["+ list.get(0);


        for (int i = 1; i < list.size(); i++) {
            result += "," +  list.get(i);
        }
        return result + "]";
    }


    public static void main(String[] args) {
        Heap h = new Heap();

        h.insert(5);
        h.insert(1);
        h.insert(2);
        h.insert(4);


    //    h.remove(2);
        System.out.println(h.toString());

    }
    */
}