package com.ocbc.auctionservice;

import lombok.SneakyThrows;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ocbc.auctionservice.MutlithreadingApplication.CalculatorThread.Action.DECREMENT;
import static com.ocbc.auctionservice.MutlithreadingApplication.CalculatorThread.Action.INCREMENT;

public class MutlithreadingApplication {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static Integer value = 0;
    private static ReentrantReadWriteLock valueLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {

        Long currentTime = new Date().getTime();
        System.out.println("Current value: " + atomicInteger);

        int calls = 1000;
        int numOfThreads = 100;
        int each = calls / numOfThreads;

        List<CalculatorThread> threadList = IntStream.range(0, numOfThreads).mapToObj(threadNumber -> {
            CalculatorThread thread = new CalculatorThread(each * threadNumber, each * (threadNumber + 1), INCREMENT);
            System.out.println(String.format("Starting new increment thread %s", thread.getName()));
            thread.start();
            return thread;
        }).collect(Collectors.toList());

        threadList.addAll(IntStream.range(0, numOfThreads).mapToObj(threadNumber -> {
            CalculatorThread thread = new CalculatorThread(each * threadNumber, each * (threadNumber + 1), DECREMENT);
            System.out.println(String.format("Starting new decrement thread %s", thread.getName()));
            thread.start();
            return thread;
        }).collect(Collectors.toList()));

        threadList.forEach(calculatorThread -> {
            try {
                calculatorThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Long timeTaken = new Date().getTime() - currentTime;

        System.out.println("Current value: " + atomicInteger);
        System.out.println("Total time taken: " + timeTaken);

    }

    public static class CalculatorThread extends Thread {

        private final int start;
        private final int end;
        private final Action action;

        public CalculatorThread(int start, int end, Action action) {
            this.start = start;
            this.end = end;
            this.action = action;
        }

        @SneakyThrows
        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                switch (action) {
                    case INCREMENT:
                        increment();
                        break;
                    case DECREMENT:
                        decrement();
                        break;
                    default:
                }
            }
        }

        public static void increment() throws InterruptedException {
            incrementCurrentValue();
            printCurrentValueAndWait();
            atomicInteger.addAndGet(1);
            printCurrentAtomicIntegerAndWait();
        }

        public static void decrement() throws InterruptedException {
            decrementCurrentValue();
            printCurrentValueAndWait();
            atomicInteger.addAndGet(-1);
            printCurrentAtomicIntegerAndWait();
        }

        public static void incrementCurrentValue() {
            ReentrantReadWriteLock.WriteLock writeLock = valueLock.writeLock();
            writeLock.lock();
            value++;
            writeLock.unlock();
        }

        public static void decrementCurrentValue() {
            ReentrantReadWriteLock.WriteLock writeLock = valueLock.writeLock();
            writeLock.lock();
            value--;
            writeLock.unlock();
        }


        public static void printCurrentAtomicIntegerAndWait() throws InterruptedException {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName() + " -> The current atomic integer is " + atomicInteger.get());
        }


        public static void printCurrentValueAndWait() throws InterruptedException {
            Thread.sleep(100);
            printCurrentValue();
        }

        public static void printCurrentValue() {
            ReentrantReadWriteLock.ReadLock readLock = valueLock.readLock();
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " -> The current value is " + value);
            readLock.unlock();
        }

        public enum Action {INCREMENT, DECREMENT}
    }
}