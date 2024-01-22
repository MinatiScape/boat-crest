package com.coveiot.khsmadb.activity;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public final class KhActivityDao_Impl implements KhActivityDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f7136a;
    public final EntityInsertionAdapter<KhBleActivity> b;
    public final SharedSQLiteStatement c;
    public final SharedSQLiteStatement d;
    public final SharedSQLiteStatement e;
    public final SharedSQLiteStatement f;
    public final SharedSQLiteStatement g;
    public final SharedSQLiteStatement h;
    public final SharedSQLiteStatement i;
    public final SharedSQLiteStatement j;
    public final SharedSQLiteStatement k;

    /* loaded from: classes8.dex */
    public class a extends SharedSQLiteStatement {
        public a(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleActivity SET isProcessed = 0 WHERE mMacAddress=? AND mTime>=?";
        }
    }

    /* loaded from: classes8.dex */
    public class b extends EntityInsertionAdapter<KhBleActivity> {
        public b(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, KhBleActivity khBleActivity) {
            supportSQLiteStatement.bindLong(1, khBleActivity.getMTime());
            supportSQLiteStatement.bindLong(2, khBleActivity.getMMode());
            supportSQLiteStatement.bindLong(3, khBleActivity.getMState());
            supportSQLiteStatement.bindLong(4, khBleActivity.getMStep());
            supportSQLiteStatement.bindLong(5, khBleActivity.getMCalorie());
            supportSQLiteStatement.bindLong(6, khBleActivity.getMDistance());
            if (khBleActivity.getMMacAddress() == null) {
                supportSQLiteStatement.bindNull(7);
            } else {
                supportSQLiteStatement.bindString(7, khBleActivity.getMMacAddress());
            }
            supportSQLiteStatement.bindLong(8, khBleActivity.isReadyToProcess() ? 1L : 0L);
            supportSQLiteStatement.bindLong(9, khBleActivity.isProcessed() ? 1L : 0L);
            supportSQLiteStatement.bindLong(10, khBleActivity.isProcessedInWorkout() ? 1L : 0L);
            if (khBleActivity.getTimeStamp() == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, khBleActivity.getTimeStamp());
            }
            if (khBleActivity.getDate() == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, khBleActivity.getDate());
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `KhBleActivity` (`mTime`,`mMode`,`mState`,`mStep`,`mCalorie`,`mDistance`,`mMacAddress`,`isReadyToProcess`,`isProcessed`,`isProcessedInWorkout`,`Timestamp`,`date`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* loaded from: classes8.dex */
    public class c extends SharedSQLiteStatement {
        public c(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleActivity SET isProcessed = 1 WHERE mMacAddress=? AND mTime<? AND isProcessed==0";
        }
    }

    /* loaded from: classes8.dex */
    public class d extends SharedSQLiteStatement {
        public d(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleActivity SET isProcessedInWorkout = 1 WHERE mMacAddress=? AND mMode!=0 AND mTime<? AND isProcessedInWorkout==0 AND isReadyToProcess==1";
        }
    }

    /* loaded from: classes8.dex */
    public class e extends SharedSQLiteStatement {
        public e(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from KhBleActivity where mMacAddress=?";
        }
    }

    /* loaded from: classes8.dex */
    public class f extends SharedSQLiteStatement {
        public f(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from KhBleActivity where mMacAddress=? AND mTime<?";
        }
    }

    /* loaded from: classes8.dex */
    public class g extends SharedSQLiteStatement {
        public g(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from KhBleActivity where mMacAddress=? AND isProcessed==1 AND mMode==0";
        }
    }

    /* loaded from: classes8.dex */
    public class h extends SharedSQLiteStatement {
        public h(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from KhBleActivity where mMacAddress=? AND isProcessedInWorkout==1 AND mMode!=0";
        }
    }

    /* loaded from: classes8.dex */
    public class i extends SharedSQLiteStatement {
        public i(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE KhBleActivity SET isReadyToProcess=1 where mMacAddress=? and mMode!=0 and mTime between ? and ?";
        }
    }

    /* loaded from: classes8.dex */
    public class j extends SharedSQLiteStatement {
        public j(KhActivityDao_Impl khActivityDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from KhBleActivity where mMacAddress=? AND date=?";
        }
    }

    public KhActivityDao_Impl(RoomDatabase roomDatabase) {
        this.f7136a = roomDatabase;
        this.b = new b(this, roomDatabase);
        this.c = new c(this, roomDatabase);
        this.d = new d(this, roomDatabase);
        this.e = new e(this, roomDatabase);
        this.f = new f(this, roomDatabase);
        this.g = new g(this, roomDatabase);
        this.h = new h(this, roomDatabase);
        this.i = new i(this, roomDatabase);
        this.j = new j(this, roomDatabase);
        this.k = new a(this, roomDatabase);
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void deleteActivityInfoForDate(String str, String str2) {
        this.f7136a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.j.acquire();
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
        this.f7136a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
            this.j.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void deleteAllActivityInfo(String str) {
        this.f7136a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.e.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7136a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
            this.e.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void deleteAllActivityInfoFor(String str, int i2) {
        this.f7136a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        this.f7136a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
            this.f.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void deleteAllProcessedActivityInfo(String str) {
        this.f7136a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.g.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7136a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
            this.g.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void deleteAllProcessedWorkoutActivityInfo(String str) {
        this.f7136a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.h.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7136a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
            this.h.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public List<KhBleActivity> getActivityInfo(String str, boolean z) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from KhBleActivity where mMacAddress=? and mMode==0 and isProcessed=? ORDER BY mTime ASC", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, z ? 1L : 0L);
        this.f7136a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7136a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mState");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mStep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "mCalorie");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "mDistance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isReadyToProcess");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "isProcessedInWorkout");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "date");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleActivity khBleActivity = new KhBleActivity(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.getString(columnIndexOrThrow7));
                int i2 = columnIndexOrThrow;
                khBleActivity.setReadyToProcess(query.getInt(columnIndexOrThrow8) != 0);
                khBleActivity.setProcessed(query.getInt(columnIndexOrThrow9) != 0);
                khBleActivity.setProcessedInWorkout(query.getInt(columnIndexOrThrow10) != 0);
                khBleActivity.setTimeStamp(query.getString(columnIndexOrThrow11));
                khBleActivity.setDate(query.getString(columnIndexOrThrow12));
                arrayList.add(khBleActivity);
                columnIndexOrThrow = i2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public List<KhBleActivity> getActivityListBetweenTime(String str, int i2, int i3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from khbleactivity where mMacAddress=? and mTime between ? and ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        acquire.bindLong(3, i3);
        this.f7136a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7136a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mState");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mStep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "mCalorie");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "mDistance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isReadyToProcess");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "isProcessedInWorkout");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "date");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleActivity khBleActivity = new KhBleActivity(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.getString(columnIndexOrThrow7));
                int i4 = columnIndexOrThrow;
                khBleActivity.setReadyToProcess(query.getInt(columnIndexOrThrow8) != 0);
                khBleActivity.setProcessed(query.getInt(columnIndexOrThrow9) != 0);
                khBleActivity.setProcessedInWorkout(query.getInt(columnIndexOrThrow10) != 0);
                khBleActivity.setTimeStamp(query.getString(columnIndexOrThrow11));
                khBleActivity.setDate(query.getString(columnIndexOrThrow12));
                arrayList.add(khBleActivity);
                columnIndexOrThrow = i4;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public Integer getMaxStepRecordByDate(String str, String str2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT MAX(mStep) FROM KhBleActivity WHERE mMacAddress=? AND date=?", 2);
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
        this.f7136a.assertNotSuspendingTransaction();
        Integer num = null;
        Cursor query = DBUtil.query(this.f7136a, acquire, false, null);
        try {
            if (query.moveToFirst() && !query.isNull(0)) {
                num = Integer.valueOf(query.getInt(0));
            }
            return num;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public List<KhBleActivity> getOrderedActivityListBetweenTime(String str, int i2, int i3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from khbleactivity where mMacAddress=? and mTime between ? and ? ORDER BY mTime", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        acquire.bindLong(3, i3);
        this.f7136a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7136a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mState");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mStep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "mCalorie");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "mDistance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isReadyToProcess");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "isProcessedInWorkout");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "date");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleActivity khBleActivity = new KhBleActivity(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.getString(columnIndexOrThrow7));
                int i4 = columnIndexOrThrow;
                khBleActivity.setReadyToProcess(query.getInt(columnIndexOrThrow8) != 0);
                khBleActivity.setProcessed(query.getInt(columnIndexOrThrow9) != 0);
                khBleActivity.setProcessedInWorkout(query.getInt(columnIndexOrThrow10) != 0);
                khBleActivity.setTimeStamp(query.getString(columnIndexOrThrow11));
                khBleActivity.setDate(query.getString(columnIndexOrThrow12));
                arrayList.add(khBleActivity);
                columnIndexOrThrow = i4;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public List<KhBleActivity> getUniqueDayInfo(String str) {
        int i2;
        boolean z;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM KhBleActivity WHERE mMacAddress=? GROUP BY date ORDER BY mTime DESC", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f7136a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7136a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mState");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mStep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "mCalorie");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "mDistance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isReadyToProcess");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "isProcessedInWorkout");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "date");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleActivity khBleActivity = new KhBleActivity(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.getString(columnIndexOrThrow7));
                if (query.getInt(columnIndexOrThrow8) != 0) {
                    i2 = columnIndexOrThrow;
                    z = true;
                } else {
                    i2 = columnIndexOrThrow;
                    z = false;
                }
                khBleActivity.setReadyToProcess(z);
                khBleActivity.setProcessed(query.getInt(columnIndexOrThrow9) != 0);
                khBleActivity.setProcessedInWorkout(query.getInt(columnIndexOrThrow10) != 0);
                khBleActivity.setTimeStamp(query.getString(columnIndexOrThrow11));
                khBleActivity.setDate(query.getString(columnIndexOrThrow12));
                arrayList.add(khBleActivity);
                columnIndexOrThrow = i2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public List<KhBleActivity> getWorkoutActivityInfo(String str, boolean z) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM KhBleActivity WHERE mMacAddress=? AND mMode!=0 AND isProcessedInWorkout=? ORDER BY mTime", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, z ? 1L : 0L);
        this.f7136a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f7136a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mTime");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mMode");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mState");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "mStep");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "mCalorie");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "mDistance");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "mMacAddress");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "isReadyToProcess");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "isProcessed");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "isProcessedInWorkout");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "Timestamp");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "date");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KhBleActivity khBleActivity = new KhBleActivity(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6), query.getString(columnIndexOrThrow7));
                int i2 = columnIndexOrThrow;
                khBleActivity.setReadyToProcess(query.getInt(columnIndexOrThrow8) != 0);
                khBleActivity.setProcessed(query.getInt(columnIndexOrThrow9) != 0);
                khBleActivity.setProcessedInWorkout(query.getInt(columnIndexOrThrow10) != 0);
                khBleActivity.setTimeStamp(query.getString(columnIndexOrThrow11));
                khBleActivity.setDate(query.getString(columnIndexOrThrow12));
                arrayList.add(khBleActivity);
                columnIndexOrThrow = i2;
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void insert(KhBleActivity khBleActivity) {
        this.f7136a.assertNotSuspendingTransaction();
        this.f7136a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<KhBleActivity>) khBleActivity);
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void insertAll(List<KhBleActivity> list) {
        this.f7136a.assertNotSuspendingTransaction();
        this.f7136a.beginTransaction();
        try {
            this.b.insert(list);
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void setWorkoutReadyToProcessBetweenTime(String str, int i2, int i3) {
        this.f7136a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.i.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        acquire.bindLong(3, i3);
        this.f7136a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
            this.i.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void updateActivityDataProcessedBefore(String str, long j2) {
        this.f7136a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j2);
        this.f7136a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
            this.c.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void updateActivityDataUnProcessedAfter(String str, int i2) {
        this.f7136a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.k.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i2);
        this.f7136a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
            this.k.release(acquire);
        }
    }

    @Override // com.coveiot.khsmadb.activity.KhActivityDao
    public void updateWorkoutActivityDataProcessedBefore(String str, long j2) {
        this.f7136a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j2);
        this.f7136a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f7136a.setTransactionSuccessful();
        } finally {
            this.f7136a.endTransaction();
            this.d.release(acquire);
        }
    }
}
