package com.coveiot.covedb.walk;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.Convertors;
import com.coveiot.covedb.ConvertorsMediaList;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.covedb.walk.model.PersonalBest;
import com.coveiot.covedb.walk.model.StepsDataModelMonthWiseCommon;
import com.coveiot.covedb.walk.model.StepsDataModelWeekWiseCommon;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class WalkDataDao_Impl implements WalkDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6992a;
    public final EntityInsertionAdapter b;
    public final EntityInsertionAdapter c;
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;
    public final SharedSQLiteStatement f;
    public final SharedSQLiteStatement g;
    public final SharedSQLiteStatement h;
    public final SharedSQLiteStatement i;
    public final SharedSQLiteStatement j;

    /* loaded from: classes8.dex */
    public class a extends ComputableLiveData<DailyWalkData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* renamed from: com.coveiot.covedb.walk.WalkDataDao_Impl$a$a  reason: collision with other inner class name */
        /* loaded from: classes8.dex */
        public class C0331a extends InvalidationTracker.Observer {
            public C0331a(String str, String... strArr) {
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
        public DailyWalkData compute() {
            if (this.g == null) {
                this.g = new C0331a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("pace");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("steps_target");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("active_time");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
                DailyWalkData dailyWalkData = null;
                Integer valueOf = null;
                if (query.moveToFirst()) {
                    DailyWalkData dailyWalkData2 = new DailyWalkData();
                    dailyWalkData2.mDate = query.getString(columnIndexOrThrow);
                    dailyWalkData2.mac_address = query.getString(columnIndexOrThrow2);
                    dailyWalkData2.setValue(query.getInt(columnIndexOrThrow3));
                    dailyWalkData2.setMeters(query.getInt(columnIndexOrThrow4));
                    dailyWalkData2.setCalories(query.getDouble(columnIndexOrThrow5));
                    dailyWalkData2.setPace(query.getDouble(columnIndexOrThrow6));
                    dailyWalkData2.setStepsTarget(query.getInt(columnIndexOrThrow7));
                    if (!query.isNull(columnIndexOrThrow8)) {
                        valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow8));
                    }
                    dailyWalkData2.setActiveTime(valueOf);
                    dailyWalkData2.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                    dailyWalkData = dailyWalkData2;
                }
                return dailyWalkData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class b extends ComputableLiveData<DailyWalkData> {
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
        public DailyWalkData compute() {
            if (this.g == null) {
                this.g = new a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("active_time");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("steps_target");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("pace");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
                DailyWalkData dailyWalkData = null;
                Integer valueOf = null;
                if (query.moveToFirst()) {
                    DailyWalkData dailyWalkData2 = new DailyWalkData();
                    dailyWalkData2.mDate = query.getString(columnIndexOrThrow);
                    if (!query.isNull(columnIndexOrThrow2)) {
                        valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow2));
                    }
                    dailyWalkData2.setActiveTime(valueOf);
                    dailyWalkData2.setValue(query.getInt(columnIndexOrThrow3));
                    dailyWalkData2.setStepsTarget(query.getInt(columnIndexOrThrow4));
                    dailyWalkData2.setCalories(query.getDouble(columnIndexOrThrow5));
                    dailyWalkData2.setMeters(query.getInt(columnIndexOrThrow6));
                    dailyWalkData2.setPace(query.getDouble(columnIndexOrThrow7));
                    dailyWalkData2.mac_address = query.getString(columnIndexOrThrow8);
                    dailyWalkData2.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                    dailyWalkData = dailyWalkData2;
                }
                return dailyWalkData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class c extends ComputableLiveData<List<HourlyWalkData>> {
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
        public List<HourlyWalkData> compute() {
            if (this.g == null) {
                this.g = new a("HourlyWalkData", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("interval_value");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("codevalue");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("distance_code_value");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("calorie_code_value");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("active_time");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    HourlyWalkData hourlyWalkData = new HourlyWalkData();
                    hourlyWalkData.setId(query.getString(columnIndexOrThrow));
                    hourlyWalkData.mDate = query.getString(columnIndexOrThrow2);
                    hourlyWalkData.setStartTime(query.getString(columnIndexOrThrow3));
                    hourlyWalkData.setEndTime(query.getString(columnIndexOrThrow4));
                    hourlyWalkData.setIntervelValue(query.getInt(columnIndexOrThrow5));
                    hourlyWalkData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                    hourlyWalkData.setDistanceCodeValue(Convertors.frommString(query.getString(columnIndexOrThrow7)));
                    hourlyWalkData.setCalorieCodeValue(ConvertorsMediaList.frommStringToListFloat(query.getString(columnIndexOrThrow8)));
                    hourlyWalkData.mac_address = query.getString(columnIndexOrThrow9);
                    hourlyWalkData.setCalories(query.getInt(columnIndexOrThrow10));
                    hourlyWalkData.setDistance(query.getInt(columnIndexOrThrow11));
                    hourlyWalkData.setActiveTime(query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12)));
                    arrayList.add(hourlyWalkData);
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
    public class d extends ComputableLiveData<List<HourlyWalkData>> {
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
        public List<HourlyWalkData> compute() {
            if (this.g == null) {
                this.g = new a("HourlyWalkData", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("active_time");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("interval_value");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("codevalue");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    HourlyWalkData hourlyWalkData = new HourlyWalkData();
                    hourlyWalkData.mDate = query.getString(columnIndexOrThrow);
                    hourlyWalkData.setStartTime(query.getString(columnIndexOrThrow2));
                    hourlyWalkData.setEndTime(query.getString(columnIndexOrThrow3));
                    hourlyWalkData.mac_address = query.getString(columnIndexOrThrow4);
                    hourlyWalkData.setId(query.getString(columnIndexOrThrow5));
                    hourlyWalkData.setActiveTime(query.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow6)));
                    hourlyWalkData.setIntervelValue(query.getInt(columnIndexOrThrow7));
                    hourlyWalkData.setDistance(query.getInt(columnIndexOrThrow8));
                    hourlyWalkData.setCalories(query.getInt(columnIndexOrThrow9));
                    hourlyWalkData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow10)));
                    arrayList.add(hourlyWalkData);
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
    public class e extends ComputableLiveData<List<DailyWalkData>> {
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
        public List<DailyWalkData> compute() {
            if (this.g == null) {
                this.g = new a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("pace");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("steps_target");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("active_time");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailyWalkData dailyWalkData = new DailyWalkData();
                    dailyWalkData.mDate = query.getString(columnIndexOrThrow);
                    dailyWalkData.mac_address = query.getString(columnIndexOrThrow2);
                    dailyWalkData.setValue(query.getInt(columnIndexOrThrow3));
                    dailyWalkData.setMeters(query.getInt(columnIndexOrThrow4));
                    dailyWalkData.setCalories(query.getDouble(columnIndexOrThrow5));
                    dailyWalkData.setPace(query.getDouble(columnIndexOrThrow6));
                    dailyWalkData.setStepsTarget(query.getInt(columnIndexOrThrow7));
                    dailyWalkData.setActiveTime(query.isNull(columnIndexOrThrow8) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow8)));
                    dailyWalkData.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                    arrayList.add(dailyWalkData);
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
    public class f extends ComputableLiveData<List<DailyWalkData>> {
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
        public List<DailyWalkData> compute() {
            if (this.g == null) {
                this.g = new a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("steps_target");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("active_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("steps");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("pace");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    DailyWalkData dailyWalkData = new DailyWalkData();
                    dailyWalkData.mDate = query.getString(columnIndexOrThrow);
                    dailyWalkData.setStepsTarget(query.getInt(columnIndexOrThrow2));
                    dailyWalkData.mac_address = query.getString(columnIndexOrThrow3);
                    dailyWalkData.setActiveTime(query.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow4)));
                    dailyWalkData.setValue(query.getInt(columnIndexOrThrow5));
                    dailyWalkData.setMeters(query.getInt(columnIndexOrThrow6));
                    dailyWalkData.setCalories(query.getDouble(columnIndexOrThrow7));
                    dailyWalkData.setPace(query.getDouble(columnIndexOrThrow8));
                    dailyWalkData.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                    arrayList.add(dailyWalkData);
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
    public class g extends ComputableLiveData<List<StepsDataModelMonthWiseCommon>> {
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
        public List<StepsDataModelMonthWiseCommon> compute() {
            if (this.g == null) {
                this.g = new a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("stepCount");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("year");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("month");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    StepsDataModelMonthWiseCommon stepsDataModelMonthWiseCommon = new StepsDataModelMonthWiseCommon();
                    stepsDataModelMonthWiseCommon.setStepCount(query.getInt(columnIndexOrThrow));
                    stepsDataModelMonthWiseCommon.distance = query.getInt(columnIndexOrThrow2);
                    stepsDataModelMonthWiseCommon.calories = query.getInt(columnIndexOrThrow3);
                    stepsDataModelMonthWiseCommon.setYear(query.getString(columnIndexOrThrow4));
                    stepsDataModelMonthWiseCommon.setMonth(query.getString(columnIndexOrThrow5));
                    arrayList.add(stepsDataModelMonthWiseCommon);
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
    public class h extends ComputableLiveData<List<StepsDataModelMonthWiseCommon>> {
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
        public List<StepsDataModelMonthWiseCommon> compute() {
            if (this.g == null) {
                this.g = new a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("stepCount");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("year");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("month");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    StepsDataModelMonthWiseCommon stepsDataModelMonthWiseCommon = new StepsDataModelMonthWiseCommon();
                    stepsDataModelMonthWiseCommon.setStepCount(query.getInt(columnIndexOrThrow));
                    stepsDataModelMonthWiseCommon.distance = query.getInt(columnIndexOrThrow2);
                    stepsDataModelMonthWiseCommon.calories = query.getInt(columnIndexOrThrow3);
                    stepsDataModelMonthWiseCommon.setYear(query.getString(columnIndexOrThrow4));
                    stepsDataModelMonthWiseCommon.setMonth(query.getString(columnIndexOrThrow5));
                    arrayList.add(stepsDataModelMonthWiseCommon);
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
    public class i extends ComputableLiveData<List<StepsDataModelWeekWiseCommon>> {
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
        public List<StepsDataModelWeekWiseCommon> compute() {
            if (this.g == null) {
                this.g = new a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("stepCount");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("year");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("week");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    StepsDataModelWeekWiseCommon stepsDataModelWeekWiseCommon = new StepsDataModelWeekWiseCommon();
                    stepsDataModelWeekWiseCommon.setStepCount(query.getInt(columnIndexOrThrow));
                    stepsDataModelWeekWiseCommon.distance = query.getInt(columnIndexOrThrow2);
                    stepsDataModelWeekWiseCommon.calories = query.getInt(columnIndexOrThrow3);
                    stepsDataModelWeekWiseCommon.setYear(query.getString(columnIndexOrThrow4));
                    stepsDataModelWeekWiseCommon.setWeek(query.getString(columnIndexOrThrow5));
                    arrayList.add(stepsDataModelWeekWiseCommon);
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
    public class j extends ComputableLiveData<List<StepsDataModelWeekWiseCommon>> {
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
        public List<StepsDataModelWeekWiseCommon> compute() {
            if (this.g == null) {
                this.g = new a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("stepCount");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("year");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("week");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    StepsDataModelWeekWiseCommon stepsDataModelWeekWiseCommon = new StepsDataModelWeekWiseCommon();
                    stepsDataModelWeekWiseCommon.setStepCount(query.getInt(columnIndexOrThrow));
                    stepsDataModelWeekWiseCommon.distance = query.getInt(columnIndexOrThrow2);
                    stepsDataModelWeekWiseCommon.calories = query.getInt(columnIndexOrThrow3);
                    stepsDataModelWeekWiseCommon.setYear(query.getString(columnIndexOrThrow4));
                    stepsDataModelWeekWiseCommon.setWeek(query.getString(columnIndexOrThrow5));
                    arrayList.add(stepsDataModelWeekWiseCommon);
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
    public class k extends EntityInsertionAdapter<HourlyWalkData> {
        public k(WalkDataDao_Impl walkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, HourlyWalkData hourlyWalkData) {
            if (hourlyWalkData.getId() == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, hourlyWalkData.getId());
            }
            String str = hourlyWalkData.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            if (hourlyWalkData.getStartTime() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, hourlyWalkData.getStartTime());
            }
            if (hourlyWalkData.getEndTime() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, hourlyWalkData.getEndTime());
            }
            supportSQLiteStatement.bindLong(5, hourlyWalkData.getIntervelValue());
            String frommArrayLisr = Convertors.frommArrayLisr(hourlyWalkData.getCodevalue());
            if (frommArrayLisr == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, frommArrayLisr);
            }
            String frommArrayLisr2 = Convertors.frommArrayLisr(hourlyWalkData.getDistanceCodeValue());
            if (frommArrayLisr2 == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, frommArrayLisr2);
            }
            String fromListFloat = ConvertorsMediaList.fromListFloat(hourlyWalkData.getCalorieCodeValue());
            if (fromListFloat == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindString(8, fromListFloat);
            }
            String str2 = hourlyWalkData.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(9);
            } else {
                supportSQLiteStatement.bindString(9, str2);
            }
            supportSQLiteStatement.bindLong(10, hourlyWalkData.getCalories());
            supportSQLiteStatement.bindLong(11, hourlyWalkData.getDistance());
            if (hourlyWalkData.getActiveTime() == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindLong(12, hourlyWalkData.getActiveTime().intValue());
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `hourlywalkdata`(`id`,`date`,`start_time`,`end_time`,`interval_value`,`codevalue`,`distance_code_value`,`calorie_code_value`,`serial_no`,`calories`,`distance`,`active_time`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class l extends ComputableLiveData<PersonalBest> {
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
        public PersonalBest compute() {
            PersonalBest personalBest;
            if (this.g == null) {
                this.g = new a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("value");
                if (query.moveToFirst()) {
                    personalBest = new PersonalBest();
                    personalBest.setDate(query.getString(columnIndexOrThrow));
                    personalBest.setValue(query.getLong(columnIndexOrThrow2));
                } else {
                    personalBest = null;
                }
                return personalBest;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class m extends ComputableLiveData<Integer> {
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
        public Integer compute() {
            Integer num;
            if (this.g == null) {
                this.g = new a("dailywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                if (query.moveToFirst() && !query.isNull(0)) {
                    num = Integer.valueOf(query.getInt(0));
                    return num;
                }
                num = null;
                return num;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class n extends ComputableLiveData<List<HourlyWalkData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                n.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public n(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<HourlyWalkData> compute() {
            if (this.g == null) {
                this.g = new a("hourlywalkdata", new String[0]);
                WalkDataDao_Impl.this.f6992a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = WalkDataDao_Impl.this.f6992a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("interval_value");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("codevalue");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("distance_code_value");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("calorie_code_value");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("calories");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("distance");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("active_time");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    HourlyWalkData hourlyWalkData = new HourlyWalkData();
                    hourlyWalkData.setId(query.getString(columnIndexOrThrow));
                    hourlyWalkData.mDate = query.getString(columnIndexOrThrow2);
                    hourlyWalkData.setStartTime(query.getString(columnIndexOrThrow3));
                    hourlyWalkData.setEndTime(query.getString(columnIndexOrThrow4));
                    hourlyWalkData.setIntervelValue(query.getInt(columnIndexOrThrow5));
                    hourlyWalkData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                    hourlyWalkData.setDistanceCodeValue(Convertors.frommString(query.getString(columnIndexOrThrow7)));
                    hourlyWalkData.setCalorieCodeValue(ConvertorsMediaList.frommStringToListFloat(query.getString(columnIndexOrThrow8)));
                    hourlyWalkData.mac_address = query.getString(columnIndexOrThrow9);
                    hourlyWalkData.setCalories(query.getInt(columnIndexOrThrow10));
                    hourlyWalkData.setDistance(query.getInt(columnIndexOrThrow11));
                    hourlyWalkData.setActiveTime(query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12)));
                    arrayList.add(hourlyWalkData);
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
    public class o extends EntityInsertionAdapter<DailyWalkData> {
        public o(WalkDataDao_Impl walkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, DailyWalkData dailyWalkData) {
            String str = dailyWalkData.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = dailyWalkData.mac_address;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            supportSQLiteStatement.bindLong(3, dailyWalkData.getValue());
            supportSQLiteStatement.bindLong(4, dailyWalkData.getMeters());
            supportSQLiteStatement.bindDouble(5, dailyWalkData.getCalories());
            supportSQLiteStatement.bindDouble(6, dailyWalkData.getPace());
            supportSQLiteStatement.bindLong(7, dailyWalkData.getStepsTarget());
            if (dailyWalkData.getActiveTime() == null) {
                supportSQLiteStatement.bindNull(8);
            } else {
                supportSQLiteStatement.bindLong(8, dailyWalkData.getActiveTime().intValue());
            }
            supportSQLiteStatement.bindLong(9, dailyWalkData.is_sync_server ? 1L : 0L);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `dailywalkdata`(`mDate`,`serial_no`,`steps`,`distance`,`calories`,`pace`,`steps_target`,`active_time`,`is_sync_server`) VALUES (?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class p extends SharedSQLiteStatement {
        public p(WalkDataDao_Impl walkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE hourlywalkdata SET distance_code_value = ?, distance = ? WHERE date =? and start_time=? and end_time  =? and serial_no =?";
        }
    }

    /* loaded from: classes8.dex */
    public class q extends SharedSQLiteStatement {
        public q(WalkDataDao_Impl walkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE dailywalkdata SET distance = ? WHERE mDate =? and serial_no =?";
        }
    }

    /* loaded from: classes8.dex */
    public class r extends SharedSQLiteStatement {
        public r(WalkDataDao_Impl walkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE hourlywalkdata SET calorie_code_value = ?, calories = ? WHERE date =? AND start_time=? AND end_time  =? AND serial_no =?";
        }
    }

    /* loaded from: classes8.dex */
    public class s extends SharedSQLiteStatement {
        public s(WalkDataDao_Impl walkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE dailywalkdata SET calories = ? WHERE mDate =? and serial_no =?";
        }
    }

    /* loaded from: classes8.dex */
    public class t extends SharedSQLiteStatement {
        public t(WalkDataDao_Impl walkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE dailywalkdata SET steps_target = ? WHERE mDate between (?) and (?) and serial_no =?";
        }
    }

    /* loaded from: classes8.dex */
    public class u extends SharedSQLiteStatement {
        public u(WalkDataDao_Impl walkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from dailywalkdata where mDate=? AND serial_no=?";
        }
    }

    /* loaded from: classes8.dex */
    public class v extends SharedSQLiteStatement {
        public v(WalkDataDao_Impl walkDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from hourlywalkdata where date=? AND serial_no=?";
        }
    }

    public WalkDataDao_Impl(RoomDatabase roomDatabase) {
        this.f6992a = roomDatabase;
        this.b = new k(this, roomDatabase);
        this.c = new o(this, roomDatabase);
        this.d = new p(this, roomDatabase);
        this.e = new q(this, roomDatabase);
        this.f = new r(this, roomDatabase);
        this.g = new s(this, roomDatabase);
        this.h = new t(this, roomDatabase);
        this.i = new u(this, roomDatabase);
        this.j = new v(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public int deleteDailyWalkDataFor(String str, String str2) {
        SupportSQLiteStatement acquire = this.i.acquire();
        this.f6992a.beginTransaction();
        try {
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
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.f6992a.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.f6992a.endTransaction();
            this.i.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public int deleteHourlyWalkDataFor(String str, String str2) {
        SupportSQLiteStatement acquire = this.j.acquire();
        this.f6992a.beginTransaction();
        try {
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
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.f6992a.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.f6992a.endTransaction();
            this.j.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public List<DailyWalkData> getAllDailyWalkData() {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select mDate,steps_target,serial_no,active_time,SUM(steps) as steps,SUM(distance) as distance,SUM(calories) as calories,SUM(pace) as pace,is_sync_server from dailywalkdata GROUP BY mDate", 0);
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("active_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("steps");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("pace");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyWalkData dailyWalkData = new DailyWalkData();
                dailyWalkData.mDate = query.getString(columnIndexOrThrow);
                dailyWalkData.setStepsTarget(query.getInt(columnIndexOrThrow2));
                dailyWalkData.mac_address = query.getString(columnIndexOrThrow3);
                dailyWalkData.setActiveTime(query.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow4)));
                dailyWalkData.setValue(query.getInt(columnIndexOrThrow5));
                dailyWalkData.setMeters(query.getInt(columnIndexOrThrow6));
                roomSQLiteQuery = acquire;
                try {
                    dailyWalkData.setCalories(query.getDouble(columnIndexOrThrow7));
                    dailyWalkData.setPace(query.getDouble(columnIndexOrThrow8));
                    dailyWalkData.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                    arrayList.add(dailyWalkData);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public List<DailyWalkData> getCompleteDailyWalkData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailywalkdata WHERE serial_no=? ORDER BY date(mDate) ", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("pace");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("active_time");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyWalkData dailyWalkData = new DailyWalkData();
                dailyWalkData.mDate = query.getString(columnIndexOrThrow);
                dailyWalkData.mac_address = query.getString(columnIndexOrThrow2);
                dailyWalkData.setValue(query.getInt(columnIndexOrThrow3));
                dailyWalkData.setMeters(query.getInt(columnIndexOrThrow4));
                dailyWalkData.setCalories(query.getDouble(columnIndexOrThrow5));
                dailyWalkData.setPace(query.getDouble(columnIndexOrThrow6));
                dailyWalkData.setStepsTarget(query.getInt(columnIndexOrThrow7));
                dailyWalkData.setActiveTime(query.isNull(columnIndexOrThrow8) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow8)));
                dailyWalkData.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                arrayList.add(dailyWalkData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<Integer> getCurrentWeekStepCount() {
        return new m(this.f6992a.getQueryExecutor(), RoomSQLiteQuery.acquire("SELECT SUM(steps) as value from dailywalkdata WHERE strftime('%W',mDate)=strftime('%W','now')", 0)).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public DailyWalkData getDailyWalkData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailywalkdata WHERE mDate =? AND serial_no=?", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("pace");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("active_time");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            DailyWalkData dailyWalkData = null;
            Integer valueOf = null;
            if (query.moveToFirst()) {
                DailyWalkData dailyWalkData2 = new DailyWalkData();
                dailyWalkData2.mDate = query.getString(columnIndexOrThrow);
                dailyWalkData2.mac_address = query.getString(columnIndexOrThrow2);
                dailyWalkData2.setValue(query.getInt(columnIndexOrThrow3));
                dailyWalkData2.setMeters(query.getInt(columnIndexOrThrow4));
                dailyWalkData2.setCalories(query.getDouble(columnIndexOrThrow5));
                dailyWalkData2.setPace(query.getDouble(columnIndexOrThrow6));
                dailyWalkData2.setStepsTarget(query.getInt(columnIndexOrThrow7));
                if (!query.isNull(columnIndexOrThrow8)) {
                    valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow8));
                }
                dailyWalkData2.setActiveTime(valueOf);
                if (query.getInt(columnIndexOrThrow9) == 0) {
                    z = false;
                }
                dailyWalkData2.is_sync_server = z;
                dailyWalkData = dailyWalkData2;
            }
            return dailyWalkData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public List<DailyWalkData> getDailyWalkDataBetweenDates(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from dailywalkdata WHERE serial_no=? AND mDate BETWEEN ? AND ?", 3);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("pace");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("active_time");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyWalkData dailyWalkData = new DailyWalkData();
                dailyWalkData.mDate = query.getString(columnIndexOrThrow);
                dailyWalkData.mac_address = query.getString(columnIndexOrThrow2);
                dailyWalkData.setValue(query.getInt(columnIndexOrThrow3));
                dailyWalkData.setMeters(query.getInt(columnIndexOrThrow4));
                int i2 = columnIndexOrThrow3;
                dailyWalkData.setCalories(query.getDouble(columnIndexOrThrow5));
                dailyWalkData.setPace(query.getDouble(columnIndexOrThrow6));
                dailyWalkData.setStepsTarget(query.getInt(columnIndexOrThrow7));
                dailyWalkData.setActiveTime(query.isNull(columnIndexOrThrow8) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow8)));
                dailyWalkData.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                arrayList.add(dailyWalkData);
                columnIndexOrThrow3 = i2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public DailyWalkData getDailyWalkDataWithDate(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailywalkdata WHERE  mDate=? AND serial_no=?", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("pace");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("active_time");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            DailyWalkData dailyWalkData = null;
            Integer valueOf = null;
            if (query.moveToFirst()) {
                DailyWalkData dailyWalkData2 = new DailyWalkData();
                dailyWalkData2.mDate = query.getString(columnIndexOrThrow);
                dailyWalkData2.mac_address = query.getString(columnIndexOrThrow2);
                dailyWalkData2.setValue(query.getInt(columnIndexOrThrow3));
                dailyWalkData2.setMeters(query.getInt(columnIndexOrThrow4));
                dailyWalkData2.setCalories(query.getDouble(columnIndexOrThrow5));
                dailyWalkData2.setPace(query.getDouble(columnIndexOrThrow6));
                dailyWalkData2.setStepsTarget(query.getInt(columnIndexOrThrow7));
                if (!query.isNull(columnIndexOrThrow8)) {
                    valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow8));
                }
                dailyWalkData2.setActiveTime(valueOf);
                if (query.getInt(columnIndexOrThrow9) == 0) {
                    z = false;
                }
                dailyWalkData2.is_sync_server = z;
                dailyWalkData = dailyWalkData2;
            }
            return dailyWalkData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public List<HourlyWalkData> getFiveMinuteData(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM HourlyWalkData WHERE date LIKE ? AND serial_no=?", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("interval_value");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("distance_code_value");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("calorie_code_value");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("active_time");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyWalkData hourlyWalkData = new HourlyWalkData();
                roomSQLiteQuery = acquire;
                try {
                    hourlyWalkData.setId(query.getString(columnIndexOrThrow));
                    hourlyWalkData.mDate = query.getString(columnIndexOrThrow2);
                    hourlyWalkData.setStartTime(query.getString(columnIndexOrThrow3));
                    hourlyWalkData.setEndTime(query.getString(columnIndexOrThrow4));
                    hourlyWalkData.setIntervelValue(query.getInt(columnIndexOrThrow5));
                    hourlyWalkData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                    hourlyWalkData.setDistanceCodeValue(Convertors.frommString(query.getString(columnIndexOrThrow7)));
                    hourlyWalkData.setCalorieCodeValue(ConvertorsMediaList.frommStringToListFloat(query.getString(columnIndexOrThrow8)));
                    hourlyWalkData.mac_address = query.getString(columnIndexOrThrow9);
                    hourlyWalkData.setCalories(query.getInt(columnIndexOrThrow10));
                    hourlyWalkData.setDistance(query.getInt(columnIndexOrThrow11));
                    hourlyWalkData.setActiveTime(query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12)));
                    arrayList.add(hourlyWalkData);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public HourlyWalkData getHourlyLatestRecordFor(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourlywalkdata WHERE interval_value>0 AND date=? AND serial_no=? ORDER BY start_time DESC LIMIT 1 ", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("interval_value");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("distance_code_value");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("calorie_code_value");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("active_time");
            HourlyWalkData hourlyWalkData = null;
            if (query.moveToFirst()) {
                HourlyWalkData hourlyWalkData2 = new HourlyWalkData();
                hourlyWalkData2.setId(query.getString(columnIndexOrThrow));
                hourlyWalkData2.mDate = query.getString(columnIndexOrThrow2);
                hourlyWalkData2.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyWalkData2.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyWalkData2.setIntervelValue(query.getInt(columnIndexOrThrow5));
                hourlyWalkData2.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                hourlyWalkData2.setDistanceCodeValue(Convertors.frommString(query.getString(columnIndexOrThrow7)));
                hourlyWalkData2.setCalorieCodeValue(ConvertorsMediaList.frommStringToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyWalkData2.mac_address = query.getString(columnIndexOrThrow9);
                hourlyWalkData2.setCalories(query.getInt(columnIndexOrThrow10));
                hourlyWalkData2.setDistance(query.getInt(columnIndexOrThrow11));
                hourlyWalkData2.setActiveTime(query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12)));
                hourlyWalkData = hourlyWalkData2;
            }
            return hourlyWalkData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public String getLastDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT mDate FROM dailywalkdata  WHERE serial_no=? ORDER BY date(mDate)  LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6992a.query(acquire);
        try {
            return query.moveToFirst() ? query.getString(0) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public DailyWalkData getLatestDayData(String str) {
        boolean z = true;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailywalkdata WHERE serial_no=? ORDER BY date(mDate) DESC  LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("pace");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("active_time");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            DailyWalkData dailyWalkData = null;
            Integer valueOf = null;
            if (query.moveToFirst()) {
                DailyWalkData dailyWalkData2 = new DailyWalkData();
                dailyWalkData2.mDate = query.getString(columnIndexOrThrow);
                dailyWalkData2.mac_address = query.getString(columnIndexOrThrow2);
                dailyWalkData2.setValue(query.getInt(columnIndexOrThrow3));
                dailyWalkData2.setMeters(query.getInt(columnIndexOrThrow4));
                dailyWalkData2.setCalories(query.getDouble(columnIndexOrThrow5));
                dailyWalkData2.setPace(query.getDouble(columnIndexOrThrow6));
                dailyWalkData2.setStepsTarget(query.getInt(columnIndexOrThrow7));
                if (!query.isNull(columnIndexOrThrow8)) {
                    valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow8));
                }
                dailyWalkData2.setActiveTime(valueOf);
                if (query.getInt(columnIndexOrThrow9) == 0) {
                    z = false;
                }
                dailyWalkData2.is_sync_server = z;
                dailyWalkData = dailyWalkData2;
            }
            return dailyWalkData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public DailyWalkData getLatestDayDataWithValue(String str) {
        boolean z = true;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailywalkdata where steps>0 and serial_no=? ORDER BY date(mDate) DESC  LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("pace");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("active_time");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            DailyWalkData dailyWalkData = null;
            Integer valueOf = null;
            if (query.moveToFirst()) {
                DailyWalkData dailyWalkData2 = new DailyWalkData();
                dailyWalkData2.mDate = query.getString(columnIndexOrThrow);
                dailyWalkData2.mac_address = query.getString(columnIndexOrThrow2);
                dailyWalkData2.setValue(query.getInt(columnIndexOrThrow3));
                dailyWalkData2.setMeters(query.getInt(columnIndexOrThrow4));
                dailyWalkData2.setCalories(query.getDouble(columnIndexOrThrow5));
                dailyWalkData2.setPace(query.getDouble(columnIndexOrThrow6));
                dailyWalkData2.setStepsTarget(query.getInt(columnIndexOrThrow7));
                if (!query.isNull(columnIndexOrThrow8)) {
                    valueOf = Integer.valueOf(query.getInt(columnIndexOrThrow8));
                }
                dailyWalkData2.setActiveTime(valueOf);
                if (query.getInt(columnIndexOrThrow9) == 0) {
                    z = false;
                }
                dailyWalkData2.is_sync_server = z;
                dailyWalkData = dailyWalkData2;
            }
            return dailyWalkData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public HourlyWalkData getLatestRecordHourly(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM hourlywalkdata where interval_value>0 AND serial_no=? ORDER BY id DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("interval_value");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("distance_code_value");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("calorie_code_value");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("active_time");
            HourlyWalkData hourlyWalkData = null;
            if (query.moveToFirst()) {
                HourlyWalkData hourlyWalkData2 = new HourlyWalkData();
                hourlyWalkData2.setId(query.getString(columnIndexOrThrow));
                hourlyWalkData2.mDate = query.getString(columnIndexOrThrow2);
                hourlyWalkData2.setStartTime(query.getString(columnIndexOrThrow3));
                hourlyWalkData2.setEndTime(query.getString(columnIndexOrThrow4));
                hourlyWalkData2.setIntervelValue(query.getInt(columnIndexOrThrow5));
                hourlyWalkData2.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                hourlyWalkData2.setDistanceCodeValue(Convertors.frommString(query.getString(columnIndexOrThrow7)));
                hourlyWalkData2.setCalorieCodeValue(ConvertorsMediaList.frommStringToListFloat(query.getString(columnIndexOrThrow8)));
                hourlyWalkData2.mac_address = query.getString(columnIndexOrThrow9);
                hourlyWalkData2.setCalories(query.getInt(columnIndexOrThrow10));
                hourlyWalkData2.setDistance(query.getInt(columnIndexOrThrow11));
                hourlyWalkData2.setActiveTime(query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12)));
                hourlyWalkData = hourlyWalkData2;
            }
            return hourlyWalkData;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<List<DailyWalkData>> getLiveAllDailyWalkData() {
        return new f(this.f6992a.getQueryExecutor(), RoomSQLiteQuery.acquire("select mDate,steps_target,serial_no,active_time,SUM(steps) as steps,SUM(distance) as distance,SUM(calories) as calories,SUM(pace) as pace,is_sync_server from dailywalkdata GROUP BY mDate", 0)).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<List<DailyWalkData>> getLiveCompleteDailyWalkData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailywalkdata WHERE serial_no=? ORDER BY date(mDate) ", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new e(this.f6992a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<List<HourlyWalkData>> getLiveFiveMinuteData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM HourlyWalkData WHERE date LIKE ? AND serial_no=?", 2);
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
        return new c(this.f6992a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<List<HourlyWalkData>> getLiveTotalFiveMinuteData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date,start_time,end_time,serial_no,id,active_time, SUM(interval_value) as interval_value ,SUM(distance) as distance ,SUM(calories) as calories, codevalue FROM HourlyWalkData WHERE date LIKE ? GROUP BY start_time", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new d(this.f6992a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<List<StepsDataModelMonthWiseCommon>> getLiveTotalWalkDataMonthWiseCommon() {
        return new h(this.f6992a.getQueryExecutor(), RoomSQLiteQuery.acquire("SELECT SUM(steps) as  stepCount,SUM(distance) as distance,SUM(calories) as calories, strftime('%Y',mDate) as year, substr(mDate, 0,8) as month FROM dailywalkdata GROUP BY month, year", 0)).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<List<StepsDataModelWeekWiseCommon>> getLiveTotalWalkDataWeekWiseCommon() {
        return new j(this.f6992a.getQueryExecutor(), RoomSQLiteQuery.acquire("SELECT SUM(steps) as  stepCount,SUM(distance) as distance,SUM(calories) as calories ,strftime('%Y',mDate) as year,strftime('%W',mDate)+1 as week FROM dailywalkdata GROUP BY week,year", 0)).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<List<StepsDataModelMonthWiseCommon>> getLiveWalkDataMonthWiseCommon(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(steps) as  stepCount,SUM(distance) as distance,SUM(calories) as calories, strftime('%Y',mDate) as year, substr(mDate, 0,8) as month FROM dailywalkdata WHERE serial_no=? GROUP BY month, year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new g(this.f6992a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<List<StepsDataModelWeekWiseCommon>> getLiveWalkDataWeekWiseCommon(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(steps) as  stepCount,SUM(distance) as distance,SUM(calories) as calories,strftime('%Y',mDate) as year, strftime('%W',mDate)+1 as week FROM dailywalkdata WHERE serial_no=? GROUP BY week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new i(this.f6992a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<PersonalBest> getMaxValueTillData() {
        return new l(this.f6992a.getQueryExecutor(), RoomSQLiteQuery.acquire("SELECT mDate as date, MAX(steps) as value from dailywalkdata", 0)).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public List<HourlyWalkData> getOrderedHourlyWalkDataFor(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM HourlyWalkData WHERE serial_no=? AND date LIKE ? ORDER BY start_time", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("interval_value");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("codevalue");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("distance_code_value");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("calorie_code_value");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("active_time");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyWalkData hourlyWalkData = new HourlyWalkData();
                roomSQLiteQuery = acquire;
                try {
                    hourlyWalkData.setId(query.getString(columnIndexOrThrow));
                    hourlyWalkData.mDate = query.getString(columnIndexOrThrow2);
                    hourlyWalkData.setStartTime(query.getString(columnIndexOrThrow3));
                    hourlyWalkData.setEndTime(query.getString(columnIndexOrThrow4));
                    hourlyWalkData.setIntervelValue(query.getInt(columnIndexOrThrow5));
                    hourlyWalkData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow6)));
                    hourlyWalkData.setDistanceCodeValue(Convertors.frommString(query.getString(columnIndexOrThrow7)));
                    hourlyWalkData.setCalorieCodeValue(ConvertorsMediaList.frommStringToListFloat(query.getString(columnIndexOrThrow8)));
                    hourlyWalkData.mac_address = query.getString(columnIndexOrThrow9);
                    hourlyWalkData.setCalories(query.getInt(columnIndexOrThrow10));
                    hourlyWalkData.setDistance(query.getInt(columnIndexOrThrow11));
                    hourlyWalkData.setActiveTime(query.isNull(columnIndexOrThrow12) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow12)));
                    arrayList.add(hourlyWalkData);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public int getRowCount(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM dailywalkdata where mDate=? AND serial_no=?", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public String getTOTALLastDate() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT mDate FROM dailywalkdata   ORDER BY date(mDate)  LIMIT 1", 0);
        Cursor query = this.f6992a.query(acquire);
        try {
            return query.moveToFirst() ? query.getString(0) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public int getTotalCalories(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(calories) from hourlywalkdata where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public int getTotalDistance(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(distance) from hourlywalkdata where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public List<HourlyWalkData> getTotalFiveMinuteData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date,start_time,end_time,serial_no,id, active_time,SUM(interval_value) as interval_value ,SUM(distance) as distance ,SUM(calories) as calories, codevalue FROM HourlyWalkData WHERE date LIKE ? GROUP BY start_time", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("date");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("start_time");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("end_time");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("id");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("active_time");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("interval_value");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("codevalue");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                HourlyWalkData hourlyWalkData = new HourlyWalkData();
                hourlyWalkData.mDate = query.getString(columnIndexOrThrow);
                hourlyWalkData.setStartTime(query.getString(columnIndexOrThrow2));
                hourlyWalkData.setEndTime(query.getString(columnIndexOrThrow3));
                hourlyWalkData.mac_address = query.getString(columnIndexOrThrow4);
                hourlyWalkData.setId(query.getString(columnIndexOrThrow5));
                hourlyWalkData.setActiveTime(query.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow6)));
                hourlyWalkData.setIntervelValue(query.getInt(columnIndexOrThrow7));
                hourlyWalkData.setDistance(query.getInt(columnIndexOrThrow8));
                hourlyWalkData.setCalories(query.getInt(columnIndexOrThrow9));
                hourlyWalkData.setCodevalue(Convertors.frommString(query.getString(columnIndexOrThrow10)));
                arrayList.add(hourlyWalkData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public int getTotalSteps(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(interval_value) from hourlywalkdata where date=? AND serial_no=?", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public List<DailyWalkData> getTotalUnSyncedWalkData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailywalkdata where serial_no=? AND is_sync_server=0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6992a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("mDate");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("steps");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("distance");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("calories");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("pace");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("steps_target");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("active_time");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow("is_sync_server");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                DailyWalkData dailyWalkData = new DailyWalkData();
                dailyWalkData.mDate = query.getString(columnIndexOrThrow);
                dailyWalkData.mac_address = query.getString(columnIndexOrThrow2);
                dailyWalkData.setValue(query.getInt(columnIndexOrThrow3));
                dailyWalkData.setMeters(query.getInt(columnIndexOrThrow4));
                dailyWalkData.setCalories(query.getDouble(columnIndexOrThrow5));
                dailyWalkData.setPace(query.getDouble(columnIndexOrThrow6));
                dailyWalkData.setStepsTarget(query.getInt(columnIndexOrThrow7));
                dailyWalkData.setActiveTime(query.isNull(columnIndexOrThrow8) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow8)));
                dailyWalkData.is_sync_server = query.getInt(columnIndexOrThrow9) != 0;
                arrayList.add(dailyWalkData);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<DailyWalkData> getTotalWalkData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT mDate,active_time,SUM(steps) as steps,steps_target,SUM(calories) as calories,SUM(distance) as distance,SUM(pace) as pace,serial_no,is_sync_server FROM dailywalkdata WHERE mDate LIKE?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new b(this.f6992a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public int getWalkCountFor(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT SUM(interval_value) FROM HourlyWalkData WHERE date LIKE ? AND serial_no=?", 2);
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
        Cursor query = this.f6992a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<DailyWalkData> getWalkData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM dailywalkdata WHERE mDate LIKE? AND serial_no=?", 2);
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
        return new a(this.f6992a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public LiveData<List<HourlyWalkData>> getWalkDataBetweenDates(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from hourlywalkdata WHERE serial_no=? AND date BETWEEN ? AND ?", 3);
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
        return new n(this.f6992a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public void insert(DailyWalkData dailyWalkData) {
        this.f6992a.beginTransaction();
        try {
            this.c.insert((EntityInsertionAdapter) dailyWalkData);
            this.f6992a.setTransactionSuccessful();
        } finally {
            this.f6992a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public void insertAll(List<HourlyWalkData> list) {
        this.f6992a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6992a.setTransactionSuccessful();
        } finally {
            this.f6992a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public void updateDailyCalorieValue(int i2, String str, String str2) {
        SupportSQLiteStatement acquire = this.g.acquire();
        this.f6992a.beginTransaction();
        try {
            acquire.bindLong(1, i2);
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
            acquire.executeUpdateDelete();
            this.f6992a.setTransactionSuccessful();
        } finally {
            this.f6992a.endTransaction();
            this.g.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public void updateDailyDistanceValue(int i2, String str, String str2) {
        SupportSQLiteStatement acquire = this.e.acquire();
        this.f6992a.beginTransaction();
        try {
            acquire.bindLong(1, i2);
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
            acquire.executeUpdateDelete();
            this.f6992a.setTransactionSuccessful();
        } finally {
            this.f6992a.endTransaction();
            this.e.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public void updateDailyTargetValue(int i2, String str, String str2, String str3) {
        SupportSQLiteStatement acquire = this.h.acquire();
        this.f6992a.beginTransaction();
        try {
            acquire.bindLong(1, i2);
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
            if (str3 == null) {
                acquire.bindNull(4);
            } else {
                acquire.bindString(4, str3);
            }
            acquire.executeUpdateDelete();
            this.f6992a.setTransactionSuccessful();
        } finally {
            this.f6992a.endTransaction();
            this.h.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public void updateHourlyCalorieValue(ArrayList<Float> arrayList, int i2, String str, String str2, String str3, String str4) {
        SupportSQLiteStatement acquire = this.f.acquire();
        this.f6992a.beginTransaction();
        try {
            String fromArrayListFloat = ConvertorsMediaList.fromArrayListFloat(arrayList);
            if (fromArrayListFloat == null) {
                acquire.bindNull(1);
            } else {
                acquire.bindString(1, fromArrayListFloat);
            }
            acquire.bindLong(2, i2);
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
            if (str3 == null) {
                acquire.bindNull(5);
            } else {
                acquire.bindString(5, str3);
            }
            if (str4 == null) {
                acquire.bindNull(6);
            } else {
                acquire.bindString(6, str4);
            }
            acquire.executeUpdateDelete();
            this.f6992a.setTransactionSuccessful();
        } finally {
            this.f6992a.endTransaction();
            this.f.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public void updateHourlyDistanceValue(ArrayList<Integer> arrayList, int i2, String str, String str2, String str3, String str4) {
        SupportSQLiteStatement acquire = this.d.acquire();
        this.f6992a.beginTransaction();
        try {
            String frommArrayLisr = Convertors.frommArrayLisr(arrayList);
            if (frommArrayLisr == null) {
                acquire.bindNull(1);
            } else {
                acquire.bindString(1, frommArrayLisr);
            }
            acquire.bindLong(2, i2);
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
            if (str3 == null) {
                acquire.bindNull(5);
            } else {
                acquire.bindString(5, str3);
            }
            if (str4 == null) {
                acquire.bindNull(6);
            } else {
                acquire.bindString(6, str4);
            }
            acquire.executeUpdateDelete();
            this.f6992a.setTransactionSuccessful();
        } finally {
            this.f6992a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.coveiot.covedb.walk.WalkDataDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM dailywalkdata where serial_no=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6992a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
