package com.coveiot.android.sportsnotification.utils;

import com.coveiot.android.bleabstract.models.DynamicSportsField;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public interface SoccerHelper {
    @NotNull
    ArrayList<DynamicSportsField> getMatchCanceledRequest(@NotNull SoccerData soccerData);

    @NotNull
    ArrayList<DynamicSportsField> getMatchStartAtRequest(@NotNull SoccerData soccerData);

    @NotNull
    ArrayList<DynamicSportsField> getNoMatchSelectedRequest();

    @NotNull
    ArrayList<DynamicSportsField> getOfflineRequest();

    @NotNull
    ArrayList<DynamicSportsField> getSoccerEventRequest(@NotNull SoccerData soccerData);

    @NotNull
    ArrayList<DynamicSportsField> getSoccerSummaryRequest(@NotNull SoccerData soccerData);
}
