package androidx.emoji2.viewsintegration;

import android.annotation.SuppressLint;
import android.text.Editable;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.emoji2.text.SpannableBuilder;
/* loaded from: classes.dex */
public final class a extends Editable.Factory {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f1289a = new Object();
    @GuardedBy("INSTANCE_LOCK")
    public static volatile Editable.Factory b;
    @Nullable
    public static Class<?> c;

    @SuppressLint({"PrivateApi"})
    public a() {
        try {
            c = Class.forName("android.text.DynamicLayout$ChangeWatcher", false, a.class.getClassLoader());
        } catch (Throwable unused) {
        }
    }

    public static Editable.Factory getInstance() {
        if (b == null) {
            synchronized (f1289a) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    @Override // android.text.Editable.Factory
    public Editable newEditable(@NonNull CharSequence charSequence) {
        Class<?> cls = c;
        if (cls != null) {
            return SpannableBuilder.create(cls, charSequence);
        }
        return super.newEditable(charSequence);
    }
}
