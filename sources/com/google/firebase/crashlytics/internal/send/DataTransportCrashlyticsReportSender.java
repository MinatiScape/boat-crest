package com.google.firebase.crashlytics.internal.send;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;
import java.nio.charset.Charset;
/* loaded from: classes10.dex */
public class DataTransportCrashlyticsReportSender {
    public static final CrashlyticsReportJsonTransform c = new CrashlyticsReportJsonTransform();
    public static final String d = e("hts/cahyiseot-agolai.o/1frlglgc/aclg", "tp:/rsltcrprsp.ogepscmv/ieo/eaybtho");
    public static final String e = e("AzSBpY4F0rHiHFdinTvM", "IayrSTFL9eJ69YeSUO2");
    public static final Transformer<CrashlyticsReport, byte[]> f = new Transformer() { // from class: com.google.firebase.crashlytics.internal.send.a
        @Override // com.google.android.datatransport.Transformer
        public final Object apply(Object obj) {
            byte[] d2;
            d2 = DataTransportCrashlyticsReportSender.d((CrashlyticsReport) obj);
            return d2;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public final Transport<CrashlyticsReport> f11252a;
    public final Transformer<CrashlyticsReport, byte[]> b;

    public DataTransportCrashlyticsReportSender(Transport<CrashlyticsReport> transport, Transformer<CrashlyticsReport, byte[]> transformer) {
        this.f11252a = transport;
        this.b = transformer;
    }

    public static /* synthetic */ void c(TaskCompletionSource taskCompletionSource, CrashlyticsReportWithSessionId crashlyticsReportWithSessionId, Exception exc) {
        if (exc != null) {
            taskCompletionSource.trySetException(exc);
        } else {
            taskCompletionSource.trySetResult(crashlyticsReportWithSessionId);
        }
    }

    public static DataTransportCrashlyticsReportSender create(Context context) {
        TransportRuntime.initialize(context);
        TransportFactory newFactory = TransportRuntime.getInstance().newFactory(new CCTDestination(d, e));
        Encoding of = Encoding.of("json");
        Transformer<CrashlyticsReport, byte[]> transformer = f;
        return new DataTransportCrashlyticsReportSender(newFactory.getTransport("FIREBASE_CRASHLYTICS_REPORT", CrashlyticsReport.class, of, transformer), transformer);
    }

    public static /* synthetic */ byte[] d(CrashlyticsReport crashlyticsReport) {
        return c.reportToJson(crashlyticsReport).getBytes(Charset.forName("UTF-8"));
    }

    public static String e(String str, String str2) {
        int length = str.length() - str2.length();
        if (length >= 0 && length <= 1) {
            StringBuilder sb = new StringBuilder(str.length() + str2.length());
            for (int i = 0; i < str.length(); i++) {
                sb.append(str.charAt(i));
                if (str2.length() > i) {
                    sb.append(str2.charAt(i));
                }
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Invalid input received");
    }

    @NonNull
    public Task<CrashlyticsReportWithSessionId> sendReport(@NonNull final CrashlyticsReportWithSessionId crashlyticsReportWithSessionId) {
        CrashlyticsReport report = crashlyticsReportWithSessionId.getReport();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.f11252a.schedule(Event.ofUrgent(report), new TransportScheduleCallback() { // from class: com.google.firebase.crashlytics.internal.send.b
            @Override // com.google.android.datatransport.TransportScheduleCallback
            public final void onSchedule(Exception exc) {
                DataTransportCrashlyticsReportSender.c(TaskCompletionSource.this, crashlyticsReportWithSessionId, exc);
            }
        });
        return taskCompletionSource.getTask();
    }
}
