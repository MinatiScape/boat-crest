package com.ido.ble.f.a.e;

import com.ido.ble.common.TimeUtil;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthHeartRateItemDao;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class d extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.d {

    /* renamed from: a  reason: collision with root package name */
    private static d f12234a;

    private d() {
    }

    private HealthHeartRateItemDao b() {
        return a().getHealthHeartRateItemDao();
    }

    public static synchronized d c() {
        d dVar;
        synchronized (d.class) {
            if (f12234a == null) {
                f12234a = new d();
            }
            dVar = f12234a;
        }
        return dVar;
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthHeartRateItem> a(long j, int i) {
        QueryBuilder<HealthHeartRateItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateItemDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateItemDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthHeartRateItem> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthHeartRateItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthHeartRateItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthHeartRateItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthHeartRateItem> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRateItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateItemDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthHeartRateItem> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthHeartRateItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        a(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthHeartRateItem healthHeartRateItem) {
        if (healthHeartRateItem != null) {
            healthHeartRateItem.setDId(j);
            b().insert(healthHeartRateItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, Date date, Date date2) {
        QueryBuilder<HealthHeartRateItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthHeartRateItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthHeartRateItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, List<HealthHeartRateItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthHeartRateItem healthHeartRateItem : list) {
            healthHeartRateItem.setDId(j);
        }
        b().insertInTx(list);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthHeartRateItem> b(long j, int i, int i2) {
        QueryBuilder<HealthHeartRateItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateItemDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateItemDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthHeartRateItem> b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        return b(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthHeartRateItem> b(long j, Date date, Date date2) {
        QueryBuilder<HealthHeartRateItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthHeartRateItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthHeartRateItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRateItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateItemDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthHeartRateItem healthHeartRateItem) {
        if (healthHeartRateItem == null || healthHeartRateItem.getHeartRateDataId() == null) {
            return;
        }
        healthHeartRateItem.setDId(j);
        b().update(healthHeartRateItem);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, List<HealthHeartRateItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        boolean z = true;
        Iterator<HealthHeartRateItem> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            HealthHeartRateItem next = it.next();
            if (next.getHeartRateDataId() == null) {
                z = false;
                break;
            }
            next.setDId(j);
        }
        if (z) {
            b().updateInTx(list);
        }
    }
}
