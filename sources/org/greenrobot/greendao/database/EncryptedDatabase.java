package org.greenrobot.greendao.database;

import android.database.Cursor;
import android.database.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
/* loaded from: classes13.dex */
public class EncryptedDatabase implements Database {

    /* renamed from: a  reason: collision with root package name */
    public final SQLiteDatabase f15473a;

    public EncryptedDatabase(SQLiteDatabase sQLiteDatabase) {
        this.f15473a = sQLiteDatabase;
    }

    @Override // org.greenrobot.greendao.database.Database
    public void beginTransaction() {
        this.f15473a.beginTransaction();
    }

    @Override // org.greenrobot.greendao.database.Database
    public void close() {
        this.f15473a.close();
    }

    @Override // org.greenrobot.greendao.database.Database
    public DatabaseStatement compileStatement(String str) {
        return new EncryptedDatabaseStatement(this.f15473a.compileStatement(str));
    }

    @Override // org.greenrobot.greendao.database.Database
    public void endTransaction() {
        this.f15473a.endTransaction();
    }

    @Override // org.greenrobot.greendao.database.Database
    public void execSQL(String str) throws SQLException {
        this.f15473a.execSQL(str);
    }

    @Override // org.greenrobot.greendao.database.Database
    public Object getRawDatabase() {
        return this.f15473a;
    }

    public SQLiteDatabase getSQLiteDatabase() {
        return this.f15473a;
    }

    @Override // org.greenrobot.greendao.database.Database
    public boolean inTransaction() {
        return this.f15473a.inTransaction();
    }

    @Override // org.greenrobot.greendao.database.Database
    public boolean isDbLockedByCurrentThread() {
        return this.f15473a.isDbLockedByCurrentThread();
    }

    @Override // org.greenrobot.greendao.database.Database
    public boolean isOpen() {
        return this.f15473a.isOpen();
    }

    @Override // org.greenrobot.greendao.database.Database
    public Cursor rawQuery(String str, String[] strArr) {
        return this.f15473a.rawQuery(str, strArr);
    }

    @Override // org.greenrobot.greendao.database.Database
    public void setTransactionSuccessful() {
        this.f15473a.setTransactionSuccessful();
    }

    @Override // org.greenrobot.greendao.database.Database
    public void execSQL(String str, Object[] objArr) throws SQLException {
        this.f15473a.execSQL(str, objArr);
    }
}
