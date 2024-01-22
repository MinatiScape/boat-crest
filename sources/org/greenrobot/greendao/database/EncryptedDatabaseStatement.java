package org.greenrobot.greendao.database;

import net.sqlcipher.database.SQLiteStatement;
/* loaded from: classes13.dex */
public class EncryptedDatabaseStatement implements DatabaseStatement {

    /* renamed from: a  reason: collision with root package name */
    public final SQLiteStatement f15474a;

    public EncryptedDatabaseStatement(SQLiteStatement sQLiteStatement) {
        this.f15474a = sQLiteStatement;
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindBlob(int i, byte[] bArr) {
        this.f15474a.bindBlob(i, bArr);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindDouble(int i, double d) {
        this.f15474a.bindDouble(i, d);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindLong(int i, long j) {
        this.f15474a.bindLong(i, j);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindNull(int i) {
        this.f15474a.bindNull(i);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void bindString(int i, String str) {
        this.f15474a.bindString(i, str);
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void clearBindings() {
        this.f15474a.clearBindings();
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void close() {
        this.f15474a.close();
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public void execute() {
        this.f15474a.execute();
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public long executeInsert() {
        return this.f15474a.executeInsert();
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public Object getRawStatement() {
        return this.f15474a;
    }

    @Override // org.greenrobot.greendao.database.DatabaseStatement
    public long simpleQueryForLong() {
        return this.f15474a.simpleQueryForLong();
    }
}
