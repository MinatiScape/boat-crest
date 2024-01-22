package com.coveiot.covedb.temperature;

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
import com.coveiot.covedb.temperature.entity.DailyTemperature;
import com.coveiot.covedb.temperature.entity.HourlyTemperature;
import com.coveiot.covedb.temperature.model.MonthlyTemperatureData;
import com.coveiot.covedb.temperature.model.WeeklyTemperatureData;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class TemperatureDao_Impl implements TemperatureDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6987a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<DailyTemperature> {
        public a(TemperatureDao_Impl temperatureDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, DailyTemperature dailyTemperature) {
            String str = dailyTemperature.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = dailyTemperature.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            supportSQLiteStatement.bindDouble(3, dailyTemperature.temperature_avg);
            supportSQLiteStatement.bindDouble(4, dailyTemperature.temperature_high);
            supportSQLiteStatement.bindDouble(5, dailyTemperature.temperature_low);
            supportSQLiteStatement.bindLong(6, dailyTemperature.is_sync_server ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_temperature`(`date`,`serial_no`,`temperature_avg`,`temperature_high`,`temperature_low`,`is_sync_server`) VALUES (?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<HourlyTemperature> {
        public b(TemperatureDao_Impl temperatureDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, HourlyTemperature hourlyTemperature) {
            if (hourlyTemperature.getId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, hourlyTemperature.getId());
            }
            String str = hourlyTemperature.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            if (hourlyTemperature.getStartTime() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, hourlyTemperature.getStartTime());
            }
            if (hourlyTemperature.getEndTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, hourlyTemperature.getEndTime());
            }
            supportSQLiteStatement.bindDouble(5, hourlyTemperature.temperature_avg);
            supportSQLiteStatement.bindDouble(6, hourlyTemperature.temperature_high);
            supportSQLiteStatement.bindDouble(7, hourlyTemperature.temperature_low);
            String fromListDouble = ConvertorsMediaList.fromListDouble(hourlyTemperature.getCodevalue());
            if (fromListDouble == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, fromListDouble);
            }
            String str2 = hourlyTemperature.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, str2);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourly_temperature`(`id`,`date`,`start_time`,`end_time`,`temperature_avg`,`temperature_high`,`temperature_low`,`codevalue`,`serial_no`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends ComputableLiveData<List<HourlyTemperature>> {
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
        public List<HourlyTemperature> compute() {
            if (this.g == null) {
                this.g = new a("hourly_temperature", new String[0]);
                TemperatureDao_Impl.this.f6987a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = TemperatureDao_Impl.this.f6987a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_avg");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("temperature_high");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("temperature_low");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    HourlyTemperature hourlyTemperature = new HourlyTemperature();
                    hourlyTemperature.setId(query.getString(columnIndexOrThrow));
                    hourlyTemperature.mDate = query.getString(columnIndexOrThrow2);
                    hourlyTemperature.setStartTime(query.getString(columnIndexOrThrow3));
                    hourlyTemperature.setEndTime(query.getString(columnIndexOrThrow4));
                    hourlyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow5);
                    hourlyTemperature.temperature_high = query.getDouble(columnIndexOrThrow6);
                    hourlyTemperature.temperature_low = query.getDouble(columnIndexOrThrow7);
                    hourlyTemperature.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                    hourlyTemperature.mac_address = query.getString(columnIndexOrThrow9);
                    arrayList.add(hourlyTemperature);
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
    public class d extends ComputableLiveData<DailyTemperature> {
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
        public DailyTemperature compute() {
            DailyTemperature dailyTemperature;
            if (this.g == null) {
                this.g = new a("daily_temperature", new String[0]);
                TemperatureDao_Impl.this.f6987a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = TemperatureDao_Impl.this.f6987a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("temperature_avg");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("temperature_high");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_low");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("is_sync_server");
                if (query.moveToFirst()) {
                    dailyTemperature = new DailyTemperature();
                    dailyTemperature.mDate = query.getString(columnIndexOrThrow);
                    dailyTemperature.mac_address = query.getString(columnIndexOrThrow2);
                    dailyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow3);
                    dailyTemperature.temperature_high = query.getDouble(columnIndexOrThrow4);
                    dailyTemperature.temperature_low = query.getDouble(columnIndexOrThrow5);
                    dailyTemperature.is_sync_server = query.getInt(columnIndexOrThrow6) != 0;
                } else {
                    dailyTemperature = null;
                }
                return dailyTemperature;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class e extends ComputableLiveData<List<DailyTemperature>> {
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
        public List<DailyTemperature> compute() {
            if (this.g == null) {
                this.g = new a("daily_temperature", new String[0]);
                TemperatureDao_Impl.this.f6987a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = TemperatureDao_Impl.this.f6987a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("temperature_avg");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("temperature_high");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_low");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailyTemperature dailyTemperature = new DailyTemperature();
                    dailyTemperature.mDate = query.getString(columnIndexOrThrow);
                    dailyTemperature.mac_address = query.getString(columnIndexOrThrow2);
                    dailyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow3);
                    dailyTemperature.temperature_high = query.getDouble(columnIndexOrThrow4);
                    dailyTemperature.temperature_low = query.getDouble(columnIndexOrThrow5);
                    dailyTemperature.is_sync_server = query.getInt(columnIndexOrThrow6) != 0;
                    arrayList.add(dailyTemperature);
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
    public class f extends ComputableLiveData<List<WeeklyTemperatureData>> {
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
        public List<WeeklyTemperatureData> compute() {
            if (this.g == null) {
                this.g = new a("daily_temperature", new String[0]);
                TemperatureDao_Impl.this.f6987a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = TemperatureDao_Impl.this.f6987a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("temperature_high");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("temperature_low");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("temperature_avg");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("week");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    WeeklyTemperatureData weeklyTemperatureData = new WeeklyTemperatureData();
                    weeklyTemperatureData.mac_address = query.getString(columnIndexOrThrow);
                    weeklyTemperatureData.temperature_high = query.getDouble(columnIndexOrThrow2);
                    weeklyTemperatureData.temperature_low = query.getDouble(columnIndexOrThrow3);
                    weeklyTemperatureData.temperature_avg = query.getDouble(columnIndexOrThrow4);
                    weeklyTemperatureData.setWeek(query.getString(columnIndexOrThrow5));
                    weeklyTemperatureData.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(weeklyTemperatureData);
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
    public class g extends ComputableLiveData<List<MonthlyTemperatureData>> {
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
        public List<MonthlyTemperatureData> compute() {
            if (this.g == null) {
                this.g = new a("daily_temperature", new String[0]);
                TemperatureDao_Impl.this.f6987a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = TemperatureDao_Impl.this.f6987a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("temperature_high");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("temperature_low");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("temperature_avg");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("month");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MonthlyTemperatureData monthlyTemperatureData = new MonthlyTemperatureData();
                    monthlyTemperatureData.mac_address = query.getString(columnIndexOrThrow);
                    monthlyTemperatureData.temperature_high = query.getDouble(columnIndexOrThrow2);
                    monthlyTemperatureData.temperature_low = query.getDouble(columnIndexOrThrow3);
                    monthlyTemperatureData.temperature_avg = query.getDouble(columnIndexOrThrow4);
                    monthlyTemperatureData.setMonth(query.getString(columnIndexOrThrow5));
                    monthlyTemperatureData.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(monthlyTemperatureData);
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

    public TemperatureDao_Impl(RoomDatabase roomDatabase) {
        this.f6987a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public List<HourlyTemperature> getHourlyTemperatureData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_temperature WHERE serial_no=? AND date=?", 2);
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
        Cursor query = this.f6987a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("temperature_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("temperature_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyTemperature hourlyTemperature = new HourlyTemperature();
                hourlyTemperature.setId(query.getString(columnIndexOrThrow));
                hourlyTemperature.mDate = query.getString(columnIndexOrThrow2);
                hourlyTemperature.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyTemperature.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow5);
                hourlyTemperature.temperature_high = query.getDouble(columnIndexOrThrow6);
                hourlyTemperature.temperature_low = query.getDouble(columnIndexOrThrow7);
                hourlyTemperature.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyTemperature.mac_address = query.getString(columnIndexOrThrow9);
                arrayList.add(hourlyTemperature);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public List<HourlyTemperature> getHourlyTemperatureDataFrom(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_temperature where datetime(date ||' '|| start_time) >= datetime(?) AND serial_no=? order by datetime(date ||' '|| start_time) ", 2);
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
        Cursor query = this.f6987a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("temperature_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("temperature_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyTemperature hourlyTemperature = new HourlyTemperature();
                hourlyTemperature.setId(query.getString(columnIndexOrThrow));
                hourlyTemperature.mDate = query.getString(columnIndexOrThrow2);
                hourlyTemperature.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyTemperature.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow5);
                hourlyTemperature.temperature_high = query.getDouble(columnIndexOrThrow6);
                hourlyTemperature.temperature_low = query.getDouble(columnIndexOrThrow7);
                hourlyTemperature.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyTemperature.mac_address = query.getString(columnIndexOrThrow9);
                arrayList.add(hourlyTemperature);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public HourlyTemperature getLatestHighTemperatureRecordHourly(String str, String str2) {
        HourlyTemperature hourlyTemperature;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * , MAX(temperature_high) from hourly_temperature where date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 2);
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
        Cursor query = this.f6987a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("temperature_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("temperature_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyTemperature = new HourlyTemperature();
                hourlyTemperature.setId(query.getString(columnIndexOrThrow));
                hourlyTemperature.mDate = query.getString(columnIndexOrThrow2);
                hourlyTemperature.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyTemperature.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow5);
                hourlyTemperature.temperature_high = query.getDouble(columnIndexOrThrow6);
                hourlyTemperature.temperature_low = query.getDouble(columnIndexOrThrow7);
                hourlyTemperature.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyTemperature.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyTemperature = null;
            }
            return hourlyTemperature;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public HourlyTemperature getLatestRecordHourly(String str) {
        HourlyTemperature hourlyTemperature;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_temperature where temperature_low>0 AND temperature_high>0 AND serial_no=? ORDER BY id DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6987a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("temperature_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("temperature_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyTemperature = new HourlyTemperature();
                hourlyTemperature.setId(query.getString(columnIndexOrThrow));
                hourlyTemperature.mDate = query.getString(columnIndexOrThrow2);
                hourlyTemperature.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyTemperature.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow5);
                hourlyTemperature.temperature_high = query.getDouble(columnIndexOrThrow6);
                hourlyTemperature.temperature_low = query.getDouble(columnIndexOrThrow7);
                hourlyTemperature.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyTemperature.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyTemperature = null;
            }
            return hourlyTemperature;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public HourlyTemperature getLatestTemperatureRecordHourly(String str, String str2, float f2, float f3) {
        HourlyTemperature hourlyTemperature;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_temperature where temperature_low>0 AND temperature_high>0 AND temperature_low>=? AND temperature_high<=? AND date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 4);
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
        Cursor query = this.f6987a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("temperature_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("temperature_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyTemperature = new HourlyTemperature();
                hourlyTemperature.setId(query.getString(columnIndexOrThrow));
                hourlyTemperature.mDate = query.getString(columnIndexOrThrow2);
                hourlyTemperature.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyTemperature.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow5);
                hourlyTemperature.temperature_high = query.getDouble(columnIndexOrThrow6);
                hourlyTemperature.temperature_low = query.getDouble(columnIndexOrThrow7);
                hourlyTemperature.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyTemperature.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyTemperature = null;
            }
            return hourlyTemperature;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public LiveData<DailyTemperature> getLiveDailyTemperatureData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_temperature WHERE date=? AND serial_no=?", 2);
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
        return new d(this.f6987a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public LiveData<List<DailyTemperature>> getLiveDayWiseTemperatureDataByMacAddress(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_temperature WHERE serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new e(this.f6987a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public LiveData<List<HourlyTemperature>> getLiveHourlyTemperatureData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_temperature WHERE date=? AND serial_no=?", 2);
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
        return new c(this.f6987a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public LiveData<List<MonthlyTemperatureData>> getLiveMonthWiseTemperatureData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX(temperature_high) as temperature_high, MIN((case when temperature_low>0 then temperature_low END)) as temperature_low, AVG(temperature_avg) as temperature_avg, strftime('%m',date) as month,strftime('%Y',date) as year from daily_temperature WHERE serial_no=?  group by month,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new g(this.f6987a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public LiveData<List<WeeklyTemperatureData>> getLiveWeekWiseTemperatureData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX(temperature_high) as temperature_high, MIN((case when temperature_low>0 then temperature_low END)) as temperature_low, AVG(temperature_avg) as temperature_avg, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_temperature  WHERE serial_no=?  group by week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new f(this.f6987a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_temperature where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6987a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public List<DailyTemperature> getTotalUnSyncedData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From daily_temperature WHERE serial_no=? AND is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6987a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("temperature_avg");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("temperature_high");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_low");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyTemperature dailyTemperature = new DailyTemperature();
                dailyTemperature.mDate = query.getString(columnIndexOrThrow);
                dailyTemperature.mac_address = query.getString(columnIndexOrThrow2);
                dailyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow3);
                dailyTemperature.temperature_high = query.getDouble(columnIndexOrThrow4);
                dailyTemperature.temperature_low = query.getDouble(columnIndexOrThrow5);
                dailyTemperature.is_sync_server = query.getInt(columnIndexOrThrow6) != 0;
                arrayList.add(dailyTemperature);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public void insert(DailyTemperature dailyTemperature) {
        this.f6987a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) dailyTemperature);
            this.f6987a.setTransactionSuccessful();
        } finally {
            this.f6987a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public void insertAll(List<HourlyTemperature> list) {
        this.f6987a.beginTransaction();
        try {
            this.c.insert((Iterable) list);
            this.f6987a.setTransactionSuccessful();
        } finally {
            this.f6987a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_temperature where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6987a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public HourlyTemperature getLatestRecordHourly(String str, float f2, float f3) {
        HourlyTemperature hourlyTemperature;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_temperature where temperature_low>0 AND temperature_high>0 AND temperature_low>=? AND temperature_high<=? AND serial_no=? ORDER BY id DESC LIMIT 1", 3);
        acquire.bindDouble(1, f2);
        acquire.bindDouble(2, f3);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        Cursor query = this.f6987a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("temperature_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("temperature_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyTemperature = new HourlyTemperature();
                hourlyTemperature.setId(query.getString(columnIndexOrThrow));
                hourlyTemperature.mDate = query.getString(columnIndexOrThrow2);
                hourlyTemperature.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyTemperature.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow5);
                hourlyTemperature.temperature_high = query.getDouble(columnIndexOrThrow6);
                hourlyTemperature.temperature_low = query.getDouble(columnIndexOrThrow7);
                hourlyTemperature.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyTemperature.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyTemperature = null;
            }
            return hourlyTemperature;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.temperature.TemperatureDao
    public HourlyTemperature getLatestTemperatureRecordHourly(String str, String str2) {
        HourlyTemperature hourlyTemperature;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_temperature where temperature_low>0 AND temperature_high>0 AND date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 2);
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
        Cursor query = this.f6987a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("temperature_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("temperature_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("temperature_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyTemperature = new HourlyTemperature();
                hourlyTemperature.setId(query.getString(columnIndexOrThrow));
                hourlyTemperature.mDate = query.getString(columnIndexOrThrow2);
                hourlyTemperature.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyTemperature.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyTemperature.temperature_avg = query.getDouble(columnIndexOrThrow5);
                hourlyTemperature.temperature_high = query.getDouble(columnIndexOrThrow6);
                hourlyTemperature.temperature_low = query.getDouble(columnIndexOrThrow7);
                hourlyTemperature.setCodevalue(ConvertorsMediaList.frommStringToListDouble(query.getString(columnIndexOrThrow8)));
                hourlyTemperature.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyTemperature = null;
            }
            return hourlyTemperature;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
