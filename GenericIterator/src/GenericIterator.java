import java.util.*;

class Tree<T extends Comparable<T>> implements Iterable<T> {

	public Tree(T v) {
		value = v;
		left = null;
		right = null;
	}

	public void insert(T v) {
		if (value.compareTo(v) == 0)
			return;
		if (value.compareTo(v) > 0)
			if (left == null)
				left = new Tree<T>(v);
			else
				left.insert(v);
		else if (value.compareTo(v) < 0)
			if (right == null)
				right = new Tree<T>(v);
			else
				right.insert(v);
	}

	public Iterator<T> iterator() {
		return new TreeIterator<T>(this);
		}

	protected T value;
	protected Tree<T> left;
	protected Tree<T> right;
}



class Student implements Comparable<Student> {

	public String name;
	int marks;

	public Student(String n, int marks) {
		name = n;
		this.marks = marks;
	}

	public int compareTo(Student s) {
		return name.compareTo(s.name);
	}
}

class TreeIterator<T extends Comparable<T>> implements Iterator<T>
{
	Stack<Tree<T>> tree_stack= new Stack<Tree<T>>();  
	public TreeIterator(Tree<T> tree)
	{  // storing the smallest value in stack
		Tree<T> temp=tree; 
		while(temp!=null)
		{
			tree_stack.push(temp);
			temp=temp.left;
		}
	}
	public boolean hasNext()
	{
		if(tree_stack.empty())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	// consider T as a student object here 
	public T next()
	{
		if(hasNext())
		{
			
			Tree<T> poped_elem=tree_stack.pop();
			Tree<T> temp=poped_elem.right;
				while(temp!=null)
				{
					tree_stack.push(temp);
					temp=temp.left;  
				}
			
			return poped_elem.value;
		}
		else
		{
			return null;
		}
	}
}

class GenericIteratorDriver {

	public static void main(String[] args) {
		
		Student s0 = new Student("N", 75);
		Student s1 = new Student("A", 50);
		Student s2 = new Student("J", 35);
		Student s3 = new Student("H", 25);
		Student s4 = new Student("R", 85);
		Student s5 = new Student("K", 65);
		Student s6 = new Student("T", 95);
		Student s7 = new Student("U", 95);
		Student s8 = new Student("B", 95);

		Tree<Student> tree1 = new Tree<Student>(s0);
		tree1.insert(s1);
		tree1.insert(s2);
		tree1.insert(s3);
		tree1.insert(s4);
		tree1.insert(s5);
		tree1.insert(s6);
		tree1.insert(s7);
		tree1.insert(s8);

		for (Student s : tree1)
			System.out.println(s.name + " " + s.marks);	
     }	
}


 