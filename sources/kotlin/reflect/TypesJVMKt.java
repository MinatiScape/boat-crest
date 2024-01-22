package kotlin.reflect;

import com.goodix.ble.gr.libdfu.BuildConfig;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.NoWhenBranchMatchedException;
import kotlin.SinceKotlin;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.f;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.KTypeBase;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlin.text.m;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class TypesJVMKt {

    /* loaded from: classes12.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[KVariance.values().length];
            try {
                iArr[KVariance.IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[KVariance.INVARIANT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[KVariance.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* loaded from: classes12.dex */
    public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Class<?>, Class<?>> {
        public static final a INSTANCE = new a();

        public a() {
            super(1, Class.class, "getComponentType", "getComponentType()Ljava/lang/Class;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Class<?> invoke(@NotNull Class<?> p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return p0.getComponentType();
        }
    }

    @ExperimentalStdlibApi
    public static final Type a(KType kType, boolean z) {
        KClassifier classifier = kType.getClassifier();
        if (classifier instanceof KTypeParameter) {
            return new c((KTypeParameter) classifier);
        }
        if (classifier instanceof KClass) {
            KClass kClass = (KClass) classifier;
            Class javaObjectType = z ? JvmClassMappingKt.getJavaObjectType(kClass) : JvmClassMappingKt.getJavaClass(kClass);
            List<KTypeProjection> arguments = kType.getArguments();
            if (arguments.isEmpty()) {
                return javaObjectType;
            }
            if (javaObjectType.isArray()) {
                if (javaObjectType.getComponentType().isPrimitive()) {
                    return javaObjectType;
                }
                KTypeProjection kTypeProjection = (KTypeProjection) CollectionsKt___CollectionsKt.singleOrNull((List<? extends Object>) arguments);
                if (kTypeProjection != null) {
                    KVariance component1 = kTypeProjection.component1();
                    KType component2 = kTypeProjection.component2();
                    int i = component1 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[component1.ordinal()];
                    if (i == -1 || i == 1) {
                        return javaObjectType;
                    }
                    if (i != 2 && i != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    Intrinsics.checkNotNull(component2);
                    Type b = b(component2, false, 1, null);
                    return b instanceof Class ? javaObjectType : new kotlin.reflect.a(b);
                }
                throw new IllegalArgumentException("kotlin.Array must have exactly one type argument: " + kType);
            }
            return c(javaObjectType, arguments);
        }
        throw new UnsupportedOperationException("Unsupported type classifier: " + kType);
    }

    public static /* synthetic */ Type b(KType kType, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return a(kType, z);
    }

    @ExperimentalStdlibApi
    public static final Type c(Class<?> cls, List<KTypeProjection> list) {
        Class<?> declaringClass = cls.getDeclaringClass();
        if (declaringClass == null) {
            ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
            for (KTypeProjection kTypeProjection : list) {
                arrayList.add(d(kTypeProjection));
            }
            return new b(cls, null, arrayList);
        } else if (Modifier.isStatic(cls.getModifiers())) {
            ArrayList arrayList2 = new ArrayList(f.collectionSizeOrDefault(list, 10));
            for (KTypeProjection kTypeProjection2 : list) {
                arrayList2.add(d(kTypeProjection2));
            }
            return new b(cls, declaringClass, arrayList2);
        } else {
            int length = cls.getTypeParameters().length;
            Type c = c(declaringClass, list.subList(length, list.size()));
            List<KTypeProjection> subList = list.subList(0, length);
            ArrayList arrayList3 = new ArrayList(f.collectionSizeOrDefault(subList, 10));
            for (KTypeProjection kTypeProjection3 : subList) {
                arrayList3.add(d(kTypeProjection3));
            }
            return new b(cls, c, arrayList3);
        }
    }

    public static final Type d(KTypeProjection kTypeProjection) {
        KVariance variance = kTypeProjection.getVariance();
        if (variance == null) {
            return WildcardTypeImpl.j.getSTAR();
        }
        KType type = kTypeProjection.getType();
        Intrinsics.checkNotNull(type);
        int i = WhenMappings.$EnumSwitchMapping$0[variance.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return new WildcardTypeImpl(a(type, true), null);
                }
                throw new NoWhenBranchMatchedException();
            }
            return a(type, true);
        }
        return new WildcardTypeImpl(null, a(type, true));
    }

    public static final String e(Type type) {
        String name;
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (cls.isArray()) {
                Sequence generateSequence = SequencesKt__SequencesKt.generateSequence(type, a.INSTANCE);
                name = ((Class) SequencesKt___SequencesKt.last(generateSequence)).getName() + m.repeat(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, SequencesKt___SequencesKt.count(generateSequence));
            } else {
                name = cls.getName();
            }
            Intrinsics.checkNotNullExpressionValue(name, "{\n        if (type.isArr…   } else type.name\n    }");
            return name;
        }
        return type.toString();
    }

    @NotNull
    public static final Type getJavaType(@NotNull KType kType) {
        Type javaType;
        Intrinsics.checkNotNullParameter(kType, "<this>");
        return (!(kType instanceof KTypeBase) || (javaType = ((KTypeBase) kType).getJavaType()) == null) ? b(kType, false, 1, null) : javaType;
    }

    @LowPriorityInOverloadResolution
    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    @ExperimentalStdlibApi
    public static /* synthetic */ void getJavaType$annotations(KType kType) {
    }
}
