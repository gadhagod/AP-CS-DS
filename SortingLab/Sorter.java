/**
 * Sorter contains methods that allow you to 
 * run selection, insersion, and merge sorts
 * on arrays of type Comparable[]. It also
 * contains a main method for you to visualize
 * the sorts
 * @author  Aarav Borthakur
 * @version 2/11/23
 */
public class Sorter
{
    private SortDisplay display;
    
    /**
    * Opens a GUI to visualize selection, insersion, and merge
    * sorts by instantiating a Sorter instance
    * @param args   An array of command line arguments
    */
    public static void main(String[] args)
    {
        new Sorter();
    }
    
    /**
     * Swaps two items in an array given their indices
     * @param arr           The array containing values to 
     *                      be swapped
     * @param firstIndex    The index of one of the values
     *                      to be swapped
     * @param secondIndex   The index of the other value to
     *                      be swapped
     * @precondition        firstIndex < arr.length && 
     *                      secondIndex < arr.length
     * @postcondition       Values at firstIndex and secondIndex
     *                      are swapped
     */
    private void swap(Comparable[] arr, int firstIndex, int secondIndex)
    {
        Comparable temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
        display.update(); 
    }

    /**
     * Constructs a new Sorter object
     */
    public Sorter()
    {
        display = new SortDisplay(this);
    }

    /**
     * Gets the index of the smallest value in an array
     * If the smallest value occurs more than once in 
     * the array, the index of the last instance is 
     * returned
     * @param a             The array whose minimum value is to
     *                      be determined
     * @param startIndex    The index to start searching in the 
     *                      array
     */
    public int indexOfMin(Comparable[] a, int startIndex)
    {
        int lowestIndex = startIndex;
        Comparable lowestVal = a[startIndex];
        for (int i = startIndex + 1; i < a.length; i++)
        {
            if (a[i].compareTo(lowestVal) < 0)
            {
                lowestIndex = i;
                lowestVal = a[i];
            }
        }
        return lowestIndex;
    }

