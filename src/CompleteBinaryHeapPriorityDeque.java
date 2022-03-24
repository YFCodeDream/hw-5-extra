import java.util.Arrays;

@SuppressWarnings({"unchecked", "SameParameterValue"})
public class CompleteBinaryHeapPriorityDeque<T extends Comparable<T>> {
    private Object[] elements;

    private int size;

    private static final int DEFAULT_CAPACITY = 8;

    private static final int EXPAND_MULTIPLIER = 2;

    public CompleteBinaryHeapPriorityDeque() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public T min() {
        return ((Element) elements[0]).value;
    }

    public void insert(int k, T x) {
        if (size >= elements.length) {
            expandArr();
        }

        Element addElement = new Element(k, x);
        elements[size] = addElement;
        size += 1;

        shiftUp(size - 1);
    }

    public T removeMin() {
        if (isEmpty()) {
            throw new IllegalStateException("current complete binary heap is empty");
        }

        swap(0, size - 1);
        T minValue = ((Element) elements[size - 1]).value;

        size -= 1;
        sinkDown(0);

        return minValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void shiftUp(int currentIndex) {
        while (currentIndex >= 0) {
            int parentIndex = getParentIndex(currentIndex);
            int parentPriority = ((Element) elements[getParentIndex(currentIndex)]).priority;

            int currentPriority = ((Element) elements[currentIndex]).priority;

            if (currentPriority < parentPriority) {
                swap(currentIndex, parentIndex);
                currentIndex = parentIndex;
            } else {
                return;
            }
        }
    }

    private void sinkDown(int currentIndex) {
        // 叶子节点不需要下滤
        int half = size >>> 1;

        while(currentIndex < half){
            int leftIndex = getLeftChildIndex(currentIndex);
            int rightIndex = getRightChildIndex(currentIndex);

            if(rightIndex < size){
                // 右孩子存在 (下标没有越界)
                int leftPriority = ((Element) elements[leftIndex]).priority;
                int rightPriority = ((Element) elements[rightIndex]).priority;
                int currentPriority = ((Element) elements[currentIndex]).priority;

                // 比较左右孩子大小
                if(leftPriority < rightPriority){
                    // 左孩子更大，比较双亲和左孩子
                    if(currentPriority < leftPriority){
                        // 双亲最大，终止下滤
                        return;
                    } else {
                        // 三者中，左孩子更大，交换双亲和左孩子的位置
                        swap(currentIndex, leftIndex);
                        // 继续下滤操作
                        currentIndex = leftIndex;
                    }
                } else {
                    // 右孩子更大，比较双亲和右孩子
                    if(currentPriority < rightPriority){
                        // 双亲最大，终止下滤
                        return;
                    } else {
                        // 三者中，右孩子更大，交换双亲和右孩子的位置
                        swap(currentIndex, rightIndex);
                        // 继续下滤操作
                        currentIndex = rightIndex;
                    }
                }
            } else {
                // 右孩子不存在 (下标越界)
                int leftPriority = ((Element) elements[leftIndex]).priority;
                int currentPriority = ((Element) elements[currentIndex]).priority;

                // 当前节点 大于 左孩子
                if(currentPriority < leftPriority){
                    // 终止下滤
                    return;
                } else {
                    // 交换 左孩子和双亲的位置
                    swap(currentIndex, leftIndex);
                    // 继续下滤操作
                    currentIndex = leftIndex;
                }
            }
        }
    }

    private int getParentIndex(int currentIndex) {
        return (currentIndex - 1) / 2;
    }

    private int getLeftChildIndex(int currentIndex) {
        return (currentIndex * 2) + 1;
    }

    private int getRightChildIndex(int currentIndex) {
        return (currentIndex + 1) * 2;
    }

    private void swap(int index_1, int index_2) {
        Object element_1 = elements[index_1];
        Object element_2 = elements[index_2];

        elements[index_1] = element_2;
        elements[index_2] = element_1;
    }

    private void expandArr() {
        Object[] newElements = new Object[elements.length * EXPAND_MULTIPLIER];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    private class Element {
        int priority;

        T value;

        public Element(int priority, T value) {
            this.priority = priority;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "priority=" + priority +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public String toString() {
        StringBuilder resStr = new StringBuilder("CompleteBinaryHeapPriorityDeque {\n\telements={\n");
        for (int i = 0; i < size; i++) {
            resStr.append("\t\t").append(elements[i]).append("\n");
        }
        resStr.append("\t}\n}");
        return resStr.toString();
    }
}
