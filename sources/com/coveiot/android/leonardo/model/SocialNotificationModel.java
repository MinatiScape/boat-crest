package com.coveiot.android.leonardo.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SocialNotificationModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Long f4859a;
    @Nullable
    public String b;
    @Nullable
    public String c;
    @Nullable
    public String d;

    public SocialNotificationModel() {
        this(null, null, null, null, 15, null);
    }

    public SocialNotificationModel(@Nullable Long l, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        this.f4859a = l;
        this.b = str;
        this.c = str2;
        this.d = str3;
    }

    @Nullable
    public final String getContent() {
        return this.d;
    }

    @Nullable
    public final Long getNotificationWhenTime() {
        return this.f4859a;
    }

    @Nullable
    public final String getPackageName() {
        return this.b;
    }

    @Nullable
    public final String getTitle() {
        return this.c;
    }

    public final void setContent(@Nullable String str) {
        this.d = str;
    }

    public final void setNotificationWhenTime(@Nullable Long l) {
        this.f4859a = l;
    }

    public final void setPackageName(@Nullable String str) {
        this.b = str;
    }

    public final void setTitle(@Nullable String str) {
        this.c = str;
    }

    public /* synthetic */ SocialNotificationModel(Long l, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3);
    }
}
