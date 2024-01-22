package com.coveiot.android.respiratoryrate.database.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRawPPGHistoryEntity;
import com.coveiot.android.respiratoryrate.database.entities.HourlyRespiratoryRawPPGDataEntity;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateDataConverter;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public final class RespiratoryRawPPGHistoryDao_Impl implements RespiratoryRawPPGHistoryDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f5673a;
    public final EntityInsertionAdapter<DailyRespiratoryRawPPGHistoryEntity> b;
    public final EntityInsertionAdapter<HourlyRespiratoryRawPPGDataEntity> c;
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;
    public final SharedSQLiteStatement f;
    public final SharedSQLiteStatement g;

    /* loaded from: classes6.dex */
    public class a extends EntityInsertionAdapter<DailyRespiratoryRawPPGHistoryEntity> {
        public a(RespiratoryRawPPGHistoryDao_Impl respiratoryRawPPGHistoryDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, DailyRespiratoryRawPPGHistoryEntity dailyRespiratoryRawPPGHistoryEntity) {
            String str = dailyRespiratoryRawPPGHistoryEntity.date;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = dailyRespiratoryRawPPGHistoryEntity.macAddress;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            supportSQLiteStatement.bindLong(3, dailyRespiratoryRawPPGHistoryEntity.getSyncedToServer());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_raw_ppg_history_table` (`date`,`macAddress`,`syncedToServer`) VALUES (?,?,?)";
        }
    }

    /* loaded from: classes6.dex */
    public class b extends EntityInsertionAdapter<HourlyRespiratoryRawPPGDataEntity> {
        public b(RespiratoryRawPPGHistoryDao_Impl respiratoryRawPPGHistoryDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, HourlyRespiratoryRawPPGDataEntity hourlyRespiratoryRawPPGDataEntity) {
            supportSQLiteStatement.bindLong(1, hourlyRespiratoryRawPPGDataEntity.getTimestamp());
            String str = hourlyRespiratoryRawPPGDataEntity.date;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            String str2 = hourlyRespiratoryRawPPGDataEntity.macAddress;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str2);
            }
            supportSQLiteStatement.bindLong(4, hourlyRespiratoryRawPPGDataEntity.getRecordNumber());
            supportSQLiteStatement.bindLong(5, hourlyRespiratoryRawPPGDataEntity.getSamplingRate());
            supportSQLiteStatement.bindLong(6, hourlyRespiratoryRawPPGDataEntity.getPpgType());
            supportSQLiteStatement.bindLong(7, hourlyRespiratoryRawPPGDataEntity.getInterval());
            supportSQLiteStatement.bindLong(8, hourlyRespiratoryRawPPGDataEntity.getDuration());
            supportSQLiteStatement.bindLong(9, hourlyRespiratoryRawPPGDataEntity.getMovementLevel());
            String fromIntArrayList = RespiratoryRateDataConverter.fromIntArrayList(hourlyRespiratoryRawPPGDataEntity.getPpgValues());
            if (fromIntArrayList == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, fromIntArrayList);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourly_raw_ppg_history_table` (`timestamp`,`date`,`macAddress`,`recordNumber`,`samplingRate`,`ppgType`,`interval`,`duration`,`movementLevel`,`ppgValues`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes6.dex */
    public class c extends SharedSQLiteStatement {
        public c(RespiratoryRawPPGHistoryDao_Impl respiratoryRawPPGHistoryDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from hourly_raw_ppg_history_table where macAddress=? AND date=?";
        }
    }

    /* loaded from: classes6.dex */
    public class d extends SharedSQLiteStatement {
        public d(RespiratoryRawPPGHistoryDao_Impl respiratoryRawPPGHistoryDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from daily_raw_ppg_history_table where macAddress=? AND date=?";
        }
    }

    /* loaded from: classes6.dex */
    public class e extends SharedSQLiteStatement {
        public e(RespiratoryRawPPGHistoryDao_Impl respiratoryRawPPGHistoryDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from hourly_raw_ppg_history_table where macAddress=?";
        }
    }

    /* loaded from: classes6.dex */
    public class f extends SharedSQLiteStatement {
        public f(RespiratoryRawPPGHistoryDao_Impl respiratoryRawPPGHistoryDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from daily_raw_ppg_history_table where macAddress=?";
        }
    }

    public RespiratoryRawPPGHistoryDao_Impl(RoomDatabase roomDatabase) {
        this.f5673a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
        this.e = new d(this, roomDatabase);
        this.f = new e(this, roomDatabase);
        this.g = new f(this, roomDatabase);
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public void deleteAllDailyRawPPGData(String str) {
        this.f5673a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.g.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5673a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f5673a.setTransactionSuccessful();
        } finally {
            this.f5673a.endTransaction();
            this.g.release(acquire);
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public void deleteAllHourlyRawPPGData(String str) {
        this.f5673a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5673a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f5673a.setTransactionSuccessful();
        } finally {
            this.f5673a.endTransaction();
            this.f.release(acquire);
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public void deleteDailyRawPPGData(String str, String str2) {
        this.f5673a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.e.acquire();
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f5673a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f5673a.setTransactionSuccessful();
        } finally {
            this.f5673a.endTransaction();
            this.e.release(acquire);
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public void deleteHourlyRawPPGData(String str, String str2) {
        this.f5673a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f5673a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f5673a.setTransactionSuccessful();
        } finally {
            this.f5673a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public List<DailyRespiratoryRawPPGHistoryEntity> getAllDailyRawPPGData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  from daily_raw_ppg_history_table where macAddress=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5673a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5673a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "syncedToServer");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyRespiratoryRawPPGHistoryEntity dailyRespiratoryRawPPGHistoryEntity = new DailyRespiratoryRawPPGHistoryEntity();
                dailyRespiratoryRawPPGHistoryEntity.date = query.getString(columnIndexOrThrow);
                dailyRespiratoryRawPPGHistoryEntity.macAddress = query.getString(columnIndexOrThrow2);
                dailyRespiratoryRawPPGHistoryEntity.setSyncedToServer(query.getInt(columnIndexOrThrow3));
                arrayList.add(dailyRespiratoryRawPPGHistoryEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public int getDailyDataCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT count(*)  from daily_raw_ppg_history_table where macAddress=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5673a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5673a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public DailyRespiratoryRawPPGHistoryEntity getDailyRawPPGData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  from daily_raw_ppg_history_table where macAddress=? AND date=? ", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f5673a.assertNotSuspendingTransaction();
        DailyRespiratoryRawPPGHistoryEntity dailyRespiratoryRawPPGHistoryEntity = null;
        Cursor query = DBUtil.query(this.f5673a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "syncedToServer");
            if (query.moveToFirst()) {
                dailyRespiratoryRawPPGHistoryEntity = new DailyRespiratoryRawPPGHistoryEntity();
                dailyRespiratoryRawPPGHistoryEntity.date = query.getString(columnIndexOrThrow);
                dailyRespiratoryRawPPGHistoryEntity.macAddress = query.getString(columnIndexOrThrow2);
                dailyRespiratoryRawPPGHistoryEntity.setSyncedToServer(query.getInt(columnIndexOrThrow3));
            }
            return dailyRespiratoryRawPPGHistoryEntity;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public List<HourlyRespiratoryRawPPGDataEntity> getHourlyRawPPGData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  from hourly_raw_ppg_history_table where macAddress=? AND date=? ", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f5673a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5673a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "timestamp");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "recordNumber");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "samplingRate");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "ppgType");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "interval");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "duration");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "movementLevel");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "ppgValues");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyRespiratoryRawPPGDataEntity hourlyRespiratoryRawPPGDataEntity = new HourlyRespiratoryRawPPGDataEntity();
                hourlyRespiratoryRawPPGDataEntity.setTimestamp(query.getLong(columnIndexOrThrow));
                hourlyRespiratoryRawPPGDataEntity.date = query.getString(columnIndexOrThrow2);
                hourlyRespiratoryRawPPGDataEntity.macAddress = query.getString(columnIndexOrThrow3);
                hourlyRespiratoryRawPPGDataEntity.setRecordNumber(query.getInt(columnIndexOrThrow4));
                hourlyRespiratoryRawPPGDataEntity.setSamplingRate(query.getInt(columnIndexOrThrow5));
                hourlyRespiratoryRawPPGDataEntity.setPpgType(query.getInt(columnIndexOrThrow6));
                hourlyRespiratoryRawPPGDataEntity.setInterval(query.getInt(columnIndexOrThrow7));
                hourlyRespiratoryRawPPGDataEntity.setDuration(query.getInt(columnIndexOrThrow8));
                hourlyRespiratoryRawPPGDataEntity.setMovementLevel(query.getInt(columnIndexOrThrow9));
                columnIndexOrThrow10 = columnIndexOrThrow10;
                hourlyRespiratoryRawPPGDataEntity.setPpgValues(RespiratoryRateDataConverter.fromStringIntList(query.getString(columnIndexOrThrow10)));
                arrayList = arrayList;
                arrayList.add(hourlyRespiratoryRawPPGDataEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public DailyRespiratoryRawPPGHistoryEntity getOldestDailyRawPPGData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from daily_raw_ppg_history_table where macAddress=? order by date LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5673a.assertNotSuspendingTransaction();
        DailyRespiratoryRawPPGHistoryEntity dailyRespiratoryRawPPGHistoryEntity = null;
        Cursor query = DBUtil.query(this.f5673a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "syncedToServer");
            if (query.moveToFirst()) {
                dailyRespiratoryRawPPGHistoryEntity = new DailyRespiratoryRawPPGHistoryEntity();
                dailyRespiratoryRawPPGHistoryEntity.date = query.getString(columnIndexOrThrow);
                dailyRespiratoryRawPPGHistoryEntity.macAddress = query.getString(columnIndexOrThrow2);
                dailyRespiratoryRawPPGHistoryEntity.setSyncedToServer(query.getInt(columnIndexOrThrow3));
            }
            return dailyRespiratoryRawPPGHistoryEntity;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public void insert(DailyRespiratoryRawPPGHistoryEntity dailyRespiratoryRawPPGHistoryEntity) {
        this.f5673a.assertNotSuspendingTransaction();
        this.f5673a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<DailyRespiratoryRawPPGHistoryEntity>) dailyRespiratoryRawPPGHistoryEntity);
            this.f5673a.setTransactionSuccessful();
        } finally {
            this.f5673a.endTransaction();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public void insertAll(List<HourlyRespiratoryRawPPGDataEntity> list) {
        this.f5673a.assertNotSuspendingTransaction();
        this.f5673a.beginTransaction();
        try {
            this.c.insert(list);
            this.f5673a.setTransactionSuccessful();
        } finally {
            this.f5673a.endTransaction();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRawPPGHistoryDao
    public void insert(HourlyRespiratoryRawPPGDataEntity hourlyRespiratoryRawPPGDataEntity) {
        this.f5673a.assertNotSuspendingTransaction();
        this.f5673a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter<HourlyRespiratoryRawPPGDataEntity>) hourlyRespiratoryRawPPGDataEntity);
            this.f5673a.setTransactionSuccessful();
        } finally {
            this.f5673a.endTransaction();
        }
    }
}
