//
//package oss.util;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.model.ObjectMetadata;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import et.vis.oss.account.vo.OssAccountEnum;
//import net.sf.json.JSONObject;
//
///**
// * 文件上传至阿里云oss服务器。
// */
//public class OSSObjectUpload {
//	private static Logger log = LogManager.getLogger(OSSObjectUpload.class);
//	
//
//	public static String uploadImageToAliyun(File file) throws Exception {
//		return uploadImageToAliyun(OssAccountEnum.DEFAULT, file);
//	}
//	
//	/**
//	 * 文件上传至阿里云oss服务器。
//	 * @throws Exception
//	 * 
//	 */
//	public static String uploadImageToAliyun(OssAccountEnum accountVO, File file) throws Exception {
//		log.info("--**OSSObjectUpload.uploadFileToAliyun() 开始上传图片到oss*--" + DateUtilsCommon.getTodayFormat());
//		// 1,上传的服务器 节点,可内网,课外网, 2,access_id, 3.access_keysecret
//		OSSClient client = new OSSClient(accountVO.getEndpoint(), accountVO.getAccessKeyId(), accountVO.getAccessKeySecret());
//		String bucket_name = accountVO.getBucketName();
//		
//		ObjectMetadata objectMeta = new ObjectMetadata();
//		objectMeta.setContentLength(file.length());
//		// 可以在metadata中标记文件类型
//		objectMeta.setContentType("image/jpeg");
//		objectMeta.setExpirationTime(DateUtilsCommon.getAfterDate(300));
//		InputStream input = null;
//		String fileName = file.getName();
//		// 通过传递过来的文件名，用下划线截取出用户编码作为文件夹名。
//		int i = fileName.indexOf("_");
//		String key = null;
//		if (i <= 0) {
//			key = "temp/" + fileName;
//		} else {
//			key = fileName.substring(0, i) + "/" + fileName;
//		}
//		try {
//			input = new FileInputStream(file);
//			client.putObject(bucket_name, key, input, objectMeta);
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("--**oss upload image failed ,filename=" + fileName, e);
//			throw e;
//		} finally {
//			if (input != null) {
//				input.close();
//			}
//		}
//		log.info("--**OSS upload image successful, filename =/" + key + "," + DateUtilsCommon.getTodayFormat());
//		return accountVO.getAccessUrl() +"/"+ key;
//	}
//
//	/**
//	 * 
//	 * @param content   html contet 
//	 * @param bucket_name oss bucket_name
//	 * @param key  path+fileName  20160606/temp.html
//	 * @return
//	 * @throws Exception
//	 */
//	public static String uploadHtmlFileToAliyun(OssAccountEnum accountVO, String content, String key) throws Exception {
//		log.info("--**OSSObjectUpload.uploadHtmlFileToAliyun() 开始上传html到oss*--");
//		// 1,上传的服务器 节点,可内网,课外网, 2,access_id, 3.access_keysecret
//		OSSClient client = new OSSClient(accountVO.getEndpoint(), accountVO.getAccessKeyId(), accountVO.getAccessKeySecret());
//		String bucket_name = accountVO.getBucketName();
//		
//		ObjectMetadata objectMeta = new ObjectMetadata();
//		// 可以在metadata中标记文件类型
//		objectMeta.setContentType("text/html");
//		objectMeta.setExpirationTime(DateUtilsCommon.getAfterDate(300));
//		InputStream input = null;
//
//		try {
//			input = new ByteArrayInputStream(content.getBytes("UTF-8"));
//			client.putObject(bucket_name, key, input, objectMeta);
//		} catch (Exception e) {
//			log.error("--**oss upload html failed ,filename="+key , e);
//			throw e;
//		} finally {
//			try {
//				input.close();
//			} catch (IOException e) {
//			}
//		}
//		String path = accountVO.getAccessUrl() + "/" + key;
//		log.info("--**OSS upload html successful, filename = "+path);
//		return path;
//	}
//	
//
//	/**
//	 * 将Json对象以字节流形式上传到oss指定文件
//	 * @param accountVO
//	 * @param content
//	 * @param key
//	 * @return
//	 * @throws Exception
//	 */
//	public static String uploadJsonStringToAliyun(OssAccountEnum accountVO, JSONObject jsonObject, String key) throws Exception {
//		log.info("--**OSSObjectUpload.uploadStringToAliyun() 开始上传字符串到oss*--");
//		String bucket_name = accountVO.getBucketName();
//		// 1,上传的服务器 节点,可内网,课外网, 2,access_id, 3.access_keysecret
//		OSSClient client = new OSSClient(accountVO.getEndpoint(), accountVO.getAccessKeyId(), accountVO.getAccessKeySecret());
//		ObjectMetadata objectMeta = new ObjectMetadata();
//		// 可以在metadata中标记文件类型
//		objectMeta.setContentType("application/json");
//		objectMeta.setContentEncoding("gzip");
//		
//		// 上传文件流。
//		InputStream inputStream = null;
//		try {
//			inputStream = new ByteArrayInputStream(jsonObject.toString().getBytes("UTF-8"));
//			client.putObject(bucket_name, key, inputStream, objectMeta);
//		}catch (Exception e) {
//			log.error("--**oss upload html failed ,filename="+key , e);
//			throw e;
//		} finally {
//			// 关闭OSSClient。
//			if (client != null) {
//				client.shutdown();
//			}
//			if (inputStream != null) {
//				inputStream.close();
//			}
//		}
//		String path = accountVO.getAccessUrl()  +"/"+ key;
//		log.info("--**OSS upload html successful, filename = "+path);
//		return path;
//	}
//	
//	public static void main(String[] args) throws Exception {
//		
//	}
//
//}
