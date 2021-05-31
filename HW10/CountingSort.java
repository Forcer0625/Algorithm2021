class CountingSort
{
    //CountingSort.sort(a,0,a.length);
    public static void sort(int[] a, int start, int end)
    {
        
        int[] count = new int[ findMax(a)+1 ];
        for (int i=0; i < end; ++i)
            count[ a[i] ]++;

        for (int i=0, k=0; k < end; ++i)
            while (count[i]-- != 0 )
                a[k++] = i;
    }
    private static int findMax(int[] a)
    {
        int max=Integer.MIN_VALUE;
        for(int compare : a)
            max = max < compare ? compare : max;
        return max;
    }
}
