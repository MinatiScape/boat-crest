package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class c extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.a {

    /* renamed from: a  reason: collision with root package name */
    private static c f12233a;

    private c() {
    }

    private HealthBloodPressedDao b() {
        return a().getHealthBloodPressedDao();
    }

    public static synchronized c c() {
        c cVar;
        synchronized (c.class) {
            if (f12233a == null) {
                f12233a = new c();
            }
            cVar = f12233a;
        }
        return cVar;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthBloodPressed a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthBloodPressed> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedDao.Properties.DId.eq(Long.valueOf(j)), HealthBloodPressedDao.Properties.Year.eq(Integer.valueOf(i)), HealthBloodPressedDao.Properties.Month.eq(Integer.valueOf(i2)), HealthBloodPressedDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthBloodPressed> a(long j, int i) {
        QueryBuilder<HealthBloodPressed> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedDao.Properties.DId.eq(Long.valueOf(j)), HealthBloodPressedDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthBloodPressedDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthBloodPressed> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthBloodPressed> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthBloodPressedDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthBloodPressedDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthBloodPressed> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthBloodPressed> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthBloodPressedDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthBloodPressed healthBloodPressed) {
        if (healthBloodPressed == null) {
            return;
        }
        HealthBloodPressed a2 = a(j, healthBloodPressed.getYear(), healthBloodPressed.getMonth(), healthBloodPressed.getDay());
        if (a2 == null) {
            healthBloodPressed.setDId(j);
            b().insert(healthBloodPressed);
            return;
        }
        healthBloodPressed.setBloodPressedId(a2.getBloodPressedId());
        a(j, healthBloodPressed);
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthBloodPressed> b(long j, int i, int i2) {
        QueryBuilder<HealthBloodPressed> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedDao.Properties.DId.eq(Long.valueOf(j)), HealthBloodPressedDao.Properties.Year.eq(Integer.valueOf(i)), HealthBloodPressedDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthBloodPressedDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthBloodPressed> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthBloodPressedDao.Properties.DId.eq(Long.valueOf(j)), HealthBloodPressedDao.Properties.Year.eq(Integer.valueOf(i)), HealthBloodPressedDao.Properties.Month.eq(Integer.valueOf(i2)), HealthBloodPressedDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthBloodPressed healthBloodPressed) {
        if (healthBloodPressed == null || healthBloodPressed.getBloodPressedId() == null) {
            return;
        }
        healthBloodPressed.setDId(j);
        b().update(healthBloodPressed);
    }
}
