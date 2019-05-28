package et.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 不好分类的工具包
 * @author 李雅翔
 * @date 2018年1月2日
 */
public class OtherToolKit{
	
	private static long start_date = 0;//获取程序运行时间
	private static final String ALLCHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//加密字符串使用,活动使用
	
	public static void main(String[] args) {
//		String email = "lyx@e-teng.com.cn";
//		System.out.println(isEmailAddress(email));
		System.out.println(getRandomNumberString(4));
	}
	/**
	 * 判断输入的字符串是否为数字,只匹配正整数
	 * @param str
	 * @return true：是
	 */
	public static boolean isNumber(String str) {
		String regex = "[0-9]+\\.?[0-9]*";
		Pattern p = Pattern.compile(regex);  
		Matcher matcher = p.matcher(str); 
		return matcher.matches();
	}
	
	/**
	 * 判断输入字符串是不是手机号
	 * @param str
	 * @return true：是
	 */
	public static boolean isPhoneNumber(String str) {
		String regex = "^\\d{11}$";
		Pattern p = Pattern.compile(regex);  
		Matcher matcher = p.matcher(str); 
		return matcher.matches();
	}
	
	/**
	 * 判断输入字符串是不是邮箱
	 * @param str
	 * @return true：是
	 */
	public static boolean isEmailAddress(String str) {
		String regex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
		Pattern p = Pattern.compile(regex);  
		Matcher matcher = p.matcher(str); 
		return matcher.matches();
	}
		
	/**
	 * 设置程序开始运行时间
	 */
	public static long setRunTime() {
		start_date = System.currentTimeMillis();
		return start_date;
	}
	
	/**
	 * 获取程序从调用开始方法开始，运行的耗时，运行时间会打印到日志
	 * 
	 * @return long 毫秒
	 * @throws Exception
	 */
	public static long getRunTime() throws Exception {
		if (start_date == 0) 
			throw new Exception("请先调用获取开始时间方法(setRuntime())，再调用结束时间方法(getRuntime())");
		long runningTime = getRunTime(start_date);
		start_date = System.currentTimeMillis();
		return runningTime;
	}
	
	/**
	 * 获取程序从调用开始方法开始，运行的耗时，运行时间会打印到日志
	 * 
	 * @param date 程序开始运行时间
	 * @return long 毫秒
	 * @throws Exception
	 */

	public static long getRunTime(long date) throws Exception {
		if (date <= 0) 
			throw new Exception("输入日期必须大于0");
		long runningTime = System.currentTimeMillis()-date;
		return runningTime;
	}
	
	/**
	 * @功能说明:在日志文件中，打印异常堆栈 
     * @param Throwable 
     * @param strLine	截取异常栈的行数
     * @return:String 
	 */
    public static String logExceptionStack(Throwable e, int strLine) {  
        StringWriter sw = new StringWriter(); 
        e.printStackTrace(new PrintWriter(sw));
        if (strLine == 0) {
			return sw.toString();
		}else {
			return StringProcessingUtils.subStringLine(sw.toString(), strLine);
		}
		
    }  
	
    /**
     * 获取文件内容,以字符串形式返回
     * @param path  src/main/java/et/vis/area/daily/api/区域的一天-json.json
     * @return
     * @throws IOException
     */
	public static String getFileContent(String path) throws IOException {
		File f = new File(path);
		if (!f.exists()) {
			return "";
		}
		FileReader reader = new FileReader(f);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder stringBuilder= new StringBuilder();
        String str = null;
        while((str = br.readLine()) != null) {
        	stringBuilder.append(str.trim());
        }  
        br.close();
       return stringBuilder.toString();
	}
	
	/**
	 * 分转化为元
	 * @param FenMoney 分金额 100
	 * @return		       元金额 1.0
	 */
	public static Double FenTransferYuan(Double FenMoney) {
		if (FenMoney == null) {
			return FenMoney;
		}
		return FenMoney/100;
	}
	
	/**
	 * 加密字符串 用于控制 活动简拼
	 * @param str 要加密的字符串
	 * @return
	 */
	public static String encryptedStr(String str) {
		char[] cs = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : cs) {
			sb.append(ALLCHAR.charAt(c%36));
		}
		return sb.toString();
	}
	
    

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字) 
     * @param length  				随机字符串长度 
     * @return 随机字符串 
     * 
     */
	public static String getRandomString(int length) {
		StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        }  
        return sb.toString(); 
	}
	
    /**
     * 返回一个定长的随机数字(只包含、数字) 
     * @param length  				多位随机数字
     * @return 随机字符串 
     * 
     */
	public static String getRandomNumberString(int length) {
		StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(random.nextInt(10));  
        }  
        return sb.toString(); 
	}
}
