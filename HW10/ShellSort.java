class ShellSort
{
    final private static int[] gap_seq={19903198,8845866,3931496,1747331,7765915,345152,153401,68178,30301,13467,5985,2660,1182,525,233,103,46,20,9,4,1};
    //Shellsort.sort(a,0,a.length);
    public static void sort(int a[],int start,int end)
    {
        for (int gap : gap_seq)
        {
            for (int i = start+gap ; i < end  ; i ++ )
            {
                int temp = a[i];
                int j;
                for (j = i; j >= gap+start && a[j - gap] > temp; j -= gap)
                    a[j] = a[j - gap];
                a[j] = temp;
            }
        }
    }
}
