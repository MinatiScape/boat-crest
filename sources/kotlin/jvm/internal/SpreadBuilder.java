package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
/* loaded from: classes12.dex */
public class SpreadBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<Object> f14083a;

    public SpreadBuilder(int i) {
        this.f14083a = new ArrayList<>(i);
    }

    public void add(Object obj) {
        this.f14083a.add(obj);
    }

    public void addSpread(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr.length > 0) {
                ArrayList<Object> arrayList = this.f14083a;
                arrayList.ensureCapacity(arrayList.size() + objArr.length);
                Collections.addAll(this.f14083a, objArr);
            }
        } else if (obj instanceof Collection) {
            this.f14083a.addAll((Collection) obj);
        } else if (obj instanceof Iterable) {
            for (Object obj2 : (Iterable) obj) {
                this.f14083a.add(obj2);
            }
        } else if (obj instanceof Iterator) {
            Iterator it = (Iterator) obj;
            while (it.hasNext()) {
                this.f14083a.add(it.next());
            }
        } else {
            throw new UnsupportedOperationException("Don't know how to spread " + obj.getClass());
        }
    }

    public int size() {
        return this.f14083a.size();
    }

    public Object[] toArray(Object[] objArr) {
        return this.f14083a.toArray(objArr);
    }
}
