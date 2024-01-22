package com.google.firebase.ml.vision.text;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public class FirebaseVisionCloudTextRecognizerOptions {
    public static final int DENSE_MODEL = 2;
    public static final int SPARSE_MODEL = 1;

    /* renamed from: a  reason: collision with root package name */
    public final List<String> f11468a;
    public final int b;
    public final boolean c;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public List<String> f11469a = new ArrayList();
        public int b = 1;
        public boolean c = false;

        @NonNull
        public FirebaseVisionCloudTextRecognizerOptions build() {
            return new FirebaseVisionCloudTextRecognizerOptions(this.f11469a, this.b, this.c);
        }

        @NonNull
        public Builder enforceCertFingerprintMatch() {
            this.c = true;
            return this;
        }

        @NonNull
        public Builder setLanguageHints(@NonNull List<String> list) {
            Preconditions.checkNotNull(list, "Provided hinted languages can not be null");
            this.f11469a = list;
            Collections.sort(list);
            return this;
        }

        @NonNull
        public Builder setModelType(@CloudTextModelType int i) {
            boolean z = true;
            if (i != 1 && i != 2) {
                z = false;
            }
            Preconditions.checkArgument(z, "modelType should be either SPARSE_MODEL or DENSE_MODEL");
            this.b = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface CloudTextModelType {
    }

    public FirebaseVisionCloudTextRecognizerOptions(@NonNull List<String> list, @CloudTextModelType int i, boolean z) {
        Preconditions.checkNotNull(list, "Provided hinted languages can not be null");
        this.f11468a = list;
        this.b = i;
        this.c = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseVisionCloudTextRecognizerOptions) {
            FirebaseVisionCloudTextRecognizerOptions firebaseVisionCloudTextRecognizerOptions = (FirebaseVisionCloudTextRecognizerOptions) obj;
            return this.f11468a.equals(firebaseVisionCloudTextRecognizerOptions.getHintedLanguages()) && this.b == firebaseVisionCloudTextRecognizerOptions.b && this.c == firebaseVisionCloudTextRecognizerOptions.c;
        }
        return false;
    }

    @NonNull
    public List<String> getHintedLanguages() {
        return this.f11468a;
    }

    @CloudTextModelType
    public int getModelType() {
        return this.b;
    }

    public int hashCode() {
        return Objects.hashCode(this.f11468a, Integer.valueOf(this.b), Boolean.valueOf(this.c));
    }

    public final boolean isEnforceCertFingerprintMatch() {
        return this.c;
    }
}
