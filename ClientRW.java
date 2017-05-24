import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;

import com.amazonaws.services.sqs.model.Message;


public class ClientRW {
	static ArrayList<String> val=new ArrayList<String>(); 
	static int id;  
	static HashMap<String, Integer> hm = new HashMap<String, Integer>();
	static HashMap<String, Integer> hm1 = new HashMap<String, Integer>();
	static Queue rq= null;
static String mql,rql;
	public static  String RemBackE(){
		 mql=  SImpleQueueService.getCredAma("tQ");
		ArrayList<Message> mess = new ArrayList<Message>();
			  
			  if (mess.size() > 0) {
				  
				  Message m = mess.get(0);
				  mql = m.getBody();
				  SImpleQueueService.delm(mql,m);
			  }
			return mql;
	}
	
	public static String ResQ() {
		rql= SImpleQueueService.getCredAma("rQ");
		ArrayList<Message> mess1 = new ArrayList<Message>();
		 if (mess1.size() > 0) {
			  
			  Message m = mess1.get(0);
			  rql = m.getBody();
			  SImpleQueueService.delm(rql,m);
		  }
		return rql;
	}
	
	@SuppressWarnings("rawtypes")
	public static void CheckSc(ArrayList<String> str){
		String s=null;
		double st= System.nanoTime();
		Iterator itr= str.iterator();
		while(itr.hasNext()){
			s=(String) itr.next();
			int cur=id++;
			hm.put(s, cur);
			DynamDB am= new DynamDB();
			int sp1 = Integer.parseInt(s.split(" ")[1]);
			hm1.put(s,0);
			//rq.insert(hm1);
			
		}
		double ent= System.nanoTime();
		double tot= st-ent;
		System.out.println("The total time taken is "+tot);
		
	}
	
	
	public static void main(String args[]) throws FileNotFoundException{
		String  we ;
		
		File fis= new File("WORKLOAD_FILE");
		BufferedReader buff_file = new BufferedReader(new FileReader(fis));
		
	Scanner sc =new Scanner(System.in);
	System.out.println("Enter the number of workers to be used");
	int nooftask=sc.nextInt();
	Scanner sc1= new Scanner(System.in);
	System.out.println("Enter the number of tasks to operate");
	
	for(int i=0;i<nooftask;i++){
		we=RemBackE();
		val.add(we);
		CheckSc(val);
	}
  	System.out.println("The process is sucessfull");
	}		
}
