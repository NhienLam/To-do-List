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
	
	/**
	 * Checks if the variable is the Integer type
	 * @param str the target that needs to check
	 * @return  true if the input is an integer, else return false
	 */
	public boolean isInteger(String str) 
	{ 	   
		try 
		{
			Integer.parseInt(str);  // Make an input to an integer
			return true; 
		}
		catch( Exception e) 
		{ 
			// return false if the input cannot be made to an integer
			return false;
		}
	} 
	
	/**
	 * Displays the ToDo List
	 */
	public void display()
	{
		if(toDo.isEmpty())
		{
			System.out.println("The List is empty");
		}
		else
		{
			Iterator itr = toDo.iterator(); 
	        while (itr.hasNext()) 
	        {
	            System.out.println(itr.next());
	        }
		}		
    }
	
	/**
	 * 
	 * Removes the Task from the List
	 * @param target that wants to remove
	 */
	public void remove(ToDoItem target)
	{
		if(toDo.contains(target))
		{
			toDo.remove(target);
		}
		else
		{
			runToDoList();
		}
	}
	
	/**
	 * Writes the List to a File
	 */
	public void writeDataToFile()
	{
		try
		{
			// Build a chain of writers
			File f = new File("savedTodoList");
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("TO-DO LIST: ");
			bw.newLine();
			
			if(toDo.isEmpty())
			{
				bw.write("The List is empty");
				bw.newLine();
			}
			else
			{
				Iterator itr = toDo.iterator(); 
		        while (itr.hasNext()) 
		        {
		        	bw.write(itr.next().toString());
					bw.newLine();
		        }
			}		
			
			bw.newLine();
			
			// Close bw and fw
			bw.close();
			fw.close();
		}
		catch (IOException ioe)
		{
			ioe.getMessage();
		}
	}
	
	/**
	 * Changes the priority of the existed Task
	 * @param des the description of the existed Task that user wants to change
	 * @param beforePri the priority of the Task
	 * @param afterPri the NEW priority of the Task the user wants to change to
	 */
	public void changePriority(String des, int beforePri, int afterPri)
	{
		ToDoItem target = new ToDoItem(des, beforePri);
		if(toDo.contains(target))
		{
			toDo.remove(target);
			toDo.add(new ToDoItem(des, afterPri));
		}
		else
		{
			System.out.println("There is no such Task in the List");
			runToDoList();
		}
	}
	
	/**
	 * Starts the ToDoList tasks
	 */
	public void runToDoList()
	{
		Scanner in = new Scanner(System.in);
		
		System.out.println("What do you want to perform?"  
				+ "\n" + "Press 'd' --- Display List"
				+ "\n" + "Press 's' --- Save List"
				+ "\n" + "Press 'a' --- Add Task"
				+ "\n" + "Press 'r' --- Remove Task"
				+ "\n" + "Press 'c' --- Change Priority"
				+ "\n" + "Press 'e' --- End"
				);
		
		String input = in.nextLine();
		
		// Ask again if the user didn't enter anything
		while(input.length() == 0)
		{
			System.out.println("Invalid input! Please try again"
					+ "\n" + "Press 'd' --- Display List"
					+ "\n" + "Press 's' --- Save List"
					+ "\n" + "Press 'a' --- Add Task"
					+ "\n" + "Press 'r' --- Remove Task"
					+ "\n" + "Press 'c' --- Change Priority"
					+ "\n" + "Press 'e' --- End"
					);
			input = in.nextLine();
		}
		
		// If the input is more than 1 character, only take the 1st character of the input
		if(input.length() > 1) 
		{
			input = input.substring(0,1);
		}
		
		// Add task to the List
		if(input.equals("a"))
		{
			System.out.print("Enter the task you want to add: ");
			String description = in.nextLine();
			System.out.println("Enter the priority of the the task: ");
			String pri = in.nextLine();
			while(!isInteger(pri))
			{
				System.out.println("Invalid input! Please enter an Integer for priority: ");
				pri = in.nextLine();
			}
			int priority = Integer.valueOf(pri);
			ToDoItem task = new ToDoItem(description, priority);
			add(task);
			
			runToDoList();
		}
		
		// Display the To-Do List
		else if(input.equals("d"))
		{
			display();
			
			runToDoList();
		}
		
		// Change priority of the existed Task
		else if(input.equals("c"))
		{
			System.out.println("Enter the task you want to change: ");
			String description = in.nextLine();

			System.out.println("Enter its priority: ");
			String beforePri = in.nextLine();
			while(!isInteger(beforePri))
			{
				System.out.println("Invalid input! Please enter an Integer for priority: ");
				beforePri = in.nextLine();
			}
			int beforePriority = Integer.valueOf(beforePri);
			
			System.out.println("Enter the new priority: ");
			String afterPri = in.nextLine();
			while(!isInteger(afterPri))
			{
				System.out.println("Invalid input! Please enter an Integer for priority: ");
				afterPri = in.nextLine();
			}
			int afterPriority = Integer.valueOf(afterPri);
			
			changePriority(description, beforePriority, afterPriority);
			
			runToDoList();
		}
		
		// Remove the task from the List
		else if(input.equals("r"))
		{
			System.out.print("Enter the task you want to remove: ");
			String description = in.nextLine();

			System.out.println("Enter its priority: ");
			String pri = in.nextLine();
			while(!isInteger(pri))
			{
				System.out.println("Invalid input! Please enter an Integer for priority: ");
				pri = in.nextLine();
			}
			int priority = Integer.valueOf(pri);
			
			ToDoItem target = new ToDoItem(description, priority);
			toDo.remove(target);
			
			runToDoList();
		}
		
		// Save the List ie. Write the List in another file
		else if(input.equals("s"))
		{
			writeDataToFile();
			System.out.println("Saved!");
			
			runToDoList();
		}
		
		// Stop running
		else if(input.equals("e"))
		{
			System.out.println("Thank you!!");
		}
		
		// The input is valid, ask again
		else
		{
			// *********************** HAVEN'T FINISHED
			System.out.println("Invalid input! Please try again"
					+ "\n" + "Press 'd' --- Display List"
					+ "\n" + "Press 's' --- Save List"
					+ "\n" + "Press 'a' --- Add Task"
					+ "\n" + "Press 'r' --- Remove Task"
					+ "\n" + "Press 'c' --- Change Task"
					);
			input = in.nextLine();
		}
	}
}
