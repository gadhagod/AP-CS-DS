import java.util.Arrays;

public class MyLocationTester {
    private static void printArr(MyLocation[] arr)
    {
        System.out.println("  " + Arrays.toString(arr));
    }

    private static int generateRandCoord()
    {
        return (int) (Math.random() * 6);
    }

    public static void main(String[] args) 
    {
        MyLocation[] locations = new MyLocation[10];
        for (int i = 0; i < 10; i++)
        {
            locations[i] = new MyLocation(generateRandCoord(), generateRandCoord());
        }
        System.out.println("Unsorted array of locations:");
        printArr(locations);

        System.out.println("\nWe will now use selection sort to test that indexOfMin works\n");

        (new Sorter()).insertionSort(locations);
        System.out.println("Sorted array of locations:");
        printArr(locations);

        System.out.println("\n If the above array is sorted, then indexOfMin and MyLocation are written correctly");
    }
}
