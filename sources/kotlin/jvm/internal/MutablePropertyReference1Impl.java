package kotlin.jvm.internal;

import com.goodix.ble.gr.libdfu.BuildConfig;
import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
/* loaded from: classes12.dex */
public class MutablePropertyReference1Impl extends MutablePropertyReference1 {
    public MutablePropertyReference1Impl(KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(CallableReference.NO_RECEIVER, ((ClassBasedDeclarationContainer) kDeclarationContainer).getJClass(), str, str2, !(kDeclarationContainer instanceof KClass) ? 1 : 0);
    }

    @Override // kotlin.reflect.KProperty1
    public Object get(Object obj) {
        return getGetter().call(obj);
    }

    @Override // kotlin.reflect.KMutableProperty1
    public void set(Object obj, Object obj2) {
        getSetter().call(obj, obj2);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public MutablePropertyReference1Impl(Class cls, String str, String str2, int i) {
        super(CallableReference.NO_RECEIVER, cls, str, str2, i);
    }

    @SinceKotlin(version = BuildConfig.VERSION_NAME)
    public MutablePropertyReference1Impl(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
