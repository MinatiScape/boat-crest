package org.greenrobot.greendao;

import android.database.Cursor;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScope;
import org.greenrobot.greendao.internal.DaoConfig;
/* loaded from: classes13.dex */
public class InternalUnitTestDaoAccess<T, K> {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractDao<T, K> f15469a;

    public InternalUnitTestDaoAccess(Database database, Class<AbstractDao<T, K>> cls, IdentityScope<?, ?> identityScope) throws Exception {
        DaoConfig daoConfig = new DaoConfig(database, cls);
        daoConfig.setIdentityScope(identityScope);
        this.f15469a = cls.getConstructor(DaoConfig.class).newInstance(daoConfig);
    }

    public AbstractDao<T, K> getDao() {
        return this.f15469a;
    }

    public K getKey(T t) {
        return this.f15469a.getKey(t);
    }

    public Property[] getProperties() {
        return this.f15469a.getProperties();
    }

    public boolean isEntityUpdateable() {
        return this.f15469a.isEntityUpdateable();
    }

    public T readEntity(Cursor cursor, int i) {
        return this.f15469a.readEntity(cursor, i);
    }

    public K readKey(Cursor cursor, int i) {
        return this.f15469a.readKey(cursor, i);
    }
}
