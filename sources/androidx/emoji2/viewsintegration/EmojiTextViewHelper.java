package androidx.emoji2.viewsintegration;

import android.os.Build;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.EmojiCompat;
/* loaded from: classes.dex */
public final class EmojiTextViewHelper {

    /* renamed from: a  reason: collision with root package name */
    public final b f1286a;

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class a extends b {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f1287a;
        public final androidx.emoji2.viewsintegration.b b;
        public boolean c = true;

        public a(TextView textView) {
            this.f1287a = textView;
            this.b = new androidx.emoji2.viewsintegration.b(textView);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        @NonNull
        public InputFilter[] a(@NonNull InputFilter[] inputFilterArr) {
            if (!this.c) {
                return i(inputFilterArr);
            }
            return g(inputFilterArr);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        public boolean b() {
            return this.c;
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        public void c(boolean z) {
            if (z) {
                e();
            }
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        public void d(boolean z) {
            this.c = z;
            e();
            l();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        public void e() {
            this.f1287a.setTransformationMethod(f(this.f1287a.getTransformationMethod()));
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        @Nullable
        public TransformationMethod f(@Nullable TransformationMethod transformationMethod) {
            if (this.c) {
                return m(transformationMethod);
            }
            return k(transformationMethod);
        }

        @NonNull
        public final InputFilter[] g(@NonNull InputFilter[] inputFilterArr) {
            int length = inputFilterArr.length;
            for (InputFilter inputFilter : inputFilterArr) {
                if (inputFilter == this.b) {
                    return inputFilterArr;
                }
            }
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length + 1];
            System.arraycopy(inputFilterArr, 0, inputFilterArr2, 0, length);
            inputFilterArr2[length] = this.b;
            return inputFilterArr2;
        }

        public final SparseArray<InputFilter> h(@NonNull InputFilter[] inputFilterArr) {
            SparseArray<InputFilter> sparseArray = new SparseArray<>(1);
            for (int i = 0; i < inputFilterArr.length; i++) {
                if (inputFilterArr[i] instanceof androidx.emoji2.viewsintegration.b) {
                    sparseArray.put(i, inputFilterArr[i]);
                }
            }
            return sparseArray;
        }

        @NonNull
        public final InputFilter[] i(@NonNull InputFilter[] inputFilterArr) {
            SparseArray<InputFilter> h = h(inputFilterArr);
            if (h.size() == 0) {
                return inputFilterArr;
            }
            int length = inputFilterArr.length;
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - h.size()];
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (h.indexOfKey(i2) < 0) {
                    inputFilterArr2[i] = inputFilterArr[i2];
                    i++;
                }
            }
            return inputFilterArr2;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public void j(boolean z) {
            this.c = z;
        }

        @Nullable
        public final TransformationMethod k(@Nullable TransformationMethod transformationMethod) {
            return transformationMethod instanceof d ? ((d) transformationMethod).a() : transformationMethod;
        }

        public final void l() {
            this.f1287a.setFilters(a(this.f1287a.getFilters()));
        }

        @NonNull
        public final TransformationMethod m(@Nullable TransformationMethod transformationMethod) {
            return ((transformationMethod instanceof d) || (transformationMethod instanceof PasswordTransformationMethod)) ? transformationMethod : new d(transformationMethod);
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        @NonNull
        public InputFilter[] a(@NonNull InputFilter[] inputFilterArr) {
            return inputFilterArr;
        }

        public boolean b() {
            return false;
        }

        public void c(boolean z) {
        }

        public void d(boolean z) {
        }

        public void e() {
        }

        @Nullable
        public TransformationMethod f(@Nullable TransformationMethod transformationMethod) {
            return transformationMethod;
        }
    }

    @RequiresApi(19)
    /* loaded from: classes.dex */
    public static class c extends b {

        /* renamed from: a  reason: collision with root package name */
        public final a f1288a;

        public c(TextView textView) {
            this.f1288a = new a(textView);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        @NonNull
        public InputFilter[] a(@NonNull InputFilter[] inputFilterArr) {
            return g() ? inputFilterArr : this.f1288a.a(inputFilterArr);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        public boolean b() {
            return this.f1288a.b();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        public void c(boolean z) {
            if (g()) {
                return;
            }
            this.f1288a.c(z);
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        public void d(boolean z) {
            if (g()) {
                this.f1288a.j(z);
            } else {
                this.f1288a.d(z);
            }
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        public void e() {
            if (g()) {
                return;
            }
            this.f1288a.e();
        }

        @Override // androidx.emoji2.viewsintegration.EmojiTextViewHelper.b
        @Nullable
        public TransformationMethod f(@Nullable TransformationMethod transformationMethod) {
            return g() ? transformationMethod : this.f1288a.f(transformationMethod);
        }

        public final boolean g() {
            return !EmojiCompat.isConfigured();
        }
    }

    public EmojiTextViewHelper(@NonNull TextView textView) {
        this(textView, true);
    }

    @NonNull
    public InputFilter[] getFilters(@NonNull InputFilter[] inputFilterArr) {
        return this.f1286a.a(inputFilterArr);
    }

    public boolean isEnabled() {
        return this.f1286a.b();
    }

    public void setAllCaps(boolean z) {
        this.f1286a.c(z);
    }

    public void setEnabled(boolean z) {
        this.f1286a.d(z);
    }

    public void updateTransformationMethod() {
        this.f1286a.e();
    }

    @Nullable
    public TransformationMethod wrapTransformationMethod(@Nullable TransformationMethod transformationMethod) {
        return this.f1286a.f(transformationMethod);
    }

    public EmojiTextViewHelper(@NonNull TextView textView, boolean z) {
        Preconditions.checkNotNull(textView, "textView cannot be null");
        if (Build.VERSION.SDK_INT < 19) {
            this.f1286a = new b();
        } else if (!z) {
            this.f1286a = new c(textView);
        } else {
            this.f1286a = new a(textView);
        }
    }
}
