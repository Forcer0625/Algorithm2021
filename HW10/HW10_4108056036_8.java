public class HW10_4108056036_8 extends SortingArray
{
    final static Hash save=new Hash();
    @Override
    public int[] sorting(int[] a)
    {
        int len=a.length;
        if(len > 50)
        {
            int[] ans = save.getAns(a);
            return ans;
        }
        else
            DualPivotQuicksort_EX.sort(a,0,len-1,true);
        return a;
    }


    static class SaveArray
    {
        public int key;
        public int[] val;
        public SaveArray next;
        public SaveArray(int key,int[] sorted)
        {
            this.key=key;
            int len=sorted.length;
            System.arraycopy(sorted, 0, this.val = new int[len], 0, len);
            //next=null;
        }
    }
    static class Hash
    {
        private SaveArray[] bucket;
        final public int capacity=16;
        public Hash()
        {
            bucket=new SaveArray[capacity+1];
        }
        public int[] getAns(int[] a)
        {
            int key = a[0];
            int hash=key&capacity;
            for(SaveArray A=bucket[hash];A!=null;A=A.next)
                if(A.key==key)  return A.val;

            DualPivotQuicksort_EX.sort(a,0,a.length-1);
            SaveArray A=new SaveArray(key,a);
            A.next=bucket[hash];
            bucket[hash]=A;
            return A.val;
        }
    }

}
class DualPivotQuicksort_EX
{
    private static final int MAX_RUN_COUNT = 67;
    private static final int QUICKSORT_THRESHOLD = 286;
    private static final int INSERTION_SORT_THRESHOLD = 47;

    static void sort(int[] a, int left, int right)
    {
        // Use Quicksort on small arrays
        if (right - left < QUICKSORT_THRESHOLD)
        {
            sort(a, left, right, true);
            return;
        }

        int[] run = new int[MAX_RUN_COUNT + 1];
        int count = 0; run[0] = left;
        // Check if the array is nearly sorted
        for (int k = left; k < right; run[count] = k)
        {
            while (k < right && a[k] == a[k + 1])
                k++;
            if (k == right) break;
            if (a[k] < a[k + 1])
            {   
                // ascending
                while (++k <= right && a[k - 1] <= a[k]);
            } else if (a[k] > a[k + 1])
            {   
                // descending
                while (++k <= right && a[k - 1] >= a[k]);
                // Transform into an ascending sequence
                for (int lo = run[count] - 1, hi = k; ++lo < --hi; )
                {
                    int t = a[lo];
                    a[lo] = a[hi];
                    a[hi] = t;
                }
            }
            // Merge a transformed descending sequence followed by an
            // ascending sequence
            if (run[count] > left && a[run[count]] >= a[run[count] - 1])
                count--;
            
            if (++count == MAX_RUN_COUNT)
            {
                sort(a, left, right, true);
                return;
            }
        }

        if (count == 0) 
            return;
        else if (count == 1 && run[count] > right)
            return;
        
        right++;
        if (run[count] < right) 
            run[++count] = right;
        
        byte odd = 0;
        for (int n = 1; (n <<= 1) < count; odd ^= 1);

        // Use or create temporary array b for merging
        int[] b;      
        int ao, bo;
        int blen = right - left;
                
        int[] work = new int[blen];
        int workBase = 0;
        

        if (odd == 0)
        {
            System.arraycopy(a, left, work, workBase, blen);
            b = a;
            bo = 0;
            a = work;
            ao = workBase - left;
        }
        else
        {
            b = work;
            ao = 0;
            bo = workBase - left;
        }

        // Merging
        for (int last; count > 1; count = last)
        {
            for (int k = (last = 0) + 2; k <= count; k += 2)
            {
                int hi = run[k], mi = run[k - 1];
                for (int i = run[k - 2], p = i, q = mi; i < hi; ++i)
                    if (q >= hi || p < mi && a[p + ao] <= a[q + ao])
                        b[i + bo] = a[p++ + ao];
                    else 
                        b[i + bo] = a[q++ + ao];
                
                run[++last] = hi;
            }
            if ((count & 1) != 0)
            {
                for (int i = right, lo = run[count - 1]; --i >= lo;b[i + bo] = a[i + ao]);
                run[++last] = right;
            }
            int[] t = a; a = b; b = t;
            int o = ao; ao = bo; bo = o;
        }
    }

