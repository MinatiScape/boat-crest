package org.greenrobot.greendao.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/* loaded from: classes13.dex */
public class StandardDatabase implements Database {

    /* renamed from: a  reason: collision with root package name */
    public final SQLiteDatabase f15475a;

    public StandardDatabase(SQLiteDatabase sQLiteDatabase) {
        this.f15475a = sQLiteDatabase;
    }

    @Override // org.greenrobot.greendao.database.Database
    public void beginTransaction() {
        this.f15475a.beginTransaction();
    }

    @Override // org.greenrobot.greendao.database.Database
    public void close() {
        this.f15475a.close();
    }

    @Override // org.greenrobot.greendao.database.Database
    public DatabaseStatement compileStatement(String str) {
        return new StandardDatabaseStatement(this.f15475a.compileStatement(str));
    }

    @Override // org.greenrobot.greendao.database.Database
    public void endTransaction() {
        this.f15475a.endTransaction();
    }

    @Override // org.greenrobot.greendao.database.Database
    public void execSQL(String str) throws SQLException {
        this.f15475a.execSQL(str);
    }

    @Override // org.greenrobot.greendao.database.Database
    public Object getRawDatabase() {
        return this.f15475a;
    }

    public SQLiteDatabase getSQLiteDatabase() {
        return this.f15475a;
    }

    @Override // org.greenrobot.greendao.database.Database
    public boolean inTransaction() {
        return this.f15475a.inTransaction();
    }

    @Override // org.greenrobot.greendao.database.Database
    public boolean isDbLockedByCurrentThread() {
        return this.f15475a.isDbLockedByCurrentThread();
    }

    @Override // org.greenrobot.greendao.database.Database
    public boolean isOpen() {
        return this.f15475a.isOpen();
    }

    @Override // org.greenrobot.greendao.database.Database
    public Cursor rawQuery(String str, String[] strArr) {
        return this.f15475a.rawQuery(str, strArr);
    }

    @Override // org.greenrobot.greendao.database.Database
    public void setTransactionSuccessful() {
        this.f15475a.setTransactionSuccessful();
    }

    @Override // org.greenrobot.greendao.database.Database
    public void execSQL(String str, Object[] objArr) throws SQLException {
        this.f15475a.execSQL(str, objArr);
    }
}
