package com.google.firebase.ml.vision.document;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes10.dex */
public class FirebaseVisionCloudDocumentRecognizerOptions {

    /* renamed from: a  reason: collision with root package name */
    public final List<String> f11444a;
    public final boolean b;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public List<String> f11445a = new ArrayList();
        public boolean b = false;

        @NonNull
        public FirebaseVisionCloudDocumentRecognizerOptions build() {
            return new FirebaseVisionCloudDocumentRecognizerOptions(this.f11445a, this.b);
        }

        @NonNull
        public Builder enforceCertFingerprintMatch() {
            this.b = true;
            return this;
        }

        @NonNull
        public Builder setLanguageHints(@NonNull List<String> list) {
            Preconditions.checkNotNull(list, "Provided hinted languages can not be null");
            this.f11445a = list;
            Collections.sort(list);
            return this;
        }
    }

    public FirebaseVisionCloudDocumentRecognizerOptions(@NonNull List<String> list, boolean z) {
        Preconditions.checkNotNull(list, "Provided hinted languages can not be null");
        this.f11444a = list;
        this.b = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FirebaseVisionCloudDocumentRecognizerOptions) {
            FirebaseVisionCloudDocumentRecognizerOptions firebaseVisionCloudDocumentRecognizerOptions = (FirebaseVisionCloudDocumentRecognizerOptions) obj;
            return this.f11444a.equals(firebaseVisionCloudDocumentRecognizerOptions.getHintedLanguages()) && this.b == firebaseVisionCloudDocumentRecognizerOptions.b;
        }
        return false;
    }

    @NonNull
    public List<String> getHintedLanguages() {
        return this.f11444a;
    }

    public int hashCode() {
        return Objects.hashCode(this.f11444a, Boolean.valueOf(this.b));
    }

    public final boolean isEnforceCertFingerprintMatch() {
        return this.b;
    }
}
