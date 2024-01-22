package com.coveiot.android.sportsnotificationsdk.models.scorecard;

import com.google.android.material.color.c;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u001d\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b)\u0010*R$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0004\u001a\u0004\b\u000b\u0010\u0006\"\u0004\b\f\u0010\bR$\u0010\u0015\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0019\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0010\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R$\u0010\u001d\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u0010\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014R$\u0010!\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0010\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R$\u0010%\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\"\u0010\u0010\u001a\u0004\b#\u0010\u0012\"\u0004\b$\u0010\u0014R$\u0010'\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b&\u0010\u0004\u001a\u0004\b'\u0010\u0006\"\u0004\b(\u0010\b¨\u0006+"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/SocrecardFielder;", "", "", "a", "Ljava/lang/String;", "getFielderId", "()Ljava/lang/String;", "setFielderId", "(Ljava/lang/String;)V", "fielderId", "b", "getFielderName", "setFielderName", "fielderName", "", c.f10260a, "Ljava/lang/Integer;", "getCatches", "()Ljava/lang/Integer;", "setCatches", "(Ljava/lang/Integer;)V", "catches", "d", "getRunoutThrower", "setRunoutThrower", "runoutThrower", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "getRunoutCatcher", "setRunoutCatcher", "runoutCatcher", "f", "getRunoutDirectHit", "setRunoutDirectHit", "runoutDirectHit", "g", "getStumping", "setStumping", "stumping", "h", "isSubstitute", "setSubstitute", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class SocrecardFielder {
    @SerializedName("fielder_id")
    @Expose
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5977a;
    @SerializedName("fielder_name")
    @Expose
    @Nullable
    private String b;
    @SerializedName("catches")
    @Expose
    @Nullable
    private Integer c;
    @SerializedName("runout_thrower")
    @Expose
    @Nullable
    private Integer d;
    @SerializedName("runout_catcher")
    @Expose
    @Nullable
    private Integer e;
    @SerializedName("runout_direct_hit")
    @Expose
    @Nullable
    private Integer f;
    @SerializedName("stumping")
    @Expose
    @Nullable
    private Integer g;
    @SerializedName("is_substitute")
    @Expose
    @Nullable
    private String h;

    @Nullable
    public final Integer getCatches() {
        return this.c;
    }

    @Nullable
    public final String getFielderId() {
        return this.f5977a;
    }

    @Nullable
    public final String getFielderName() {
        return this.b;
    }

    @Nullable
    public final Integer getRunoutCatcher() {
        return this.e;
    }

    @Nullable
    public final Integer getRunoutDirectHit() {
        return this.f;
    }

    @Nullable
    public final Integer getRunoutThrower() {
        return this.d;
    }

    @Nullable
    public final Integer getStumping() {
        return this.g;
    }

    @Nullable
    public final String isSubstitute() {
        return this.h;
    }

    public final void setCatches(@Nullable Integer num) {
        this.c = num;
    }

    public final void setFielderId(@Nullable String str) {
        this.f5977a = str;
    }

    public final void setFielderName(@Nullable String str) {
        this.b = str;
    }

    public final void setRunoutCatcher(@Nullable Integer num) {
        this.e = num;
    }

    public final void setRunoutDirectHit(@Nullable Integer num) {
        this.f = num;
    }

    public final void setRunoutThrower(@Nullable Integer num) {
        this.d = num;
    }

    public final void setStumping(@Nullable Integer num) {
        this.g = num;
    }

    public final void setSubstitute(@Nullable String str) {
        this.h = str;
    }
}
