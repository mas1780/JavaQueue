import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class hw3 {
	static int[] waitTimes;
	public static void main(String args[]){
		CustomerQueue Customers = getInput("customersfile.txt");
		Customers.Served();
		outputFile(Customers, "queriesfile.txt");
	}
	
	public static <C> CustomerQueue<C> getInput(String inp) {
		//create an object of type customer queue to 
		//input the data from the file
		CustomerQueue<C> Customers = new CustomerQueue<C>();
		try {
		String input; 
		//use a buffered reader to read data from text file
			BufferedReader reader = new BufferedReader(new FileReader(inp));
			input = reader.readLine();
			//since the time cost in seconds is the present in the 
			//first line of the text file we can extract it
			customersNode.Timecost = Integer.parseInt(input);
			while((input = reader.readLine()) != null) {
				C time;
				C ID; 
				input = reader.readLine();
				ID = (C) (input.split("  ")[1]);
				//we add [1] because input is an array of strings and 
				//we are able to select which index position we require
				input = reader.readLine(); 
				time = (C) input.split(" ")[1];
				Customers.putInto(ID, time);
			}
			reader.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("file not found");	
		}
		return Customers;
		//returns linked list of type CustomerQueue
	}
	
	public static void outputFile(CustomerQueue C, String S) {
		try {
		BufferedReader reader = new BufferedReader(new FileReader(S));
		FileWriter Fwriter = new FileWriter("Output.txt");
		BufferedWriter Bwriter  = new BufferedWriter(Fwriter);
		String inp= reader.readLine(); 
		while(inp != null) {
			if (inp.equals("NUMBER-OF-CUSTOMERS-SERVED")) {
				Bwriter.write("NUMBER-OF-CUSTOMERS-SERVED:" + 
			C.servedCust);
				Bwriter.newLine();
				break;
			}
			if (inp.equals("LONGEST-BREAK-LENGTH")) {
				Bwriter.write("LONGEST-BREAK-LENGTH:" + 
			C.maxBreak);
				Bwriter.newLine();
				break;
			}
			if (inp.equals("TOTAL-IDLE-TIME")) {
				Bwriter.write("TOTAL-IDLE-TIME:" + 
			C.idleTime);
				Bwriter.newLine();
				break;
			}
			if (inp.equals("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME")) {
				Bwriter.write("MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME:" +  
			C.maxSizeQueue);
				Bwriter.newLine();
				break;
			}
			if (inp.matches("WAITING-TIME-OF \\d*")) {
				int ID = Integer.parseInt(inp.substring(inp.indexOf(" ")+1, 
						inp.length()));
				for(int i=0; i< ID-1; i++) {
					C.start= C.start.next;
				}
				int temp= C.start.waitingTime;
				Bwriter.write(inp + ":" + temp);
			
				Bwriter.newLine();
				break;
			}
			Bwriter.close();
			reader.close();
			
		}
		}catch(Exception ex){
			System.out.println( "Unable to open file");
	}
}
}