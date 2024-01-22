package com.google.firebase.ml.vision.text;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.firebase_ml.zzkt;
/* loaded from: classes10.dex */
public class RecognizedLanguage {

    /* renamed from: a  reason: collision with root package name */
    public final String f11472a;

    public RecognizedLanguage(@Nullable String str) {
        this.f11472a = str;
    }

    @Nullable
    public static RecognizedLanguage zza(@Nullable zzkt zzktVar) {
        if (zzktVar == null || zzktVar.getLanguageCode() == null || zzktVar.getLanguageCode().isEmpty()) {
            return null;
        }
        return new RecognizedLanguage(zzktVar.getLanguageCode());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RecognizedLanguage) {
            RecognizedLanguage recognizedLanguage = (RecognizedLanguage) obj;
            String str = this.f11472a;
            if (str == null) {
                return recognizedLanguage.f11472a == null;
            }
            return str.equals(recognizedLanguage.f11472a);
        }
        return false;
    }

    @Nullable
    public String getLanguageCode() {
        return this.f11472a;
    }

    public int hashCode() {
        return Objects.hashCode(this.f11472a);
    }
}
