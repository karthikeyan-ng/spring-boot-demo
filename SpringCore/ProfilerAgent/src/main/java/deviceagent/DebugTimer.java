package deviceagent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DebugTimer {
	public static PrintWriter print_line; 

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	 

	public static synchronized void log(String s) {
		if (print_line == null) { 
			File file = new File("c:/domains/agent.log");
			FileWriter write;
			try {
				write = new FileWriter(file, true);
				print_line = new PrintWriter( write ); 
			} catch (IOException e) {
				print_line = new PrintWriter( System.out );
			} 
		}
		print_line.println(sdf.format(new Date()) + "\t" + s);
		print_line.flush();
		print_line.close();
		print_line = null;
	}
	
	public static long methodEntered(String className, String method) {
		return 0;
	}
	
	public static void methodLeft(String className, String method, long id, long elapsedTime) {
		if (elapsedTime > 5) {
			StringBuilder sb = new StringBuilder();
			
			sb.append("Profiling\t");
			sb.append(elapsedTime);
			sb.append('\t');
	
			StackTraceElement elems[] = Thread.currentThread().getStackTrace();

			sb.append(elems.length-2);

			/*sb.append(className);
			sb.append('#');
			sb.append(method);*/

			try {
				for (int i = 2; i < 2+3 && i < elems.length; i++) {
					sb.append('\t');
					sb.append(elems[i].getClassName());
					sb.append('#');
					sb.append(elems[i].getMethodName());
					sb.append(':');
					sb.append(elems[i].getLineNumber());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			log(sb.toString());
		}
	}
	
}
