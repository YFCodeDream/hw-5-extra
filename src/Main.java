import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        CompleteBinaryHeapPriorityDeque<Integer> priorityDeque =
                new CompleteBinaryHeapPriorityDeque<>();
        LinkedHashMap<Integer, Integer> testData = new LinkedHashMap<>();
        testData.put(2, 6);
        testData.put(1, 5);
        testData.put(4, 8);
        testData.put(3, 7);
        testData.forEach(priorityDeque::insert);

        System.out.println(priorityDeque);

        System.out.println("the element with highest priority in the queue");
        System.out.println(priorityDeque.min());

        System.out.println("remove and return the element with highest priority in the queue");
        System.out.println(priorityDeque.removeMin());

        System.out.println("the element with highest priority in the queue");
        System.out.println(priorityDeque.min());
        System.out.println(priorityDeque);

        System.out.println("insert an element with highest priority again");
        priorityDeque.insert(1, 5);
        System.out.println(priorityDeque);
    }
}
