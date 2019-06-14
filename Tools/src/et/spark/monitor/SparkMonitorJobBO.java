//package et.spark.monitor;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//import java.util.TimeZone;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.jgroups.util.Tuple;
//
//import et.common.restful.ARestModule;
//import et.common.utils.DateUtilsCommon;
//import et.common.utils.GetHttpRequestJson;
//import et.vis.timed.task.job.SparkMonitorTimedTask;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
///**
// * 获取指定日期 spark执行失败的任务列表
// * @author lyx
// * @date:   Sep 18, 2018 5:02:59 PM
// */
//public class SparkMonitorJobBO extends ARestModule {
//	//spark-history restAPI地址
//	private static String httpUrl = "http://114.215.42.116:18080/api/v1/applications";
//	private static Logger log = LogManager.getLogger(SparkMonitorTimedTask.class);
//	
//	private static List<ApplicationVO> errorList = new ArrayList<>();//错误消息内容
//	
//	/**
//	 * 获取指定日期执行失败的spark任务
//	 * @param now_date
//	 * @return ApplicationVO
//	 * @throws Exception
//	 */
//	public List<ApplicationVO> sparkJobsImplementation(String now_date) throws Exception {
//		log.info("----------- 获取 {} 执行失败的spark 任务情况    BO 开始 -----------", now_date);
//		log.info("----------- 获取所有spark 任务  -----------");
//		List<ApplicationVO> list = applications(now_date);
//		log.info("今日执行的 spark任务有:" + list.toString());
//		for (ApplicationVO applicationVO : list) {
//			Tuple<Boolean, String> tuple = applicationsJobs(applicationVO.getId());
//			if (!tuple.getVal1()) {
//				applicationVO.setMessage(tuple.getVal2());
//				errorList.add(applicationVO);
//			}
//		}
//		log.info("-----------  {} spark执行情况:  总共任务{}个,错误任务:{}个 。  -----------", now_date, list.size(), errorList.size());
//		return errorList;
//	}
//
//	/**
//	 * 查询指定时间 执行的spark任务
//	 * 
//	 * @param now_date
//	 * @throws Exception
//	 */
//	private static List<ApplicationVO> applications(String now_date) throws Exception {
//		List<ApplicationVO> list = new ArrayList<>();
//		
//		SimpleDateFormat inputSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'GMT'");
//		inputSdf.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
//    	SimpleDateFormat outputSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
//    	
//		ApplicationVO applicationVO = null;
//		JSONObject jsonObject = null;
//		JSONObject jsonObject2 = null;
//		String requestStr = GetHttpRequestJson.sendGet(httpUrl);
////		String requestStr = getJsonFile();//本地测试使用
//		JSONArray requestJsonArray = JSONArray.fromObject(requestStr);
//		for (int i = 0; i < requestJsonArray.size(); i++) {
//			jsonObject = requestJsonArray.getJSONObject(i);
//			
//			jsonObject2 = jsonObject.getJSONArray("attempts").getJSONObject(0);//执行现行内容
//			String startTime = outputSdf.format(inputSdf.parse(jsonObject2.getString("startTime")));//格林威治时区换北京时区
//			if (DateUtilsCommon.differentDays(
//					DateUtilsCommon.getDateFormatter(startTime, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd"), now_date) != 0 ) {
//				//任务开始时间不是输入日期当天
//				continue;
//			}
//			log.info(jsonObject);
//			String endTime = outputSdf.format(inputSdf.parse(jsonObject2.getString("endTime")));
//			applicationVO = new ApplicationVO();
//			applicationVO.setStartTime(startTime);
//			applicationVO.setEndTime(endTime);
//			applicationVO.setId(jsonObject.getString("id"));
//			applicationVO.setName(jsonObject.getString("name"));
//			applicationVO.setCompleted(jsonObject2.getBoolean("completed"));
//			if (!applicationVO.isCompleted()) {
//				applicationVO.setMessage(jsonObject.toString());
//				errorList.add(applicationVO);
//			}else {
//				list.add(applicationVO);
//			}
//		}
//		return list;
//	}
//	
//	/**
//	 * 指定app-id的jobs执行情况
//	 * @param applicationId
//	 * @return 1: 是否是执行成功 2：执行失败返回数据内容 成功返回空
//	 * @throws Exception 
//	 */
//	private static Tuple<Boolean, String> applicationsJobs(String applicationId) throws Exception {
//		String url = httpUrl+"/"+applicationId+"/jobs";
//		String requestStr = GetHttpRequestJson.sendGet(url);
////		String requestStr = getJsonFile2();//本地测试使用
//		JSONArray requestJsonArray = JSONArray.fromObject(requestStr);
//		JSONObject jsonObject = null;
//		
//		for (int i = 0; i < requestJsonArray.size(); i++) {
//			jsonObject = requestJsonArray.getJSONObject(i);
//			if (jsonObject.containsKey("status") && !"SUCCEEDED".equals(jsonObject.getString("status"))) {
//				return new Tuple<Boolean, String>(false, requestJsonArray.toString());
//			}
//		}
//		return new Tuple<Boolean, String>(true, "");
//	}
//	
//	public static void main(String[] args) throws Exception {
//		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'GMT'");
//		//String time = sdf.format(new Date());
//		//System.out.println(time);
//		//String time = "2018-09-16T19:28:56.862GMT";
//		
//		//System.out.println("2018-09-16 19:28:56".substring(11));
////		applications("2018-09-17");
//		long start = System.currentTimeMillis();
////		applicationsJobs("application_1510130120285_1661");
//		List<ApplicationVO> list  = applications("2018-10-23");
//		for (ApplicationVO applicationVO : list) {
//			System.out.println(applicationVO);
//			
//		}
//		long end = System.currentTimeMillis();
//		System.out.println(end-start);
////		new SparkMonitorJobBO().sparkJobsImplementation("2018-09-17");
//	}
//	
//	//spark所有任务查询返回数据
////	public static String getJsonFile() throws IOException {
////		FileReader reader = null;
////		reader = new FileReader("C:\\Users\\Admin\\Desktop\\spark执行情况接口测试文件\\applications.txt");
////        BufferedReader br = new BufferedReader(reader);
////        StringBuilder stringBuilder= new StringBuilder();
////        String str = null;
////        while((str = br.readLine()) != null) {
////        	stringBuilder.append(str.trim());
////        }  
////        br.close();
////       return stringBuilder.toString();
////	}
//	
//	//指定app-id查询返回数据
////	public static String getJsonFile2() throws IOException {
////		FileReader reader = null;
////		reader = new FileReader("C:\\Users\\Admin\\Desktop\\spark执行情况接口测试文件\\applicationsjob.txt");
////        BufferedReader br = new BufferedReader(reader);
////        StringBuilder stringBuilder= new StringBuilder();
////        String str = null;
////        while((str = br.readLine()) != null) {
////        	stringBuilder.append(str.trim());
////        }  
////        br.close();
////       return stringBuilder.toString();
////	}
//}
