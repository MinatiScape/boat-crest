package com.coveiot.khjstyledb.deviceinfo;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes8.dex */
public final class KHJstyleDeviceInfoDao_Impl implements KHJstyleDeviceInfoDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7118a;
    public final EntityInsertionAdapter b;
    public final EntityDeletionOrUpdateAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KHJstyleEntityDeviceInfo> {
        public a(KHJstyleDeviceInfoDao_Impl kHJstyleDeviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KHJstyleEntityDeviceInfo kHJstyleEntityDeviceInfo) {
            if (kHJstyleEntityDeviceInfo.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, kHJstyleEntityDeviceInfo.getMacAddress());
            }
            supportSQLiteStatement.bindLong(2, kHJstyleEntityDeviceInfo.getLastSyncWalkTimeStamp());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `device_info`(`macAddress`,`walk_last_sync_timestamp`) VALUES (?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityDeletionOrUpdateAdapter<KHJstyleEntityDeviceInfo> {
        public b(KHJstyleDeviceInfoDao_Impl kHJstyleDeviceInfoDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KHJstyleEntityDeviceInfo kHJstyleEntityDeviceInfo) {
            if (kHJstyleEntityDeviceInfo.getMacAddress() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, kHJstyleEntityDeviceInfo.getMacAddress());
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM `device_info` WHERE `macAddress` = ?";
        }
    }

    public KHJstyleDeviceInfoDao_Impl(RoomDatabase roomDatabase) {
        this.f7118a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.khjstyledb.deviceinfo.KHJstyleDeviceInfoDao
    public void deleteDeviceInfo(KHJstyleEntityDeviceInfo kHJstyleEntityDeviceInfo) {
        this.f7118a.beginTransaction();
        try {
            this.c.handle(kHJstyleEntityDeviceInfo);
            this.f7118a.setTransactionSuccessful();
        } finally {
            this.f7118a.endTransaction();
        }
    }

    @Override // com.coveiot.khjstyledb.deviceinfo.KHJstyleDeviceInfoDao
    public KHJstyleEntityDeviceInfo getDeviceInfoBy(String str) {
        KHJstyleEntityDeviceInfo kHJstyleEntityDeviceInfo;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM device_info WHERE macAddress=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f7118a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(DeviceKey.MacAddress);
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("walk_last_sync_timestamp");
            if (query.moveToFirst()) {
                kHJstyleEntityDeviceInfo = new KHJstyleEntityDeviceInfo();
                kHJstyleEntityDeviceInfo.setMacAddress(query.getString(columnIndexOrThrow));
                kHJstyleEntityDeviceInfo.setLastSyncWalkTimeStamp(query.getLong(columnIndexOrThrow2));
            } else {
                kHJstyleEntityDeviceInfo = null;
            }
            return kHJstyleEntityDeviceInfo;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khjstyledb.deviceinfo.KHJstyleDeviceInfoDao
    public void insert(KHJstyleEntityDeviceInfo kHJstyleEntityDeviceInfo) {
        this.f7118a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) kHJstyleEntityDeviceInfo);
            this.f7118a.setTransactionSuccessful();
        } finally {
            this.f7118a.endTransaction();
        }
    }
}
