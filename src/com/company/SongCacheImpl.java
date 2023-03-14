package com.company;

import sun.nio.ch.ThreadPool;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

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
    private ConcurrentHashMap<String, Integer> map;
    public SongCacheImpl(){
        map = new ConcurrentHashMap<>();
    }
    @Override
    public void recordSongPlays(String songId, int numPlays) {
        // update the new value of given ID
        map.put(songId, map.getOrDefault(songId, 0)+numPlays);
    }

    @Override
    public int getPlaysForSong(String songId) {
        return map.getOrDefault(songId, -1);
    }
// https://stackoverflow.com/questions/18971849/best-way-to-get-top-n-keyssorted-by-values-in-a-hashmap
    @Override
    public List<String> getTopNSongsPlayed(int n) {
        if (n==0) return null;
        PriorityQueue<String> topN = new PriorityQueue<String>(n, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(map.get(s1), map.get(s2));
            }
        });

        for(String key:map.keySet()){
            if (topN.size() < n)
                topN.add(key);
            else if (map.get(topN.peek()) < map.get(key)) {
                topN.poll();
                topN.add(key);
            }
        }
        return (List) Arrays.asList(topN.toArray());
    }

    public static void main(String[] args) {
        // write your code here
        SongCache cache = new SongCacheImpl();

        cache.recordSongPlays("ID-1", 3);
        cache.recordSongPlays("ID-1", 1);
        cache.recordSongPlays("ID-2", 2);
        cache.recordSongPlays("ID-3", 5);
        assertThat(cache.getPlaysForSong("ID-1"), is(4));
        assertThat(cache.getPlaysForSong("ID-9"), is(-1));
        assert(cache.getTopNSongsPlayed(2).contains("ID-3")&&cache.getTopNSongsPlayed(2).contains("ID-1")==true);
        assert(cache.getTopNSongsPlayed(0)==null);
    }
}
