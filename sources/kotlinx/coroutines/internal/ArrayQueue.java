package kotlinx.coroutines.internal;

import java.util.Objects;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public class ArrayQueue<T> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Object[] f14167a = new Object[16];
    public int b;
    public int c;

    public final void a() {
        Object[] objArr = this.f14167a;
        int length = objArr.length;
        Object[] objArr2 = new Object[length << 1];
        ArraysKt___ArraysJvmKt.copyInto$default(objArr, objArr2, 0, this.b, 0, 10, (Object) null);
        Object[] objArr3 = this.f14167a;
        int length2 = objArr3.length;
        int i = this.b;
        ArraysKt___ArraysJvmKt.copyInto$default(objArr3, objArr2, length2 - i, 0, i, 4, (Object) null);
        this.f14167a = objArr2;
        this.b = 0;
        this.c = length;
    }

    public final void addLast(@NotNull T t) {
        Object[] objArr = this.f14167a;
        int i = this.c;
        objArr[i] = t;
        int length = (objArr.length - 1) & (i + 1);
        this.c = length;
        if (length == this.b) {
            a();
        }
    }

    public final void clear() {
        this.b = 0;
        this.c = 0;
        this.f14167a = new Object[this.f14167a.length];
    }

    public final boolean isEmpty() {
        return this.b == this.c;
    }

    @Nullable
    public final T removeFirstOrNull() {
        int i = this.b;
        if (i == this.c) {
            return null;
        }
        Object[] objArr = this.f14167a;
        T t = (T) objArr[i];
        objArr[i] = null;
        this.b = (i + 1) & (objArr.length - 1);
        Objects.requireNonNull(t, "null cannot be cast to non-null type T of kotlinx.coroutines.internal.ArrayQueue");
        return t;
    }
}
