package com.coveiot.khsmadb.deviceInfo;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public final class KhSmaDeviceInfoDao_Impl implements KhSmaDeviceInfoDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7143a;
    public final EntityInsertionAdapter<KhSmaDeviceInfo> b;
    public final SharedSQLiteStatement c;
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;
    public final SharedSQLiteStatement f;
    public final SharedSQLiteStatement g;
    public final SharedSQLiteStatement h;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KhSmaDeviceInfo> {
        public a(KhSmaDeviceInfoDao_Impl khSmaDeviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhSmaDeviceInfo khSmaDeviceInfo) {
            if (khSmaDeviceInfo.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, khSmaDeviceInfo.getMacAddress());
            }
            if (khSmaDeviceInfo.getStepDataLastSyncTime() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindLong(2, khSmaDeviceInfo.getStepDataLastSyncTime().longValue());
            }
            if (khSmaDeviceInfo.getHrDataLastSyncTime() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindLong(3, khSmaDeviceInfo.getHrDataLastSyncTime().longValue());
            }
            if (khSmaDeviceInfo.getSleepDataLastSyncTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindLong(4, khSmaDeviceInfo.getSleepDataLastSyncTime().longValue());
            }
            if (khSmaDeviceInfo.getTemperatureDataLastSyncTime() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindLong(5, khSmaDeviceInfo.getTemperatureDataLastSyncTime().longValue());
            }
            if (khSmaDeviceInfo.getBpDataLastSyncTime() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindLong(6, khSmaDeviceInfo.getBpDataLastSyncTime().longValue());
            }
            if (khSmaDeviceInfo.getSpO2DataLastSyncTime() == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindLong(7, khSmaDeviceInfo.getSpO2DataLastSyncTime().longValue());
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhSmaDeviceInfo` (`macAddress`,`stepDataLastSyncTime`,`hrDataLastSyncTime`,`sleepDataLastSyncTime`,`temperatureDataLastSyncTime`,`bpDataLastSyncTime`,`spO2DataLastSyncTime`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhSmaDeviceInfoDao_Impl khSmaDeviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhSmaDeviceInfo SET stepDataLastSyncTime=? WHERE macAddress=?";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends SharedSQLiteStatement {
        public c(KhSmaDeviceInfoDao_Impl khSmaDeviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhSmaDeviceInfo SET hrDataLastSyncTime=? WHERE macAddress=?";
        }
    }

    /* loaded from: classes8.dex */
    public class d extends SharedSQLiteStatement {
        public d(KhSmaDeviceInfoDao_Impl khSmaDeviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhSmaDeviceInfo SET temperatureDataLastSyncTime=? WHERE macAddress=?";
        }
    }

    /* loaded from: classes8.dex */
    public class e extends SharedSQLiteStatement {
        public e(KhSmaDeviceInfoDao_Impl khSmaDeviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhSmaDeviceInfo SET sleepDataLastSyncTime=? WHERE macAddress=?";
        }
    }

    /* loaded from: classes8.dex */
    public class f extends SharedSQLiteStatement {
        public f(KhSmaDeviceInfoDao_Impl khSmaDeviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhSmaDeviceInfo SET bpDataLastSyncTime=? WHERE macAddress=?";
        }
    }

    /* loaded from: classes8.dex */
    public class g extends SharedSQLiteStatement {
        public g(KhSmaDeviceInfoDao_Impl khSmaDeviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhSmaDeviceInfo SET spO2DataLastSyncTime=? WHERE macAddress=?";
        }
    }

    public KhSmaDeviceInfoDao_Impl(RoomDatabase roomDatabase) {
        this.f7143a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
        this.e = new d(this, roomDatabase);
        this.f = new e(this, roomDatabase);
        this.g = new f(this, roomDatabase);
        this.h = new g(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao
    public KhSmaDeviceInfo getDeviceInfo(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhSmaDeviceInfo where macAddress=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7143a.assertNotSuspendingTransaction();
        KhSmaDeviceInfo khSmaDeviceInfo = null;
        Long valueOf = null;
        Cursor query = DBUtil.query(this.f7143a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "stepDataLastSyncTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "hrDataLastSyncTime");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "sleepDataLastSyncTime");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "temperatureDataLastSyncTime");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "bpDataLastSyncTime");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "spO2DataLastSyncTime");
            if (query.moveToFirst()) {
                KhSmaDeviceInfo khSmaDeviceInfo2 = new KhSmaDeviceInfo(query.getString(columnIndexOrThrow));
                khSmaDeviceInfo2.setStepDataLastSyncTime(query.isNull(columnIndexOrThrow2) ? null : Long.valueOf(query.getLong(columnIndexOrThrow2)));
                khSmaDeviceInfo2.setHrDataLastSyncTime(query.isNull(columnIndexOrThrow3) ? null : Long.valueOf(query.getLong(columnIndexOrThrow3)));
                khSmaDeviceInfo2.setSleepDataLastSyncTime(query.isNull(columnIndexOrThrow4) ? null : Long.valueOf(query.getLong(columnIndexOrThrow4)));
                khSmaDeviceInfo2.setTemperatureDataLastSyncTime(query.isNull(columnIndexOrThrow5) ? null : Long.valueOf(query.getLong(columnIndexOrThrow5)));
                khSmaDeviceInfo2.setBpDataLastSyncTime(query.isNull(columnIndexOrThrow6) ? null : Long.valueOf(query.getLong(columnIndexOrThrow6)));
                if (!query.isNull(columnIndexOrThrow7)) {
                    valueOf = Long.valueOf(query.getLong(columnIndexOrThrow7));
                }
                khSmaDeviceInfo2.setSpO2DataLastSyncTime(valueOf);
                khSmaDeviceInfo = khSmaDeviceInfo2;
            }
            return khSmaDeviceInfo;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao
    public void insert(KhSmaDeviceInfo khSmaDeviceInfo) {
        this.f7143a.assertNotSuspendingTransaction();
        this.f7143a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhSmaDeviceInfo>) khSmaDeviceInfo);
            this.f7143a.setTransactionSuccessful();
        } finally {
            this.f7143a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao
    public void updateBloodPressureLastSyncTime(String str, long j) {
        this.f7143a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.g.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f7143a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7143a.setTransactionSuccessful();
        } finally {
            this.f7143a.endTransaction();
            this.g.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao
    public void updateHeartRateLastSyncTime(String str, long j) {
        this.f7143a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f7143a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7143a.setTransactionSuccessful();
        } finally {
            this.f7143a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao
    public void updateSleepLastSyncTime(String str, long j) {
        this.f7143a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f7143a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7143a.setTransactionSuccessful();
        } finally {
            this.f7143a.endTransaction();
            this.f.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao
    public void updateSpO2LastSyncTime(String str, long j) {
        this.f7143a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.h.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f7143a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7143a.setTransactionSuccessful();
        } finally {
            this.f7143a.endTransaction();
            this.h.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao
    public void updateStepLastSyncTime(String str, long j) {
        this.f7143a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f7143a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7143a.setTransactionSuccessful();
        } finally {
            this.f7143a.endTransaction();
            this.c.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.deviceInfo.KhSmaDeviceInfoDao
    public void updateTemperatureLastSyncTime(String str, long j) {
        this.f7143a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.e.acquire();
        acquire.bindLong(1, j);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.f7143a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7143a.setTransactionSuccessful();
        } finally {
            this.f7143a.endTransaction();
            this.e.release(acquire);
        }
    }
}
