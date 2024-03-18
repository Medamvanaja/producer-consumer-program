import java.util.LinkedList;
public class Main 
{
    public static void main(String args[]) 
                         throws InterruptedException
    {
        final OddEven obj=new OddEven();
        Thread t1=new Thread(new Runnable()
        {
          public void run()
          {
              try 
              {
                  obj.odd();
              }
              catch(InterruptedException e)
              {
                  e.printStackTrace();
              }
          }
        });
        Thread t2=new Thread(new Runnable()
        {
            public void run()
            {
                try 
                {
                    obj.even();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
    }
    public static class OddEven
    {
        int number=1;
        boolean isEven=false;
        public void odd() throws InterruptedException
        {
            while(number<10)
            {
                synchronized(this)
                {
                    while(isEven)
                      wait();
                    System.out.println("producer are in odd:"+number);
                    number++;
                    isEven=true;
                    notify();
                    Thread.sleep(1000);
                }
            }
        }
        public void even() throws InterruptedException
        {
            while(number<=10)
            {
                synchronized(this)
                {
                    while(! isEven)
                    wait();
                    System.out.println("consumer are in even:"+number);
                    number++;
                    isEven=false;
                    notify();
                    Thread.sleep(1000);
                }
            }
        }
    }
}

