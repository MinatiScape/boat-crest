package com.coveiot.covedb.ambientsound;

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
import com.coveiot.covedb.ambientsound.model.MonthlyAmbientSoundData;
import com.coveiot.covedb.ambientsound.model.WeeklyAmbientSoundData;
import com.coveiot.covedb.stress.model.MinMaxData;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class AmbientSoundDao_Impl implements AmbientSoundDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6937a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;
    public final EntityDeletionOrUpdateAdapter d;

    /* loaded from: classes8.dex */
    public class a extends EntityInsertionAdapter<EntityHourlyAmbientSoundData> {
        public a(AmbientSoundDao_Impl ambientSoundDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHourlyAmbientSoundData entityHourlyAmbientSoundData) {
            String str = entityHourlyAmbientSoundData.serial_no;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            if (entityHourlyAmbientSoundData.getDate() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityHourlyAmbientSoundData.getDate());
            }
            if (entityHourlyAmbientSoundData.getStartTime() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, entityHourlyAmbientSoundData.getStartTime());
            }
            if (entityHourlyAmbientSoundData.getEndTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityHourlyAmbientSoundData.getEndTime());
            }
            supportSQLiteStatement.bindLong(5, entityHourlyAmbientSoundData.d);
            supportSQLiteStatement.bindLong(6, entityHourlyAmbientSoundData.e);
            supportSQLiteStatement.bindDouble(7, entityHourlyAmbientSoundData.f);
            String frommArrayLisr = Convertors.frommArrayLisr(entityHourlyAmbientSoundData.getCodedValues());
            if (frommArrayLisr == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, frommArrayLisr);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourly_ambient_sound_table`(`serial_no`,`date`,`start_time`,`end_time`,`min_ambient_sound`,`max_ambient_sound`,`avg_ambient_sound`,`coded_values`) VALUES (?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<EntityDailyAmbientSoundData> {
        public b(AmbientSoundDao_Impl ambientSoundDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityDailyAmbientSoundData entityDailyAmbientSoundData) {
            String str = entityDailyAmbientSoundData.serial_no;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            supportSQLiteStatement.bindLong(2, entityDailyAmbientSoundData.is_sync_server ? 1L : 0L);
            if (entityDailyAmbientSoundData.getDate() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, entityDailyAmbientSoundData.getDate());
            }
            supportSQLiteStatement.bindLong(4, entityDailyAmbientSoundData.b);
            supportSQLiteStatement.bindLong(5, entityDailyAmbientSoundData.c);
            supportSQLiteStatement.bindDouble(6, entityDailyAmbientSoundData.d);
            supportSQLiteStatement.bindLong(7, entityDailyAmbientSoundData.e);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_ambient_sound_table`(`serial_no`,`is_sync_server`,`date`,`min_ambient_sound`,`max_ambient_sound`,`avg_ambient_sound`,`total_time`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends EntityDeletionOrUpdateAdapter<EntityHourlyAmbientSoundData> {
        public c(AmbientSoundDao_Impl ambientSoundDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityHourlyAmbientSoundData entityHourlyAmbientSoundData) {
            String str = entityHourlyAmbientSoundData.serial_no;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            if (entityHourlyAmbientSoundData.getDate() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityHourlyAmbientSoundData.getDate());
            }
            if (entityHourlyAmbientSoundData.getStartTime() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, entityHourlyAmbientSoundData.getStartTime());
            }
            if (entityHourlyAmbientSoundData.getEndTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityHourlyAmbientSoundData.getEndTime());
            }
            supportSQLiteStatement.bindLong(5, entityHourlyAmbientSoundData.d);
            supportSQLiteStatement.bindLong(6, entityHourlyAmbientSoundData.e);
            supportSQLiteStatement.bindDouble(7, entityHourlyAmbientSoundData.f);
            String frommArrayLisr = Convertors.frommArrayLisr(entityHourlyAmbientSoundData.getCodedValues());
            if (frommArrayLisr == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, frommArrayLisr);
            }
            String str2 = entityHourlyAmbientSoundData.serial_no;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, str2);
            }
            if (entityHourlyAmbientSoundData.getStartTime() == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, entityHourlyAmbientSoundData.getStartTime());
            }
            if (entityHourlyAmbientSoundData.getDate() == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, entityHourlyAmbientSoundData.getDate());
            }
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE OR ABORT `hourly_ambient_sound_table` SET `serial_no` = ?,`date` = ?,`start_time` = ?,`end_time` = ?,`min_ambient_sound` = ?,`max_ambient_sound` = ?,`avg_ambient_sound` = ?,`coded_values` = ? WHERE `serial_no` = ? AND `start_time` = ? AND `date` = ?";
        }
    }

    /* loaded from: classes8.dex */
    public class d extends ComputableLiveData<List<EntityHourlyAmbientSoundData>> {
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
        public List<EntityHourlyAmbientSoundData> compute() {
            if (this.g == null) {
                this.g = new a("hourly_ambient_sound_table", new String[0]);
                AmbientSoundDao_Impl.this.f6937a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = AmbientSoundDao_Impl.this.f6937a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_ambient_sound");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_ambient_sound");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_ambient_sound");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("coded_values");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityHourlyAmbientSoundData entityHourlyAmbientSoundData = new EntityHourlyAmbientSoundData();
                    entityHourlyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                    entityHourlyAmbientSoundData.setDate(query.getString(columnIndexOrThrow2));
                    entityHourlyAmbientSoundData.setStartTime(query.getString(columnIndexOrThrow3));
                    entityHourlyAmbientSoundData.setEndTime(query.getString(columnIndexOrThrow4));
                    entityHourlyAmbientSoundData.d = query.getInt(columnIndexOrThrow5);
                    entityHourlyAmbientSoundData.e = query.getInt(columnIndexOrThrow6);
                    entityHourlyAmbientSoundData.f = query.getDouble(columnIndexOrThrow7);
                    entityHourlyAmbientSoundData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow8)));
                    arrayList.add(entityHourlyAmbientSoundData);
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
    public class e extends ComputableLiveData<EntityDailyAmbientSoundData> {
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
        public EntityDailyAmbientSoundData compute() {
            EntityDailyAmbientSoundData entityDailyAmbientSoundData;
            if (this.g == null) {
                this.g = new a("daily_ambient_sound_table", new String[0]);
                AmbientSoundDao_Impl.this.f6937a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = AmbientSoundDao_Impl.this.f6937a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("is_sync_server");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("min_ambient_sound");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("max_ambient_sound");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("avg_ambient_sound");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_time");
                if (query.moveToFirst()) {
                    entityDailyAmbientSoundData = new EntityDailyAmbientSoundData();
                    entityDailyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                    entityDailyAmbientSoundData.is_sync_server = query.getInt(columnIndexOrThrow2) != 0;
                    entityDailyAmbientSoundData.setDate(query.getString(columnIndexOrThrow3));
                    entityDailyAmbientSoundData.b = query.getInt(columnIndexOrThrow4);
                    entityDailyAmbientSoundData.c = query.getInt(columnIndexOrThrow5);
                    entityDailyAmbientSoundData.d = query.getDouble(columnIndexOrThrow6);
                    entityDailyAmbientSoundData.e = query.getInt(columnIndexOrThrow7);
                } else {
                    entityDailyAmbientSoundData = null;
                }
                return entityDailyAmbientSoundData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class f extends ComputableLiveData<List<EntityDailyAmbientSoundData>> {
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
        public List<EntityDailyAmbientSoundData> compute() {
            if (this.g == null) {
                this.g = new a("daily_ambient_sound_table", new String[0]);
                AmbientSoundDao_Impl.this.f6937a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = AmbientSoundDao_Impl.this.f6937a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("is_sync_server");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("min_ambient_sound");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("max_ambient_sound");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("avg_ambient_sound");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_time");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    EntityDailyAmbientSoundData entityDailyAmbientSoundData = new EntityDailyAmbientSoundData();
                    entityDailyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                    entityDailyAmbientSoundData.is_sync_server = query.getInt(columnIndexOrThrow2) != 0;
                    entityDailyAmbientSoundData.setDate(query.getString(columnIndexOrThrow3));
                    entityDailyAmbientSoundData.b = query.getInt(columnIndexOrThrow4);
                    entityDailyAmbientSoundData.c = query.getInt(columnIndexOrThrow5);
                    entityDailyAmbientSoundData.d = query.getDouble(columnIndexOrThrow6);
                    entityDailyAmbientSoundData.e = query.getInt(columnIndexOrThrow7);
                    arrayList.add(entityDailyAmbientSoundData);
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
    public class g extends ComputableLiveData<List<WeeklyAmbientSoundData>> {
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
        public List<WeeklyAmbientSoundData> compute() {
            if (this.g == null) {
                this.g = new a("daily_ambient_sound_table", new String[0]);
                AmbientSoundDao_Impl.this.f6937a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = AmbientSoundDao_Impl.this.f6937a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("maxAmbientSound");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("minAmbientSound");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("avgAmbientSound");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("week");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    WeeklyAmbientSoundData weeklyAmbientSoundData = new WeeklyAmbientSoundData();
                    weeklyAmbientSoundData.mac_address = query.getString(columnIndexOrThrow);
                    weeklyAmbientSoundData.maxAmbientSound = query.getInt(columnIndexOrThrow2);
                    weeklyAmbientSoundData.minAmbientSound = query.getInt(columnIndexOrThrow3);
                    weeklyAmbientSoundData.avgAmbientSound = query.getInt(columnIndexOrThrow4);
                    weeklyAmbientSoundData.setWeek(query.getString(columnIndexOrThrow5));
                    weeklyAmbientSoundData.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(weeklyAmbientSoundData);
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
    public class h extends ComputableLiveData<List<MonthlyAmbientSoundData>> {
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
        public List<MonthlyAmbientSoundData> compute() {
            if (this.g == null) {
                this.g = new a("daily_ambient_sound_table", new String[0]);
                AmbientSoundDao_Impl.this.f6937a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = AmbientSoundDao_Impl.this.f6937a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mac_address");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("maxAmbientSound");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("minAmbientSound");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("avgAmbientSound");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("month");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MonthlyAmbientSoundData monthlyAmbientSoundData = new MonthlyAmbientSoundData();
                    monthlyAmbientSoundData.mac_address = query.getString(columnIndexOrThrow);
                    monthlyAmbientSoundData.maxAmbientSound = query.getInt(columnIndexOrThrow2);
                    monthlyAmbientSoundData.minAmbientSound = query.getInt(columnIndexOrThrow3);
                    monthlyAmbientSoundData.avgAmbientSound = query.getInt(columnIndexOrThrow4);
                    monthlyAmbientSoundData.setMonth(query.getString(columnIndexOrThrow5));
                    monthlyAmbientSoundData.year = query.getString(columnIndexOrThrow6);
                    arrayList.add(monthlyAmbientSoundData);
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
    public class i extends ComputableLiveData<EntityHourlyAmbientSoundData> {
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
        public EntityHourlyAmbientSoundData compute() {
            EntityHourlyAmbientSoundData entityHourlyAmbientSoundData;
            if (this.g == null) {
                this.g = new a("hourly_ambient_sound_table", new String[0]);
                AmbientSoundDao_Impl.this.f6937a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = AmbientSoundDao_Impl.this.f6937a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_ambient_sound");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_ambient_sound");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_ambient_sound");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("coded_values");
                if (query.moveToFirst()) {
                    entityHourlyAmbientSoundData = new EntityHourlyAmbientSoundData();
                    entityHourlyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                    entityHourlyAmbientSoundData.setDate(query.getString(columnIndexOrThrow2));
                    entityHourlyAmbientSoundData.setStartTime(query.getString(columnIndexOrThrow3));
                    entityHourlyAmbientSoundData.setEndTime(query.getString(columnIndexOrThrow4));
                    entityHourlyAmbientSoundData.d = query.getInt(columnIndexOrThrow5);
                    entityHourlyAmbientSoundData.e = query.getInt(columnIndexOrThrow6);
                    entityHourlyAmbientSoundData.f = query.getDouble(columnIndexOrThrow7);
                    entityHourlyAmbientSoundData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow8)));
                } else {
                    entityHourlyAmbientSoundData = null;
                }
                return entityHourlyAmbientSoundData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    public AmbientSoundDao_Impl(RoomDatabase roomDatabase) {
        this.f6937a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public EntityHourlyAmbientSoundData getAmbientSoundDataWithDateAndTime(String str, String str2, String str3, String str4) {
        EntityHourlyAmbientSoundData entityHourlyAmbientSoundData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_ambient_sound_table WHERE serial_no=? AND date=? and start_time=? AND end_time=?", 4);
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
        Cursor query = this.f6937a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_ambient_sound");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_ambient_sound");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_ambient_sound");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("coded_values");
            if (query.moveToFirst()) {
                entityHourlyAmbientSoundData = new EntityHourlyAmbientSoundData();
                entityHourlyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyAmbientSoundData.setDate(query.getString(columnIndexOrThrow2));
                entityHourlyAmbientSoundData.setStartTime(query.getString(columnIndexOrThrow3));
                entityHourlyAmbientSoundData.setEndTime(query.getString(columnIndexOrThrow4));
                entityHourlyAmbientSoundData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyAmbientSoundData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyAmbientSoundData.f = query.getDouble(columnIndexOrThrow7);
                entityHourlyAmbientSoundData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow8)));
            } else {
                entityHourlyAmbientSoundData = null;
            }
            return entityHourlyAmbientSoundData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public List<EntityHourlyAmbientSoundData> getHourlyAmbientSoundData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_ambient_sound_table WHERE serial_no=? AND date=?", 2);
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
        Cursor query = this.f6937a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_ambient_sound");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_ambient_sound");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_ambient_sound");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("coded_values");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHourlyAmbientSoundData entityHourlyAmbientSoundData = new EntityHourlyAmbientSoundData();
                entityHourlyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyAmbientSoundData.setDate(query.getString(columnIndexOrThrow2));
                entityHourlyAmbientSoundData.setStartTime(query.getString(columnIndexOrThrow3));
                entityHourlyAmbientSoundData.setEndTime(query.getString(columnIndexOrThrow4));
                entityHourlyAmbientSoundData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyAmbientSoundData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyAmbientSoundData.f = query.getDouble(columnIndexOrThrow7);
                entityHourlyAmbientSoundData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow8)));
                arrayList.add(entityHourlyAmbientSoundData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public List<EntityHourlyAmbientSoundData> getHourlyAmbientSoundDataFrom(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from hourly_ambient_sound_table where datetime(date ||' '|| start_time) >= datetime(?)  AND serial_no=?  order by datetime(date ||' '|| start_time) ", 2);
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
        Cursor query = this.f6937a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_ambient_sound");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_ambient_sound");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_ambient_sound");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("coded_values");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityHourlyAmbientSoundData entityHourlyAmbientSoundData = new EntityHourlyAmbientSoundData();
                entityHourlyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyAmbientSoundData.setDate(query.getString(columnIndexOrThrow2));
                entityHourlyAmbientSoundData.setStartTime(query.getString(columnIndexOrThrow3));
                entityHourlyAmbientSoundData.setEndTime(query.getString(columnIndexOrThrow4));
                entityHourlyAmbientSoundData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyAmbientSoundData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyAmbientSoundData.f = query.getDouble(columnIndexOrThrow7);
                entityHourlyAmbientSoundData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow8)));
                arrayList.add(entityHourlyAmbientSoundData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public LiveData<EntityHourlyAmbientSoundData> getLatestHourlyAmbientSoundData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(" SELECT * FROM hourly_ambient_sound_table where min_ambient_sound >0  AND serial_no=? ORDER BY date DESC , start_time DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new i(this.f6937a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public EntityHourlyAmbientSoundData getLatestRecordHourly(String str) {
        EntityHourlyAmbientSoundData entityHourlyAmbientSoundData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_ambient_sound_table where min_ambient_sound>0 AND max_ambient_sound>0 AND serial_no=?  ORDER BY date DESC,  start_time DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6937a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_ambient_sound");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_ambient_sound");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_ambient_sound");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("coded_values");
            if (query.moveToFirst()) {
                entityHourlyAmbientSoundData = new EntityHourlyAmbientSoundData();
                entityHourlyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyAmbientSoundData.setDate(query.getString(columnIndexOrThrow2));
                entityHourlyAmbientSoundData.setStartTime(query.getString(columnIndexOrThrow3));
                entityHourlyAmbientSoundData.setEndTime(query.getString(columnIndexOrThrow4));
                entityHourlyAmbientSoundData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyAmbientSoundData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyAmbientSoundData.f = query.getDouble(columnIndexOrThrow7);
                entityHourlyAmbientSoundData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow8)));
            } else {
                entityHourlyAmbientSoundData = null;
            }
            return entityHourlyAmbientSoundData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public LiveData<EntityDailyAmbientSoundData> getLiveDailyAmbientSoundData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_ambient_sound_table WHERE date=? AND serial_no=?", 2);
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
        return new e(this.f6937a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public LiveData<List<EntityDailyAmbientSoundData>> getLiveDayWiseAmbientSoundDataByMacAddress(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM daily_ambient_sound_table WHERE serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new f(this.f6937a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public LiveData<List<EntityHourlyAmbientSoundData>> getLiveHourlyAmbientSoundData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_ambient_sound_table WHERE date=? AND serial_no=?", 2);
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
        return new d(this.f6937a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public LiveData<List<MonthlyAmbientSoundData>> getLiveMonthWiseAmbientSoundData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX(max_ambient_sound) as maxAmbientSound, MIN((case when min_ambient_sound>0 then min_ambient_sound END)) as minAmbientSound, AVG(avg_ambient_sound) as avgAmbientSound, strftime('%m',date) as month,strftime('%Y',date) as year from daily_ambient_sound_table WHERE serial_no=?  group by month,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new h(this.f6937a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public LiveData<List<WeeklyAmbientSoundData>> getLiveWeekWiseAmbientSoundData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT serial_no as mac_address,MAX(max_ambient_sound) as maxAmbientSound, MIN((case when min_ambient_sound>0 then min_ambient_sound END)) as minAmbientSound, AVG(avg_ambient_sound) as avgAmbientSound, strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_ambient_sound_table  WHERE serial_no=?  group by week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new g(this.f6937a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public MinMaxData getMinMaxAmbientSound(String str, String str2, String str3, String str4, String str5) {
        MinMaxData minMaxData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select max(max_ambient_sound) as maxValue, min(min_ambient_sound)  as minValue from (select min_ambient_sound,max_ambient_sound, (date || ' ' || start_time) as datestarttime ,(date|| ' ' || end_time) as dateendtime    from hourly_ambient_sound_table where date>=? and date<=? and min_ambient_sound >0  and max_ambient_sound > 0 and serial_no=?)    where datestarttime >=? and dateendtime <=?", 5);
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
        Cursor query = this.f6937a.query(acquire);
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

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_ambient_sound_table where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6937a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public List<EntityDailyAmbientSoundData> getTotalUnSyncedData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * From daily_ambient_sound_table WHERE serial_no=? AND is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6937a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("is_sync_server");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("min_ambient_sound");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("max_ambient_sound");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("avg_ambient_sound");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("total_time");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                EntityDailyAmbientSoundData entityDailyAmbientSoundData = new EntityDailyAmbientSoundData();
                entityDailyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                entityDailyAmbientSoundData.is_sync_server = query.getInt(columnIndexOrThrow2) != 0;
                entityDailyAmbientSoundData.setDate(query.getString(columnIndexOrThrow3));
                entityDailyAmbientSoundData.b = query.getInt(columnIndexOrThrow4);
                entityDailyAmbientSoundData.c = query.getInt(columnIndexOrThrow5);
                entityDailyAmbientSoundData.d = query.getDouble(columnIndexOrThrow6);
                entityDailyAmbientSoundData.e = query.getInt(columnIndexOrThrow7);
                arrayList.add(entityDailyAmbientSoundData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public long insert(EntityHourlyAmbientSoundData entityHourlyAmbientSoundData) {
        this.f6937a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(entityHourlyAmbientSoundData);
            this.f6937a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f6937a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public void insertAll(List<EntityHourlyAmbientSoundData> list) {
        this.f6937a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6937a.setTransactionSuccessful();
        } finally {
            this.f6937a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public void insertAllDailyAmbientSound(List<EntityDailyAmbientSoundData> list) {
        this.f6937a.beginTransaction();
        try {
            this.c.insert((Iterable) list);
            this.f6937a.setTransactionSuccessful();
        } finally {
            this.f6937a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public void insertDailyAmbientSound(EntityDailyAmbientSoundData entityDailyAmbientSoundData) {
        this.f6937a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter) entityDailyAmbientSoundData);
            this.f6937a.setTransactionSuccessful();
        } finally {
            this.f6937a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public int updateHourlyAmbientSoundData(EntityHourlyAmbientSoundData entityHourlyAmbientSoundData) {
        this.f6937a.beginTransaction();
        try {
            int handle = this.d.handle(entityHourlyAmbientSoundData) + 0;
            this.f6937a.setTransactionSuccessful();
            return handle;
        } finally {
            this.f6937a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM daily_ambient_sound_table where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6937a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.ambientsound.AmbientSoundDao
    public EntityHourlyAmbientSoundData getLatestRecordHourly(String str, String str2) {
        EntityHourlyAmbientSoundData entityHourlyAmbientSoundData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourly_ambient_sound_table where min_ambient_sound>0 AND max_ambient_sound>0 AND serial_no=? AND date=?  ORDER BY   start_time DESC LIMIT 1", 2);
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
        Cursor query = this.f6937a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("min_ambient_sound");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("max_ambient_sound");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("avg_ambient_sound");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("coded_values");
            if (query.moveToFirst()) {
                entityHourlyAmbientSoundData = new EntityHourlyAmbientSoundData();
                entityHourlyAmbientSoundData.serial_no = query.getString(columnIndexOrThrow);
                entityHourlyAmbientSoundData.setDate(query.getString(columnIndexOrThrow2));
                entityHourlyAmbientSoundData.setStartTime(query.getString(columnIndexOrThrow3));
                entityHourlyAmbientSoundData.setEndTime(query.getString(columnIndexOrThrow4));
                entityHourlyAmbientSoundData.d = query.getInt(columnIndexOrThrow5);
                entityHourlyAmbientSoundData.e = query.getInt(columnIndexOrThrow6);
                entityHourlyAmbientSoundData.f = query.getDouble(columnIndexOrThrow7);
                entityHourlyAmbientSoundData.setCodedValues(Convertors.frommString(query.getString(columnIndexOrThrow8)));
            } else {
                entityHourlyAmbientSoundData = null;
            }
            return entityHourlyAmbientSoundData;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
