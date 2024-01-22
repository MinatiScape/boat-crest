package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R;
import androidx.emoji2.viewsintegration.EmojiTextViewHelper;
/* loaded from: classes.dex */
public class f {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final TextView f471a;
    @NonNull
    public final EmojiTextViewHelper b;

    public f(@NonNull TextView textView) {
        this.f471a = textView;
        this.b = new EmojiTextViewHelper(textView, false);
    }

    @NonNull
    public InputFilter[] a(@NonNull InputFilter[] inputFilterArr) {
        return this.b.getFilters(inputFilterArr);
    }

    public boolean b() {
        return this.b.isEnabled();
    }

    public void c(@Nullable AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.f471a.getContext().obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i, 0);
        try {
            int i2 = R.styleable.AppCompatTextView_emojiCompatEnabled;
            boolean z = obtainStyledAttributes.hasValue(i2) ? obtainStyledAttributes.getBoolean(i2, true) : true;
            obtainStyledAttributes.recycle();
            e(z);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void d(boolean z) {
        this.b.setAllCaps(z);
    }

    public void e(boolean z) {
        this.b.setEnabled(z);
    }

    @Nullable
    public TransformationMethod f(@Nullable TransformationMethod transformationMethod) {
        return this.b.wrapTransformationMethod(transformationMethod);
    }
}
