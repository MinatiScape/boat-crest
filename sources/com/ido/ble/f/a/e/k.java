package com.ido.ble.f.a.e;

import com.ido.ble.common.TimeUtil;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSpO2ItemDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class k extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.b<HealthSpO2Item> {

    /* renamed from: a  reason: collision with root package name */
    private static k f12241a;

    private k() {
    }

    private HealthSpO2ItemDao b() {
        return a().getHealthSpO2ItemDao();
    }

    public static synchronized k c() {
        k kVar;
        synchronized (k.class) {
            if (f12241a == null) {
                f12241a = new k();
            }
            kVar = f12241a;
        }
        return kVar;
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSpO2Item> a(long j, int i) {
        QueryBuilder<HealthSpO2Item> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2ItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2ItemDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2ItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSpO2Item> a(long j, int i, int i2) {
        return b(j, b(i, i2), a(i, i2));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSpO2Item> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSpO2Item> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2ItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2ItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSpO2ItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSpO2ItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2ItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSpO2Item> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSpO2Item> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2ItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2ItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        a(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthSpO2Item healthSpO2Item) {
        if (healthSpO2Item != null) {
            healthSpO2Item.setDId(j);
            b().insert(healthSpO2Item);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, Date date, Date date2) {
        QueryBuilder<HealthSpO2Item> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthSpO2ItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthSpO2ItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, List<HealthSpO2Item> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthSpO2Item healthSpO2Item : list) {
            healthSpO2Item.setDId(j);
        }
        b().insertInTx(list);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSpO2Item> b(long j, int i, int i2) {
        QueryBuilder<HealthSpO2Item> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2ItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2ItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSpO2ItemDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2ItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSpO2Item> b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        return b(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSpO2Item> b(long j, Date date, Date date2) {
        QueryBuilder<HealthSpO2Item> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthSpO2ItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthSpO2ItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSpO2Item> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2ItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2ItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSpO2ItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSpO2ItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthSpO2Item healthSpO2Item) {
        if (healthSpO2Item != null) {
            healthSpO2Item.setDId(j);
            b().update(healthSpO2Item);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, List<HealthSpO2Item> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthSpO2Item healthSpO2Item : list) {
            healthSpO2Item.setDId(j);
        }
        b().updateInTx(list);
    }
}
