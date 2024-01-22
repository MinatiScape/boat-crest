package com.coveiot.android.navigation.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.coveiot.android.navigation.db.dao.RecentSearchHistoryDao;
import com.coveiot.android.navigation.db.entity.EntityRecentSearchHistory;
@Database(entities = {EntityRecentSearchHistory.class}, exportSchema = false, version = 1)
/* loaded from: classes5.dex */
public abstract class RecentSearchHistoryDatabase extends RoomDatabase {

    /* renamed from: a  reason: collision with root package name */
    public static RecentSearchHistoryDatabase f5507a = null;
    public static final String searchHistoryDatabase = "searchHistoryDatabase";

    public static RecentSearchHistoryDatabase getDatabase(Context context) {
        if (f5507a == null) {
            f5507a = (RecentSearchHistoryDatabase) Room.databaseBuilder(context, RecentSearchHistoryDatabase.class, searchHistoryDatabase).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return f5507a;
    }

    public abstract RecentSearchHistoryDao recentSearchHistoryDao();
}
