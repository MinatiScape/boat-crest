package kotlin.enums;

import java.io.Serializable;
import java.lang.Enum;
import kotlin.ExperimentalStdlibApi;
import kotlin.SinceKotlin;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.8")
@ExperimentalStdlibApi
/* loaded from: classes12.dex */
public final class a<T extends Enum<T>> extends AbstractList<T> implements EnumEntries<T>, Serializable {
    @Nullable
    private volatile T[] _entries;
    @NotNull
    private final Function0<T[]> entriesProvider;

    public a(@NotNull Function0<T[]> entriesProvider) {
        Intrinsics.checkNotNullParameter(entriesProvider, "entriesProvider");
        this.entriesProvider = entriesProvider;
    }

    private final T[] getEntries() {
        T[] tArr = this._entries;
        if (tArr != null) {
            return tArr;
        }
        T[] invoke = this.entriesProvider.invoke();
        this._entries = invoke;
        return invoke;
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(getEntries());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Enum) {
            return contains((a<T>) ((Enum) obj));
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return getEntries().length;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Enum) {
            return indexOf((a<T>) ((Enum) obj));
        }
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Enum) {
            return lastIndexOf((a<T>) ((Enum) obj));
        }
        return -1;
    }

    public boolean contains(@NotNull T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return ((Enum) ArraysKt___ArraysKt.getOrNull(getEntries(), element.ordinal())) == element;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public T get(int i) {
        T[] entries = getEntries();
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(i, entries.length);
        return entries[i];
    }

    public int indexOf(@NotNull T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        int ordinal = element.ordinal();
        if (((Enum) ArraysKt___ArraysKt.getOrNull(getEntries(), ordinal)) == element) {
            return ordinal;
        }
        return -1;
    }

    public int lastIndexOf(@NotNull T element) {
        Intrinsics.checkNotNullParameter(element, "element");
        return indexOf((Object) element);
    }
}
