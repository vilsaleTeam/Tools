package utils.gzip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * json压缩工具类
 * @author lyx
 * @date:   May 27, 2019 11:32:25 AM
 */
public class GZIPTest {

	public static void main(String[] args) throws IOException {
		String path = "C:\\Users\\Admin\\Desktop\\实际数据生成.json";
		String json = getJsonFile(path);
		System.out.println(json);
		String string = GZIP.compress(json);
		System.out.println(string);
		String string2 = GZIP.unCompress(string);
		
		System.out.println(string2);
		
//		 File file =new File("C:\\Users\\Admin\\Desktop\\成本对象分析实际数据生成_test.json");
//		 System.out.println(file.getName());
//		 FileWriter fileWritter = new FileWriter(file);
//		 fileWritter.write(string);
//		 fileWritter.close();
	}

	public static String getJsonFile(String path) throws IOException {
		FileReader reader = new FileReader(path);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder stringBuilder= new StringBuilder();
        String str = null;
        while((str = br.readLine()) != null) {
        	stringBuilder.append(str.trim());
        }  
        br.close();
       return stringBuilder.toString();
	}
}
