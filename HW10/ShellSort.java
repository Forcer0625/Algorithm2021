class ShellSort
{
    final static int[] gap_seq={19903198,8845866,3931496,1747331,7765915,345152,153401,68178,30301,13467,5985,2660,1182,525,233,103,46,20,9,4,1};
    //Shellsort.sort(arr,0,arr.length);
    final static void sort(int arr[],int from,int to)
    {
        int n = to;
        for (int gap : gap_seq)
        {
            for (int i = from+gap; i < n  ; i ++ )
            {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap+from && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = temp;
            }
        }
    }
}
