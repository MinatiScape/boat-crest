package androidx.work.impl.model;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public final class PreferenceDao_Impl implements PreferenceDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f1821a;
    public final EntityInsertionAdapter<Preference> b;

    /* loaded from: classes.dex */
    public class a extends EntityInsertionAdapter<Preference> {
        public a(PreferenceDao_Impl preferenceDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, Preference preference) {
            String str = preference.mKey;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            Long l = preference.mValue;
            if (l == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindLong(2, l.longValue());
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
        }
    }

    /* loaded from: classes.dex */
    public class b implements Callable<Long> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public b(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Long call() throws Exception {
            Long l = null;
            Cursor query = DBUtil.query(PreferenceDao_Impl.this.f1821a, this.h, false, null);
            try {
                if (query.moveToFirst() && !query.isNull(0)) {
                    l = Long.valueOf(query.getLong(0));
                }
                return l;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    public PreferenceDao_Impl(RoomDatabase roomDatabase) {
        this.f1821a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public Long getLongValue(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT long_value FROM Preference where `key`=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f1821a.assertNotSuspendingTransaction();
        Long l = null;
        Cursor query = DBUtil.query(this.f1821a, acquire, false, null);
        try {
            if (query.moveToFirst() && !query.isNull(0)) {
                l = Long.valueOf(query.getLong(0));
            }
            return l;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public LiveData<Long> getObservableLongValue(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT long_value FROM Preference where `key`=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f1821a.getInvalidationTracker().createLiveData(new String[]{"Preference"}, false, new b(acquire));
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public void insertPreference(Preference preference) {
        this.f1821a.assertNotSuspendingTransaction();
        this.f1821a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<Preference>) preference);
            this.f1821a.setTransactionSuccessful();
        } finally {
            this.f1821a.endTransaction();
        }
    }
}
