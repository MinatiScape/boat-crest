package com.ido.ble.f.a.d.j;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public interface b<T> {
    List<T> a(long j, int i);

    List<T> a(long j, int i, int i2);

    List<T> a(long j, int i, int i2, int i3);

    List<T> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr);

    void a(long j, int i, int i2, int i3, int i4, int i5, int i6);

    void a(long j, T t);

    void a(long j, Date date, Date date2);

    void a(long j, List<T> list);

    List<T> b(long j, int i, int i2);

    List<T> b(long j, int i, int i2, int i3, int i4, int i5, int i6);

    List<T> b(long j, Date date, Date date2);

    void b(long j, int i, int i2, int i3);

    void b(long j, T t);

    void b(long j, List<T> list);
}
