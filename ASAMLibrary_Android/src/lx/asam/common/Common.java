package lx.asam.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lx.org.json.JSONArray;
import lx.org.json.JSONObject;

import com.ibm.rational.rhapsody.animation.ArgData;

public class Common {
	public static void putArgs(JSONObject msg,ArgData[] args) {
		for(ArgData arg : args) {
			msg.append("ArgName",arg.getArgName());
			msg.append("ArgValue", arg.getArgValue());
			msg.append("ArgClass", arg.getArgClass().getName());
		}
	}
	
	public static ArgData[] getArgs(JSONObject msg) {
		if(msg.isNull("ArgClass")) {
			return null;
		}
		
		JSONArray names = msg.getJSONArray("ArgName");
		JSONArray values = msg.getJSONArray("ArgValue");
		JSONArray classes = msg.getJSONArray("ArgClass");

		int length = names.length();

		ArgData[] args = new ArgData[length];
				
		for(int i = 0;i < length;i++) {
			args[i] = new ArgData();
			args[i].setArgClassName(classes.getString(i));
			args[i].setArgName(names.getString(i));
			args[i].setArgValue(values.getString(i));
		}
		
		return args;
	}

	public static String toScript(ArgData[] args) {
		if(args == null) return "";
		
		ArgData arg = null;
		StringBuffer script = new StringBuffer();
		
		for(int i = 0;i < args.length;i++) {
			arg = args[i];
			script.append(arg.getArgValue());
			script.append(" : ");
			script.append(arg.getArgName());
//			script.append("");
//			script.append(arg.getArgClassName());
			
			if(i + 1 < args.length) {
				script.append(", ");
			}
		}
	
		String temp = 	script.toString();
		String regex = "((?=[\\x21-\\x7e]+)[^A-Za-z0-9])";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(temp);
		
		for(int i = 0,begin = 0;m.find();i++) {
			String t = m.group();
			begin = m.start() + i;
			script.replace(begin, begin + 1, "\\" + t);
		}

		return script.toString();
	}
}
