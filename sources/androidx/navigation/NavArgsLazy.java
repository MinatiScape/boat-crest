package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.NavArgs;
import java.lang.reflect.Method;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B#\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0005\u001a\u00020\u0004H\u0016R\u0016\u0010\b\u001a\u00028\u00008V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/navigation/NavArgsLazy;", "Landroidx/navigation/NavArgs;", "Args", "Lkotlin/Lazy;", "", "isInitialized", "getValue", "()Landroidx/navigation/NavArgs;", "value", "Lkotlin/reflect/KClass;", "navArgsClass", "Lkotlin/Function0;", "Landroid/os/Bundle;", "argumentProducer", "<init>", "(Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)V", "navigation-common-ktx_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class NavArgsLazy<Args extends NavArgs> implements Lazy<Args> {
    public Args h;
    public final KClass<Args> i;
    public final Function0<Bundle> j;

    public NavArgsLazy(@NotNull KClass<Args> navArgsClass, @NotNull Function0<Bundle> argumentProducer) {
        Intrinsics.checkParameterIsNotNull(navArgsClass, "navArgsClass");
        Intrinsics.checkParameterIsNotNull(argumentProducer, "argumentProducer");
        this.i = navArgsClass;
        this.j = argumentProducer;
    }

    @Override // kotlin.Lazy
    public boolean isInitialized() {
        return this.h != null;
    }

    @Override // kotlin.Lazy
    @NotNull
    public Args getValue() {
        Args args = this.h;
        if (args == null) {
            Bundle invoke = this.j.invoke();
            Method method = NavArgsLazyKt.getMethodMap().get(this.i);
            if (method == null) {
                Class javaClass = JvmClassMappingKt.getJavaClass((KClass) this.i);
                Class<Bundle>[] methodSignature = NavArgsLazyKt.getMethodSignature();
                method = javaClass.getMethod("fromBundle", (Class[]) Arrays.copyOf(methodSignature, methodSignature.length));
                NavArgsLazyKt.getMethodMap().put(this.i, method);
                Intrinsics.checkExpressionValueIsNotNull(method, "navArgsClass.java.getMet…hod\n                    }");
            }
            Object invoke2 = method.invoke(null, invoke);
            if (invoke2 != null) {
                Args args2 = (Args) invoke2;
                this.h = args2;
                return args2;
            }
            throw new TypeCastException("null cannot be cast to non-null type Args");
        }
        return args;
    }
}
