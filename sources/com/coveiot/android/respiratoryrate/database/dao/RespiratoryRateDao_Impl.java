package com.coveiot.android.respiratoryrate.database.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateData;
import com.coveiot.android.respiratoryrate.database.entities.RespiratoryRateDataConverter;
import com.coveiot.android.respiratoryrate.model.MonthlyRespiratoryRateData;
import com.coveiot.android.respiratoryrate.model.RespiratoryRateListBean;
import com.coveiot.android.respiratoryrate.model.WeeklyRespiratoryRateData;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
public final class RespiratoryRateDao_Impl implements RespiratoryRateDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f5672a;
    public final EntityInsertionAdapter<DailyRespiratoryRateEntity> b;
    public final SharedSQLiteStatement c;

    /* loaded from: classes6.dex */
    public class a extends EntityInsertionAdapter<DailyRespiratoryRateEntity> {
        public a(RespiratoryRateDao_Impl respiratoryRateDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, DailyRespiratoryRateEntity dailyRespiratoryRateEntity) {
            String str = dailyRespiratoryRateEntity.mDate;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            String str2 = dailyRespiratoryRateEntity.macAddress;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str2);
            }
            Integer num = dailyRespiratoryRateEntity.isSyncedWithServer;
            if (num == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindLong(3, num.intValue());
            }
            RespiratoryRateData respiratoryRateData = dailyRespiratoryRateEntity.data;
            if (respiratoryRateData != null) {
                if (respiratoryRateData.getDate() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, respiratoryRateData.getDate());
                }
                if (respiratoryRateData.getTzOffset() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, respiratoryRateData.getTzOffset());
                }
                if (respiratoryRateData.getComputedDate() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, respiratoryRateData.getComputedDate());
                }
                if (respiratoryRateData.getMin() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, respiratoryRateData.getMin().intValue());
                }
                if (respiratoryRateData.getMax() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindLong(8, respiratoryRateData.getMax().intValue());
                }
                if (respiratoryRateData.getAvg() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindLong(9, respiratoryRateData.getAvg().intValue());
                }
                String fromIntArrayList = RespiratoryRateDataConverter.fromIntArrayList(respiratoryRateData.getValues());
                if (fromIntArrayList == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, fromIntArrayList);
                }
                if (respiratoryRateData.getBaseUnit() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, respiratoryRateData.getBaseUnit());
                }
                String fromFloatArrayList = RespiratoryRateDataConverter.fromFloatArrayList(respiratoryRateData.getAccuracies());
                if (fromFloatArrayList == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, fromFloatArrayList);
                }
                String fromRespiratorySourceData = RespiratoryRateDataConverter.fromRespiratorySourceData(respiratoryRateData.getSource());
                if (fromRespiratorySourceData == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, fromRespiratorySourceData);
                }
                String fromRespiratoryBaseUnitsData = RespiratoryRateDataConverter.fromRespiratoryBaseUnitsData(respiratoryRateData.getBaseUnits());
                if (fromRespiratoryBaseUnitsData == null) {
                    supportSQLiteStatement.bindNull(14);
                    return;
                } else {
                    supportSQLiteStatement.bindString(14, fromRespiratoryBaseUnitsData);
                    return;
                }
            }
            supportSQLiteStatement.bindNull(4);
            supportSQLiteStatement.bindNull(5);
            supportSQLiteStatement.bindNull(6);
            supportSQLiteStatement.bindNull(7);
            supportSQLiteStatement.bindNull(8);
            supportSQLiteStatement.bindNull(9);
            supportSQLiteStatement.bindNull(10);
            supportSQLiteStatement.bindNull(11);
            supportSQLiteStatement.bindNull(12);
            supportSQLiteStatement.bindNull(13);
            supportSQLiteStatement.bindNull(14);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `daily_respiratory_rate` (`mDate`,`macAddress`,`isSyncedWithServer`,`date`,`tzOffset`,`computedDate`,`min`,`max`,`avg`,`values`,`baseUnit`,`accuracies`,`source`,`baseUnits`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes6.dex */
    public class b extends SharedSQLiteStatement {
        public b(RespiratoryRateDao_Impl respiratoryRateDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE daily_respiratory_rate SET isSyncedWithServer = 1 WHERE macAddress=? AND mDate=?";
        }
    }

    /* loaded from: classes6.dex */
    public class c implements Callable<DailyRespiratoryRateEntity> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public c(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:44:0x014d A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0010, B:5:0x006a, B:7:0x0070, B:9:0x0076, B:11:0x007c, B:13:0x0082, B:15:0x0088, B:17:0x008e, B:19:0x0094, B:21:0x009a, B:23:0x00a0, B:25:0x00a6, B:42:0x0136, B:44:0x014d, B:46:0x015b, B:45:0x0151, B:29:0x00b0, B:33:0x00da, B:37:0x00ed, B:41:0x0100, B:40:0x00f8, B:36:0x00e5, B:32:0x00d2), top: B:54:0x0010 }] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0151 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0010, B:5:0x006a, B:7:0x0070, B:9:0x0076, B:11:0x007c, B:13:0x0082, B:15:0x0088, B:17:0x008e, B:19:0x0094, B:21:0x009a, B:23:0x00a0, B:25:0x00a6, B:42:0x0136, B:44:0x014d, B:46:0x015b, B:45:0x0151, B:29:0x00b0, B:33:0x00da, B:37:0x00ed, B:41:0x0100, B:40:0x00f8, B:36:0x00e5, B:32:0x00d2), top: B:54:0x0010 }] */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity call() throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 361
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao_Impl.c.call():com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity");
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes6.dex */
    public class d implements Callable<List<DailyRespiratoryRateEntity>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public d(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:45:0x0160 A[Catch: all -> 0x0188, TryCatch #0 {all -> 0x0188, blocks: (B:3:0x0010, B:4:0x006f, B:6:0x0075, B:8:0x007b, B:10:0x0081, B:12:0x0087, B:14:0x008d, B:16:0x0093, B:18:0x0099, B:20:0x009f, B:22:0x00a5, B:24:0x00ab, B:26:0x00b1, B:43:0x0145, B:45:0x0160, B:47:0x0173, B:46:0x0166, B:30:0x00bd, B:34:0x00e9, B:38:0x00fc, B:42:0x010f, B:41:0x0107, B:37:0x00f4, B:33:0x00e1), top: B:54:0x0010 }] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0166 A[Catch: all -> 0x0188, TryCatch #0 {all -> 0x0188, blocks: (B:3:0x0010, B:4:0x006f, B:6:0x0075, B:8:0x007b, B:10:0x0081, B:12:0x0087, B:14:0x008d, B:16:0x0093, B:18:0x0099, B:20:0x009f, B:22:0x00a5, B:24:0x00ab, B:26:0x00b1, B:43:0x0145, B:45:0x0160, B:47:0x0173, B:46:0x0166, B:30:0x00bd, B:34:0x00e9, B:38:0x00fc, B:42:0x010f, B:41:0x0107, B:37:0x00f4, B:33:0x00e1), top: B:54:0x0010 }] */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.util.List<com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity> call() throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 397
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao_Impl.d.call():java.util.List");
        }

        public void finalize() {
            this.h.release();
        }
    }

    /* loaded from: classes6.dex */
    public class e implements Callable<List<WeeklyRespiratoryRateData>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public e(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<WeeklyRespiratoryRateData> call() throws Exception {
            Cursor query = DBUtil.query(RespiratoryRateDao_Impl.this.f5672a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, Constants.PRIORITY_MAX);
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "min");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "avg");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "endDate");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "startDate");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "week");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    WeeklyRespiratoryRateData weeklyRespiratoryRateData = new WeeklyRespiratoryRateData();
                    weeklyRespiratoryRateData.setMacAddress(query.getString(columnIndexOrThrow));
                    weeklyRespiratoryRateData.setMax(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    weeklyRespiratoryRateData.setMin(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    weeklyRespiratoryRateData.setAvg(query.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow4)));
                    weeklyRespiratoryRateData.setEndDate(query.getString(columnIndexOrThrow5));
                    weeklyRespiratoryRateData.setStartDate(query.getString(columnIndexOrThrow6));
                    weeklyRespiratoryRateData.setWeek(query.getString(columnIndexOrThrow7));
                    weeklyRespiratoryRateData.setYear(query.getString(columnIndexOrThrow8));
                    arrayList.add(weeklyRespiratoryRateData);
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

    /* loaded from: classes6.dex */
    public class f implements Callable<List<MonthlyRespiratoryRateData>> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public f(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public List<MonthlyRespiratoryRateData> call() throws Exception {
            Cursor query = DBUtil.query(RespiratoryRateDao_Impl.this.f5672a, this.h, false, null);
            try {
                int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, DeviceKey.MacAddress);
                int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, Constants.PRIORITY_MAX);
                int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "min");
                int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "avg");
                int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "endDate");
                int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "startDate");
                int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "month");
                int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "year");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MonthlyRespiratoryRateData monthlyRespiratoryRateData = new MonthlyRespiratoryRateData();
                    monthlyRespiratoryRateData.setMacAddress(query.getString(columnIndexOrThrow));
                    monthlyRespiratoryRateData.setMax(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                    monthlyRespiratoryRateData.setMin(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                    monthlyRespiratoryRateData.setAvg(query.isNull(columnIndexOrThrow4) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow4)));
                    monthlyRespiratoryRateData.setEndDate(query.getString(columnIndexOrThrow5));
                    monthlyRespiratoryRateData.setStartDate(query.getString(columnIndexOrThrow6));
                    monthlyRespiratoryRateData.setMonth(query.getString(columnIndexOrThrow7));
                    monthlyRespiratoryRateData.setYear(query.getString(columnIndexOrThrow8));
                    arrayList.add(monthlyRespiratoryRateData);
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

    /* loaded from: classes6.dex */
    public class g implements Callable<DailyRespiratoryRateEntity> {
        public final /* synthetic */ RoomSQLiteQuery h;

        public g(RoomSQLiteQuery roomSQLiteQuery) {
            this.h = roomSQLiteQuery;
        }

        /* JADX WARN: Removed duplicated region for block: B:44:0x014d A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0010, B:5:0x006a, B:7:0x0070, B:9:0x0076, B:11:0x007c, B:13:0x0082, B:15:0x0088, B:17:0x008e, B:19:0x0094, B:21:0x009a, B:23:0x00a0, B:25:0x00a6, B:42:0x0136, B:44:0x014d, B:46:0x015b, B:45:0x0151, B:29:0x00b0, B:33:0x00da, B:37:0x00ed, B:41:0x0100, B:40:0x00f8, B:36:0x00e5, B:32:0x00d2), top: B:54:0x0010 }] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0151 A[Catch: all -> 0x0164, TryCatch #0 {all -> 0x0164, blocks: (B:3:0x0010, B:5:0x006a, B:7:0x0070, B:9:0x0076, B:11:0x007c, B:13:0x0082, B:15:0x0088, B:17:0x008e, B:19:0x0094, B:21:0x009a, B:23:0x00a0, B:25:0x00a6, B:42:0x0136, B:44:0x014d, B:46:0x015b, B:45:0x0151, B:29:0x00b0, B:33:0x00da, B:37:0x00ed, B:41:0x0100, B:40:0x00f8, B:36:0x00e5, B:32:0x00d2), top: B:54:0x0010 }] */
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity call() throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 361
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao_Impl.g.call():com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity");
        }

        public void finalize() {
            this.h.release();
        }
    }

    public RespiratoryRateDao_Impl(RoomDatabase roomDatabase) {
        this.f5672a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public List<RespiratoryRateListBean> getDailyRespiratoryRateDateBetweenDates(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date as date, min as min, max as max  from daily_respiratory_rate where macAddress=? AND date between (?) and (?) AND min is not null AND min > 0 AND max is not null AND max > 0 ORDER BY date DESC", 3);
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
        this.f5672a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5672a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "min");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, Constants.PRIORITY_MAX);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                RespiratoryRateListBean respiratoryRateListBean = new RespiratoryRateListBean();
                respiratoryRateListBean.setDate(query.getString(columnIndexOrThrow));
                respiratoryRateListBean.setMin(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                respiratoryRateListBean.setMax(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                arrayList.add(respiratoryRateListBean);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public List<RespiratoryRateListBean> getDailyRespiratoryRateRangeBetweenDates(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT date as date, min as min, max as max  from daily_respiratory_rate where macAddress=? AND date between (?) and (?) AND min is not null AND min > 0 AND max is not null AND max > 0 ORDER BY date ASC", 3);
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
        this.f5672a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5672a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "date");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "min");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, Constants.PRIORITY_MAX);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                RespiratoryRateListBean respiratoryRateListBean = new RespiratoryRateListBean();
                respiratoryRateListBean.setDate(query.getString(columnIndexOrThrow));
                respiratoryRateListBean.setMin(query.isNull(columnIndexOrThrow2) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow2)));
                respiratoryRateListBean.setMax(query.isNull(columnIndexOrThrow3) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow3)));
                arrayList.add(respiratoryRateListBean);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public String getLastSyncedDate(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT mDate from daily_respiratory_rate WHERE macAddress=? ORDER BY mDate DESC limit 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5672a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5672a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getString(0) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public LiveData<DailyRespiratoryRateEntity> getLatestRespiratoryRateData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from daily_respiratory_rate where macAddress=? ORDER BY mDate DESC LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f5672a.getInvalidationTracker().createLiveData(new String[]{"daily_respiratory_rate"}, false, new g(acquire));
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0173 A[Catch: all -> 0x019e, TryCatch #1 {all -> 0x019e, blocks: (B:9:0x0077, B:10:0x0082, B:12:0x0088, B:14:0x008e, B:16:0x0094, B:18:0x009a, B:20:0x00a0, B:22:0x00a6, B:24:0x00ac, B:26:0x00b2, B:28:0x00b8, B:30:0x00be, B:32:0x00c4, B:49:0x0158, B:51:0x0173, B:53:0x0186, B:52:0x0179, B:36:0x00d0, B:40:0x00fc, B:44:0x010f, B:48:0x0122, B:47:0x011a, B:43:0x0107, B:39:0x00f4), top: B:65:0x0077 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0179 A[Catch: all -> 0x019e, TryCatch #1 {all -> 0x019e, blocks: (B:9:0x0077, B:10:0x0082, B:12:0x0088, B:14:0x008e, B:16:0x0094, B:18:0x009a, B:20:0x00a0, B:22:0x00a6, B:24:0x00ac, B:26:0x00b2, B:28:0x00b8, B:30:0x00be, B:32:0x00c4, B:49:0x0158, B:51:0x0173, B:53:0x0186, B:52:0x0179, B:36:0x00d0, B:40:0x00fc, B:44:0x010f, B:48:0x0122, B:47:0x011a, B:43:0x0107, B:39:0x00f4), top: B:65:0x0077 }] */
    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity> getListOfUnSyncedRespiratoryRate(java.lang.String r22) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao_Impl.getListOfUnSyncedRespiratoryRate(java.lang.String):java.util.List");
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public LiveData<List<DailyRespiratoryRateEntity>> getLiveDayWiseRespiratoryRateData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from daily_respiratory_rate where macAddress=? AND min is not null AND min > 0 AND max is not null AND max > 0", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f5672a.getInvalidationTracker().createLiveData(new String[]{"daily_respiratory_rate"}, false, new d(acquire));
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public LiveData<List<MonthlyRespiratoryRateData>> getLiveMonthWiseRespiratoryRateData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT macAddress as macAddress,MAX((case when max>0 then max END)) as max, MIN((case when min>0 then min END)) as min, AVG((case when avg>0 then avg END)) as avg, MAX(mDate) as endDate, MIN(mDate) as startDate, strftime('%m',date) as month,strftime('%Y',date) as year from daily_respiratory_rate WHERE macAddress=?  group by month,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f5672a.getInvalidationTracker().createLiveData(new String[]{"daily_respiratory_rate"}, false, new f(acquire));
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public LiveData<List<WeeklyRespiratoryRateData>> getLiveWeekWiseRespiratoryRateData(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT macAddress as macAddress,MAX((case when max>0 then max END)) as max, MIN((case when min>0 then min END)) as min, AVG((case when avg>0 then avg END)) as avg, MAX(mDate) as endDate, MIN(mDate) as startDate,strftime('%W',date)+1 as week,strftime('%Y',date) as year from daily_respiratory_rate  WHERE macAddress=?  group by week,year", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.f5672a.getInvalidationTracker().createLiveData(new String[]{"daily_respiratory_rate"}, false, new e(acquire));
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x016e A[Catch: all -> 0x0180, TryCatch #0 {all -> 0x0180, blocks: (B:50:0x0157, B:52:0x016e, B:54:0x017c, B:53:0x0172, B:37:0x00d1, B:41:0x00fb, B:45:0x010e, B:49:0x0121, B:48:0x0119, B:44:0x0106, B:40:0x00f3), top: B:65:0x00d1 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0172 A[Catch: all -> 0x0180, TryCatch #0 {all -> 0x0180, blocks: (B:50:0x0157, B:52:0x016e, B:54:0x017c, B:53:0x0172, B:37:0x00d1, B:41:0x00fb, B:45:0x010e, B:49:0x0121, B:48:0x0119, B:44:0x0106, B:40:0x00f3), top: B:65:0x00d1 }] */
    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity getRespiratoryRate(java.lang.String r18, java.lang.String r19) {
        /*
            Method dump skipped, instructions count: 407
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao_Impl.getRespiratoryRate(java.lang.String, java.lang.String):com.coveiot.android.respiratoryrate.database.entities.DailyRespiratoryRateEntity");
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public LiveData<DailyRespiratoryRateEntity> getRespiratoryRateLiveData(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT *  from daily_respiratory_rate where macAddress=? AND mDate=? ", 2);
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
        return this.f5672a.getInvalidationTracker().createLiveData(new String[]{"daily_respiratory_rate"}, false, new c(acquire));
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public int getRowCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) from daily_respiratory_rate where macAddress=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f5672a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f5672a, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public long insert(DailyRespiratoryRateEntity dailyRespiratoryRateEntity) {
        this.f5672a.assertNotSuspendingTransaction();
        this.f5672a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(dailyRespiratoryRateEntity);
            this.f5672a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.f5672a.endTransaction();
        }
    }

    @Override // com.coveiot.android.respiratoryrate.database.dao.RespiratoryRateDao
    public void updateRespiratoryRateToServer(String str, String str2) {
        this.f5672a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
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
        this.f5672a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f5672a.setTransactionSuccessful();
        } finally {
            this.f5672a.endTransaction();
            this.c.release(acquire);
        }
    }
}
