package org.greenrobot.greendao.test;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
/* loaded from: classes13.dex */
public abstract class AbstractDaoSessionTest<T extends AbstractDaoMaster, S extends AbstractDaoSession> extends DbTest {
    public final Class<T> b;
    public T daoMaster;
    public S daoSession;

    public AbstractDaoSessionTest(Class<T> cls) {
        this(cls, true);
    }

    @Override // org.greenrobot.greendao.test.DbTest
    public void setUp() throws Exception {
        super.setUp();
        try {
            this.daoMaster = this.b.getConstructor(Database.class).newInstance(this.db);
            this.b.getMethod("createAllTables", Database.class, Boolean.TYPE).invoke(null, this.db, Boolean.FALSE);
            this.daoSession = (S) this.daoMaster.newSession();
        } catch (Exception e) {
            throw new RuntimeException("Could not prepare DAO session test", e);
        }
    }

    public AbstractDaoSessionTest(Class<T> cls, boolean z) {
        super(z);
        this.b = cls;
    }
}
