package io.shipbook.shipbooksdk.Util;

import android.widget.CompoundButton;
import io.shipbook.shipbooksdk.InnerLog;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u00008@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroid/widget/CompoundButton;", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "getOnCheckedChangeListener", "(Landroid/widget/CompoundButton;)Landroid/widget/CompoundButton$OnCheckedChangeListener;", "onCheckedChangeListener", "shipbooksdk_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class CompoundButtonExtKt {
    @Nullable
    public static final CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener(@NotNull CompoundButton receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        try {
            Field declaredField = Class.forName("android.widget.CompoundButton").getDeclaredField("mOnCheckedChangeListener");
            if (declaredField != null) {
                declaredField.setAccessible(true);
                Object obj = declaredField.get(receiver$0);
                if (!(obj instanceof CompoundButton.OnCheckedChangeListener)) {
                    obj = null;
                }
                return (CompoundButton.OnCheckedChangeListener) obj;
            }
            return null;
        } catch (ClassNotFoundException unused) {
            InnerLog.e$default(InnerLog.INSTANCE, "Reflection", "Class Not Found.", null, 4, null);
            return null;
        } catch (IllegalAccessException unused2) {
            InnerLog.e$default(InnerLog.INSTANCE, "Reflection", "Illegal Access.", null, 4, null);
            return null;
        } catch (NoSuchFieldException unused3) {
            InnerLog.e$default(InnerLog.INSTANCE, "Reflection", "No Such Field.", null, 4, null);
            return null;
        }
    }
}
