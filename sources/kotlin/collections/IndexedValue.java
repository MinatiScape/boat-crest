package kotlin.collections;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class IndexedValue<T> {

    /* renamed from: a  reason: collision with root package name */
    public final int f14059a;
    public final T b;

    public IndexedValue(int i, T t) {
        this.f14059a = i;
        this.b = t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IndexedValue copy$default(IndexedValue indexedValue, int i, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = indexedValue.f14059a;
        }
        if ((i2 & 2) != 0) {
            obj = indexedValue.b;
        }
        return indexedValue.copy(i, obj);
    }

    public final int component1() {
        return this.f14059a;
    }

    public final T component2() {
        return this.b;
    }

    @NotNull
    public final IndexedValue<T> copy(int i, T t) {
        return new IndexedValue<>(i, t);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IndexedValue) {
            IndexedValue indexedValue = (IndexedValue) obj;
            return this.f14059a == indexedValue.f14059a && Intrinsics.areEqual(this.b, indexedValue.b);
        }
        return false;
    }

    public final int getIndex() {
        return this.f14059a;
    }

    public final T getValue() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f14059a) * 31;
        T t = this.b;
        return hashCode + (t == null ? 0 : t.hashCode());
    }

    @NotNull
    public String toString() {
        return "IndexedValue(index=" + this.f14059a + ", value=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }
}
