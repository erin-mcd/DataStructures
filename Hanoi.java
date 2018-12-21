import java.util.Stack;
public class Hanoi {
    static Stack<Integer> a = new Stack<>();
    static Stack<Integer> b = new Stack<>();
    static Stack<Integer> c = new Stack<>();

    public static void move(int n, Stack<Integer> source, Stack<Integer> target, Stack<Integer> aux) {

        if (n > 0) {
            move(n - 1, source, aux, target);
            target.push(source.pop());
            move(n - 1, aux, target, source);
        }
    }


    public static void main(String[] args) {
        a.push(3);
        a.push(2);
        a.push(1);
        move(3, a, c, b);
        System.out.println(c.pop());
        System.out.println(c.pop());
        System.out.println(c.pop());
    }
}