package com.coveiot.android.fitnessbuddies.models;

import android.graphics.Bitmap;
import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FitnessBuddyData {
    @SerializedName("requestId")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private int f4471a;
    @SerializedName("userProfilePicture")
    @Expose
    @Nullable
    private Bitmap b;
    @SerializedName("fromUserId")
    @Expose
    private int c;
    @SerializedName("toUserId")
    @Expose
    private int d;
    @SerializedName(Constants.END_USER_GLOBAL_ID)
    @Expose
    private int e;
    @SerializedName("toCloverName")
    @Expose
    @Nullable
    private String f;
    @SerializedName("fromUserName")
    @Expose
    @Nullable
    private String g;
    @SerializedName("toUserName")
    @Expose
    @Nullable
    private String h;
    @SerializedName("profilePic")
    @Expose
    @Nullable
    private String i;
    @SerializedName("toUserMobileNumber")
    @Expose
    @Nullable
    private String j;
    @SerializedName("fromUserMobileNumber")
    @Expose
    @Nullable
    private String k;
    @SerializedName("requestStatus")
    @Expose
    @Nullable
    private String l;
    @SerializedName("buddyId")
    @Expose
    @Nullable
    private String m;
    @SerializedName("lastInvitedDate")
    @Expose
    @Nullable
    private String n;
    @SerializedName("id")
    @Expose
    private int o;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    @Nullable
    private String p;
    @SerializedName("gender")
    @Expose
    @Nullable
    private String q;
    @SerializedName("mobileNumber")
    @Expose
    @Nullable
    private String r;
    @SerializedName("profilePictureName")
    @Expose
    @Nullable
    private String s;
    @SerializedName("fromUserProfilePictureName")
    @Expose
    @Nullable
    private String t;
    @SerializedName("toUserProfilePictureName")
    @Expose
    @Nullable
    private String u;
    @SerializedName("toUserDpUrl")
    @Expose
    @Nullable
    private String v;
    @SerializedName("fromUserDpUrl")
    @Expose
    @Nullable
    private String w;
    @SerializedName("dpUrl")
    @Expose
    @Nullable
    private String x;
    @SerializedName("inviteText")
    @Expose
    @Nullable
    private String y;
    @SerializedName("inviteLinkId")
    @Expose
    @Nullable
    private String z;

    @Nullable
    public final String getBuddyId() {
        return this.m;
    }

    @Nullable
    public final String getDpUrl() {
        return this.x;
    }

    @Nullable
    public final String getFromUserDpUrl() {
        return this.w;
    }

    public final int getFromUserId() {
        return this.c;
    }

    @Nullable
    public final String getFromUserMobileNumber() {
        return this.k;
    }

    @Nullable
    public final String getFromUserName() {
        return this.g;
    }

    @Nullable
    public final String getFromUserProfilePictureName() {
        return this.t;
    }

    @Nullable
    public final String getGender() {
        return this.q;
    }

    public final int getId() {
        return this.o;
    }

    @Nullable
    public final String getInviteLinkId() {
        return this.z;
    }

    @Nullable
    public final String getInviteText() {
        return this.y;
    }

    @Nullable
    public final String getLastInvitedDate() {
        return this.n;
    }

    @Nullable
    public final String getMobileNumber() {
        return this.r;
    }

    @Nullable
    public final String getName() {
        return this.p;
    }

    @Nullable
    public final String getProfilePic() {
        return this.i;
    }

    @Nullable
    public final String getProfilePictureName() {
        return this.s;
    }

    public final int getRequestId() {
        return this.f4471a;
    }

    @Nullable
    public final String getRequestStatus() {
        return this.l;
    }

    @Nullable
    public final String getToCloverName() {
        return this.f;
    }

    @Nullable
    public final String getToUserDpUrl() {
        return this.v;
    }

    public final int getToUserId() {
        return this.d;
    }

    @Nullable
    public final String getToUserMobileNumber() {
        return this.j;
    }

    @Nullable
    public final String getToUserName() {
        return this.h;
    }

    @Nullable
    public final String getToUserProfilePictureName() {
        return this.u;
    }

    public final int getUserId() {
        return this.e;
    }

    @Nullable
    public final Bitmap getUserProfilePicture() {
        return this.b;
    }

    public final void setBuddyId(@Nullable String str) {
        this.m = str;
    }

    public final void setDpUrl(@Nullable String str) {
        this.x = str;
    }

    public final void setFromUserDpUrl(@Nullable String str) {
        this.w = str;
    }

    public final void setFromUserId(int i) {
        this.c = i;
    }

    public final void setFromUserMobileNumber(@Nullable String str) {
        this.k = str;
    }

    public final void setFromUserName(@Nullable String str) {
        this.g = str;
    }

    public final void setFromUserProfilePictureName(@Nullable String str) {
        this.t = str;
    }

    public final void setGender(@Nullable String str) {
        this.q = str;
    }

    public final void setId(int i) {
        this.o = i;
    }

    public final void setInviteLinkId(@Nullable String str) {
        this.z = str;
    }

    public final void setInviteText(@Nullable String str) {
        this.y = str;
    }

    public final void setLastInvitedDate(@Nullable String str) {
        this.n = str;
    }

    public final void setMobileNumber(@Nullable String str) {
        this.r = str;
    }

    public final void setName(@Nullable String str) {
        this.p = str;
    }

    public final void setProfilePic(@Nullable String str) {
        this.i = str;
    }

    public final void setProfilePictureName(@Nullable String str) {
        this.s = str;
    }

    public final void setRequestId(int i) {
        this.f4471a = i;
    }

    public final void setRequestStatus(@Nullable String str) {
        this.l = str;
    }

    public final void setToCloverName(@Nullable String str) {
        this.f = str;
    }

    public final void setToUserDpUrl(@Nullable String str) {
        this.v = str;
    }

    public final void setToUserId(int i) {
        this.d = i;
    }

    public final void setToUserMobileNumber(@Nullable String str) {
        this.j = str;
    }

    public final void setToUserName(@Nullable String str) {
        this.h = str;
    }

    public final void setToUserProfilePictureName(@Nullable String str) {
        this.u = str;
    }

    public final void setUserId(int i) {
        this.e = i;
    }

    public final void setUserProfilePicture(@Nullable Bitmap bitmap) {
        this.b = bitmap;
    }
}
