package com.google.mlkit.vision.text.latin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public class TextRecognizerOptions implements TextRecognizerOptionsInterface {
    @NonNull
    public static final TextRecognizerOptions DEFAULT_OPTIONS = new Builder().build();
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final AtomicReference f11648a = new AtomicReference();
    @Nullable
    public final Executor b;

    /* loaded from: classes10.dex */
    public static class Builder {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Executor f11649a;

        @NonNull
        public TextRecognizerOptions build() {
            return new TextRecognizerOptions(this.f11649a, null);
        }

        @NonNull
        public Builder setExecutor(@NonNull Executor executor) {
            this.f11649a = executor;
            return this;
        }
    }

    public /* synthetic */ TextRecognizerOptions(Executor executor, zza zzaVar) {
        this.b = executor;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TextRecognizerOptions) {
            return Objects.equal(this.b, ((TextRecognizerOptions) obj).b);
        }
        return false;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    @NonNull
    public final String getCreatorClass() {
        return true != getIsThickClient() ? "com.google.android.gms.vision.text.mlkit.TextRecognizerCreator" : "com.google.mlkit.vision.text.bundled.latin.BundledLatinTextRecognizerCreator";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    @Nullable
    public final Executor getExecutor() {
        return this.b;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final boolean getIsThickClient() {
        if (this.f11648a.get() != null) {
            return ((Boolean) this.f11648a.get()).booleanValue();
        }
        boolean z = DynamiteModule.getLocalVersion(MlKitContext.getInstance().getApplicationContext(), "com.google.mlkit.dynamite.text.latin") > 0;
        this.f11648a.set(Boolean.valueOf(z));
        return z;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    public final int getLoggingEventId() {
        return getIsThickClient() ? 24317 : 24306;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    @TextRecognizerOptionsInterface.LanguageOption
    public final int getLoggingLanguageOption() {
        return 1;
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    @NonNull
    public final String getLoggingLibraryName() {
        return true != getIsThickClient() ? "play-services-mlkit-text-recognition" : "text-recognition";
    }

    @Override // com.google.mlkit.vision.text.TextRecognizerOptionsInterface
    @NonNull
    public final String getModuleId() {
        return true != getIsThickClient() ? OptionalModuleUtils.OCR_MODULE_ID : "com.google.mlkit.dynamite.text.latin";
    }

    public int hashCode() {
        return Objects.hashCode(this.b);
    }
}
