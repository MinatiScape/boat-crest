package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.JsonReader;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;
/* loaded from: classes10.dex */
public final /* synthetic */ class b implements CrashlyticsReportJsonTransform.a {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ b f11231a = new b();

    @Override // com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform.a
    public final Object a(JsonReader jsonReader) {
        CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame r;
        r = CrashlyticsReportJsonTransform.r(jsonReader);
        return r;
    }
}
