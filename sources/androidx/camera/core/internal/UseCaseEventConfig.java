package androidx.camera.core.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ReadableConfig;
/* loaded from: classes.dex */
public interface UseCaseEventConfig extends ReadableConfig {
    public static final Config.Option<UseCase.EventCallback> OPTION_USE_CASE_EVENT_CALLBACK = Config.Option.create("camerax.core.useCaseEventCallback", UseCase.EventCallback.class);

    /* loaded from: classes.dex */
    public interface Builder<B> {
        @NonNull
        B setUseCaseEventCallback(@NonNull UseCase.EventCallback eventCallback);
    }

    @Nullable
    default UseCase.EventCallback getUseCaseEventCallback(@Nullable UseCase.EventCallback eventCallback) {
        return (UseCase.EventCallback) retrieveOption(OPTION_USE_CASE_EVENT_CALLBACK, eventCallback);
    }

    @NonNull
    default UseCase.EventCallback getUseCaseEventCallback() {
        return (UseCase.EventCallback) retrieveOption(OPTION_USE_CASE_EVENT_CALLBACK);
    }
}
