package kotlin.jvm.internal;

import kotlin.KotlinNothingValueException;
import kotlin.SinceKotlin;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SinceKotlin(version = "1.1")
/* loaded from: classes12.dex */
public class MutableLocalVariableReference extends MutablePropertyReference0 {
    @Override // kotlin.reflect.KProperty0
    @Nullable
    public Object get() {
        LocalVariableReferencesKt.a();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.jvm.internal.CallableReference
    @NotNull
    public KDeclarationContainer getOwner() {
        LocalVariableReferencesKt.a();
        throw new KotlinNothingValueException();
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(@Nullable Object obj) {
        LocalVariableReferencesKt.a();
        throw new KotlinNothingValueException();
    }
}
