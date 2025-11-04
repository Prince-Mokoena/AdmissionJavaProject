import java.util.*;
public class AdmissionApplication{
	
	public static void main(String []args){
		String [] studNums = {"214124863","215129567","224324567","204124563","215224503","219012496","202412456","202141024","214224026","218244523","215012032","201712356"};
		int [] mathScores = {6,5,7,4,6,8,3,7,6,5,4,8};
		String [] admissionStatuses = new String [studNums.length];
		int [] apsScores=new int [studNums.length];
		int [] testScores = new int [studNums.length];
		int aptitude = 0;
		Scanner sc = new Scanner (System.in);
		Random rd =new Random ();
		
		for(int i=0 ; i < apsScores.length; i++){
			apsScores[i] = rd.nextInt(42-16)+16;
		}
		displayAdmissionStatus(studNums , apsScores, testScores, mathScores, admissionStatuses);
		String option = getUserOption();
		
		while(!(option.equalsIgnoreCase("ESC"))){
			switch(option){
				case "PAA":{
					populateTestScores(testScores , studNums);
					determineAdmissionStatus(apsScores , testScores, admissionStatuses, mathScores);
					displayAdmissionStatus(studNums , apsScores, testScores , mathScores, admissionStatuses);
				}break;
				case "SRC":{
					System.out.print("Enter the student number you wantbto enter: ");
					String studSearch = sc.nextLine();
					int index = searchAdmissionStatus(studNums ,studSearch );
					if(index != -1){
						System.out.println(studNums[index]+" with admission status "+ admissionStatuses[index]);
					}else{
						System.out.println(studSearch+" is not found");
					}
				}break;
				case "ESC":{
					System.out.println("The program is exiting!!!");
				}break;
				default : {
					System.out.println("Invalid option");
				}
			}
			option = getUserOption();
		}
	} 
	public static void populateTestScores(int[] testScores , String[] studNums){
		Scanner sc = new Scanner (System.in);
		for(int i=0 ; i < studNums.length; i++){
			System.out.print("Enter aptitude test score "+studNums[i]+" :");
			int aptitude = sc.nextInt();
			boolean validate = validateTestScoress(aptitude);
			if(!validate){
				System.out.println("Invalid test score!!!");
				System.out.print("Enter aptitude test score "+studNums[i]+" :");
			   aptitude = sc.nextInt();
			}else {
				testScores[i] = aptitude;
			}
		}
	} 
	public static boolean validateTestScoress(int aptitude){
		boolean validateScore=false;
		if(aptitude >=50 && aptitude  <=100){
			validateScore=true;
		}else {
			validateScore =false;
		}
		return validateScore;
	} 
	public static String getUserOption(){
		Scanner sc =new Scanner (System.in);
		System.out.println("\n\nSelect any option to start:");
		System.out.println("\tEnter PAA-Process Admission Application.\n\tEnter SCR-Search for students’ admission status.\n\tEnter ESC-Exit.");
		String option = sc.nextLine();
		
		return option;
	} 
	public static  void determineAdmissionStatus (int[] apsScores, int [] testScores, String[] admissionStatuses, int[] mathScores){
		for(int i=0; i < apsScores.length; i++){
			if(mathScores[i] >=5 && mathScores[i] <=8){
				if(apsScores[i] >=23 && apsScores[i] <= 42 && testScores[i] >=55 ){
					admissionStatuses[i] = "Accepted for mainstream";
				}else{
				admissionStatuses[i] ="Rejected for mainstream:APS Score/ Test Score!!";

				}
			}else if(!(mathScores[i] >=5) && !(mathScores[i] <=8)){
				admissionStatuses[i] ="Rejected for mainstream: Maths level results!!";
			}else if(mathScores[i] >=4 && mathScores[i] <=8){
				if(apsScores[i] >=16 && apsScores[i] <= 42 && testScores[i] >=50 ){
					admissionStatuses[i] = "Accepted for foundation";
				}else{
				admissionStatuses[i] ="Rejected for foundation:APS Score/ Test Score!!";

				}
			}else{
				admissionStatuses[i] ="Rejected for foundation: Maths level results!!";
			}
		}
	}
	public static int searchAdmissionStatus(String[] studNums, String studSearch){
		int index=-1;
		for(int i=0 ; i < studNums.length; i++){
			if(studNums[i].equals(studSearch)){
				index =i;
				break;
			}
		}
		return index;
	} 
	public static void displayAdmissionStatus(String[] studNums ,int[] apsScores,int[] testScores,int[] mathScores,String[] admissionStatuses){
		System.out.println("\t\t*********Sosha COllege Admission Status**********");
		System.out.println("\n\n************************************************************");
		System.out.printf("%-15s %-15s %-15s %-15s %-30s%n","Stud#","APS Score","Aptitude Test","Math Level","Admission Status");
		for(int i=0 ; i < studNums.length; i++){
			System.out.printf("%-15s %-15d %-15d %-15d %-30s%n",studNums[i] , apsScores[i], testScores[i], mathScores[i], admissionStatuses[i]);
		}
	} 
}