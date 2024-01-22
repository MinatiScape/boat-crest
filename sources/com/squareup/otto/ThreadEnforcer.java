package com.squareup.otto;

import android.os.Looper;
/* loaded from: classes12.dex */
public interface ThreadEnforcer {
    public static final ThreadEnforcer ANY = new a();
    public static final ThreadEnforcer MAIN = new b();

    /* loaded from: classes12.dex */
    public static class a implements ThreadEnforcer {
        @Override // com.squareup.otto.ThreadEnforcer
        public void enforce(Bus bus) {
        }
    }

    /* loaded from: classes12.dex */
    public static class b implements ThreadEnforcer {
        @Override // com.squareup.otto.ThreadEnforcer
        public void enforce(Bus bus) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                return;
            }
            throw new IllegalStateException("Event bus " + bus + " accessed from non-main thread " + Looper.myLooper());
        }
    }

    void enforce(Bus bus);
}
