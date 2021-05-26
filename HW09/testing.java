public class testing
{

    public static void main(String[] args)
    {
        LSD compare=new HW09_4108056036_3();
        int[][] ex={{0,1},{0,2},{0,4},{1,3},{1,4},{2,5}};
        int[][] ex2={{1,3},{5,8},{6,2},{3,2}};
        LSD t=new HW09_4108056036_5();

        //System.out.println(Math.log(2));

        System.out.println(t.Distance(ex2)+"<=>"+compare.Distance(ex2));
        System.out.println(t.Distance(ex)+"<=>"+compare.Distance(ex));
        System.out.println(t.Distance(ex2)+"<=>"+compare.Distance(ex2));
        System.out.println(t.Distance(ex)+"<=>"+compare.Distance(ex));
        System.out.println(t.Distance(ex2)+"<=>"+compare.Distance(ex2));
        System.out.println(t.Distance(ex)+"<=>"+compare.Distance(ex));
        //System.out.println(Math.log(2));
        //System.out.println(t.Distance(ex2));
        //System.out.println(t.Distance(ex));
        //System.out.println(t.Distance(ex2));
        //System.out.println(t.Distance(ex));
    }
}
