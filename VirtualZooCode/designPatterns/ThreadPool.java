package designPatterns;

import animals.Animal;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ThreadPool {

    private static final int MAX_ALLOWED_THREADS = 10;
    private Executor executor;


    public ThreadPool(){
        this.executor = Executors.newFixedThreadPool (ThreadPool.MAX_ALLOWED_THREADS);
    }

    
    public void addToThreadPool(Animal animal){
        this.executor.execute(new Thread(animal));
    }


    public void cleanThreadPool() { this.executor = Executors.newFixedThreadPool (ThreadPool.MAX_ALLOWED_THREADS); }
}