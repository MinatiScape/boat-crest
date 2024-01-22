package androidx.core.os;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import java.io.Serializable;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class BundleKt {
    @NotNull
    public static final Bundle bundleOf(@NotNull Pair<String, ? extends Object>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        Bundle bundle = new Bundle(pairs.length);
        for (Pair<String, ? extends Object> pair : pairs) {
            String component1 = pair.component1();
            Object component2 = pair.component2();
            if (component2 == null) {
                bundle.putString(component1, null);
            } else if (component2 instanceof Boolean) {
                bundle.putBoolean(component1, ((Boolean) component2).booleanValue());
            } else if (component2 instanceof Byte) {
                bundle.putByte(component1, ((Number) component2).byteValue());
            } else if (component2 instanceof Character) {
                bundle.putChar(component1, ((Character) component2).charValue());
            } else if (component2 instanceof Double) {
                bundle.putDouble(component1, ((Number) component2).doubleValue());
            } else if (component2 instanceof Float) {
                bundle.putFloat(component1, ((Number) component2).floatValue());
            } else if (component2 instanceof Integer) {
                bundle.putInt(component1, ((Number) component2).intValue());
            } else if (component2 instanceof Long) {
                bundle.putLong(component1, ((Number) component2).longValue());
            } else if (component2 instanceof Short) {
                bundle.putShort(component1, ((Number) component2).shortValue());
            } else if (component2 instanceof Bundle) {
                bundle.putBundle(component1, (Bundle) component2);
            } else if (component2 instanceof CharSequence) {
                bundle.putCharSequence(component1, (CharSequence) component2);
            } else if (component2 instanceof Parcelable) {
                bundle.putParcelable(component1, (Parcelable) component2);
            } else if (component2 instanceof boolean[]) {
                bundle.putBooleanArray(component1, (boolean[]) component2);
            } else if (component2 instanceof byte[]) {
                bundle.putByteArray(component1, (byte[]) component2);
            } else if (component2 instanceof char[]) {
                bundle.putCharArray(component1, (char[]) component2);
            } else if (component2 instanceof double[]) {
                bundle.putDoubleArray(component1, (double[]) component2);
            } else if (component2 instanceof float[]) {
                bundle.putFloatArray(component1, (float[]) component2);
            } else if (component2 instanceof int[]) {
                bundle.putIntArray(component1, (int[]) component2);
            } else if (component2 instanceof long[]) {
                bundle.putLongArray(component1, (long[]) component2);
            } else if (component2 instanceof short[]) {
                bundle.putShortArray(component1, (short[]) component2);
            } else if (component2 instanceof Object[]) {
                Class<?> componentType = component2.getClass().getComponentType();
                Intrinsics.checkNotNull(componentType);
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    Intrinsics.checkNotNull(component2, "null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                    bundle.putParcelableArray(component1, (Parcelable[]) component2);
                } else if (String.class.isAssignableFrom(componentType)) {
                    Intrinsics.checkNotNull(component2, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                    bundle.putStringArray(component1, (String[]) component2);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    Intrinsics.checkNotNull(component2, "null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                    bundle.putCharSequenceArray(component1, (CharSequence[]) component2);
                } else if (Serializable.class.isAssignableFrom(componentType)) {
                    bundle.putSerializable(component1, (Serializable) component2);
                } else {
                    throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + component1 + Typography.quote);
                }
            } else if (component2 instanceof Serializable) {
                bundle.putSerializable(component1, (Serializable) component2);
            } else {
                int i = Build.VERSION.SDK_INT;
                if (i >= 18 && (component2 instanceof IBinder)) {
                    a.a(bundle, component1, (IBinder) component2);
                } else if (i >= 21 && (component2 instanceof Size)) {
                    b.a(bundle, component1, (Size) component2);
                } else if (i >= 21 && (component2 instanceof SizeF)) {
                    b.b(bundle, component1, (SizeF) component2);
                } else {
                    throw new IllegalArgumentException("Illegal value type " + component2.getClass().getCanonicalName() + " for key \"" + component1 + Typography.quote);
                }
            }
        }
        return bundle;
    }

    @NotNull
    public static final Bundle bundleOf() {
        return new Bundle(0);
    }
}
