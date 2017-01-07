package com.util.类;

import java.io.IOException;
import java.io.InputStream;

public class 获取上传的txt的编码 {
	public static String getTxtCode(InputStream is) {
		String code;
		int p;
		try {
			p = (is.read() << 8) + is.read();
			switch (p) {
			case 0xefbb:
				code = "UTF-8";
				break;
			case 0xfffe:
				code = "Unicode";
				break;
			case 0xfeff:
				code = "UTF-16BE";
				break;
			default:
				code = "GBK";
				break;
			}
			return code;
		} catch (IOException e) {
			e.printStackTrace();
			return "UTF-8";
		}
	}
}
