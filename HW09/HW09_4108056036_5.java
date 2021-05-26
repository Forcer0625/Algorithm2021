public class HW09_4108056036_5 extends LSD
{
    //public static int  FAcount;
    //final static FArraylist[] FArraylists=new FArraylist[320000];
    @Override
    public int Distance(int[][] inputArr)
    {
        //FAcount=0;
        int ans=0;
        int len=inputArr.length+1;
        int maxDepthNode=0;

        FQueue queue=new FQueue(len);
        FSet find=new FSet(len);
        FHash edge=new FHash(len<<1);
        for(int[] v : inputArr) edge.put(v[0],v[1]);


        int first=edge.maxDegreeVertex;
        find.put(first,1);
        queue.enqueue(first);
        while(!queue.empty())
        {
            first=queue.dequeue();
            int self_depth=find.get(first)+1;
            if(self_depth>ans)
            {
                    ans=self_depth;
                    maxDepthNode=first;
            }
            /*FArraylist arr=edge.get(first);
            for(arr.read();arr.hasNext();)
            {*/
                //int n=arr.next();
            FArraylist arr=edge.get(first);
            for(int i=arr.len;i!=-1;i--)
            {
                int n=arr.elem[i];
                if(!find.contain(n))
                {
                    find.put(n, self_depth);
                    queue.enqueue(n);
                }
            }
            /*}*/
        }
        int sec=maxDepthNode;
        find.sec_put(sec, 1);
        queue.enqueue(sec);
        while(!queue.empty())
        {
            sec=queue.dequeue();
            int self_depth=find.sec_get(sec)+1;
            if(self_depth>ans)
                ans=self_depth;
            FArraylist arr=edge.get(sec);
            for(arr.read();arr.hasNext();)
            {
                int n=arr.next();
                if(!find.sec_contain(n))
                {
                    find.sec_put(n, self_depth);
                    queue.enqueue(n);
                }
            }
            
        }

        return ans-2;
    }
    class FSet
    {
        private int _cap;
        private Element[] list;

        FSet(int cap)
        {
            this._cap=(1<<(int)(Math.log(cap)/0.7+1));
            this.list=new Element[this._cap--];
        }

        class Element
        {
            int key;
            int val;
            Element next;
            boolean flag;
        }

        void put(int k, int v)
        {
            Element newE=new Element();
            newE.flag=false;
            newE.key=k;
            newE.val=v;
            newE.next=list[k&_cap];
            list[k&_cap]=newE;
        }
        void sec_put(int k,int v)
        {
            for(Element e=list[k&_cap];e!=null;e=e.next)
                if(e.key==k)
                {
                    e.flag=true;
                    e.val=v;
                    return;
                }
            Element newE=new Element();
            newE.flag=true;
            newE.key=k;
            newE.val=v;
            newE.next=list[k&_cap];
            list[k&_cap]=newE;
        }
        int get(int n)
        {
            for(Element count=list[n&_cap];count!=null;count=count.next)
                if(count.key == n) return count.val;
            return 0;
        }
        int sec_get(int n)
        {
            for(Element count=list[n&_cap];count!=null;count=count.next)
                if(count.key == n && count.flag) return count.val;
            
            return 0;
        }

        boolean contain(int n)
        {
            for(Element c=list[n&_cap]; c!=null; c=c.next)
                if(c.key==n) return true;
            return false;
        }
        boolean sec_contain(int n)
        {
            for(Element c=list[n&_cap]; c!=null; c=c.next)
                if(c.key==n && c.flag) return true;
            return false;
        }
    }
    class Vertex
    {
        int key;
        FArraylist link;
        Vertex next;
        int degree;
        public Vertex(int v)
        {
            key=v;
            link=new FArraylist();
            next=null;
            degree=0;
        }
    }
    class FHash
    {
        public int capacity;
        private Vertex[] bucket;
        public  int maxDegree;
        public  int maxDegreeVertex;
        public FHash(int cap)
        {
            //double c=Math.log(cap)/0.3010d;
            capacity=(1<<(int)(Math.log(cap)/0.7+1 /*log2+1*/));
            bucket=new Vertex[capacity--];
            maxDegree=-1;
            maxDegreeVertex=-1;
        }
        final public void put(int v,int u)
        {
            append(v, u);
            append(u, v);
        }
        final public void append(int v,int u)
        {
            int hash=v&capacity;
            for(Vertex V=bucket[hash];V!=null;V=V.next)
            if(V.key==v)
            {
                if(++V.degree>maxDegree)
                {
                    maxDegree=V.degree;
                    maxDegreeVertex=v;
                }
                V.link.add(u);
                return;
            }

            Vertex V=new Vertex(v);
            V.link.add(u);
            V.next=bucket[hash];
            bucket[hash]=V;
        }

        final FArraylist get(int v)
        {
            for(Vertex V=bucket[v&capacity];V!=null;V=V.next)
                if(V.key==v) return V.link;
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