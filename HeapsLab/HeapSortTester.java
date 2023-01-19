/**
 * Tests HeapUtils methods on a randomly generated heap array of size 12, 
 * with the heapSize 11
 * 
 * Modified to work with heapSize as an instance variable
 * and heap[] as an array. If both heap[] and heapSize 
 * are instance variables in your code then use the other tester
 * 
 * @author  Anu Datar
 * based on the work of Vijay B and Gwyneth C.
 * @version Dec 6, 2016
 */
public class HeapSortTester
{

    /**
     * runs a randomly generated array through HeapUtils methods to test
     * 
     * @param   args arguments from the command line
     */
    public static void main(String[] args)
    {
        int heapSize=11;
        Comparable[] arr = new Comparable[heapSize+1];
        HeapDisplay unsorted = new HeapDisplay();
        HeapUtils util = new HeapUtils(heapSize);
        System.out.println("This is the initial array ");
        System.out.println("------------------------------");
        System.out.print("|");
        for(int i = 1; i <= heapSize; i++)
        {
            arr[i] = (int)(Math.random( )* 100) + 1;
            System.out.print(arr[i] + "|");
        }
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
        unsorted.displayHeap(arr,heapSize);
        System.out.println("\nUnsorted array displayed. Now constructing heap...");
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
            //ignore
        }
        util.heapSort(arr);
        HeapDisplay sorted = new HeapDisplay();
        sorted.displayHeap(arr, heapSize);
        System.out.println("This is the sorted array using a max heap");
        System.out.println("------------------------------");
        System.out.print("|");
        for(int i = 1; i <= heapSize; i++)
        {
            System.out.print(arr[i] + "|");
            if(i > 1 && arr[i].compareTo(arr[i - 1]) < 0)
            {
                throw new RuntimeException("Items are not sorted in order from least to greatest");
            }            
        }
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("Heapsort works well!");
        
        System.out.println("Testing insert");
       
        System.out.println("This is the initial max heap ");
        System.out.println("------------------------------");
        System.out.print("|");
        for(int i = 1; i <= heapSize; i++)
        {
            arr[i] = (int)(Math.random( )* 100) + 1;
            System.out.print(arr[i] + "|");
        }
        arr[1] = 89;
        arr[2] = 52;
        arr[3] = 34;
        arr[4] = 12;
        arr[5] = 26;
        arr[6] = 15;
        arr[7] = 11;
        arr[8] = 7;
        heapSize = 8;
        util = new HeapUtils(heapSize);
        int insert = (int)(Math.random( )* 100) + 1;
        System.out.println("\nHeap constructed. Inserting value " + insert + " into the array...");
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
            
        }
        arr = util.insert(arr, insert);
        boolean inserted = false;
        for(int i = 1; i <= heapSize+1; i++)
        {
            System.out.print(arr[i] + "|");
            if(arr[i].equals(insert))
                inserted = true;
        }
        if( ! inserted)
        {
            throw new RuntimeException("Item was not inserted");
        }
        HeapDisplay insertUnsorted = new HeapDisplay();
        insertUnsorted.displayHeap(arr,heapSize+1);
        System.out.println(insert + " has been inserted. \nNow removing the root node from heap...");
        try
        {
            Thread.sleep(100);
        }
        catch(InterruptedException e)
        {
            //ignore
        }
        util.remove(arr);
        System.out.println("The root node has been removed. Now, heapsort will be called.");
        try
        {
            Thread.sleep(300);
        }
        catch(InterruptedException e)
        {
            //ignore
        }
        util.heapSort(arr);
        HeapDisplay insertSorted = new HeapDisplay();
        insertSorted.displayHeap(arr, heapSize);
        System.out.println("This is the sorted array using a max heap");
        System.out.println("------------------------------");
        System.out.print("|");
        for(int i = 1; i < 9; i++)
        {
            System.out.print(arr[i] + "|");
            if(i > 1 && arr[i].compareTo(arr[i - 1]) < 0)
            {
                throw new RuntimeException("Items are not sorted in order from least to greatest");
            }
        }
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
        
        System.out.println("Have a nice day"); 

    }
}