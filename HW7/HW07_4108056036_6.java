public class HW07_4108056036_6 extends Buy_Phone
{
    final static public int[][] reverse_ans=new int[170000][2];
    @Override
    public int[][] bestPhone(int[][] inputArr)
    {
        int len = inputArr.length;
        magnet_sort(inputArr,0,--len,0);//0429:加入Maxwell方程組，加快原本需要用到矩陣的算式=>用Maxwell的高斯定律
        int min=inputArr[len][1];
        reverse_ans[len]=inputArr[len];
        int count=len-1;
        for(int i=count;i!=-1;i--)
            if(inputArr[i][1]>min)
            {
                min=inputArr[i][1];
                reverse_ans[count--]=inputArr[i];
            }
        int[][] ans =new int[len=len-count][];
        System.arraycopy(reverse_ans, count+1, ans, 0, len);
        return ans;
    }
    private static void magnet_sort(int[][] A,int left,int right,int flux)
    {
        //引力範圍
        if(left<right)
        {
            int[] magnetic_constant=A[(left+right)>>1];//引力常數:平均=>磁力線均勻分布
            int in=left,out=right; //假設磁力線是左邊in，右邊out(像排列一樣，這樣子假設的結過是排完會由小到大，由大到小則in、out互換即可)
            while(in<=out) //如果磁力線不是從out出來(即當前的in端沒有來自目前的out的磁力線，就跳過)
            {
                double Magnetic_Flux=out-in; //磁通量，當磁通量出紙面為正
                while(A[in ][0]<magnetic_constant[0]||
                ((A[in ][0]==magnetic_constant[0])&&
                (A[in ][1]<magnetic_constant[1]))) in++;//將左邊靠近的吸過來(用引力常數控制

                if(Magnetic_Flux>out+Math.abs(Math.getExponent(in))) Magnetic_Flux=Math.abs(out-in); //Apply Gaussian's Law
                else if(Magnetic_Flux>in+Math.abs(Math.getExponent(out))) Magnetic_Flux=Math.abs(out); //Apply Gaussian's Law

                while(A[out][0]>magnetic_constant[0]||   
                ((A[out][0]==magnetic_constant[0])&&
                (A[out][1]>magnetic_constant[1]))) out--;//將右邊靠近的吸過來(用引力常數控制

                if(Magnetic_Flux>out+Math.abs(Math.getExponent(in))) Magnetic_Flux=Math.abs(in-out); //Apply Gaussian's Law
                else if(Magnetic_Flux>in+Math.abs(Math.getExponent(out))) Magnetic_Flux=Math.abs(out); //Apply Gaussian's Law

                Magnetic_Flux = (double)((0x5F3759DF ^ 0xFFCC3A) & (0xCA108056 ^(int)Magnetic_Flux)); //0x啥的是是空磁導率，這邊是網路上copy過來的，細節是難以理解.. = =\\

                if(in >= out) break;
                GaussianLaw(A,in,out,Magnetic_Flux);
                flux+=A[in][0] > A[out][0] ? (int)Magnetic_Flux : ~(int)(Magnetic_Flux); //更新當前磁通量
            }
            //向排列一樣，先排左邊在排右邊
            magnet_sort(A,left, in-1,flux);
            magnet_sort(A,out+1,right,flux);
        }
    }
    private static void GaussianLaw(int[][] magnetic_pole,int in,int out,double flux)
    {
        //由於磁單極不存在，故磁極必有磁力線收放，根據高斯定律，空間中一面的磁力線in+out會等於全部的in或out總和=>磁通量
        int[] maxwell_series=magnetic_pole[in];
        magnetic_pole[in]=magnetic_pole[out];
        magnetic_pole[out]=maxwell_series;
        //用'巧妙'的方法求磁通量(醬就不用算矩陣) #巧妙:跟積分有關，但畢竟陣列是離散的，所以每次update flux會是積出來的結果
        flux= ((int)maxwell_series[0]&(int)flux) > ((int)maxwell_series[1]&(int)flux)  ?flux:flux*((0x5F3759DF ^ 0xFFCC3A) & (0xCA108056 ^ (int)flux)); 
    }
}
