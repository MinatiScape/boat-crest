package com.coveiot.android.sportsnotificationsdk.models.matchlist;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0016\u0010\u0017R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\u0011\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0015\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/matchlist/MListToss;", "", "", "a", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "text", "", "b", "Ljava/lang/Integer;", "getWinner", "()Ljava/lang/Integer;", "setWinner", "(Ljava/lang/Integer;)V", "winner", c.f10260a, "getDecision", "setDecision", "decision", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class MListToss {
    @SerializedName("text")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5947a;
    @SerializedName("winner")
    @Expose
    @Nullable
    private Integer b;
    @SerializedName("decision")
    @Expose
    @Nullable
    private Integer c;

    @Nullable
    public final Integer getDecision() {
        return this.c;
    }

    @Nullable
    public final String getText() {
        return this.f5947a;
    }

    @Nullable
    public final Integer getWinner() {
        return this.b;
    }

    public final void setDecision(@Nullable Integer num) {
        this.c = num;
    }

    public final void setText(@Nullable String str) {
        this.f5947a = str;
    }

    public final void setWinner(@Nullable Integer num) {
        this.b = num;
    }
}
