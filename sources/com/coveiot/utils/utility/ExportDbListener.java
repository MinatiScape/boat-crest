package com.coveiot.utils.utility;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public interface ExportDbListener {
    void onFailure(@NotNull String str);

    void onSuccess(@NotNull String str);
}
