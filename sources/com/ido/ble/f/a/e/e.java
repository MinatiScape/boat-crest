package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class e extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.c {

    /* renamed from: a  reason: collision with root package name */
    private static e f12235a;

    private e() {
    }

    private HealthHeartRateDao b() {
        return a().getHealthHeartRateDao();
    }

    public static synchronized e c() {
        e eVar;
        synchronized (e.class) {
            if (f12235a == null) {
                f12235a = new e();
            }
            eVar = f12235a;
        }
        return eVar;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthHeartRate a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateDao.Properties.Date);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRate> a(long j, int i) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRate> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthHeartRateDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRate> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthHeartRate healthHeartRate) {
        if (healthHeartRate == null) {
            return;
        }
        HealthHeartRate a2 = a(j, healthHeartRate.getYear(), healthHeartRate.getMonth(), healthHeartRate.getDay());
        if (a2 == null) {
            healthHeartRate.setDId(j);
            b().insert(healthHeartRate);
            return;
        }
        healthHeartRate.setRateDataId(a2.getRateDataId());
        a(j, healthHeartRate);
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRate> b(long j, int i, int i2) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthHeartRate healthHeartRate) {
        if (healthHeartRate == null || healthHeartRate.getRateDataId() == null) {
            return;
        }
        healthHeartRate.setDId(j);
        b().update(healthHeartRate);
    }
}
