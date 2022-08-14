package designPatterns;

import animals.Animal;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * a Class that defines the ThreadPool Design pattern, Class that manage the threads
 *
 * @version 2020.3.3
 * @author Nitzan Tomer
 */
public class ThreadPool {

    private static final int MAX_ALLOWED_THREADS = 10;
    private Executor executor;


    /**
     * The constructor of the ThreadPool object, Sets the fields of the object
     */
    public ThreadPool(){
        this.executor = Executors.newFixedThreadPool (ThreadPool.MAX_ALLOWED_THREADS);
    }


    /**
     * Method in order to add a "thread" object to our thread pool
     *
     * @param animal is an Animal representing animal from our zoo
     */
    public void addToThreadPool(Animal animal){
        this.executor.execute(new Thread(animal));
    }


    /**
     * Method in order to reset our thread pool (In case we want to reset the program)
     */
    public void cleanThreadPool() { this.executor = Executors.newFixedThreadPool (ThreadPool.MAX_ALLOWED_THREADS); }
}