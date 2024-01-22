package com.mappls.sdk.traffic.db.dao;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import com.mappls.sdk.traffic.db.ProbeLocation;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class b implements com.mappls.sdk.traffic.db.dao.a {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f13318a;
    public final EntityInsertionAdapter b;
    public final EntityDeletionOrUpdateAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<ProbeLocation> {
        public a(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        public final void bind(SupportSQLiteStatement supportSQLiteStatement, ProbeLocation probeLocation) {
            ProbeLocation probeLocation2 = probeLocation;
            if (probeLocation2.getTime() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, probeLocation2.getTime());
            }
            supportSQLiteStatement.bindDouble(2, probeLocation2.getLongitude());
            supportSQLiteStatement.bindDouble(3, probeLocation2.getLatitude());
            supportSQLiteStatement.bindDouble(4, probeLocation2.getSpeed());
            supportSQLiteStatement.bindDouble(5, probeLocation2.getHeading());
            if (probeLocation2.getDeviceType() == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, probeLocation2.getDeviceType());
            }
            supportSQLiteStatement.bindLong(7, probeLocation2.getEngineState());
            supportSQLiteStatement.bindDouble(8, probeLocation2.getAltitude());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "INSERT OR REPLACE INTO `locations`(`time`,`longitude`,`latitude`,`speed`,`heading`,`deviceType`,`engineState`,`altitude`) VALUES (?,?,?,?,?,?,?,?)";
        }
    }

    /* renamed from: com.mappls.sdk.traffic.db.dao.b$b  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    public class C0709b extends EntityDeletionOrUpdateAdapter<ProbeLocation> {
        public C0709b(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        public final void bind(SupportSQLiteStatement supportSQLiteStatement, ProbeLocation probeLocation) {
            ProbeLocation probeLocation2 = probeLocation;
            if (probeLocation2.getTime() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, probeLocation2.getTime());
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public final String createQuery() {
            return "DELETE FROM `locations` WHERE `time` = ?";
        }
    }

    public b(RoomDatabase roomDatabase) {
        this.f13318a = roomDatabase;
        this.b = new a(roomDatabase);
        this.c = new C0709b(roomDatabase);
    }

    public final ArrayList a() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM locations ORDER BY time DESC LIMIT 20", 0);
        Cursor query = this.f13318a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP);
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("longitude");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("latitude");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("speed");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("heading");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("deviceType");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("engineState");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow(SavingTrackHelper.TRACK_COL_ALTITUDE);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                ProbeLocation probeLocation = new ProbeLocation();
                probeLocation.setTime(query.getString(columnIndexOrThrow));
                probeLocation.setLongitude(query.getDouble(columnIndexOrThrow2));
                probeLocation.setLatitude(query.getDouble(columnIndexOrThrow3));
                probeLocation.setSpeed(query.getFloat(columnIndexOrThrow4));
                probeLocation.setHeading(query.getFloat(columnIndexOrThrow5));
                probeLocation.setDeviceType(query.getString(columnIndexOrThrow6));
                probeLocation.setEngineState(query.getInt(columnIndexOrThrow7));
                probeLocation.setAltitude(query.getDouble(columnIndexOrThrow8));
                arrayList.add(probeLocation);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public final void a(ArrayList arrayList) {
        this.f13318a.beginTransaction();
        try {
            this.c.handleMultiple(arrayList);
            this.f13318a.setTransactionSuccessful();
        } finally {
            this.f13318a.endTransaction();
        }
    }

    public final void a(List<ProbeLocation> list) {
        this.f13318a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f13318a.setTransactionSuccessful();
        } finally {
            this.f13318a.endTransaction();
        }
    }
}