    /**
     * Sorts an array by traversing down the array
     * and swapping the smallest value ahead of
     * the current index - 1 with the value at 
     * the current index
     * @param a         The array to sort
     * @postcondition   array a is sorted
     */
    public void selectionSort(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            int minIndex = indexOfMin(a, i);
            swap(a, i, minIndex);
        }
    }

    /**
     * Takes in a partially-sorted array and adds
     * the next value in the array to the sorted
     * portion by moving it down the array until 
     * finding it's spot
     * @param a             The array containing the
     *                      value to move
     * @param nextIndex     The index of the value
     *                      to be added to the sorted
     *                      portion; should be the 
     *                      first index of the unsorted
     *                      portion
     * @precondition        a[0] to a[nextIndex-1] is 
     *                      sorted
     * @postcondition       a[0] to a[nextIndex] is 
     *                      sorted
     */
    public void insert(Comparable[] a, int nextIndex)
    {
        Comparable temp = a[nextIndex];
        int curr = nextIndex - 1;
        while (curr >= 0 && a[curr].compareTo(temp) > 0)
        {
            a[curr + 1] = a[curr];
            curr--;
            display.update(); 
        }
        a[curr + 1] = temp;
        display.update(); 
    }

    /**
     * Sorts an array by traversing down the array
     * and shifting the smallest element ahead to 
     * a sorted portion of the array
     * @param a         The array to be sorted
     * @postcondition   array a is sorted
     */
    public void insertionSort(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
        {
            insert(a, i);
        }
    }

    /**
     * Sorts an array by repeatedly halfing
     * it until only two or one elements are 
     * left in the array. When one or two elements
     * are left in the array, the values are 
     * swapped if they are out of order.
     * All the sub-arrays are then joined together
     * again to produce the sorted array a
     * @param a         The array to be sorted
     * @postcondition   a is sorted
     */
    public void mergesort(Comparable[] a)
    {
        mergesortHelp(a, 0, a.length - 1);
    }

    /**
     * Recursively sorts a portion of an array 
     * given its lower and upper bounds. Repeatedly
     * calls itself until the input sub-arrays are
     * one or two elements long. Once this base
     * case is reached, the values are swapped if
     * they are out of order. The sub-arrays are
     * then joined back together using the merge
     * method
     * @param a         The array containing the 
     *                  sub-array to be sorted
     * @param lowIndex  The smallest index of the
     *                  sub-array to be sorted
     * @param highIndex The largest index of the
     *                  sub-array to be sorted
     * @postcondition   All elements with indices
     *                  lowIndex to highIndex
     *                  are in ascending order
     */
    private void mergesortHelp(Comparable[] a, int lowIndex, int highIndex)
    {   
        int midIndex = (highIndex + lowIndex) / 2;
        if (Math.abs(lowIndex - highIndex) == 1 && a[lowIndex].compareTo(a[highIndex]) > 0)
        {
            swap(a, lowIndex, highIndex);
        }
        else if (lowIndex < highIndex)
        {
            mergesortHelp(a, lowIndex, midIndex);
            mergesortHelp(a, midIndex + 1, highIndex);
            merge(a, lowIndex, midIndex, highIndex);
        }
    }
    
    /**
    * Merges the two halves of the input array into one.  The method assumes
    * that each half of the input array is sorted as follows:
    *                a[lowIndex] to a[midIndex] are in increasing order.
    *                a[midIndex + 1] to a[highIndex] are in increasing order.
    * The method creates an array to hold the output.  It then establishes 
    * two pointers into the two halves of the input array.  The values at the
    * pointer locations are compared, and the smallest is added to the output
    * array.  The corresponding pointer is then increased by one.  In the event
    * either half becomes empty, the remaining values are copied to the output
    * array.
    * Postcondition: a[lowIndex] to a[highIndex] are in increasing order.
    *
    * @param a is the input array of Comparable values
    * @param lowIndex is the index into the array a corresponding to the beginning
    *        of the first half of the array to merge
    * @param midIndex is the index of the last value in the first half of the array
    * @param highIndex is the index of the last value in the second half of the array
    */
    private void merge(Comparable[] a, int lowIndex, int midIndex, int highIndex)
    {
        Comparable[] copy = new Comparable[a.length];
        for (int i = lowIndex; i <= highIndex; i++)
            copy[i] = a[i];
        int left = lowIndex;
        int right = midIndex + 1;
        for (int i = lowIndex; i <= highIndex; i++)
        {
            if (right > highIndex ||
                (left <= midIndex && copy[left].compareTo(copy[right]) < 0))
            {
                a[i] = copy[left];
                left++;
            }
            else
            {
                a[i] = copy[right];
                right++;
            }
            display.update();
        }
    }

    /**     
     * Method: quicksort()
     * Usage: sorter.quicksort(inputArray)
     * -------------------------------------
     * quicksort() does not actual do the sorting,
     * just calls quicksortHelp with parameters (a, 0, a.length-1),
     * which does the actual quick sorting
     * 
     * Postcondition: a[lowIndex] to a[highIndex] are in increasing order
     * @param a - array of comparable elements to be sorted with quick sort
     */
    public void quicksort(Comparable[] a)
    {
        /* To be implemented post the AP Exam */
    }

    /**
     * Method: quicksortHelp()
     * Usage: quicksortHelp(a, low, high)
     * ------------------------------------------
     * Quick soritng is a recursive sorting algorithm that sets a pivot point (lowIndex in this case)
     * and calls partition which rough sort: puts every element less than pivot left of pivot, and every element bigger than pivot right of pivot
     * then quicksortHelp is called on the sections left & right of the pivot point
     * 
     * Base case: section of the array given by low & highIndex has 1 element (high <= low), 
     *         which is "sorted" by definition, therefore nothing is done to it
     * Recursive reduction: the element at lowIndex is sorted as the pivot using partition() and the index where it lands is returned.
     *         The array is then divided from (low,pivot-1) & (pivot+1,high) because index pivot is already sorted 
     *         and quicksortHelp is used again on sections left & right of the pivot element
     * 
     * Postcondition: a[lowIndex] to a[highIndex] are in increasing order
     * @param a - array of comparable elements to be sorted with quick sort
     * @param lowIndex - beginning index of section of array to be sorted
     * @param highIndex - ending index of section of array to be sorted
     */
    private void quicksortHelp(Comparable[] a, int lowIndex, int highIndex)
    {   
        /* To be implemented post the AP Exam */
    }
    
    /**
    * Method partition
    * Usuage: int pivotIndex = partition(a, lowIndex, highIndex)
    *___________________________________________________________
    *
    *Returns the index of the pivot element defined as follows:
    *                All elements on the left side of the pivot (from lowIndex)
    *                are less than or equal to the pivot.
    *                All elements on the right side of the pivot (through highIndex)
    *                are greater than or equal to the pivot.
    * The computation is performed in place.
    * @param a the array to partion
    * @param lowIndex is the index of the start of the part of array a to consider
    * @param highIndex is the index of the end of the part of array a to consider
    * @return the index of the pivot element in array a
    */
    private int partition(Comparable[] a, int lowIndex, int highIndex)
    {
        /* To be implemented post the AP Exam */
        return -1;
    }
}