import java.util.PriorityQueue;

/**
 * Build a to-do list with priority to-do items
 * @author Nhien (Ricky) Lam 
 *
 */
public class ToDoList
{
	private PriorityQueue<ToDoItem> toDo;
	
	/**
	 * Constructs a PriorityQueue to-do list
	 */
	public ToDoList()
	{
		toDo = new PriorityQueue<>();
	}
	
	/**
	 * Adds an item for this ToDoList
	 * @param item to add
	 */
	public void add(ToDoItem item) 
	{
		toDo.add(item);
	}
	
	/**
	 * Removes and returns the next item to do
	 * @return the next item to do
	 */
	public ToDoItem nextItem()
	{
		return toDo.remove();
	}
	
	/**
	 * Check if there is at least one item left to do 
	 * @return true if there is at least one item left to do otherwise false
	 */
	public boolean hasNext() 
	{		
		if(toDo.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Gets the next item to do but does not remove it from the list
	 * @return the next item to do
	 */
	public ToDoItem peek()
	{
		return toDo.peek();
	}
}
