
/**
 * Builds a to-do item
 * @author Nhien (Ricky) Lam 
 *
 */
public class ToDoItem implements Comparable
{
	private String description;
	private int priority;
	
	/**
	 * Constructs a to-do item with description and priority
	 * @param description
	 * @param priority
	 */
	public ToDoItem(String description, int priority) 
	{
		this.description = description;
		this.priority = priority;
	}

	/**
	 * Gets the description
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Gets the priority
	 * @return the priority
	 */
	public int priority()
	{
		return priority;
	}

	/**
	 * Checks if 2 to-do items have the same priority and description
	 * @return true if and only if 2 items have both the same priority and description, false otherwise
	 */
	public boolean equals(Object otherObject)
	{
		if (otherObject == null)
		{
			return false;
		}
		if (this.getClass() != otherObject.getClass()) 
		{
			return false;
		}
		
		ToDoItem other = (ToDoItem)otherObject;
		return (this.priority == other.priority && this.description.equals(other.description));
	}

	/**
	 * Compares the to-do item by priority and description
	 * If priorities are equal, order by description
	 * @return the difference of the 2 items
	 */
	public int compareTo(Object otherObject)
	{
		ToDoItem other = (ToDoItem) otherObject;
		
		if(this.priority != other.priority)
		{
			return Double.compare(this.priority, other.priority);
		}
		else
		{
			return this.description.compareTo(other.description);
		}
	}

	/**
	 * Gets a String representation of the ToDoItem
	 * @return the a String representation of the ToDoItem
	 */
	public String toString()
	{
		return  getClass().getName() + "[description=" + description  + ",priority=" + priority + "]";
	}

}
