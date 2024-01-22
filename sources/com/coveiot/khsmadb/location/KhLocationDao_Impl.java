package com.coveiot.khsmadb.location;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class KhLocationDao_Impl implements KhLocationDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7149a;
    public final EntityInsertionAdapter<KhLocation> b;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KhLocation> {
        public a(KhLocationDao_Impl khLocationDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhLocation khLocation) {
            supportSQLiteStatement.bindLong(1, khLocation.getMTime());
            supportSQLiteStatement.bindLong(2, khLocation.getMActivityMode());
            supportSQLiteStatement.bindLong(3, khLocation.getMAltitude());
            supportSQLiteStatement.bindDouble(4, khLocation.getMLongitude());
            supportSQLiteStatement.bindDouble(5, khLocation.getMLatitude());
            if (khLocation.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, khLocation.getMacAddress());
            }
            if (khLocation.getDate() == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, khLocation.getDate());
            }
            if (khLocation.getDatetime() == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, khLocation.getDatetime());
            }
            supportSQLiteStatement.bindLong(9, khLocation.isProcessed() ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhLocation` (`mTime`,`mActivityMode`,`mAltitude`,`mLongitude`,`mLatitude`,`macAddress`,`date`,`datetime`,`isProcessed`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    public KhLocationDao_Impl(RoomDatabase roomDatabase) {
        this.f7149a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.location.KhLocationDao
    public List<KhLocation> getLocationDataListBetweenTime(String str, int i, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhLocation where macAddress=? and mTime between ? and ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        acquire.bindLong(3, i2);
        this.f7149a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7149a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mActivityMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mAltitude");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mLongitude");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "mLatitude");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "datetime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhLocation khLocation = new KhLocation(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getFloat(columnIndexOrThrow4), query.getFloat(columnIndexOrThrow5), query.getString(columnIndexOrThrow6));
                khLocation.setDate(query.getString(columnIndexOrThrow7));
                khLocation.setDatetime(query.getString(columnIndexOrThrow8));
                khLocation.setProcessed(query.getInt(columnIndexOrThrow9) != 0);
                arrayList.add(khLocation);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.location.KhLocationDao
    public void insert(KhLocation khLocation) {
        this.f7149a.assertNotSuspendingTransaction();
        this.f7149a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhLocation>) khLocation);
            this.f7149a.setTransactionSuccessful();
        } finally {
            this.f7149a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.location.KhLocationDao
    public void insertAll(List<KhLocation> list) {
        this.f7149a.assertNotSuspendingTransaction();
        this.f7149a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7149a.setTransactionSuccessful();
        } finally {
            this.f7149a.endTransaction();
        }
    }
}
