package com.coveiot.android.navigation.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.coveiot.android.navigation.db.entity.EntityRecentSearchHistory;
import com.coveiot.android.navigation.db.model.RecentSearchHistoryData;
import java.util.List;
import org.jetbrains.annotations.NotNull;
@Dao
/* loaded from: classes5.dex */
public interface RecentSearchHistoryDao {
    @Query("DELETE FROM entity_navigation_recent_search_history")
    void clearRecentSearchHistoryTable();

    @Query("SELECT * FROM entity_navigation_recent_search_history ORDER BY timeStamp DESC")
    @NotNull
    LiveData<List<RecentSearchHistoryData>> fetchAllRecentSearchHistoryData();

    @Insert(onConflict = 1)
    void insertSelectedPlace(@NotNull EntityRecentSearchHistory entityRecentSearchHistory);
}
