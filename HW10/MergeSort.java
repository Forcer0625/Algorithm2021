class MergeSort
{
    private static int[] aux;
    //Mergesort.sort(a,0,a.length);
    public static void sort(int[] a, int start, int end)
    {
        aux = new int[a.length];
        divide(a, start, end);
    }
    private static void merge(int[] a,int start,int mid, int end)
    {
        if(a[mid-1] <= a[mid]) return;

        int i = start;
        int j = mid;

        System.arraycopy(a, i, aux, i, end-i);
        
        while(i!=mid && j!=end) a[start++] = (aux[i] < aux[j])? aux[i++] : aux[j++];
        System.arraycopy(aux, i, a, start       , mid-i);
        System.arraycopy(aux, j, a, start+mid-i , end-j);
    }   
    private static void divide(int[] a,int start ,int end)
    {
        if(end - start < 2)
            return;
        int mid = (start+end) >> 1;
        divide(a,start, mid);
        divide(a,mid, end);
        merge(a,start, mid, end);
    }
}
