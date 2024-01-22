package com.coveiot.leaderboard.utils;

import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class ShareData implements Serializable {
    @Nullable
    private MyBadgesModel.DataBean.BadgesBean data;
    @Nullable
    private String date;
    private int progress;
    private int progressStatus;
    @Nullable
    private String rank;
    @Nullable
    private String rankDate;
    @Nullable
    private String rankLevel;
    @Nullable
    private String steps;
    @NotNull
    private String userName = "";

    @Nullable
    public final MyBadgesModel.DataBean.BadgesBean getData() {
        return this.data;
    }

    @Nullable
    public final String getDate() {
        return this.date;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final int getProgressStatus() {
        return this.progressStatus;
    }

    @Nullable
    public final String getRank() {
        return this.rank;
    }

    @Nullable
    public final String getRankDate() {
        return this.rankDate;
    }

    @Nullable
    public final String getRankLevel() {
        return this.rankLevel;
    }

    @Nullable
    public final String getSteps() {
        return this.steps;
    }

    @NotNull
    public final String getUserName() {
        return this.userName;
    }

    public final void setData(@Nullable MyBadgesModel.DataBean.BadgesBean badgesBean) {
        this.data = badgesBean;
    }

    public final void setDate(@Nullable String str) {
        this.date = str;
    }

    public final void setProgress(int i) {
        this.progress = i;
    }

    public final void setProgressStatus(int i) {
        this.progressStatus = i;
    }

    public final void setRank(@Nullable String str) {
        this.rank = str;
    }

    public final void setRankDate(@Nullable String str) {
        this.rankDate = str;
    }

    public final void setRankLevel(@Nullable String str) {
        this.rankLevel = str;
    }

    public final void setSteps(@Nullable String str) {
        this.steps = str;
    }

    public final void setUserName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userName = str;
    }
}
