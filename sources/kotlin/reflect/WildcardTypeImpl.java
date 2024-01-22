package kotlin.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import kotlin.ExperimentalStdlibApi;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@ExperimentalStdlibApi
/* loaded from: classes12.dex */
public final class WildcardTypeImpl implements WildcardType, Type {
    @NotNull
    public static final Companion j = new Companion(null);
    @NotNull
    public static final WildcardTypeImpl k = new WildcardTypeImpl(null, null);
    @Nullable
    public final Type h;
    @Nullable
    public final Type i;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final WildcardTypeImpl getSTAR() {
            return WildcardTypeImpl.k;
        }
    }

    public WildcardTypeImpl(@Nullable Type type, @Nullable Type type2) {
        this.h = type;
        this.i = type2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) obj;
            if (Arrays.equals(getUpperBounds(), wildcardType.getUpperBounds()) && Arrays.equals(getLowerBounds(), wildcardType.getLowerBounds())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.reflect.WildcardType
    @NotNull
    public Type[] getLowerBounds() {
        Type type = this.i;
        return type == null ? new Type[0] : new Type[]{type};
    }

    @Override // java.lang.reflect.Type
    @NotNull
    public String getTypeName() {
        String e;
        String e2;
        if (this.i != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("? super ");
            e2 = TypesJVMKt.e(this.i);
            sb.append(e2);
            return sb.toString();
        }
        Type type = this.h;
        if (type == null || Intrinsics.areEqual(type, Object.class)) {
            return "?";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("? extends ");
        e = TypesJVMKt.e(this.h);
        sb2.append(e);
        return sb2.toString();
    }

    @Override // java.lang.reflect.WildcardType
    @NotNull
    public Type[] getUpperBounds() {
        Type[] typeArr = new Type[1];
        Type type = this.h;
        if (type == null) {
            type = Object.class;
        }
        typeArr[0] = type;
        return typeArr;
    }

    public int hashCode() {
        return Arrays.hashCode(getUpperBounds()) ^ Arrays.hashCode(getLowerBounds());
    }

    @NotNull
    public String toString() {
        return getTypeName();
    }
}
