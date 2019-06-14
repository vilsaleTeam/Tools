//package oss.util;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.text.ParseException;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.model.OSSObject;
//
//import et.vis.oss.account.vo.OssAccountEnum;
//import net.sf.json.JSONObject;
//
///**
// * 下载oss文件
// * @author lyx
// * @date:   2018年7月26日 上午11:11:07
// */
//public class OSSDownload {
//	
//	private static Logger log = LogManager.getLogger(OSSDownload.class);
//	
//
//	/**
//     * 下载oss文件
//     * @return
//	 * @throws IOException 
//     * @throws Exception
//     */
//    public static JSONObject downloadVisShareJsonFromOSS(String ossPath) throws ParseException, IOException {
//    	log.info("从oss下载指定文件,下载地址:{}", ossPath);
//		String pushDateStr = downloadFromOSS(OssAccountEnum.VIS_SHARE, ossPath);
//		if (pushDateStr == null || "".equals(pushDateStr)) {
//			return new JSONObject();
//		}
//		JSONObject jsonObject = JSONObject.fromObject(pushDateStr);
//		return jsonObject;
//	}
//    
//	/**
//     * 下载oss文件
//     * @return
//	 * @throws IOException 
//     * @throws Exception
//     */
//    public static JSONObject downloadJournalDataJsonFromOSS(String ossPath) throws ParseException, IOException {
//    	log.info("从oss下载指定文件,下载地址:{}", ossPath);
//		String pushDateStr = downloadFromOSS(OssAccountEnum.JOURNAL_DATA, ossPath);
//		if (pushDateStr == null || "".equals(pushDateStr)) {
//			return new JSONObject();
//		}
//		JSONObject jsonObject = JSONObject.fromObject(pushDateStr);
//		return jsonObject;
//	}
//    
//    /**
//     * 从oss下载文本数据，以字符串返回
//     * @param objectName	对象key
//     * @return
//     * @throws IOException
//     */
//    public static String downloadFromOSS(OssAccountEnum ossaccount,String key) throws IOException {
//    	log.info("从oss下载json数据,使用账户:{},下载地址:{}", ossaccount.getName(), key);
//        String endpoint = ossaccount.getEndpoint();
//        String accessKeyId = ossaccount.getAccessKeyId();
//        String accessKeySecret = ossaccount.getAccessKeySecret();
//        String bucketName = ossaccount.getBucketName();
//        OSSClient ossClient = null;
//        BufferedReader reader = null;
//        try {
//        	 ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//             // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
//        	 OSSObject ossObject = null;
//        	 try {
//        		 ossObject = ossClient.getObject(bucketName, key);
//			} catch (Exception e) {
//				log.error("<从oss下载json数据> 访问数据不存在返回null");
//				return null;
//			}
//             // 访问数据存在
//             reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
//             StringBuilder json = new StringBuilder();
//             String line = null;
//             while ((line = reader.readLine()) != null) {
//                 json.append(line);
//             }
//             return json.toString();
//		} finally {
//			if (reader != null) {
//				reader.close();
//			}
//			if (ossClient != null) {
//				ossClient.shutdown();
//			}
//		}
//    }
//}
