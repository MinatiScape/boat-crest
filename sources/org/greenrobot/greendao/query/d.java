package org.greenrobot.greendao.query;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes13.dex */
public class d<T> {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractDao<T, ?> f15490a;
    public final List<WhereCondition> b = new ArrayList();
    public final String c;

    public d(AbstractDao<T, ?> abstractDao, String str) {
        this.f15490a = abstractDao;
        this.c = str;
    }

    public void a(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        d(whereCondition);
        this.b.add(whereCondition);
        for (WhereCondition whereCondition2 : whereConditionArr) {
            d(whereCondition2);
            this.b.add(whereCondition2);
        }
    }

    public void b(StringBuilder sb, List<Object> list, WhereCondition whereCondition) {
        d(whereCondition);
        whereCondition.appendTo(sb, this.c);
        whereCondition.appendValuesTo(list);
    }

    public void c(StringBuilder sb, String str, List<Object> list) {
        ListIterator<WhereCondition> listIterator = this.b.listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.hasPrevious()) {
                sb.append(" AND ");
            }
            WhereCondition next = listIterator.next();
            next.appendTo(sb, str);
            next.appendValuesTo(list);
        }
    }

    public void d(WhereCondition whereCondition) {
        if (whereCondition instanceof WhereCondition.PropertyCondition) {
            e(((WhereCondition.PropertyCondition) whereCondition).property);
        }
    }

    public void e(Property property) {
        AbstractDao<T, ?> abstractDao = this.f15490a;
        if (abstractDao != null) {
            Property[] properties = abstractDao.getProperties();
            int length = properties.length;
            boolean z = false;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (property == properties[i]) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (z) {
                return;
            }
            throw new DaoException("Property '" + property.name + "' is not part of " + this.f15490a);
        }
    }

    public WhereCondition f(String str, WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        StringBuilder sb = new StringBuilder("(");
        ArrayList arrayList = new ArrayList();
        b(sb, arrayList, whereCondition);
        sb.append(str);
        b(sb, arrayList, whereCondition2);
        for (WhereCondition whereCondition3 : whereConditionArr) {
            sb.append(str);
            b(sb, arrayList, whereCondition3);
        }
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        return new WhereCondition.StringCondition(sb.toString(), arrayList.toArray());
    }

    public boolean g() {
        return this.b.isEmpty();
    }
}
