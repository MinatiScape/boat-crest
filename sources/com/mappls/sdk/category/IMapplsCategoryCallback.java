package com.mappls.sdk.category;

import androidx.annotation.Keep;
import com.mappls.sdk.category.model.PoiResult;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes11.dex */
public interface IMapplsCategoryCallback {
    void onCancel();

    void onCategorySelected(@NotNull PoiResult poiResult);
}
