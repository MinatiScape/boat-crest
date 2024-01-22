package com.mappls.sdk.category.fragment;

import androidx.annotation.Keep;
import com.mappls.sdk.category.model.PoiResult;
import java.util.List;
import org.jetbrains.annotations.NotNull;
@Keep
/* loaded from: classes11.dex */
public interface ICategoryResultListener {
    void onCategoryResult(@NotNull List<PoiResult> list);

    void onCategorySelectedResult(@NotNull PoiResult poiResult);

    void onResultCancel();
}
