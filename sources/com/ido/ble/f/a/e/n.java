package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class n extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.h {

    /* renamed from: a  reason: collision with root package name */
    private static n f12244a;

    private n() {
    }

    private HealthSportDao b() {
        return a().getHealthSportDao();
    }

    public static synchronized n c() {
        n nVar;
        synchronized (n.class) {
            if (f12244a == null) {
                f12244a = new n();
            }
            nVar = f12244a;
        }
        return nVar;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthSport a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), HealthSportDao.Properties.Year.eq(Integer.valueOf(i)), HealthSportDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSportDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSport> a(long j, int i) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), HealthSportDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSport> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthSportDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthSportDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSport> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthSport healthSport) {
        if (healthSport == null) {
            return;
        }
        HealthSport a2 = a(j, healthSport.getYear(), healthSport.getMonth(), healthSport.getDay());
        if (a2 == null) {
            healthSport.setDId(j);
            b().insert(healthSport);
            return;
        }
        healthSport.setSportDataId(a2.getSportDataId());
        a(j, healthSport);
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSport> b(long j, int i, int i2) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), HealthSportDao.Properties.Year.eq(Integer.valueOf(i)), HealthSportDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), HealthSportDao.Properties.Year.eq(Integer.valueOf(i)), HealthSportDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSportDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthSport healthSport) {
        if (healthSport == null || healthSport.getSportDataId() == null) {
            return;
        }
        healthSport.setDId(j);
        b().update(healthSport);
    }
}
