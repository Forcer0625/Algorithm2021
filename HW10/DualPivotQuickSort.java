class DualPivotQuickSort
{
    //DualPivotQuickSort.sort(a,0,a.length-1);
    public static void sort(int[] a,int start, int end)
    {
        if( start < end)
        { 
            int[] piv = partition(a, start, end);
            sort(a, start, piv[0] - 1);
            sort(a, piv[0] + 1, piv[1] - 1);
            sort(a, piv[1] + 1, end);
        }
    } 
    private static int[] partition(int[] a, int start, int end)
    { 
        if (a[start] > a[end])
            swap(a, start, end);

        int j = start + 1;
        int g = end - 1, k = start + 1,
        p = a[start], q = a[end];

        while (k <= g)
        { 
            if (a[k] < p)
                swap(a, k, j++);
            else if (a[k] >= q)
            {
                while (a[g] > q && k < g) g--; 
                swap(a, k, g--); 
                if (a[k] < p)
                    swap(a, k, j++);
            }
            k++;
        }

        swap(a, start, --j);
        swap(a, end, ++g);
        int[] dp={j,g};
        return dp;
    }
    private static void swap(int[] a,int i,int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}
