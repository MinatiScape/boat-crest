package org.greenrobot.greendao.query;

import org.greenrobot.greendao.AbstractDao;
/* loaded from: classes13.dex */
public abstract class c<T> extends a<T> {
    public final int limitPosition;
    public final int offsetPosition;

    public c(AbstractDao<T, ?> abstractDao, String str, String[] strArr, int i, int i2) {
        super(abstractDao, str, strArr);
        this.limitPosition = i;
        this.offsetPosition = i2;
    }

    public void setLimit(int i) {
        checkThread();
        int i2 = this.limitPosition;
        if (i2 != -1) {
            this.parameters[i2] = Integer.toString(i);
            return;
        }
        throw new IllegalStateException("Limit must be set with QueryBuilder before it can be used here");
    }

    public void setOffset(int i) {
        checkThread();
        int i2 = this.offsetPosition;
        if (i2 != -1) {
            this.parameters[i2] = Integer.toString(i);
            return;
        }
        throw new IllegalStateException("Offset must be set with QueryBuilder before it can be used here");
    }

    @Override // org.greenrobot.greendao.query.a
    public c<T> setParameter(int i, Object obj) {
        if (i >= 0 && (i == this.limitPosition || i == this.offsetPosition)) {
            throw new IllegalArgumentException("Illegal parameter index: " + i);
        }
        return (c) super.setParameter(i, obj);
    }
}
