package com.ido.ble.f.a.e;

import com.ido.ble.common.TimeUtil;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSleepItemDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class i extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.g {

    /* renamed from: a  reason: collision with root package name */
    private static i f12239a;

    private i() {
    }

    private HealthSleepItemDao b() {
        return a().getHealthSleepItemDao();
    }

    public static synchronized i c() {
        i iVar;
        synchronized (i.class) {
            if (f12239a == null) {
                f12239a = new i();
            }
            iVar = f12239a;
        }
        return iVar;
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> a(long j, int i) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSleepItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> a(long j, int i, int i2) {
        return b(j, b(i, i2), a(i, i2));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSleepItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSleepItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSleepItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSleepItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        a(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthSleepItem healthSleepItem) {
        if (healthSleepItem != null) {
            healthSleepItem.setDId(j);
            b().insert(healthSleepItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, Date date, Date date2) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthSleepItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, List<HealthSleepItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthSleepItem healthSleepItem : list) {
            healthSleepItem.setDId(j);
        }
        b().insertInTx(list);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> b(long j, int i, int i2) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSleepItemDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSleepItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        return b(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> b(long j, Date date, Date date2) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthSleepItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSleepItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSleepItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthSleepItem healthSleepItem) {
        if (healthSleepItem != null) {
            healthSleepItem.setDId(j);
            b().update(healthSleepItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, List<HealthSleepItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthSleepItem healthSleepItem : list) {
            healthSleepItem.setDId(j);
        }
        b().updateInTx(list);
    }
}
