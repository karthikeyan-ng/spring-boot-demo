package com.techstack.java7.trywithresource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * 1. Overview 
 * 		Support for try-with-resources – introduced in Java 7 – allows us
 * to declare resources to be used in a try block with the assurance that the
 * resources will be closed when after execution of that block. The resources
 * declared must implement the AutoCloseable interface.
 * 
 * 2. Using try-with-resources
 * 		Simply put, to be auto-closed, a resource must be both declared and 
 * initialized inside the try, as shown below:
 * 	<code>
 * 		try (PrintWriter writer = new PrintWriter(new File("test.txt"))) {
 *   		writer.println("Hello World");
 *		}
 * 	</code> 
 * 
 * @author KARTHIKEYAN N
 *
 */
public class TestJava7TryWithResource {
	
	private static String fileName = "src/main/resources/test2.txt";
	private static String testRead = "src/main/resources/read.txt";
	private static String testWrite = "src/main/resources/write.txt";
	
	public static void main(String[] args) {
		
		test_tryWithAutoClosable();
		test_oldSchoolTryCatchFinally();
		test_newWayTryWithResource();
		test_TryWithMultipleResources();
		test_CustomTryWithResource();
		test_ResourceClosingOrder();
	}

	private static void test_tryWithAutoClosable() {
		try (PrintWriter writer = new PrintWriter(new File(fileName))) {
		    writer.println("Hello World");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void test_oldSchoolTryCatchFinally() {
		
		Scanner scanner = null;
		try {
		    scanner = new Scanner(new File(fileName));
		    while (scanner.hasNext()) {
		        System.out.println(scanner.nextLine());
		    }
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} finally {
		    if (scanner != null) {
		        scanner.close();
		    }
		}
	}
	
	/**
	 * Java 7 way
	 */
	private static void test_newWayTryWithResource() {
		try (Scanner scanner = new Scanner(new File(fileName))) {
		    while (scanner.hasNext()) {
		        System.out.println(scanner.nextLine());
		    }
		} catch (FileNotFoundException fnfe) {
		    fnfe.printStackTrace();
		}
	}
	
	/**
	 * Multiple resources can be declared just fine in a try-with-resources 
	 * block by separating them with semicolon:
	 */
	public static void test_TryWithMultipleResources() {
		try (Scanner scanner = new Scanner(new File(testRead));
				PrintWriter writer = new PrintWriter(new File(testWrite))) {
			while (scanner.hasNext()) {
				writer.print(scanner.nextLine() + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A Custom Resource with AutoCloseable
	 * 
	 * To construct a custom resource that will be correctly handled by a
	 * try-with-resources block, the class should implement the Closeable or
	 * AutoCloseable interfaces, and override the close method:
	 */
	public static void test_CustomTryWithResource() {
		
		try(MyResource resource = new MyResource()) {
			resource.doPrintingJob();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Resource Closing Order
	 * 
	 * Resources that were defined/acquired first will be closed last; let’s look at an example of this behavior:
	 */
	public static void test_ResourceClosingOrder() {
		try (AutoCloseableResourcesFirst af = new AutoCloseableResourcesFirst();
				AutoCloseableResourcesSecond as = new AutoCloseableResourcesSecond()) {

			af.doSomething();
			as.doSomething();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class MyResource implements AutoCloseable {
	
	@Override
	public void close() throws Exception {
		System.out.println("Closed MyResource");
	}
	
	public void doPrintingJob() {
		IntStream.range(0, 100).boxed().forEach(System.out::print);
	}
}

/**
 * Resource1
 * 
 * @author KARTHIKEYAN N
 *
 */
class AutoCloseableResourcesFirst implements AutoCloseable {
	 
    public AutoCloseableResourcesFirst() {
        System.out.println("Constructor -> AutoCloseableResources_First");
    }
 
    public void doSomething() {
        System.out.println("Something -> AutoCloseableResources_First");
    }
 
    @Override
    public void close() throws Exception {
        System.out.println("Closed AutoCloseableResources_First");
    }
}

/**
 * Resource2
 * @author KARTHIKEYAN N
 *
 */
class AutoCloseableResourcesSecond implements AutoCloseable {
	 
    public AutoCloseableResourcesSecond() {
        System.out.println("Constructor -> AutoCloseableResources_Second");
    }
 
    public void doSomething() {
        System.out.println("Something -> AutoCloseableResources_Second");
    }
 
    @Override
    public void close() throws Exception {
        System.out.println("Closed AutoCloseableResources_Second");
    }
}
