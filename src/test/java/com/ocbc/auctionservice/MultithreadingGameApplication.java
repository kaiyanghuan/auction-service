package com.ocbc.auctionservice;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class MultithreadingGameApplication {

    public static Random random = new Random();
    public static Map<String, ReentrantReadWriteLock> reentrantLocks;

    public static void main(String[] args) {
        Long currentTime = new Date().getTime();

        List<Player> players = generatePlayers(100);
        reentrantLocks = generateLocks(players);

        System.out.println("Number of players created: " + players.size());

        int numOfThreads = 5000;

        IntStream.range(0, numOfThreads)
                .mapToObj(threadNumber -> {
                    List<Player> playersChoice = randomPlayersChoice(players);
                    ActionThread thread = new ActionThread(playersChoice.get(0), playersChoice.get(1));
//                    System.out.println(String.format("Starting new action thread %s", thread.getName()));
                    thread.start();
                    return thread;
                })
                .forEach(actionThread -> {
                    try {
//                        System.out.println("Thread " + actionThread.getName() + " has joined");
                        actionThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });


        Long timeTaken = new Date().getTime() - currentTime;
        System.out.println("Game ended");
        System.out.println("Total time taken: " + timeTaken);

        players.stream().filter(Player::dead)
                .forEach(player -> System.out.println("Player " + player.getName() + " has been killed in battle"));
        players.stream().filter(Player::isAlive)
                .forEach(player -> System.out.println("Player " + player.getName() + " is alive and has dealt " + player.getDamageDealt() + " damage in total"));

        Optional<Player> mvpPlayer = players.stream().max(Comparator.comparingInt(Player::getDamageDealt));
        mvpPlayer.ifPresent(player -> System.out.println("MVP for the game, Player " + player.getName() + " has dealt a total of " + player.getDamageDealt() + " damage"));
    }

    public static class ActionThread extends Thread {
        private final Player player1;
        private final Player player2;

        public ActionThread(Player player1, Player player2) {
            this.player1 = player1;
            this.player2 = player2;
        }

        @Override
        public void run() {

            int retries = 0;
            while (retries < 3) {

                String player1Id = player1.getId() + "";
                String player2Id = player2.getId() + "";

                WriteLock player1WriteLock = reentrantLocks.get(player1Id).writeLock();
                WriteLock player2WriteLock = reentrantLocks.get(player2Id).writeLock();
                try {
//                    System.out.println(Thread.currentThread().getName() + " trying to retrieve locks for " + player1Id + " with retries of " + retries);
                    if (player1WriteLock.tryLock()) {
                        player1WriteLock.lock();

//                        System.out.println(Thread.currentThread().getName() + " trying to retrieve locks for " + player2Id + " with retries of " + retries);
                        if (player2WriteLock.tryLock()) {
                            player2WriteLock.lock();

                            if (player1.isAlive()) {
                                player1.addDamageDealt();
                                player2.takenDamage(player1.getDamage());
                                System.out.println("Player " + player1.getName() + " dealt " + player1.getDamage() + " damage to Player " + player2.getName());
                                if (player2.dead()) {
                                    System.out.println("Player " + player1.getName() + " has killed Player " + player2.getName());
                                }
                            }

                            player1WriteLock.unlock();
                            player2WriteLock.unlock();

                            retries = 4;
                        }
                    }
                    if(!player1WriteLock.isHeldByCurrentThread() || !player2WriteLock.isHeldByCurrentThread()){
                        Thread.sleep(2000);
                        retries += 1;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (player1WriteLock.isHeldByCurrentThread()) {
                        player1WriteLock.unlock();
                    }
                    if (player2WriteLock.isHeldByCurrentThread()) {
                        player2WriteLock.unlock();
                    }
                }
            }
        }

    }

    public static List<Player> generatePlayers(int numOfPlayers) {
        return IntStream.range(0, numOfPlayers)
                .mapToObj(index -> new Player(index, random.nextInt(50) + 100, random.nextInt(10) + 20, 0, true, generateRandomName()))
                .collect(Collectors.toList());
    }

    public static List<Player> randomPlayersChoice(List<Player> players) {
        int choice1 = random.nextInt(players.size());
        int choice2 = random.nextInt(players.size());
        while (choice1 == choice2) {
            choice2 = random.nextInt(players.size());
        }
        return Arrays.asList(players.get(choice1), players.get(choice2));
    }

    public static Map<String, ReentrantReadWriteLock> generateLocks(List<Player> players) {
        return players.stream().collect(Collectors.toMap(player -> player.getId() + "", player -> new ReentrantReadWriteLock()));
    }

    public static String generateRandomName() {
        return Arrays.asList("Alice", "Benson", "Clarence", "Daniel", "Esther", "Francis", "Geoffery", "Henry", "Issac")
                .get(random.nextInt(9));
    }
}
