package com.google.mlkit.vision.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.inject.Provider;
import com.google.mlkit.common.sdkinternal.ClientPriority;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
@KeepForSdk
/* loaded from: classes10.dex */
public class MultiFlavorDetectorCreator {

    /* renamed from: a  reason: collision with root package name */
    public final Map f11640a = new HashMap();

    @KeepForSdk
    /* loaded from: classes10.dex */
    public interface DetectorCreator<DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> {
        @NonNull
        @KeepForSdk
        DetectorT create(@NonNull OptionsT optionst);
    }

    @KeepForSdk
    /* loaded from: classes10.dex */
    public interface DetectorOptions<DetectorT> {
    }

    @KeepForSdk
    /* loaded from: classes10.dex */
    public interface MultiFlavorDetector {
    }

    @KeepForSdk
    /* loaded from: classes10.dex */
    public static class Registration {

        /* renamed from: a  reason: collision with root package name */
        public final Class f11641a;
        public final Provider b;
        @ClientPriority
        public final int c;

        @KeepForSdk
        public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> Registration(@NonNull Class<? extends OptionsT> cls, @NonNull Provider<? extends DetectorCreator<DetectorT, OptionsT>> provider) {
            this(cls, provider, 100);
        }

        @KeepForSdk
        public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> Registration(@NonNull Class<? extends OptionsT> cls, @NonNull Provider<? extends DetectorCreator<DetectorT, OptionsT>> provider, @ClientPriority int i) {
            this.f11641a = cls;
            this.b = provider;
            this.c = i;
        }

        @ClientPriority
        public final int a() {
            return this.c;
        }

        public final Provider b() {
            return this.b;
        }

        public final Class c() {
            return this.f11641a;
        }
    }

    public MultiFlavorDetectorCreator(Set set) {
        HashMap hashMap = new HashMap();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Registration registration = (Registration) it.next();
            Class c = registration.c();
            if (!this.f11640a.containsKey(c) || registration.a() >= ((Integer) Preconditions.checkNotNull((Integer) hashMap.get(c))).intValue()) {
                this.f11640a.put(c, registration.b());
                hashMap.put(c, Integer.valueOf(registration.a()));
            }
        }
    }

    @NonNull
    @KeepForSdk
    public static synchronized MultiFlavorDetectorCreator getInstance() {
        MultiFlavorDetectorCreator multiFlavorDetectorCreator;
        synchronized (MultiFlavorDetectorCreator.class) {
            multiFlavorDetectorCreator = (MultiFlavorDetectorCreator) MlKitContext.getInstance().get(MultiFlavorDetectorCreator.class);
        }
        return multiFlavorDetectorCreator;
    }

    @NonNull
    @KeepForSdk
    public <DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> DetectorT create(@NonNull OptionsT optionst) {
        return (DetectorT) ((DetectorCreator) ((Provider) Preconditions.checkNotNull((Provider) this.f11640a.get(optionst.getClass()))).get()).create(optionst);
    }
}
