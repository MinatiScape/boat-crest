package okio;

import java.util.ArrayList;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.s;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class FileMetadata {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f14313a;
    public final boolean b;
    @Nullable
    public final Path c;
    @Nullable
    public final Long d;
    @Nullable
    public final Long e;
    @Nullable
    public final Long f;
    @Nullable
    public final Long g;
    @NotNull
    public final Map<KClass<?>, Object> h;

    public FileMetadata() {
        this(false, false, null, null, null, null, null, null, 255, null);
    }

    public FileMetadata(boolean z, boolean z2, @Nullable Path path, @Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, @NotNull Map<KClass<?>, ? extends Object> extras) {
        Intrinsics.checkNotNullParameter(extras, "extras");
        this.f14313a = z;
        this.b = z2;
        this.c = path;
        this.d = l;
        this.e = l2;
        this.f = l3;
        this.g = l4;
        this.h = s.toMap(extras);
    }

    @NotNull
    public final FileMetadata copy(boolean z, boolean z2, @Nullable Path path, @Nullable Long l, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, @NotNull Map<KClass<?>, ? extends Object> extras) {
        Intrinsics.checkNotNullParameter(extras, "extras");
        return new FileMetadata(z, z2, path, l, l2, l3, l4, extras);
    }

    @Nullable
    public final <T> T extra(@NotNull KClass<? extends T> type) {
        Intrinsics.checkNotNullParameter(type, "type");
        Object obj = this.h.get(type);
        if (obj == null) {
            return null;
        }
        return (T) KClasses.cast(type, obj);
    }

    @Nullable
    public final Long getCreatedAtMillis() {
        return this.e;
    }

    @NotNull
    public final Map<KClass<?>, Object> getExtras() {
        return this.h;
    }

    @Nullable
    public final Long getLastAccessedAtMillis() {
        return this.g;
    }

    @Nullable
    public final Long getLastModifiedAtMillis() {
        return this.f;
    }

    @Nullable
    public final Long getSize() {
        return this.d;
    }

    @Nullable
    public final Path getSymlinkTarget() {
        return this.c;
    }

    public final boolean isDirectory() {
        return this.b;
    }

    public final boolean isRegularFile() {
        return this.f14313a;
    }

    @NotNull
    public String toString() {
        ArrayList arrayList = new ArrayList();
        if (this.f14313a) {
            arrayList.add("isRegularFile");
        }
        if (this.b) {
            arrayList.add("isDirectory");
        }
        if (this.d != null) {
            arrayList.add("byteCount=" + this.d);
        }
        if (this.e != null) {
            arrayList.add("createdAt=" + this.e);
        }
        if (this.f != null) {
            arrayList.add("lastModifiedAt=" + this.f);
        }
        if (this.g != null) {
            arrayList.add("lastAccessedAt=" + this.g);
        }
        if (!this.h.isEmpty()) {
            arrayList.add("extras=" + this.h);
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList, ", ", "FileMetadata(", ")", 0, null, null, 56, null);
    }

    public /* synthetic */ FileMetadata(boolean z, boolean z2, Path path, Long l, Long l2, Long l3, Long l4, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) == 0 ? z2 : false, (i & 4) != 0 ? null : path, (i & 8) != 0 ? null : l, (i & 16) != 0 ? null : l2, (i & 32) != 0 ? null : l3, (i & 64) == 0 ? l4 : null, (i & 128) != 0 ? s.emptyMap() : map);
    }
}
