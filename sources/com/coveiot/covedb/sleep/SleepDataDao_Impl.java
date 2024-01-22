package com.coveiot.covedb.sleep;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.Convertors;
import com.coveiot.covedb.sleep.model.SleepDataModelForLastNight;
import com.coveiot.covedb.sleep.model.SleepDataModelMonthWiseCommon;
import com.coveiot.covedb.sleep.model.SleepDataModelWeekWiseCommon;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class SleepDataDao_Impl implements SleepDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6973a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;

    /* loaded from: classes8.dex */
    public class a extends ComputableLiveData<DailySleepDataAlias> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* renamed from: com.coveiot.covedb.sleep.SleepDataDao_Impl$a$a  reason: collision with other inner class name */
        /* loaded from: classes8.dex */
        public class C0330a extends InvalidationTracker.Observer {
            public C0330a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                a.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public DailySleepDataAlias compute() {
            DailySleepDataAlias dailySleepDataAlias;
            if (this.g == null) {
                this.g = new C0330a("HourlySleepData", "dailysleepdata");
                SleepDataDao_Impl.this.f6973a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SleepDataDao_Impl.this.f6973a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("_id");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("deep_sleep");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("light_sleep");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("awake");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("value");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("lastNightSleep");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("lastNightDeepSleep");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("lastNightLightSleep");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("lastNightawake");
                if (query.moveToFirst()) {
                    dailySleepDataAlias = new DailySleepDataAlias();
                    dailySleepDataAlias.date = query.getString(columnIndexOrThrow);
                    dailySleepDataAlias._id = query.getString(columnIndexOrThrow2);
                    dailySleepDataAlias.deep_sleep = query.getInt(columnIndexOrThrow3);
                    dailySleepDataAlias.light_sleep = query.getInt(columnIndexOrThrow4);
                    dailySleepDataAlias.awake = query.getInt(columnIndexOrThrow5);
                    dailySleepDataAlias.target = query.getInt(columnIndexOrThrow6);
                    dailySleepDataAlias.value = query.getInt(columnIndexOrThrow7);
                    dailySleepDataAlias.lastNightSleep = query.getInt(columnIndexOrThrow8);
                    dailySleepDataAlias.lastNightDeepSleep = query.getInt(columnIndexOrThrow9);
                    dailySleepDataAlias.lastNightLightSleep = query.getInt(columnIndexOrThrow10);
                    dailySleepDataAlias.lastNightawake = query.getInt(columnIndexOrThrow11);
                } else {
                    dailySleepDataAlias = null;
                }
                return dailySleepDataAlias;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class b extends ComputableLiveData<List<DailySleepData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                b.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<DailySleepData> compute() {
            int i;
            boolean z;
            if (this.g == null) {
                this.g = new a("dailysleepdata", new String[0]);
                SleepDataDao_Impl.this.f6973a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SleepDataDao_Impl.this.f6973a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("_id");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("deep_sleep");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("light_sleep");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("awake");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("sleepScore");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("maxHr");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("minHr");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("maxStress");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("minStress");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow("minAmbientSound");
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("maxAmbientSound");
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("breathQuality");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("isMinMaxUpdated");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("value");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("is_sync_server");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailySleepData dailySleepData = new DailySleepData();
                    ArrayList arrayList2 = arrayList;
                    dailySleepData.setDate(query.getString(columnIndexOrThrow));
                    dailySleepData.mac_address = query.getString(columnIndexOrThrow2);
                    dailySleepData.set_id(query.getString(columnIndexOrThrow3));
                    dailySleepData.setDeepSleep(query.getInt(columnIndexOrThrow4));
                    dailySleepData.setLightSleep(query.getInt(columnIndexOrThrow5));
                    dailySleepData.setAwakeTime(query.getInt(columnIndexOrThrow6));
                    dailySleepData.setSleepTarget(query.getInt(columnIndexOrThrow7));
                    dailySleepData.setSleepScore(query.getInt(columnIndexOrThrow8));
                    dailySleepData.setMaxHr(query.getInt(columnIndexOrThrow9));
                    dailySleepData.setMinHr(query.getInt(columnIndexOrThrow10));
                    dailySleepData.setMaxStress(query.getInt(columnIndexOrThrow11));
                    dailySleepData.setMinStress(query.getInt(columnIndexOrThrow12));
                    dailySleepData.setMinAmbientSound(query.getInt(columnIndexOrThrow13));
                    int i3 = i2;
                    int i4 = columnIndexOrThrow;
                    dailySleepData.setMaxAmbientSound(query.getInt(i3));
                    int i5 = columnIndexOrThrow15;
                    dailySleepData.setBreathQuality(query.getInt(i5));
                    int i6 = columnIndexOrThrow16;
                    dailySleepData.setIsMinMaxUpdated(query.getInt(i6));
                    int i7 = columnIndexOrThrow17;
                    dailySleepData.setValue(query.getInt(i7));
                    int i8 = columnIndexOrThrow18;
                    if (query.getInt(i8) != 0) {
                        i = i7;
                        z = true;
                    } else {
                        i = i7;
                        z = false;
                    }
                    dailySleepData.p = z;
                    arrayList2.add(dailySleepData);
                    arrayList = arrayList2;
                    columnIndexOrThrow = i4;
                    i2 = i3;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow16 = i6;
                    columnIndexOrThrow17 = i;
                    columnIndexOrThrow18 = i8;
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
    public class c extends ComputableLiveData<List<HourlySleepData>> {
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
        public List<HourlySleepData> compute() {
            if (this.g == null) {
                this.g = new a("HourlySleepData", new String[0]);
                SleepDataDao_Impl.this.f6973a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SleepDataDao_Impl.this.f6973a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("codevalue");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("ligth_sleep");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("deep_sleep");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("awake");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("intervalValue");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    HourlySleepData hourlySleepData = new HourlySleepData();
                    hourlySleepData.setId(query.getString(columnIndexOrThrow));
                    hourlySleepData.setDate(query.getString(columnIndexOrThrow2));
                    hourlySleepData.setStartTime(query.getString(columnIndexOrThrow3));
                    hourlySleepData.setEndTime(query.getString(columnIndexOrThrow4));
                    hourlySleepData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow5)));
                    hourlySleepData.mac_address = query.getString(columnIndexOrThrow6);
                    hourlySleepData.setLigthSleep(query.getInt(columnIndexOrThrow7));
                    hourlySleepData.setDeepSleep(query.getInt(columnIndexOrThrow8));
                    hourlySleepData.setAwake(query.getInt(columnIndexOrThrow9));
                    hourlySleepData.setIntervalValue(query.getInt(columnIndexOrThrow10));
                    arrayList.add(hourlySleepData);
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
    public class d extends ComputableLiveData<List<DailySleepData>> {
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
        public List<DailySleepData> compute() {
            int i;
            boolean z;
            if (this.g == null) {
                this.g = new a("dailysleepdata", new String[0]);
                SleepDataDao_Impl.this.f6973a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SleepDataDao_Impl.this.f6973a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("_id");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("deep_sleep");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("light_sleep");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("awake");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("sleepScore");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("maxHr");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("minHr");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("maxStress");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("minStress");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow("minAmbientSound");
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("maxAmbientSound");
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("breathQuality");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("isMinMaxUpdated");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("value");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("is_sync_server");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailySleepData dailySleepData = new DailySleepData();
                    ArrayList arrayList2 = arrayList;
                    dailySleepData.setDate(query.getString(columnIndexOrThrow));
                    dailySleepData.mac_address = query.getString(columnIndexOrThrow2);
                    dailySleepData.set_id(query.getString(columnIndexOrThrow3));
                    dailySleepData.setDeepSleep(query.getInt(columnIndexOrThrow4));
                    dailySleepData.setLightSleep(query.getInt(columnIndexOrThrow5));
                    dailySleepData.setAwakeTime(query.getInt(columnIndexOrThrow6));
                    dailySleepData.setSleepTarget(query.getInt(columnIndexOrThrow7));
                    dailySleepData.setSleepScore(query.getInt(columnIndexOrThrow8));
                    dailySleepData.setMaxHr(query.getInt(columnIndexOrThrow9));
                    dailySleepData.setMinHr(query.getInt(columnIndexOrThrow10));
                    dailySleepData.setMaxStress(query.getInt(columnIndexOrThrow11));
                    dailySleepData.setMinStress(query.getInt(columnIndexOrThrow12));
                    dailySleepData.setMinAmbientSound(query.getInt(columnIndexOrThrow13));
                    int i3 = i2;
                    int i4 = columnIndexOrThrow;
                    dailySleepData.setMaxAmbientSound(query.getInt(i3));
                    int i5 = columnIndexOrThrow15;
                    dailySleepData.setBreathQuality(query.getInt(i5));
                    int i6 = columnIndexOrThrow16;
                    dailySleepData.setIsMinMaxUpdated(query.getInt(i6));
                    int i7 = columnIndexOrThrow17;
                    dailySleepData.setValue(query.getInt(i7));
                    int i8 = columnIndexOrThrow18;
                    if (query.getInt(i8) != 0) {
                        i = i7;
                        z = true;
                    } else {
                        i = i7;
                        z = false;
                    }
                    dailySleepData.p = z;
                    arrayList2.add(dailySleepData);
                    arrayList = arrayList2;
                    columnIndexOrThrow = i4;
                    i2 = i3;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow16 = i6;
                    columnIndexOrThrow17 = i;
                    columnIndexOrThrow18 = i8;
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
    public class e extends EntityInsertionAdapter<HourlySleepData> {
        public e(SleepDataDao_Impl sleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, HourlySleepData hourlySleepData) {
            if (hourlySleepData.getId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, hourlySleepData.getId());
            }
            if (hourlySleepData.getDate() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, hourlySleepData.getDate());
            }
            if (hourlySleepData.getStartTime() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, hourlySleepData.getStartTime());
            }
            if (hourlySleepData.getEndTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, hourlySleepData.getEndTime());
            }
            String frommArrayLisr = Convertors.frommArrayLisr(hourlySleepData.d);
            if (frommArrayLisr == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, frommArrayLisr);
            }
            String str = hourlySleepData.mac_address;
            if (str == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str);
            }
            supportSQLiteStatement.bindLong(7, hourlySleepData.getLigthSleep());
            supportSQLiteStatement.bindLong(8, hourlySleepData.getDeepSleep());
            supportSQLiteStatement.bindLong(9, hourlySleepData.getAwake());
            supportSQLiteStatement.bindLong(10, hourlySleepData.getIntervalValue());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourlysleepdata`(`id`,`date`,`start_time`,`end_time`,`codevalue`,`serial_no`,`ligth_sleep`,`deep_sleep`,`awake`,`intervalValue`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class f extends EntityInsertionAdapter<DailySleepData> {
        public f(SleepDataDao_Impl sleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, DailySleepData dailySleepData) {
            if (dailySleepData.getDate() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, dailySleepData.getDate());
            }
            String str = dailySleepData.mac_address;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            if (dailySleepData.get_id() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, dailySleepData.get_id());
            }
            supportSQLiteStatement.bindLong(4, dailySleepData.getDeepSleep());
            supportSQLiteStatement.bindLong(5, dailySleepData.getLightSleep());
            supportSQLiteStatement.bindLong(6, dailySleepData.getAwakeTime());
            supportSQLiteStatement.bindLong(7, dailySleepData.getSleepTarget());
            supportSQLiteStatement.bindLong(8, dailySleepData.getSleepScore());
            supportSQLiteStatement.bindLong(9, dailySleepData.getMaxHr());
            supportSQLiteStatement.bindLong(10, dailySleepData.getMinHr());
            supportSQLiteStatement.bindLong(11, dailySleepData.getMaxStress());
            supportSQLiteStatement.bindLong(12, dailySleepData.getMinStress());
            supportSQLiteStatement.bindLong(13, dailySleepData.getMinAmbientSound());
            supportSQLiteStatement.bindLong(14, dailySleepData.getMaxAmbientSound());
            supportSQLiteStatement.bindLong(15, dailySleepData.getBreathQuality());
            supportSQLiteStatement.bindLong(16, dailySleepData.getIsMinMaxUpdated());
            supportSQLiteStatement.bindLong(17, dailySleepData.getValue());
            supportSQLiteStatement.bindLong(18, dailySleepData.p ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `dailysleepdata`(`date`,`serial_no`,`_id`,`deep_sleep`,`light_sleep`,`awake`,`target`,`sleepScore`,`maxHr`,`minHr`,`maxStress`,`minStress`,`minAmbientSound`,`maxAmbientSound`,`breathQuality`,`isMinMaxUpdated`,`value`,`is_sync_server`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class g extends SharedSQLiteStatement {
        public g(SleepDataDao_Impl sleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE dailysleepdata SET  minHr=? , maxHr=? , minStress=? , maxStress=?,isMinMaxUpdated=? WHERE serial_no=? and date=?";
        }
    }

    /* loaded from: classes8.dex */
    public class h extends SharedSQLiteStatement {
        public h(SleepDataDao_Impl sleepDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE dailysleepdata SET   minHr=? , maxHr=? , minStress=? , maxStress=?, minAmbientSound=? , maxAmbientSound=?,isMinMaxUpdated=? WHERE serial_no=? and date=?";
        }
    }

    /* loaded from: classes8.dex */
    public class i extends ComputableLiveData<DailySleepData> {
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
        public DailySleepData compute() {
            DailySleepData dailySleepData;
            if (this.g == null) {
                this.g = new a("dailysleepdata", new String[0]);
                SleepDataDao_Impl.this.f6973a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SleepDataDao_Impl.this.f6973a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("_id");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("deep_sleep");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("light_sleep");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("awake");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("sleepScore");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("maxHr");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("minHr");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("maxStress");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("minStress");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow("minAmbientSound");
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow("maxAmbientSound");
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("breathQuality");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("isMinMaxUpdated");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("value");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("is_sync_server");
                if (query.moveToFirst()) {
                    dailySleepData = new DailySleepData();
                    dailySleepData.setDate(query.getString(columnIndexOrThrow));
                    dailySleepData.mac_address = query.getString(columnIndexOrThrow2);
                    dailySleepData.set_id(query.getString(columnIndexOrThrow3));
                    dailySleepData.setDeepSleep(query.getInt(columnIndexOrThrow4));
                    dailySleepData.setLightSleep(query.getInt(columnIndexOrThrow5));
                    dailySleepData.setAwakeTime(query.getInt(columnIndexOrThrow6));
                    dailySleepData.setSleepTarget(query.getInt(columnIndexOrThrow7));
                    dailySleepData.setSleepScore(query.getInt(columnIndexOrThrow8));
                    dailySleepData.setMaxHr(query.getInt(columnIndexOrThrow9));
                    dailySleepData.setMinHr(query.getInt(columnIndexOrThrow10));
                    dailySleepData.setMaxStress(query.getInt(columnIndexOrThrow11));
                    dailySleepData.setMinStress(query.getInt(columnIndexOrThrow12));
                    dailySleepData.setMinAmbientSound(query.getInt(columnIndexOrThrow13));
                    dailySleepData.setMaxAmbientSound(query.getInt(columnIndexOrThrow14));
                    dailySleepData.setBreathQuality(query.getInt(columnIndexOrThrow15));
                    dailySleepData.setIsMinMaxUpdated(query.getInt(columnIndexOrThrow16));
                    dailySleepData.setValue(query.getInt(columnIndexOrThrow17));
                    dailySleepData.p = query.getInt(columnIndexOrThrow18) != 0;
                } else {
                    dailySleepData = null;
                }
                return dailySleepData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class j extends ComputableLiveData<List<DailySleepDataAlias>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                j.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<DailySleepDataAlias> compute() {
            if (this.g == null) {
                this.g = new a("HourlySleepData", "dailysleepdata");
                SleepDataDao_Impl.this.f6973a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SleepDataDao_Impl.this.f6973a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("_id");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("deep_sleep");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("light_sleep");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("awake");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("value");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("lastNightSleep");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("lastNightDeepSleep");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("lastNightLightSleep");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("lastNightawake");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailySleepDataAlias dailySleepDataAlias = new DailySleepDataAlias();
                    dailySleepDataAlias.date = query.getString(columnIndexOrThrow);
                    dailySleepDataAlias._id = query.getString(columnIndexOrThrow2);
                    dailySleepDataAlias.deep_sleep = query.getInt(columnIndexOrThrow3);
                    dailySleepDataAlias.light_sleep = query.getInt(columnIndexOrThrow4);
                    dailySleepDataAlias.awake = query.getInt(columnIndexOrThrow5);
                    dailySleepDataAlias.target = query.getInt(columnIndexOrThrow6);
                    dailySleepDataAlias.value = query.getInt(columnIndexOrThrow7);
                    dailySleepDataAlias.lastNightSleep = query.getInt(columnIndexOrThrow8);
                    dailySleepDataAlias.lastNightDeepSleep = query.getInt(columnIndexOrThrow9);
                    dailySleepDataAlias.lastNightLightSleep = query.getInt(columnIndexOrThrow10);
                    dailySleepDataAlias.lastNightawake = query.getInt(columnIndexOrThrow11);
                    arrayList.add(dailySleepDataAlias);
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
    public class k extends ComputableLiveData<List<SleepDataModelMonthWiseCommon>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                k.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public k(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<SleepDataModelMonthWiseCommon> compute() {
            if (this.g == null) {
                this.g = new a("dailysleepdata", new String[0]);
                SleepDataDao_Impl.this.f6973a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SleepDataDao_Impl.this.f6973a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("month");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("totalDeepSleep");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("totalLightSleep");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("awake");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("rowCount");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    SleepDataModelMonthWiseCommon sleepDataModelMonthWiseCommon = new SleepDataModelMonthWiseCommon();
                    sleepDataModelMonthWiseCommon.setMonth(query.getString(columnIndexOrThrow));
                    sleepDataModelMonthWiseCommon.setTotalDeepSleep(query.getInt(columnIndexOrThrow2));
                    sleepDataModelMonthWiseCommon.setTotalLightSleep(query.getInt(columnIndexOrThrow3));
                    sleepDataModelMonthWiseCommon.setAwake(query.getInt(columnIndexOrThrow4));
                    sleepDataModelMonthWiseCommon.setRowCount(query.getInt(columnIndexOrThrow5));
                    arrayList.add(sleepDataModelMonthWiseCommon);
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
    public class l extends ComputableLiveData<List<SleepDataModelWeekWiseCommon>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                l.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public l(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<SleepDataModelWeekWiseCommon> compute() {
            if (this.g == null) {
                this.g = new a("dailysleepdata", new String[0]);
                SleepDataDao_Impl.this.f6973a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SleepDataDao_Impl.this.f6973a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("week");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("totalDeepSleep");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("totalLightSleep");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("awake");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("rowCount");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    SleepDataModelWeekWiseCommon sleepDataModelWeekWiseCommon = new SleepDataModelWeekWiseCommon();
                    sleepDataModelWeekWiseCommon.setWeek(query.getString(columnIndexOrThrow));
                    sleepDataModelWeekWiseCommon.setTotalDeepSleep(query.getInt(columnIndexOrThrow2));
                    sleepDataModelWeekWiseCommon.setTotalLightSleep(query.getInt(columnIndexOrThrow3));
                    sleepDataModelWeekWiseCommon.setAwake(query.getInt(columnIndexOrThrow4));
                    sleepDataModelWeekWiseCommon.setRowCount(query.getInt(columnIndexOrThrow5));
                    arrayList.add(sleepDataModelWeekWiseCommon);
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
    public class m extends ComputableLiveData<List<SleepDataModelForLastNight>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                m.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public m(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<SleepDataModelForLastNight> compute() {
            if (this.g == null) {
                this.g = new a("HourlySleepData", new String[0]);
                SleepDataDao_Impl.this.f6973a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = SleepDataDao_Impl.this.f6973a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("codevalue");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("ligth_sleep");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("deep_sleep");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("awake");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("intervalValue");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("today_date");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    SleepDataModelForLastNight sleepDataModelForLastNight = new SleepDataModelForLastNight();
                    sleepDataModelForLastNight.setId(query.getString(columnIndexOrThrow));
                    sleepDataModelForLastNight.setDate(query.getString(columnIndexOrThrow2));
                    sleepDataModelForLastNight.setStartTime(query.getString(columnIndexOrThrow3));
                    sleepDataModelForLastNight.setEndTime(query.getString(columnIndexOrThrow4));
                    sleepDataModelForLastNight.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow5)));
                    sleepDataModelForLastNight.setLigthSleep(query.getInt(columnIndexOrThrow6));
                    sleepDataModelForLastNight.setDeepSleep(query.getInt(columnIndexOrThrow7));
                    sleepDataModelForLastNight.setAwake(query.getInt(columnIndexOrThrow8));
                    sleepDataModelForLastNight.setIntervalValue(query.getInt(columnIndexOrThrow9));
                    sleepDataModelForLastNight.setToday_date(query.getString(columnIndexOrThrow10));
                    arrayList.add(sleepDataModelForLastNight);
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

    public SleepDataDao_Impl(RoomDatabase roomDatabase) {
        this.f6973a = roomDatabase;
        this.b = new e(this, roomDatabase);
        this.c = new f(this, roomDatabase);
        this.d = new g(this, roomDatabase);
        this.e = new h(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public int getAwakeInterval(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(awake) FROM HourlySleepData WHERE date=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public List<DailySleepDataAlias> getDailyLastNightSleepData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select c.*, lastNightSleep, lastNightDeepSleep,lastNightLightSleep,lastNightawake from (select date, sum(intervalValue) as lastNightSleep, sum(deep_sleep) as lastNightDeepSleep,sum(ligth_sleep) as lastNightLightSleep,sum(awake) as lastNightawake from (select intervalValue,deep_sleep,ligth_sleep,awake, case when start_time >= time('12:00:00') then date(date,'+1 day') else date end as date from HourlySleepData WHERE serial_no=?) a group by date) b, dailysleepdata c where b.date = c.date AND c.serial_no=?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("deep_sleep");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("light_sleep");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("awake");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("value");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("lastNightSleep");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("lastNightDeepSleep");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("lastNightLightSleep");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("lastNightawake");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailySleepDataAlias dailySleepDataAlias = new DailySleepDataAlias();
                dailySleepDataAlias.date = query.getString(columnIndexOrThrow);
                dailySleepDataAlias._id = query.getString(columnIndexOrThrow2);
                dailySleepDataAlias.deep_sleep = query.getInt(columnIndexOrThrow3);
                dailySleepDataAlias.light_sleep = query.getInt(columnIndexOrThrow4);
                dailySleepDataAlias.awake = query.getInt(columnIndexOrThrow5);
                dailySleepDataAlias.target = query.getInt(columnIndexOrThrow6);
                dailySleepDataAlias.value = query.getInt(columnIndexOrThrow7);
                dailySleepDataAlias.lastNightSleep = query.getInt(columnIndexOrThrow8);
                dailySleepDataAlias.lastNightDeepSleep = query.getInt(columnIndexOrThrow9);
                dailySleepDataAlias.lastNightLightSleep = query.getInt(columnIndexOrThrow10);
                dailySleepDataAlias.lastNightawake = query.getInt(columnIndexOrThrow11);
                arrayList.add(dailySleepDataAlias);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public List<DailySleepData> getDailySleepDataBetweenDates(String str, String str2, String str3) {
        RoomSQLiteQuery roomSQLiteQuery;
        int i2;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from dailysleepdata WHERE serial_no=? AND date BETWEEN ? AND ?", 3);
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
        Cursor query = this.f6973a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("deep_sleep");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("light_sleep");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("awake");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("sleepScore");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("maxHr");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("minHr");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("maxStress");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("minStress");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("minAmbientSound");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("maxAmbientSound");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("breathQuality");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("isMinMaxUpdated");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("value");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("is_sync_server");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailySleepData dailySleepData = new DailySleepData();
                    ArrayList arrayList2 = arrayList;
                    dailySleepData.setDate(query.getString(columnIndexOrThrow));
                    dailySleepData.mac_address = query.getString(columnIndexOrThrow2);
                    dailySleepData.set_id(query.getString(columnIndexOrThrow3));
                    dailySleepData.setDeepSleep(query.getInt(columnIndexOrThrow4));
                    dailySleepData.setLightSleep(query.getInt(columnIndexOrThrow5));
                    dailySleepData.setAwakeTime(query.getInt(columnIndexOrThrow6));
                    dailySleepData.setSleepTarget(query.getInt(columnIndexOrThrow7));
                    dailySleepData.setSleepScore(query.getInt(columnIndexOrThrow8));
                    dailySleepData.setMaxHr(query.getInt(columnIndexOrThrow9));
                    dailySleepData.setMinHr(query.getInt(columnIndexOrThrow10));
                    dailySleepData.setMaxStress(query.getInt(columnIndexOrThrow11));
                    dailySleepData.setMinStress(query.getInt(columnIndexOrThrow12));
                    dailySleepData.setMinAmbientSound(query.getInt(columnIndexOrThrow13));
                    int i4 = i3;
                    int i5 = columnIndexOrThrow;
                    dailySleepData.setMaxAmbientSound(query.getInt(i4));
                    int i6 = columnIndexOrThrow15;
                    dailySleepData.setBreathQuality(query.getInt(i6));
                    int i7 = columnIndexOrThrow16;
                    dailySleepData.setIsMinMaxUpdated(query.getInt(i7));
                    int i8 = columnIndexOrThrow17;
                    dailySleepData.setValue(query.getInt(i8));
                    int i9 = columnIndexOrThrow18;
                    if (query.getInt(i9) != 0) {
                        i2 = i8;
                        z = true;
                    } else {
                        i2 = i8;
                        z = false;
                    }
                    dailySleepData.p = z;
                    arrayList2.add(dailySleepData);
                    columnIndexOrThrow18 = i9;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i5;
                    i3 = i4;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow16 = i7;
                    columnIndexOrThrow17 = i2;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public DailySleepData getDailySleepDataWithDate(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQuery;
        DailySleepData dailySleepData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailysleepdata WHERE  date=? AND serial_no=?", 2);
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
        Cursor query = this.f6973a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("deep_sleep");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("light_sleep");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("awake");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("sleepScore");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("maxHr");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("minHr");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("maxStress");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("minStress");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("minAmbientSound");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("maxAmbientSound");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("breathQuality");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("isMinMaxUpdated");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("value");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("is_sync_server");
                if (query.moveToFirst()) {
                    dailySleepData = new DailySleepData();
                    dailySleepData.setDate(query.getString(columnIndexOrThrow));
                    dailySleepData.mac_address = query.getString(columnIndexOrThrow2);
                    dailySleepData.set_id(query.getString(columnIndexOrThrow3));
                    dailySleepData.setDeepSleep(query.getInt(columnIndexOrThrow4));
                    dailySleepData.setLightSleep(query.getInt(columnIndexOrThrow5));
                    dailySleepData.setAwakeTime(query.getInt(columnIndexOrThrow6));
                    dailySleepData.setSleepTarget(query.getInt(columnIndexOrThrow7));
                    dailySleepData.setSleepScore(query.getInt(columnIndexOrThrow8));
                    dailySleepData.setMaxHr(query.getInt(columnIndexOrThrow9));
                    dailySleepData.setMinHr(query.getInt(columnIndexOrThrow10));
                    dailySleepData.setMaxStress(query.getInt(columnIndexOrThrow11));
                    dailySleepData.setMinStress(query.getInt(columnIndexOrThrow12));
                    dailySleepData.setMinAmbientSound(query.getInt(columnIndexOrThrow13));
                    dailySleepData.setMaxAmbientSound(query.getInt(columnIndexOrThrow14));
                    dailySleepData.setBreathQuality(query.getInt(columnIndexOrThrow15));
                    dailySleepData.setIsMinMaxUpdated(query.getInt(columnIndexOrThrow16));
                    dailySleepData.setValue(query.getInt(columnIndexOrThrow17));
                    dailySleepData.p = query.getInt(columnIndexOrThrow18) != 0;
                } else {
                    dailySleepData = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return dailySleepData;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public int getDeepSleepInterval(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(deep_sleep) FROM HourlySleepData WHERE date=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public String getLastDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date FROM dailysleepdata WHERE serial_no=?   ORDER BY date(date)  LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            return query.moveToFirst() ? query.getString(0) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public List<SleepDataModelForLastNight> getLastNightSleepDataHourly(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select id,date,start_time,end_time,codevalue,ligth_sleep,deep_sleep,awake,intervalValue,(?) as 'today_date' from HourlySleepData where serial_no=? AND id between (?) and (?)", 4);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str3 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str3);
        }
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
        Cursor query = this.f6973a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("ligth_sleep");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("deep_sleep");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("awake");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("intervalValue");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("today_date");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                SleepDataModelForLastNight sleepDataModelForLastNight = new SleepDataModelForLastNight();
                sleepDataModelForLastNight.setId(query.getString(columnIndexOrThrow));
                sleepDataModelForLastNight.setDate(query.getString(columnIndexOrThrow2));
                sleepDataModelForLastNight.setStartTime(query.getString(columnIndexOrThrow3));
                sleepDataModelForLastNight.setEndTime(query.getString(columnIndexOrThrow4));
                sleepDataModelForLastNight.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow5)));
                sleepDataModelForLastNight.setLigthSleep(query.getInt(columnIndexOrThrow6));
                sleepDataModelForLastNight.setDeepSleep(query.getInt(columnIndexOrThrow7));
                sleepDataModelForLastNight.setAwake(query.getInt(columnIndexOrThrow8));
                sleepDataModelForLastNight.setIntervalValue(query.getInt(columnIndexOrThrow9));
                sleepDataModelForLastNight.setToday_date(query.getString(columnIndexOrThrow10));
                arrayList.add(sleepDataModelForLastNight);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public HourlySleepData getLatestRecordHourly(String str) {
        HourlySleepData hourlySleepData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM HourlySleepData where ligth_sleep>0 OR deep_sleep>0 AND serial_no=?  ORDER BY id DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("ligth_sleep");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("deep_sleep");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("awake");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("intervalValue");
            if (query.moveToFirst()) {
                hourlySleepData = new HourlySleepData();
                hourlySleepData.setId(query.getString(columnIndexOrThrow));
                hourlySleepData.setDate(query.getString(columnIndexOrThrow2));
                hourlySleepData.setStartTime(query.getString(columnIndexOrThrow3));
                hourlySleepData.setEndTime(query.getString(columnIndexOrThrow4));
                hourlySleepData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow5)));
                hourlySleepData.mac_address = query.getString(columnIndexOrThrow6);
                hourlySleepData.setLigthSleep(query.getInt(columnIndexOrThrow7));
                hourlySleepData.setDeepSleep(query.getInt(columnIndexOrThrow8));
                hourlySleepData.setAwake(query.getInt(columnIndexOrThrow9));
                hourlySleepData.setIntervalValue(query.getInt(columnIndexOrThrow10));
            } else {
                hourlySleepData = null;
            }
            return hourlySleepData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public int getLightSleepInterval(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(ligth_sleep) FROM HourlySleepData WHERE date=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public LiveData<List<DailySleepDataAlias>> getLiveDailyLastNightSleepData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select c.*, lastNightSleep, lastNightDeepSleep,lastNightLightSleep,lastNightawake from (select date, sum(intervalValue) as lastNightSleep, sum(deep_sleep) as lastNightDeepSleep,sum(ligth_sleep) as lastNightLightSleep,sum(awake) as lastNightawake from (select intervalValue,deep_sleep,ligth_sleep,awake, case when start_time >= time('12:00:00') then date(date,'+1 day') else date end as date from HourlySleepData WHERE serial_no=?) a group by date) b, dailysleepdata c where b.date = c.date AND c.serial_no=?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        return new j(this.f6973a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public LiveData<DailySleepDataAlias> getLiveLastNightSleepData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select c.*, lastNightSleep, lastNightDeepSleep,lastNightLightSleep,lastNightawake from (select date, sum(intervalValue) as lastNightSleep, sum(deep_sleep) as lastNightDeepSleep,sum(ligth_sleep) as lastNightLightSleep,sum(awake) as lastNightawake from (select intervalValue,deep_sleep,ligth_sleep,awake, case when start_time >= time('12:00:00') then date(date,'+1 day') else date end as date from HourlySleepData WHERE serial_no=?) a group by date) b, dailysleepdata c where b.date =? AND c.serial_no=?", 3);
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
        if (str2 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str2);
        }
        return new a(this.f6973a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public LiveData<List<SleepDataModelForLastNight>> getLiveLastNightSleepDataHourly(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select id,date,start_time,end_time,codevalue,ligth_sleep,deep_sleep,awake,intervalValue,(?) as 'today_date' from HourlySleepData where serial_no=? AND id between (?) and (?)", 4);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str3 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str3);
        }
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
        return new m(this.f6973a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public LiveData<List<SleepDataModelMonthWiseCommon>> getLiveMonthWiseData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT substr(date, 0,8) as month,sum(deep_sleep) as totalDeepSleep, sum(light_sleep) as totalLightSleep,sum(awake) as awake, count(*) as rowCount from dailysleepdata WHERE  serial_no=? GROUP BY month", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new k(this.f6973a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public LiveData<DailySleepData> getLiveSleepData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailysleepdata WHERE date LIKE?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new i(this.f6973a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public LiveData<List<HourlySleepData>> getLiveTotalMinuteData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM HourlySleepData where date LIKE? AND serial_no=?", 2);
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
        return new c(this.f6973a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public LiveData<List<DailySleepData>> getLiveTotalSleepData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailysleepdata WHERE  serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new b(this.f6973a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public LiveData<List<DailySleepData>> getLiveTotalUnSyncedSleepData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailysleepdata WHERE  serial_no=? and is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new d(this.f6973a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public LiveData<List<SleepDataModelWeekWiseCommon>> getLiveWeekWiseData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT strftime('%W',date)+1 as week,sum(deep_sleep) as totalDeepSleep, sum(light_sleep) as totalLightSleep,sum(awake) as awake, count(*) as rowCount from dailysleepdata WHERE  serial_no=? GROUP BY week", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new l(this.f6973a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM hourlysleepdata where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6973a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public List<HourlySleepData> getSleepDataBetweenDates(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from hourlysleepdata WHERE serial_no=? AND date BETWEEN ? AND ?", 3);
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
        Cursor query = this.f6973a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("ligth_sleep");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("deep_sleep");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("awake");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("intervalValue");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlySleepData hourlySleepData = new HourlySleepData();
                hourlySleepData.setId(query.getString(columnIndexOrThrow));
                hourlySleepData.setDate(query.getString(columnIndexOrThrow2));
                hourlySleepData.setStartTime(query.getString(columnIndexOrThrow3));
                hourlySleepData.setEndTime(query.getString(columnIndexOrThrow4));
                hourlySleepData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow5)));
                hourlySleepData.mac_address = query.getString(columnIndexOrThrow6);
                hourlySleepData.setLigthSleep(query.getInt(columnIndexOrThrow7));
                hourlySleepData.setDeepSleep(query.getInt(columnIndexOrThrow8));
                hourlySleepData.setAwake(query.getInt(columnIndexOrThrow9));
                hourlySleepData.setIntervalValue(query.getInt(columnIndexOrThrow10));
                arrayList.add(hourlySleepData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public int getSleepInterval(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(intervalValue) FROM HourlySleepData WHERE date=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public List<HourlySleepData> getTotalMinuteData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM HourlySleepData where date LIKE? AND serial_no=?", 2);
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
        Cursor query = this.f6973a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("ligth_sleep");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("deep_sleep");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("awake");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("intervalValue");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlySleepData hourlySleepData = new HourlySleepData();
                hourlySleepData.setId(query.getString(columnIndexOrThrow));
                hourlySleepData.setDate(query.getString(columnIndexOrThrow2));
                hourlySleepData.setStartTime(query.getString(columnIndexOrThrow3));
                hourlySleepData.setEndTime(query.getString(columnIndexOrThrow4));
                hourlySleepData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow5)));
                hourlySleepData.mac_address = query.getString(columnIndexOrThrow6);
                hourlySleepData.setLigthSleep(query.getInt(columnIndexOrThrow7));
                hourlySleepData.setDeepSleep(query.getInt(columnIndexOrThrow8));
                hourlySleepData.setAwake(query.getInt(columnIndexOrThrow9));
                hourlySleepData.setIntervalValue(query.getInt(columnIndexOrThrow10));
                arrayList.add(hourlySleepData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public List<DailySleepData> getTotalSleepData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        int i2;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailysleepdata WHERE  serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("_id");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("deep_sleep");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("light_sleep");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("awake");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("sleepScore");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("maxHr");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("minHr");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("maxStress");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("minStress");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("minAmbientSound");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("maxAmbientSound");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("breathQuality");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("isMinMaxUpdated");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("value");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("is_sync_server");
            int i3 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailySleepData dailySleepData = new DailySleepData();
                ArrayList arrayList2 = arrayList;
                dailySleepData.setDate(query.getString(columnIndexOrThrow));
                dailySleepData.mac_address = query.getString(columnIndexOrThrow2);
                dailySleepData.set_id(query.getString(columnIndexOrThrow3));
                dailySleepData.setDeepSleep(query.getInt(columnIndexOrThrow4));
                dailySleepData.setLightSleep(query.getInt(columnIndexOrThrow5));
                dailySleepData.setAwakeTime(query.getInt(columnIndexOrThrow6));
                dailySleepData.setSleepTarget(query.getInt(columnIndexOrThrow7));
                dailySleepData.setSleepScore(query.getInt(columnIndexOrThrow8));
                dailySleepData.setMaxHr(query.getInt(columnIndexOrThrow9));
                dailySleepData.setMinHr(query.getInt(columnIndexOrThrow10));
                dailySleepData.setMaxStress(query.getInt(columnIndexOrThrow11));
                dailySleepData.setMinStress(query.getInt(columnIndexOrThrow12));
                dailySleepData.setMinAmbientSound(query.getInt(columnIndexOrThrow13));
                int i4 = i3;
                int i5 = columnIndexOrThrow;
                dailySleepData.setMaxAmbientSound(query.getInt(i4));
                int i6 = columnIndexOrThrow15;
                dailySleepData.setBreathQuality(query.getInt(i6));
                int i7 = columnIndexOrThrow16;
                dailySleepData.setIsMinMaxUpdated(query.getInt(i7));
                int i8 = columnIndexOrThrow17;
                dailySleepData.setValue(query.getInt(i8));
                int i9 = columnIndexOrThrow18;
                if (query.getInt(i9) != 0) {
                    i2 = i8;
                    z = true;
                } else {
                    i2 = i8;
                    z = false;
                }
                dailySleepData.p = z;
                arrayList2.add(dailySleepData);
                columnIndexOrThrow18 = i9;
                arrayList = arrayList2;
                columnIndexOrThrow = i5;
                i3 = i4;
                columnIndexOrThrow15 = i6;
                columnIndexOrThrow16 = i7;
                columnIndexOrThrow17 = i2;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public List<DailySleepData> getTotalUnSyncedSleepData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        int i2;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailysleepdata WHERE  serial_no=? and is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("_id");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("deep_sleep");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("light_sleep");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("awake");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("sleepScore");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("maxHr");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("minHr");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("maxStress");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("minStress");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("minAmbientSound");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("maxAmbientSound");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("breathQuality");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("isMinMaxUpdated");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("value");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("is_sync_server");
            int i3 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailySleepData dailySleepData = new DailySleepData();
                ArrayList arrayList2 = arrayList;
                dailySleepData.setDate(query.getString(columnIndexOrThrow));
                dailySleepData.mac_address = query.getString(columnIndexOrThrow2);
                dailySleepData.set_id(query.getString(columnIndexOrThrow3));
                dailySleepData.setDeepSleep(query.getInt(columnIndexOrThrow4));
                dailySleepData.setLightSleep(query.getInt(columnIndexOrThrow5));
                dailySleepData.setAwakeTime(query.getInt(columnIndexOrThrow6));
                dailySleepData.setSleepTarget(query.getInt(columnIndexOrThrow7));
                dailySleepData.setSleepScore(query.getInt(columnIndexOrThrow8));
                dailySleepData.setMaxHr(query.getInt(columnIndexOrThrow9));
                dailySleepData.setMinHr(query.getInt(columnIndexOrThrow10));
                dailySleepData.setMaxStress(query.getInt(columnIndexOrThrow11));
                dailySleepData.setMinStress(query.getInt(columnIndexOrThrow12));
                dailySleepData.setMinAmbientSound(query.getInt(columnIndexOrThrow13));
                int i4 = i3;
                int i5 = columnIndexOrThrow;
                dailySleepData.setMaxAmbientSound(query.getInt(i4));
                int i6 = columnIndexOrThrow15;
                dailySleepData.setBreathQuality(query.getInt(i6));
                int i7 = columnIndexOrThrow16;
                dailySleepData.setIsMinMaxUpdated(query.getInt(i7));
                int i8 = columnIndexOrThrow17;
                dailySleepData.setValue(query.getInt(i8));
                int i9 = columnIndexOrThrow18;
                if (query.getInt(i9) != 0) {
                    i2 = i8;
                    z = true;
                } else {
                    i2 = i8;
                    z = false;
                }
                dailySleepData.p = z;
                arrayList2.add(dailySleepData);
                columnIndexOrThrow18 = i9;
                arrayList = arrayList2;
                columnIndexOrThrow = i5;
                i3 = i4;
                columnIndexOrThrow15 = i6;
                columnIndexOrThrow16 = i7;
                columnIndexOrThrow17 = i2;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public List<DailySleepData> getTotalUnsyncedMinMaxHRStressSleepData(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        int i2;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailysleepdata WHERE  serial_no=? and isMinMaxUpdated=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("_id");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("deep_sleep");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("light_sleep");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("awake");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("sleepScore");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow("maxHr");
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("minHr");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("maxStress");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("minStress");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow("minAmbientSound");
            columnIndexOrThrow14 = query.getColumnIndexOrThrow("maxAmbientSound");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow("breathQuality");
            int columnIndexOrThrow16 = query.getColumnIndexOrThrow("isMinMaxUpdated");
            int columnIndexOrThrow17 = query.getColumnIndexOrThrow("value");
            int columnIndexOrThrow18 = query.getColumnIndexOrThrow("is_sync_server");
            int i3 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailySleepData dailySleepData = new DailySleepData();
                ArrayList arrayList2 = arrayList;
                dailySleepData.setDate(query.getString(columnIndexOrThrow));
                dailySleepData.mac_address = query.getString(columnIndexOrThrow2);
                dailySleepData.set_id(query.getString(columnIndexOrThrow3));
                dailySleepData.setDeepSleep(query.getInt(columnIndexOrThrow4));
                dailySleepData.setLightSleep(query.getInt(columnIndexOrThrow5));
                dailySleepData.setAwakeTime(query.getInt(columnIndexOrThrow6));
                dailySleepData.setSleepTarget(query.getInt(columnIndexOrThrow7));
                dailySleepData.setSleepScore(query.getInt(columnIndexOrThrow8));
                dailySleepData.setMaxHr(query.getInt(columnIndexOrThrow9));
                dailySleepData.setMinHr(query.getInt(columnIndexOrThrow10));
                dailySleepData.setMaxStress(query.getInt(columnIndexOrThrow11));
                dailySleepData.setMinStress(query.getInt(columnIndexOrThrow12));
                dailySleepData.setMinAmbientSound(query.getInt(columnIndexOrThrow13));
                int i4 = i3;
                int i5 = columnIndexOrThrow;
                dailySleepData.setMaxAmbientSound(query.getInt(i4));
                int i6 = columnIndexOrThrow15;
                dailySleepData.setBreathQuality(query.getInt(i6));
                int i7 = columnIndexOrThrow16;
                dailySleepData.setIsMinMaxUpdated(query.getInt(i7));
                int i8 = columnIndexOrThrow17;
                dailySleepData.setValue(query.getInt(i8));
                int i9 = columnIndexOrThrow18;
                if (query.getInt(i9) != 0) {
                    i2 = i8;
                    z = true;
                } else {
                    i2 = i8;
                    z = false;
                }
                dailySleepData.p = z;
                arrayList2.add(dailySleepData);
                columnIndexOrThrow18 = i9;
                arrayList = arrayList2;
                columnIndexOrThrow = i5;
                i3 = i4;
                columnIndexOrThrow15 = i6;
                columnIndexOrThrow16 = i7;
                columnIndexOrThrow17 = i2;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public void insert(DailySleepData dailySleepData) {
        this.f6973a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter) dailySleepData);
            this.f6973a.setTransactionSuccessful();
        } finally {
            this.f6973a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public void insertAllSleepData(List<HourlySleepData> list) {
        this.f6973a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6973a.setTransactionSuccessful();
        } finally {
            this.f6973a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public void updateMinMaxAmbientSoundInDailySleepData(Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str, String str2, Integer num7) {
        SupportSQLiteStatement acquire = this.e.acquire();
        this.f6973a.beginTransaction();
        try {
            if (num == null) {
                acquire.bindNull(1);
            } else {
                acquire.bindLong(1, num.intValue());
            }
            if (num2 == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindLong(2, num2.intValue());
            }
            if (num4 == null) {
                acquire.bindNull(3);
            } else {
                acquire.bindLong(3, num4.intValue());
            }
            if (num3 == null) {
                acquire.bindNull(4);
            } else {
                acquire.bindLong(4, num3.intValue());
            }
            if (num5 == null) {
                acquire.bindNull(5);
            } else {
                acquire.bindLong(5, num5.intValue());
            }
            if (num6 == null) {
                acquire.bindNull(6);
            } else {
                acquire.bindLong(6, num6.intValue());
            }
            if (num7 == null) {
                acquire.bindNull(7);
            } else {
                acquire.bindLong(7, num7.intValue());
            }
            if (str == null) {
                acquire.bindNull(8);
            } else {
                acquire.bindString(8, str);
            }
            if (str2 == null) {
                acquire.bindNull(9);
            } else {
                acquire.bindString(9, str2);
            }
            acquire.executeUpdateDelete();
            this.f6973a.setTransactionSuccessful();
        } finally {
            this.f6973a.endTransaction();
            this.e.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public void updateMinMaxHrStressInDailySleepData(Integer num, Integer num2, Integer num3, Integer num4, String str, String str2, Integer num5) {
        SupportSQLiteStatement acquire = this.d.acquire();
        this.f6973a.beginTransaction();
        try {
            if (num == null) {
                acquire.bindNull(1);
            } else {
                acquire.bindLong(1, num.intValue());
            }
            if (num2 == null) {
                acquire.bindNull(2);
            } else {
                acquire.bindLong(2, num2.intValue());
            }
            if (num4 == null) {
                acquire.bindNull(3);
            } else {
                acquire.bindLong(3, num4.intValue());
            }
            if (num3 == null) {
                acquire.bindNull(4);
            } else {
                acquire.bindLong(4, num3.intValue());
            }
            if (num5 == null) {
                acquire.bindNull(5);
            } else {
                acquire.bindLong(5, num5.intValue());
            }
            if (str == null) {
                acquire.bindNull(6);
            } else {
                acquire.bindString(6, str);
            }
            if (str2 == null) {
                acquire.bindNull(7);
            } else {
                acquire.bindString(7, str2);
            }
            acquire.executeUpdateDelete();
            this.f6973a.setTransactionSuccessful();
        } finally {
            this.f6973a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM dailysleepdata where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.sleep.SleepDataDao
    public List<DailySleepData> getTotalSleepData(String str, Integer num) {
        RoomSQLiteQuery roomSQLiteQuery;
        int i2;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailysleepdata WHERE value>0 AND serial_no=? ORDER BY _id DESC LIMIT?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (num == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindLong(2, num.intValue());
        }
        Cursor query = this.f6973a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("deep_sleep");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("light_sleep");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("awake");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow(TypedValues.AttributesType.S_TARGET);
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("sleepScore");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("maxHr");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("minHr");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("maxStress");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("minStress");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow("minAmbientSound");
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow("maxAmbientSound");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow("breathQuality");
                int columnIndexOrThrow16 = query.getColumnIndexOrThrow("isMinMaxUpdated");
                int columnIndexOrThrow17 = query.getColumnIndexOrThrow("value");
                int columnIndexOrThrow18 = query.getColumnIndexOrThrow("is_sync_server");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailySleepData dailySleepData = new DailySleepData();
                    ArrayList arrayList2 = arrayList;
                    dailySleepData.setDate(query.getString(columnIndexOrThrow));
                    dailySleepData.mac_address = query.getString(columnIndexOrThrow2);
                    dailySleepData.set_id(query.getString(columnIndexOrThrow3));
                    dailySleepData.setDeepSleep(query.getInt(columnIndexOrThrow4));
                    dailySleepData.setLightSleep(query.getInt(columnIndexOrThrow5));
                    dailySleepData.setAwakeTime(query.getInt(columnIndexOrThrow6));
                    dailySleepData.setSleepTarget(query.getInt(columnIndexOrThrow7));
                    dailySleepData.setSleepScore(query.getInt(columnIndexOrThrow8));
                    dailySleepData.setMaxHr(query.getInt(columnIndexOrThrow9));
                    dailySleepData.setMinHr(query.getInt(columnIndexOrThrow10));
                    dailySleepData.setMaxStress(query.getInt(columnIndexOrThrow11));
                    dailySleepData.setMinStress(query.getInt(columnIndexOrThrow12));
                    dailySleepData.setMinAmbientSound(query.getInt(columnIndexOrThrow13));
                    int i4 = i3;
                    int i5 = columnIndexOrThrow;
                    dailySleepData.setMaxAmbientSound(query.getInt(i4));
                    int i6 = columnIndexOrThrow15;
                    dailySleepData.setBreathQuality(query.getInt(i6));
                    int i7 = columnIndexOrThrow16;
                    dailySleepData.setIsMinMaxUpdated(query.getInt(i7));
                    int i8 = columnIndexOrThrow17;
                    dailySleepData.setValue(query.getInt(i8));
                    int i9 = columnIndexOrThrow18;
                    if (query.getInt(i9) != 0) {
                        i2 = i8;
                        z = true;
                    } else {
                        i2 = i8;
                        z = false;
                    }
                    dailySleepData.p = z;
                    arrayList2.add(dailySleepData);
                    columnIndexOrThrow18 = i9;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i5;
                    i3 = i4;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow16 = i7;
                    columnIndexOrThrow17 = i2;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }
}
