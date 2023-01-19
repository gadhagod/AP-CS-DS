import java.util.Arrays;

public class Main 
{
    public static void main(String[] args) throws InterruptedException 
    {
        Comparable[] heap = new Comparable[12];
        int heapSize = heap.length - 1;
        
        for (int i = 1; i < heap.length; i++)
        {
            heap[i] = new Integer((int) (Math.random() * 100));
        }
        HeapDisplay heapDisplay = new HeapDisplay();
        HeapUtils heapUtils = new HeapUtils(heapSize);

        System.out.println(Arrays.toString(heap));
        heapDisplay.displayHeap(heap, heapSize);
        

        /*
        System.out.println(Arrays.toString(heap));
        heapUtils.buildHeap(heap);
        System.out.println(Arrays.toString(heap));
         */
        //System.out.println(Arrays.toString(heapUtils.heapSort(heap)));
        /*
        HeapUtils heapUtils = new HeapUtils(heapSize);
        //heapDisplay.displayHeap(newHeap, heapSize);
        heapUtils.buildHeap(heap);
        //(new HeapDisplay()).displayHeap(heap, heapSize);

        Comparable[] newNewHeap = new Comparable[12];
        for (int i = 1; i < newHeap.length; i++)
        {
            newNewHeap[i] = newHeap[i];
        }
        heapUtils.heapSort(newNewHeap);
        //(new HeapDisplay()).displayHeap(newNewHeap, heapSize);
        System.out.println(Arrays.toString(newNewHeap))        ;
         */
    }
}
