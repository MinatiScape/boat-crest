package com.google.firebase.crashlytics.internal.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;
/* loaded from: classes10.dex */
public class UserMetadata {

    /* renamed from: a  reason: collision with root package name */
    public String f11140a = null;
    public final KeysMap b = new KeysMap(64, 1024);
    public final KeysMap c = new KeysMap(64, 8192);

    @NonNull
    public Map<String, String> getCustomKeys() {
        return this.b.getKeys();
    }

    public Map<String, String> getInternalKeys() {
        return this.c.getKeys();
    }

    @Nullable
    public String getUserId() {
        return this.f11140a;
    }

    public void setCustomKey(String str, String str2) {
        this.b.setKey(str, str2);
    }

    public void setCustomKeys(Map<String, String> map) {
        this.b.setKeys(map);
    }

    public void setInternalKey(String str, String str2) {
        this.c.setKey(str, str2);
    }

    public void setUserId(String str) {
        this.f11140a = this.b.sanitizeAttribute(str);
    }
}
