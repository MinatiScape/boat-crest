package com.ido.ble.f.a.d.j;

import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
/* loaded from: classes11.dex */
public interface a<T> {
    T a(long j, int i, int i2, int i3);

    List<T> a(long j, int i);

    List<T> a(long j, int i, int i2);

    List<T> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr);

    void a(long j, T t);

    List<T> b(long j, int i, int i2);

    void b(long j, int i, int i2, int i3);

    void b(long j, T t);
}
