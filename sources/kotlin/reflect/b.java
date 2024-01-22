package kotlin.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@ExperimentalStdlibApi
/* loaded from: classes12.dex */
public final class b implements ParameterizedType, Type {
    @NotNull
    public final Class<?> h;
    @Nullable
    public final Type i;
    @NotNull
    public final Type[] j;

    /* loaded from: classes12.dex */
    public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Type, String> {
        public static final a INSTANCE = new a();

        public a() {
            super(1, TypesJVMKt.class, "typeToString", "typeToString(Ljava/lang/reflect/Type;)Ljava/lang/String;", 1);
        }

        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public final String invoke(@NotNull Type p0) {
            String e;
            Intrinsics.checkNotNullParameter(p0, "p0");
            e = TypesJVMKt.e(p0);
            return e;
        }
    }

    public b(@NotNull Class<?> rawType, @Nullable Type type, @NotNull List<? extends Type> typeArguments) {
        Intrinsics.checkNotNullParameter(rawType, "rawType");
        Intrinsics.checkNotNullParameter(typeArguments, "typeArguments");
        this.h = rawType;
        this.i = type;
        this.j = (Type[]) typeArguments.toArray(new Type[0]);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) obj;
            if (Intrinsics.areEqual(this.h, parameterizedType.getRawType()) && Intrinsics.areEqual(this.i, parameterizedType.getOwnerType()) && Arrays.equals(getActualTypeArguments(), parameterizedType.getActualTypeArguments())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.reflect.ParameterizedType
    @NotNull
    public Type[] getActualTypeArguments() {
        return this.j;
    }

    @Override // java.lang.reflect.ParameterizedType
    @Nullable
    public Type getOwnerType() {
        return this.i;
    }

    @Override // java.lang.reflect.ParameterizedType
    @NotNull
    public Type getRawType() {
        return this.h;
    }

    @Override // java.lang.reflect.Type
    @NotNull
    public String getTypeName() {
        String e;
        String e2;
        StringBuilder sb = new StringBuilder();
        Type type = this.i;
        if (type != null) {
            e2 = TypesJVMKt.e(type);
            sb.append(e2);
            sb.append("$");
            sb.append(this.h.getSimpleName());
        } else {
            e = TypesJVMKt.e(this.h);
            sb.append(e);
        }
        Type[] typeArr = this.j;
        if (!(typeArr.length == 0)) {
            ArraysKt___ArraysKt.joinTo(typeArr, sb, (r14 & 2) != 0 ? ", " : null, (r14 & 4) != 0 ? "" : "<", (r14 & 8) == 0 ? ">" : "", (r14 & 16) != 0 ? -1 : 0, (r14 & 32) != 0 ? "..." : null, (r14 & 64) != 0 ? null : a.INSTANCE);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    public int hashCode() {
        int hashCode = this.h.hashCode();
        Type type = this.i;
        return (hashCode ^ (type != null ? type.hashCode() : 0)) ^ Arrays.hashCode(getActualTypeArguments());
    }

    @NotNull
    public String toString() {
        return getTypeName();
    }
}
