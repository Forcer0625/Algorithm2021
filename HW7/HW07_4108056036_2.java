public class HW07_4108056036_2 extends Buy_Phone
{
    //Original version by 41080560043
    @Override
    public int[][] bestPhone(int[][] inputArr){
        int len = inputArr.length;
        int[][] work_array = new int[len][2];
        int[][] temp = new int[len][2];
        Sort(inputArr,work_array,0,len--);
        int min=inputArr[len][1];
        temp[len]=inputArr[len];
        int count=len-1;
        for(int i=count;i!=-1;i--)
            if(inputArr[i][1]>min)
            {
                min=inputArr[i][1];
                temp[count--]=inputArr[i];
            }
        
        int[][] ans =new int[len=len-count][2];
        System.arraycopy(temp, 1+count, ans, 0, len);
        return ans;
    }
    private static void Sort(int[][] array, int[][] workArray, int start, int count) 
    { 
        if (count < 2) 
            return; 
        int mid=count>>1;
        Sort(array, workArray, start,mid); 
        Sort(array, workArray, start + mid, count - mid); 
        Merge(array, workArray, start,mid, start + mid, count - mid); 
    }  
    private static void Merge(int[][] array, int[][] workArray, int leftStart, int leftCount, int rightStart, int rightCount) 
    { 
        int i = leftStart, j = rightStart, leftBound = leftStart + leftCount, rightBound = rightStart + rightCount, index = leftStart; 
        while (i < leftBound || j < rightBound) 
        { 
            if (i < leftBound && j < rightBound) 
            { 
                if (array[j][0] < array[i][0]) 
                    workArray[index] = array[j++]; 
                else if(array[j][0] > array[i][0])
                    workArray[index] = array[i++];
                else
                    if(array[j][1] < array[i][1]) 
                        workArray[index] = array[j++]; 
                    else
                        workArray[index] = array[i++];
            } 
            else if (i < leftBound) 
                workArray[index] = array[i++]; 
            else
                workArray[index] = array[j++]; 
            ++index; 
        } 
        for (i = leftStart; i < index; ++i) 
            array[i] = workArray[i]; 
    }
}
