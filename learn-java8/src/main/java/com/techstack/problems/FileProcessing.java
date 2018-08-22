/**
 * 
 */
package com.techstack.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
/**
 * How to read files using Thread concepts.
 * 
 * 
 * Producer - Consumer problem - without using ExecutorService
 * 
 * @author Karthikeyan N
 *
 */
public class FileProcessing {

    private static final int NUMBER_OF_CONSUMERS = 3;
    private static final int NUMBER_OF_PRODUCERS = 3;
    private static final int QUEUE_SIZE = 2;
    private static BlockingQueue<String> queue;
    private static Collection<Thread> producerThreadCollection, allThreadCollection;

    public static void main(String[] args) {
        producerThreadCollection = new ArrayList<Thread>();
        allThreadCollection = new ArrayList<Thread>();
        queue = new LinkedBlockingDeque<String>(QUEUE_SIZE);

        createAndStartProducers();
        createAndStartConsumers();

        for(Thread t: allThreadCollection){
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("Controller finished");
    }

    private static void createAndStartProducers(){
        for(int i = 1; i <= NUMBER_OF_PRODUCERS; i++){
            Producer producer = new Producer(Paths.get("src/main/resources/multithreading/producer_consumer/test"+i+".txt"), queue);
            Thread producerThread = new Thread(producer,"producer-"+i);
            producerThreadCollection.add(producerThread);
            producerThread.start();
        }
        allThreadCollection.addAll(producerThreadCollection);
    }

    private static void createAndStartConsumers(){
        for(int i = 0; i < NUMBER_OF_CONSUMERS; i++){
            Thread consumerThread = new Thread(new Consumer(queue), "consumer-"+i);
            allThreadCollection.add(consumerThread);
            consumerThread.start();
        }
    }

    public static boolean isProducerAlive(){
        for(Thread t: producerThreadCollection){
            if(t.isAlive())
                return true;
        }
        return false;
    }
}

/**
 * Here's the code for the Producer class that will be used to create all of the
 * threads whose job it is to read a single file each. You'll see that the
 * producer reads a specific file line by line and adds those lines to the queue
 * as there is space available by making use of the put method.
 * 
 * @author Karthikeyan N
 *
 */
class Producer implements Runnable {

    private Path fileToRead;
    private BlockingQueue<String> queue;

    public Producer(Path filePath, BlockingQueue<String> q) {
        fileToRead = filePath;
        queue = q;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = Files.newBufferedReader(fileToRead);
            String line;
            while((line = reader.readLine()) != null){
                try {
                    queue.put(line);
                    System.out.println(Thread.currentThread().getName() + " added \"" + line + "\" to queue, queue size: " + queue.size());             
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName()+" finished");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

/**
 * Here is the Consumer class that will be responsible for reading data from the
 * queue and processing it appropriately. Notice that this class does not use
 * the take method. I wrote it this way so that the program would end after
 * processing all the files. If you want the consumers to stay alive you could
 * replace poll with take (along with a few other minor adjustments to the run
 * method like handing the InterruptedException that might occur while waiting
 * for take to return a value).
 * 
 * @author Karthikeyan N
 *
 */
class Consumer implements Runnable {
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> q) {
        queue = q;
    }

    public void run(){
        while(true){
            String line = queue.poll();

            if(line == null && !FileProcessing.isProducerAlive())
                return;

            if(line != null){
                System.out.println(Thread.currentThread().getName()+" processing line: "+line);
                //Do something with the line here like see if it contains a string
            }

        }
    }
}
