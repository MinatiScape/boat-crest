package com.coveiot.covedb.stress;

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
import com.coveiot.covedb.stress.entity.DailyStress;
import com.coveiot.covedb.stress.entity.HourlyStress;
import com.coveiot.covedb.stress.model.MinMaxData;
import com.coveiot.covedb.stress.model.MonthlyStressData;
import com.coveiot.covedb.stress.model.WeeklyStressData;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class StressDao_Impl implements StressDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6981a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<DailyStress> {
        public a(StressDao_Impl stressDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, DailyStress dailyStress) {
            String str = dailyStress.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = dailyStress.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            supportSQLiteStatement.bindDouble(3, dailyStress.stress_avg);
            supportSQLiteStatement.bindLong(4, dailyStress.stress_high);
            supportSQLiteStatement.bindLong(5, dailyStress.stress_low);
            supportSQLiteStatement.bindDouble(6, dailyStress.baselinne);
            supportSQLiteStatement.bindDouble(7, dailyStress.readiness);
            supportSQLiteStatement.bindLong(8, dailyStress.isFeedbackGiven ? 1L : 0L);
            supportSQLiteStatement.bindLong(9, dailyStress.is_sync_server ? 1L : 0L);
            String fromListString = ConvertorsMediaList.fromListString(dailyStress.AnsweredQuestions_FeedBack);
            if (fromListString == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, fromListString);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_stress`(`date`,`serial_no`,`stress_avg`,`stress_high`,`stress_low`,`baseline`,`readiness`,`isfeedbackgiven`,`is_sync_server`,`answered_questions`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<HourlyStress> {
        public b(StressDao_Impl stressDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, HourlyStress hourlyStress) {
            if (hourlyStress.getId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, hourlyStress.getId());
            }
            String str = hourlyStress.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            if (hourlyStress.getStartTime() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, hourlyStress.getStartTime());
            }
            if (hourlyStress.getEndTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, hourlyStress.getEndTime());
            }
            supportSQLiteStatement.bindDouble(5, hourlyStress.stress_avg);
            supportSQLiteStatement.bindLong(6, hourlyStress.stress_high);
            supportSQLiteStatement.bindLong(7, hourlyStress.stress_low);
            String fromListInteger = ConvertorsMediaList.fromListInteger(hourlyStress.getCodevalue());
            if (fromListInteger == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, fromListInteger);
            }
            String str2 = hourlyStress.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, str2);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourly_stress`(`id`,`date`,`start_time`,`end_time`,`stress_avg`,`stress_high`,`stress_low`,`codevalue`,`serial_no`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends ComputableLiveData<List<HourlyStress>> {
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
        public List<HourlyStress> compute() {
            if (this.g == null) {
                this.g = new a("hourly_stress", new String[0]);
                StressDao_Impl.this.f6981a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = StressDao_Impl.this.f6981a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    HourlyStress hourlyStress = new HourlyStress();
                    hourlyStress.setId(query.getString(columnIndexOrThrow));
                    hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                    hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                    hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                    hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                    hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                    hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                    hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                    hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
                    arrayList.add(hourlyStress);
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
    public class d extends ComputableLiveData<DailyStress> {
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
        public DailyStress compute() {
            DailyStress dailyStress;
            if (this.g == null) {
                this.g = new a("daily_stress", new String[0]);
                StressDao_Impl.this.f6981a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = StressDao_Impl.this.f6981a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("stress_avg");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("stress_high");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_low");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("isfeedbackgiven");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("answered_questions");
                if (query.moveToFirst()) {
                    dailyStress = new DailyStress();
                    dailyStress.mDate = query.getString(columnIndexOrThrow);
                    dailyStress.mac_address = query.getString(columnIndexOrThrow2);
                    dailyStress.stress_avg = query.getDouble(columnIndexOrThrow3);
                    dailyStress.stress_high = query.getInt(columnIndexOrThrow4);
                    dailyStress.stress_low = query.getInt(columnIndexOrThrow5);
                    dailyStress.baselinne = query.getDouble(columnIndexOrThrow6);
                    dailyStress.readiness = query.getDouble(columnIndexOrThrow7);
                    dailyStress.isFeedbackGiven = query.getInt(columnIndexOrThrow8) != 0;
                    dailyStress.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                    dailyStress.AnsweredQuestions_FeedBack = ConvertorsMediaList.frommStringToListString(query.getString(columnIndexOrThrow10));
                } else {
                    dailyStress = null;
                }
                return dailyStress;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class e extends ComputableLiveData<List<DailyStress>> {
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
        public List<DailyStress> compute() {
            if (this.g == null) {
                this.g = new a("daily_stress", new String[0]);
                StressDao_Impl.this.f6981a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = StressDao_Impl.this.f6981a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("stress_avg");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("stress_high");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_low");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("isfeedbackgiven");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("answered_questions");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailyStress dailyStress = new DailyStress();
                    dailyStress.mDate = query.getString(columnIndexOrThrow);
                    dailyStress.mac_address = query.getString(columnIndexOrThrow2);
                    int i = columnIndexOrThrow;
                    dailyStress.stress_avg = query.getDouble(columnIndexOrThrow3);
                    dailyStress.stress_high = query.getInt(columnIndexOrThrow4);
                    dailyStress.stress_low = query.getInt(columnIndexOrThrow5);
                    dailyStress.baselinne = query.getDouble(columnIndexOrThrow6);
                    dailyStress.readiness = query.getDouble(columnIndexOrThrow7);
                    boolean z = true;
                    dailyStress.isFeedbackGiven = query.getInt(columnIndexOrThrow8) != 0;
                    if (query.getInt(columnIndexOrThrow9) == 0) {
                        z = false;
                    }
                    dailyStress.is_sync_server = z;
                    dailyStress.AnsweredQuestions_FeedBack = ConvertorsMediaList.frommStringToListString(query.getString(columnIndexOrThrow10));
                    arrayList.add(dailyStress);
                    columnIndexOrThrow = i;
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
    public class f extends ComputableLiveData<List<WeeklyStressData>> {
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
        public List<WeeklyStressData> compute() {
            if (this.g == null) {
                this.g = new a("daily_stress", new String[0]);
                StressDao_Impl.this.f6981a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = StressDao_Impl.this.f6981a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("stress_high");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("stress_low");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("stress_avg");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("week");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    WeeklyStressData weeklyStressData = new WeeklyStressData();
                    weeklyStressData.mac_address = query.getString(columnIndexOrThrow);
                    weeklyStressData.stress_high = query.getInt(columnIndexOrThrow2);
                    weeklyStressData.stress_low = query.getInt(columnIndexOrThrow3);
                    weeklyStressData.stress_avg = query.getDouble(columnIndexOrThrow4);
                    weeklyStressData.setWeek(query.getString(columnIndexOrThrow5));
                    weeklyStressData.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(weeklyStressData);
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
    public class g extends ComputableLiveData<List<MonthlyStressData>> {
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
        public List<MonthlyStressData> compute() {
            if (this.g == null) {
                this.g = new a("daily_stress", new String[0]);
                StressDao_Impl.this.f6981a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = StressDao_Impl.this.f6981a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("stress_high");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("stress_low");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("stress_avg");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("month");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MonthlyStressData monthlyStressData = new MonthlyStressData();
                    monthlyStressData.mac_address = query.getString(columnIndexOrThrow);
                    monthlyStressData.stress_high = query.getInt(columnIndexOrThrow2);
                    monthlyStressData.stress_low = query.getInt(columnIndexOrThrow3);
                    monthlyStressData.stress_avg = query.getDouble(columnIndexOrThrow4);
                    monthlyStressData.setMonth(query.getString(columnIndexOrThrow5));
                    monthlyStressData.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(monthlyStressData);
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
    public class h extends ComputableLiveData<HourlyStress> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                h.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public HourlyStress compute() {
            HourlyStress hourlyStress;
            if (this.g == null) {
                this.g = new a("hourly_stress", new String[0]);
                StressDao_Impl.this.f6981a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = StressDao_Impl.this.f6981a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
                if (query.moveToFirst()) {
                    hourlyStress = new HourlyStress();
                    hourlyStress.setId(query.getString(columnIndexOrThrow));
                    hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                    hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                    hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                    hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                    hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                    hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                    hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                    hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
                } else {
                    hourlyStress = null;
                }
                return hourlyStress;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    public StressDao_Impl(RoomDatabase roomDatabase) {
        this.f6981a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public DailyStress getDailyStressData(String str, String str2) {
        DailyStress dailyStress;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_stress WHERE date=? AND serial_no=?", 2);
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
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("isfeedbackgiven");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("answered_questions");
            if (query.moveToFirst()) {
                dailyStress = new DailyStress();
                dailyStress.mDate = query.getString(columnIndexOrThrow);
                dailyStress.mac_address = query.getString(columnIndexOrThrow2);
                dailyStress.stress_avg = query.getDouble(columnIndexOrThrow3);
                dailyStress.stress_high = query.getInt(columnIndexOrThrow4);
                dailyStress.stress_low = query.getInt(columnIndexOrThrow5);
                dailyStress.baselinne = query.getDouble(columnIndexOrThrow6);
                dailyStress.readiness = query.getDouble(columnIndexOrThrow7);
                dailyStress.isFeedbackGiven = query.getInt(columnIndexOrThrow8) != 0;
                if (query.getInt(columnIndexOrThrow9) == 0) {
                    z = false;
                }
                dailyStress.is_sync_server = z;
                dailyStress.AnsweredQuestions_FeedBack = ConvertorsMediaList.frommStringToListString(query.getString(columnIndexOrThrow10));
            } else {
                dailyStress = null;
            }
            return dailyStress;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public List<HourlyStress> getHourlyStressData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_stress WHERE serial_no=? AND date=?", 2);
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
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyStress hourlyStress = new HourlyStress();
                hourlyStress.setId(query.getString(columnIndexOrThrow));
                hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
                arrayList.add(hourlyStress);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public List<HourlyStress> getHourlyStressDataFrom(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_stress where datetime(date ||' '|| start_time) >= datetime(?) AND serial_no=? order by datetime(date ||' '|| start_time) ", 2);
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
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyStress hourlyStress = new HourlyStress();
                hourlyStress.setId(query.getString(columnIndexOrThrow));
                hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
                arrayList.add(hourlyStress);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public HourlyStress getLatestHighStressRecordHourly(String str, String str2) {
        HourlyStress hourlyStress;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * , MAX(stress_high) from hourly_stress where date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 2);
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
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyStress = new HourlyStress();
                hourlyStress.setId(query.getString(columnIndexOrThrow));
                hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyStress = null;
            }
            return hourlyStress;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public HourlyStress getLatestRecordHourly(String str) {
        HourlyStress hourlyStress;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_stress where stress_low>0 AND stress_high>0 AND serial_no=? ORDER BY id DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyStress = new HourlyStress();
                hourlyStress.setId(query.getString(columnIndexOrThrow));
                hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyStress = null;
            }
            return hourlyStress;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public LiveData<HourlyStress> getLatestRecordHourlyLiveData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_stress where stress_low>0 AND stress_high>0 AND serial_no=? ORDER BY id DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new h(this.f6981a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public HourlyStress getLatestStressRecordHourly(String str, String str2, float f2, float f3) {
        HourlyStress hourlyStress;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_stress where stress_low>0 AND stress_high>0 AND stress_low>=? AND stress_high<=? AND date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 4);
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
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyStress = new HourlyStress();
                hourlyStress.setId(query.getString(columnIndexOrThrow));
                hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyStress = null;
            }
            return hourlyStress;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public LiveData<DailyStress> getLiveDailyStressData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_stress WHERE date=? AND serial_no=?", 2);
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
        return new d(this.f6981a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public LiveData<List<DailyStress>> getLiveDayWiseStressDataByMacAddress(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_stress WHERE serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new e(this.f6981a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public List<DailyStress> getLiveDayWiseStressDataByStartAndEndDate(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_stress WHERE serial_no=? and date BETWEEN ? AND ?", 3);
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
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("isfeedbackgiven");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("answered_questions");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyStress dailyStress = new DailyStress();
                dailyStress.mDate = query.getString(columnIndexOrThrow);
                dailyStress.mac_address = query.getString(columnIndexOrThrow2);
                int i = columnIndexOrThrow;
                dailyStress.stress_avg = query.getDouble(columnIndexOrThrow3);
                dailyStress.stress_high = query.getInt(columnIndexOrThrow4);
                dailyStress.stress_low = query.getInt(columnIndexOrThrow5);
                dailyStress.baselinne = query.getDouble(columnIndexOrThrow6);
                dailyStress.readiness = query.getDouble(columnIndexOrThrow7);
                dailyStress.isFeedbackGiven = query.getInt(columnIndexOrThrow8) != 0;
                dailyStress.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                dailyStress.AnsweredQuestions_FeedBack = ConvertorsMediaList.frommStringToListString(query.getString(columnIndexOrThrow10));
                arrayList.add(dailyStress);
                columnIndexOrThrow = i;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public LiveData<List<HourlyStress>> getLiveHourlyStressData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_stress WHERE date=? AND serial_no=?", 2);
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
        return new c(this.f6981a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public LiveData<List<MonthlyStressData>> getLiveMonthWiseStressData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX((case when stress_high>0 then stress_high END)) as stress_high, MIN((case when stress_low>0 then stress_low END)) as stress_low, AVG((case when stress_avg>0 then stress_avg END)) as stress_avg, strftime('%m',date) as month,strftime('%Y',date) as year from daily_stress WHERE serial_no=?  group by month,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new g(this.f6981a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public LiveData<List<WeeklyStressData>> getLiveWeekWiseStressData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX((case when stress_high>0 then stress_high END)) as stress_high, MIN((case when stress_low>0 then stress_low END)) as stress_low, AVG((case when stress_avg>0 then stress_avg END)) as stress_avg, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_stress  WHERE serial_no=?  group by week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new f(this.f6981a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public MinMaxData getMinMaxStress(String str, String str2, String str3, String str4, String str5) {
        MinMaxData minMaxData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select max(stress_high) as maxValue, min(stress_low)  as minValue from (select stress_low,stress_high, (date || ' ' || start_time) as datestarttime ,(date|| ' ' || end_time) as dateendtime    from hourly_stress where date>=? and date<=? and stress_low >0  and stress_high > 0 and serial_no=?)    where  datestarttime >=? and dateendtime <=?", 5);
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
        if (str4 == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str4);
        }
        if (str5 == null) {
            acquire.bindNull(5);
        } else {
            acquire.bindString(5, str5);
        }
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("maxValue");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("minValue");
            if (query.moveToFirst()) {
                minMaxData = new MinMaxData();
                minMaxData.setMaxValue(query.getInt(columnIndexOrThrow));
                minMaxData.setMinValue(query.getInt(columnIndexOrThrow2));
            } else {
                minMaxData = null;
            }
            return minMaxData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_stress where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6981a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public HourlyStress getStressDataWithDateAndTime(String str, String str2, String str3, String str4) {
        HourlyStress hourlyStress;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_stress WHERE serial_no=? AND date=? and start_time=? AND end_time=?", 4);
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
        if (str3 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str3);
        }
        if (str4 == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str4);
        }
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyStress = new HourlyStress();
                hourlyStress.setId(query.getString(columnIndexOrThrow));
                hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyStress = null;
            }
            return hourlyStress;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public List<DailyStress> getTotalUnSyncedData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From daily_stress WHERE serial_no=? AND is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("baseline");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("readiness");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("isfeedbackgiven");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("answered_questions");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyStress dailyStress = new DailyStress();
                dailyStress.mDate = query.getString(columnIndexOrThrow);
                dailyStress.mac_address = query.getString(columnIndexOrThrow2);
                dailyStress.stress_avg = query.getDouble(columnIndexOrThrow3);
                dailyStress.stress_high = query.getInt(columnIndexOrThrow4);
                dailyStress.stress_low = query.getInt(columnIndexOrThrow5);
                dailyStress.baselinne = query.getDouble(columnIndexOrThrow6);
                dailyStress.readiness = query.getDouble(columnIndexOrThrow7);
                dailyStress.isFeedbackGiven = query.getInt(columnIndexOrThrow8) != 0;
                dailyStress.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                dailyStress.AnsweredQuestions_FeedBack = ConvertorsMediaList.frommStringToListString(query.getString(columnIndexOrThrow10));
                arrayList.add(dailyStress);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public void insert(DailyStress dailyStress) {
        this.f6981a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) dailyStress);
            this.f6981a.setTransactionSuccessful();
        } finally {
            this.f6981a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public void insertAll(List<HourlyStress> list) {
        this.f6981a.beginTransaction();
        try {
            this.c.insert((Iterable) list);
            this.f6981a.setTransactionSuccessful();
        } finally {
            this.f6981a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_stress where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6981a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public HourlyStress getLatestRecordHourly(String str, float f2, float f3) {
        HourlyStress hourlyStress;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_stress where stress_low>0 AND stress_high>0 AND stress_low>=? AND stress_high<=? AND serial_no=? ORDER BY id DESC LIMIT 1", 3);
        acquire.bindDouble(1, f2);
        acquire.bindDouble(2, f3);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyStress = new HourlyStress();
                hourlyStress.setId(query.getString(columnIndexOrThrow));
                hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyStress = null;
            }
            return hourlyStress;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.stress.StressDao
    public HourlyStress getLatestStressRecordHourly(String str, String str2) {
        HourlyStress hourlyStress;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_stress where stress_low>0 AND stress_high>0 AND date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 2);
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
        Cursor query = this.f6981a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("stress_avg");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("stress_high");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("stress_low");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            if (query.moveToFirst()) {
                hourlyStress = new HourlyStress();
                hourlyStress.setId(query.getString(columnIndexOrThrow));
                hourlyStress.mDate = query.getString(columnIndexOrThrow2);
                hourlyStress.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyStress.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyStress.stress_avg = query.getDouble(columnIndexOrThrow5);
                hourlyStress.stress_high = query.getInt(columnIndexOrThrow6);
                hourlyStress.stress_low = query.getInt(columnIndexOrThrow7);
                hourlyStress.setCodevalue(ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyStress.mac_address = query.getString(columnIndexOrThrow9);
            } else {
                hourlyStress = null;
            }
            return hourlyStress;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
