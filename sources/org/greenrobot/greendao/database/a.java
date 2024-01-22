package org.greenrobot.greendao.database;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
/* loaded from: classes13.dex */
public class a extends SQLiteOpenHelper implements DatabaseOpenHelper.a {
    @Override // org.greenrobot.greendao.database.DatabaseOpenHelper.a
    public Database a(String str) {
        return e(getReadableDatabase(str));
    }

    @Override // org.greenrobot.greendao.database.DatabaseOpenHelper.a
    public Database b(String str) {
        return e(getWritableDatabase(str));
    }

    @Override // org.greenrobot.greendao.database.DatabaseOpenHelper.a
    public Database c(char[] cArr) {
        return e(getReadableDatabase(cArr));
    }

    @Override // org.greenrobot.greendao.database.DatabaseOpenHelper.a
    public Database d(char[] cArr) {
        return e(getWritableDatabase(cArr));
    }

    public final Database e(SQLiteDatabase sQLiteDatabase) {
        return new EncryptedDatabase(sQLiteDatabase);
    }
}
