//package et.spark.monitor;
//
//import java.util.List;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.Response;
//
//import et.common.restful.ARestModule;
//import et.common.restful.ResponseBuilder;
//import net.sf.json.JSONObject;
//
///**
// * 每日定时任务监控API对外接口
// * @author jyj 2017-07-21
// */
//@Path(value="/api/spark")
//@Produces("application/json;charset=UTF-8")
//public class SparkMonitorJobAPI extends ARestModule{
//	
//	/**
//	 * 获取失败的定时任务 对外接口 
//	 * @param now_date
//	 * @return
//	 * 
//	 *  http://localhost:8080/vsar/api/spark/test?now_date=2018-10-23
//	 */
//	@GET
//	@Path("/test")
//	public Response getDailySparkJobTest(@QueryParam("now_date") String now_date){	
//		log.info("*********每日定时任务监控 获取失败的定时任务 对外接口 ，<参数列表>now_date:{}</参数列表>***********",now_date);
//		JSONObject ret = new JSONObject();
//		try {
//			SparkMonitorJobBO bo = new SparkMonitorJobBO();
//			List<ApplicationVO> list = bo.sparkJobsImplementation(now_date);
//			ret.put("code", "200");
//			ret.put("message", list.toString());
//			return ResponseBuilder.success(ret.toString());
//		} catch (Exception e) {
//			ret.put("code", "500");
//			ret.put("message", "数据查询失败");
//			return ResponseBuilder.error(ret.toString());
//		}
//	}
//}
