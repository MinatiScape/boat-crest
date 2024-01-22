package org.greenrobot.greendao.database;

import android.database.sqlite.SQLiteStatement;
/* loaded from: classes13.dex */
public class StandardDatabaseStatement implements DatabaseStatement {

    /* renamed from: a  reason: collision with root package name */
    public final SQLiteStatement f15476a;

    public StandardDatabaseStatement(SQLiteStatement sQLiteStatement) {
        this.f15476a = sQLiteStatement;
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindBlob(int i, byte[] bArr) {
        this.f15476a.bindBlob(i, bArr);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindDouble(int i, double d) {
        this.f15476a.bindDouble(i, d);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindLong(int i, long j) {
        this.f15476a.bindLong(i, j);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindNull(int i) {
        this.f15476a.bindNull(i);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindString(int i, String str) {
        this.f15476a.bindString(i, str);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void clearBindings() {
        this.f15476a.clearBindings();
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void close() {
        this.f15476a.close();
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void execute() {
        this.f15476a.execute();
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public long executeInsert() {
        return this.f15476a.executeInsert();
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public Object getRawStatement() {
        return this.f15476a;
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public long simpleQueryForLong() {
        return this.f15476a.simpleQueryForLong();
    }
}
