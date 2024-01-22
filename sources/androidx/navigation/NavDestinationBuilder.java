package androidx.navigation;

import androidx.annotation.IdRes;
import androidx.navigation.NavDestination;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@NavDestinationDsl
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\b\u0017\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u00012\u00020\u0003B!\u0012\u000e\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u001f\u0012\b\b\u0001\u0010)\u001a\u00020\u0010¢\u0006\u0004\b*\u0010+J'\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tJ\u000e\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0004J\u001f\u0010\r\u001a\u00020\b2\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tJ'\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tJ\u000f\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R$\u0010\u001e\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u001f8\u0004@\u0004X\u0084\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010)\u001a\u00020\u00108\u0006@\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(¨\u0006,"}, d2 = {"Landroidx/navigation/NavDestinationBuilder;", "Landroidx/navigation/NavDestination;", "D", "", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "Lkotlin/Function1;", "Landroidx/navigation/NavArgumentBuilder;", "", "Lkotlin/ExtensionFunctionType;", "argumentBuilder", "argument", "uriPattern", "deepLink", "Landroidx/navigation/NavDeepLinkDslBuilder;", "navDeepLink", "", "actionId", "Landroidx/navigation/NavActionBuilder;", "actionBuilder", Constants.KEY_ACTION, "build", "()Landroidx/navigation/NavDestination;", "", "a", "Ljava/lang/CharSequence;", "getLabel", "()Ljava/lang/CharSequence;", "setLabel", "(Ljava/lang/CharSequence;)V", Constants.ScionAnalytics.PARAM_LABEL, "Landroidx/navigation/Navigator;", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Landroidx/navigation/Navigator;", "getNavigator", "()Landroidx/navigation/Navigator;", "navigator", "f", "I", "getId", "()I", "id", "<init>", "(Landroidx/navigation/Navigator;I)V", "navigation-common-ktx_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public class NavDestinationBuilder<D extends NavDestination> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public CharSequence f1447a;
    public Map<String, NavArgument> b;
    public List<NavDeepLink> c;
    public Map<Integer, NavAction> d;
    @NotNull
    public final Navigator<? extends D> e;
    public final int f;

    public NavDestinationBuilder(@NotNull Navigator<? extends D> navigator, @IdRes int i) {
        Intrinsics.checkParameterIsNotNull(navigator, "navigator");
        this.e = navigator;
        this.f = i;
        this.b = new LinkedHashMap();
        this.c = new ArrayList();
        this.d = new LinkedHashMap();
    }

    public final void action(int i, @NotNull Function1<? super NavActionBuilder, Unit> actionBuilder) {
        Intrinsics.checkParameterIsNotNull(actionBuilder, "actionBuilder");
        Map<Integer, NavAction> map = this.d;
        Integer valueOf = Integer.valueOf(i);
        NavActionBuilder navActionBuilder = new NavActionBuilder();
        actionBuilder.invoke(navActionBuilder);
        map.put(valueOf, navActionBuilder.build$navigation_common_ktx_release());
    }

    public final void argument(@NotNull String name, @NotNull Function1<? super NavArgumentBuilder, Unit> argumentBuilder) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(argumentBuilder, "argumentBuilder");
        Map<String, NavArgument> map = this.b;
        NavArgumentBuilder navArgumentBuilder = new NavArgumentBuilder();
        argumentBuilder.invoke(navArgumentBuilder);
        map.put(name, navArgumentBuilder.build());
    }

    @NotNull
    public D build() {
        D createDestination = this.e.createDestination();
        createDestination.setId(this.f);
        createDestination.setLabel(this.f1447a);
        for (Map.Entry<String, NavArgument> entry : this.b.entrySet()) {
            createDestination.addArgument(entry.getKey(), entry.getValue());
        }
        for (NavDeepLink navDeepLink : this.c) {
            createDestination.addDeepLink(navDeepLink);
        }
        for (Map.Entry<Integer, NavAction> entry2 : this.d.entrySet()) {
            createDestination.putAction(entry2.getKey().intValue(), entry2.getValue());
        }
        return createDestination;
    }

    public final void deepLink(@NotNull String uriPattern) {
        Intrinsics.checkParameterIsNotNull(uriPattern, "uriPattern");
        this.c.add(new NavDeepLink(uriPattern));
    }

    public final int getId() {
        return this.f;
    }

    @Nullable
    public final CharSequence getLabel() {
        return this.f1447a;
    }

    @NotNull
    public final Navigator<? extends D> getNavigator() {
        return this.e;
    }

    public final void setLabel(@Nullable CharSequence charSequence) {
        this.f1447a = charSequence;
    }

    public final void deepLink(@NotNull Function1<? super NavDeepLinkDslBuilder, Unit> navDeepLink) {
        Intrinsics.checkParameterIsNotNull(navDeepLink, "navDeepLink");
        List<NavDeepLink> list = this.c;
        NavDeepLinkDslBuilder navDeepLinkDslBuilder = new NavDeepLinkDslBuilder();
        navDeepLink.invoke(navDeepLinkDslBuilder);
        list.add(navDeepLinkDslBuilder.build$navigation_common_ktx_release());
    }
}
