package com.google.firebase.platforminfo;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import java.util.Iterator;
import java.util.Set;
/* loaded from: classes10.dex */
public class DefaultUserAgentPublisher implements UserAgentPublisher {

    /* renamed from: a  reason: collision with root package name */
    public final String f11473a;
    public final GlobalLibraryVersionRegistrar b;

    public DefaultUserAgentPublisher(Set<c> set, GlobalLibraryVersionRegistrar globalLibraryVersionRegistrar) {
        this.f11473a = c(set);
        this.b = globalLibraryVersionRegistrar;
    }

    public static /* synthetic */ UserAgentPublisher b(ComponentContainer componentContainer) {
        return new DefaultUserAgentPublisher(componentContainer.setOf(c.class), GlobalLibraryVersionRegistrar.getInstance());
    }

    public static String c(Set<c> set) {
        StringBuilder sb = new StringBuilder();
        Iterator<c> it = set.iterator();
        while (it.hasNext()) {
            c next = it.next();
            sb.append(next.b());
            sb.append('/');
            sb.append(next.c());
            if (it.hasNext()) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public static Component<UserAgentPublisher> component() {
        return Component.builder(UserAgentPublisher.class).add(Dependency.setOf(c.class)).factory(new ComponentFactory() { // from class: com.google.firebase.platforminfo.b
            @Override // com.google.firebase.components.ComponentFactory
            public final Object create(ComponentContainer componentContainer) {
                UserAgentPublisher b;
                b = DefaultUserAgentPublisher.b(componentContainer);
                return b;
            }
        }).build();
    }

    @Override // com.google.firebase.platforminfo.UserAgentPublisher
    public String getUserAgent() {
        if (this.b.a().isEmpty()) {
            return this.f11473a;
        }
        return this.f11473a + ' ' + c(this.b.a());
    }
}
