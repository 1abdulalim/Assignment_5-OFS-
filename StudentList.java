import java.io.*;
import java.text.*;
import java.util.*;
public class StudentList {
	public static void main(String[] args) {
        // Checking if argument is valid or not
		char checkerChar = args[0].substring(0).charAt(0);
		if(args[0].length() >1 && (checkerChar == 'a' || checkerChar == 'r' || checkerChar == 'c')){
			System.out.println(Constant.validText2);
			return;
		}
		if(checkerChar!='a' && checkerChar!= 'r' && checkerChar!= 'c' && checkerChar!='?' && checkerChar!='+'){
			System.out.println(Constant.validText);
			return;
		}
		// This function prints all the Students name from the text file
		if(args[0].equals("a")) {
			System.out.println(Constant.loadingText);
			try {
				// contain all the students name
				String StudentName[] = getBufferedReader().readLine().split(", ");
				for(String student : StudentName) {
					System.out.println(student);
				}
			}
			catch (Exception e){

			}
			System.out.println(Constant.dataLoaded);
		}
		// This function prints a random student name from the text file
		else if(args[0].equals("r")) {
			System.out.println(Constant.loadingText);
			try {
				String StudentName[] = getBufferedReader().readLine().split(", ");
				// Index will contain a value between 0 and Length of the StudentInfo array
				int randomIndex = new Random().nextInt(StudentName.length);
				System.out.println(StudentName[randomIndex]);
			}
			catch (Exception e){
				System.out.println(Constant.dataNotFound);
			}
			System.out.println(Constant.dataLoaded);
		}
		// This function add a new student name to the last of the text file
		else if(args[0].contains("+")){
			System.out.println(Constant.loadingText);
			try {
				// Creating a BufferWriter to access text file
				BufferedWriter bufferedWriter = new BufferedWriter(
						new FileWriter(Constant.textFile, true));
				String line = getBufferedReader().readLine();
				// This function will clear al the data in text file
				clearAll();
				String newLine = line;
				String given = args[0].substring(1);
				newLine+=", ";
				newLine+=given;
				String formatedDate= new SimpleDateFormat("dd/mm/yyyy-hh:mm:ss a").format(new Date());
				// This line add new name to the last of the string with update time and date
				bufferedWriter.write(newLine + "\nList newLine updated on " +formatedDate);
				bufferedWriter.close();
			}
			catch (Exception e){

			}
			System.out.println(Constant.dataLoaded);
		}
		//	This function Tells if a specific name is in the text file or not
		else if(args[0].contains("?")) {
			System.out.println(Constant.loadingText);
			try {
				String StudentInfo[] = getBufferedReader().readLine().split(", ");
				int idx;
				for( idx = 0; idx<StudentInfo.length; idx++) {
					if(StudentInfo[idx].trim().equals(args[0].substring(1).trim())) {
						System.out.println(Constant.dataFound);
						break;
					}
				}
				if(idx>=StudentInfo.length){
					System.out.println("The word you are searching for "+ "( " + args[0].substring(1).trim() + " )" + " is not found!");
				}
			}
			catch (Exception e){

			}
			System.out.println(Constant.dataLoaded);
		}
		//	This function counts the number of words in the text file
		else if(args[0].contains("c")) {
			System.out.println(Constant.loadingText);
			try {
				String StudentCharArray[] = getBufferedReader().readLine().split(", ");
				System.out.println(StudentCharArray.length +" word(s) found ");
			}
			catch (Exception e){

			}
			System.out.println(Constant.dataLoaded);
		}
	}

	private static void clearAll() throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter(Constant.textFile, false), false);
		printWriter.flush();
		printWriter.close();
	}
	private static BufferedReader getBufferedReader() throws FileNotFoundException {
		return new BufferedReader(
				new InputStreamReader(
						new FileInputStream(Constant.textFile)));
	}
}