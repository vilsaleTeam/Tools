package et.common.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetFile {
	
	public static String getJsonFile() throws IOException {
//		FileReader reader = new FileReader("src/main/java/et/vis/corp/daily/api/公司的一天json数据样式.json");
		FileReader reader = new FileReader("C:\\Users\\Administrator\\Desktop\\期刊设置json.json");
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
