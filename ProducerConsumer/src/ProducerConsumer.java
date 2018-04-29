// HW2:  Outline of Producer-Consumer:

import java.util.Random;

public class ProducerConsumer {
	public static void main(String[] args) {
		DropBox db = new DropBox(5);
		Producer p = new Producer(db);
		Consumer c = new Consumer(db);
		p.start();
		c.start();
	}
}

class Producer extends Thread {
	private DropBox db;

	public Producer(DropBox db) {
		this.db = db;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			db.put(i);
			try {
				Thread.sleep(new Random().nextInt(100));
			} catch (Exception e) {
			}
		}
	}
}

class Consumer extends Thread {
	private DropBox db;
	int value;

	public Consumer(DropBox db) {
		this.db = db;
	}

	public void run() {
		while (true) {
			value = db.get();
			try {
				Thread.sleep(new Random().nextInt(500));
			} catch (Exception e) {
			}
		}
	}
}

class DropBox {
	// to be filled in by you
	//data fields
	private int[] data;
	private int n;   // Part 1 point 1, size of the array, for parameterized constructor and kept as private
	private int p;   // Part 1 point 2, index of array where next value will be put by producer
	private int g;   // Part 1 point 2, index of array where next value is going to be get by consumer
	private int count; // Part 1 point 3, no of integers that can be taken by consumer without intervention of put()

	// parameterized constructor
	public DropBox(int n)
	{
		this.n=n;
		data=new int[this.n];  // making array of n elements for data
		p=0;
		g=0;
		count=0;
	}
	
	//method fields
	boolean empty()       // to check if buffer is empty
	{
		if(count==0)
			return true;
		else
			return false;
	}
	boolean full()        // to check if buffer is full
	{   
		if(count==n)
			return true;
		else
			return false;
		}
	
	public synchronized int get()
	{ 	int get=g;
		try 
		{
			
			while(empty())
			{
			wait(); 
			}
			System.out.println("Value got: "+data[g]);
			g=(g+1)%n;
			count--;       // decreasing the number of values available for pick
			notify();
			
			
		}catch(InterruptedException e)
			{	}
		return data[get];
	}
	public synchronized void put(int v)
	{
		try
		{
			while(full())
			{
				wait();
			}
			data[p]=v;
			
			p=(p+1)%n;    // for circular array condition
			count++;      // incrementing number of values get can pick 
			System.out.println("Value Put: "+data[p]);
			notify();
		
	     }catch(InterruptedException e)
			{
	    	
			}

}
}
