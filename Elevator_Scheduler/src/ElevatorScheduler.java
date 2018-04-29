
import java.util.*;

public class ElevatorScheduler {

	public static void main(String[] args) {
		Elevator E = new Elevator();
		Scheduler S = new Scheduler(E,6);	

		E.S = S;  // added this line for state maintenance
		
		Person p1 = new Person("A",S);
		Person p2 = new Person("B",S);
		Person p3 = new Person("C",S);	
		Person p4 = new Person("D",S);
		Person p5 = new Person("E",S);
		Person p6 = new Person("F",S);

		p1.start();
	    p2.start();
	    p3.start();
	    p4.start();
        p5.start();
	    p6.start();
	    

		S.start();
		
		}
}


class Elevator {
	
	int current_floor;
	String direction;

	Scheduler S;    // added this line for state maintenance
	
	String state;   // for query-based debugging
	
	public Elevator() {
		current_floor = 0;
		direction = "up";
	}
	
	synchronized void go(int floor, String dir) {
		 if (direction.compareTo(dir) != 0)
			direction = dir;
		 if (current_floor != floor)
			current_floor = floor;

		String up = S.up.toString();
		String down = S.down.toString();
		
		state = current_floor + "-" + direction + "-" + up + "-" + down;


	}
}

class Person extends Thread {
	Scheduler S;
	String name;
	int from = 0;
	int to = 0;
	
	public Person(String name, Scheduler S) {
        this.name = name;
        this.S = S;
    }

	public void run() {
        try {
            while (true) {
        	  if (to > from) 
         		  S.up(name,from, to);
        	  if (from > to) 
        		  S.down(name,from, to);
        	  from = to;
    		  to = (from + new Random().nextInt(97)) % S.max;
            }
        } catch(Exception e){}
    }
}


class Scheduler extends Thread {
	
	int max;
	int current_floor;
	String direction;
	Elevator elevator;
	
	SortedSet<Integer> up = new TreeSet<Integer>();
	SortedSet<Integer> down = new TreeSet<Integer>();

	public Scheduler(Elevator e, int max_floors) {
	      elevator = e;
	      this.max = max_floors;
          current_floor = 0;
	      direction = "up";

	}
	
	public void run() {
		while(true) {
			schedule();
		}
	}

	synchronized void up(String name, int from, int to) {
		// name can be used for printing, if desired
		try {
			up.add(from);   // press up button at floor 'from'
		    notifyAll();
		    
		    while (current_floor != from)
			    wait();
		    
		    up.add(to);    // get into elevator and press up button to 'to'
		    notifyAll();
		    
		    while (current_floor != to)
			    wait();
		    // reach floor 'to'
		}
		catch (Exception e) {}

	}
	synchronized void down(String name, int from, int to) {
		// name can be used for printing
		try {
			down.add(-from); // press down button at floor 'from'
		    notifyAll();
		    
		    while (current_floor != from)
			    wait();
		    
		    down.add(-to);	// get into elevator and press down button to 'to'
		    notifyAll();
		    
		    while (current_floor != to)
			    wait();     
		    // reach floor 'to'	    
		}
		catch (Exception e) {}
	}
	
	synchronized void schedule()  {
		try {
				
		while (up.isEmpty() && down.isEmpty())
			wait();
				
		Log.print_queues("Floor " + current_floor + ", direction = " + direction + ", ", up, down);

		  if (current_floor==(max-1)) 
			 direction = "down";
		
	      if (current_floor == 0) 
				 direction = "up";
			
	      if (direction == "up")  {
					
				 // write code here
	    	 // a check if direction reversal is required or not
	    	
	    	  if((up.isEmpty()||up.last()<current_floor)&&(current_floor>=(-down.first())))
	    	  {
	    		  direction="down";
	    	  }
	    	  // for case when up request completed and higher floor has request to go down 
	    	   
	    	  else if((up.isEmpty()||up.last()<current_floor)&&(current_floor<(-down.first())))
	    	  {
	    		  current_floor=(-down.first());   // go to highest floor which has requested the down 
	    		  down.remove(-current_floor);
	    	  }
	    	  else
	    	  {   
	    		 // complete the up request one by one until highest up request is completed
	    		  
	    		 for(int up_floor:up)
	    		 {
	    			 // case1 when current floor is equal to up_floor
	    			 if(up_floor==current_floor)
	    			 {
	    				 up.remove(current_floor);
	    				 break;
	    			 }
	    			 // case2 if up_floor is above the current status of elevator
	    			 else if(up_floor>current_floor)
	    			 {
	    				 current_floor=up_floor;
	    				 up.remove(current_floor);
	    				 break;
	    			 }
	    		  }
	    		 
	    	  }
	    	  
	    	  
	    	
	    	}
	    	
	    	
	      
            else // direction == "down" 
            {	 
            	// a check if direction reversal is required or not
  	    	  
  	    	  if((down.isEmpty()||(-down.last())>current_floor)&&(current_floor<=up.first()))
            	{
  	    		  direction="up";
  	    	  }
  	    	 // for case when down requests completed and lower floor has request to go up 
	    	   else if((down.isEmpty()||(-down.last())>current_floor)&&(current_floor>up.first()))
  	    	  {
	    		  current_floor=up.first();   // go to lowest floor which has requested the up 
	    		  up.remove(current_floor);
	    	  }
	    	  else
	    	  {   
	    		 // complete the down request one by one until lowest down request is completed
	    		  
	    		 for(int down_floor:down)
	    		 {
	    			 // case1 when current floor is equal to down_floor
	    			 if(-down_floor==current_floor)
	    			 {
	    				 down.remove(-current_floor);
	    				 break;
	    			 }
	    			 // case2 if down_floor is below the current status of elevator
	    			 else if(-down_floor<current_floor)
	    			 {
	    				 current_floor=-down_floor;
	    				 down.remove(-current_floor);
	    				 break;
	    			 }
	    		  }
	    		 
	    	  }
            }
	    Log.print_queues("Floor = " + current_floor + ", direction = " + direction + ", ", up, down);

		notifyAll();
		}

		catch (Exception e) {}
	}
    
}


// For logging output

class Log {
	
	static synchronized void print_queues(String action, SortedSet<Integer> up, SortedSet<Integer> down) {

		System.out.print(action + "up:[ ");

		for (int f: up) 
			System.out.print(f + " ");

		System.out.print("] down:[ ");

		for (int f: down) 
			System.out.print((0-f) + " ");

		System.out.println("]");
	}		 

}



