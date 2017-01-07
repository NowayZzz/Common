package zp.com.cn.code;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author tfq
 * @datetime 2011-10-13
 */
public class MD5加密解密 {

	
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 锟斤拷锟杰斤拷锟斤拷锟姐法 执锟斤拷一锟轿硷拷锟杰ｏ拷锟斤拷锟轿斤拷锟斤拷
	 */ 
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	public static void main(String args[]) {
		String s = new String("tangfuqiang");
		System.out.println("原始锟斤拷" + s);
		System.out.println("MD5锟斤拷" + string2MD5(s));
		System.out.println("锟斤拷锟杰的ｏ拷" + convertMD5(s));
		System.out.println("锟斤拷锟杰的ｏ拷" + convertMD5(convertMD5(s)));
		
		System.out.println(convertMD5("amlhbXh0"));

	}
}