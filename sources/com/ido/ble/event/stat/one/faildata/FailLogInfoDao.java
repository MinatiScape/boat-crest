package com.ido.ble.event.stat.one.faildata;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.ido.ble.data.manage.database.DaoSession;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
/* loaded from: classes11.dex */
public class FailLogInfoDao extends AbstractDao<c, Long> {
    public static final String TABLENAME = "FAIL_LOG_INFO";

    /* loaded from: classes11.dex */
    public static class Properties {

        /* renamed from: a  reason: collision with root package name */
        public static final Property f12221a = new Property(0, Long.class, "id", true, "_id");
        public static final Property b = new Property(1, String.class, ProductAction.ACTION_DETAIL, false, "DETAIL");
        public static final Property c = new Property(2, String.class, NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, false, "TIME");
    }

    public FailLogInfoDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public FailLogInfoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void a(Database database, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        database.execSQL("CREATE TABLE " + str + "\"FAIL_LOG_INFO\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"DETAIL\" TEXT,\"TIME\" TEXT UNIQUE );");
    }

    public static void b(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"FAIL_LOG_INFO\"");
        database.execSQL(sb.toString());
    }

    @Override // org.greenrobot.greendao.AbstractDao
    /* renamed from: a */
    public Long getKey(c cVar) {
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    /* renamed from: a */
    public final Long updateKeyAfterInsert(c cVar, long j) {
        cVar.a(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    /* renamed from: a */
    public void readEntity(Cursor cursor, c cVar, int i) {
        int i2 = i + 0;
        cVar.a(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        cVar.a(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        cVar.b(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    /* renamed from: a */
    public final void bindValues(SQLiteStatement sQLiteStatement, c cVar) {
        sQLiteStatement.clearBindings();
        Long b = cVar.b();
        if (b != null) {
            sQLiteStatement.bindLong(1, b.longValue());
        }
        String a2 = cVar.a();
        if (a2 != null) {
            sQLiteStatement.bindString(2, a2);
        }
        String c = cVar.c();
        if (c != null) {
            sQLiteStatement.bindString(3, c);
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    /* renamed from: a */
    public final void bindValues(DatabaseStatement databaseStatement, c cVar) {
        databaseStatement.clearBindings();
        Long b = cVar.b();
        if (b != null) {
            databaseStatement.bindLong(1, b.longValue());
        }
        String a2 = cVar.a();
        if (a2 != null) {
            databaseStatement.bindString(2, a2);
        }
        String c = cVar.c();
        if (c != null) {
            databaseStatement.bindString(3, c);
        }
    }

    @Override // org.greenrobot.greendao.AbstractDao
    /* renamed from: b */
    public boolean hasKey(c cVar) {
        return cVar.b() != null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public final boolean isEntityUpdateable() {
        return true;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public c readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        return new c(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }
}
