package com.coveiot.covedb.hrv;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.ConvertorsMediaList;
import com.coveiot.covedb.hrv.entity.DailyHRV;
import com.coveiot.covedb.hrv.entity.HourlyHRV;
import com.coveiot.covedb.hrv.model.MonthlyHRVData;
import com.coveiot.covedb.hrv.model.WeeklyHRVData;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class HRVDao_Impl implements HRVDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6953a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<DailyHRV> {
        public a(HRVDao_Impl hRVDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, DailyHRV dailyHRV) {
            String str = dailyHRV.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = dailyHRV.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            supportSQLiteStatement.bindDouble(3, dailyHRV.hrv_avg);
            supportSQLiteStatement.bindDouble(4, dailyHRV.hrv_high);
            supportSQLiteStatement.bindDouble(5, dailyHRV.hrv_low);
            supportSQLiteStatement.bindDouble(6, dailyHRV.baselinne);
            supportSQLiteStatement.bindDouble(7, dailyHRV.readiness);
            supportSQLiteStatement.bindLong(8, dailyHRV.is_sync_server ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_hrv`(`date`,`serial_no`,`hrv_avg`,`hrv_high`,`hrv_low`,`baseline`,`readiness`,`is_sync_server`) VALUES (?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<HourlyHRV> {
        public b(HRVDao_Impl hRVDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, HourlyHRV hourlyHRV) {
            if (hourlyHRV.getId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, hourlyHRV.getId());
            }
            String str = hourlyHRV.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            if (hourlyHRV.getStartTime() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, hourlyHRV.getStartTime());
            }
            if (hourlyHRV.getEndTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, hourlyHRV.getEndTime());
            }
            supportSQLiteStatement.bindDouble(5, hourlyHRV.hrv_avg);
            supportSQLiteStatement.bindDouble(6, hourlyHRV.hrv_high);
            supportSQLiteStatement.bindDouble(7, hourlyHRV.hrv_low);
            String fromListDouble = ConvertorsMediaList.fromListDouble(hourlyHRV.getCodevalue());
            if (fromListDouble == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, fromListDouble);
            }
            String str2 = hourlyHRV.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, str2);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourly_hrv`(`id`,`date`,`start_time`,`end_time`,`hrv_avg`,`hrv_high`,`hrv_low`,`codevalue`,`serial_no`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends ComputableLiveData<List<HourlyHRV>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                c.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<HourlyHRV> compute() {
            if (this.g == null) {
                this.g = new a("hourly_hrv", new String[0]);
                HRVDao_Impl.this.f6953a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HRVDao_Impl.this.f6953a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_avg");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_high");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("hrv_low");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    HourlyHRV hourlyHRV = new HourlyHRV();
                    hourlyHRV.setId(query.getString(columnIndexOrThrow));
                    hourlyHRV.mDate = query.getString(columnIndexOrThrow2);
                    hourlyHRV.setStartTime(query.getString(columnIndexOrThrow3));
                    hourlyHRV.setEndTime(query.getString(columnIndexOrThrow4));
                    hourlyHRV.hrv_avg = query.getDouble(columnIndexOrThrow5);
                    hourlyHRV.hrv_high = query.getDouble(columnIndexOrThrow6);
                    hourlyHRV.hrv_low = query.getDouble(columnIndexOrThrow7);
                    hourlyHRV.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                    hourlyHRV.mac_address = query.getString(columnIndexOrThrow9);
                    arrayList.add(hourlyHRV);
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class d extends ComputableLiveData<DailyHRV> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                d.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public DailyHRV compute() {
            DailyHRV dailyHRV;
            if (this.g == null) {
                this.g = new a("daily_hrv", new String[0]);
                HRVDao_Impl.this.f6953a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HRVDao_Impl.this.f6953a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("hrv_avg");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("hrv_high");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_low");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("is_sync_server");
                if (query.moveToFirst()) {
                    dailyHRV = new DailyHRV();
                    dailyHRV.mDate = query.getString(columnIndexOrThrow);
                    dailyHRV.mac_address = query.getString(columnIndexOrThrow2);
                    dailyHRV.hrv_avg = query.getDouble(columnIndexOrThrow3);
                    dailyHRV.hrv_high = query.getDouble(columnIndexOrThrow4);
                    dailyHRV.hrv_low = query.getDouble(columnIndexOrThrow5);
                    dailyHRV.baselinne = query.getDouble(columnIndexOrThrow6);
                    dailyHRV.readiness = query.getDouble(columnIndexOrThrow7);
                    dailyHRV.is_sync_server = query.getInt(columnIndexOrThrow8) != 0;
                } else {
                    dailyHRV = null;
                }
                return dailyHRV;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class e extends ComputableLiveData<List<DailyHRV>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                e.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<DailyHRV> compute() {
            if (this.g == null) {
                this.g = new a("daily_hrv", new String[0]);
                HRVDao_Impl.this.f6953a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HRVDao_Impl.this.f6953a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("hrv_avg");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("hrv_high");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_low");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailyHRV dailyHRV = new DailyHRV();
                    dailyHRV.mDate = query.getString(columnIndexOrThrow);
                    dailyHRV.mac_address = query.getString(columnIndexOrThrow2);
                    dailyHRV.hrv_avg = query.getDouble(columnIndexOrThrow3);
                    dailyHRV.hrv_high = query.getDouble(columnIndexOrThrow4);
                    dailyHRV.hrv_low = query.getDouble(columnIndexOrThrow5);
                    dailyHRV.baselinne = query.getDouble(columnIndexOrThrow6);
                    dailyHRV.readiness = query.getDouble(columnIndexOrThrow7);
                    dailyHRV.is_sync_server = query.getInt(columnIndexOrThrow8) != 0;
                    arrayList.add(dailyHRV);
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class f extends ComputableLiveData<List<WeeklyHRVData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                f.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<WeeklyHRVData> compute() {
            if (this.g == null) {
                this.g = new a("daily_hrv", new String[0]);
                HRVDao_Impl.this.f6953a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HRVDao_Impl.this.f6953a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("hrv_high");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("hrv_low");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("hrv_avg");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("baseline");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("week");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    WeeklyHRVData weeklyHRVData = new WeeklyHRVData();
                    weeklyHRVData.mac_address = query.getString(columnIndexOrThrow);
                    weeklyHRVData.hrv_high = query.getDouble(columnIndexOrThrow2);
                    weeklyHRVData.hrv_low = query.getDouble(columnIndexOrThrow3);
                    weeklyHRVData.hrv_avg = query.getDouble(columnIndexOrThrow4);
                    weeklyHRVData.baseline = query.getDouble(columnIndexOrThrow5);
                    weeklyHRVData.setWeek(query.getString(columnIndexOrThrow6));
                    weeklyHRVData.year = query.getString(columnIndexOrThrow7);
                    arrayList.add(weeklyHRVData);
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class g extends ComputableLiveData<List<MonthlyHRVData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                g.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<MonthlyHRVData> compute() {
            if (this.g == null) {
                this.g = new a("daily_hrv", new String[0]);
                HRVDao_Impl.this.f6953a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HRVDao_Impl.this.f6953a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("hrv_high");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("hrv_low");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("hrv_avg");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("baseline");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("month");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MonthlyHRVData monthlyHRVData = new MonthlyHRVData();
                    monthlyHRVData.mac_address = query.getString(columnIndexOrThrow);
                    monthlyHRVData.hrv_high = query.getDouble(columnIndexOrThrow2);
                    monthlyHRVData.hrv_low = query.getDouble(columnIndexOrThrow3);
                    monthlyHRVData.hrv_avg = query.getDouble(columnIndexOrThrow4);
                    monthlyHRVData.baseline = query.getDouble(columnIndexOrThrow5);
                    monthlyHRVData.setMonth(query.getString(columnIndexOrThrow6));
                    monthlyHRVData.year = query.getString(columnIndexOrThrow7);
                    arrayList.add(monthlyHRVData);
                }
                return arrayList;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    public HRVDao_Impl(RoomDatabase roomDatabase) {
        this.f6953a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public DailyHRV geDailyHRVData(String str, String str2) {
        DailyHRV dailyHRV;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_hrv WHERE date=? AND serial_no=?", 2);
        boolean z = true;
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
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("is_sync_server");
            if (query.moveToFirst()) {
                dailyHRV = new DailyHRV();
                dailyHRV.mDate = query.getString(columnIndexOrThrow);
                dailyHRV.mac_address = query.getString(columnIndexOrThrow2);
                dailyHRV.hrv_avg = query.getDouble(columnIndexOrThrow3);
                dailyHRV.hrv_high = query.getDouble(columnIndexOrThrow4);
                dailyHRV.hrv_low = query.getDouble(columnIndexOrThrow5);
                dailyHRV.baselinne = query.getDouble(columnIndexOrThrow6);
                dailyHRV.readiness = query.getDouble(columnIndexOrThrow7);
                if (query.getInt(columnIndexOrThrow8) == 0) {
                    z = false;
                }
                dailyHRV.is_sync_server = z;
            } else {
                dailyHRV = null;
            }
            return dailyHRV;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public List<DailyHRV> getHRVDataByStartAndEndDate(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_hrv WHERE serial_no=? and date BETWEEN ? AND ?", 3);
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
        if (str3 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str3);
        }
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyHRV dailyHRV = new DailyHRV();
                dailyHRV.mDate = query.getString(columnIndexOrThrow);
                dailyHRV.mac_address = query.getString(columnIndexOrThrow2);
                dailyHRV.hrv_avg = query.getDouble(columnIndexOrThrow3);
                dailyHRV.hrv_high = query.getDouble(columnIndexOrThrow4);
                dailyHRV.hrv_low = query.getDouble(columnIndexOrThrow5);
                dailyHRV.baselinne = query.getDouble(columnIndexOrThrow6);
                dailyHRV.readiness = query.getDouble(columnIndexOrThrow7);
                dailyHRV.is_sync_server = query.getInt(columnIndexOrThrow8) != 0;
                arrayList.add(dailyHRV);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public List<HourlyHRV> getHourlyHRVData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_hrv WHERE serial_no=? AND date=?", 2);
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
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyHRV hourlyHRV = new HourlyHRV();
                hourlyHRV.setId(query.getString(columnIndexOrThrow));
                hourlyHRV.mDate = query.getString(columnIndexOrThrow2);
                hourlyHRV.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyHRV.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyHRV.hrv_avg = query.getDouble(columnIndexOrThrow5);
                hourlyHRV.hrv_high = query.getDouble(columnIndexOrThrow6);
                hourlyHRV.hrv_low = query.getDouble(columnIndexOrThrow7);
                hourlyHRV.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyHRV.mac_address = query.getString(columnIndexOrThrow9);
                arrayList.add(hourlyHRV);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public List<HourlyHRV> getHourlyHRVDataFrom(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_hrv where datetime(date ||' '|| start_time) >= datetime(?) AND serial_no=? order by datetime(date ||' '|| start_time) ", 2);
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
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyHRV hourlyHRV = new HourlyHRV();
                hourlyHRV.setId(query.getString(columnIndexOrThrow));
                hourlyHRV.mDate = query.getString(columnIndexOrThrow2);
                hourlyHRV.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyHRV.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyHRV.hrv_avg = query.getDouble(columnIndexOrThrow5);
                hourlyHRV.hrv_high = query.getDouble(columnIndexOrThrow6);
                hourlyHRV.hrv_low = query.getDouble(columnIndexOrThrow7);
                hourlyHRV.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyHRV.mac_address = query.getString(columnIndexOrThrow9);
                arrayList.add(hourlyHRV);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public HourlyHRV getLatestHRVRecordHourly(String str, String str2, float f2, float f3) {
        HourlyHRV hourlyHRV;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_hrv where hrv_low>0 AND hrv_high>0 AND hrv_low>=? AND hrv_high<=? AND date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 4);
        acquire.bindDouble(1, f2);
        acquire.bindDouble(2, f3);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        if (str2 == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str2);
        }
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyHRV = new HourlyHRV();
                hourlyHRV.setId(query.getString(columnIndexOrThrow));
                hourlyHRV.mDate = query.getString(columnIndexOrThrow2);
                hourlyHRV.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyHRV.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyHRV.hrv_avg = query.getDouble(columnIndexOrThrow5);
                hourlyHRV.hrv_high = query.getDouble(columnIndexOrThrow6);
                hourlyHRV.hrv_low = query.getDouble(columnIndexOrThrow7);
                hourlyHRV.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyHRV.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyHRV = null;
            }
            return hourlyHRV;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public HourlyHRV getLatestHighHRVRecordHourly(String str, String str2) {
        HourlyHRV hourlyHRV;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * , MAX(hrv_high) from hourly_hrv where date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 2);
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
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyHRV = new HourlyHRV();
                hourlyHRV.setId(query.getString(columnIndexOrThrow));
                hourlyHRV.mDate = query.getString(columnIndexOrThrow2);
                hourlyHRV.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyHRV.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyHRV.hrv_avg = query.getDouble(columnIndexOrThrow5);
                hourlyHRV.hrv_high = query.getDouble(columnIndexOrThrow6);
                hourlyHRV.hrv_low = query.getDouble(columnIndexOrThrow7);
                hourlyHRV.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyHRV.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyHRV = null;
            }
            return hourlyHRV;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public HourlyHRV getLatestRecordHourly(String str) {
        HourlyHRV hourlyHRV;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_hrv where hrv_low>0 AND hrv_low>0 AND serial_no=? ORDER BY id DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyHRV = new HourlyHRV();
                hourlyHRV.setId(query.getString(columnIndexOrThrow));
                hourlyHRV.mDate = query.getString(columnIndexOrThrow2);
                hourlyHRV.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyHRV.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyHRV.hrv_avg = query.getDouble(columnIndexOrThrow5);
                hourlyHRV.hrv_high = query.getDouble(columnIndexOrThrow6);
                hourlyHRV.hrv_low = query.getDouble(columnIndexOrThrow7);
                hourlyHRV.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyHRV.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyHRV = null;
            }
            return hourlyHRV;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public LiveData<DailyHRV> getLiveDailyHRVData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_hrv WHERE date=? AND serial_no=?", 2);
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
        return new d(this.f6953a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public LiveData<List<DailyHRV>> getLiveDayWiseHRVDataByMacAddress(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_hrv WHERE serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new e(this.f6953a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public LiveData<List<HourlyHRV>> getLiveHourlyHRVData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_hrv WHERE date=? AND serial_no=?", 2);
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
        return new c(this.f6953a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public LiveData<List<MonthlyHRVData>> getLiveMonthWiseHRVData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX((case when hrv_high>0 then hrv_high END)) as hrv_high, MIN((case when hrv_low>0 then hrv_low END)) as hrv_low, AVG((case when hrv_avg>0 then hrv_avg END)) as hrv_avg, AVG((case when baseline>0 then baseline END)) as baseline, strftime('%m',date) as month,strftime('%Y',date) as year from daily_hrv WHERE serial_no=?  group by month,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new g(this.f6953a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public LiveData<List<WeeklyHRVData>> getLiveWeekWiseHRVData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX((case when hrv_high>0 then hrv_high END)) as hrv_high, MIN((case when hrv_low>0 then hrv_low END)) as hrv_low, AVG((case when hrv_avg>0 then hrv_avg END)) as hrv_avg, AVG((case when baseline>0 then baseline END)) as baseline, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_hrv  WHERE serial_no=?  group by week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new f(this.f6953a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_hrv where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6953a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public List<DailyHRV> getTotalUnSyncedData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From daily_hrv WHERE serial_no=? AND is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyHRV dailyHRV = new DailyHRV();
                dailyHRV.mDate = query.getString(columnIndexOrThrow);
                dailyHRV.mac_address = query.getString(columnIndexOrThrow2);
                dailyHRV.hrv_avg = query.getDouble(columnIndexOrThrow3);
                dailyHRV.hrv_high = query.getDouble(columnIndexOrThrow4);
                dailyHRV.hrv_low = query.getDouble(columnIndexOrThrow5);
                dailyHRV.baselinne = query.getDouble(columnIndexOrThrow6);
                dailyHRV.readiness = query.getDouble(columnIndexOrThrow7);
                dailyHRV.is_sync_server = query.getInt(columnIndexOrThrow8) != 0;
                arrayList.add(dailyHRV);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public void insert(DailyHRV dailyHRV) {
        this.f6953a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) dailyHRV);
            this.f6953a.setTransactionSuccessful();
        } finally {
            this.f6953a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public void insertAll(List<HourlyHRV> list) {
        this.f6953a.beginTransaction();
        try {
            this.c.insert((Iterable) list);
            this.f6953a.setTransactionSuccessful();
        } finally {
            this.f6953a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_hrv where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6953a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public HourlyHRV getLatestRecordHourly(String str, float f2, float f3) {
        HourlyHRV hourlyHRV;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_hrv where hrv_low>0 AND hrv_low>0 AND hrv_low>=? AND hrv_low<=? AND serial_no=? ORDER BY id DESC LIMIT 1", 3);
        acquire.bindDouble(1, f2);
        acquire.bindDouble(2, f3);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyHRV = new HourlyHRV();
                hourlyHRV.setId(query.getString(columnIndexOrThrow));
                hourlyHRV.mDate = query.getString(columnIndexOrThrow2);
                hourlyHRV.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyHRV.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyHRV.hrv_avg = query.getDouble(columnIndexOrThrow5);
                hourlyHRV.hrv_high = query.getDouble(columnIndexOrThrow6);
                hourlyHRV.hrv_low = query.getDouble(columnIndexOrThrow7);
                hourlyHRV.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyHRV.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyHRV = null;
            }
            return hourlyHRV;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.hrv.HRVDao
    public HourlyHRV getLatestHRVRecordHourly(String str, String str2) {
        HourlyHRV hourlyHRV;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_hrv where hrv_low>0 AND hrv_high>0 AND date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 2);
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
        Cursor query = this.f6953a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hrv_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("hrv_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("hrv_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyHRV = new HourlyHRV();
                hourlyHRV.setId(query.getString(columnIndexOrThrow));
                hourlyHRV.mDate = query.getString(columnIndexOrThrow2);
                hourlyHRV.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyHRV.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyHRV.hrv_avg = query.getDouble(columnIndexOrThrow5);
                hourlyHRV.hrv_high = query.getDouble(columnIndexOrThrow6);
                hourlyHRV.hrv_low = query.getDouble(columnIndexOrThrow7);
                hourlyHRV.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyHRV.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyHRV = null;
            }
            return hourlyHRV;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
