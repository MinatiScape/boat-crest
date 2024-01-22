package com.coveiot.khtouchdb.stress;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khtouchdb.converter.TGStressItemConverter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHTGStressDataDao_Impl implements KHTGStressDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7197a;
    public final EntityInsertionAdapter<EntityTGStressData> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityTGStressData> {
        public a(KHTGStressDataDao_Impl kHTGStressDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityTGStressData entityTGStressData) {
            if (entityTGStressData.getDate() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityTGStressData.getDate());
            }
            supportSQLiteStatement.bindLong(2, entityTGStressData.getStartTime());
            if (entityTGStressData.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, entityTGStressData.getMacAddress());
            }
            supportSQLiteStatement.bindLong(4, entityTGStressData.getHaveMoreData() ? 1L : 0L);
            supportSQLiteStatement.bindLong(5, entityTGStressData.getOffset());
            String fromList = TGStressItemConverter.fromList(entityTGStressData.getItems());
            if (fromList == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, fromList);
            }
            supportSQLiteStatement.bindLong(7, entityTGStressData.getTimeStamp());
            supportSQLiteStatement.bindLong(8, entityTGStressData.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entity_tg_stress` (`date`,`startTime`,`macAddress`,`haveMoreData`,`offset`,`items`,`timeStamp`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KHTGStressDataDao_Impl kHTGStressDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE ENTITY_TG_STRESS SET isProcessed = 1 WHERE macAddress=? and timeStamp<? AND isProcessed == 0";
        }
    }

    public KHTGStressDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7197a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    @Override // com.coveiot.khtouchdb.stress.KHTGStressDataDao
    public List<EntityTGStressData> getAllUnProcessedPressureData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from ENTITY_TG_STRESS  WHERE macAddress=? and isProcessed == 0 ORDER BY timeStamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7197a.assertNotSuspendingTransaction();
        String str2 = null;
        Cursor query = DBUtil.query(this.f7197a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "startTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "haveMoreData");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, TypedValues.CycleType.S_WAVE_OFFSET);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, FirebaseAnalytics.Param.ITEMS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "timeStamp");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityTGStressData entityTGStressData = new EntityTGStressData(query.isNull(columnIndexOrThrow) ? str2 : query.getString(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.isNull(columnIndexOrThrow3) ? str2 : query.getString(columnIndexOrThrow3));
                entityTGStressData.setHaveMoreData(query.getInt(columnIndexOrThrow4) != 0);
                entityTGStressData.setOffset(query.getInt(columnIndexOrThrow5));
                entityTGStressData.setItems(TGStressItemConverter.fromStr(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6)));
                entityTGStressData.setTimeStamp(query.getLong(columnIndexOrThrow7));
                entityTGStressData.setProcessed(query.getInt(columnIndexOrThrow8) != 0);
                arrayList.add(entityTGStressData);
                str2 = null;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khtouchdb.stress.KHTGStressDataDao
    public long insert(EntityTGStressData entityTGStressData) {
        this.f7197a.assertNotSuspendingTransaction();
        this.f7197a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityTGStressData);
            this.f7197a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f7197a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.stress.KHTGStressDataDao
    public void insertAll(List<EntityTGStressData> list) {
        this.f7197a.assertNotSuspendingTransaction();
        this.f7197a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7197a.setTransactionSuccessful();
        } finally {
            this.f7197a.endTransaction();
        }
    }

    @Override // com.coveiot.khtouchdb.stress.KHTGStressDataDao
    public void updatePressureDataProcessedBefore(String str, long j) {
        this.f7197a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7197a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7197a.setTransactionSuccessful();
        } finally {
            this.f7197a.endTransaction();
            this.c.release(acquire);
        }
    }
}
