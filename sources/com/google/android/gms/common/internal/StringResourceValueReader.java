package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes6.dex */
public class StringResourceValueReader {

    /* renamed from: a  reason: collision with root package name */
    public final Resources f8332a;
    public final String b;

    public StringResourceValueReader(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        Resources resources = context.getResources();
        this.f8332a = resources;
        this.b = resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue);
    }

    @Nullable
    @KeepForSdk
    public String getString(@NonNull String str) {
        int identifier = this.f8332a.getIdentifier(str, "string", this.b);
        if (identifier == 0) {
            return null;
        }
        return this.f8332a.getString(identifier);
    }
}
