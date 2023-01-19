/**
 * HeapUtils is a fantastic class that allows you 
 * to perform operations on the data structure known as heaps, 
 * such as insersion, removal, building, and heapifying. The
 * heap starts with a given size. Heaps are useful for
 * storing, manipulating, sorting, and indexing the various
 * objects.
 * @author Aarav Borthakur
 * @version 01/12/22
 */
public class HeapUtils 
{
    private int heapSize;

    /**
     * Constructs a HeapUtils object given the size of the 
     * the heap, on which you 
     * can perform operations on heaps such as insertion, 
     * deletion, building, and heapifying. O(1) order
     * complexity.
     * @param heapSize      The size of the heap
     */
    public HeapUtils(int heapSize)
    {
        this.heapSize = heapSize;
    }

    /**
     * Gets the index of the left child of the node 
     * rooted at a given index of a heap. This method 
     * has an order complexity of O(1). Gets the left
     * child with 2 * index.
     * @param heap          The heap, represented as an array 
     *                      of Comparables
     * @param parentIndex   The index of the node whose
     *                      left child index is to be 
     *                      retrieved
     */
    private int getLeftIndex(int parentIndex)
    {
        return 2 * parentIndex;
    }

    /**
     * Gets the index of the right child of the node 
     * rooted at a given index of a heap. This method 
     * has an order complexity of O(1). Gets the right
     * child's index with 2 * index + 1
     * @param heap          The heap, represented as an array 
     *                      of Comparables
     * @param parentIndex   The index of the node whose
     *                      right child's index is to be 
     *                      retrieved
     */
    private int getRightIndex(int parentIndex)
    {
        return 2 * parentIndex + 1;
    }

    /**
     * Gets the index of the last non-leaf node
     * in a given heap. Gets the last non leaf node's 
     * index with heapSize / 2. It has an order
     * complexity of O(1).
     */
    private int getLastNonLeafNodeIndex()
    {
        return heapSize / 2;
    }

    /**
     * remove removes and returns the root value from a given 
     * heap, maintaining the qualities of a heap. It does
     * so by swapping the root and right most nodes of the
     * heap and then reducing the heapSize by one. 
     * It then heapifies the root subtree; if any swaps occur
     * then the subtree with the swapped value is heapified,
     * and so on until the while tree is a heap.
     * This method has an order complexity of O(log n).
     * @param heap          The heap whose root is to be removed
     * @return              The former root of the heap supplied
     * 
     */
    public Comparable remove(Comparable[] heap)
    {
        Comparable formerRootValue = heap[1];
        int rightMostIndex = heapSize;
        swap(heap, 1, rightMostIndex);
        heapSize--;
        heapify(heap, 1);
        return formerRootValue;
    }

    /**
     * 
     * buildHeap arranges a complete binary tree, represented as an array,
     * to be a heap. Values in the array are intepreted as a complete binary
     * tree by doing a level order traversal. For example, if your array 
     * contains [null, 1, 3, 4], Then 1 will be considered the root node.
     * 3 its right child, and 4 its left child.
     * buldHeap works by heapifying each subtree, starting at the 
     * first non-leaf node, going left in its level. After heapifying all
     * subtrees rooted at the second-most bottom level, it heapifies the 
     * subtrees rooted at the level above, and so on until heapifying
     * the tree rooted at index 1 of heap.
     * The values in the nodes can be arranged in an
     * arbitrary way. buildHeap an order complexity of O(n log n).
     * @param heap      The array of Comparables to be arranged
     *                  into a heap; heap is a complete binary
     *                  tree; the value at index 0 is null
     * @precondition    heap is an arbitrarily-arranged complete binary
     *                  tree; the value at index 0 of heap is null
     * @postcondition   heap contains a complete binary tree 
     *                  satisfying the heap condition.
     */
    public void buildHeap(Comparable[] heap)
    {
        int lastLeafNodeIndex = getLastNonLeafNodeIndex();
        for (int i = lastLeafNodeIndex; i > 0; i--)
        {
            heapify(heap, i);
        }
    }

