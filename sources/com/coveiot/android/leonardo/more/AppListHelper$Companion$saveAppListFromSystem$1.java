package com.coveiot.android.leonardo.more;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Telephony;
import android.telecom.TelecomManager;
import com.coveiot.android.boat.R;
import com.coveiot.covepreferences.AppListPreference;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.more.AppListHelper$Companion$saveAppListFromSystem$1", f = "AppListHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class AppListHelper$Companion$saveAppListFromSystem$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppListHelper$Companion$saveAppListFromSystem$1(Context context, Continuation<? super AppListHelper$Companion$saveAppListFromSystem$1> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new AppListHelper$Companion$saveAppListFromSystem$1(this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((AppListHelper$Companion$saveAppListFromSystem$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            final PackageManager packageManager = this.$context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "context.getPackageManager()");
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            Intrinsics.checkNotNullExpressionValue(queryIntentActivities, "pm.queryIntentActivities(main, 0)");
            try {
                if (queryIntentActivities.size() > 1) {
                    h.sortWith(queryIntentActivities, new Comparator() { // from class: com.coveiot.android.leonardo.more.AppListHelper$Companion$saveAppListFromSystem$1$invokeSuspend$$inlined$sortBy$1
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            String str;
                            ResolveInfo it = (ResolveInfo) t;
                            String str2 = null;
                            if (it != null) {
                                Intrinsics.checkNotNullExpressionValue(it, "it");
                                String str3 = it.activityInfo.packageName.toString();
                                PackageManager packageManager2 = packageManager;
                                CharSequence applicationLabel = packageManager2.getApplicationLabel(packageManager2.getApplicationInfo(str3, 128));
                                Intrinsics.checkNotNull(applicationLabel, "null cannot be cast to non-null type kotlin.String");
                                str = ((String) applicationLabel).toLowerCase();
                                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
                            } else {
                                str = null;
                            }
                            ResolveInfo it2 = (ResolveInfo) t2;
                            if (it2 != null) {
                                Intrinsics.checkNotNullExpressionValue(it2, "it");
                                String str4 = it2.activityInfo.packageName.toString();
                                PackageManager packageManager3 = packageManager;
                                CharSequence applicationLabel2 = packageManager3.getApplicationLabel(packageManager3.getApplicationInfo(str4, 128));
                                Intrinsics.checkNotNull(applicationLabel2, "null cannot be cast to non-null type kotlin.String");
                                str2 = ((String) applicationLabel2).toLowerCase();
                                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).toLowerCase()");
                            }
                            return f.compareValues(str, str2);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            String[] stringArray = this.$context.getResources().getStringArray(R.array.app_package_list_cy);
            Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…rray.app_package_list_cy)");
            String[] stringArray2 = this.$context.getResources().getStringArray(R.array.app_name_list_cy);
            Intrinsics.checkNotNullExpressionValue(stringArray2, "context.resources.getStr…R.array.app_name_list_cy)");
            String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this.$context);
            Object systemService = this.$context.getSystemService("telecom");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telecom.TelecomManager");
            String defaultDialerPackage = ((TelecomManager) systemService).getDefaultDialerPackage();
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                try {
                    String str = resolveInfo.activityInfo.packageName.toString();
                    if (str != null) {
                        int size = arrayList.size();
                        boolean z = false;
                        for (int i = 0; i < size; i++) {
                            if (Intrinsics.areEqual(str, arrayList2.get(i))) {
                                z = true;
                            }
                        }
                        if (!z && !ArraysKt___ArraysKt.toList(stringArray).contains(str) && !str.equals(defaultSmsPackage) && !str.equals(defaultDialerPackage)) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
                            Intrinsics.checkNotNull(applicationLabel, "null cannot be cast to non-null type kotlin.String");
                            arrayList.add((String) applicationLabel);
                            arrayList2.add(str);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            arrayList.addAll(0, ArraysKt___ArraysKt.toList(stringArray2));
            arrayList2.addAll(0, ArraysKt___ArraysKt.toList(stringArray));
            AppListPreference.Companion.saveSystemAppsList(this.$context, new Pair<>(arrayList, arrayList2));
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
