import java.util.*;
import java.lang.*;
import java.io.*;

/* @author Luthfi Kurnia Putra
	Kelas ini akan membuat sebuah permainan Quiz Acak Kata
	dimana pemain harus menebak kata yang telah diacak hurufnya 
	oleh program
 */
public class Quiz
{
	static int indexPresented;
	static int size;
	static BufferedReader br;
	static BufferedReader bf;
	static ArrayList<String> fileContent;
	static String arrayOfWords[];
	static String jumbledWords;
	static String answer;
	static Random rand;
	static boolean stopQuiz = false;
	
	public static void main (String[] args) throws java.lang.Exception
	{
		// Inialisasi Objek-objek beserta konten kata-kata yang ada pada kuis
		// Dalam kasus ini yang disediakan kata-kata yang disediakan adalah
		// 'buku', 'sampah', 'roti', 'kambing', 'nasi', 'goreng', 'sayur', dan 'tumis' 
		fileContent = new ArrayList<String>();
		br = new BufferedReader(new InputStreamReader(System.in));
		bf = new BufferedReader(new FileReader("words.txt"));
		readFile();
		rand = new Random(); 
		size = arrayOfWords.length;
	
		// Display judul kuis
		System.out.println("=============== Selamat Datang di Tebak Kata =================");
		System.out.println("");
		startQuiz();
	}

	public static void readFile() throws IOException {
		String read = "" ;
		while ( (read = bf.readLine()) != null) {
			fileContent.add(read);
		}

		arrayOfWords = new String [fileContent.size()];

		for (int i = 0; i < fileContent.size(); i++) {
			arrayOfWords[i] = fileContent.get(i);
		}
	}
	
	// fungsi ini memilih secara random kata-kata yang akan dijadikan soal
	public static void generateProblem() {
		indexPresented = rand.nextInt(size);
		answer = arrayOfWords[indexPresented];
	}
	
	// fungsi ini mengacak string jawaban menjadi sebuah kata acak / 'jumbled'
	public static void jumbleWords() {
		String tempString = "";
		int answerSize = answer.length();
		int arrayOfCharIndex [] = new int[answerSize];
		
		// mengisi loop dengan index antara 1 - size dari array kata-kata soal
		for (int i = 0; i < answerSize; i++){
			arrayOfCharIndex[i] = i;
		}
		
		// array index kemudian diacak menggunakan algoritma Fisher-Yates
		for (int i = answerSize - 1; i > 0; i--)
    	{
      		int index = rand.nextInt(i + 1);
      		
      		int temp = arrayOfCharIndex[index];
      		arrayOfCharIndex[index] = arrayOfCharIndex[i];
      		arrayOfCharIndex[i] = temp;
    	}
    	
    	// pada variable String temp dibuat sebuah string berisi kata yang sudah acak sesuai
    	// dengan index pada array arrayOfCharIndex
    	for (int i = 0; i < answerSize; i++) {
    		tempString = tempString + answer.charAt(arrayOfCharIndex[i]);
    	}

    	// nilai jumbledWords menjadi sebuah kata acak yang baru
    	jumbledWords = tempString;
    	System.out.println("Tebak kata: " + jumbledWords);
	}
	

	// fungsi yang berguna sebagai 'recursive loop' untuk kuis, dimana kuis akan dilanjutkan 
	// jika player ingin melanjutkan (dengan mengetikkan 'Ya') dan permainan akan dihentikan
	// ketika player mengetikkan 'exit'
	public static void presentQuiz() {
		presentQuizHelper(0);
		
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
	
	// fungsi yang berguna untuk memanggil recursive loop jikalau player menjawab salah. Pertanyaan
	// akan diulang kembali dan loop tidak akan selesai sampai player menjawab dengan benar.
	public static void presentQuizHelper(int count) {
		System.out.print("Jawab : ");
		String ans = "";
		try{
			ans = br.readLine();
		}
		catch (IOException e){}
		
		if (ans.equals(answer)){
			System.out.println("BENAR!");
		}else if (count < 2) {
			System.out.println("SALAH! Silakan coba lagi");
			presentQuizHelper(count+1);
		}else{

			// Kode yang memberi kesempatan pemain untuk menyerah dari permainan. Jika pemain
			// telah salah menjawab sebanyak 3 kali, maka opsi ini akan muncul
			System.out.println("MASIH SALAH! Apakah anda ingin menyerah?");
			System.out.println("Ketik 'Ya' untuk membuka jawaban");

			String forfeit = "";
			try{
			forfeit = br.readLine();
			}
			catch (IOException e){}

			if (forfeit.equalsIgnoreCase("Ya")){
				System.out.println("Jawabannya adalah : " + answer);
			}else {
				presentQuizHelper(0);
			}
		} 
	}
	
	// fungsi untuk kuis yang berguna untuk mengiterasi kuis pada program
	public static void startQuiz() {
		
		while (!stopQuiz){
			generateProblem();
			jumbleWords();
			presentQuiz();
		}
	}
}