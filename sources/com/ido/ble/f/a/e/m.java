package com.ido.ble.f.a.e;

import com.ido.ble.common.TimeUtil;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.data.manage.database.HealthSportItemDao;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class m extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.i {

    /* renamed from: a  reason: collision with root package name */
    private static m f12243a;

    private m() {
    }

    private HealthSportItemDao b() {
        return a().getHealthSportItemDao();
    }

    public static synchronized m c() {
        m mVar;
        synchronized (m.class) {
            if (f12243a == null) {
                f12243a = new m();
            }
            mVar = f12243a;
        }
        return mVar;
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSportItem> a(long j, int i) {
        QueryBuilder<HealthSportItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSportItemDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSportItem> a(long j, int i, int i2) {
        return b(j, b(i, i2), a(i, i2));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSportItem> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSportItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSportItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSportItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSportItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSportItem> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSportItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        a(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthSportItem healthSportItem) {
        if (healthSportItem != null) {
            healthSportItem.setDId(j);
            b().insert(healthSportItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, Date date, Date date2) {
        QueryBuilder<HealthSportItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthSportItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthSportItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, List<HealthSportItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthSportItem healthSportItem : list) {
            healthSportItem.setDId(j);
        }
        b().insertInTx(list);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSportItem> b(long j, int i, int i2) {
        QueryBuilder<HealthSportItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSportItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSportItemDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSportItem> b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        return b(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSportItem> b(long j, Date date, Date date2) {
        QueryBuilder<HealthSportItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthSportItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthSportItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSportItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSportItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSportItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSportItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthSportItem healthSportItem) {
        if (healthSportItem == null || healthSportItem.getSportDataId() == null) {
            return;
        }
        healthSportItem.setDId(j);
        b().update(healthSportItem);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, List<HealthSportItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        boolean z = true;
        Iterator<HealthSportItem> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            HealthSportItem next = it.next();
            if (next.getSportDataId() == null) {
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
