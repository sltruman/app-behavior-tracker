package lx.asam.common;

import java.io.UnsupportedEncodingException;

public class Log {
	static {
		System.load("/system/lib/lxasamcommon.so");
	}

	public static void e(String message) {
		message = message + '\0';
		try {
			_e(message.getBytes("GBK"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
	private static native void _e(byte[] message);
}
