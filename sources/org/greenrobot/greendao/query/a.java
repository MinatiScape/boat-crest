package org.greenrobot.greendao.query;

import java.util.Date;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.InternalQueryDaoAccess;
/* loaded from: classes13.dex */
public abstract class a<T> {
    public final AbstractDao<T, ?> dao;
    public final InternalQueryDaoAccess<T> daoAccess;
    public final Thread ownerThread = Thread.currentThread();
    public final String[] parameters;
    public final String sql;

    public a(AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        this.dao = abstractDao;
        this.daoAccess = new InternalQueryDaoAccess<>(abstractDao);
        this.sql = str;
        this.parameters = strArr;
    }

    public static String[] toStringArray(Object[] objArr) {
        int length = objArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            if (obj != null) {
                strArr[i] = obj.toString();
            } else {
                strArr[i] = null;
            }
        }
        return strArr;
    }

    public void checkThread() {
        if (Thread.currentThread() != this.ownerThread) {
            throw new DaoException("Method may be called only in owner thread, use forCurrentThread to get an instance for this thread");
        }
    }

    public a<T> setParameter(int i, Object obj) {
        checkThread();
        if (obj != null) {
            this.parameters[i] = obj.toString();
        } else {
            this.parameters[i] = null;
        }
        return this;
    }

    public a<T> setParameter(int i, Date date) {
        return setParameter(i, date != null ? Long.valueOf(date.getTime()) : null);
    }

    public a<T> setParameter(int i, Boolean bool) {
        return setParameter(i, bool != null ? Integer.valueOf(bool.booleanValue() ? 1 : 0) : null);
    }
}
