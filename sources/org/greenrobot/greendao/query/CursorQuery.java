package org.greenrobot.greendao.query;

import android.database.Cursor;
import java.util.Date;
import org.greenrobot.greendao.AbstractDao;
/* loaded from: classes13.dex */
public class CursorQuery<T> extends c<T> {

    /* renamed from: a  reason: collision with root package name */
    public final b<T> f15484a;

    /* loaded from: classes13.dex */
    public static final class b<T2> extends org.greenrobot.greendao.query.b<T2, CursorQuery<T2>> {
        public final int e;
        public final int f;

        public b(AbstractDao abstractDao, String str, String[] strArr, int i, int i2) {
            super(abstractDao, str, strArr);
            this.e = i;
            this.f = i2;
        }

        @Override // org.greenrobot.greendao.query.b
        /* renamed from: e */
        public CursorQuery<T2> a() {
            return new CursorQuery<>(this, this.b, this.f15489a, (String[]) this.c.clone(), this.e, this.f);
        }
    }

    public static <T2> CursorQuery<T2> a(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr, int i, int i2) {
        return new b(abstractDao, str, org.greenrobot.greendao.query.a.toStringArray(objArr), i, i2).b();
    }

    public static <T2> CursorQuery<T2> internalCreate(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return a(abstractDao, str, objArr, -1, -1);
    }

    public CursorQuery forCurrentThread() {
        return this.f15484a.c(this);
    }

    public Cursor query() {
        checkThread();
        return this.dao.getDatabase().rawQuery(this.sql, this.parameters);
    }

    @Override // org.greenrobot.greendao.query.c
    public /* bridge */ /* synthetic */ void setLimit(int i) {
        super.setLimit(i);
    }

    @Override // org.greenrobot.greendao.query.c
    public /* bridge */ /* synthetic */ void setOffset(int i) {
        super.setOffset(i);
    }

    public CursorQuery(b<T> bVar, AbstractDao<T, ?> abstractDao, String str, String[] strArr, int i, int i2) {
        super(abstractDao, str, strArr, i, i2);
        this.f15484a = bVar;
    }

    @Override // org.greenrobot.greendao.query.c, org.greenrobot.greendao.query.a
    public CursorQuery<T> setParameter(int i, Object obj) {
        return (CursorQuery) super.setParameter(i, obj);
    }

    @Override // org.greenrobot.greendao.query.a
    public CursorQuery<T> setParameter(int i, Date date) {
        return (CursorQuery) super.setParameter(i, date);
    }

    @Override // org.greenrobot.greendao.query.a
    public CursorQuery<T> setParameter(int i, Boolean bool) {
        return (CursorQuery) super.setParameter(i, bool);
    }
}
