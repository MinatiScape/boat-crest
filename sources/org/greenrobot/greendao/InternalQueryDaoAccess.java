package org.greenrobot.greendao;

import android.database.Cursor;
import java.util.List;
import org.greenrobot.greendao.internal.TableStatements;
/* loaded from: classes13.dex */
public final class InternalQueryDaoAccess<T> {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractDao<T, ?> f15468a;

    public InternalQueryDaoAccess(AbstractDao<T, ?> abstractDao) {
        this.f15468a = abstractDao;
    }

    public TableStatements getStatements() {
        return this.f15468a.getStatements();
    }

    public List<T> loadAllAndCloseCursor(Cursor cursor) {
        return this.f15468a.loadAllAndCloseCursor(cursor);
    }

    public T loadCurrent(Cursor cursor, int i, boolean z) {
        return this.f15468a.loadCurrent(cursor, i, z);
    }

    public T loadUniqueAndCloseCursor(Cursor cursor) {
        return this.f15468a.loadUniqueAndCloseCursor(cursor);
    }

    public static <T2> TableStatements getStatements(AbstractDao<T2, ?> abstractDao) {
        return abstractDao.getStatements();
    }
}
