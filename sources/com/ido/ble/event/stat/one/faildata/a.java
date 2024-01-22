package com.ido.ble.event.stat.one.faildata;

import com.ido.ble.data.manage.database.DaoSession;
import com.ido.ble.event.stat.one.faildata.FailLogInfoDao;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class a {
    private static a b;

    /* renamed from: a  reason: collision with root package name */
    private DaoSession f12222a;

    private a() {
    }

    public static synchronized a b() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                a aVar2 = new a();
                b = aVar2;
                aVar2.c();
            }
            aVar = b;
        }
        return aVar;
    }

    private void c() {
        this.f12222a = com.ido.ble.f.a.b.d().b();
    }

    public List<c> a() {
        return this.f12222a.getFailLogInfoDao().loadAll();
    }

    public synchronized void a(c cVar) {
        this.f12222a.getFailLogInfoDao().insertOrReplace(cVar);
    }

    public synchronized void a(String str) {
        this.f12222a.getFailLogInfoDao().queryBuilder().where(FailLogInfoDao.Properties.c.eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }
}
