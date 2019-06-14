package utils.gzip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import et.common.utils.GetHttpRequestJson;


public class HttpPostGzipTest {
    public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\Admin\\Desktop\\成本对象分析实际数据生成.json";
		String json = getJsonFile(path);
		System.out.println(json);
		String json1 = GetHttpRequestJson.sendPostByGZIP("http://192.168.0.69/test/20190527/", json);
		System.out.println(json1);

	}

	public static String getJsonFile(String path) throws IOException {
		File file = new File(path);
		System.out.println(file.length());//bytes
		FileReader reader = new FileReader(file);
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
