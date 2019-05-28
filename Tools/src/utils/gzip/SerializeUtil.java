package utils.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 对象序列化工具
 * @author lyx
 * @date:   May 22, 2019 4:26:34 PM
 */
public class SerializeUtil {
	
//	public static void main(String[] args) throws Exception {
//		JournalVO journalVO = new JournalVO();
//		journalVO.setJournal_id("aab");
//		String key = "aaaaaa011";
//		RedisUtil.setObject(key, journalVO, 100);
////		byte[] bs = serizlize(journalVO);
////		Object object = deserialize(bs);
//		
//		Object object = RedisUtil.getObject(key);
//		System.out.println((JournalVO)object);
//	}
	/**
	 * 序列化
	 * @param object
	 * @return
	 * @throws Exception 
	 */
    public static byte[] serizlize(Object object) throws Exception{
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } finally {
            if(baos != null){
                baos.close();
            }
            if (oos != null) {
                oos.close();
            }
        }
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     * @throws Exception 
     */
    public static Object deserialize(byte[] bytes) throws Exception{
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null; 
        try{
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        }catch(Exception e){
           throw e;
        }finally {
        	if (bais != null) {
        		bais.close();
			}
        	if (ois != null) {
				ois.close();
			}
        }
    }
}