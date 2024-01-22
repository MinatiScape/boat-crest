package org.greenrobot.greendao.test;

import android.test.AndroidTestCase;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoLog;
/* loaded from: classes13.dex */
public abstract class AbstractDaoTestLongPk<D extends AbstractDao<T, Long>, T> extends AbstractDaoTestSinglePk<D, T, Long> {
    public AbstractDaoTestLongPk(Class<D> cls) {
        super(cls);
    }

    public void testAssignPk() {
        if (this.daoAccess.isEntityUpdateable()) {
            T createEntity = createEntity(null);
            if (createEntity != null) {
                T createEntity2 = createEntity(null);
                this.dao.insert(createEntity);
                this.dao.insert(createEntity2);
                Long l = (Long) this.daoAccess.getKey(createEntity);
                AndroidTestCase.assertNotNull(l);
                Long l2 = (Long) this.daoAccess.getKey(createEntity2);
                AndroidTestCase.assertNotNull(l2);
                AndroidTestCase.assertFalse(l.equals(l2));
                AndroidTestCase.assertNotNull(this.dao.load(l));
                AndroidTestCase.assertNotNull(this.dao.load(l2));
                return;
            }
            DaoLog.d("Skipping testAssignPk for " + this.daoClass + " (createEntity returned null for null key)");
            return;
        }
        DaoLog.d("Skipping testAssignPk for not updateable " + this.daoClass);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.test.AbstractDaoTestSinglePk
    public Long createRandomPk() {
        return Long.valueOf(this.random.nextLong());
    }
}
