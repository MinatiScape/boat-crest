package org.greenrobot.greendao.query;

import java.util.Date;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
/* loaded from: classes13.dex */
public class DeleteQuery<T> extends org.greenrobot.greendao.query.a<T> {

    /* renamed from: a  reason: collision with root package name */
    public final b<T> f15485a;

    /* loaded from: classes13.dex */
    public static final class b<T2> extends org.greenrobot.greendao.query.b<T2, DeleteQuery<T2>> {
        @Override // org.greenrobot.greendao.query.b
        /* renamed from: e */
        public DeleteQuery<T2> a() {
            return new DeleteQuery<>(this, this.b, this.f15489a, (String[]) this.c.clone());
        }

        public b(AbstractDao<T2, ?> abstractDao, String str, String[] strArr) {
            super(abstractDao, str, strArr);
        }
    }

    public static <T2> DeleteQuery<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return new b(abstractDao, str, org.greenrobot.greendao.query.a.toStringArray(objArr)).b();
    }

    public void executeDeleteWithoutDetachingEntities() {
        checkThread();
        Database database = this.dao.getDatabase();
        if (database.isDbLockedByCurrentThread()) {
            this.dao.getDatabase().execSQL(this.sql, this.parameters);
            return;
        }
        database.beginTransaction();
        try {
            this.dao.getDatabase().execSQL(this.sql, this.parameters);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    public DeleteQuery<T> forCurrentThread() {
        return (DeleteQuery) this.f15485a.c(this);
    }

    public DeleteQuery(b<T> bVar, AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        super(abstractDao, str, strArr);
        this.f15485a = bVar;
    }

    @Override // org.greenrobot.greendao.query.a
    public DeleteQuery<T> setParameter(int i, Object obj) {
        return (DeleteQuery) super.setParameter(i, obj);
    }

    @Override // org.greenrobot.greendao.query.a
    public DeleteQuery<T> setParameter(int i, Date date) {
        return (DeleteQuery) super.setParameter(i, date);
    }

    @Override // org.greenrobot.greendao.query.a
    public DeleteQuery<T> setParameter(int i, Boolean bool) {
        return (DeleteQuery) super.setParameter(i, bool);
    }
}
