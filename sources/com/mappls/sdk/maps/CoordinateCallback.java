package com.mappls.sdk.maps;

import java.util.List;
/* loaded from: classes11.dex */
public interface CoordinateCallback {
    void coordinateResultSuccess(List<CoordinateResult> list);

    void onFailure();
}
