package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.data.manage.database.HealthSwimmingDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class o extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.e<HealthSwimming> {

    /* renamed from: a  reason: collision with root package name */
    private static o f12245a;

    private o() {
    }

    private HealthSwimmingDao b() {
        return a().getHealthSwimmingDao();
    }

    public static synchronized o c() {
        o oVar;
        synchronized (o.class) {
            if (f12245a == null) {
                f12245a = new o();
            }
            oVar = f12245a;
        }
        return oVar;
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthSwimming> a(long j, int i) {
        QueryBuilder<HealthSwimming> queryBuilder = b().queryBuilder();
        queryBuilder.where(HealthSwimmingDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSwimmingDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthSwimming> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthSwimming> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthSwimmingDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthSwimmingDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthSwimming> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSwimming> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSwimmingDao.Properties.DId.eq(Long.valueOf(j)), HealthSwimmingDao.Properties.Year.eq(Integer.valueOf(i)), HealthSwimmingDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSwimmingDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthSwimming> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSwimming> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSwimmingDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        QueryBuilder<HealthSwimming> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSwimmingDao.Properties.DId.eq(Long.valueOf(j)), HealthSwimmingDao.Properties.Year.eq(Integer.valueOf(i)), HealthSwimmingDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSwimmingDao.Properties.Day.eq(Integer.valueOf(i3)), HealthSwimmingDao.Properties.Hour.eq(Integer.valueOf(i4)), HealthSwimmingDao.Properties.Minute.eq(Integer.valueOf(i5)), HealthSwimmingDao.Properties.Second.eq(Integer.valueOf(i6))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.e
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthSwimming healthSwimming) {
        if (healthSwimming == null) {
            return;
        }
        HealthSwimming b = b(j, healthSwimming.getYear(), healthSwimming.getMonth(), healthSwimming.getDay(), healthSwimming.getHour(), healthSwimming.getMinute(), healthSwimming.getSecond());
        if (b == null) {
            healthSwimming.setDId(j);
            b().insert(healthSwimming);
            return;
        }
        healthSwimming.setDataId(b.getDataId());
        a(j, healthSwimming);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.e
    public HealthSwimming b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        QueryBuilder<HealthSwimming> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSwimmingDao.Properties.DId.eq(Long.valueOf(j)), HealthSwimmingDao.Properties.Year.eq(Integer.valueOf(i)), HealthSwimmingDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSwimmingDao.Properties.Day.eq(Integer.valueOf(i3)), HealthSwimmingDao.Properties.Hour.eq(Integer.valueOf(i4)), HealthSwimmingDao.Properties.Minute.eq(Integer.valueOf(i5)), HealthSwimmingDao.Properties.Second.eq(Integer.valueOf(i6))), new WhereCondition[0]);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthSwimming> b(long j, int i, int i2) {
        QueryBuilder<HealthSwimming> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSwimmingDao.Properties.Year.eq(Integer.valueOf(i)), HealthSwimmingDao.Properties.Month.eq(Integer.valueOf(i2)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSwimmingDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSwimming> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSwimmingDao.Properties.DId.eq(Long.valueOf(j)), HealthSwimmingDao.Properties.Year.eq(Integer.valueOf(i)), HealthSwimmingDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSwimmingDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.e
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthSwimming healthSwimming) {
        if (healthSwimming == null || healthSwimming.getDataId() == null) {
            return;
        }
        healthSwimming.setDId(j);
        b().update(healthSwimming);
    }
}
