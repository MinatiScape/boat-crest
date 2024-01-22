package com.coveiot.khjstyledb.walk;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.khjstyledb.Convertors;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class KHJstyleWalkDataDao_Impl implements KHJstyleWalkDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7132a;
    public final EntityInsertionAdapter b;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<KHJstyleHourlyWalkData> {
        public a(KHJstyleWalkDataDao_Impl kHJstyleWalkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KHJstyleHourlyWalkData kHJstyleHourlyWalkData) {
            if (kHJstyleHourlyWalkData.getId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, kHJstyleHourlyWalkData.getId());
            }
            String str = kHJstyleHourlyWalkData.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            if (kHJstyleHourlyWalkData.getStartTime() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, kHJstyleHourlyWalkData.getStartTime());
            }
            if (kHJstyleHourlyWalkData.getEndTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, kHJstyleHourlyWalkData.getEndTime());
            }
            supportSQLiteStatement.bindLong(5, kHJstyleHourlyWalkData.getIntervelValue());
            String frommArrayLisr = Convertors.frommArrayLisr(kHJstyleHourlyWalkData.getCodevalue());
            if (frommArrayLisr == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, frommArrayLisr);
            }
            String str2 = kHJstyleHourlyWalkData.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, str2);
            }
            supportSQLiteStatement.bindLong(8, kHJstyleHourlyWalkData.getCalories());
            supportSQLiteStatement.bindLong(9, kHJstyleHourlyWalkData.getDistance());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourlywalkdata`(`id`,`date`,`start_time`,`end_time`,`interval_value`,`codevalue`,`serial_no`,`calories`,`distance`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    public KHJstyleWalkDataDao_Impl(RoomDatabase roomDatabase) {
        this.f7132a = roomDatabase;
        this.b = new a(this, roomDatabase);
    }

    @Override // com.coveiot.khjstyledb.walk.KHJstyleWalkDataDao
    public List<KHJstyleHourlyWalkData> getHourlyStepsValueBetween(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourlywalkdata WHERE serial_no=? AND id BETWEEN ? AND ?", 3);
        if (str3 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str3);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        if (str2 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str2);
        }
        Cursor query = this.f7132a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("interval_value");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("distance");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KHJstyleHourlyWalkData kHJstyleHourlyWalkData = new KHJstyleHourlyWalkData();
                kHJstyleHourlyWalkData.setId(query.getString(columnIndexOrThrow));
                kHJstyleHourlyWalkData.mDate = query.getString(columnIndexOrThrow2);
                kHJstyleHourlyWalkData.setStartTime(query.getString(columnIndexOrThrow3));
                kHJstyleHourlyWalkData.setEndTime(query.getString(columnIndexOrThrow4));
                kHJstyleHourlyWalkData.setIntervelValue(query.getInt(columnIndexOrThrow5));
                kHJstyleHourlyWalkData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                kHJstyleHourlyWalkData.mac_address = query.getString(columnIndexOrThrow7);
                kHJstyleHourlyWalkData.setCalories(query.getInt(columnIndexOrThrow8));
                kHJstyleHourlyWalkData.setDistance(query.getInt(columnIndexOrThrow9));
                arrayList.add(kHJstyleHourlyWalkData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khjstyledb.walk.KHJstyleWalkDataDao
    public void insertAll(List<KHJstyleHourlyWalkData> list) {
        this.f7132a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f7132a.setTransactionSuccessful();
        } finally {
            this.f7132a.endTransaction();
        }
    }
}
