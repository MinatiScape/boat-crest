package com.coveiot.khjstyledb.heartrate;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khjstyledb.heartrate.model.SessionHR;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHJstyleHeartRateDao_Impl implements KHJstyleHeartRateDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7127a;
    public final EntityInsertionAdapter b;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KHJstyleEntitySessionHeartRateData> {
        public a(KHJstyleHeartRateDao_Impl kHJstyleHeartRateDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KHJstyleEntitySessionHeartRateData kHJstyleEntitySessionHeartRateData) {
            String str = kHJstyleEntitySessionHeartRateData.serialNo;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            supportSQLiteStatement.bindLong(2, kHJstyleEntitySessionHeartRateData.getTimeStamp());
            supportSQLiteStatement.bindLong(3, kHJstyleEntitySessionHeartRateData.getHeartRate());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `history_session_hr`(`serialNo`,`timeStamp`,`heartRate`) VALUES (?,?,?)";
        }
    }

    public KHJstyleHeartRateDao_Impl(RoomDatabase roomDatabase) {
        this.f7127a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // com.coveiot.khjstyledb.heartrate.KHJstyleHeartRateDao
    public List<SessionHR> getHeartRateDataListBetweenTime(Long l, Long l2, String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT heartRate as hrValue, timeStamp as hrTimeStamp FROM history_session_hr WHERE serialNo=? AND timeStamp BETWEEN ? AND ?", 3);
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
        Cursor query = this.f7127a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("hrValue");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("hrTimeStamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SessionHR sessionHR = new SessionHR();
                sessionHR.setHrValue(query.getInt(columnIndexOrThrow));
                sessionHR.setHrTimeStamp(query.getLong(columnIndexOrThrow2));
                arrayList.add(sessionHR);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khjstyledb.heartrate.KHJstyleHeartRateDao
    public List<SessionHR> getSessionHeartRateDataList(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT heartRate as hrValue, timeStamp as hrTimeStamp FROM history_session_hr WHERE serialNo=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f7127a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("hrValue");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("hrTimeStamp");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SessionHR sessionHR = new SessionHR();
                sessionHR.setHrValue(query.getInt(columnIndexOrThrow));
                sessionHR.setHrTimeStamp(query.getLong(columnIndexOrThrow2));
                arrayList.add(sessionHR);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khjstyledb.heartrate.KHJstyleHeartRateDao
    public void insert(KHJstyleEntitySessionHeartRateData kHJstyleEntitySessionHeartRateData) {
        this.f7127a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) kHJstyleEntitySessionHeartRateData);
            this.f7127a.setTransactionSuccessful();
        } finally {
            this.f7127a.endTransaction();
        }
    }

    @Override // com.coveiot.khjstyledb.heartrate.KHJstyleHeartRateDao
    public void insertAll(List<KHJstyleEntitySessionHeartRateData> list) {
        this.f7127a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f7127a.setTransactionSuccessful();
        } finally {
            this.f7127a.endTransaction();
        }
    }
}
