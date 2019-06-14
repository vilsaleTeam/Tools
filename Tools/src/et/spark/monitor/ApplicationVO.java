package et.spark.monitor;

public class ApplicationVO {

	private String id = "";
	private String name = "";
	private String startTime = "";
	private String endTime = "";
	private boolean completed = false;//完成状态
	private String message = "";//错误时为详细内容，正确为空
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ApplicationVO [id=" + id + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", completed=" + completed + ", message=" + message + "]";
	}
	
	
	
	
	
	
}
