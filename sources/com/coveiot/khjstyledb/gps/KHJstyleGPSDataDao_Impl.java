package com.coveiot.khjstyledb.gps;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khjstyledb.gps.model.KHSessionGPSData;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHJstyleGPSDataDao_Impl implements KHJstyleGPSDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7121a;
    public final EntityInsertionAdapter b;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KHJstyleSessionGPSData> {
        public a(KHJstyleGPSDataDao_Impl kHJstyleGPSDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KHJstyleSessionGPSData kHJstyleSessionGPSData) {
            String str = kHJstyleSessionGPSData.serialNo;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            supportSQLiteStatement.bindLong(2, kHJstyleSessionGPSData.getTimeStamp());
            supportSQLiteStatement.bindDouble(3, kHJstyleSessionGPSData.getLatitude());
            supportSQLiteStatement.bindDouble(4, kHJstyleSessionGPSData.getLongitude());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR IGNORE INTO `history_session_gps`(`serialNo`,`timeStamp`,`latitude`,`longitude`) VALUES (?,?,?,?)";
        }
    }

    public KHJstyleGPSDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7121a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // com.coveiot.khjstyledb.gps.KHJstyleGPSDataDao
    public List<KHSessionGPSData> getGPSDataListBetweenTime(Long l, Long l2, String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT latitude as latitudeValue, longitude as longitudeValue, timeStamp as gpsTimeStamp FROM history_session_gps WHERE serialNo=? AND timeStamp BETWEEN ? AND ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (l == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindLong(2, l.longValue());
        }
        if (l2 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindLong(3, l2.longValue());
        }
        Cursor query = this.f7121a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("latitudeValue");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("longitudeValue");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("gpsTimeStamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KHSessionGPSData kHSessionGPSData = new KHSessionGPSData();
                kHSessionGPSData.setLatitudeValue(query.getDouble(columnIndexOrThrow));
                kHSessionGPSData.setLongitudeValue(query.getDouble(columnIndexOrThrow2));
                kHSessionGPSData.setGpsTimeStamp(query.getLong(columnIndexOrThrow3));
                arrayList.add(kHSessionGPSData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khjstyledb.gps.KHJstyleGPSDataDao
    public void insert(KHJstyleSessionGPSData kHJstyleSessionGPSData) {
        this.f7121a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) kHJstyleSessionGPSData);
            this.f7121a.setTransactionSuccessful();
        } finally {
            this.f7121a.endTransaction();
        }
    }

    @Override // com.coveiot.khjstyledb.gps.KHJstyleGPSDataDao
    public void insertAll(List<KHJstyleSessionGPSData> list) {
        this.f7121a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f7121a.setTransactionSuccessful();
        } finally {
            this.f7121a.endTransaction();
        }
    }
}
