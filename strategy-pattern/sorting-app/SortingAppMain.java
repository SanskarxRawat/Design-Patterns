import java.util.Arrays;

// Strategy Interface
interface SortingStrategy {
    void sort(int[] numbers);
}

// Concrete Strategy Classes
class QuickSort implements SortingStrategy {
    @Override
    public void sort(int[] numbers) {
        System.out.println("Sorting using QuickSort");
        quickSort(numbers, 0, numbers.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}

class MergeSort implements SortingStrategy {
    @Override
    public void sort(int[] numbers) {
        System.out.println("Sorting using MergeSort");
        mergeSort(numbers, 0, numbers.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        int[] L = new int[n1];
        int[] R = new int[n2];
        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, middle + 1, R, 0, n2);
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }
}

class BubbleSort implements SortingStrategy {
    @Override
    public void sort(int[] numbers) {
        System.out.println("Sorting using BubbleSort");
        int n = numbers.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }
}

// Context Class
class SortingApp {
    private SortingStrategy sortingStrategy;

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void sort(int[] numbers) {
        if (sortingStrategy == null) {
            System.out.println("No sorting strategy set");
        } else {
            sortingStrategy.sort(numbers);
        }
    }
}

// Client Code
public class SortingAppMain {
    public static void main(String[] args) {
        SortingApp sortingApp = new SortingApp();
        int[] numbers = {5, 2, 9, 1, 5, 6};

        // Apply QuickSort
        sortingApp.setSortingStrategy(new QuickSort());
        sortingApp.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));

        // Apply MergeSort
        numbers = new int[]{5, 2, 9, 1, 5, 6};
        sortingApp.setSortingStrategy(new MergeSort());
        sortingApp.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));

        // Apply BubbleSort
        numbers = new int[]{5, 2, 9, 1, 5, 6};
        sortingApp.setSortingStrategy(new BubbleSort());
        sortingApp.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));
    }
}

/**
Explanation:
1. `SortingStrategy` is the strategy interface that defines the method `sort()` for sorting algorithms.
2. `QuickSort`, `MergeSort`, and `BubbleSort` are concrete strategy classes that implement different sorting algorithms.
3. `SortingApp` is the context class that maintains a reference to a `SortingStrategy` and uses it to sort an array.
4. `SortingAppMain` is the client that sets different sorting strategies and sorts an array using those strategies.
*/
