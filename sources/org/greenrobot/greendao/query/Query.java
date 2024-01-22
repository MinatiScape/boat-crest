package org.greenrobot.greendao.query;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.apihint.Internal;
import org.greenrobot.greendao.rx.RxQuery;
import rx.schedulers.Schedulers;
/* loaded from: classes13.dex */
public class Query<T> extends c<T> {

    /* renamed from: a  reason: collision with root package name */
    public final b<T> f15487a;
    public volatile RxQuery b;
    public volatile RxQuery c;

    /* loaded from: classes13.dex */
    public static final class b<T2> extends org.greenrobot.greendao.query.b<T2, Query<T2>> {
        public final int e;
        public final int f;

        public b(AbstractDao<T2, ?> abstractDao, String str, String[] strArr, int i, int i2) {
            super(abstractDao, str, strArr);
            this.e = i;
            this.f = i2;
        }

        @Override // org.greenrobot.greendao.query.b
        /* renamed from: e */
        public Query<T2> a() {
            return new Query<>(this, this.b, this.f15489a, (String[]) this.c.clone(), this.e, this.f);
        }
    }

    public static <T2> Query<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr, int i, int i2) {
        return new b(abstractDao, str, org.greenrobot.greendao.query.a.toStringArray(objArr), i, i2).b();
    }

    public static <T2> Query<T2> internalCreate(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return a(abstractDao, str, objArr, -1, -1);
    }

    @Internal
    public RxQuery __InternalRx() {
        if (this.c == null) {
            this.c = new RxQuery(this, Schedulers.io());
        }
        return this.c;
    }

    @Internal
    public RxQuery __internalRxPlain() {
        if (this.b == null) {
            this.b = new RxQuery(this);
        }
        return this.b;
    }

    public Query<T> forCurrentThread() {
        return (Query) this.f15487a.c(this);
    }

    public List<T> list() {
        checkThread();
        return this.daoAccess.loadAllAndCloseCursor(this.dao.getDatabase().rawQuery(this.sql, this.parameters));
    }

    public CloseableListIterator<T> listIterator() {
        return listLazyUncached().listIteratorAutoClose();
    }

    public LazyList<T> listLazy() {
        checkThread();
        return new LazyList<>(this.daoAccess, this.dao.getDatabase().rawQuery(this.sql, this.parameters), true);
    }

    public LazyList<T> listLazyUncached() {
        checkThread();
        return new LazyList<>(this.daoAccess, this.dao.getDatabase().rawQuery(this.sql, this.parameters), false);
    }

    @Override // org.greenrobot.greendao.query.c
    public /* bridge */ /* synthetic */ void setLimit(int i) {
        super.setLimit(i);
    }

    @Override // org.greenrobot.greendao.query.c
    public /* bridge */ /* synthetic */ void setOffset(int i) {
        super.setOffset(i);
    }

    public T unique() {
        checkThread();
        return this.daoAccess.loadUniqueAndCloseCursor(this.dao.getDatabase().rawQuery(this.sql, this.parameters));
    }

    public T uniqueOrThrow() {
        T unique = unique();
        if (unique != null) {
            return unique;
        }
        throw new DaoException("No entity found for query");
    }

    public Query(b<T> bVar, AbstractDao<T, ?> abstractDao, String str, String[] strArr, int i, int i2) {
        super(abstractDao, str, strArr, i, i2);
        this.f15487a = bVar;
    }

    @Override // org.greenrobot.greendao.query.c, org.greenrobot.greendao.query.a
    public Query<T> setParameter(int i, Object obj) {
        return (Query) super.setParameter(i, obj);
    }

    @Override // org.greenrobot.greendao.query.a
    public Query<T> setParameter(int i, Date date) {
        return (Query) super.setParameter(i, date);
    }

    @Override // org.greenrobot.greendao.query.a
    public Query<T> setParameter(int i, Boolean bool) {
        return (Query) super.setParameter(i, bool);
    }
}
