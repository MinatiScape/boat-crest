package com.google.android.recaptcha;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public interface RecaptchaTasksClient {
    @NotNull
    Task<String> executeTask(@NonNull RecaptchaAction recaptchaAction);

    @NotNull
    Task<String> executeTask(@NonNull RecaptchaAction recaptchaAction, long j);
}
