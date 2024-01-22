package com.coveiot.covedb.heartrate;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.Convertors;
import com.coveiot.covedb.heartrate.model.MonthlyHeartRateData;
import com.coveiot.covedb.heartrate.model.WeeklyHeartRateData;
import com.coveiot.covedb.stress.model.MinMaxData;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class HeartRateDao_Impl implements HeartRateDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6950a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;
    public final EntityDeletionOrUpdateAdapter d;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHourlyHeartRateData> {
        public a(HeartRateDao_Impl heartRateDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHourlyHeartRateData entityHourlyHeartRateData) {
            String str = entityHourlyHeartRateData.serial_no;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = entityHourlyHeartRateData.f6949a;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            String str3 = entityHourlyHeartRateData.b;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str3);
            }
            if (entityHourlyHeartRateData.getDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityHourlyHeartRateData.getDate());
            }
            supportSQLiteStatement.bindLong(5, entityHourlyHeartRateData.d);
            supportSQLiteStatement.bindLong(6, entityHourlyHeartRateData.e);
            supportSQLiteStatement.bindDouble(7, entityHourlyHeartRateData.f);
            supportSQLiteStatement.bindLong(8, entityHourlyHeartRateData.g);
            supportSQLiteStatement.bindLong(9, entityHourlyHeartRateData.h);
            String frommArrayLisr = Convertors.frommArrayLisr(entityHourlyHeartRateData.getCodedValues());
            if (frommArrayLisr == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, frommArrayLisr);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourly_heart_rate_table`(`serial_no`,`start_time`,`end_time`,`date`,`min_heart_rate`,`max_heart_rate`,`avg_heart_rate`,`rest_heart_rate`,`time_stamp`,`coded_values`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<EntityDailyHeartRateData> {
        public b(HeartRateDao_Impl heartRateDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityDailyHeartRateData entityDailyHeartRateData) {
            String str = entityDailyHeartRateData.serial_no;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            if (entityDailyHeartRateData.getDate() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityDailyHeartRateData.getDate());
            }
            supportSQLiteStatement.bindLong(3, entityDailyHeartRateData.b);
            supportSQLiteStatement.bindLong(4, entityDailyHeartRateData.c);
            supportSQLiteStatement.bindLong(5, entityDailyHeartRateData.d);
            supportSQLiteStatement.bindLong(6, entityDailyHeartRateData.e);
            supportSQLiteStatement.bindDouble(7, entityDailyHeartRateData.getColumn_1());
            supportSQLiteStatement.bindDouble(8, entityDailyHeartRateData.getColumn_2());
            if (entityDailyHeartRateData.getColumn_3() == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, entityDailyHeartRateData.getColumn_3());
            }
            supportSQLiteStatement.bindLong(10, entityDailyHeartRateData.is_sync_server ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_heart_rate_table`(`serial_no`,`date`,`min_heart_rate`,`max_heart_rate`,`avg_heart_rate`,`rest_heart_rate`,`column_1`,`column_2`,`column_3`,`is_sync_server`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends EntityDeletionOrUpdateAdapter<EntityHourlyHeartRateData> {
        public c(HeartRateDao_Impl heartRateDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHourlyHeartRateData entityHourlyHeartRateData) {
            String str = entityHourlyHeartRateData.serial_no;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = entityHourlyHeartRateData.f6949a;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            String str3 = entityHourlyHeartRateData.b;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str3);
            }
            if (entityHourlyHeartRateData.getDate() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityHourlyHeartRateData.getDate());
            }
            supportSQLiteStatement.bindLong(5, entityHourlyHeartRateData.d);
            supportSQLiteStatement.bindLong(6, entityHourlyHeartRateData.e);
            supportSQLiteStatement.bindDouble(7, entityHourlyHeartRateData.f);
            supportSQLiteStatement.bindLong(8, entityHourlyHeartRateData.g);
            supportSQLiteStatement.bindLong(9, entityHourlyHeartRateData.h);
            String frommArrayLisr = Convertors.frommArrayLisr(entityHourlyHeartRateData.getCodedValues());
            if (frommArrayLisr == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, frommArrayLisr);
            }
            String str4 = entityHourlyHeartRateData.serial_no;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, str4);
            }
            String str5 = entityHourlyHeartRateData.f6949a;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, str5);
            }
            if (entityHourlyHeartRateData.getDate() == null) {
                supportSQLiteStatement.bindNull(13);
            } else {
                supportSQLiteStatement.bindString(13, entityHourlyHeartRateData.getDate());
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE OR ABORT `hourly_heart_rate_table` SET `serial_no` = ?,`start_time` = ?,`end_time` = ?,`date` = ?,`min_heart_rate` = ?,`max_heart_rate` = ?,`avg_heart_rate` = ?,`rest_heart_rate` = ?,`time_stamp` = ?,`coded_values` = ? WHERE `serial_no` = ? AND `start_time` = ? AND `date` = ?";
        }
    }

    /* loaded from: classes8.dex */
    public class d extends ComputableLiveData<List<EntityHourlyHeartRateData>> {
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
        public List<EntityHourlyHeartRateData> compute() {
            if (this.g == null) {
                this.g = new a("hourly_heart_rate_table", new String[0]);
                HeartRateDao_Impl.this.f6950a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HeartRateDao_Impl.this.f6950a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_heart_rate");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_heart_rate");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_heart_rate");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("rest_heart_rate");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("time_stamp");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("coded_values");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHourlyHeartRateData entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                    entityHourlyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                    entityHourlyHeartRateData.f6949a = query.getString(columnIndexOrThrow2);
                    entityHourlyHeartRateData.b = query.getString(columnIndexOrThrow3);
                    entityHourlyHeartRateData.setDate(query.getString(columnIndexOrThrow4));
                    entityHourlyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                    entityHourlyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                    entityHourlyHeartRateData.f = query.getFloat(columnIndexOrThrow7);
                    entityHourlyHeartRateData.g = query.getInt(columnIndexOrThrow8);
                    entityHourlyHeartRateData.h = query.getLong(columnIndexOrThrow9);
                    entityHourlyHeartRateData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow10)));
                    arrayList.add(entityHourlyHeartRateData);
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
    public class e extends ComputableLiveData<EntityDailyHeartRateData> {
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
        public EntityDailyHeartRateData compute() {
            EntityDailyHeartRateData entityDailyHeartRateData;
            if (this.g == null) {
                this.g = new a("daily_heart_rate_table", new String[0]);
                HeartRateDao_Impl.this.f6950a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HeartRateDao_Impl.this.f6950a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("min_heart_rate");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("max_heart_rate");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("avg_heart_rate");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("rest_heart_rate");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("column_1");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("column_2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("column_3");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("is_sync_server");
                if (query.moveToFirst()) {
                    entityDailyHeartRateData = new EntityDailyHeartRateData();
                    entityDailyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                    entityDailyHeartRateData.setDate(query.getString(columnIndexOrThrow2));
                    entityDailyHeartRateData.b = query.getInt(columnIndexOrThrow3);
                    entityDailyHeartRateData.c = query.getInt(columnIndexOrThrow4);
                    entityDailyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                    entityDailyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                    entityDailyHeartRateData.setColumn_1(query.getFloat(columnIndexOrThrow7));
                    entityDailyHeartRateData.setColumn_2(query.getFloat(columnIndexOrThrow8));
                    entityDailyHeartRateData.setColumn_3(query.getString(columnIndexOrThrow9));
                    entityDailyHeartRateData.is_sync_server = query.getInt(columnIndexOrThrow10) != 0;
                } else {
                    entityDailyHeartRateData = null;
                }
                return entityDailyHeartRateData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class f extends ComputableLiveData<List<EntityDailyHeartRateData>> {
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
        public List<EntityDailyHeartRateData> compute() {
            if (this.g == null) {
                this.g = new a("daily_heart_rate_table", new String[0]);
                HeartRateDao_Impl.this.f6950a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HeartRateDao_Impl.this.f6950a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("min_heart_rate");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("max_heart_rate");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("avg_heart_rate");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("rest_heart_rate");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("column_1");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("column_2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("column_3");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityDailyHeartRateData entityDailyHeartRateData = new EntityDailyHeartRateData();
                    entityDailyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                    entityDailyHeartRateData.setDate(query.getString(columnIndexOrThrow2));
                    entityDailyHeartRateData.b = query.getInt(columnIndexOrThrow3);
                    entityDailyHeartRateData.c = query.getInt(columnIndexOrThrow4);
                    entityDailyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                    entityDailyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                    entityDailyHeartRateData.setColumn_1(query.getFloat(columnIndexOrThrow7));
                    entityDailyHeartRateData.setColumn_2(query.getFloat(columnIndexOrThrow8));
                    entityDailyHeartRateData.setColumn_3(query.getString(columnIndexOrThrow9));
                    entityDailyHeartRateData.is_sync_server = query.getInt(columnIndexOrThrow10) != 0;
                    arrayList.add(entityDailyHeartRateData);
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
    public class g extends ComputableLiveData<List<WeeklyHeartRateData>> {
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
        public List<WeeklyHeartRateData> compute() {
            if (this.g == null) {
                this.g = new a("daily_heart_rate_table", new String[0]);
                HeartRateDao_Impl.this.f6950a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HeartRateDao_Impl.this.f6950a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("maxHeartRate");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("minHeartRate");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("avg_heart_rate");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("week");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    WeeklyHeartRateData weeklyHeartRateData = new WeeklyHeartRateData();
                    weeklyHeartRateData.mac_address = query.getString(columnIndexOrThrow);
                    weeklyHeartRateData.maxHeartRate = query.getInt(columnIndexOrThrow2);
                    weeklyHeartRateData.minHeartRate = query.getInt(columnIndexOrThrow3);
                    weeklyHeartRateData.avg_heart_rate = query.getInt(columnIndexOrThrow4);
                    weeklyHeartRateData.setWeek(query.getString(columnIndexOrThrow5));
                    weeklyHeartRateData.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(weeklyHeartRateData);
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
    public class h extends ComputableLiveData<List<MonthlyHeartRateData>> {
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
        public List<MonthlyHeartRateData> compute() {
            if (this.g == null) {
                this.g = new a("daily_heart_rate_table", new String[0]);
                HeartRateDao_Impl.this.f6950a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HeartRateDao_Impl.this.f6950a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("max_heart_rate");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("min_heart_rate");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("avg_heart_rate");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("month");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MonthlyHeartRateData monthlyHeartRateData = new MonthlyHeartRateData();
                    monthlyHeartRateData.mac_address = query.getString(columnIndexOrThrow);
                    monthlyHeartRateData.max_heart_rate = query.getInt(columnIndexOrThrow2);
                    monthlyHeartRateData.min_heart_rate = query.getInt(columnIndexOrThrow3);
                    monthlyHeartRateData.avg_heart_rate = query.getInt(columnIndexOrThrow4);
                    monthlyHeartRateData.setMonth(query.getString(columnIndexOrThrow5));
                    monthlyHeartRateData.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(monthlyHeartRateData);
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
    public class i extends ComputableLiveData<EntityHourlyHeartRateData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                i.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public EntityHourlyHeartRateData compute() {
            EntityHourlyHeartRateData entityHourlyHeartRateData;
            if (this.g == null) {
                this.g = new a("hourly_heart_rate_table", new String[0]);
                HeartRateDao_Impl.this.f6950a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = HeartRateDao_Impl.this.f6950a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_heart_rate");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_heart_rate");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_heart_rate");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("rest_heart_rate");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("time_stamp");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("coded_values");
                if (query.moveToFirst()) {
                    entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                    entityHourlyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                    entityHourlyHeartRateData.f6949a = query.getString(columnIndexOrThrow2);
                    entityHourlyHeartRateData.b = query.getString(columnIndexOrThrow3);
                    entityHourlyHeartRateData.setDate(query.getString(columnIndexOrThrow4));
                    entityHourlyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                    entityHourlyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                    entityHourlyHeartRateData.f = query.getFloat(columnIndexOrThrow7);
                    entityHourlyHeartRateData.g = query.getInt(columnIndexOrThrow8);
                    entityHourlyHeartRateData.h = query.getLong(columnIndexOrThrow9);
                    entityHourlyHeartRateData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow10)));
                } else {
                    entityHourlyHeartRateData = null;
                }
                return entityHourlyHeartRateData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    public HeartRateDao_Impl(RoomDatabase roomDatabase) {
        this.f6950a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public EntityDailyHeartRateData getDailyHeartRateData(String str, String str2) {
        EntityDailyHeartRateData entityDailyHeartRateData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_heart_rate_table WHERE date=? AND serial_no=?", 2);
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
        Cursor query = this.f6950a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("min_heart_rate");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("max_heart_rate");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("avg_heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("rest_heart_rate");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("column_1");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("column_2");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("column_3");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("is_sync_server");
            if (query.moveToFirst()) {
                entityDailyHeartRateData = new EntityDailyHeartRateData();
                entityDailyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                entityDailyHeartRateData.setDate(query.getString(columnIndexOrThrow2));
                entityDailyHeartRateData.b = query.getInt(columnIndexOrThrow3);
                entityDailyHeartRateData.c = query.getInt(columnIndexOrThrow4);
                entityDailyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                entityDailyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                entityDailyHeartRateData.setColumn_1(query.getFloat(columnIndexOrThrow7));
                entityDailyHeartRateData.setColumn_2(query.getFloat(columnIndexOrThrow8));
                entityDailyHeartRateData.setColumn_3(query.getString(columnIndexOrThrow9));
                if (query.getInt(columnIndexOrThrow10) == 0) {
                    z = false;
                }
                entityDailyHeartRateData.is_sync_server = z;
            } else {
                entityDailyHeartRateData = null;
            }
            return entityDailyHeartRateData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public List<EntityDailyHeartRateData> getDailyHeartRateDataByStartAndEndDate(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_heart_rate_table WHERE serial_no=? and date BETWEEN ? AND ?", 3);
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
        Cursor query = this.f6950a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("min_heart_rate");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("max_heart_rate");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("avg_heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("rest_heart_rate");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("column_1");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("column_2");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("column_3");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityDailyHeartRateData entityDailyHeartRateData = new EntityDailyHeartRateData();
                entityDailyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                entityDailyHeartRateData.setDate(query.getString(columnIndexOrThrow2));
                entityDailyHeartRateData.b = query.getInt(columnIndexOrThrow3);
                entityDailyHeartRateData.c = query.getInt(columnIndexOrThrow4);
                entityDailyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                entityDailyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                entityDailyHeartRateData.setColumn_1(query.getFloat(columnIndexOrThrow7));
                entityDailyHeartRateData.setColumn_2(query.getFloat(columnIndexOrThrow8));
                entityDailyHeartRateData.setColumn_3(query.getString(columnIndexOrThrow9));
                entityDailyHeartRateData.is_sync_server = query.getInt(columnIndexOrThrow10) != 0;
                arrayList.add(entityDailyHeartRateData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public EntityHourlyHeartRateData getHeartRateDataWithDateAndTime(String str, String str2, String str3, String str4) {
        EntityHourlyHeartRateData entityHourlyHeartRateData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_heart_rate_table WHERE serial_no=? AND date=? and start_time=? AND end_time=?", 4);
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
        Cursor query = this.f6950a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_heart_rate");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_heart_rate");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("rest_heart_rate");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("time_stamp");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("coded_values");
            if (query.moveToFirst()) {
                entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                entityHourlyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyHeartRateData.f6949a = query.getString(columnIndexOrThrow2);
                entityHourlyHeartRateData.b = query.getString(columnIndexOrThrow3);
                entityHourlyHeartRateData.setDate(query.getString(columnIndexOrThrow4));
                entityHourlyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyHeartRateData.f = query.getFloat(columnIndexOrThrow7);
                entityHourlyHeartRateData.g = query.getInt(columnIndexOrThrow8);
                entityHourlyHeartRateData.h = query.getLong(columnIndexOrThrow9);
                entityHourlyHeartRateData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow10)));
            } else {
                entityHourlyHeartRateData = null;
            }
            return entityHourlyHeartRateData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public List<EntityHourlyHeartRateData> getHourlyHeartRateData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_heart_rate_table WHERE serial_no=? AND date=?", 2);
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
        Cursor query = this.f6950a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_heart_rate");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_heart_rate");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("rest_heart_rate");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("time_stamp");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("coded_values");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHourlyHeartRateData entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                entityHourlyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyHeartRateData.f6949a = query.getString(columnIndexOrThrow2);
                entityHourlyHeartRateData.b = query.getString(columnIndexOrThrow3);
                entityHourlyHeartRateData.setDate(query.getString(columnIndexOrThrow4));
                entityHourlyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyHeartRateData.f = query.getFloat(columnIndexOrThrow7);
                entityHourlyHeartRateData.g = query.getInt(columnIndexOrThrow8);
                int i2 = columnIndexOrThrow;
                entityHourlyHeartRateData.h = query.getLong(columnIndexOrThrow9);
                entityHourlyHeartRateData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow10)));
                arrayList.add(entityHourlyHeartRateData);
                columnIndexOrThrow = i2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public List<EntityHourlyHeartRateData> getHourlyHeartRateDataFrom(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_heart_rate_table where datetime(date ||' '|| start_time) >= datetime(?)  AND serial_no=?  order by datetime(date ||' '|| start_time) ", 2);
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
        Cursor query = this.f6950a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_heart_rate");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_heart_rate");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("rest_heart_rate");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("time_stamp");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("coded_values");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHourlyHeartRateData entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                entityHourlyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyHeartRateData.f6949a = query.getString(columnIndexOrThrow2);
                entityHourlyHeartRateData.b = query.getString(columnIndexOrThrow3);
                entityHourlyHeartRateData.setDate(query.getString(columnIndexOrThrow4));
                entityHourlyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyHeartRateData.f = query.getFloat(columnIndexOrThrow7);
                entityHourlyHeartRateData.g = query.getInt(columnIndexOrThrow8);
                int i2 = columnIndexOrThrow;
                entityHourlyHeartRateData.h = query.getLong(columnIndexOrThrow9);
                entityHourlyHeartRateData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow10)));
                arrayList.add(entityHourlyHeartRateData);
                columnIndexOrThrow = i2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public LiveData<EntityHourlyHeartRateData> getLatestHourlyHeartRateData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(" SELECT * FROM hourly_heart_rate_table where min_heart_rate >0  AND serial_no=? ORDER BY date DESC , start_time DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new i(this.f6950a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public EntityHourlyHeartRateData getLatestRecordHourly(String str) {
        EntityHourlyHeartRateData entityHourlyHeartRateData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_heart_rate_table where min_heart_rate>0 AND max_heart_rate>0 AND serial_no=?  ORDER BY date DESC,  start_time DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6950a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_heart_rate");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_heart_rate");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("rest_heart_rate");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("time_stamp");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("coded_values");
            if (query.moveToFirst()) {
                entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                entityHourlyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyHeartRateData.f6949a = query.getString(columnIndexOrThrow2);
                entityHourlyHeartRateData.b = query.getString(columnIndexOrThrow3);
                entityHourlyHeartRateData.setDate(query.getString(columnIndexOrThrow4));
                entityHourlyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyHeartRateData.f = query.getFloat(columnIndexOrThrow7);
                entityHourlyHeartRateData.g = query.getInt(columnIndexOrThrow8);
                entityHourlyHeartRateData.h = query.getLong(columnIndexOrThrow9);
                entityHourlyHeartRateData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow10)));
            } else {
                entityHourlyHeartRateData = null;
            }
            return entityHourlyHeartRateData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public LiveData<EntityDailyHeartRateData> getLiveDailyHeartRateData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_heart_rate_table WHERE date=? AND serial_no=?", 2);
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
        return new e(this.f6950a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public LiveData<List<EntityDailyHeartRateData>> getLiveDayWiseHeartRateDataByMacAddress(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_heart_rate_table WHERE serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new f(this.f6950a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public LiveData<List<EntityHourlyHeartRateData>> getLiveHourlyHeartRateData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_heart_rate_table WHERE date=? AND serial_no=?", 2);
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
        return new d(this.f6950a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public LiveData<List<MonthlyHeartRateData>> getLiveMonthWiseHeartRateData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX(max_heart_rate) as max_heart_rate, MIN((case when min_heart_rate>0 then min_heart_rate END)) as min_heart_rate, AVG(avg_heart_rate) as avg_heart_rate, strftime('%m',date) as month,strftime('%Y',date) as year from daily_heart_rate_table WHERE serial_no=?  group by month,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new h(this.f6950a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public LiveData<List<WeeklyHeartRateData>> getLiveWeekWiseHeartRateData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX(max_heart_rate) as maxHeartRate, MIN((case when min_heart_rate>0 then min_heart_rate END)) as minHeartRate, AVG(avg_heart_rate) as avg_heart_rate, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_heart_rate_table  WHERE serial_no=?  group by week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new g(this.f6950a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public MinMaxData getMinMaxHR(String str, String str2, String str3, String str4, String str5) {
        MinMaxData minMaxData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select max(max_heart_rate) as maxValue, min(min_heart_rate )  as minValue from (select min_heart_rate,max_heart_rate, (date || ' ' || start_time) as datestarttime ,(date|| ' ' || end_time) as dateendtime    from hourly_heart_rate_table where date>=? and date<=? and min_heart_rate >0  and max_heart_rate > 0 and serial_no=?)    where datestarttime >=? and dateendtime <=?", 5);
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
        Cursor query = this.f6950a.query(acquire);
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

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_heart_rate_table where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6950a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public List<EntityDailyHeartRateData> getTotalUnSyncedData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From daily_heart_rate_table WHERE serial_no=? AND is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6950a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("min_heart_rate");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("max_heart_rate");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("avg_heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("rest_heart_rate");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("column_1");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("column_2");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("column_3");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityDailyHeartRateData entityDailyHeartRateData = new EntityDailyHeartRateData();
                entityDailyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                entityDailyHeartRateData.setDate(query.getString(columnIndexOrThrow2));
                entityDailyHeartRateData.b = query.getInt(columnIndexOrThrow3);
                entityDailyHeartRateData.c = query.getInt(columnIndexOrThrow4);
                entityDailyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                entityDailyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                entityDailyHeartRateData.setColumn_1(query.getFloat(columnIndexOrThrow7));
                entityDailyHeartRateData.setColumn_2(query.getFloat(columnIndexOrThrow8));
                entityDailyHeartRateData.setColumn_3(query.getString(columnIndexOrThrow9));
                entityDailyHeartRateData.is_sync_server = query.getInt(columnIndexOrThrow10) != 0;
                arrayList.add(entityDailyHeartRateData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public long insert(EntityHourlyHeartRateData entityHourlyHeartRateData) {
        this.f6950a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityHourlyHeartRateData);
            this.f6950a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f6950a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public void insertAll(List<EntityHourlyHeartRateData> list) {
        this.f6950a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6950a.setTransactionSuccessful();
        } finally {
            this.f6950a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public void insertAllDailyHeartRate(List<EntityDailyHeartRateData> list) {
        this.f6950a.beginTransaction();
        try {
            this.c.insert((Iterable) list);
            this.f6950a.setTransactionSuccessful();
        } finally {
            this.f6950a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public void insertDailyHeartRate(EntityDailyHeartRateData entityDailyHeartRateData) {
        this.f6950a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter) entityDailyHeartRateData);
            this.f6950a.setTransactionSuccessful();
        } finally {
            this.f6950a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public int upateHourlyHeartRateData(EntityHourlyHeartRateData entityHourlyHeartRateData) {
        this.f6950a.beginTransaction();
        try {
            int handle = this.d.handle(entityHourlyHeartRateData) + 0;
            this.f6950a.setTransactionSuccessful();
            return handle;
        } finally {
            this.f6950a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_heart_rate_table where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6950a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.heartrate.HeartRateDao
    public EntityHourlyHeartRateData getLatestRecordHourly(String str, String str2) {
        EntityHourlyHeartRateData entityHourlyHeartRateData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_heart_rate_table where min_heart_rate>0 AND max_heart_rate>0 AND serial_no=? AND date=?  ORDER BY   start_time DESC LIMIT 1", 2);
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
        Cursor query = this.f6950a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_heart_rate");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_heart_rate");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_heart_rate");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("rest_heart_rate");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("time_stamp");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("coded_values");
            if (query.moveToFirst()) {
                entityHourlyHeartRateData = new EntityHourlyHeartRateData();
                entityHourlyHeartRateData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyHeartRateData.f6949a = query.getString(columnIndexOrThrow2);
                entityHourlyHeartRateData.b = query.getString(columnIndexOrThrow3);
                entityHourlyHeartRateData.setDate(query.getString(columnIndexOrThrow4));
                entityHourlyHeartRateData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyHeartRateData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyHeartRateData.f = query.getFloat(columnIndexOrThrow7);
                entityHourlyHeartRateData.g = query.getInt(columnIndexOrThrow8);
                entityHourlyHeartRateData.h = query.getLong(columnIndexOrThrow9);
                entityHourlyHeartRateData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow10)));
            } else {
                entityHourlyHeartRateData = null;
            }
            return entityHourlyHeartRateData;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
