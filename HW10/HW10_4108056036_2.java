public class HW10_4108056036_2 extends SortingArray
{
    final static int[] work_array=new int[200000];
    public int[] sorting(int[] inputArr)
    {
        Sort(inputArr, 0, inputArr.length);
        return inputArr;
    }

    private static void Sort(int[] array,int start, int count) 
    { 
        if (count < 2) 
            return;
        int mid=count>>1;
        Sort(array, start,mid); 
        Sort(array, start + mid, count - mid); 
        Merge(array, start, mid, start + mid, count - mid); 
        //Reverse_Merge(array, start, count >> 1, start + (count >> 1), count - (count >> 1)); 
    } 
    private static void Merge(int[] array, int leftStart, int leftCount, int rightStart, int rightCount) 
    { 
        int i = leftStart, j = rightStart, leftBound = leftStart + leftCount, rightBound = rightStart + rightCount, index = leftStart; 
        while (i < leftBound || j < rightBound) 
        { 
            if (i < leftBound && j < rightBound) 
            { 
                if(array[j] < array[i]) 
                    work_array[index] = array[j++]; 
                else
                    work_array[index] = array[i++];
            } 
            else if (i < leftBound) 
                work_array[index] = array[i++]; 
            else
                work_array[index] = array[j++]; 
            ++index; 
        } 
        for (i = leftStart; i < index; ++i) 
            array[i] = work_array[i]; 
    }
}
