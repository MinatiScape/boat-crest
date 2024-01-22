package androidx.sqlite.db.framework;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
/* loaded from: classes.dex */
public class b implements SupportSQLiteOpenHelper {
    public final Context h;
    public final String i;
    public final SupportSQLiteOpenHelper.Callback j;
    public final boolean k;
    public final Object l = new Object();
    public a m;
    public boolean n;

    /* loaded from: classes.dex */
    public static class a extends SQLiteOpenHelper {
        public final androidx.sqlite.db.framework.a[] h;
        public final SupportSQLiteOpenHelper.Callback i;
        public boolean j;

        /* renamed from: androidx.sqlite.db.framework.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class C0183a implements DatabaseErrorHandler {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ SupportSQLiteOpenHelper.Callback f1687a;
            public final /* synthetic */ androidx.sqlite.db.framework.a[] b;

            public C0183a(SupportSQLiteOpenHelper.Callback callback, androidx.sqlite.db.framework.a[] aVarArr) {
                this.f1687a = callback;
                this.b = aVarArr;
            }

            @Override // android.database.DatabaseErrorHandler
            public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                this.f1687a.onCorruption(a.c(this.b, sQLiteDatabase));
            }
        }

        public a(Context context, String str, androidx.sqlite.db.framework.a[] aVarArr, SupportSQLiteOpenHelper.Callback callback) {
            super(context, str, null, callback.version, new C0183a(callback, aVarArr));
            this.i = callback;
            this.h = aVarArr;
        }

        public static androidx.sqlite.db.framework.a c(androidx.sqlite.db.framework.a[] aVarArr, SQLiteDatabase sQLiteDatabase) {
            androidx.sqlite.db.framework.a aVar = aVarArr[0];
            if (aVar == null || !aVar.a(sQLiteDatabase)) {
                aVarArr[0] = new androidx.sqlite.db.framework.a(sQLiteDatabase);
            }
            return aVarArr[0];
        }

        public synchronized SupportSQLiteDatabase a() {
            this.j = false;
            SQLiteDatabase readableDatabase = super.getReadableDatabase();
            if (this.j) {
                close();
                return a();
            }
            return b(readableDatabase);
        }

        public androidx.sqlite.db.framework.a b(SQLiteDatabase sQLiteDatabase) {
            return c(this.h, sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
        public synchronized void close() {
            super.close();
            this.h[0] = null;
        }

        public synchronized SupportSQLiteDatabase d() {
            this.j = false;
            SQLiteDatabase writableDatabase = super.getWritableDatabase();
            if (this.j) {
                close();
                return d();
            }
            return b(writableDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            this.i.onConfigure(b(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            this.i.onCreate(b(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.j = true;
            this.i.onDowngrade(b(sQLiteDatabase), i, i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (this.j) {
                return;
            }
            this.i.onOpen(b(sQLiteDatabase));
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.j = true;
            this.i.onUpgrade(b(sQLiteDatabase), i, i2);
        }
    }

    public b(Context context, String str, SupportSQLiteOpenHelper.Callback callback, boolean z) {
        this.h = context;
        this.i = str;
        this.j = callback;
        this.k = z;
    }

    public final a a() {
        a aVar;
        synchronized (this.l) {
            if (this.m == null) {
                androidx.sqlite.db.framework.a[] aVarArr = new androidx.sqlite.db.framework.a[1];
                int i = Build.VERSION.SDK_INT;
                if (i >= 23 && this.i != null && this.k) {
                    this.m = new a(this.h, new File(this.h.getNoBackupFilesDir(), this.i).getAbsolutePath(), aVarArr, this.j);
                } else {
                    this.m = new a(this.h, this.i, aVarArr, this.j);
                }
                if (i >= 16) {
                    this.m.setWriteAheadLoggingEnabled(this.n);
                }
            }
            aVar = this.m;
        }
        return aVar;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a().close();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.i;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getReadableDatabase() {
        return a().a();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        return a().d();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    @RequiresApi(api = 16)
    public void setWriteAheadLoggingEnabled(boolean z) {
        synchronized (this.l) {
            a aVar = this.m;
            if (aVar != null) {
                aVar.setWriteAheadLoggingEnabled(z);
            }
            this.n = z;
        }
    }
}
