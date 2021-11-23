package com.application.mob4git;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecyclerItemDao {
    @Insert
    void insert(RecyclerItem memo);

    @Update
    void update(RecyclerItem memo);

    @Query("UPDATE memoTable SET memo_text = :t, memo_title = :d WHERE memo_key = :key")
    void update(String t, String d, int key);

    @Delete
    void delete(RecyclerItem memo);

    @Query("SELECT * FROM memoTable")
    List<RecyclerItem> getAll();

    @Query("DELETE FROM memoTable")
    void deleteAll();

    @Query("SELECT COUNT(*) as cnt FROM memoTable")
    int getDataCount();

}
