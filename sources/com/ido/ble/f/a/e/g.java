package com.ido.ble.f.a.e;

import com.ido.ble.common.TimeUtil;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthPressureItemDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class g extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.b<HealthPressureItem> {

    /* renamed from: a  reason: collision with root package name */
    private static g f12237a;

    private g() {
    }

    private HealthPressureItemDao b() {
        return a().getHealthPressureItemDao();
    }

    public static synchronized g c() {
        g gVar;
        synchronized (g.class) {
            if (f12237a == null) {
                f12237a = new g();
            }
            gVar = f12237a;
        }
        return gVar;
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> a(long j, int i) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> a(long j, int i, int i2) {
        return b(j, b(i, i2), a(i, i2));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthPressureItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        a(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthPressureItem healthPressureItem) {
        if (healthPressureItem != null) {
            healthPressureItem.setDId(j);
            b().insert(healthPressureItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, Date date, Date date2) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthPressureItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, List<HealthPressureItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthPressureItem healthPressureItem : list) {
            healthPressureItem.setDId(j);
        }
        b().insertInTx(list);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> b(long j, int i, int i2) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureItemDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        return b(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> b(long j, Date date, Date date2) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthPressureItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthPressureItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthPressureItem healthPressureItem) {
        if (healthPressureItem != null) {
            healthPressureItem.setDId(j);
            b().update(healthPressureItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, List<HealthPressureItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthPressureItem healthPressureItem : list) {
            healthPressureItem.setDId(j);
        }
        b().updateInTx(list);
    }
}
