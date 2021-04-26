public class HW07_4108056036_3 extends Buy_Phone
{
    //two one-dimension array
    final static public int[][] work_array=new int[200000][2];
    final static public int[] SCREEN_SIZE=new int[200000];
    final static public int[] PERFORMANCE=new int[200000];
    @Override
    public int[][] bestPhone(int[][] inputArr)
    {
        int len = inputArr.length;
        Sort(inputArr,0,len--);
        int min=PERFORMANCE[len]=inputArr[len][1];
        SCREEN_SIZE[len]=inputArr[len][0];
        int count=len-1;
        for(int i=count;i!=-1;i--)
            if(inputArr[i][1]>min)
            { 
                SCREEN_SIZE[count]=inputArr[i][0];
                PERFORMANCE[count--]=min=inputArr[i][1];
            }
        int[][] ans =new int[len-count][2];
        count++;
        for(int i=0,lim=len-count+1;i!=lim;i++,count++)
        {
            ans[i][0]=SCREEN_SIZE[count];
            ans[i][1]=PERFORMANCE[count];
        }
        return ans;
    }
    private static void Sort(int[][] array,int start, int count) 
    { 
        if (count < 2) 
            return;
        int mid=count>>1;
        Sort(array, start,mid); 
        Sort(array, start + mid, count - mid); 
        Merge(array, start, mid, start + mid, count - mid); 
    } 
    private static void Merge(int[][] array, int leftStart, int leftCount, int rightStart, int rightCount) 
    { 
        int i = leftStart, j = rightStart, leftBound = leftStart + leftCount, rightBound = rightStart + rightCount, index = leftStart; 
        while (i < leftBound || j < rightBound) 
        { 
            if (i < leftBound && j < rightBound) 
            { 
                if (array[j][0] < array[i][0]) 
                    work_array[index] = array[j++]; 
                else if(array[j][0] > array[i][0])
                    work_array[index] = array[i++];
                else
                    if(array[j][1] < array[i][1]) 
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
