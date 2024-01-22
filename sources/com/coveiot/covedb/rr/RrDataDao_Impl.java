package com.coveiot.covedb.rr;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.Convertors;
import com.coveiot.covedb.rr.entity.EntityDailyRrData;
import com.coveiot.covedb.rr.entity.EntityHourlyRrData;
import com.coveiot.covedb.rr.model.MonthlyRrData;
import com.coveiot.covedb.rr.model.WeeklyRrData;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class RrDataDao_Impl implements RrDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6963a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHourlyRrData> {
        public a(RrDataDao_Impl rrDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHourlyRrData entityHourlyRrData) {
            String str = entityHourlyRrData.serial_no;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            if (entityHourlyRrData.getStart_time() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityHourlyRrData.getStart_time());
            }
            if (entityHourlyRrData.getEnd_time() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, entityHourlyRrData.getEnd_time());
            }
            if (entityHourlyRrData.getDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityHourlyRrData.getDate());
            }
            supportSQLiteStatement.bindLong(5, entityHourlyRrData.avgRrValue);
            String frommArrayLisr = Convertors.frommArrayLisr(entityHourlyRrData.getCodedValues());
            if (frommArrayLisr == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, frommArrayLisr);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourly_rr_table`(`serial_no`,`start_time`,`end_time`,`date`,`avgRrValue`,`coded_values`) VALUES (?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<EntityDailyRrData> {
        public b(RrDataDao_Impl rrDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityDailyRrData entityDailyRrData) {
            String str = entityDailyRrData.serial_no;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            if (entityDailyRrData.getDate() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityDailyRrData.getDate());
            }
            supportSQLiteStatement.bindLong(3, entityDailyRrData.getAvgRrvalue());
            supportSQLiteStatement.bindLong(4, entityDailyRrData.is_sync_server ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_rr_table`(`serial_no`,`date`,`avgRrvalue`,`is_sync_server`) VALUES (?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends ComputableLiveData<List<EntityDailyRrData>> {
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
        public List<EntityDailyRrData> compute() {
            if (this.g == null) {
                this.g = new a("daily_rr_table", new String[0]);
                RrDataDao_Impl.this.f6963a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = RrDataDao_Impl.this.f6963a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("avgRrvalue");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityDailyRrData entityDailyRrData = new EntityDailyRrData();
                    entityDailyRrData.serial_no = query.getString(columnIndexOrThrow);
                    entityDailyRrData.setDate(query.getString(columnIndexOrThrow2));
                    entityDailyRrData.setAvgRrvalue(query.getInt(columnIndexOrThrow3));
                    entityDailyRrData.is_sync_server = query.getInt(columnIndexOrThrow4) != 0;
                    arrayList.add(entityDailyRrData);
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
    public class d extends ComputableLiveData<EntityDailyRrData> {
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
        public EntityDailyRrData compute() {
            EntityDailyRrData entityDailyRrData;
            if (this.g == null) {
                this.g = new a("daily_rr_table", new String[0]);
                RrDataDao_Impl.this.f6963a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = RrDataDao_Impl.this.f6963a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("avgRrvalue");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("is_sync_server");
                if (query.moveToFirst()) {
                    entityDailyRrData = new EntityDailyRrData();
                    entityDailyRrData.serial_no = query.getString(columnIndexOrThrow);
                    entityDailyRrData.setDate(query.getString(columnIndexOrThrow2));
                    entityDailyRrData.setAvgRrvalue(query.getInt(columnIndexOrThrow3));
                    entityDailyRrData.is_sync_server = query.getInt(columnIndexOrThrow4) != 0;
                } else {
                    entityDailyRrData = null;
                }
                return entityDailyRrData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class e extends ComputableLiveData<List<EntityHourlyRrData>> {
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
        public List<EntityHourlyRrData> compute() {
            if (this.g == null) {
                this.g = new a("hourly_rr_table", new String[0]);
                RrDataDao_Impl.this.f6963a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = RrDataDao_Impl.this.f6963a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("avgRrValue");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("coded_values");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHourlyRrData entityHourlyRrData = new EntityHourlyRrData();
                    entityHourlyRrData.serial_no = query.getString(columnIndexOrThrow);
                    entityHourlyRrData.setStart_time(query.getString(columnIndexOrThrow2));
                    entityHourlyRrData.setEnd_time(query.getString(columnIndexOrThrow3));
                    entityHourlyRrData.setDate(query.getString(columnIndexOrThrow4));
                    entityHourlyRrData.avgRrValue = query.getInt(columnIndexOrThrow5);
                    entityHourlyRrData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                    arrayList.add(entityHourlyRrData);
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
    public class f extends ComputableLiveData<List<WeeklyRrData>> {
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
        public List<WeeklyRrData> compute() {
            if (this.g == null) {
                this.g = new a("daily_rr_table", new String[0]);
                RrDataDao_Impl.this.f6963a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = RrDataDao_Impl.this.f6963a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("avgRrValue");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("week");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    WeeklyRrData weeklyRrData = new WeeklyRrData();
                    weeklyRrData.avgRrValue = query.getInt(columnIndexOrThrow);
                    weeklyRrData.week = query.getString(columnIndexOrThrow2);
                    weeklyRrData.year = query.getString(columnIndexOrThrow3);
                    arrayList.add(weeklyRrData);
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
    public class g extends ComputableLiveData<List<MonthlyRrData>> {
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
        public List<MonthlyRrData> compute() {
            if (this.g == null) {
                this.g = new a("daily_rr_table", new String[0]);
                RrDataDao_Impl.this.f6963a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = RrDataDao_Impl.this.f6963a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("avgRrValue");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("month");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MonthlyRrData monthlyRrData = new MonthlyRrData();
                    monthlyRrData.avgRrValue = query.getInt(columnIndexOrThrow);
                    monthlyRrData.month = query.getString(columnIndexOrThrow2);
                    monthlyRrData.year = query.getString(columnIndexOrThrow3);
                    arrayList.add(monthlyRrData);
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
    public class h extends ComputableLiveData<List<EntityDailyRrData>> {
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
        public List<EntityDailyRrData> compute() {
            if (this.g == null) {
                this.g = new a("daily_rr_table", new String[0]);
                RrDataDao_Impl.this.f6963a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = RrDataDao_Impl.this.f6963a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("avgRrvalue");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityDailyRrData entityDailyRrData = new EntityDailyRrData();
                    entityDailyRrData.serial_no = query.getString(columnIndexOrThrow);
                    entityDailyRrData.setDate(query.getString(columnIndexOrThrow2));
                    entityDailyRrData.setAvgRrvalue(query.getInt(columnIndexOrThrow3));
                    entityDailyRrData.is_sync_server = query.getInt(columnIndexOrThrow4) != 0;
                    arrayList.add(entityDailyRrData);
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

    public RrDataDao_Impl(RoomDatabase roomDatabase) {
        this.f6963a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public List<EntityHourlyRrData> getHourLevelRr(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_rr_table WHERE serial_no=? AND date=?", 2);
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
        Cursor query = this.f6963a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("avgRrValue");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("coded_values");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHourlyRrData entityHourlyRrData = new EntityHourlyRrData();
                entityHourlyRrData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyRrData.setStart_time(query.getString(columnIndexOrThrow2));
                entityHourlyRrData.setEnd_time(query.getString(columnIndexOrThrow3));
                entityHourlyRrData.setDate(query.getString(columnIndexOrThrow4));
                entityHourlyRrData.avgRrValue = query.getInt(columnIndexOrThrow5);
                entityHourlyRrData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                arrayList.add(entityHourlyRrData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public LiveData<EntityDailyRrData> getLiveDayLevelRr(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_rr_table WHERE serial_no=? AND date=?", 2);
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
        return new d(this.f6963a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public LiveData<List<EntityDailyRrData>> getLiveDayWiseRr(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_rr_table WHERE serial_no=? GROUP BY date", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new c(this.f6963a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public LiveData<List<EntityDailyRrData>> getLiveDayWiseRrDataByMacAddress(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_rr_table WHERE serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new h(this.f6963a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public LiveData<List<EntityHourlyRrData>> getLiveHourLevelRr(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_rr_table WHERE serial_no=? AND date=?", 2);
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
        return new e(this.f6963a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public LiveData<List<MonthlyRrData>> getLiveMonthWiseRr(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT AVG((case when avgRrValue>0 then avgRrValue END)) as avgRrValue, strftime('%m',date)+1 as month,strftime('%Y',date) as year FROM daily_rr_table WHERE serial_no=? GROUP BY month,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new g(this.f6963a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public LiveData<List<WeeklyRrData>> getLiveWeekWiseRr(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT AVG((case when avgRrValue>0 then avgRrValue END)) as avgRrValue,strftime('%W',date)+1 as week,strftime('%Y',date) as year FROM daily_rr_table WHERE serial_no=? GROUP BY week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new f(this.f6963a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_rr_table where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6963a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public List<EntityDailyRrData> getUnSyncedRrData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_rr_table WHERE serial_no=? AND is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6963a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("avgRrvalue");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityDailyRrData entityDailyRrData = new EntityDailyRrData();
                entityDailyRrData.serial_no = query.getString(columnIndexOrThrow);
                entityDailyRrData.setDate(query.getString(columnIndexOrThrow2));
                entityDailyRrData.setAvgRrvalue(query.getInt(columnIndexOrThrow3));
                entityDailyRrData.is_sync_server = query.getInt(columnIndexOrThrow4) != 0;
                arrayList.add(entityDailyRrData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public void insert(EntityHourlyRrData entityHourlyRrData) {
        this.f6963a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) entityHourlyRrData);
            this.f6963a.setTransactionSuccessful();
        } finally {
            this.f6963a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public void insertAll(List<EntityHourlyRrData> list) {
        this.f6963a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6963a.setTransactionSuccessful();
        } finally {
            this.f6963a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public void insertAllDailyRr(List<EntityDailyRrData> list) {
        this.f6963a.beginTransaction();
        try {
            this.c.insert((Iterable) list);
            this.f6963a.setTransactionSuccessful();
        } finally {
            this.f6963a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public void insertDailyRr(EntityDailyRrData entityDailyRrData) {
        this.f6963a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter) entityDailyRrData);
            this.f6963a.setTransactionSuccessful();
        } finally {
            this.f6963a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.rr.RrDataDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_rr_table where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6963a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