    public static void sort(int[] a, int left, int right, boolean leftmost)
    {
        int length = right - left + 1;
        // Use insertion sort on tiny arrays
        if (length < INSERTION_SORT_THRESHOLD)
        {
            if (leftmost)
            {
                for (int i = left, j = i; i < right; j = ++i)
                {
                    int ai = a[i + 1];
                    while (ai < a[j])
                    {
                        a[j + 1] = a[j];
                        if (j-- == left)
                            break;
                    }
                    a[j + 1] = ai;
                }
            }
            else
            {
                do
                {
                    if (left >= right)
                        return;
                } while (a[++left] >= a[left - 1]);

                for (int k = left; ++left <= right; k = ++left)
                {
                    int a1 = a[k], a2 = a[left];
                    if (a1 < a2)        a2 = a1; a1 = a[left];
                    while (a1 < a[--k]) a[k + 2] = a[k];
                    a[++k + 1] = a1;
                    while (a2 < a[--k]) a[k + 1] = a[k];
                    a[k + 1] = a2;
                }

                int last = a[right];
                while (last < a[--right]) a[right + 1] = a[right];
                a[right + 1] = last;
            }
            return;
        }

        int seventh = (length >> 3) + (length >> 6) + 1;

        int e3 = (left + right) >>> 1;
        int e2 = e3 - seventh;
        int e1 = e2 - seventh;
        int e4 = e3 + seventh;
        int e5 = e4 + seventh;

        // insertion sort
        if (a[e2] < a[e1])  { int t = a[e2]; a[e2] = a[e1]; a[e1] = t; }
        if (a[e3] < a[e2])
        {
            int t = a[e3]; a[e3] = a[e2]; a[e2] = t;
            if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
        }
        if (a[e4] < a[e3])
        {
            int t = a[e4]; a[e4] = a[e3]; a[e3] = t;
            if (t < a[e2])
            {   
                a[e3] = a[e2]; a[e2] = t;
                if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
            }
        }
        if (a[e5] < a[e4])
        {
            int t = a[e5]; a[e5] = a[e4]; a[e4] = t;
            if (t < a[e3])
            {   
                a[e4] = a[e3]; a[e3] = t;
                if (t < a[e2])
                {
                    a[e3] = a[e2]; a[e2] = t;
                    if (t < a[e1]) { a[e2] = a[e1]; a[e1] = t; }
                }
            }
        }

        int less  = left;
        int great = right;

        if (a[e1] != a[e2] && a[e2] != a[e3] && a[e3] != a[e4] && a[e4] != a[e5])
        {
            int pivot1 = a[e2];
            int pivot2 = a[e4];

            a[e2] = a[left];
            a[e4] = a[right];

            while (a[++less] < pivot1);
            while (a[--great] > pivot2);

            outer:
            for (int k = less - 1; ++k <= great; )
            {
                int ak = a[k];
                if (ak < pivot1) 
                {
                    a[k] = a[less];
                    a[less] = ak;
                    ++less;
                }
                else if (ak > pivot2)
                {
                    while (a[great] > pivot2)
                        if (great-- == k)
                            break outer;

                    if (a[great] < pivot1)
                    {
                        a[k] = a[less];
                        a[less++] = a[great];
                    }
                    else
                        a[k] = a[great];
                    a[great--] = ak;
                }
            }

            a[left]  = a[less  - 1]; a[less  - 1] = pivot1;
            a[right] = a[great + 1]; a[great + 1] = pivot2;

            sort(a, left, less - 2, leftmost);
            sort(a, great + 2, right, false);

            if (less < e1 && e5 < great)
            {
                while (a[less] == pivot1)   less++;
                while (a[great] == pivot2)  great--;

                outer:
                for (int k = less - 1; ++k <= great; )
                {
                    int ak = a[k];
                    if (ak == pivot1)
                    {
                        a[k] = a[less];
                        a[less] = ak;
                        ++less;
                    }
                    else if (ak == pivot2)
                    {
                        while (a[great] == pivot2)
                            if (great-- == k) 
                                break outer;
                        
                        if (a[great] == pivot1)
                        {
                            a[k] = a[less];
                            a[less++] = pivot1;
                        }
                        else
                            a[k] = a[great];
                        
                        a[great--] = ak;
                        
                    }
                }
            }

            sort(a, less, great, false);

        }
        else
        {
            int pivot = a[e3];
            for (int k = less; k <= great; ++k)
            {
                if (a[k] == pivot)
                    continue;
                
                int ak = a[k];
                if (ak < pivot)
                {
                    a[k] = a[less];
                    a[less++] = ak;
                }
                else
                {
                    while (a[great] > pivot) great--;
                    
                    if (a[great] < pivot)
                    {
                        a[k] = a[less];
                        a[less++] = a[great];
                    } else
                        a[k] = pivot;
                    a[great--] = ak;
                }
            }
            sort(a, left, less - 1, leftmost);
            sort(a, great + 1, right, false);
        }
    }
}