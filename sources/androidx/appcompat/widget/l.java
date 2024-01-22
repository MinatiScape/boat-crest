package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.Preconditions;
/* loaded from: classes.dex */
public final class l {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public TextView f474a;
    @Nullable
    public TextClassifier b;

    @RequiresApi(26)
    /* loaded from: classes.dex */
    public static final class a {
        @NonNull
        @DoNotInline
        public static TextClassifier a(@NonNull TextView textView) {
            TextClassificationManager textClassificationManager = (TextClassificationManager) textView.getContext().getSystemService(TextClassificationManager.class);
            if (textClassificationManager != null) {
                return textClassificationManager.getTextClassifier();
            }
            return TextClassifier.NO_OP;
        }
    }

    public l(@NonNull TextView textView) {
        this.f474a = (TextView) Preconditions.checkNotNull(textView);
    }

    @NonNull
    @RequiresApi(api = 26)
    public TextClassifier a() {
        TextClassifier textClassifier = this.b;
        return textClassifier == null ? a.a(this.f474a) : textClassifier;
    }

    @RequiresApi(api = 26)
    public void b(@Nullable TextClassifier textClassifier) {
        this.b = textClassifier;
    }
}
