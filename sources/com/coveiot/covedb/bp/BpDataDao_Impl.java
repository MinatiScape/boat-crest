package com.coveiot.covedb.bp;

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
import com.coveiot.covedb.bp.entities.EntityDailyBp;
import com.coveiot.covedb.bp.entities.EntityHourlyBp;
import com.coveiot.covedb.bp.model.MonthLevelBp;
import com.coveiot.covedb.bp.model.WeekLevelBp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class BpDataDao_Impl implements BpDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6942a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityDailyBp> {
        public a(BpDataDao_Impl bpDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityDailyBp entityDailyBp) {
            String str = entityDailyBp.date;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = entityDailyBp.serial_no;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            supportSQLiteStatement.bindLong(3, entityDailyBp.systolic_bp);
            supportSQLiteStatement.bindLong(4, entityDailyBp.diastolic_bp);
            supportSQLiteStatement.bindLong(5, entityDailyBp.is_sync_server ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_bp`(`date`,`serial_no`,`systolic_bp`,`diastolic_bp`,`is_sync_server`) VALUES (?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<EntityHourlyBp> {
        public b(BpDataDao_Impl bpDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHourlyBp entityHourlyBp) {
            String str = entityHourlyBp.date;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = entityHourlyBp.serial_no;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            String str3 = entityHourlyBp.start_hour;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str3);
            }
            String str4 = entityHourlyBp.end_hour;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str4);
            }
            supportSQLiteStatement.bindLong(5, entityHourlyBp.systolic_bp);
            supportSQLiteStatement.bindLong(6, entityHourlyBp.diastolic_bp);
            String fromBpCodedValueList = Convertors.fromBpCodedValueList(entityHourlyBp.codevalues);
            if (fromBpCodedValueList == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, fromBpCodedValueList);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourly_bp`(`date`,`serial_no`,`start_hour`,`end_hour`,`systolic_bp`,`diastolic_bp`,`codevalues`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends ComputableLiveData<List<EntityDailyBp>> {
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
        public List<EntityDailyBp> compute() {
            if (this.g == null) {
                this.g = new a("daily_bp", new String[0]);
                BpDataDao_Impl.this.f6942a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = BpDataDao_Impl.this.f6942a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("systolic_bp");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("diastolic_bp");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityDailyBp entityDailyBp = new EntityDailyBp();
                    entityDailyBp.date = query.getString(columnIndexOrThrow);
                    entityDailyBp.serial_no = query.getString(columnIndexOrThrow2);
                    entityDailyBp.systolic_bp = query.getInt(columnIndexOrThrow3);
                    entityDailyBp.diastolic_bp = query.getInt(columnIndexOrThrow4);
                    entityDailyBp.is_sync_server = query.getInt(columnIndexOrThrow5) != 0;
                    arrayList.add(entityDailyBp);
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
    public class d extends ComputableLiveData<EntityDailyBp> {
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
        public EntityDailyBp compute() {
            EntityDailyBp entityDailyBp;
            if (this.g == null) {
                this.g = new a("daily_bp", new String[0]);
                BpDataDao_Impl.this.f6942a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = BpDataDao_Impl.this.f6942a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("systolic_bp");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("diastolic_bp");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("is_sync_server");
                if (query.moveToFirst()) {
                    entityDailyBp = new EntityDailyBp();
                    entityDailyBp.date = query.getString(columnIndexOrThrow);
                    entityDailyBp.serial_no = query.getString(columnIndexOrThrow2);
                    entityDailyBp.systolic_bp = query.getInt(columnIndexOrThrow3);
                    entityDailyBp.diastolic_bp = query.getInt(columnIndexOrThrow4);
                    entityDailyBp.is_sync_server = query.getInt(columnIndexOrThrow5) != 0;
                } else {
                    entityDailyBp = null;
                }
                return entityDailyBp;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class e extends ComputableLiveData<List<EntityHourlyBp>> {
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
        public List<EntityHourlyBp> compute() {
            if (this.g == null) {
                this.g = new a("hourly_bp", new String[0]);
                BpDataDao_Impl.this.f6942a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = BpDataDao_Impl.this.f6942a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_hour");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_hour");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("systolic_bp");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("diastolic_bp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("codevalues");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHourlyBp entityHourlyBp = new EntityHourlyBp();
                    entityHourlyBp.date = query.getString(columnIndexOrThrow);
                    entityHourlyBp.serial_no = query.getString(columnIndexOrThrow2);
                    entityHourlyBp.start_hour = query.getString(columnIndexOrThrow3);
                    entityHourlyBp.end_hour = query.getString(columnIndexOrThrow4);
                    entityHourlyBp.systolic_bp = query.getInt(columnIndexOrThrow5);
                    entityHourlyBp.diastolic_bp = query.getInt(columnIndexOrThrow6);
                    entityHourlyBp.codevalues = Convertors.toBpCodedValues(query.getString(columnIndexOrThrow7));
                    arrayList.add(entityHourlyBp);
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
    public class f extends ComputableLiveData<List<WeekLevelBp>> {
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
        public List<WeekLevelBp> compute() {
            if (this.g == null) {
                this.g = new a("daily_bp", new String[0]);
                BpDataDao_Impl.this.f6942a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = BpDataDao_Impl.this.f6942a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("systolic_bp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("diastolic_bp");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("week");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    WeekLevelBp weekLevelBp = new WeekLevelBp();
                    weekLevelBp.systolic_bp = query.getInt(columnIndexOrThrow);
                    weekLevelBp.diastolic_bp = query.getInt(columnIndexOrThrow2);
                    weekLevelBp.week = query.getString(columnIndexOrThrow3);
                    weekLevelBp.year = query.getString(columnIndexOrThrow4);
                    arrayList.add(weekLevelBp);
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
    public class g extends ComputableLiveData<List<MonthLevelBp>> {
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
        public List<MonthLevelBp> compute() {
            if (this.g == null) {
                this.g = new a("daily_bp", new String[0]);
                BpDataDao_Impl.this.f6942a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = BpDataDao_Impl.this.f6942a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("systolic_bp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("diastolic_bp");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("month");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MonthLevelBp monthLevelBp = new MonthLevelBp();
                    monthLevelBp.systolic_bp = query.getInt(columnIndexOrThrow);
                    monthLevelBp.diastolic_bp = query.getInt(columnIndexOrThrow2);
                    monthLevelBp.month = query.getString(columnIndexOrThrow3);
                    monthLevelBp.year = query.getString(columnIndexOrThrow4);
                    arrayList.add(monthLevelBp);
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

    public BpDataDao_Impl(RoomDatabase roomDatabase) {
        this.f6942a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public List<EntityHourlyBp> getHourLevelBp(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_bp WHERE serial_no=? AND date=?", 2);
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
        Cursor query = this.f6942a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_hour");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_hour");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("systolic_bp");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("diastolic_bp");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("codevalues");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHourlyBp entityHourlyBp = new EntityHourlyBp();
                entityHourlyBp.date = query.getString(columnIndexOrThrow);
                entityHourlyBp.serial_no = query.getString(columnIndexOrThrow2);
                entityHourlyBp.start_hour = query.getString(columnIndexOrThrow3);
                entityHourlyBp.end_hour = query.getString(columnIndexOrThrow4);
                entityHourlyBp.systolic_bp = query.getInt(columnIndexOrThrow5);
                entityHourlyBp.diastolic_bp = query.getInt(columnIndexOrThrow6);
                entityHourlyBp.codevalues = Convertors.toBpCodedValues(query.getString(columnIndexOrThrow7));
                arrayList.add(entityHourlyBp);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public List<EntityHourlyBp> getHourlyBPDataFrom(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_bp where datetime(date ||' '|| start_hour) > datetime(?) order by datetime(date ||' '|| start_hour) ", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6942a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_hour");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_hour");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("systolic_bp");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("diastolic_bp");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("codevalues");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHourlyBp entityHourlyBp = new EntityHourlyBp();
                entityHourlyBp.date = query.getString(columnIndexOrThrow);
                entityHourlyBp.serial_no = query.getString(columnIndexOrThrow2);
                entityHourlyBp.start_hour = query.getString(columnIndexOrThrow3);
                entityHourlyBp.end_hour = query.getString(columnIndexOrThrow4);
                entityHourlyBp.systolic_bp = query.getInt(columnIndexOrThrow5);
                entityHourlyBp.diastolic_bp = query.getInt(columnIndexOrThrow6);
                entityHourlyBp.codevalues = Convertors.toBpCodedValues(query.getString(columnIndexOrThrow7));
                arrayList.add(entityHourlyBp);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public EntityHourlyBp getLatestRecordHourly(String str) {
        EntityHourlyBp entityHourlyBp;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_bp where diastolic_bp>0 AND systolic_bp>0 AND serial_no=?  ORDER BY date DESC,  start_hour DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6942a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_hour");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_hour");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("systolic_bp");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("diastolic_bp");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("codevalues");
            if (query.moveToFirst()) {
                entityHourlyBp = new EntityHourlyBp();
                entityHourlyBp.date = query.getString(columnIndexOrThrow);
                entityHourlyBp.serial_no = query.getString(columnIndexOrThrow2);
                entityHourlyBp.start_hour = query.getString(columnIndexOrThrow3);
                entityHourlyBp.end_hour = query.getString(columnIndexOrThrow4);
                entityHourlyBp.systolic_bp = query.getInt(columnIndexOrThrow5);
                entityHourlyBp.diastolic_bp = query.getInt(columnIndexOrThrow6);
                entityHourlyBp.codevalues = Convertors.toBpCodedValues(query.getString(columnIndexOrThrow7));
            } else {
                entityHourlyBp = null;
            }
            return entityHourlyBp;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public LiveData<EntityDailyBp> getLiveDayLevelBp(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_bp WHERE serial_no=? AND date=?", 2);
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
        return new d(this.f6942a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public LiveData<List<EntityDailyBp>> getLiveDayWiseBp(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_bp WHERE serial_no=? GROUP BY date", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new c(this.f6942a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public LiveData<List<EntityHourlyBp>> getLiveHourLevelBp(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_bp WHERE serial_no=? AND date=?", 2);
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
        return new e(this.f6942a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public LiveData<List<MonthLevelBp>> getLiveMonthWiseBp(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT AVG((case when systolic_bp>0 then systolic_bp END)) as systolic_bp, AVG((case when diastolic_bp>0 then diastolic_bp END)) as diastolic_bp,  strftime('%m',date)+1 as month,strftime('%Y',date) as year FROM daily_bp WHERE serial_no=? GROUP BY month,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new g(this.f6942a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public LiveData<List<WeekLevelBp>> getLiveWeekWiseBp(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT AVG((case when systolic_bp>0 then systolic_bp END)) as systolic_bp, AVG((case when diastolic_bp>0 then diastolic_bp END)) as diastolic_bp,  strftime('%W',date)+1 as week,strftime('%Y',date) as year FROM daily_bp WHERE serial_no=? GROUP BY week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new f(this.f6942a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_bp where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6942a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public List<EntityDailyBp> getUnSyncedBpData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_bp WHERE serial_no=? AND is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6942a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("systolic_bp");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("diastolic_bp");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityDailyBp entityDailyBp = new EntityDailyBp();
                entityDailyBp.date = query.getString(columnIndexOrThrow);
                entityDailyBp.serial_no = query.getString(columnIndexOrThrow2);
                entityDailyBp.systolic_bp = query.getInt(columnIndexOrThrow3);
                entityDailyBp.diastolic_bp = query.getInt(columnIndexOrThrow4);
                entityDailyBp.is_sync_server = query.getInt(columnIndexOrThrow5) != 0;
                arrayList.add(entityDailyBp);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public void insert(EntityDailyBp entityDailyBp) {
        this.f6942a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) entityDailyBp);
            this.f6942a.setTransactionSuccessful();
        } finally {
            this.f6942a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public void insert(List<EntityHourlyBp> list) {
        this.f6942a.beginTransaction();
        try {
            this.c.insert((Iterable) list);
            this.f6942a.setTransactionSuccessful();
        } finally {
            this.f6942a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_bp where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6942a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.bp.BpDataDao
    public EntityHourlyBp getLatestRecordHourly(String str, String str2) {
        EntityHourlyBp entityHourlyBp;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_bp where diastolic_bp>0 AND systolic_bp>0 AND serial_no=?  AND date=? ORDER BY  start_hour DESC LIMIT 1", 2);
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
        Cursor query = this.f6942a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_hour");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_hour");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("systolic_bp");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("diastolic_bp");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("codevalues");
            if (query.moveToFirst()) {
                entityHourlyBp = new EntityHourlyBp();
                entityHourlyBp.date = query.getString(columnIndexOrThrow);
                entityHourlyBp.serial_no = query.getString(columnIndexOrThrow2);
                entityHourlyBp.start_hour = query.getString(columnIndexOrThrow3);
                entityHourlyBp.end_hour = query.getString(columnIndexOrThrow4);
                entityHourlyBp.systolic_bp = query.getInt(columnIndexOrThrow5);
                entityHourlyBp.diastolic_bp = query.getInt(columnIndexOrThrow6);
                entityHourlyBp.codevalues = Convertors.toBpCodedValues(query.getString(columnIndexOrThrow7));
            } else {
                entityHourlyBp = null;
            }
            return entityHourlyBp;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
