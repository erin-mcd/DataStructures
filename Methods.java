import java.util.ArrayList;
import java.util.List;



public class Methods {
    public static void main(String[] args) {
        List s = new ArrayList();
        List f = new ArrayList();
        List k = new ArrayList();
        List<Integer> m = new ArrayList();
        List<String> w = new ArrayList();
        String str = "Hello how are you today?";

        w.add("hi");
        w.add("hey");
        w.add("hello");
        w.add("bye");

        m.add(3);
        m.add(5);
        m.add(10);
        m.add(20);
        m.add(23);

        s.add(2);
        s.add(2);
        s.add("hi");
        s.add(56);
        s.add(true);
        s.add(23);

        f.add(true);
        f.add(2);
        f.add("hi");
        f.add(23);
        f.add(2);
        f.add(56);

        k.add(true);
        k.add(2);
        k.add("hi");
        k.add(23);
        k.add(2);
        k.add(56);

        System.out.println(unique(s));
        removeAllInstancesOf(k, 2);
        System.out.println(k);
        System.out.println(allMultiples(m, 5));
        System.out.println(allStringsOfSize(w, 3));
        System.out.println(stringToListOfWords(str));
        System.out.println(isPermutation(s, f));
    }

    public static <E> boolean unique(List<E> list) {
        boolean unique = true;
        //iterates through the list; compares each element to every other element in the list except itself
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j)) && i != j)
                    unique = false;
            }
        }
        return unique;
    }

    public static List allMultiples(List<Integer> list, Integer input) {
        List<Integer> result = new ArrayList();
        //for every number in the list, if the number is divided by the input number and the remainder is 0, the number must be a multiple
        for (Integer num : list) {
            if (num % input == 0)
                result.add(num);
        }
        //returns list of all multiples
        return result;
    }

    public static List allStringsOfSize(List<String> list, int length) {
        List<String> result = new ArrayList();
        //Iterates through list of strings
        for (int i = 0; i < list.size(); i++) {
            //b is the individual string retrieved by i
            String b = list.get(i);
            //add b to the new list if it is the desired length
            if (b.length() == length)
                result.add(list.get(i));
        }
        return result;
    }

    public static List stringToListOfWords(String input) {
        List<String> result = new ArrayList();
        //input is split every time there is any white space and each part is put into an array
        String[] s = input.split("\\s+");

        //this array is turned into a list
        for (int i = 0; i < s.length; i++) {
            result.add(s[i]);
        }
        return result;
    }

    public static <E> void removeAllInstancesOf(List<E> list, E item) {
        //iterate backwards through the list
        for (int i = list.size() - 1; i >= 0; i--) {
            //if the chosen item is there, remove it
            if (list.get(i) == item)
                list.remove(list.get(i));
        }
    }

    public static <E> boolean isPermutation(List<E> listOne, List<E> listTwo) {
        boolean result = false;

        //takes each element in list one and compares it to each element in list two
        if (listOne.size() == listTwo.size()) {
            for (int i = 0; i < listOne.size(); i++) {
                for (int j = 0; j < listTwo.size(); j++) {
                    //if the elements are the same, remove these elements from both lists
                    if (listOne.get(i).equals(listTwo.get(j))) {
                        listOne.remove(listOne.get(i));
                        listTwo.remove(listTwo.get(j));
                        //reset i and j so it continues going through the entire list every time
                        i = 0;
                        j = 0;
                        //if there is only one element left in list on and it is the same element as the one in list two, it is a permutation
                        if (listOne.size() == 1 && listOne.get(i).equals(listTwo.get(j))) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }
}
