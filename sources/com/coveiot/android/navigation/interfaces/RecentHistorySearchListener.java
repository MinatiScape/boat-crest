package com.coveiot.android.navigation.interfaces;

import com.coveiot.android.navigation.db.model.RecentSearchHistoryData;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public interface RecentHistorySearchListener {
    void onRecentHistorySelected(@NotNull RecentSearchHistoryData recentSearchHistoryData);
}
