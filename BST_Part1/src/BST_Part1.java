
// CSE 522: Homework 1, Part 1

class BST_Part1 {
	public static void main(String args[]) {
		 Tree tr;
		 tr = new Tree(100);
		 tr.insert(50);
		 tr.insert(125);
		 tr.insert(150);
		 tr.insert(25);
		 tr.insert(75);
		 tr.insert(20);
		 tr.insert(90);
	     tr.delete(20);
	     tr.delete(20);
		 tr.delete(125);
		 tr.delete(150);
		 tr.delete(100);
		 tr.delete(50);
		 tr.delete(75);
		 tr.delete(25);
		 tr.delete(90);
	
	}
}

class Tree { // Defines one node of a binary search tree
	
	public Tree(int n) {
		value = n;
		left = null;
		right = null;
	}
	
	public void insert(int n) {
		if (value == n)
			return;
		if (value < n)
			if (right == null)
				right = new Tree(n);
			else
				right.insert(n);
		else if (left == null)
			left = new Tree(n);
		else
			left.insert(n);
	}

	
	public Tree min() {
		// returns the Tree node with the minimum value
		// you should write the code
		Tree minValue= this;
		while(minValue.left!=null)
		{
			minValue=minValue.left;
		}
	    return minValue;
	}
	
	public Tree max() {
		// returns the Tree node with the maximum value
		// you should write the code
		Tree maxValue=this;
		while(maxValue.right!=null)
		{
			maxValue=maxValue.right;
		}
		return maxValue;
	
		
	}
	
	
	public Tree find(int n) {
		// returns the Tree node with value n
		// returns null if n is not present in the tree
		// you should write the code
		Tree valueN=this;
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
	
	public void delete(int n) {  
		Tree t = find(n);
		if (t == null) {  
			// print out error message and return
		 System.out.println("Number not available in Tree");
		 return;
		}
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
	
	private void case1(Tree t, Tree root) {
		
		Tree temp=root;       // find the tree node which has link to t.
		
		
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
	
	private void case2(Tree t, Tree root) {  
		Tree temp= root;  // to find Tree node which has link to t. 
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
	
	private void case3(Tree t, String which_side) {  
		// which == "left" or which == "right"
		Tree temp=null; // for storing max or min node 
		
		if(which_side=="left")
		{
			  
			temp=t.left.max(); //find max of left sub tree
		          		    
		}
		if(which_side=="right")
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

	protected int value;
	protected Tree left;
	protected Tree right;
}