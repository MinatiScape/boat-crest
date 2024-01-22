package org.greenrobot.greendao.query;

import android.database.Cursor;
import java.util.Date;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
/* loaded from: classes13.dex */
public class CountQuery<T> extends org.greenrobot.greendao.query.a<T> {

    /* renamed from: a  reason: collision with root package name */
    public final b<T> f15483a;

    /* loaded from: classes13.dex */
    public static final class b<T2> extends org.greenrobot.greendao.query.b<T2, CountQuery<T2>> {
        @Override // org.greenrobot.greendao.query.b
        /* renamed from: e */
        public CountQuery<T2> a() {
            return new CountQuery<>(this, this.b, this.f15489a, (String[]) this.c.clone());
        }

        public b(AbstractDao<T2, ?> abstractDao, String str, String[] strArr) {
            super(abstractDao, str, strArr);
        }
    }

    public static <T2> CountQuery<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return new b(abstractDao, str, org.greenrobot.greendao.query.a.toStringArray(objArr)).b();
    }

    public long count() {
        checkThread();
        Cursor rawQuery = this.dao.getDatabase().rawQuery(this.sql, this.parameters);
        try {
            if (rawQuery.moveToNext()) {
                if (rawQuery.isLast()) {
                    if (rawQuery.getColumnCount() == 1) {
                        return rawQuery.getLong(0);
                    }
                    throw new DaoException("Unexpected column count: " + rawQuery.getColumnCount());
                }
                throw new DaoException("Unexpected row count: " + rawQuery.getCount());
            }
            throw new DaoException("No result for count");
        } finally {
            rawQuery.close();
        }
    }

    public CountQuery<T> forCurrentThread() {
        return (CountQuery) this.f15483a.c(this);
    }

    public CountQuery(b<T> bVar, AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        super(abstractDao, str, strArr);
        this.f15483a = bVar;
    }

    @Override // org.greenrobot.greendao.query.a
    public CountQuery<T> setParameter(int i, Object obj) {
        return (CountQuery) super.setParameter(i, obj);
    }

    @Override // org.greenrobot.greendao.query.a
    public CountQuery<T> setParameter(int i, Date date) {
        return (CountQuery) super.setParameter(i, date);
    }

    @Override // org.greenrobot.greendao.query.a
    public CountQuery<T> setParameter(int i, Boolean bool) {
        return (CountQuery) super.setParameter(i, bool);
    }
}
