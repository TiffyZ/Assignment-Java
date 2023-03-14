package com.company;

import java.util.*;
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
    // Use ConcurrentHashMap to ensure the thread synchronized and efficient
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

    @Override
    public List<String> getTopNSongsPlayed(int n) {
        if (n<=0) return null;
        // if the input is bigger than the total number of songs, return all
        if (n>map.size()) return new ArrayList<>(map.keySet());

        List<String> res = new ArrayList<>();
        // Add your map elements into a list which accepts EntrySet<String, Integer> as Input
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        // Sort the list by using comparingByValue method in Entry
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        // Add the keys to result list
        for(Map.Entry<String, Integer> e :list.subList(0,n)){
            res.add(e.getKey());
        }
        return res;
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
