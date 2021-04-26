public class testing
{
    public static void main(String[] args)
    {
        int[][] ex={{7,3},{5,8},{3,5},{2,8},{3,4},{9,6}};
        Buy_Phone t=new  HW07_4108056036_4();
        check(t.bestPhone(ex));
        System.out.println("\n");
        check(ex);
        
    }
    public static void check(int [][] input)
    {
        for(int i=0;i<input.length;i++)
        {
            System.out.println(input[i][0]+" "+input[i][1]);
        }
    }
}
