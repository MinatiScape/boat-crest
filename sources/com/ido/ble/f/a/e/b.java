package com.ido.ble.f.a.e;

import com.ido.ble.common.TimeUtil;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthBloodPressedItemDao;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class b extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.b {

    /* renamed from: a  reason: collision with root package name */
    private static b f12232a;

    private b() {
    }

    private HealthBloodPressedItemDao b() {
        return a().getHealthBloodPressedItemDao();
    }

    public static synchronized b c() {
        b bVar;
        synchronized (b.class) {
            if (f12232a == null) {
                f12232a = new b();
            }
            bVar = f12232a;
        }
        return bVar;
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthBloodPressedItem> a(long j, int i) {
        QueryBuilder<HealthBloodPressedItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedItemDao.Properties.DId.eq(Long.valueOf(j)), HealthBloodPressedItemDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthBloodPressedItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthBloodPressedItem> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthBloodPressedItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthBloodPressedItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthBloodPressedItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthBloodPressedItem> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthBloodPressedItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedItemDao.Properties.DId.eq(Long.valueOf(j)), HealthBloodPressedItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthBloodPressedItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthBloodPressedItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthBloodPressedItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthBloodPressedItem> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthBloodPressedItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthBloodPressedItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        a(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthBloodPressedItem healthBloodPressedItem) {
        if (healthBloodPressedItem != null) {
            healthBloodPressedItem.setDId(j);
            b().insert(healthBloodPressedItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, Date date, Date date2) {
        QueryBuilder<HealthBloodPressedItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthBloodPressedItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthBloodPressedItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, List<HealthBloodPressedItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthBloodPressedItem healthBloodPressedItem : list) {
            healthBloodPressedItem.setDId(j);
        }
        b().insertInTx(list);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthBloodPressedItem> b(long j, int i, int i2) {
        QueryBuilder<HealthBloodPressedItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedItemDao.Properties.DId.eq(Long.valueOf(j)), HealthBloodPressedItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthBloodPressedItemDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthBloodPressedItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthBloodPressedItem> b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        return b(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthBloodPressedItem> b(long j, Date date, Date date2) {
        QueryBuilder<HealthBloodPressedItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthBloodPressedItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthBloodPressedItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(date), property.le(date2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthBloodPressedItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedItemDao.Properties.DId.eq(Long.valueOf(j)), HealthBloodPressedItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthBloodPressedItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthBloodPressedItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthBloodPressedItem healthBloodPressedItem) {
        if (healthBloodPressedItem == null || healthBloodPressedItem.getBloodPressedItemId() == null) {
            return;
        }
        healthBloodPressedItem.setDId(j);
        b().update(healthBloodPressedItem);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, List<HealthBloodPressedItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        boolean z = true;
        Iterator<HealthBloodPressedItem> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            HealthBloodPressedItem next = it.next();
            if (next.getBloodPressedItemId() == null) {
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
