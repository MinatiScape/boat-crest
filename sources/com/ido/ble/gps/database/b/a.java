package com.ido.ble.gps.database.b;

import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class a extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.a<HealthGps> {

    /* renamed from: a  reason: collision with root package name */
    private static a f12298a;

    private a() {
    }

    private HealthGpsDao b() {
        return a().getHealthGpsDao();
    }

    public static a c() {
        if (f12298a == null) {
            f12298a = new a();
        }
        return f12298a;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthGps a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthGps> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthGpsDao.Properties.DId.eq(Long.valueOf(j)), HealthGpsDao.Properties.Year.eq(Integer.valueOf(i)), HealthGpsDao.Properties.Month.eq(Integer.valueOf(i2)), HealthGpsDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        return queryBuilder.unique();
    }

    public List<HealthGps> a(long j) {
        QueryBuilder<HealthGps> queryBuilder = b().queryBuilder();
        queryBuilder.where(HealthGpsDao.Properties.DId.eq(Long.valueOf(j)), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthGps> a(long j, int i) {
        QueryBuilder<HealthGps> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthGpsDao.Properties.DId.eq(Long.valueOf(j)), HealthGpsDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthGpsDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthGps> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthGps> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthGpsDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthGpsDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthGps> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthGps> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthGpsDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthGps healthGps) {
        if (healthGps == null) {
            return;
        }
        HealthGps a2 = a(j, healthGps.getYear().intValue(), healthGps.getMonth().intValue(), healthGps.getDay().intValue());
        if (a2 == null) {
            healthGps.setDId(j);
            b().insert(healthGps);
            return;
        }
        healthGps.setHealthGpsId(a2.getHealthGpsId());
        a(j, healthGps);
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthGps> b(long j, int i, int i2) {
        QueryBuilder<HealthGps> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthGpsDao.Properties.DId.eq(Long.valueOf(j)), HealthGpsDao.Properties.Year.eq(Integer.valueOf(i)), HealthGpsDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthGpsDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthGps> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthGpsDao.Properties.DId.eq(Long.valueOf(j)), HealthGpsDao.Properties.Year.eq(Integer.valueOf(i)), HealthGpsDao.Properties.Month.eq(Integer.valueOf(i2)), HealthGpsDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthGps healthGps) {
        if (healthGps == null) {
            return;
        }
        healthGps.setDId(j);
        b().update(healthGps);
    }
}