    /**
     * Swaps two items of an array, given their indeces. It
     * has a order complexity of O(1).
     * @param arr         The array that contains the values 
     *                    to be swapped
     * @param firstIndex  The index of the first value to be
     *                    swapped
     * @param secondIndex The second value to be swapped
     * @postcondition     Values at firstIndex and secondIndex
     *                    are swapped
     */
    private void swap(Comparable[] arr, int firstIndex, int secondIndex)
    {
        Comparable temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    /**
     * Converts a complete binary tree into a heap,
     * does this by checking the root node
     * against its children and then swapping it with
     * the larger child
     * recursing till the swapped value reaches
     * it's rightful place in the heap.
     * @param heap      the array containing
     *                  the values to be converted into a max-heap
     * @param index     the index of the imagined
     *                  root of the complete binary tree that
     *                  is to be heapified or converted to a max heap
     *                  (max value is at root)
     * @precondition    The first value of heap is null; heap 
     */
    public void heapify(Comparable[] heap, int index)
    {
        int leftChildIndex = getLeftIndex(index);
        int rightChildIndex = getRightIndex(index);
        int largestIndex = index;

        if (leftChildIndex <= heapSize && heap[leftChildIndex].compareTo(heap[largestIndex]) > 0)
        {
            largestIndex = leftChildIndex;
        }
        if (rightChildIndex <= heapSize && heap[rightChildIndex].compareTo(heap[largestIndex]) > 0)
        {
            largestIndex = rightChildIndex;
        }
        if (largestIndex != index)
        {
            swap(heap, largestIndex, index);
            heapify(heap, largestIndex);
        }
    }

    /**
     * heapSort sorts the items in an array. Does so
     * by first arranging the values in the given
     * array into a heap with HeapUtils.buildHeap, which
     * arranges a complete binary tree, represented as an array,
     * to be a heap. Values in the array are intepreted as a complete binary
     * tree by doing a level order traversal. For example, if your array 
     * contains [null, 1, 3, 4], Then 1 will be considered the root node.
     * 3 its right child, and 4 its left child.
     * buldHeap works by heapifying each subtree, starting at the 
     * first non-leaf node, going left in its level. After heapifying all
     * subtrees rooted at the second-most bottom level, it heapifies the 
     * subtrees rooted at the level above, and so on until heapifying
     * the tree rooted at index 1 of heap. After building the
     * heap, heapSort uses HeapUtils.remove to swap
     * the root and right most nodes of the
     * heap and then reducing the heapSize by one. As a result,
     * the value at the root is moved moved to the end of the 
     * array. The root subtree is then heapified; if any swaps occur
     * then the subtree with the swapped value is heapified,
     * and so on until the while tree is a heap. In the end,
     * all values in the heap are arranged in ascending order.
     * heapSort has an order complexity of O(n log n).
     * @param heap      The array containing values to be sorted;
     *                  the first value of heap should be null
     * @precondition    The first value of heap is null
     * @postcondition   heap points to an array with the values 
     *                  sorted in ascending order
     * @return          The array with the values sorted in
     *                  ascending order
     */
    public Comparable[] heapSort(Comparable[] heap)
    {
        buildHeap(heap);
        for (int i = heapSize; i > 1; i--)
        {
            remove(heap);
        }
        return heap;
    }

    /**
     * Gets the parent of the node rooted at
     * index, by dividing its index by two.
     * getParentIndex as an order complexity
     * of O(1)
     * @param index     The index of the
     *                  node whose parent's
     *                  index is to be
     *                  retrieved
     * @return          The index of the
     *                  parent node of 
     *                  the node rooted 
     *                  at index index.
     */
    private int getParentIndex(int index)
    {
        return index / 2;
    }


    /**
     * insert unserts a value into a heap. insert
     * does so by adding the value at the end of the
     * heap and increasing heapSize by one (also
     * doubles the capacity of the array representing
     * the heap if necessary). insert then continously
     * bubbles up from the newly-added node and swaps
     * parents with children if a child has a greater
     * value than its parent, until no swaps need
     * to be made and heap attributes are obtained.
     * This method has an order complexity of O(log n).
     * 
     * @param heap          The heap, represented as an array
     *                      of Comparables, to be intepreted
     *                      as a heap with a level-order 
     *                      traversal
     * @param item          The item to add to the heap
     * @precondition        The first value of heap is null
     * @return              The new heap array, item added to it
     */
    public Comparable[] insert(Comparable[] heap, Comparable item)
    {
        heapSize++;
        Comparable[] newHeap;
        if (heapSize >= heap.length)
        {
            newHeap = new Comparable[heapSize * 2];
            for (int i = 1; i < heap.length; i++)
            {
                newHeap[i] = heap[i];
            }
        }
        else
        {
            newHeap = heap;
        }
        newHeap[heapSize] = item;
        int parentIndex = getParentIndex(heapSize);
        boolean cont = true;
        while (parentIndex >= 1 && cont)
        {
            int largestIndex = parentIndex;
            if (newHeap[getLeftIndex(parentIndex)] != null && 
                newHeap[getLeftIndex(parentIndex)].compareTo(newHeap[largestIndex]) > 0)
            {
                largestIndex = getLeftIndex(parentIndex);
            }
            if (newHeap[getRightIndex(parentIndex)] != null && 
                newHeap[getRightIndex(parentIndex)].compareTo(newHeap[largestIndex]) > 0)
            {
                largestIndex = getRightIndex(parentIndex);
            }
            cont = largestIndex != parentIndex;
            if (cont)
            {
                swap(newHeap, largestIndex, parentIndex);
                parentIndex = getParentIndex(parentIndex);
            }
            
        }
        return newHeap;
    }
}
