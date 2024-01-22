package com.google.android.recaptcha.internal;

import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.f;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes10.dex */
public final class zzdk {
    @NotNull
    public static final zzdk zza = new zzdk();
    @NotNull
    private static final List zzb = zzc(CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"www.recaptcha.net", "www.gstatic.com/recaptcha"}));

    private zzdk() {
    }

    public static final boolean zza(@NotNull Uri uri) {
        return !TextUtils.isEmpty(uri.toString()) && Intrinsics.areEqual("https", uri.getScheme()) && !TextUtils.isEmpty(uri.getHost()) && zzb(uri.toString());
    }

    private static final boolean zzb(String str) {
        List<String> list = zzb;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (String str2 : list) {
            if (m.startsWith$default(str, str2, false, 2, null)) {
                return true;
            }
        }
        return false;
    }

    private static final List zzc(List list) {
        ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add("https://" + ((String) it.next()) + MqttTopic.TOPIC_LEVEL_SEPARATOR);
        }
        return arrayList;
    }
}
