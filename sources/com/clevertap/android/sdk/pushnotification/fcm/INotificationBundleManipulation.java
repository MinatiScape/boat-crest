package com.clevertap.android.sdk.pushnotification.fcm;

import android.os.Bundle;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public interface INotificationBundleManipulation<T> {
    @NotNull
    INotificationBundleManipulation<T> addPriority(T t);

    @NotNull
    Bundle build();
}
