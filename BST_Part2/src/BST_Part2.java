//CSE 522: Homework 1, Part 2

  class BST_Part2 {

	  public static void main(String[] args) {
			AbsTree tr = new DupTree(100);
			 tr.insert(50);
			 tr.insert(125);
			 tr.insert(150);
			 tr.insert(25);
			 tr.insert(75);
			 tr.insert(20);	
			 tr.insert(90);
			 tr.insert(50);
			 tr.insert(125);
			 tr.insert(150);
			 tr.insert(25);
			 tr.insert(75);
			 tr.insert(20);	
			 tr.insert(90);
	
			 tr.delete(20);
			 tr.delete(20);
			 tr.delete(20);
			 tr.delete(150);
			 tr.delete(100);
			 tr.delete(150);
			 tr.delete(125);
			 tr.delete(125);
			 tr.delete(50);
			 tr.delete(50);
			 tr.delete(25);
			 tr.delete(50);
			 tr.delete(75);
			 tr.delete(90);
			 tr.delete(25);
			 tr.delete(50);
			 tr.delete(75);
			 tr.delete(90);
		}
  }
  
abstract class AbsTree {

	public AbsTree(int n) {
		value = n;
		left = null;
		right = null;
	}

	public void insert(int n) {
		if (value == n)
			count_duplicates();
		else if (value < n)
			if (right == null)
				right = add_node(n);
			else
				right.insert(n);
		else if (left == null)
			left = add_node(n);
		else
			left.insert(n);
	}
	
	public void delete(int n) { // assume > 1 nodes in tree
		AbsTree t = find(n);
		if (t == null) {  
			// print out error message and return
		 System.out.println("Number not available in Tree");
		 return;
		}
		boolean deleteCheck=t.deleteFlag();
		if(deleteCheck==true) {
		// adapt Part 1 solution here
		
		if (t.left == null && t.right == null) { 
			// do case1 and return
			if(t==this) System.out.println("Cannot delete the term, only root value is present");
			else
			{
				case1(t,this);
			}
			return;
		}
		if (t.left == null || t.right == null) {  
			if (t != this) { 
				// do case2 and return
				case2(t,this);
				return;
			} else {
				// do case3 and return
				// case to delete root with one subtree
				if(t.left==null)
				{
					case3(t,"right");
					return;
				}
				
				if(t.right==null)
				{
					case3(t,"left");
					return;
				}
				
				
				
			}
		}
		// do case3 and return
				
			case3(t,"right");  //delete in right subtree, will take min node to replace
		    return;
		}
	}
	
	protected void case1(AbsTree t, AbsTree root) {  
        AbsTree temp=root;       // find the tree node which has link to t.
		int flag_deleted=0;   // flag to check delete operation
		
		while(flag_deleted==0)
		{
			if(temp.left==t)
				{
				temp.left=null;
				flag_deleted=1;
				}
			else if(temp.right==t)
				{
				temp.right=null;
				flag_deleted=1;
				}
			else
			{
				if(temp.value>t.value)
				{
					temp=temp.left;
				}
				else
				{
					temp=temp.right;
				}
			}
		}
	}
	
	protected void case2(AbsTree t, AbsTree root) {  
		AbsTree temp= root;  // to find Tree node which has link to t. 
		int flag_deleted=0;  // flag to check delete operation 
		while(flag_deleted==0)
		{
			if(temp.left==t)
			{
			    if(t.left==null)
			    	temp.left=t.right;
			    if(t.right==null)
			    	temp.left=t.left;
			
			flag_deleted=1;
			}
		else if(temp.right==t)
			{
			   if(t.left==null)
			     temp.right=t.right;
			   if(t.right==null)
				   temp.right=t.left;
			
			 flag_deleted=1;
			}
		else
		{
			if(temp.value>t.value)
			{
			temp=temp.left;
			}
			else
			{
			temp=temp.right;
			}
			
		}
		}
	}
	
	protected void case3(AbsTree t, String side) {  
		// which == "left" or which == "right"
				AbsTree temp=null; // for storing max or min node 
				
				if(side=="left")
				{
					  
					temp=t.left.max(); //find max of left sub tree
				          		    
				}
				if(side=="right")
				{
				   temp=t.right.min(); // find min of right sub tree
				
				}
				
				if(temp.left==null && temp.right==null)  
				{
					case1(temp,this);
				}
				if(temp.left!=null || temp.right!=null)
				{
					case2(temp,this);
				}
				t.value=temp.value;
	}

	private AbsTree find(int n) {
		        // returns the Tree node with value n
				// returns null if n is not present in the tree
				// you should write the code
				AbsTree valueN=this;
				while(valueN!=null)
				{
					if(valueN.value==n) break;
					else if (n>valueN.value && valueN.right!=null)
						valueN=valueN.right;
					else if (n<valueN.value && valueN.left!=null)
						valueN=valueN.left;
					else
						valueN=null;
				}
			     return valueN;
	}
	
	public AbsTree min() {
		// returns the Tree node with the minimum value
				// you should write the code
				AbsTree minValue= this;
				while(minValue.left!=null)
				{
					minValue=minValue.left;
				}
			    return minValue;
	}

	public AbsTree max() {
		// returns the Tree node with the maximum value
				// you should write the code
				AbsTree maxValue=this;
				while(maxValue.right!=null)
				{
					maxValue=maxValue.right;
				}
				return maxValue;
	}

	protected int value;
	protected AbsTree left;
	protected AbsTree right;

	// Protected Abstract Methods
	
	protected abstract AbsTree add_node(int n);
	protected abstract void count_duplicates();
	
	// additional protected abstract methods, as needed
	protected abstract boolean deleteFlag();
}


class Tree extends AbsTree {

	public Tree(int n) {
		super(n);
	}

	protected AbsTree add_node(int n) {
		return new Tree(n);
	}

	protected void count_duplicates() {
		;
	}
	
	// additional protected methods, as needed
    protected boolean deleteFlag()
    {
    	return true;
    }
}

class DupTree extends AbsTree {

	public DupTree(int n) {
		super(n);
		count = 1;
	};

	protected AbsTree add_node(int n) {
		return new DupTree(n);
	}

	protected void count_duplicates() {
		count++;
	}

	// additional protected methods, as needed
	protected boolean deleteFlag()
    {
		
		if(count==1)
    	 return true;  // to delete the node
		else{count--;
		return false;  // to reduce count by one for node
          }
	    }
	protected int count;
}