package com.google.crypto.tink.shaded.protobuf;

import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes10.dex */
public class ExtensionRegistryLite {
    public static volatile boolean b = false;
    public static boolean c = true;
    public static volatile ExtensionRegistryLite d;
    public static final ExtensionRegistryLite e = new ExtensionRegistryLite(true);

    /* renamed from: a  reason: collision with root package name */
    public final Map<b, GeneratedMessageLite.GeneratedExtension<?, ?>> f10946a;

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final Class<?> f10947a = a();

        public static Class<?> a() {
            try {
                return Class.forName("com.google.crypto.tink.shaded.protobuf.Extension");
            } catch (ClassNotFoundException unused) {
                return null;
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final Object f10948a;
        public final int b;

        public b(Object obj, int i) {
            this.f10948a = obj;
            this.b = i;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                return this.f10948a == bVar.f10948a && this.b == bVar.b;
            }
            return false;
        }

        public int hashCode() {
            return (System.identityHashCode(this.f10948a) * 65535) + this.b;
        }
    }

    public ExtensionRegistryLite() {
        this.f10946a = new HashMap();
    }

    public static ExtensionRegistryLite getEmptyRegistry() {
        ExtensionRegistryLite extensionRegistryLite = d;
        if (extensionRegistryLite == null) {
            synchronized (ExtensionRegistryLite.class) {
                extensionRegistryLite = d;
                if (extensionRegistryLite == null) {
                    if (c) {
                        extensionRegistryLite = j.b();
                    } else {
                        extensionRegistryLite = e;
                    }
                    d = extensionRegistryLite;
                }
            }
        }
        return extensionRegistryLite;
    }

    public static boolean isEagerlyParseMessageSets() {
        return b;
    }

    public static ExtensionRegistryLite newInstance() {
        if (c) {
            return j.a();
        }
        return new ExtensionRegistryLite();
    }

    public static void setEagerlyParseMessageSets(boolean z) {
        b = z;
    }

    public final void add(GeneratedMessageLite.GeneratedExtension<?, ?> generatedExtension) {
        this.f10946a.put(new b(generatedExtension.getContainingTypeDefaultInstance(), generatedExtension.getNumber()), generatedExtension);
    }

    public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType containingtype, int i) {
        return (GeneratedMessageLite.GeneratedExtension<ContainingType, ?>) this.f10946a.get(new b(containingtype, i));
    }

    public ExtensionRegistryLite getUnmodifiable() {
        return new ExtensionRegistryLite(this);
    }

    public ExtensionRegistryLite(ExtensionRegistryLite extensionRegistryLite) {
        if (extensionRegistryLite == e) {
            this.f10946a = Collections.emptyMap();
        } else {
            this.f10946a = Collections.unmodifiableMap(extensionRegistryLite.f10946a);
        }
    }

    public final void add(ExtensionLite<?, ?> extensionLite) {
        if (GeneratedMessageLite.GeneratedExtension.class.isAssignableFrom(extensionLite.getClass())) {
            add((GeneratedMessageLite.GeneratedExtension) extensionLite);
        }
        if (c && j.d(this)) {
            try {
                getClass().getMethod(ProductAction.ACTION_ADD, a.f10947a).invoke(this, extensionLite);
            } catch (Exception e2) {
                throw new IllegalArgumentException(String.format("Could not invoke ExtensionRegistry#add for %s", extensionLite), e2);
            }
        }
    }

    public ExtensionRegistryLite(boolean z) {
        this.f10946a = Collections.emptyMap();
    }
}
