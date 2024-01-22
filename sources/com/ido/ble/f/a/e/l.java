package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Dao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class l extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.a<HealthSpO2> {

    /* renamed from: a  reason: collision with root package name */
    private static l f12242a;

    private l() {
    }

    private HealthSpO2Dao b() {
        return a().getHealthSpO2Dao();
    }

    public static synchronized l c() {
        l lVar;
        synchronized (l.class) {
            if (f12242a == null) {
                f12242a = new l();
            }
            lVar = f12242a;
        }
        return lVar;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthSpO2 a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2Dao.Properties.Year.eq(Integer.valueOf(i)), HealthSpO2Dao.Properties.Month.eq(Integer.valueOf(i2)), HealthSpO2Dao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2Dao.Properties.Date);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSpO2> a(long j, int i) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2Dao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2Dao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSpO2> a(long j, int i, int i2) {
        Date b = b(i, i2);
        Date a2 = a(i, i2);
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthSpO2Dao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.ge(b), property.le(a2)), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSpO2> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2Dao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void b(long j, HealthSpO2 healthSpO2) {
        if (healthSpO2 == null) {
            return;
        }
        HealthSpO2 a2 = a(j, healthSpO2.getYear(), healthSpO2.getMonth(), healthSpO2.getDay());
        if (a2 == null) {
            healthSpO2.setDId(j);
            b().insert(healthSpO2);
            return;
        }
        healthSpO2.setId(a2.getId());
        a(j, healthSpO2);
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSpO2> b(long j, int i, int i2) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2Dao.Properties.Year.eq(Integer.valueOf(i)), HealthSpO2Dao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2Dao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2Dao.Properties.Year.eq(Integer.valueOf(i)), HealthSpO2Dao.Properties.Month.eq(Integer.valueOf(i2)), HealthSpO2Dao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* renamed from: b  reason: avoid collision after fix types in other method */
    public void a(long j, HealthSpO2 healthSpO2) {
        if (healthSpO2 == null || healthSpO2.getId() == null) {
            return;
        }
        healthSpO2.setId(Long.valueOf(j));
        b().update(healthSpO2);
    }
}
