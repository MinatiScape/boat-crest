package androidx.constraintlayout.core;
/* loaded from: classes.dex */
public class b<T> implements a<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Object[] f857a;
    public int b;

    public b(int i) {
        if (i > 0) {
            this.f857a = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    @Override // androidx.constraintlayout.core.a
    public void a(T[] tArr, int i) {
        if (i > tArr.length) {
            i = tArr.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            T t = tArr[i2];
            int i3 = this.b;
            Object[] objArr = this.f857a;
            if (i3 < objArr.length) {
                objArr[i3] = t;
                this.b = i3 + 1;
            }
        }
    }

    @Override // androidx.constraintlayout.core.a
    public T acquire() {
        int i = this.b;
        if (i > 0) {
            int i2 = i - 1;
            Object[] objArr = this.f857a;
            T t = (T) objArr[i2];
            objArr[i2] = null;
            this.b = i - 1;
            return t;
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.a
    public boolean release(T t) {
        int i = this.b;
        Object[] objArr = this.f857a;
        if (i < objArr.length) {
            objArr[i] = t;
            this.b = i + 1;
            return true;
        }
        return false;
    }
}
