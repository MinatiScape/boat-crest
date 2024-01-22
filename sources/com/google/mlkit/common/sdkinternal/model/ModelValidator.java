package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.model.RemoteModel;
import java.io.File;
@KeepForSdk
/* loaded from: classes10.dex */
public interface ModelValidator {

    @KeepForSdk
    /* loaded from: classes10.dex */
    public static class ValidationResult {
        @NonNull
        @KeepForSdk
        public static final ValidationResult VALID = new ValidationResult(ErrorCode.OK, null);

        /* renamed from: a  reason: collision with root package name */
        public final ErrorCode f11600a;
        @Nullable
        public final String b;

        @KeepForSdk
        /* loaded from: classes10.dex */
        public enum ErrorCode {
            OK,
            TFLITE_VERSION_INCOMPATIBLE,
            MODEL_FORMAT_INVALID
        }

        @KeepForSdk
        public ValidationResult(@NonNull ErrorCode errorCode, @Nullable String str) {
            this.f11600a = errorCode;
            this.b = str;
        }

        @NonNull
        @KeepForSdk
        public ErrorCode getErrorCode() {
            return this.f11600a;
        }

        @Nullable
        @KeepForSdk
        public String getErrorMessage() {
            return this.b;
        }

        @KeepForSdk
        public boolean isValid() {
            return this.f11600a == ErrorCode.OK;
        }
    }

    @NonNull
    @KeepForSdk
    ValidationResult validateModel(@NonNull File file, @NonNull RemoteModel remoteModel);
}
