package com.mappls.sdk.navigation.apis;

import android.content.SharedPreferences;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.apis.c;
/* loaded from: classes11.dex */
public final class d implements c {

    /* renamed from: a  reason: collision with root package name */
    public NavigationApplication f12877a;

    /* loaded from: classes11.dex */
    public class a implements c.a {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ SharedPreferences.Editor f12878a;

        public a(SharedPreferences.Editor editor) {
            this.f12878a = editor;
        }

        @Override // com.mappls.sdk.navigation.apis.c.a
        public final c.a a(int i, String str) {
            this.f12878a.putInt(str, i);
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.c.a
        public final boolean commit() {
            return this.f12878a.commit();
        }

        @Override // com.mappls.sdk.navigation.apis.c.a
        public final c.a putBoolean(String str, boolean z) {
            this.f12878a.putBoolean(str, z);
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.c.a
        public final c.a putFloat(String str, float f) {
            this.f12878a.putFloat(str, f);
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.c.a
        public final c.a putLong(String str, long j) {
            this.f12878a.putLong(str, j);
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.c.a
        public final c.a putString(String str, String str2) {
            this.f12878a.putString(str, str2);
            return this;
        }

        @Override // com.mappls.sdk.navigation.apis.c.a
        public final c.a remove(String str) {
            this.f12878a.remove(str);
            return this;
        }
    }

    public d(NavigationApplication navigationApplication) {
        this.f12877a = navigationApplication;
    }

    public final float a(SharedPreferences sharedPreferences, String str) {
        return sharedPreferences.getFloat(str, 0.0f);
    }

    public final SharedPreferences a(String str) {
        return this.f12877a.getSharedPreferences(str, 0);
    }

    public final c.a a(Object obj) {
        return new a(((SharedPreferences) obj).edit());
    }
}
