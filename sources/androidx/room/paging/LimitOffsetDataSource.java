package androidx.room.paging;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.paging.PositionalDataSource;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.Collections;
import java.util.List;
import java.util.Set;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
public abstract class LimitOffsetDataSource<T> extends PositionalDataSource<T> {
    public final RoomSQLiteQuery f;
    public final String g;
    public final String h;
    public final RoomDatabase i;
    public final InvalidationTracker.Observer j;
    public final boolean k;

    /* loaded from: classes.dex */
    public class a extends InvalidationTracker.Observer {
        public a(String[] strArr) {
            super(strArr);
        }

        @Override // androidx.room.InvalidationTracker.Observer
        public void onInvalidated(@NonNull Set<String> set) {
            LimitOffsetDataSource.this.invalidate();
        }
    }

    public LimitOffsetDataSource(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, boolean z, String... strArr) {
        this(roomDatabase, RoomSQLiteQuery.copyFrom(supportSQLiteQuery), z, strArr);
    }

    public final RoomSQLiteQuery b(int i, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(this.h, this.f.getArgCount() + 2);
        acquire.copyArgumentsFrom(this.f);
        acquire.bindLong(acquire.getArgCount() - 1, i2);
        acquire.bindLong(acquire.getArgCount(), i);
        return acquire;
    }

    public abstract List<T> convertRows(Cursor cursor);

    public int countItems() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(this.g, this.f.getArgCount());
        acquire.copyArgumentsFrom(this.f);
        Cursor query = this.i.query(acquire);
        try {
            if (query.moveToFirst()) {
                return query.getInt(0);
            }
            return 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.paging.DataSource
    public boolean isInvalid() {
        this.i.getInvalidationTracker().refreshVersionsSync();
        return super.isInvalid();
    }

    @Override // androidx.paging.PositionalDataSource
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams loadInitialParams, @NonNull PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        RoomSQLiteQuery roomSQLiteQuery2;
        List<? extends T> emptyList = Collections.emptyList();
        this.i.beginTransaction();
        Cursor cursor = null;
        try {
            int countItems = countItems();
            if (countItems != 0) {
                int computeInitialLoadPosition = PositionalDataSource.computeInitialLoadPosition(loadInitialParams, countItems);
                roomSQLiteQuery = b(computeInitialLoadPosition, PositionalDataSource.computeInitialLoadSize(loadInitialParams, computeInitialLoadPosition, countItems));
                try {
                    cursor = this.i.query(roomSQLiteQuery);
                    List<T> convertRows = convertRows(cursor);
                    this.i.setTransactionSuccessful();
                    roomSQLiteQuery2 = roomSQLiteQuery;
                    i = computeInitialLoadPosition;
                    emptyList = convertRows;
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    this.i.endTransaction();
                    if (roomSQLiteQuery != null) {
                        roomSQLiteQuery.release();
                    }
                    throw th;
                }
            } else {
                i = 0;
                roomSQLiteQuery2 = null;
            }
            if (cursor != null) {
                cursor.close();
            }
            this.i.endTransaction();
            if (roomSQLiteQuery2 != null) {
                roomSQLiteQuery2.release();
            }
            loadInitialCallback.onResult(emptyList, i, countItems);
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = null;
        }
    }

    @Override // androidx.paging.PositionalDataSource
    public void loadRange(@NonNull PositionalDataSource.LoadRangeParams loadRangeParams, @NonNull PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
        loadRangeCallback.onResult(loadRange(loadRangeParams.startPosition, loadRangeParams.loadSize));
    }

    public LimitOffsetDataSource(RoomDatabase roomDatabase, RoomSQLiteQuery roomSQLiteQuery, boolean z, String... strArr) {
        this.i = roomDatabase;
        this.f = roomSQLiteQuery;
        this.k = z;
        this.g = "SELECT COUNT(*) FROM ( " + roomSQLiteQuery.getSql() + " )";
        this.h = "SELECT * FROM ( " + roomSQLiteQuery.getSql() + " ) LIMIT ? OFFSET ?";
        a aVar = new a(strArr);
        this.j = aVar;
        roomDatabase.getInvalidationTracker().addWeakObserver(aVar);
    }

    @NonNull
    public List<T> loadRange(int i, int i2) {
        RoomSQLiteQuery b = b(i, i2);
        if (this.k) {
            this.i.beginTransaction();
            Cursor cursor = null;
            try {
                cursor = this.i.query(b);
                List<T> convertRows = convertRows(cursor);
                this.i.setTransactionSuccessful();
                return convertRows;
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                this.i.endTransaction();
                b.release();
            }
        }
        Cursor query = this.i.query(b);
        try {
            return convertRows(query);
        } finally {
            query.close();
            b.release();
        }
    }
}
