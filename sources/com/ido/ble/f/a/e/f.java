package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthHeartRateSecondDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class f extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.a<HealthHeartRateSecond> {

    /* renamed from: a  reason: collision with root package name */
    private static f f12236a;

    private f() {
    }

    private HealthHeartRateSecondDao b() {
        return a().getHealthHeartRateSecondDao();
    }

    public static synchronized f c() {
        f fVar;
        synchronized (f.class) {
            if (f12236a == null) {
                f12236a = new f();
            }
            fVar = f12236a;
        }
        return fVar;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthHeartRateSecond a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateSecondDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateSecondDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateSecondDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateSecondDao.Properties.Date);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRateSecond> a(long j, int i) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateSecondDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateSecondDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRateSecond> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthHeartRateSecondDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRateSecond> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateSecondDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthHeartRateSecond healthHeartRateSecond) {
        if (healthHeartRateSecond == null) {
            return;
        }
        HealthHeartRateSecond a2 = a(j, healthHeartRateSecond.getYear(), healthHeartRateSecond.getMonth(), healthHeartRateSecond.getDay());
        if (a2 == null) {
            healthHeartRateSecond.setDId(j);
            b().insert(healthHeartRateSecond);
            return;
        }
        healthHeartRateSecond.setDataId(a2.getDataId());
        a(j, healthHeartRateSecond);
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRateSecond> b(long j, int i, int i2) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateSecondDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateSecondDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateSecondDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateSecondDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateSecondDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateSecondDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthHeartRateSecond healthHeartRateSecond) {
        if (healthHeartRateSecond == null || healthHeartRateSecond.getDataId() == null) {
            return;
        }
        healthHeartRateSecond.setDId(j);
        b().update(healthHeartRateSecond);
    }
}
