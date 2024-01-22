package androidx.work.impl.model;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.work.Data;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class WorkProgressDao_Impl implements WorkProgressDao {

    /* renamed from: a  reason: collision with root package name */
    public final RoomDatabase f1825a;
    public final EntityInsertionAdapter<WorkProgress> b;
    public final SharedSQLiteStatement c;
    public final SharedSQLiteStatement d;

    /* loaded from: classes.dex */
    public class a extends EntityInsertionAdapter<WorkProgress> {
        public a(WorkProgressDao_Impl workProgressDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, WorkProgress workProgress) {
            String str = workProgress.mWorkSpecId;
            if (str == null) {
                supportSQLiteStatement.bindNull(1);
            } else {
                supportSQLiteStatement.bindString(1, str);
            }
            byte[] byteArrayInternal = Data.toByteArrayInternal(workProgress.mProgress);
            if (byteArrayInternal == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindBlob(2, byteArrayInternal);
            }
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `WorkProgress` (`work_spec_id`,`progress`) VALUES (?,?)";
        }
    }

    /* loaded from: classes.dex */
    public class b extends SharedSQLiteStatement {
        public b(WorkProgressDao_Impl workProgressDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE from WorkProgress where work_spec_id=?";
        }
    }

    /* loaded from: classes.dex */
    public class c extends SharedSQLiteStatement {
        public c(WorkProgressDao_Impl workProgressDao_Impl, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM WorkProgress";
        }
    }

    public WorkProgressDao_Impl(RoomDatabase roomDatabase) {
        this.f1825a = roomDatabase;
        this.b = new a(this, roomDatabase);
        this.c = new b(this, roomDatabase);
        this.d = new c(this, roomDatabase);
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void delete(String str) {
        this.f1825a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f1825a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f1825a.setTransactionSuccessful();
        } finally {
            this.f1825a.endTransaction();
            this.c.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void deleteAll() {
        this.f1825a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        this.f1825a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f1825a.setTransactionSuccessful();
        } finally {
            this.f1825a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public Data getProgressForWorkSpecId(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT progress FROM WorkProgress WHERE work_spec_id=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f1825a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f1825a, acquire, false, null);
        try {
            return query.moveToFirst() ? Data.fromByteArray(query.getBlob(0)) : null;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public List<Data> getProgressForWorkSpecIds(List<String> list) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT progress FROM WorkProgress WHERE work_spec_id IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 0);
        int i = 1;
        for (String str : list) {
            if (str == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindString(i, str);
            }
            i++;
        }
        this.f1825a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f1825a, acquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(Data.fromByteArray(query.getBlob(0)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkProgressDao
    public void insert(WorkProgress workProgress) {
        this.f1825a.assertNotSuspendingTransaction();
        this.f1825a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<WorkProgress>) workProgress);
            this.f1825a.setTransactionSuccessful();
        } finally {
            this.f1825a.endTransaction();
        }
    }
}
