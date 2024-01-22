package com.mappls.sdk.category;

import androidx.annotation.Keep;
import com.mappls.sdk.category.model.PoiResult;
import java.util.List;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes11.dex */
public interface IMapplsPOICallback {
    void getPOIResultListener(@NotNull List<PoiResult> list);
}
