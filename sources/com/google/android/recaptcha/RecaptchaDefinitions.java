package com.google.android.recaptcha;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class RecaptchaDefinitions {
    public static final long DEFAULT_TIMEOUT_EXECUTE = 5000;
    public static final long DEFAULT_TIMEOUT_INIT = 10000;
    @NotNull
    public static final RecaptchaDefinitions INSTANCE = new RecaptchaDefinitions();
    public static final long MIN_TIMEOUT_EXECUTE = 5000;
    public static final long MIN_TIMEOUT_INIT = 5000;

    private RecaptchaDefinitions() {
    }
}
