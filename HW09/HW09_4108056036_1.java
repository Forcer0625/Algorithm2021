public class HW09_4108056036_1 extends LSD
{
    //8191 16383
    final FQueue queue=new FQueue(8191,0);
    final FHash edge=new FHash(16384,0);
    static int num=0;
    @Override
    public int Distance(int[][] inputArr)
    {
        int ans=0;
        //int len=inputArr.length+1;
        int maxDepthNode=0;
        int thisnum=num;
        //FQueue queue=new FQueue(len);
        //FHash edge=new FHash(len<<1);
        for(int[] v : inputArr) edge.put(v[0],v[1],thisnum);


        int first=edge.maxDegreeVertex;
        edge.Setput1(first, 1);//find.put(first,1);
        queue.enqueue(first);
        while(!queue.empty())
        {
            first=queue.dequeue();
            Vertex v=edge.getV(first);
            int self_depth=v.val+1;
            if(self_depth>maxDepthNode)
                maxDepthNode=first;
            
            FArraylist arr=v.link;//edge.get(first);
            for(arr.read();arr.hasNext();)
            {
                int n=arr.next();
                if(!edge.contain(n,thisnum))
                {
                    edge.Setput1(n, self_depth);
                    queue.enqueue(n);
                }
            }
        }
        int sec=maxDepthNode;
        edge.Setput2(sec, 1);
        queue.enqueue(sec);
        while(!queue.empty())
        {
            sec=queue.dequeue();
            Vertex v=edge.getV(sec);
            int self_depth=v.val+1;//edge.Setget2(sec)+1;//find.sec_get(sec)+1;
            if(self_depth>ans)
                ans=self_depth;
            FArraylist arr=v.link;
            for(arr.read();arr.hasNext();)
            {
                int n=arr.next();
                if(!edge.contain2(n,thisnum))
                {
                    edge.Setput2(n, self_depth);
                    queue.enqueue(n);
                }
            }
            
        }
        num++;
        return ans-2;
    }
    class Vertex
    {
        int key;
        FArraylist link;
        Vertex next;
        int degree;
        boolean flag;
        int val;
        int count;
        //int find;

        public Vertex(int v,int num)
        {
            key=v;
            link=new FArraylist();
            next=null;
            degree=0;
            val=-1;
            count=num;
        }
        void reset(int num)
        {
            link=new FArraylist();
            degree=0;
            val=-1;
            count=num;
        }
        /*public Vertex(int v,int find)
        {
            key=v;
            link=new FArraylist();
            next=null;
            degree=0;
            this.find=find;
        }*/
    }
    class FHash
    {
        public int capacity;
        private Vertex[] bucket;
        public  int maxDegree;
        public  int maxDegreeVertex;
        public FHash(int cap)
        {
            capacity=(1<<(int)(Math.log(cap)/0.7+1 /*log2+1*/));
            bucket=new Vertex[capacity--];
            maxDegree=-1;
            maxDegreeVertex=-1;
        }
        public FHash(int cap,int i)
        {
            capacity=cap;
            bucket=new Vertex[capacity--];
            maxDegree=-1;
            maxDegreeVertex=-1;
        }
        final public void put(int v,int u,int num)
        {
            append(v, u,num);
            append(u, v,num);
        }
        final public void append(int v,int u,int num)
        {
            int hash=v&capacity;
            for(Vertex V=bucket[hash];V!=null;V=V.next)
                if(V.key==v)
                {   
                    if(V.count!=num)
                        V.reset(num);
                    if(++V.degree>maxDegree)
                    {
                        maxDegree=V.degree;
                        maxDegreeVertex=v;
                    }
                    V.link.add(u);
                    return;
                }

            Vertex V=new Vertex(v,num);
            V.link.add(u);
            V.next=bucket[hash];
            bucket[hash]=V;
        }

        final FArraylist get(int v,int num)
        {
            for(Vertex V=bucket[v&capacity];V!=null;V=V.next)
                if(V.key==v && V.count==num) return V.link;
            return null;
        }
        void Setput1(int k,int v)
        {
            for(Vertex V=bucket[k&capacity];V!=null;V=V.next)
                if(V.key==k)
                {
                    V.val=v;
                    V.flag=false;
                }
        }
        void Setput2(int k,int v)
        {
            for(Vertex V=bucket[k&capacity];V!=null;V=V.next)
                if(V.key==k)
                {
                    V.val=v;
                    V.flag=true;
                    return;
                }
        }
        int Setget(int n)
        {
            for(Vertex V=bucket[n&capacity];V!=null;V=V.next)
                if(V.key==n)    return V.val;
            return 0;
        }
        final int Setget2(int n)
        {
            for(Vertex V=bucket[n&capacity];V!=null;V=V.next)
                if(V.key==n && V.flag)    return V.val;
            return 0;
        }
        final boolean contain(int n,int num)
        {
            for(Vertex V=bucket[n&capacity];V!=null;V=V.next)
                if(V.key==n && V.val!=-1 &&V.count==num)    return true;
            return false;
        }
        final boolean contain2(int n,int num)
        {
            for(Vertex V=bucket[n&capacity];V!=null;V=V.next)
                if(V.key==n && V.flag && V.count==num)    return true;
            return false;
        }
        Vertex getV(int v)
        {
            for(Vertex V=bucket[v&capacity];V!=null;V=V.next)
                if(V.key==v && V.val!=-1 && V.count==num)    return V;
            return null;
        }
    }
    class FArraylist
    {
        private int cap;
        private int len;
        private int[] elem;
        private int pointer;

        FArraylist()
        {
            this.cap=32;
            this.len=-1;
            this.elem=new int[cap];
        }

        public final void add(int n)
        {
            if(++this.len!=this.cap)
                this.elem[this.len]=n;
            else
            {
                this.cap<<=1;
                int[] newElem=new int[this.cap];
                System.arraycopy(elem, 0, newElem, 0, len);
                newElem[this.len]=n;
                this.elem=newElem;
            }
        }

        public final void read()
        {
            this.pointer=0;
        }

        public final boolean hasNext()
        {
            return pointer<=len;
        }

        public final int next()
        {
            return elem[pointer++];
        }
    }
    class FQueue
    {
        private int _cap;
        private int head;
        private int tail;
        private int[] list;

        FQueue(int cap)
        {
            this._cap=(1<<(int)(Math.log(cap)/Math.log(2)+1))-1;
            list=new int[_cap+1];
            head=0; 
            tail=0;
        }
        FQueue(int cap,int i)
        {
            this._cap=cap;
            list=new int[_cap+1];
            head=0; 
            tail=0;
        }

        public final void enqueue(int n)
        {
            list[tail]=n;
            tail=(tail+1)&_cap;
        }

        public final int dequeue()
        {
            int ret=list[head];
            head=(head+1)&_cap;
            return ret;
        }

        public final boolean empty()
        {
            return head==tail;
        }
    }
}