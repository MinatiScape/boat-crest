package com.coveiot.textrecognition.base;

import com.coveiot.textrecognition.base.BaseOcrResponse;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public interface ResponseCallback<T extends BaseOcrResponse> {
    void onFailure(@NotNull OcrError ocrError);

    void onSuccess(@NotNull T t);
}
