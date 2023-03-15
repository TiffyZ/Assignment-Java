package com.company;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

interface SongCache {
    /**
     * Record number of plays for a song.
     */
    void recordSongPlays(String songId, int
            numPlays);
    /**
     * Fetch the number of plays for a song.
     *
     * @return the number of plays, or -1 if the
    song ID is unknown.
     */
    int getPlaysForSong(String songId);
    /**
     * Return the top N songs played, in descending
     order of number of plays.
     */
    List<String> getTopNSongsPlayed(int n);
}
public class SongCacheImpl implements SongCache{
    // Use ConcurrentHashMap to ensure the thread synchronized and efficient
    private final ConcurrentHashMap<String, Integer> songs;
    public SongCacheImpl(){
        songs = new ConcurrentHashMap<>();
    }
    @Override
    public void recordSongPlays(String songId, int numPlays) {
        // update the new value of given ID
        if (songs.containsKey(songId)){
            songs.compute(songId, (key, value)-> value + numPlays);
        }else{
            songs.put(songId, numPlays);
        }
    }

    @Override
    public int getPlaysForSong(String songId) {
        return songs.getOrDefault(songId, -1);
    }

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        if (n<=0) return null;
        // if the input is bigger than the total number of songs, return all
        if (n>songs.size()) return new ArrayList<>(songs.entrySet().stream()
                .sorted((e1,e2)->e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));

        // Sort the nodes by using PriorityQueue (minimum heap)
        PriorityQueue<Map.Entry<String,Integer>> heap = new PriorityQueue<>(n, Map.Entry.comparingByValue());
        for (Map.Entry<String, Integer> entry : songs.entrySet()) {
            heap.add(entry);
            // if the size is beyond required number, poll 1 minimum node
            if (heap.size() > n) {
                heap.poll();
            }
        }
        return heap.stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public static void main(String[] args){
        // write your code here
        SongCache cache = new SongCacheImpl();
        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);
        assertThat(cache.getPlaysForSong("ID-1"), is(4));
        assertThat(cache.getPlaysForSong("ID-9"), is(-1));
        System.out.println(cache.getTopNSongsPlayed(8));
        assert(cache.getTopNSongsPlayed(2).contains("ID-3")&&
                cache.getTopNSongsPlayed(2).contains("ID-1")==true);
        assert(cache.getTopNSongsPlayed(0)==null);

        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            threadPool.execute(new Thread(()->{
                cache.recordSongPlays("ID-3", 5);
                System.out.println();
            },"Thread"+i));
        }
        //TODO: IllegalMonitorStateException
        try{
            System.out.println(threadPool.isTerminated());
            while (!threadPool.isTerminated()){
                threadPool.wait(1000);
                System.out.println(threadPool.shutdownNow());
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        assertThat(cache.getPlaysForSong("ID-3"), is(20));
    }
}
