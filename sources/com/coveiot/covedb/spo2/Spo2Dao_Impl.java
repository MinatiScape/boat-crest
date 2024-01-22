package com.coveiot.covedb.spo2;

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
import com.coveiot.covedb.spo2.entity.DailyPeriodicSpo2;
import com.coveiot.covedb.spo2.entity.EntityHourlySpo2;
import com.coveiot.covedb.spo2.model.MonthlySpo2Data;
import com.coveiot.covedb.spo2.model.WeeklySpo2Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class Spo2Dao_Impl implements Spo2Dao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6977a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<DailyPeriodicSpo2> {
        public a(Spo2Dao_Impl spo2Dao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, DailyPeriodicSpo2 dailyPeriodicSpo2) {
            String str = dailyPeriodicSpo2.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = dailyPeriodicSpo2.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            supportSQLiteStatement.bindDouble(3, dailyPeriodicSpo2.spo2_avg);
            supportSQLiteStatement.bindLong(4, dailyPeriodicSpo2.spo2_high);
            supportSQLiteStatement.bindLong(5, dailyPeriodicSpo2.spo2_low);
            supportSQLiteStatement.bindLong(6, dailyPeriodicSpo2.is_sync_server ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_spo2`(`date`,`serial_no`,`spo2_avg`,`spo2_high`,`spo2_low`,`is_sync_server`) VALUES (?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<EntityHourlySpo2> {
        public b(Spo2Dao_Impl spo2Dao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHourlySpo2 entityHourlySpo2) {
            if (entityHourlySpo2.getId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, entityHourlySpo2.getId());
            }
            String str = entityHourlySpo2.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            String str2 = entityHourlySpo2.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str2);
            }
            if (entityHourlySpo2.getStartTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityHourlySpo2.getStartTime());
            }
            if (entityHourlySpo2.getEndTime() == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, entityHourlySpo2.getEndTime());
            }
            supportSQLiteStatement.bindDouble(6, entityHourlySpo2.spo2_avg);
            supportSQLiteStatement.bindLong(7, entityHourlySpo2.spo2_high);
            supportSQLiteStatement.bindLong(8, entityHourlySpo2.spo2_low);
            String fromListInteger = ConvertorsMediaList.fromListInteger(entityHourlySpo2.codevalue);
            if (fromListInteger == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, fromListInteger);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourly_spo2`(`id`,`date`,`serial_no`,`start_time`,`end_time`,`spo2_avg`,`spo2_high`,`spo2_low`,`codevalue`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends ComputableLiveData<List<EntityHourlySpo2>> {
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
        public List<EntityHourlySpo2> compute() {
            if (this.g == null) {
                this.g = new a("hourly_spo2", new String[0]);
                Spo2Dao_Impl.this.f6977a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = Spo2Dao_Impl.this.f6977a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("spo2_avg");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("spo2_high");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2_low");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("codevalue");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHourlySpo2 entityHourlySpo2 = new EntityHourlySpo2();
                    entityHourlySpo2.setId(query.getString(columnIndexOrThrow));
                    entityHourlySpo2.mDate = query.getString(columnIndexOrThrow2);
                    entityHourlySpo2.mac_address = query.getString(columnIndexOrThrow3);
                    entityHourlySpo2.setStartTime(query.getString(columnIndexOrThrow4));
                    entityHourlySpo2.setEndTime(query.getString(columnIndexOrThrow5));
                    entityHourlySpo2.spo2_avg = query.getDouble(columnIndexOrThrow6);
                    entityHourlySpo2.spo2_high = query.getInt(columnIndexOrThrow7);
                    entityHourlySpo2.spo2_low = query.getInt(columnIndexOrThrow8);
                    entityHourlySpo2.codevalue = ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow9));
                    arrayList.add(entityHourlySpo2);
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
    public class d extends ComputableLiveData<DailyPeriodicSpo2> {
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
        public DailyPeriodicSpo2 compute() {
            DailyPeriodicSpo2 dailyPeriodicSpo2;
            if (this.g == null) {
                this.g = new a("daily_spo2", new String[0]);
                Spo2Dao_Impl.this.f6977a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = Spo2Dao_Impl.this.f6977a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("spo2_avg");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("spo2_high");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("spo2_low");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("is_sync_server");
                if (query.moveToFirst()) {
                    dailyPeriodicSpo2 = new DailyPeriodicSpo2();
                    dailyPeriodicSpo2.mDate = query.getString(columnIndexOrThrow);
                    dailyPeriodicSpo2.mac_address = query.getString(columnIndexOrThrow2);
                    dailyPeriodicSpo2.spo2_avg = query.getDouble(columnIndexOrThrow3);
                    dailyPeriodicSpo2.spo2_high = query.getInt(columnIndexOrThrow4);
                    dailyPeriodicSpo2.spo2_low = query.getInt(columnIndexOrThrow5);
                    dailyPeriodicSpo2.is_sync_server = query.getInt(columnIndexOrThrow6) != 0;
                } else {
                    dailyPeriodicSpo2 = null;
                }
                return dailyPeriodicSpo2;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class e extends ComputableLiveData<List<DailyPeriodicSpo2>> {
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
        public List<DailyPeriodicSpo2> compute() {
            if (this.g == null) {
                this.g = new a("daily_spo2", new String[0]);
                Spo2Dao_Impl.this.f6977a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = Spo2Dao_Impl.this.f6977a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("spo2_avg");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("spo2_high");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("spo2_low");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailyPeriodicSpo2 dailyPeriodicSpo2 = new DailyPeriodicSpo2();
                    dailyPeriodicSpo2.mDate = query.getString(columnIndexOrThrow);
                    dailyPeriodicSpo2.mac_address = query.getString(columnIndexOrThrow2);
                    dailyPeriodicSpo2.spo2_avg = query.getDouble(columnIndexOrThrow3);
                    dailyPeriodicSpo2.spo2_high = query.getInt(columnIndexOrThrow4);
                    dailyPeriodicSpo2.spo2_low = query.getInt(columnIndexOrThrow5);
                    dailyPeriodicSpo2.is_sync_server = query.getInt(columnIndexOrThrow6) != 0;
                    arrayList.add(dailyPeriodicSpo2);
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
    public class f extends ComputableLiveData<List<WeeklySpo2Data>> {
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
        public List<WeeklySpo2Data> compute() {
            if (this.g == null) {
                this.g = new a("daily_spo2", new String[0]);
                Spo2Dao_Impl.this.f6977a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = Spo2Dao_Impl.this.f6977a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("spo2_high");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("spo2_low");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("spo2_avg");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("week");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    WeeklySpo2Data weeklySpo2Data = new WeeklySpo2Data();
                    weeklySpo2Data.mac_address = query.getString(columnIndexOrThrow);
                    weeklySpo2Data.spo2_high = query.getDouble(columnIndexOrThrow2);
                    weeklySpo2Data.spo2_low = query.getDouble(columnIndexOrThrow3);
                    weeklySpo2Data.spo2_avg = query.getDouble(columnIndexOrThrow4);
                    weeklySpo2Data.setWeek(query.getString(columnIndexOrThrow5));
                    weeklySpo2Data.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(weeklySpo2Data);
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
    public class g extends ComputableLiveData<List<MonthlySpo2Data>> {
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
        public List<MonthlySpo2Data> compute() {
            if (this.g == null) {
                this.g = new a("daily_spo2", new String[0]);
                Spo2Dao_Impl.this.f6977a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = Spo2Dao_Impl.this.f6977a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("spo2_high");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("spo2_low");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("spo2_avg");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("month");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MonthlySpo2Data monthlySpo2Data = new MonthlySpo2Data();
                    monthlySpo2Data.mac_address = query.getString(columnIndexOrThrow);
                    monthlySpo2Data.spo2_high = query.getDouble(columnIndexOrThrow2);
                    monthlySpo2Data.spo2_low = query.getDouble(columnIndexOrThrow3);
                    monthlySpo2Data.spo2_avg = query.getDouble(columnIndexOrThrow4);
                    monthlySpo2Data.setMonth(query.getString(columnIndexOrThrow5));
                    monthlySpo2Data.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(monthlySpo2Data);
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

    public Spo2Dao_Impl(RoomDatabase roomDatabase) {
        this.f6977a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public DailyPeriodicSpo2 getDailySpo2Data(String str, String str2) {
        DailyPeriodicSpo2 dailyPeriodicSpo2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_spo2 WHERE date=? AND serial_no=?", 2);
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
        Cursor query = this.f6977a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("spo2_avg");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("spo2_high");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("spo2_low");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("is_sync_server");
            if (query.moveToFirst()) {
                dailyPeriodicSpo2 = new DailyPeriodicSpo2();
                dailyPeriodicSpo2.mDate = query.getString(columnIndexOrThrow);
                dailyPeriodicSpo2.mac_address = query.getString(columnIndexOrThrow2);
                dailyPeriodicSpo2.spo2_avg = query.getDouble(columnIndexOrThrow3);
                dailyPeriodicSpo2.spo2_high = query.getInt(columnIndexOrThrow4);
                dailyPeriodicSpo2.spo2_low = query.getInt(columnIndexOrThrow5);
                if (query.getInt(columnIndexOrThrow6) == 0) {
                    z = false;
                }
                dailyPeriodicSpo2.is_sync_server = z;
            } else {
                dailyPeriodicSpo2 = null;
            }
            return dailyPeriodicSpo2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public List<EntityHourlySpo2> getHourlySpo2Data(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_spo2 WHERE serial_no=? AND date=?", 2);
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
        Cursor query = this.f6977a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("spo2_avg");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("spo2_high");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2_low");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("codevalue");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHourlySpo2 entityHourlySpo2 = new EntityHourlySpo2();
                entityHourlySpo2.setId(query.getString(columnIndexOrThrow));
                entityHourlySpo2.mDate = query.getString(columnIndexOrThrow2);
                entityHourlySpo2.mac_address = query.getString(columnIndexOrThrow3);
                entityHourlySpo2.setStartTime(query.getString(columnIndexOrThrow4));
                entityHourlySpo2.setEndTime(query.getString(columnIndexOrThrow5));
                entityHourlySpo2.spo2_avg = query.getDouble(columnIndexOrThrow6);
                entityHourlySpo2.spo2_high = query.getInt(columnIndexOrThrow7);
                entityHourlySpo2.spo2_low = query.getInt(columnIndexOrThrow8);
                entityHourlySpo2.codevalue = ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow9));
                arrayList.add(entityHourlySpo2);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public List<EntityHourlySpo2> getHourlySpo2DataFrom(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_spo2 where datetime(date ||' '|| start_time) >= datetime(?) AND serial_no=? order by datetime(date ||' '|| start_time) ", 2);
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
        Cursor query = this.f6977a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("spo2_avg");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("spo2_high");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2_low");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("codevalue");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHourlySpo2 entityHourlySpo2 = new EntityHourlySpo2();
                entityHourlySpo2.setId(query.getString(columnIndexOrThrow));
                entityHourlySpo2.mDate = query.getString(columnIndexOrThrow2);
                entityHourlySpo2.mac_address = query.getString(columnIndexOrThrow3);
                entityHourlySpo2.setStartTime(query.getString(columnIndexOrThrow4));
                entityHourlySpo2.setEndTime(query.getString(columnIndexOrThrow5));
                entityHourlySpo2.spo2_avg = query.getDouble(columnIndexOrThrow6);
                entityHourlySpo2.spo2_high = query.getInt(columnIndexOrThrow7);
                entityHourlySpo2.spo2_low = query.getInt(columnIndexOrThrow8);
                entityHourlySpo2.codevalue = ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow9));
                arrayList.add(entityHourlySpo2);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public EntityHourlySpo2 getLatestHighSpo2RecordHourly(String str, String str2) {
        EntityHourlySpo2 entityHourlySpo2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * , MAX(spo2_high) from hourly_spo2 where date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 2);
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
        Cursor query = this.f6977a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("spo2_avg");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("spo2_high");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2_low");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("codevalue");
            if (query.moveToFirst()) {
                entityHourlySpo2 = new EntityHourlySpo2();
                entityHourlySpo2.setId(query.getString(columnIndexOrThrow));
                entityHourlySpo2.mDate = query.getString(columnIndexOrThrow2);
                entityHourlySpo2.mac_address = query.getString(columnIndexOrThrow3);
                entityHourlySpo2.setStartTime(query.getString(columnIndexOrThrow4));
                entityHourlySpo2.setEndTime(query.getString(columnIndexOrThrow5));
                entityHourlySpo2.spo2_avg = query.getDouble(columnIndexOrThrow6);
                entityHourlySpo2.spo2_high = query.getInt(columnIndexOrThrow7);
                entityHourlySpo2.spo2_low = query.getInt(columnIndexOrThrow8);
                entityHourlySpo2.codevalue = ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow9));
            } else {
                entityHourlySpo2 = null;
            }
            return entityHourlySpo2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public EntityHourlySpo2 getLatestRecordHourly(String str) {
        EntityHourlySpo2 entityHourlySpo2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_spo2 where spo2_low>0 AND spo2_high>0 AND serial_no=? ORDER BY id DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6977a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("spo2_avg");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("spo2_high");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2_low");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("codevalue");
            if (query.moveToFirst()) {
                entityHourlySpo2 = new EntityHourlySpo2();
                entityHourlySpo2.setId(query.getString(columnIndexOrThrow));
                entityHourlySpo2.mDate = query.getString(columnIndexOrThrow2);
                entityHourlySpo2.mac_address = query.getString(columnIndexOrThrow3);
                entityHourlySpo2.setStartTime(query.getString(columnIndexOrThrow4));
                entityHourlySpo2.setEndTime(query.getString(columnIndexOrThrow5));
                entityHourlySpo2.spo2_avg = query.getDouble(columnIndexOrThrow6);
                entityHourlySpo2.spo2_high = query.getInt(columnIndexOrThrow7);
                entityHourlySpo2.spo2_low = query.getInt(columnIndexOrThrow8);
                entityHourlySpo2.codevalue = ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow9));
            } else {
                entityHourlySpo2 = null;
            }
            return entityHourlySpo2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public EntityHourlySpo2 getLatestSpo2RecordHourly(String str, String str2, float f2, float f3) {
        EntityHourlySpo2 entityHourlySpo2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_spo2 where spo2_low>0 AND spo2_high>0 AND spo2_low>=? AND spo2_high<=? AND date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 4);
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
        Cursor query = this.f6977a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("spo2_avg");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("spo2_high");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2_low");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("codevalue");
            if (query.moveToFirst()) {
                entityHourlySpo2 = new EntityHourlySpo2();
                entityHourlySpo2.setId(query.getString(columnIndexOrThrow));
                entityHourlySpo2.mDate = query.getString(columnIndexOrThrow2);
                entityHourlySpo2.mac_address = query.getString(columnIndexOrThrow3);
                entityHourlySpo2.setStartTime(query.getString(columnIndexOrThrow4));
                entityHourlySpo2.setEndTime(query.getString(columnIndexOrThrow5));
                entityHourlySpo2.spo2_avg = query.getDouble(columnIndexOrThrow6);
                entityHourlySpo2.spo2_high = query.getInt(columnIndexOrThrow7);
                entityHourlySpo2.spo2_low = query.getInt(columnIndexOrThrow8);
                entityHourlySpo2.codevalue = ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow9));
            } else {
                entityHourlySpo2 = null;
            }
            return entityHourlySpo2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public LiveData<DailyPeriodicSpo2> getLiveDailySpo2Data(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_spo2 WHERE date=? AND serial_no=?", 2);
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
        return new d(this.f6977a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public LiveData<List<DailyPeriodicSpo2>> getLiveDayWiseSpo2DataByMacAddress(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_spo2 WHERE serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new e(this.f6977a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public LiveData<List<EntityHourlySpo2>> getLiveHourlySpo2Data(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_spo2 WHERE date=? AND serial_no=?", 2);
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
        return new c(this.f6977a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public LiveData<List<MonthlySpo2Data>> getLiveMonthWiseSpo2Data(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX(spo2_high) as spo2_high, MIN((case when spo2_low>0 then spo2_low END)) as spo2_low, AVG(spo2_avg) as spo2_avg, strftime('%m',date) as month,strftime('%Y',date) as year from daily_spo2  WHERE serial_no=?  group by month,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new g(this.f6977a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public LiveData<List<WeeklySpo2Data>> getLiveWeekWiseSpo2Data(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX(spo2_high) as spo2_high,  MIN((case when spo2_low>0 then spo2_low END)) as spo2_low, AVG(spo2_avg) as spo2_avg, strftime('%W',date)+1 as week, strftime('%Y',date) as year from daily_spo2  WHERE serial_no=? group by week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new f(this.f6977a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_spo2 where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6977a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public List<DailyPeriodicSpo2> getTotalUnSyncedData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From daily_spo2 WHERE serial_no=? AND is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6977a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("spo2_avg");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("spo2_high");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("spo2_low");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyPeriodicSpo2 dailyPeriodicSpo2 = new DailyPeriodicSpo2();
                dailyPeriodicSpo2.mDate = query.getString(columnIndexOrThrow);
                dailyPeriodicSpo2.mac_address = query.getString(columnIndexOrThrow2);
                dailyPeriodicSpo2.spo2_avg = query.getDouble(columnIndexOrThrow3);
                dailyPeriodicSpo2.spo2_high = query.getInt(columnIndexOrThrow4);
                dailyPeriodicSpo2.spo2_low = query.getInt(columnIndexOrThrow5);
                dailyPeriodicSpo2.is_sync_server = query.getInt(columnIndexOrThrow6) != 0;
                arrayList.add(dailyPeriodicSpo2);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public void insert(DailyPeriodicSpo2 dailyPeriodicSpo2) {
        this.f6977a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) dailyPeriodicSpo2);
            this.f6977a.setTransactionSuccessful();
        } finally {
            this.f6977a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public void insertAll(List<EntityHourlySpo2> list) {
        this.f6977a.beginTransaction();
        try {
            this.c.insert((Iterable) list);
            this.f6977a.setTransactionSuccessful();
        } finally {
            this.f6977a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_spo2 where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6977a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public EntityHourlySpo2 getLatestRecordHourly(String str, float f2, float f3) {
        EntityHourlySpo2 entityHourlySpo2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_spo2 where spo2_low>0 AND spo2_high>0 AND spo2_low>=? AND spo2_high<=? AND serial_no=? ORDER BY id DESC LIMIT 1", 3);
        acquire.bindDouble(1, f2);
        acquire.bindDouble(2, f3);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        Cursor query = this.f6977a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("spo2_avg");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("spo2_high");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2_low");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("codevalue");
            if (query.moveToFirst()) {
                entityHourlySpo2 = new EntityHourlySpo2();
                entityHourlySpo2.setId(query.getString(columnIndexOrThrow));
                entityHourlySpo2.mDate = query.getString(columnIndexOrThrow2);
                entityHourlySpo2.mac_address = query.getString(columnIndexOrThrow3);
                entityHourlySpo2.setStartTime(query.getString(columnIndexOrThrow4));
                entityHourlySpo2.setEndTime(query.getString(columnIndexOrThrow5));
                entityHourlySpo2.spo2_avg = query.getDouble(columnIndexOrThrow6);
                entityHourlySpo2.spo2_high = query.getInt(columnIndexOrThrow7);
                entityHourlySpo2.spo2_low = query.getInt(columnIndexOrThrow8);
                entityHourlySpo2.codevalue = ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow9));
            } else {
                entityHourlySpo2 = null;
            }
            return entityHourlySpo2;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.spo2.Spo2Dao
    public EntityHourlySpo2 getLatestSpo2RecordHourly(String str, String str2) {
        EntityHourlySpo2 entityHourlySpo2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_spo2 where spo2_low>0 AND spo2_high>0 AND date=? AND serial_no=? ORDER BY id DESC LIMIT 1", 2);
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
        Cursor query = this.f6977a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("spo2_avg");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("spo2_high");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2_low");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("codevalue");
            if (query.moveToFirst()) {
                entityHourlySpo2 = new EntityHourlySpo2();
                entityHourlySpo2.setId(query.getString(columnIndexOrThrow));
                entityHourlySpo2.mDate = query.getString(columnIndexOrThrow2);
                entityHourlySpo2.mac_address = query.getString(columnIndexOrThrow3);
                entityHourlySpo2.setStartTime(query.getString(columnIndexOrThrow4));
                entityHourlySpo2.setEndTime(query.getString(columnIndexOrThrow5));
                entityHourlySpo2.spo2_avg = query.getDouble(columnIndexOrThrow6);
                entityHourlySpo2.spo2_high = query.getInt(columnIndexOrThrow7);
                entityHourlySpo2.spo2_low = query.getInt(columnIndexOrThrow8);
                entityHourlySpo2.codevalue = ConvertorsMediaList.frommIntegerToListFloat(query.getString(columnIndexOrThrow9));
            } else {
                entityHourlySpo2 = null;
            }
            return entityHourlySpo2;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
