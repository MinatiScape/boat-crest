package org.greenrobot.greendao.query;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.a;
/* loaded from: classes13.dex */
public abstract class b<T, Q extends a<T>> {

    /* renamed from: a  reason: collision with root package name */
    public final String f15489a;
    public final AbstractDao<T, ?> b;
    public final String[] c;
    public final Map<Long, WeakReference<Q>> d = new HashMap();

    public b(AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        this.b = abstractDao;
        this.f15489a = str;
        this.c = strArr;
    }

    public abstract Q a();

    public Q b() {
        Q q;
        long id = Thread.currentThread().getId();
        synchronized (this.d) {
            WeakReference<Q> weakReference = this.d.get(Long.valueOf(id));
            q = weakReference != null ? weakReference.get() : null;
            if (q == null) {
                d();
                q = a();
                this.d.put(Long.valueOf(id), new WeakReference<>(q));
            } else {
                String[] strArr = this.c;
                System.arraycopy(strArr, 0, q.parameters, 0, strArr.length);
            }
        }
        return q;
    }

    public Q c(Q q) {
        if (Thread.currentThread() == q.ownerThread) {
            String[] strArr = this.c;
            System.arraycopy(strArr, 0, q.parameters, 0, strArr.length);
            return q;
        }
        return b();
    }

    public void d() {
        synchronized (this.d) {
            Iterator<Map.Entry<Long, WeakReference<Q>>> it = this.d.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getValue().get() == null) {
                    it.remove();
                }
            }
        }
    }
}
