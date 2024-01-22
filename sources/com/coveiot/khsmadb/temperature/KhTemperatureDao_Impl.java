package com.coveiot.khsmadb.temperature;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class KhTemperatureDao_Impl implements KhTemperatureDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7162a;
    public final EntityInsertionAdapter<KhBleTemperature> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KhBleTemperature> {
        public a(KhTemperatureDao_Impl khTemperatureDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhBleTemperature khBleTemperature) {
            supportSQLiteStatement.bindLong(1, khBleTemperature.getMTime());
            supportSQLiteStatement.bindLong(2, khBleTemperature.getMTemperature());
            if (khBleTemperature.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, khBleTemperature.getMMacAddress());
            }
            if (khBleTemperature.getConvertedDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, khBleTemperature.getConvertedDate());
            }
            supportSQLiteStatement.bindLong(5, khBleTemperature.isProcessed() ? 1L : 0L);
            String str = khBleTemperature.timeStamp;
            if (str == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhBleTemperature` (`mTime`,`mTemperature`,`mMacAddress`,`convertedDate`,`isProcessed`,`Timestamp`) VALUES (?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends SharedSQLiteStatement {
        public b(KhTemperatureDao_Impl khTemperatureDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleTemperature SET isProcessed = 1 WHERE mMacAddress=? and mTime<? AND isProcessed == 0";
        }
    }

    public KhTemperatureDao_Impl(RoomDatabase roomDatabase) {
        this.f7162a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.temperature.KhTemperatureDao
    public List<KhBleTemperature> getAllTemperatureData(String str, boolean z) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleTemperature where mMacAddress=? and isProcessed=? and mTemperature>0 ORDER BY mTime", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, z ? 1L : 0L);
        this.f7162a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7162a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTemperature");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleTemperature khBleTemperature = new KhBleTemperature(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleTemperature.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleTemperature.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBleTemperature);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.temperature.KhTemperatureDao
    public List<KhBleTemperature> getTemperatureDataBetween(String str, long j, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleTemperature where mMacAddress=? and mTime>=? and mTime<=? ORDER BY mTime", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.f7162a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7162a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTemperature");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleTemperature khBleTemperature = new KhBleTemperature(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleTemperature.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleTemperature.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBleTemperature);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.temperature.KhTemperatureDao
    public List<KhBleTemperature> getTemperatureDataFor(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleTemperature where mMacAddress=? and convertedDate=? ORDER BY mTime", 2);
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
        this.f7162a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7162a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTemperature");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleTemperature khBleTemperature = new KhBleTemperature(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleTemperature.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleTemperature.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBleTemperature);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.temperature.KhTemperatureDao
    public List<KhBleTemperature> getTemperatureDataListBetweenTime(String str, int i, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleTemperature where mMacAddress=? and mTime between ? and ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        acquire.bindLong(3, i2);
        this.f7162a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7162a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mTemperature");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "convertedDate");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleTemperature khBleTemperature = new KhBleTemperature(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getString(columnIndexOrThrow3), query.getString(columnIndexOrThrow4));
                khBleTemperature.setProcessed(query.getInt(columnIndexOrThrow5) != 0);
                khBleTemperature.timeStamp = query.getString(columnIndexOrThrow6);
                arrayList.add(khBleTemperature);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.temperature.KhTemperatureDao
    public List<String> getUniqueDatesForTemperature(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT convertedDate from KhBleTemperature where mMacAddress=? ORDER BY mTime", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7162a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7162a, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(query.getString(0));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.temperature.KhTemperatureDao
    public void insert(KhBleTemperature khBleTemperature) {
        this.f7162a.assertNotSuspendingTransaction();
        this.f7162a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhBleTemperature>) khBleTemperature);
            this.f7162a.setTransactionSuccessful();
        } finally {
            this.f7162a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.temperature.KhTemperatureDao
    public void insertAll(List<KhBleTemperature> list) {
        this.f7162a.assertNotSuspendingTransaction();
        this.f7162a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7162a.setTransactionSuccessful();
        } finally {
            this.f7162a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.temperature.KhTemperatureDao
    public void updateTemperatureDataProcessedBefore(String str, long j) {
        this.f7162a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.f7162a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7162a.setTransactionSuccessful();
        } finally {
            this.f7162a.endTransaction();
            this.c.release(acquire);
        }
    }
}
