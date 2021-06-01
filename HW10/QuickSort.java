class QuickSort
{
    //QuickSort.sort(a,0,a.length-1);
    public static void sort(int[] a, int start, int end)    
    {
        if(!(start < end)) return;
        int q = partition(a,start,end); 
        sort(a,start, q-1);
        sort(a,q+1, end);
    }
    private static int partition(int[] a, int start, int end)
    {
        int i=start-1;
        for(int j=start;j<end;j++) 
            if(a[j] <= a[end])
                swap(a,++i,j);
        swap(a,++i,end);
        return i;
    }
    private static void swap(int[] a,int i,int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}
