import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class Quiz
{
	static int indexPresented;
	static int size;
	static BufferedReader br;
	static String arrayOfWords[];
	static String jumbledWords;
	static String answer;
	static Random rand;
	static boolean stopQuiz = false;
	
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		br = new BufferedReader(new InputStreamReader(System.in));
		rand = new Random(); 
		arrayOfWords = new String[] {"buku", "sampah", "roti", "kambing", "nasi", "goreng", "sayur", "tumis"};
		size = arrayOfWords.length;
	
	
		System.out.println("=============== Selamat Datang di Tebak Kata =================");
		System.out.println("");
		startQuiz();
	}
	
	public static void generateProblem() {
		indexPresented = rand.nextInt(size);
		answer = arrayOfWords[indexPresented];
	}
	
	public static void jumbleWords() {
		String tempString = "";
		int answerSize = answer.length();
		int arrayOfCharIndex [] = new int[answerSize];
		
		for (int i = 0; i < answerSize; i++){
			arrayOfCharIndex[i] = i;
		}
		
		for (int i = answerSize - 1; i > 0; i--)
    	{
      		int index = rand.nextInt(i + 1);
      		
      		int temp = arrayOfCharIndex[index];
      		arrayOfCharIndex[index] = arrayOfCharIndex[i];
      		arrayOfCharIndex[i] = temp;
    	}
    	
    	for (int i = 0; i < answerSize; i++) {
    		tempString = tempString + answer.charAt(arrayOfCharIndex[i]);
    	}

    	jumbledWords = tempString;
    	System.out.println("Tebak kata: " + jumbledWords);
	}
	
	public static void presentQuiz() {
		presentQuizHelper();
		
		System.out.println("");
		System.out.println ("Ingin main lagi?");
		System.out.println ("Ketik 'Ya' untuk main lagi");
		System.out.println ("Ketik 'exit' untuk keluar");

		String response = "";
		try{
			response = br.readLine();
			System.out.println("");
		}
		catch (IOException e){}
		
		if (response.equals("exit")){
			stopQuiz = true;
		}
	}
	
	public static void presentQuizHelper() {
		System.out.print("Jawab : ");
		String ans = "";
		try{
			ans = br.readLine();
		}
		catch (IOException e){}
		
		if (ans.equals(answer)){
			System.out.println("BENAR!");
		}else {
			System.out.println("SALAH! Silakan coba lagi");
			presentQuizHelper();
		}
	}
	
	public static void startQuiz() {
		
		while (!stopQuiz){
			generateProblem();
			jumbleWords();
			presentQuiz();
		}
	}
}