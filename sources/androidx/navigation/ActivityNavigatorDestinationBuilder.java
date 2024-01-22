package androidx.navigation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import androidx.annotation.IdRes;
import androidx.navigation.ActivityNavigator;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
@NavDestinationDsl
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010&\u001a\u00020%\u0012\b\b\u0001\u0010(\u001a\u00020'¢\u0006\u0004\b)\u0010*J\b\u0010\u0003\u001a\u00020\u0002H\u0016R$\u0010\u000b\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR,\u0010\u0014\u001a\f\u0012\u0006\b\u0001\u0012\u00020\r\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0018\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0006\u001a\u0004\b\u0016\u0010\b\"\u0004\b\u0017\u0010\nR$\u0010 \u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010$\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\u0006\u001a\u0004\b\"\u0010\b\"\u0004\b#\u0010\n¨\u0006+"}, d2 = {"Landroidx/navigation/ActivityNavigatorDestinationBuilder;", "Landroidx/navigation/NavDestinationBuilder;", "Landroidx/navigation/ActivityNavigator$Destination;", "build", "", "h", "Ljava/lang/String;", "getTargetPackage", "()Ljava/lang/String;", "setTargetPackage", "(Ljava/lang/String;)V", "targetPackage", "Lkotlin/reflect/KClass;", "Landroid/app/Activity;", "i", "Lkotlin/reflect/KClass;", "getActivityClass", "()Lkotlin/reflect/KClass;", "setActivityClass", "(Lkotlin/reflect/KClass;)V", "activityClass", "j", "getAction", "setAction", Constants.KEY_ACTION, "Landroid/net/Uri;", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "Landroid/net/Uri;", "getData", "()Landroid/net/Uri;", "setData", "(Landroid/net/Uri;)V", "data", "l", "getDataPattern", "setDataPattern", "dataPattern", "Landroidx/navigation/ActivityNavigator;", "navigator", "", "id", "<init>", "(Landroidx/navigation/ActivityNavigator;I)V", "navigation-runtime-ktx_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ActivityNavigatorDestinationBuilder extends NavDestinationBuilder<ActivityNavigator.Destination> {
    public final Context g;
    @Nullable
    public String h;
    @Nullable
    public KClass<? extends Activity> i;
    @Nullable
    public String j;
    @Nullable
    public Uri k;
    @Nullable
    public String l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityNavigatorDestinationBuilder(@NotNull ActivityNavigator navigator, @IdRes int i) {
        super(navigator, i);
        Intrinsics.checkParameterIsNotNull(navigator, "navigator");
        Context a2 = navigator.a();
        Intrinsics.checkExpressionValueIsNotNull(a2, "navigator.context");
        this.g = a2;
    }

    @Nullable
    public final String getAction() {
        return this.j;
    }

    @Nullable
    public final KClass<? extends Activity> getActivityClass() {
        return this.i;
    }

    @Nullable
    public final Uri getData() {
        return this.k;
    }

    @Nullable
    public final String getDataPattern() {
        return this.l;
    }

    @Nullable
    public final String getTargetPackage() {
        return this.h;
    }

    public final void setAction(@Nullable String str) {
        this.j = str;
    }

    public final void setActivityClass(@Nullable KClass<? extends Activity> kClass) {
        this.i = kClass;
    }

    public final void setData(@Nullable Uri uri) {
        this.k = uri;
    }

    public final void setDataPattern(@Nullable String str) {
        this.l = str;
    }

    public final void setTargetPackage(@Nullable String str) {
        this.h = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.navigation.NavDestinationBuilder
    @NotNull
    public ActivityNavigator.Destination build() {
        ActivityNavigator.Destination destination = (ActivityNavigator.Destination) super.build();
        destination.setTargetPackage(this.h);
        KClass<? extends Activity> kClass = this.i;
        if (kClass != null) {
            destination.setComponentName(new ComponentName(this.g, JvmClassMappingKt.getJavaClass((KClass) kClass)));
        }
        destination.setAction(this.j);
        destination.setData(this.k);
        destination.setDataPattern(this.l);
        return destination;
    }
}
