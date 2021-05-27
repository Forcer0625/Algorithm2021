public class HW10_4108056036_5 extends SortingArray
{
    final static int[] gap_seq={19903198,8845866,3931496,1747331,7765915,345152,153401,68178,30301,13467,5985,2660,1182,525,233,103,46,20,9,4,1};
    public int[] sorting(int[] inputArr)
    {
        ShellSort(inputArr);
        return inputArr;
    }

    final static void ShellSort(int arr[])
    {
        int n = arr.length;
        for (int gap : gap_seq)
        {
            for (int i = gap; i < n; i ++ )
            {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = temp;
            }
        }
    }
}
