package androidx.navigation;

import androidx.navigation.NavArgument;
import com.google.android.material.color.c;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@NavDestinationDsl
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0006\u0010\u0003\u001a\u00020\u0002R*\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00048\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR.\u0010\u0013\u001a\u0004\u0018\u00010\u00012\b\u0010\u0005\u001a\u0004\u0018\u00010\u00018\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R,\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u00142\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00148F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Landroidx/navigation/NavArgumentBuilder;", "", "Landroidx/navigation/NavArgument;", "build", "", "value", c.f10260a, "Z", "getNullable", "()Z", "setNullable", "(Z)V", "nullable", "d", "Ljava/lang/Object;", "getDefaultValue", "()Ljava/lang/Object;", "setDefaultValue", "(Ljava/lang/Object;)V", "defaultValue", "Landroidx/navigation/NavType;", "getType", "()Landroidx/navigation/NavType;", "setType", "(Landroidx/navigation/NavType;)V", "type", "<init>", "()V", "navigation-common-ktx_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class NavArgumentBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final NavArgument.Builder f1435a = new NavArgument.Builder();
    public NavType<?> b;
    public boolean c;
    @Nullable
    public Object d;

    @NotNull
    public final NavArgument build() {
        NavArgument build = this.f1435a.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "builder.build()");
        return build;
    }

    @Nullable
    public final Object getDefaultValue() {
        return this.d;
    }

    public final boolean getNullable() {
        return this.c;
    }

    @NotNull
    public final NavType<?> getType() {
        NavType<?> navType = this.b;
        if (navType != null) {
            return navType;
        }
        throw new IllegalStateException("NavType has not been set on this builder.");
    }

    public final void setDefaultValue(@Nullable Object obj) {
        this.d = obj;
        this.f1435a.setDefaultValue(obj);
    }

    public final void setNullable(boolean z) {
        this.c = z;
        this.f1435a.setIsNullable(z);
    }

    public final void setType(@NotNull NavType<?> value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.b = value;
        this.f1435a.setType(value);
    }
}
