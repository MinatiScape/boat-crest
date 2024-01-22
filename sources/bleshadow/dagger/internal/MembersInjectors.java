package bleshadow.dagger.internal;

import bleshadow.dagger.MembersInjector;
/* loaded from: classes.dex */
public final class MembersInjectors {

    /* loaded from: classes.dex */
    public enum a implements MembersInjector<Object> {
        INSTANCE;

        @Override // bleshadow.dagger.MembersInjector
        public void injectMembers(Object obj) {
            Preconditions.checkNotNull(obj, "Cannot inject members into a null reference");
        }
    }

    public static <T> MembersInjector<T> noOp() {
        return a.INSTANCE;
    }
}
