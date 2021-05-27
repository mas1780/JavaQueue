public class CustomerQueue<C> {
	int servedCust; //# of customers served
	int maxSizeQueue; //the maximum number of people in a queue 
	int cSize; //current number of people in queue
	int idleTime; //the total idle time of employee
	int maxBreak; //the maximum break for the employee
	//start is the 1st person in line, end is last person in line
	//waiting to be served
	customersNode<Integer> start; 
	customersNode<Integer> end;
	
	//constructor
	CustomerQueue() {
		servedCust = 0;
		maxSizeQueue = 0;
		cSize= 0;
		idleTime= 0;
		maxBreak = 0;
		start = null; 
		end = null; 
		
	}
	
	//method to add elements to the linked list 
	void putInto(C iD, C time2) {
		cSize++;
		int time;
		int ID;
		//translate the C input into int data type for seconds
		String[] timeList = ((String) time2).split(":");
		time= Integer.parseInt(timeList[1])*60 + 
				Integer.parseInt(timeList[2]) + 
				Integer.parseInt(timeList[0])*3600;
		ID =  Integer.parseInt((String) iD);
		if(start==null){
			start = new customersNode<Integer>(ID, time);
			end = start;
		}
		else{
			end.next = new customersNode<Integer>(ID,time);
			end = end.next;
		}
	}
	
	void Served() {
		//base case check
		int clock= 9*3600;
		if(start==null){
			System.out.println("Did not detect customers to serve");
			return;
		}
		int waiting=0;
		customersNode<Integer> queue = start;
		//handling those that come before 9am
		while(queue.arvtime<9*3600 && queue.next != null) {
			waiting++;
			cSize++;
			queue.waitingTime = (9*3600) - queue.arvtime + 
					((cSize-1)*customersNode.Timecost);
			servedCust++;
			queue= queue.next;
		}
		
		if(waiting>maxSizeQueue) {
			maxSizeQueue =waiting;
		}
		clock = clock + servedCust*customersNode.Timecost;
		
		//handling those that came after 9 am but before 5pm
		while (queue.arvtime>9*3600 && queue.arvtime< 17*3600 &&
				queue.next != null) {
			queue.waitingTime = clock-queue.arvtime;
			if (queue.waitingTime < 0) {
				idleTime =idleTime +(-1*(clock-queue.arvtime));
				if ((clock-queue.arvtime)>maxBreak) {
					maxBreak= clock-queue.arvtime;
				}
				waiting = waiting - 1*((queue.arvtime-clock)/
						customersNode.Timecost);
				queue.waitingTime=0;
			}
			if (queue.waitingTime>0) {
				waiting++;
				queue.arvtime=clock;
			}
			 
			clock = queue.arvtime + customersNode.Timecost;
			servedCust++;
			waiting--;
			if(waiting <0) {waiting =0;}
			if(waiting>maxSizeQueue) {
				maxSizeQueue =waiting;
			}
			
			queue=queue.next;
		}
		
	}
}
