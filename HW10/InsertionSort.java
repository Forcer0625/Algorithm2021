class InsertionSort
{
    //InsertionSort.sort(a, 0, a.length);
    public static void sort(int[] a,int start, int end)
    {
        for(int i=1+start; i<end; i++)
            for(int j=i-1; j >= start && (a[j] > a[j+1]); j--)
                swap(a,j,j+1);
    }
    private static void swap(int[] a,int i,int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}
