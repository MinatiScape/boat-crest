package com.coveiot.android.fitnesschallenges.model;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FitnessChallengeShareModel {
    @SerializedName("id")
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f4520a;
    @SerializedName("type")
    @Nullable
    private String b;

    @Nullable
    public final String getChallengeId() {
        return this.f4520a;
    }

    @Nullable
    public final String getChallengeType() {
        return this.b;
    }

    public final void setChallengeId(@Nullable String str) {
        this.f4520a = str;
    }

    public final void setChallengeType(@Nullable String str) {
        this.b = str;
    }
}
