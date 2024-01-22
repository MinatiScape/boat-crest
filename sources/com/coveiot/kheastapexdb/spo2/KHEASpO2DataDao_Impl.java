package com.coveiot.kheastapexdb.spo2;

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
public final class KHEASpO2DataDao_Impl implements KHEASpO2DataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7069a;
    public final EntityInsertionAdapter<EntityEASpO2Data> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityEASpO2Data> {
        public a(KHEASpO2DataDao_Impl kHEASpO2DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityEASpO2Data entityEASpO2Data) {
            EntityEASpO2Data entityEASpO2Data2 = entityEASpO2Data;
            supportSQLiteStatement.bindLong(1, entityEASpO2Data2.getEpochTimeStamp());
            if (entityEASpO2Data2.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityEASpO2Data2.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, entityEASpO2Data2.getSpo2Value());
            supportSQLiteStatement.bindLong(4, entityEASpO2Data2.getTimeStamp());
            supportSQLiteStatement.bindLong(5, entityEASpO2Data2.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_ea_spo2` (`epochTimeStamp`,`macAddress`,`spo2Value`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHEASpO2DataDao_Impl kHEASpO2DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_ea_spo2 SET isProcessed = 1 WHERE macAddress=? AND isProcessed == 0 AND timeStamp<?";
        }
    }

    public KHEASpO2DataDao_Impl(RoomDatabase roomDatabase) {
        this.f7069a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.kheastapexdb.spo2.KHEASpO2DataDao
    public List<EntityEASpO2Data> getAllUnProcessedSpO2Data(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_ea_spo2  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7069a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7069a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "epochTimeStamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "spo2Value");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityEASpO2Data entityEASpO2Data = new EntityEASpO2Data(query.getLong(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                entityEASpO2Data.setSpo2Value(query.getInt(columnIndexOrThrow3));
                entityEASpO2Data.setTimeStamp(query.getLong(columnIndexOrThrow4));
                entityEASpO2Data.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                arrayList.add(entityEASpO2Data);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.kheastapexdb.spo2.KHEASpO2DataDao
    public long insert(EntityEASpO2Data entityEASpO2Data) {
        this.f7069a.assertNotSuspendingTransaction();
        this.f7069a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityEASpO2Data);
            this.f7069a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7069a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.spo2.KHEASpO2DataDao
    public void insertAll(List<EntityEASpO2Data> list) {
        this.f7069a.assertNotSuspendingTransaction();
        this.f7069a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7069a.setTransactionSuccessful();
        } finally {
            this.f7069a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.spo2.KHEASpO2DataDao
    public void updateSpO2DataProcessedBefore(String str, long j) {
        this.f7069a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7069a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7069a.setTransactionSuccessful();
        } finally {
            this.f7069a.endTransaction();
            this.c.release(acquire);
        }
    }
}
