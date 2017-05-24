import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;




public class ClientLW extends Thread {
	static int thread_count;
static	Queue<String> tq = new ConcurrentLinkedQueue<String>();
static	Queue<String> rq = new ConcurrentLinkedQueue<String>();
	public static boolean flag=true;
	static int nooftask;
	static String re;
	 static File fs1= new File("WORKLOAD_FILE");
	private static class MessageLoop  extends ClientLW  {
		//running the client 
		int id=0;
		String readerInput;
		//running threads
		public void run() {
			if(flag=true){
				FileReader fs;
				int i=0;
				try {
					fs = new FileReader("WORKLOAD_FILE");
					BufferedReader bufferedReader = new java.io.BufferedReader(fs);
					readerInput = bufferedReader.readLine();
				//	while(readerInput!= null){
						//to add task
						for( i=1;i<=nooftask;i++)
						{
							id++;
							tq.add(readerInput+":"+id);
							System.out.println(id+" "+nooftask+" "+i+" "+tq);
							System.out.println("Hi");
						}
					}
					//}
				 catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			else{
				
				 while(( re=rq.poll())!=null)
				 {
					 System.out.println(re);
				 }
			}
		}
	}
private static class Message  extends MessageLoop  {
	String t="";
	//running threads
		public void run(){
			System.out.println(tq);
			while((t=tq.poll())!=null)
			{
			long sleepTime=Integer.parseInt(t.substring(6,t.indexOf(":")));
			try{
			Thread.currentThread().sleep(sleepTime);
			} catch(Exception ex){
			ex.printStackTrace();
			}

			rq.add(0+t);
			System.out.println(0+" "+t);
			}
			}
		

		
	}
	public static void main(String[] args) throws InterruptedException
	{
		
		Scanner sc2= new Scanner(System.in);
		System.out.println("Enter the number of tasks to be performed ");
		 nooftask= sc2.nextInt();
		  Scanner sc = new Scanner(System.in);
		   	System.out.println	("\n\n********* Client Executing *********\n\n");
		   	System.out.println("\n Enter no. of threads (1 or 2 or 4 or 8 or 16) : \n");
		   	thread_count=sc.nextInt();
System.out.println("client -s LOCAL -t "+thread_count+" -"+nooftask+" "+fs1);
		   	/*------For a Single threads -------*/
		   	if(thread_count == 1)
		   	{
		   		/* Thread initialisation  and starting the thread */
		   		Thread c1 = new Thread(new MessageLoop());
		   		Thread w1 = new Thread(new Message());
		   		long startTime =  System.currentTimeMillis();
		   		c1.start();
		   		/*To terminate the thread  */
		   		c1.join();
		   		w1.start();
		   		w1.join();
		   		flag=false;
		   		Thread rc= new Thread(new MessageLoop());
		   		rc.start();
		   		rc.join();
		   		long end_time =  System.currentTimeMillis();
		   		double total_time =  (end_time - startTime)/1000;
		   		//System.out.println(total_time);
		   		double RTT= total_time;
		   		System.out.println("Result Queue is "+rq);
		   		System.out.println("Worker Execution time"+total_time);
		   		//System.out.println("Latency = "+RTT*1000+"ms");//calculates the return time
		   		//System.out.println("Throughput = "+((8*BYTE/RTT)/(1024*1024))+" Mb/sec");
		   		//System.out.println("\n Data received successful!!!!\n\n");
		   	}
		   	/*------For a Two threads -------*/
		   	else if(thread_count == 2)
		   	{
		   		/* Thread initialisation  and starting the thread */
		   		Thread c1 = new Thread(new MessageLoop());
		   		Thread w1 = new Thread(new Message());
		   		Thread w2 = new Thread(new Message());
		   		double startTime1 = System.currentTimeMillis();
		   		c1.start();	
		   		c1.join();
		   		w1.start();
		   		
		   		w2.start();
		   		/*To terminate the thread  */
		   		
		   		w1.join();
		   		w2.join();
		   		Thread rc= new Thread(new MessageLoop());
		   		rc.start();
		   		rc.join();
		   		double end_time1 =  System.currentTimeMillis();
		   		double total_time1 = (end_time1 - startTime1)/1000;
		   		System.out.println("Result Queue is "+rq);
		   		System.out.println("Worker Execution time"+total_time1);
		   		//double RTT= total_time1/thread_count;
		   		
			
		   	}
		   	else if(thread_count == 4)
		   	{
		   		/* Thread initialisation  and starting the thread */
		   		Thread c1 = new Thread(new MessageLoop());
		   		Thread w1 = new Thread(new Message());
		   		Thread w2 = new Thread(new Message());
		   		Thread w3 = new Thread(new Message());
		   		Thread w4 = new Thread(new Message());
		   		double startTime1 = System.currentTimeMillis();
		   		c1.start();
		   		c1.join();
		   		w1.start();
		   		w2.start();
		   		w3.start();
		   		w4.start();
		   		/*To terminate the thread  */
		   		
		   		w1.join();
		   		w2.join();
		   		w3.join();
		   		w4.join();
		   		Thread rc= new Thread(new MessageLoop());
		   		rc.start();
		   		rc.join();
		   		double end_time1 =  System.currentTimeMillis();
		   		double total_time1 = (end_time1 - startTime1)/1000;
		   		System.out.println("Result Queue is "+rq);
		   		System.out.println("Worker Execution time"+total_time1);
		   		//double RTT= total_time1/thread_count;
		   		
			
		   	}
		   	else if(thread_count == 8)
		   	{
		   		/* Thread initialisation  and starting the thread */
		   		Thread c1 = new Thread(new MessageLoop());
		   		Thread w1 = new Thread(new Message());
		   		Thread w2 = new Thread(new Message());
		   		Thread w3 = new Thread(new Message());
		   		Thread w4 = new Thread(new Message());
		   		Thread w5 = new Thread(new Message());
		   		Thread w6 = new Thread(new Message());
		   		Thread w7 = new Thread(new Message());
		   		Thread w8 = new Thread(new Message());
		   		double startTime1 = System.currentTimeMillis();
		   		c1.start();
		   		c1.join();
		   		w1.start();
		   		w2.start();
		   		w3.start();
		   		w4.start();
		   		w5.start();
		   		w6.start();
		   		w7.start();
		   		w8.start();
		   		/*To terminate the thread  */
		   		
		   		w1.join();
		   		w2.join();
		   		w3.join();
		   		w4.join();
		   		w5.join();
		   		w6.join();
		   		w7.join();
		   		w8.join();
		   		Thread rc= new Thread(new MessageLoop());
		   		rc.start();
		   		rc.join();
		   		double end_time1 =  System.currentTimeMillis();
		   		double total_time1 = (end_time1 - startTime1)/1000;
		   		System.out.println("Result Queue is "+rq);
		   		System.out.println("Worker Execution time"+total_time1);
		   		//double RTT= total_time1/thread_count;
		   	}
		   	else if(thread_count == 16)
		   	{
		   		/* Thread initialisation  and starting the thread */
		   		Thread c1 = new Thread(new MessageLoop());
		   		Thread w1 = new Thread(new Message());
		   		Thread w2 = new Thread(new Message());
		   		Thread w3 = new Thread(new Message());
		   		Thread w4 = new Thread(new Message());
		   		Thread w5 = new Thread(new Message());
		   		Thread w6 = new Thread(new Message());
		   		Thread w7 = new Thread(new Message());
		   		Thread w8 = new Thread(new Message());
		   		Thread w9 = new Thread(new Message());
		   		Thread w10 = new Thread(new Message());
		   		Thread w11 = new Thread(new Message());
		   		Thread w12 = new Thread(new Message());
		   		Thread w13 = new Thread(new Message());
		   		Thread w14 = new Thread(new Message());
		   		Thread w15 = new Thread(new Message());
		   		Thread w16 = new Thread(new Message());
		   		double startTime1 = System.currentTimeMillis();
		   		c1.start();
		   		c1.join();
		   		w1.start();
		   		w2.start();
		   		w3.start();
		   		w4.start();
		   		w5.start();
		   		w6.start();
		   		w7.start();
		   		w8.start();
		   		w9.start();
		   		w10.start();
		   		w11.start();
		   		w12.start();
		   		w13.start();
		   		w14.start();
		   		w15.start();
		   		w16.start();
		   		/*To terminate the thread  */
		   		
		   		w1.join();
		   		w2.join();
		   		w3.join();
		   		w4.join();
		   		w5.join();
		   		w6.join();
		   		w7.join();
		   		w8.join();
		   		w9.join();
		   		w10.join();
		   		w11.join();
		   		w12.join();
		   		w13.join();
		   		w14.join();
		   		w15.join();
		   		w16.join();
		   		Thread rc= new Thread(new MessageLoop());
		   		rc.start();
		   		rc.join();
		   		double end_time1 =  System.currentTimeMillis();
		   		double total_time1 = (end_time1 - startTime1)/1000;
		   		System.out.println("Result Queue is "+rq);
		   		System.out.println("Worker Execution time"+total_time1);
		   		//double RTT= total_time1/thread_count;
		   		
			
		   	}
	}
}
