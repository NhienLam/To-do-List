import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Build a To-Do List with priority to-do items
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
		System.out.println("Added!");
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
		System.out.println("*********************");
		if(toDo.isEmpty())
		{
			System.out.println("The List is empty");
		}
		else
		{
			PriorityQueue<ToDoItem> sub = new PriorityQueue<>(toDo);
			while(!sub.isEmpty())
	     		{
				System.out.println(sub.remove());
	        	}
		}
		System.out.println("*********************")
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
			System.out.println("Removed!");
		}
		else
		{
			System.out.println("ERROR: There is no such item in the List");
			runToDoList();
		}
	}
	
	/**
	 * Writes the List to a File
	 */
	public void writeDataToFile(String filename)
	{
		try
		{
			// Build a chain of writers
			File f = new File(filename);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("***** TO-DO LIST ***** ");
			bw.newLine();

			Iterator itr = toDo.iterator(); 
			while (itr.hasNext()) 
			{
				bw.write(itr.next().toString());
				bw.newLine();
			}
					
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
			System.out.println("ERROR! There is no such Task in the List");
			runToDoList();
		}
	}

	/**
	 * Reads data from another file (the saved List)
	 * @param filename the file to read
	 * @throws FileNotFoundException
	 */
	public void readFile(String filename) throws FileNotFoundException 
	{
		try
		{
			// Build chain of readers.
			File f = new File(filename);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			// Skip the first line, which is not a task
			br.readLine();
			
			String line;
			
			// Read each task until the end of the file
			while((line = br.readLine()) != null)
			{
				String[] arr = line.split(":");
				ToDoItem item = new ToDoItem(arr[1].trim(), Integer.parseInt(arr[0].trim()));
				this.add(item);	
			}	
			
			//Close br, fr
			br.close();
			fr.close();	
		}
		catch(IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}
		
		System.out.println("*** This is the Saved List ***");
		this.display();		
	}
	
	
	/**
	 * Starts the ToDoList tasks
	 */
	public void runToDoList()
	{
		System.out.println("*********************");

		// valid input options
		String s = "dsgarcq";
		
		// get input from user
		Scanner in = new Scanner(System.in);
		
		System.out.print("What do you want to do?"  
				+ "\n" + "Enter 'D' --- Display List"
				+ "\n" + "Enter 'S' --- Save List"
				+ "\n" + "Enter 'G' --- Get Saved List"
				+ "\n" + "Enter 'A' --- Add Task"
				+ "\n" + "Enter 'R' --- Remove Task"
				+ "\n" + "Enter 'C' --- Change Priority"
				+ "\n" + "Enter 'Q' --- Quit"
				+ "\n" + "Enter you option: "
				);
		
		String input = in.nextLine();
		
		// Check if the input is valid
		while(!s.contains(input) || input.length() != 1)
		{
			System.out.println("*********************");
			System.out.print("Invalid input! Please try again"
					+ "\n" + "Enter 'D' --- Display List"
					+ "\n" + "Enter 'S' --- Save List"
					+ "\n" + "Enter 'A' --- Add Task"
					+ "\n" + "Enter 'R' --- Remove Task"
					+ "\n" + "Enter 'C' --- Change Priority"
					+ "\n" + "Enter 'Q' --- Quit"
					+ "\n" + "Enter you option: "
					);
			input = in.nextLine();
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
				System.out.println("ERROR: Invalid input! Please enter an Integer for priority: ");
				pri = in.nextLine();
			}

			int priority = Integer.valueOf(pri);
			this.add( new ToDoItem(description, priority));
			
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
			System.out.println("Changed!");
			
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
			writeDataToFile("savedTodoList");
			System.out.println("Saved!");
			
			runToDoList();
		}
		
		// Get the saved List
		else if(input.equals("g"))
		{
			try
			{				
				readFile("savedTodoList");
			}
			catch(FileNotFoundException fnf)
			{
				System.out.println(fnf.getMessage());
			}
			
			runToDoList();
		}
		
		// Stop running
		else if(input.equals("q"))
		{
			System.out.println("Thank you!! See you again!");
		}
	}
}
