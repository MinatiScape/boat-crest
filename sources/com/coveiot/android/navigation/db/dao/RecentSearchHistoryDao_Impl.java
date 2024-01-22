package com.coveiot.android.navigation.db.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.navigation.db.entity.EntityRecentSearchHistory;
import com.coveiot.android.navigation.db.model.RecentSearchHistoryData;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes5.dex */
public final class RecentSearchHistoryDao_Impl implements RecentSearchHistoryDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f5509a;
    public final EntityInsertionAdapter<EntityRecentSearchHistory> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes5.dex */
    public class a extends EntityInsertionAdapter<EntityRecentSearchHistory> {
        public a(RecentSearchHistoryDao_Impl recentSearchHistoryDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityRecentSearchHistory entityRecentSearchHistory) {
            if (entityRecentSearchHistory.getPlaceName() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityRecentSearchHistory.getPlaceName());
            }
            if (entityRecentSearchHistory.getPlaceAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityRecentSearchHistory.getPlaceAddress());
            }
            supportSQLiteStatement.bindDouble(3, entityRecentSearchHistory.getDistance());
            supportSQLiteStatement.bindLong(4, entityRecentSearchHistory.getOrderIndex());
            if (entityRecentSearchHistory.getType() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityRecentSearchHistory.getType());
            }
            if (entityRecentSearchHistory.getMapplsPin() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, entityRecentSearchHistory.getMapplsPin());
            }
            supportSQLiteStatement.bindLong(7, entityRecentSearchHistory.getTimeStamp());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_navigation_recent_search_history` (`placeName`,`placeAddress`,`distance`,`orderIndex`,`type`,`mapplsPin`,`timeStamp`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes5.dex */
    public class b extends SharedSQLiteStatement {
        public b(RecentSearchHistoryDao_Impl recentSearchHistoryDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM entity_navigation_recent_search_history";
        }
    }

    /* loaded from: classes5.dex */
    public class c implements Callable<List<RecentSearchHistoryData>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public c(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<RecentSearchHistoryData> call() throws Exception {
            Cursor query = DBUtil.query(RecentSearchHistoryDao_Impl.this.f5509a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "placeName");
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "placeAddress");
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "distance");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "orderIndex");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "type");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "mapplsPin");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    arrayList.add(new RecentSearchHistoryData(query.getString(columnIndexOrThrow), query.getString(columnIndexOrThrow2), query.getDouble(columnIndexOrThrow3), query.getLong(columnIndexOrThrow4), query.getString(columnIndexOrThrow5), query.getString(columnIndexOrThrow6), query.isNull(columnIndexOrThrow7) ? null : Long.valueOf(query.getLong(columnIndexOrThrow7))));
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    public RecentSearchHistoryDao_Impl(RoomDatabase roomDatabase) {
        this.f5509a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.android.navigation.db.dao.RecentSearchHistoryDao
    public void clearRecentSearchHistoryTable() {
        this.f5509a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        this.f5509a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f5509a.setTransactionSuccessful();
        } finally {
            this.f5509a.endTransaction();
            this.c.release(acquire);
        }
    }

    @Override // com.coveiot.android.navigation.db.dao.RecentSearchHistoryDao
    public LiveData<List<RecentSearchHistoryData>> fetchAllRecentSearchHistoryData() {
        return this.f5509a.getInvalidationTracker().createLiveData(new String[]{"entity_navigation_recent_search_history"}, false, new c(RoomSQLiteQuery.acquire("SELECT * FROM entity_navigation_recent_search_history ORDER BY timeStamp DESC", 0)));
    }

    @Override // com.coveiot.android.navigation.db.dao.RecentSearchHistoryDao
    public void insertSelectedPlace(EntityRecentSearchHistory entityRecentSearchHistory) {
        this.f5509a.assertNotSuspendingTransaction();
        this.f5509a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<EntityRecentSearchHistory>) entityRecentSearchHistory);
            this.f5509a.setTransactionSuccessful();
        } finally {
            this.f5509a.endTransaction();
        }
    }
}
