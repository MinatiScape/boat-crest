package com.coveiot.covedb.manualdata.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.coveiot.covedb.manualdata.entities.EntityManualData;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes8.dex */
public final class ManualDataDao_Impl implements ManualDataDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f6958a;
    public final EntityInsertionAdapter b;

    /* loaded from: classes8.dex */
    public class a extends ComputableLiveData<EntityManualData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* renamed from: com.coveiot.covedb.manualdata.dao.ManualDataDao_Impl$a$a  reason: collision with other inner class name */
        /* loaded from: classes8.dex */
        public class C0329a extends InvalidationTracker.Observer {
            public C0329a(String str, String... strArr) {
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
        public EntityManualData compute() {
            EntityManualData entityManualData;
            if (this.g == null) {
                this.g = new C0329a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                if (query.moveToFirst()) {
                    entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                    entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
                } else {
                    entityManualData = null;
                }
                return entityManualData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class a0 extends ComputableLiveData<EntityManualData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                a0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public EntityManualData compute() {
            EntityManualData entityManualData;
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                if (query.moveToFirst()) {
                    entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                    entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
                } else {
                    entityManualData = null;
                }
                return entityManualData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class b extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class b0 extends ComputableLiveData<EntityManualData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                b0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public EntityManualData compute() {
            EntityManualData entityManualData;
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                if (query.moveToFirst()) {
                    entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                    entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
                } else {
                    entityManualData = null;
                }
                return entityManualData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class c extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class c0 extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                c0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class d extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class d0 extends ComputableLiveData<EntityManualData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                d0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public EntityManualData compute() {
            EntityManualData entityManualData;
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                if (query.moveToFirst()) {
                    entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                    entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
                } else {
                    entityManualData = null;
                }
                return entityManualData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class e extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class e0 extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                e0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class f extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class f0 extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                f0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class g extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class g0 extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                g0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public g0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class h extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class h0 extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                h0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public h0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class i extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class i0 extends ComputableLiveData<EntityManualData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                i0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public i0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public EntityManualData compute() {
            EntityManualData entityManualData;
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                if (query.moveToFirst()) {
                    entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                    entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
                } else {
                    entityManualData = null;
                }
                return entityManualData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class j extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class j0 extends ComputableLiveData<EntityManualData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                j0.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public j0(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public EntityManualData compute() {
            EntityManualData entityManualData;
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                if (query.moveToFirst()) {
                    entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                    entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
                } else {
                    entityManualData = null;
                }
                return entityManualData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class k extends EntityInsertionAdapter<EntityManualData> {
        public k(ManualDataDao_Impl manualDataDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, EntityManualData entityManualData) {
            supportSQLiteStatement.bindLong(1, entityManualData.getTimeStamp());
            if (entityManualData.getSource() == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, entityManualData.getSource());
            }
            if (entityManualData.getSerialNo() == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, entityManualData.getSerialNo());
            }
            if (entityManualData.getUserDeviceId() == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, entityManualData.getUserDeviceId());
            }
            supportSQLiteStatement.bindLong(5, entityManualData.getHr());
            supportSQLiteStatement.bindLong(6, entityManualData.getSystolicBp());
            supportSQLiteStatement.bindLong(7, entityManualData.getDiastolicBp());
            supportSQLiteStatement.bindDouble(8, entityManualData.getSpo2());
            supportSQLiteStatement.bindDouble(9, entityManualData.getTemperature());
            supportSQLiteStatement.bindLong(10, entityManualData.isSyncedWithServer() ? 1L : 0L);
            supportSQLiteStatement.bindLong(11, entityManualData.isLevelInterpretation() ? 1L : 0L);
            if (entityManualData.getSpo2Level() == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, entityManualData.getSpo2Level());
            }
            supportSQLiteStatement.bindLong(13, entityManualData.getHrv());
            supportSQLiteStatement.bindLong(14, entityManualData.getStress());
            supportSQLiteStatement.bindLong(15, entityManualData.getVascularAging());
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `manual_data`(`timeStamp`,`source`,`serial_no`,`userDeviceId`,`hr`,`systolicBp`,`diastolicBp`,`spo2`,`temperature`,`isSyncedWithServer`,`isLevelInterpretation`,`spo2Level`,`hrv`,`stress`,`vascularAging`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class l extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class m extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class n extends ComputableLiveData<List<EntityManualData>> {
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
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class o extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                o.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public o(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class p extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                p.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public p(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class q extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                q.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public q(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class r extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                r.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public r(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class s extends ComputableLiveData<EntityManualData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                s.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public s(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public EntityManualData compute() {
            EntityManualData entityManualData;
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                if (query.moveToFirst()) {
                    entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                    entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
                } else {
                    entityManualData = null;
                }
                return entityManualData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class t extends ComputableLiveData<EntityManualData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                t.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public t(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public EntityManualData compute() {
            EntityManualData entityManualData;
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                if (query.moveToFirst()) {
                    entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                    entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
                } else {
                    entityManualData = null;
                }
                return entityManualData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class u extends ComputableLiveData<EntityManualData> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                u.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public u(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public EntityManualData compute() {
            EntityManualData entityManualData;
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                if (query.moveToFirst()) {
                    entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                    entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
                } else {
                    entityManualData = null;
                }
                return entityManualData;
            } finally {
                query.close();
            }
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes8.dex */
    public class v extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                v.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public v(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class w extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                w.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public w(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class x extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                x.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public x(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class y extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                y.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public y(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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
    public class z extends ComputableLiveData<List<EntityManualData>> {
        public InvalidationTracker.Observer g;
        public final /* synthetic */ RoomSQLiteQuery h;

        /* loaded from: classes8.dex */
        public class a extends InvalidationTracker.Observer {
            public a(String str, String... strArr) {
                super(str, strArr);
            }

            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                z.this.invalidate();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public z(Executor executor, RoomSQLiteQuery roomSQLiteQuery) {
            super(executor);
            this.h = roomSQLiteQuery;
        }

        @Override // androidx.lifecycle.ComputableLiveData
        /* renamed from: a */
        public List<EntityManualData> compute() {
            if (this.g == null) {
                this.g = new a("manual_data", new String[0]);
                ManualDataDao_Impl.this.f6958a.getInvalidationTracker().addWeakObserver(this.g);
            }
            Cursor query = ManualDataDao_Impl.this.f6958a.query(this.h);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
                int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
                int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
                int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
                int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
                int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
                int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
                int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
                int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
                int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
                int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
                int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
                int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
                int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i2 = columnIndexOrThrow;
                    int i3 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z = false;
                    }
                    entityManualData.setLevelInterpretation(z);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    entityManualData.setStress(query.getInt(i4));
                    int i5 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i5));
                    arrayList2.add(entityManualData);
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow2 = i3;
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

    public ManualDataDao_Impl(RoomDatabase roomDatabase) {
        this.f6958a = roomDatabase;
        this.b = new k(this, roomDatabase);
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getBpData(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) ORDER BY timeStamp DESC", 3);
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
        return new l(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public List<EntityManualData> getBpDataWithoutLiveData(String str, String str2, String str3, String str4) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND (strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) BETWEEN ? AND ?) AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) ORDER BY timeStamp DESC", 4);
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
        Cursor query = this.f6958a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    int i3 = columnIndexOrThrow11;
                    int i4 = columnIndexOrThrow12;
                    int i5 = columnIndexOrThrow;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    entityManualData.setLevelInterpretation(query.getInt(i3) != 0);
                    entityManualData.setSpo2Level(query.getString(i4));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i6 = i2;
                    entityManualData.setStress(query.getInt(i6));
                    int i7 = columnIndexOrThrow15;
                    int i8 = columnIndexOrThrow2;
                    entityManualData.setVascularAging(query.getInt(i7));
                    arrayList.add(entityManualData);
                    columnIndexOrThrow2 = i8;
                    columnIndexOrThrow15 = i7;
                    i2 = i6;
                    columnIndexOrThrow = i5;
                    columnIndexOrThrow12 = i4;
                    columnIndexOrThrow11 = i3;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getHighestSpo2DataForEachDay() {
        return new c(this.f6958a.getQueryExecutor(), RoomSQLiteQuery.acquire("Select *, max(spo2) from manual_data group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))", 0)).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getHrvData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE hrv>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no)", 2);
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
        return new v(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public EntityManualData getLastBpDataFor(String str) {
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
        EntityManualData entityManualData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select * from manual_data  where systolicBp>0 AND timeStamp in (Select max(timeStamp) from manual_data  WHERE source=COALESCE(?,source))", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6958a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
            columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
            if (query.moveToFirst()) {
                entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                entityManualData.setLevelInterpretation(query.getInt(columnIndexOrThrow11) != 0);
                entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
            } else {
                entityManualData = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return entityManualData;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getLastBpDataForEachDay(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data  WHERE serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))", 2);
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
        return new q(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public EntityManualData getLastMeasuredBp(String str) {
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
        EntityManualData entityManualData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE diastolicBp>0 AND systolicBp > 0 AND serial_no=COALESCE(?,serial_no) ORDER BY timeStamp DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6958a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
            columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
            if (query.moveToFirst()) {
                entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                entityManualData.setLevelInterpretation(query.getInt(columnIndexOrThrow11) != 0);
                entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
            } else {
                entityManualData = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return entityManualData;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getLastMeasuredBpHourlyDataForDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT max(timestamp),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch')) = ? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch'))) it WHERE md.timestamp = it.timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new p(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<EntityManualData> getLastMeasuredBpLiveData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE diastolicBp>0 AND systolicBp > 0 AND serial_no=COALESCE(?,serial_no) ORDER BY timeStamp DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new d0(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<EntityManualData> getLastMeasuredHRV(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE hrv>0  AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) ORDER BY timeStamp DESC LIMIT 1", 2);
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
        return new a0(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<EntityManualData> getLastMeasuredSpo2(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE spo2>0  AND serial_no=COALESCE(?,serial_no) ORDER BY timeStamp DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new s(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public EntityManualData getLastMeasuredSpo2ByMacAddress(String str) {
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
        EntityManualData entityManualData;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE spo2>0  AND serial_no=COALESCE(?,serial_no) ORDER BY timeStamp DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        Cursor query = this.f6958a.query(acquire);
        try {
            columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
            columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
            columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
            columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
            columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
            columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
            columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
            columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
            columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
            columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
            columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
            columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
            columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
            if (query.moveToFirst()) {
                entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                entityManualData.setLevelInterpretation(query.getInt(columnIndexOrThrow11) != 0);
                entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                entityManualData.setStress(query.getInt(columnIndexOrThrow14));
                entityManualData.setVascularAging(query.getInt(columnIndexOrThrow15));
            } else {
                entityManualData = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return entityManualData;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getLastMeasuredSpo2Data(String str, String str2, String str3, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) AND isLevelInterpretation=? ORDER BY timeStamp DESC", 4);
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
        acquire.bindLong(4, i2);
        return new f0(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<EntityManualData> getLastMeasuredSpo2DataFor(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data WHERE strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))=? group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new i0(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT max(timestamp),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = ? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp", 3);
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
        return new m(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<EntityManualData> getLastMeasuredStress(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE stress>0  AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) ORDER BY timeStamp DESC LIMIT 1", 2);
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
        return new b0(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getLastSpo2DataForEachDay() {
        return new h0(this.f6958a.getQueryExecutor(), RoomSQLiteQuery.acquire("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))", 0)).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT max(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = ? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp", 3);
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
        return new e(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT min(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = ? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp", 3);
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
        return new f(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public List<EntityManualData> getSpo2DataList(String str, String str2, String str3) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source)", 3);
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
        Cursor query = this.f6958a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    int i3 = columnIndexOrThrow11;
                    int i4 = columnIndexOrThrow12;
                    int i5 = columnIndexOrThrow;
                    int i6 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    entityManualData.setLevelInterpretation(query.getInt(i3) != 0);
                    entityManualData.setSpo2Level(query.getString(i4));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i7 = i2;
                    entityManualData.setStress(query.getInt(i7));
                    int i8 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i8));
                    arrayList.add(entityManualData);
                    i2 = i7;
                    columnIndexOrThrow15 = i8;
                    columnIndexOrThrow = i5;
                    columnIndexOrThrow2 = i6;
                    columnIndexOrThrow11 = i3;
                    columnIndexOrThrow12 = i4;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getSpo2ata(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source)", 3);
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
        return new w(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getStressData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE stress>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no)", 2);
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
        return new y(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getTemperatureData(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE temperature>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source)", 3);
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
        return new g0(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public List<EntityManualData> getUnSyncedBpData() {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND  isSyncedWithServer=0", 0);
        Cursor query = this.f6958a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i3 = columnIndexOrThrow;
                    int i4 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z2 = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z2 = false;
                    }
                    entityManualData.setLevelInterpretation(z2);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i5 = i2;
                    entityManualData.setStress(query.getInt(i5));
                    int i6 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i6));
                    arrayList2.add(entityManualData);
                    i2 = i5;
                    columnIndexOrThrow15 = i6;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i3;
                    columnIndexOrThrow2 = i4;
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

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public List<EntityManualData> getUnSyncedData() {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE isSyncedWithServer=0", 0);
        Cursor query = this.f6958a.query(acquire);
        try {
            int columnIndexOrThrow = query.getColumnIndexOrThrow("timeStamp");
            int columnIndexOrThrow2 = query.getColumnIndexOrThrow("source");
            int columnIndexOrThrow3 = query.getColumnIndexOrThrow("serial_no");
            int columnIndexOrThrow4 = query.getColumnIndexOrThrow("userDeviceId");
            int columnIndexOrThrow5 = query.getColumnIndexOrThrow("hr");
            int columnIndexOrThrow6 = query.getColumnIndexOrThrow("systolicBp");
            int columnIndexOrThrow7 = query.getColumnIndexOrThrow("diastolicBp");
            int columnIndexOrThrow8 = query.getColumnIndexOrThrow("spo2");
            int columnIndexOrThrow9 = query.getColumnIndexOrThrow(DeviceKey.TempData);
            int columnIndexOrThrow10 = query.getColumnIndexOrThrow("isSyncedWithServer");
            int columnIndexOrThrow11 = query.getColumnIndexOrThrow("isLevelInterpretation");
            int columnIndexOrThrow12 = query.getColumnIndexOrThrow("spo2Level");
            int columnIndexOrThrow13 = query.getColumnIndexOrThrow(DeviceKey.HRV);
            int columnIndexOrThrow14 = query.getColumnIndexOrThrow(DeviceKey.Stress);
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = query.getColumnIndexOrThrow(DeviceKey.VascularAging);
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    ArrayList arrayList2 = arrayList;
                    int i3 = columnIndexOrThrow;
                    int i4 = columnIndexOrThrow2;
                    EntityManualData entityManualData = new EntityManualData(query.getLong(columnIndexOrThrow), query.getString(columnIndexOrThrow2));
                    entityManualData.setSerialNo(query.getString(columnIndexOrThrow3));
                    entityManualData.setUserDeviceId(query.getString(columnIndexOrThrow4));
                    entityManualData.setHr(query.getInt(columnIndexOrThrow5));
                    entityManualData.setSystolicBp(query.getInt(columnIndexOrThrow6));
                    entityManualData.setDiastolicBp(query.getInt(columnIndexOrThrow7));
                    entityManualData.setSpo2(query.getDouble(columnIndexOrThrow8));
                    entityManualData.setTemperature(query.getDouble(columnIndexOrThrow9));
                    boolean z2 = true;
                    entityManualData.setSyncedWithServer(query.getInt(columnIndexOrThrow10) != 0);
                    if (query.getInt(columnIndexOrThrow11) == 0) {
                        z2 = false;
                    }
                    entityManualData.setLevelInterpretation(z2);
                    entityManualData.setSpo2Level(query.getString(columnIndexOrThrow12));
                    entityManualData.setHrv(query.getInt(columnIndexOrThrow13));
                    int i5 = i2;
                    entityManualData.setStress(query.getInt(i5));
                    int i6 = columnIndexOrThrow15;
                    entityManualData.setVascularAging(query.getInt(i6));
                    arrayList2.add(entityManualData);
                    i2 = i5;
                    columnIndexOrThrow15 = i6;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i3;
                    columnIndexOrThrow2 = i4;
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

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public void insert(EntityManualData entityManualData) {
        this.f6958a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter) entityManualData);
            this.f6958a.setTransactionSuccessful();
        } finally {
            this.f6958a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public void insertAll(List<EntityManualData> list) {
        this.f6958a.beginTransaction();
        try {
            this.b.insert((Iterable) list);
            this.f6958a.setTransactionSuccessful();
        } finally {
            this.f6958a.endTransaction();
        }
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getHighestSpo2DataForEachDay(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select *, max(spo2) from manual_data WHERE serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))", 2);
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
        return new d(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getLastSpo2DataForEachDay(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data  WHERE serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))", 2);
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
        return new b(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<EntityManualData> getLastMeasuredSpo2(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE spo2>0  AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) ORDER BY timeStamp DESC LIMIT 1", 2);
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
        return new t(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<EntityManualData> getLastMeasuredSpo2DataFor(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data WHERE strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))=? AND serial_no=COALESCE(?,serial_no) group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))", 2);
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
        return new j0(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getHrvData(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE hrv>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source)", 3);
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
        return new x(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getStressData(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE stress>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source)", 3);
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
        return new z(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getBpData(String str, String str2, String str3, String str4) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE systolicBp>0 AND diastolicBp>0 AND (strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) BETWEEN ? AND ?) AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) ORDER BY timeStamp DESC", 4);
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
        return new c0(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(String str, String str2, String str3, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT max(timestamp),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) AND isLevelInterpretation=? AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = ? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp", 4);
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
        acquire.bindLong(3, i2);
        if (str == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str);
        }
        return new n(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(String str, String str2, String str3, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT max(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND isLevelInterpretation=? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp", 4);
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
        acquire.bindLong(4, i2);
        return new g(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(String str, String str2, String str3, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT min(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND isLevelInterpretation=? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp", 4);
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
        acquire.bindLong(4, i2);
        return new h(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getSpo2ata(String str, String str2, String str3, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')) = ? AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) AND isLevelInterpretation=?", 4);
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
        acquire.bindLong(4, i2);
        return new e0(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<EntityManualData> getLastMeasuredSpo2(String str, String str2, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE spo2>0 AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source) AND isLevelInterpretation=? ORDER BY timeStamp DESC LIMIT 1", 3);
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
        acquire.bindLong(3, i2);
        return new u(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<EntityManualData> getLastMeasuredSpo2DataFor(String str, String str2, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select * from manual_data  where timeStamp in (Select max(timeStamp) from manual_data WHERE strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime'))=? AND serial_no=COALESCE(?,serial_no) AND isLevelInterpretation=? group by strftime('%Y-%m-%d', datetime(timeStamp/1000, 'unixepoch','localtime')))", 3);
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
        acquire.bindLong(3, i2);
        return new a(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getLastMeasuredSpo2HourlyDataForDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT max(timestamp),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = ? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new o(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getMaxSpo2HourlyDataForDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT max(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE spo2>0 AND strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = ? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new i(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getMinSpo2HourlyDataForDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("Select md.* from manual_data md,(SELECT min(spo2),timestamp,strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime')) FROM manual_data WHERE strftime('%Y-%m-%d',datetime(timeStamp/1000, 'unixepoch','localtime')) = ? group by  strftime('%H', datetime(timeStamp/1000, 'unixepoch','localtime'))) it WHERE md.timestamp = it.timestamp", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new j(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }

    @Override // com.coveiot.covedb.manualdata.dao.ManualDataDao
    public LiveData<List<EntityManualData>> getSpo2DataList(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM manual_data WHERE spo2>0  AND serial_no=COALESCE(?,serial_no) AND source=COALESCE(?,source)", 2);
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
        return new r(this.f6958a.getQueryExecutor(), acquire).getLiveData();
    }
}
