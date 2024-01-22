package com.google.android.material.color;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import com.google.android.material.color.DynamicColors;
/* loaded from: classes10.dex */
public class DynamicColorsOptions {
    public static final DynamicColors.Precondition d = new a();
    public static final DynamicColors.OnAppliedCallback e = new b();
    @StyleRes

    /* renamed from: a  reason: collision with root package name */
    public final int f10253a;
    @NonNull
    public final DynamicColors.Precondition b;
    @NonNull
    public final DynamicColors.OnAppliedCallback c;

    /* loaded from: classes10.dex */
    public static class Builder {
        @StyleRes

        /* renamed from: a  reason: collision with root package name */
        public int f10254a;
        @NonNull
        public DynamicColors.Precondition b = DynamicColorsOptions.d;
        @NonNull
        public DynamicColors.OnAppliedCallback c = DynamicColorsOptions.e;

        @NonNull
        public DynamicColorsOptions build() {
            return new DynamicColorsOptions(this, null);
        }

        @NonNull
        public Builder setOnAppliedCallback(@NonNull DynamicColors.OnAppliedCallback onAppliedCallback) {
            this.c = onAppliedCallback;
            return this;
        }

        @NonNull
        public Builder setPrecondition(@NonNull DynamicColors.Precondition precondition) {
            this.b = precondition;
            return this;
        }

        @NonNull
        public Builder setThemeOverlay(@StyleRes int i) {
            this.f10254a = i;
            return this;
        }
    }

    /* loaded from: classes10.dex */
    public class a implements DynamicColors.Precondition {
        @Override // com.google.android.material.color.DynamicColors.Precondition
        public boolean shouldApplyDynamicColors(@NonNull Activity activity, int i) {
            return true;
        }
    }

    /* loaded from: classes10.dex */
    public class b implements DynamicColors.OnAppliedCallback {
        @Override // com.google.android.material.color.DynamicColors.OnAppliedCallback
        public void onApplied(@NonNull Activity activity) {
        }
    }

    public /* synthetic */ DynamicColorsOptions(Builder builder, a aVar) {
        this(builder);
    }

    @NonNull
    public DynamicColors.OnAppliedCallback getOnAppliedCallback() {
        return this.c;
    }

    @NonNull
    public DynamicColors.Precondition getPrecondition() {
        return this.b;
    }

    @StyleRes
    public int getThemeOverlay() {
        return this.f10253a;
    }

    public DynamicColorsOptions(Builder builder) {
        this.f10253a = builder.f10254a;
        this.b = builder.b;
        this.c = builder.c;
    }
}
