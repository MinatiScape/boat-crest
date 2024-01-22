package com.coveiot.android.respiratoryrate;

import com.coveiot.android.bleabstract.models.RawPPGHistoryData;
import java.util.ArrayList;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public interface SyncRawPPGDataResultListener {
    void onFailure(@Nullable String str);

    void onSuccess(@Nullable Map<String, ArrayList<RawPPGHistoryData>> map);
}
