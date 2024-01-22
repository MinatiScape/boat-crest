package com.ido.ble.gps.database.b;

import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.gps.database.HealthGpsItemDao;
import java.util.List;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public class b extends com.ido.ble.f.a.e.p.a {

    /* renamed from: a  reason: collision with root package name */
    private static b f12299a;

    private b() {
    }

    private HealthGpsItemDao b() {
        return a().getHealthGpsItemDao();
    }

    public static b c() {
        if (f12299a == null) {
            f12299a = new b();
        }
        return f12299a;
    }

    public List<HealthGpsItem> a(long j, long j2) {
        QueryBuilder<HealthGpsItem> queryBuilder = b().queryBuilder();
        WhereCondition eq = HealthGpsItemDao.Properties.DId.eq(Long.valueOf(j));
        Property property = HealthGpsItemDao.Properties.Date;
        queryBuilder.where(queryBuilder.and(eq, property.eq(Long.valueOf(j2)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(property);
        return queryBuilder.list();
    }

    public List<HealthGpsItem> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthGpsItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthGpsItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthGpsItemDao.Properties.Date);
        return queryBuilder.list();
    }

    public void a(long j, HealthGpsItem healthGpsItem) {
        if (healthGpsItem == null) {
            return;
        }
        healthGpsItem.setDId(j);
        b().insert(healthGpsItem);
    }

    public void a(long j, List<HealthGpsItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (HealthGpsItem healthGpsItem : list) {
            healthGpsItem.setDId(j);
        }
        b().insertInTx(list);
    }
}
