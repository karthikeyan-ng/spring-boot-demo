import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import deviceagent.DebugTimer;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.Modifier;

public class Agent {

	public static boolean isSkipped(int modifiers) {
    	return Modifier.isNative(modifiers) ||
    		Modifier.isAbstract(modifiers) ||
    		Modifier.isAnnotation(modifiers) ||
    		Modifier.isEnum(modifiers) ||
    		Modifier.isInterface(modifiers) ||
    		Modifier.isPackage(modifiers);
	}
	
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader classLoader, String className, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {

                if (className != null && className.startsWith("com/ford/fc") 
                			&& !className.contains("WeldClientProxy")
                			&& !className.contains("$Proxy$")
                			&& !className.contains("$Jaxb")
                			) {
                    // Javassist
                    try {
                    	ClassPool cp = new ClassPool();
                    	cp.appendClassPath(new LoaderClassPath(classLoader));
                    	
                        //ClassPool cp = ClassPool.getDefault();
                        
                        CtClass cc;
                        //cc = cp.get(className.replace("/", "."));
                        String simpleClassName = className.replace("/", ".");
                        cc = cp.makeClass(new java.io.ByteArrayInputStream(bytes));

                    	if (isSkipped(cc.getModifiers())) {
                    		return null;
                    	}
                    		

                        int cnt = 0;
                        for (CtMethod m : cc.getDeclaredMethods()) {
                        	if (isSkipped(m.getModifiers()))
                        		continue;
                        	
	                        /*m.addLocalVariable("elapsedTime", CtClass.longType);
	                        m.insertBefore("elapsedTime = System.currentTimeMillis();");
	                        m.insertAfter("{elapsedTime = System.currentTimeMillis() - elapsedTime;"
	                                + "System.out.println(\"xxxprofile: \" + elapsedTime);}");*/
	                        m.addLocalVariable("profiler_elapsedTime", CtClass.longType);
	                        m.insertBefore("profiler_elapsedTime = System.currentTimeMillis();");
	                        m.addLocalVariable("profiler_method_identifier", CtClass.longType);
	                        m.insertBefore("profiler_method_identifier = deviceagent.DebugTimer.methodEntered(\""+simpleClassName+"\", \""+m.getName()+"\");");
	                        m.insertAfter("deviceagent.DebugTimer.methodLeft(\""+simpleClassName+"\", \""+m.getName()+"\", profiler_method_identifier, System.currentTimeMillis() - profiler_elapsedTime);");
	                        cnt++;
                        }
                        byte[] byteCode = cc.toBytecode();
                        cc.detach();
                        if (cnt>0)
                        	DebugTimer.log("Instrumentalizing\t"+className+ "\t"+cnt+" methods");
                        return byteCode;
                    } catch (Exception ex) {
                    	DebugTimer.log("FAILED Instrumentalizing\t"+className+ "\t"+ex.getMessage());
                    	ex.printStackTrace(DebugTimer.print_line);
                    }
                }

                return null;
            }
        });
    }
}
