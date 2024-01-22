package com.coveiot.covedb.sedentary;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.sedentary.entities.EntitySedentary;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class SedentaryDataDao_Impl implements SedentaryDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6966a;
    public final EntityInsertionAdapter b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntitySedentary> {
        public a(SedentaryDataDao_Impl sedentaryDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntitySedentary entitySedentary) {
            supportSQLiteStatement.bindLong(1, entitySedentary.getTimestamp());
            if (entitySedentary.getSerial_number() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entitySedentary.getSerial_number());
            }
            supportSQLiteStatement.bindLong(3, entitySedentary.getRecordNumber());
            if (entitySedentary.getDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entitySedentary.getDate());
            }
            supportSQLiteStatement.bindLong(5, entitySedentary.isSavedServer() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `sedentary_data`(`timestamp`,`serial_number`,`recordNumber`,`date`,`is_saved_server`) VALUES (?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(SedentaryDataDao_Impl sedentaryDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE sedentary_data SET is_saved_server= 1 WHERE date = ?";
        }
    }

    public SedentaryDataDao_Impl(RoomDatabase roomDatabase) {
        this.f6966a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.sedentary.SedentaryDataDao
    public int countOfSedentaryAlertsForDate(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM sedentary_data where date=? AND serial_number=?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        Cursor query = this.f6966a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sedentary.SedentaryDataDao
    public int countOfSedentaryAlertsFrom(long j, String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM sedentary_data where timestamp>=? AND serial_number=?", 2);
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        Cursor query = this.f6966a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sedentary.SedentaryDataDao
    public long getLastSedentaryAlertTimeStampFor(long j, String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT timestamp FROM sedentary_data where timestamp>=? AND serial_number=? ORDER BY timestamp DESC LIMIT 1", 2);
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        Cursor query = this.f6966a.query(acquire);
        try {
            return query.moveToFirst() ? query.getLong(0) : 0L;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sedentary.SedentaryDataDao
    public List<EntitySedentary> getUnSyncedSedentaryAlertsList(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM sedentary_data where is_saved_server = 0 AND serial_number=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6966a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("timestamp");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_number");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("recordNumber");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("is_saved_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntitySedentary entitySedentary = new EntitySedentary();
                entitySedentary.setTimestamp(query.getLong(columnIndexOrThrow));
                entitySedentary.setSerial_number(query.getString(columnIndexOrThrow2));
                entitySedentary.setRecordNumber(query.getInt(columnIndexOrThrow3));
                entitySedentary.setDate(query.getString(columnIndexOrThrow4));
                entitySedentary.setSavedServer(query.getInt(columnIndexOrThrow5) != 0);
                arrayList.add(entitySedentary);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sedentary.SedentaryDataDao
    public void insert(EntitySedentary entitySedentary) {
        this.f6966a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) entitySedentary);
            this.f6966a.setTransactionSuccessful();
        } finally {
            this.f6966a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.sedentary.SedentaryDataDao
    public void insertAll(List<EntitySedentary> list) {
        this.f6966a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6966a.setTransactionSuccessful();
        } finally {
            this.f6966a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.sedentary.SedentaryDataDao
    public void updateSyncState(String str) {
        SupportSQLiteStatement acquire = this.c.acquire();
        this.f6966a.beginTransaction();
        try {
            if (str == null) {
                acquire.bindNull(1);
            } else {
                acquire.bindString(1, str);
            }
            acquire.executeUpdateDelete();
            this.f6966a.setTransactionSuccessful();
        } finally {
            this.f6966a.endTransaction();
            this.c.release(acquire);
        }
    }
}
