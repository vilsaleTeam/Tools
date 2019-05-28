package utils.gzip;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.vilsale.database.TestDB;

/**
 * gzip演示
 * @author lyx
 * @date:   May 28, 2019 4:44:14 PM
 */
public class GZIPDmo {
	
	/**
	 * 保存使用GZIP压缩的数据到数据库，以二级制形式存储
	 * @return
	 * @throws Exception
	 */
	public static String saveData() throws Exception {
		PreparedStatement st = null;
		try {
			
			String SQL = "insert into journal_data(data_message,md5_str,create_date,data_id,ts) values(?,'F130C30AC74DBC355ECE5EB04D189B15','2019-05-28','0e54b7c6-c917-4f7a-b914-66c12036e1e1','2019-05-28 09:33:48')";
			st= TestDB.getPrepareStatement(SQL);
			st.setBinaryStream(1, GZIP.compressReturnInputSteam("abc"));
			st.execute();
			return "";
		} finally {
			TestDB.close();
		}
	}
	
	/**
	 * 存储时使用INSERT INTO 表名(字段名) VALUES (COMPRESS('数据')); 插入数据
	 * 使用mysql自带的GZIP压缩方法，压缩数据，使用mysql自带解压缩方法，解压数据
	 * @param data_id
	 * @return
	 * @throws Exception
	 */
	public String getContenData(String data_id) throws Exception {
		Statement st = null;
		try {
			st= TestDB.getStatement();
			ResultSet rs = st.executeQuery("SELECT UNCOMPRESS(data_message) FROM journal_data where data_id='"+data_id+"'");
			if(rs.next()){  
				Blob blob = rs.getBlob(1);  
				InputStream in = blob.getBinaryStream();  
				// 创建一个新的输出流
			    ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buffer = new byte[256];
			    int n = 0;
				// 将未压缩数据读入字节数组
			     while ((n = in.read(buffer)) >= 0){
			           out.write(buffer, 0, n);
			     }
				String string = out.toString();
				System.out.println(string);
			}  

		} finally {
			TestDB.close();
		}
		return "";
		
	}
	
	/**
	 * 数据库存储为GZIP压缩后的二级制数据，使用java解压缩GZIP数据
	 * @param data_id	输入id
	 * @return
	 * @throws Exception
	 */
	public String getDataByGZIP(String data_id) throws Exception {
		Statement st = null;
		try {
			st= TestDB.getStatement();
			ResultSet rs = st.executeQuery("SELECT data_message FROM journal_data where data_id='"+data_id+"'");
			if(rs.next()){  
				Blob blob = rs.getBlob(1);  
				InputStream in = blob.getBinaryStream();  
				String string = GZIP.unCompressReadByInputStram(in);
				System.out.println(string);
			}  
			return "";
		} finally {
			TestDB.close();
		}
		
	}
	
}
