package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class h extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.a<HealthPressure> {

    /* renamed from: a  reason: collision with root package name */
    private static h f12238a;

    private h() {
    }

    private HealthPressureDao b() {
        return a().getHealthPressureDao();
    }

    public static synchronized h c() {
        h hVar;
        synchronized (h.class) {
            if (f12238a == null) {
                f12238a = new h();
            }
            hVar = f12238a;
        }
        return hVar;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthPressure a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureDao.Properties.Month.eq(Integer.valueOf(i2)), HealthPressureDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureDao.Properties.Date);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthPressure> a(long j, int i) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthPressure> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthPressureDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthPressureDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthPressure> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthPressure healthPressure) {
        if (healthPressure == null) {
            return;
        }
        HealthPressure a2 = a(j, healthPressure.getYear(), healthPressure.getMonth(), healthPressure.getDay());
        if (a2 == null) {
            healthPressure.setDId(j);
            b().insert(healthPressure);
            return;
        }
        healthPressure.setId(a2.getId());
        a(j, healthPressure);
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthPressure> b(long j, int i, int i2) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureDao.Properties.Month.eq(Integer.valueOf(i2)), HealthPressureDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthPressure healthPressure) {
        if (healthPressure == null || healthPressure.getId() == null) {
            return;
        }
        healthPressure.setId(Long.valueOf(j));
        b().update(healthPressure);
    }
}
