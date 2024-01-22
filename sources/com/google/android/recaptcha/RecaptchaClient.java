package com.google.android.recaptcha;

import androidx.annotation.NonNull;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public interface RecaptchaClient {
    @NonNull
    @Nullable
    /* renamed from: execute-0E7RQCE  reason: not valid java name */
    Object mo112execute0E7RQCE(@NonNull RecaptchaAction recaptchaAction, long j, @NonNull Continuation<? super Result<String>> continuation);

    @NonNull
    @Nullable
    /* renamed from: execute-gIAlu-s  reason: not valid java name */
    Object mo113executegIAlus(@NonNull RecaptchaAction recaptchaAction, @NonNull Continuation<? super Result<String>> continuation);
}
