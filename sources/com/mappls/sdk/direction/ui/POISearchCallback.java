package com.mappls.sdk.direction.ui;

import androidx.annotation.Keep;
import com.mappls.sdk.category.model.PoiResult;
import java.util.List;
@Keep
/* loaded from: classes11.dex */
public interface POISearchCallback {
    void getPoiSearchResults(List<PoiResult> list);
}
