package com.google.mlkit.vision.text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public interface TextRecognizerOptionsInterface {
    public static final int LATIN = 1;
    public static final int LATIN_AND_CHINESE = 2;
    public static final int LATIN_AND_DEVANAGARI = 3;
    public static final int LATIN_AND_JAPANESE = 4;
    public static final int LATIN_AND_KOREAN = 5;

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface LanguageOption {
    }

    @NonNull
    @KeepForSdk
    String getCreatorClass();

    @Nullable
    @KeepForSdk
    Executor getExecutor();

    @KeepForSdk
    boolean getIsThickClient();

    @KeepForSdk
    int getLoggingEventId();

    @LanguageOption
    @KeepForSdk
    int getLoggingLanguageOption();

    @NonNull
    @KeepForSdk
    String getLoggingLibraryName();

    @NonNull
    @KeepForSdk
    String getModuleId();
}
