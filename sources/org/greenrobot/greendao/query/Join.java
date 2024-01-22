package org.greenrobot.greendao.query;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
/* loaded from: classes13.dex */
public class Join<SRC, DST> {

    /* renamed from: a  reason: collision with root package name */
    public final String f15486a;
    public final AbstractDao<DST, ?> b;
    public final Property c;
    public final Property d;
    public final String e;
    public final d<DST> f;

    public Join(String str, Property property, AbstractDao<DST, ?> abstractDao, Property property2, String str2) {
        this.f15486a = str;
        this.c = property;
        this.b = abstractDao;
        this.d = property2;
        this.e = str2;
        this.f = new d<>(abstractDao, str2);
    }

    public WhereCondition and(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.f.f(" AND ", whereCondition, whereCondition2, whereConditionArr);
    }

    public String getTablePrefix() {
        return this.e;
    }

    public WhereCondition or(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.f.f(" OR ", whereCondition, whereCondition2, whereConditionArr);
    }

    public Join<SRC, DST> where(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        this.f.a(whereCondition, whereConditionArr);
        return this;
    }

    public Join<SRC, DST> whereOr(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        this.f.a(or(whereCondition, whereCondition2, whereConditionArr), new WhereCondition[0]);
        return this;
    }
}
