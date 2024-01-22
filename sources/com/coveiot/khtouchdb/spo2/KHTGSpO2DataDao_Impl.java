package com.coveiot.khtouchdb.spo2;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khtouchdb.converter.TGSPO2ItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHTGSpO2DataDao_Impl implements KHTGSpO2DataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7193a;
    public final EntityInsertionAdapter<EntityTGSpO2Data> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityTGSpO2Data> {
        public a(KHTGSpO2DataDao_Impl kHTGSpO2DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityTGSpO2Data entityTGSpO2Data) {
            if (entityTGSpO2Data.getDate() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityTGSpO2Data.getDate());
            }
            if (entityTGSpO2Data.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityTGSpO2Data.getMacAddress());
            }
            supportSQLiteStatement.bindLong(3, entityTGSpO2Data.getHaveMoreData() ? 1L : 0L);
            String fromList = TGSPO2ItemConverter.fromList(entityTGSpO2Data.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, fromList);
            }
            supportSQLiteStatement.bindLong(5, entityTGSpO2Data.getTimeStamp());
            supportSQLiteStatement.bindLong(6, entityTGSpO2Data.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_tg_spo2` (`date`,`macAddress`,`haveMoreData`,`items`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHTGSpO2DataDao_Impl kHTGSpO2DataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE ENTITY_TG_SPO2 SET isProcessed = 1 WHERE macAddress=? AND isProcessed == 0 AND timeStamp<?";
        }
    }

    public KHTGSpO2DataDao_Impl(RoomDatabase roomDatabase) {
        this.f7193a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khtouchdb.spo2.KHTGSpO2DataDao
    public List<EntityTGSpO2Data> getAllUnProcessedSpO2Data(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from ENTITY_TG_SPO2  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7193a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7193a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "haveMoreData");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityTGSpO2Data entityTGSpO2Data = new EntityTGSpO2Data(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow), query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                entityTGSpO2Data.setHaveMoreData(query.getInt(columnIndexOrThrow3) != 0);
                entityTGSpO2Data.setItems(TGSPO2ItemConverter.fromStr(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4)));
                entityTGSpO2Data.setTimeStamp(query.getLong(columnIndexOrThrow5));
                entityTGSpO2Data.setProcessed(query.getInt(columnIndexOrThrow6) != 0);
                arrayList.add(entityTGSpO2Data);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khtouchdb.spo2.KHTGSpO2DataDao
    public long insert(EntityTGSpO2Data entityTGSpO2Data) {
        this.f7193a.assertNotSuspendingTransaction();
        this.f7193a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityTGSpO2Data);
            this.f7193a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7193a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.spo2.KHTGSpO2DataDao
    public void insertAll(List<EntityTGSpO2Data> list) {
        this.f7193a.assertNotSuspendingTransaction();
        this.f7193a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7193a.setTransactionSuccessful();
        } finally {
            this.f7193a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.spo2.KHTGSpO2DataDao
    public void updateSpO2DataProcessedBefore(String str, long j) {
        this.f7193a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7193a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7193a.setTransactionSuccessful();
        } finally {
            this.f7193a.endTransaction();
            this.c.release(acquire);
        }
    }
}
