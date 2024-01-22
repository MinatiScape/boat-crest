package com.coveiot.kheastapexdb.stress;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHEAStressDataDao_Impl implements KHEAStressDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7073a;
    public final EntityInsertionAdapter<EntityEAStressData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityEAStressData> {
        public a(KHEAStressDataDao_Impl kHEAStressDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityEAStressData entityEAStressData) {
            EntityEAStressData entityEAStressData2 = entityEAStressData;
            supportSQLiteStatement.bindLong(1, entityEAStressData2.getEpochTimeStamp());
            if (entityEAStressData2.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityEAStressData2.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, entityEAStressData2.getStressValue());
            supportSQLiteStatement.bindLong(4, entityEAStressData2.getLevel());
            supportSQLiteStatement.bindLong(5, entityEAStressData2.getTimeStamp());
            supportSQLiteStatement.bindLong(6, entityEAStressData2.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_ea_stress` (`epochTimeStamp`,`macAddress`,`stressValue`,`level`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHEAStressDataDao_Impl kHEAStressDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE entity_ea_stress SET isProcessed = 1 WHERE macAddress=? and timeStamp<? AND isProcessed == 0";
        }
    }

    public KHEAStressDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7073a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.kheastapexdb.stress.KHEAStressDataDao
    public List<EntityEAStressData> getAllUnProcessedPressureData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from entity_ea_stress  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7073a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7073a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "epochTimeStamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "stressValue");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.LEVEL);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityEAStressData entityEAStressData = new EntityEAStressData(query.getLong(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                entityEAStressData.setStressValue(query.getInt(columnIndexOrThrow3));
                entityEAStressData.setLevel(query.getInt(columnIndexOrThrow4));
                entityEAStressData.setTimeStamp(query.getLong(columnIndexOrThrow5));
                entityEAStressData.setProcessed(query.getInt(columnIndexOrThrow6) != 0);
                arrayList.add(entityEAStressData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.kheastapexdb.stress.KHEAStressDataDao
    public long insert(EntityEAStressData entityEAStressData) {
        this.f7073a.assertNotSuspendingTransaction();
        this.f7073a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityEAStressData);
            this.f7073a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7073a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.stress.KHEAStressDataDao
    public void insertAll(List<EntityEAStressData> list) {
        this.f7073a.assertNotSuspendingTransaction();
        this.f7073a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7073a.setTransactionSuccessful();
        } finally {
            this.f7073a.endTransaction();
        }
    }

    @Override // com.coveiot.kheastapexdb.stress.KHEAStressDataDao
    public void updatePressureDataProcessedBefore(String str, long j) {
        this.f7073a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7073a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7073a.setTransactionSuccessful();
        } finally {
            this.f7073a.endTransaction();
            this.c.release(acquire);
        }
    }
}
