package com.google.android.play.core.integrity;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.integrity.internal.y;
import java.util.ArrayList;
/* loaded from: classes10.dex */
final class t {
    @Nullable
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final com.google.android.play.integrity.internal.v f10464a;
    private final com.google.android.play.integrity.internal.k b;
    private final String c;

    public t(Context context, com.google.android.play.integrity.internal.k kVar) {
        this.c = context.getPackageName();
        this.b = kVar;
        if (!y.a(context)) {
            kVar.b("Phonesky is not installed.", new Object[0]);
            this.f10464a = null;
            return;
        }
        this.f10464a = new com.google.android.play.integrity.internal.v(context, kVar, "IntegrityService", u.f10465a, new Object() { // from class: com.google.android.play.core.integrity.q
        }, null, null);
    }

    public static /* bridge */ /* synthetic */ Bundle a(t tVar, byte[] bArr, Long l) {
        Bundle bundle = new Bundle();
        bundle.putString("package.name", tVar.c);
        bundle.putByteArray("nonce", bArr);
        bundle.putInt("playcore.integrity.version.major", 1);
        bundle.putInt("playcore.integrity.version.minor", 1);
        bundle.putInt("playcore.integrity.version.patch", 0);
        if (l != null) {
            bundle.putLong("cloud.prj", l.longValue());
        }
        ArrayList<com.google.android.play.integrity.internal.e> arrayList = new ArrayList();
        arrayList.add(com.google.android.play.integrity.internal.e.c(3, System.currentTimeMillis()));
        ArrayList arrayList2 = new ArrayList();
        for (com.google.android.play.integrity.internal.e eVar : arrayList) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("event_type", 3);
            bundle2.putLong("event_timestamp", eVar.b());
            arrayList2.add(bundle2);
        }
        bundle.putParcelableArrayList("event_timestamps", new ArrayList<>(arrayList2));
        return bundle;
    }

    public final Task b(IntegrityTokenRequest integrityTokenRequest) {
        if (this.f10464a == null) {
            return Tasks.forException(new IntegrityServiceException(-2, null));
        }
        try {
            byte[] decode = Base64.decode(integrityTokenRequest.nonce(), 10);
            Long cloudProjectNumber = integrityTokenRequest.cloudProjectNumber();
            this.b.d("requestIntegrityToken(%s)", integrityTokenRequest);
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            this.f10464a.p(new r(this, taskCompletionSource, decode, cloudProjectNumber, taskCompletionSource, integrityTokenRequest), taskCompletionSource);
            return taskCompletionSource.getTask();
        } catch (IllegalArgumentException e) {
            return Tasks.forException(new IntegrityServiceException(-13, e));
        }
    }
}
