package com.coveiot.kheastapexdb.sleep;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHEASleepDataDao_Impl implements KHEASleepDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7064a;
    public final EntityInsertionAdapter<EntityEASleepData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityEASleepData> {
        public a(KHEASleepDataDao_Impl kHEASleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityEASleepData entityEASleepData) {
            EntityEASleepData entityEASleepData2 = entityEASleepData;
            supportSQLiteStatement.bindLong(1, entityEASleepData2.getEpochTimeStamp());
            supportSQLiteStatement.bindLong(2, entityEASleepData2.getSleepMode());
            if (entityEASleepData2.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, entityEASleepData2.getMacAddress());
            }
            supportSQLiteStatement.bindLong(4, entityEASleepData2.getTimeStamp());
            supportSQLiteStatement.bindLong(5, entityEASleepData2.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_ea_sleep` (`epochTimeStamp`,`sleepMode`,`macAddress`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHEASleepDataDao_Impl kHEASleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_ea_sleep SET isProcessed = 1 WHERE macAddress=? and timeStamp<? AND isProcessed == 0";
        }
    }

    public KHEASleepDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7064a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.kheastapexdb.sleep.KHEASleepDataDao
    public List<EntityEASleepData> getAllUnProcessedSleepData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_ea_sleep  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7064a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7064a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "epochTimeStamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "sleepMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityEASleepData entityEASleepData = new EntityEASleepData(query.getLong(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                entityEASleepData.setTimeStamp(query.getLong(columnIndexOrThrow4));
                entityEASleepData.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                arrayList.add(entityEASleepData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.kheastapexdb.sleep.KHEASleepDataDao
    public List<EntityEASleepData> getSleepDataBetween(String str, long j, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_ea_sleep where macAddress=? and timeStamp>=? and timeStamp<=? ORDER BY timeStamp", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.f7064a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7064a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "epochTimeStamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "sleepMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityEASleepData entityEASleepData = new EntityEASleepData(query.getLong(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                entityEASleepData.setTimeStamp(query.getLong(columnIndexOrThrow4));
                entityEASleepData.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                arrayList.add(entityEASleepData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.kheastapexdb.sleep.KHEASleepDataDao
    public List<String> getUniqueDatesForSleep(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT DATE(epochTimeStamp,'unixepoch') from entity_ea_sleep WHERE macAddress=? ORDER BY epochTimeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7064a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7064a, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.isNull(0) ? null : query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.kheastapexdb.sleep.KHEASleepDataDao
    public long insert(EntityEASleepData entityEASleepData) {
        this.f7064a.assertNotSuspendingTransaction();
        this.f7064a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityEASleepData);
            this.f7064a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7064a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.sleep.KHEASleepDataDao
    public void insertAll(List<EntityEASleepData> list) {
        this.f7064a.assertNotSuspendingTransaction();
        this.f7064a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7064a.setTransactionSuccessful();
        } finally {
            this.f7064a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.sleep.KHEASleepDataDao
    public void updateSleepDataProcessedBefore(String str, long j) {
        this.f7064a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7064a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7064a.setTransactionSuccessful();
        } finally {
            this.f7064a.endTransaction();
            this.c.release(acquire);
        }
    }
}
