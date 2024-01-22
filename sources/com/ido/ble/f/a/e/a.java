package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthActivityDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class a extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.e<HealthActivity> {

    /* renamed from: a  reason: collision with root package name */
    private static a f12231a;

    private a() {
    }

    private HealthActivityDao b() {
        return a().getHealthActivityDao();
    }

    public static synchronized a c() {
        a aVar;
        synchronized (a.class) {
            if (f12231a == null) {
                f12231a = new a();
            }
            aVar = f12231a;
        }
        return aVar;
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> a(long j, int i) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthActivityDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthActivityDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthActivityDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), HealthActivityDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), HealthActivityDao.Properties.Day.eq(Integer.valueOf(i3)), HealthActivityDao.Properties.Hour.eq(Integer.valueOf(i4)), HealthActivityDao.Properties.Minute.eq(Integer.valueOf(i5)), HealthActivityDao.Properties.Second.eq(Integer.valueOf(i6))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.e
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthActivity healthActivity) {
        if (healthActivity == null) {
            return;
        }
        HealthActivity b = b(j, healthActivity.getYear(), healthActivity.getMonth(), healthActivity.getDay(), healthActivity.getHour(), healthActivity.getMinute(), healthActivity.getSecond());
        if (b == null) {
            healthActivity.setDId(j);
            b().insert(healthActivity);
            return;
        }
        healthActivity.setActivityId(b.getActivityId());
        a(j, healthActivity);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.e
    public HealthActivity b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), HealthActivityDao.Properties.Day.eq(Integer.valueOf(i3)), HealthActivityDao.Properties.Hour.eq(Integer.valueOf(i4)), HealthActivityDao.Properties.Minute.eq(Integer.valueOf(i5)), HealthActivityDao.Properties.Second.eq(Integer.valueOf(i6))), new WhereCondition[0]);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> b(long j, int i, int i2) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthActivityDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), HealthActivityDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.e
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthActivity healthActivity) {
        if (healthActivity == null || healthActivity.getActivityId() == null) {
            return;
        }
        healthActivity.setDId(j);
        b().update(healthActivity);
    }
}
