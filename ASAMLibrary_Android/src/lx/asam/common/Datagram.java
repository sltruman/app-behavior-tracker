package lx.asam.common;

public class Datagram {
	static {
		System.load("/system/lib/lxasamcommon.so");
	}
	
	public static native boolean send(byte[] data);
	public static native int getpid();
}
