
import java.util.Random;
import java.util.concurrent.*;

public class Readers_Writers_Semaphores {
	
	public static void main(String[] args) {

		Database d = new Database();

		Writer w1 = new Writer(d,1);
		Writer w2 = new Writer(d,10);
		Writer w3 = new Writer(d,100);
		Writer w4 = new Writer(d,1000);
		Writer w5 = new Writer(d,10000);
		Reader r1 = new Reader(d);
		Reader r2 = new Reader(d);
		Reader r3 = new Reader(d);
		Reader r4 = new Reader(d);
		Reader r5 = new Reader(d);

		w1.start();
		r1.start();
		r2.start();
		w2.start();
		r3.start();
		r4.start();
		w3.start();
		r5.start();
		w4.start();
		w5.start();

	}
}

class Database {
	int data = 0;
	int r = 0;   // # active readers
	int w = 0;   // # active writers (0 or 1)
	int ww = 0;  // # waiting writers
	int wr = 0; // # waiting readers
	Semaphore readOrWrite= new Semaphore(1);     // Semaphore for synchronized operations over data 
	Semaphore read_wait=new Semaphore(0);      // Semaphore to make reader thread wait till some condition is satisfied 
	Semaphore write_wait= new Semaphore(0);    // Semaphore to make writer wait till some condition is satisfied 
	public void request_read() {
		try {
			readOrWrite.acquire();
			 wr++;  
			while (w == 1 || ww > 0)   
				{
				   readOrWrite.release();
				   read_wait.acquire();    // if got acquired, it means waiting is over
				   readOrWrite.acquire();
				  }
			 wr--;   
			 r++;
			readOrWrite.release();
			}catch(Exception e)
			{}		
			}

	public  void done_read() {
		try {
			readOrWrite.acquire();
			r--;                               // reduce the active readers count
		    if(write_wait.hasQueuedThreads())
		    {
		    	write_wait.release();         // notify any writer thread if waiting
		    }
		    else {
		    	read_wait.release();         // if no writer thread , notify other threads waiting
		    }
		    	readOrWrite.release();               // release the read lock 
		}catch(Exception e)
		{
			
		}
	}

	public void request_write() {
		try {
			readOrWrite.acquire();        // take a lock on resource
			  
 		    while (r > 0 || w == 1)
 		    {
				 readOrWrite.release();
				 ww++;
				 write_wait.acquire();    // to implement waiting of writers thread
				 ww--;
				 readOrWrite.acquire();
				 }
 		   
		    w = 1;
		readOrWrite.release();
	}catch(Exception e) {}
	}

	public void done_write() {
		try {
			readOrWrite.acquire();
			w = 0;
			if(write_wait.hasQueuedThreads())
		    {
		    	write_wait.release();         // notify any writer thread if waiting
		    }
		    else {
		    	read_wait.release();         //else notify any waiting reader
		    }
			readOrWrite.release();
		}catch(Exception e) {
		
	}
		}

	int read() {
		return data;
	}

	void write(int x) {
		data = data + x;
	}
}

class Reader extends Thread {
	Database d;

	public Reader(Database d) {
		this.d = d;
	}

	public void run() {

		for (int i = 0; i < 5; i++){		
			d.request_read();
			System.out.println(d.read());
			try {
				Thread.sleep(new Random().nextInt(25));
			}
			catch (Exception e) {}	
			d.done_read();
			
		}
	}
}

class Writer extends Thread {
	Database d;
	int x;

	public Writer(Database d, int x) {
		this.d = d;
		this.x = x;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {			
			d.request_write();
			d.write(x);
			try {
				Thread.sleep(new Random().nextInt(25));
			}
			catch (Exception e) {}
			d.done_write();
		}
	}
}

