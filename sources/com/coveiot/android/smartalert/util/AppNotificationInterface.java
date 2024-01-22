package com.coveiot.android.smartalert.util;

import com.coveiot.covepreferences.data.AppNotificationData;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public interface AppNotificationInterface {
    void saveAppData(@NotNull List<? extends AppNotificationData> list, @NotNull String str, boolean z);
}
