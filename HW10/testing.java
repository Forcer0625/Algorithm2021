import java.util.Random;
public class testing
{
    final static int RUNNING_TIME=100;
    final static int LENGTH=1000;
    final static double dup_rate=0.9;       //rate of duplicate elements
    final static double dupkey_chande=0.01;  //rate of different duplicate elements
    public static void main(String[] args)
    {
        SortingArray t=new HW10_4108056036_1();
        long sum=0;
        for(int i=0;i<RUNNING_TIME;i++)
        {
            
            int[] a=shuffle(create(LENGTH));
            long start=System.nanoTime();
            t.sorting(a);
            sum+=(System.nanoTime()-start);
            if(!check(a))   
            {
                System.out.println(false);
                break;
            }

        }
        System.out.println("=> "+ (sum/RUNNING_TIME) );
    }
    public static int[] create(int length)
    {
        int[] a=new int[length];
        int keep=0;
        for(int i=0;i<length;i++)
            a[i]=
            (Math.random() > dup_rate)
            ?i
            :(Math.random() > dupkey_chande)?keep:keep++;
        return a;
    }
    public static boolean check(int[] a)
    {
        int len=a.length;
        for(int i=1;i<len;i++)
            if(a[i-1]>a[i])
                return false;
        return true;
    }
    public static int[] shuffle(int[] solutionArr)
    {
        int ranIndex=0;
        int len=solutionArr.length;
        Random random = new Random();
        for (int i = 0; i < len; i++)
        {
            ranIndex = random.nextInt(len);
            swap(solutionArr, ranIndex, i);
        }
        return solutionArr;
    }
    public static void swap(int[] a,int i,int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}
