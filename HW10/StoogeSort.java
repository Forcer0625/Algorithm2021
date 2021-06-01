class StoogeSort
{
    //StoogeSort.sort(a,0,a.length-1);
    public static void sort(int[] a,int start,int end)
    {
        if(a[start]>a[end])
            swap(a,start,end);
        
        if(start+1>=end)
            return;
        
        int k=(end-start+1)/3;
        sort(a,start,end-k); 
        sort(a,start+k,end);
        sort(a,start,end-k);
    }
    private static void swap(int[] a,int i,int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}
