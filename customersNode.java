
public class customersNode <C>{
	customersNode<C> next; //to iterate through the queue
	int arvtime; //the time the customer arrives
	C customerID; //unique identification of customer
	int waitingTime; //the amount of time customer waits
	static int Timecost;
	
	//constructor
	customersNode(C id, int Arrivaltime){
		this.customerID=(C) id; 
		
		this.arvtime = Arrivaltime;
		this.waitingTime=0;
		//the following is if the customer arrives before 9am
		if (this.arvtime < 9*3600) {
			waitingTime = waitingTime +9*3600 - this.arvtime;
			this.arvtime = 9*3600; //the time they would be served
		}
	}
}



