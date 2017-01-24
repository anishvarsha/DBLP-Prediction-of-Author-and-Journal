import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerateProb {
	public static void calProb(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
		String line = null, author = "";
		Map<String,Integer> map = new HashMap<String,Integer>();
		int jCount = 0;
		
		String result = "";
		while((line=br.readLine())!=null){
			String str[] = line.split("\\|");
			if(str[0].equals(author)){
				if(!map.containsKey(str[1]))
					map.put(str[1], 1);
				else
					map.put(str[1], map.get(str[1]) + 1);
				jCount++;
			}else{
				if(!author.equals("")){
					result += genContent(author, map, jCount);
					if(result.length()>200000){
						writeFile(result);
						result = "";
					}
				}
				map.clear();
				author = str[0];
				jCount=1;				
				map.put(str[1], 1);
			}
		}
		br.close();
		
		if(result.length()>0)
			writeFile(result);
	}
	
	public static String genContent(String author, Map<String,Integer> map, int jCount){
		String result = "";
		for(String str: map.keySet())
			result += author+"|"+str+"|"+(map.get(str)/(double)jCount)+"\n";
		return result;
	}
	
	public static void writeFile(String result) throws IOException{
		String fileName = "C:/Users/Suraj/Desktop/UTD/SEM 4/ML/Project/code/author_category.txt";
		BufferedWriter br = new BufferedWriter(new FileWriter(new File(fileName), true));
		br.write(result);
		br.close();
	}
	
	public static void main(String[] args) throws IOException {
		calProb("C:/Users/Suraj/Desktop/UTD/SEM 4/ML/Project/code/dblp_master_category.txt");
	}
}
