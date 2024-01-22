package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class AutoBatchedLogRequestEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoBatchedLogRequestEncoder();

    /* loaded from: classes6.dex */
    public static final class a implements ObjectEncoder<AndroidClientInfo> {

        /* renamed from: a  reason: collision with root package name */
        public static final a f8059a = new a();
        public static final FieldDescriptor b = FieldDescriptor.of(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        public static final FieldDescriptor c = FieldDescriptor.of("model");
        public static final FieldDescriptor d = FieldDescriptor.of("hardware");
        public static final FieldDescriptor e = FieldDescriptor.of("device");
        public static final FieldDescriptor f = FieldDescriptor.of("product");
        public static final FieldDescriptor g = FieldDescriptor.of("osBuild");
        public static final FieldDescriptor h = FieldDescriptor.of("manufacturer");
        public static final FieldDescriptor i = FieldDescriptor.of("fingerprint");
        public static final FieldDescriptor j = FieldDescriptor.of("locale");
        public static final FieldDescriptor k = FieldDescriptor.of("country");
        public static final FieldDescriptor l = FieldDescriptor.of("mccMnc");
        public static final FieldDescriptor m = FieldDescriptor.of("applicationBuild");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(AndroidClientInfo androidClientInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, androidClientInfo.getSdkVersion());
            objectEncoderContext.add(c, androidClientInfo.getModel());
            objectEncoderContext.add(d, androidClientInfo.getHardware());
            objectEncoderContext.add(e, androidClientInfo.getDevice());
            objectEncoderContext.add(f, androidClientInfo.getProduct());
            objectEncoderContext.add(g, androidClientInfo.getOsBuild());
            objectEncoderContext.add(h, androidClientInfo.getManufacturer());
            objectEncoderContext.add(i, androidClientInfo.getFingerprint());
            objectEncoderContext.add(j, androidClientInfo.getLocale());
            objectEncoderContext.add(k, androidClientInfo.getCountry());
            objectEncoderContext.add(l, androidClientInfo.getMccMnc());
            objectEncoderContext.add(m, androidClientInfo.getApplicationBuild());
        }
    }

    /* loaded from: classes6.dex */
    public static final class b implements ObjectEncoder<BatchedLogRequest> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f8060a = new b();
        public static final FieldDescriptor b = FieldDescriptor.of("logRequest");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(BatchedLogRequest batchedLogRequest, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, batchedLogRequest.getLogRequests());
        }
    }

    /* loaded from: classes6.dex */
    public static final class c implements ObjectEncoder<ClientInfo> {

        /* renamed from: a  reason: collision with root package name */
        public static final c f8061a = new c();
        public static final FieldDescriptor b = FieldDescriptor.of("clientType");
        public static final FieldDescriptor c = FieldDescriptor.of("androidClientInfo");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(ClientInfo clientInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, clientInfo.getClientType());
            objectEncoderContext.add(c, clientInfo.getAndroidClientInfo());
        }
    }

    /* loaded from: classes6.dex */
    public static final class d implements ObjectEncoder<LogEvent> {

        /* renamed from: a  reason: collision with root package name */
        public static final d f8062a = new d();
        public static final FieldDescriptor b = FieldDescriptor.of("eventTimeMs");
        public static final FieldDescriptor c = FieldDescriptor.of("eventCode");
        public static final FieldDescriptor d = FieldDescriptor.of("eventUptimeMs");
        public static final FieldDescriptor e = FieldDescriptor.of("sourceExtension");
        public static final FieldDescriptor f = FieldDescriptor.of("sourceExtensionJsonProto3");
        public static final FieldDescriptor g = FieldDescriptor.of("timezoneOffsetSeconds");
        public static final FieldDescriptor h = FieldDescriptor.of("networkConnectionInfo");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(LogEvent logEvent, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, logEvent.getEventTimeMs());
            objectEncoderContext.add(c, logEvent.getEventCode());
            objectEncoderContext.add(d, logEvent.getEventUptimeMs());
            objectEncoderContext.add(e, logEvent.getSourceExtension());
            objectEncoderContext.add(f, logEvent.getSourceExtensionJsonProto3());
            objectEncoderContext.add(g, logEvent.getTimezoneOffsetSeconds());
            objectEncoderContext.add(h, logEvent.getNetworkConnectionInfo());
        }
    }

    /* loaded from: classes6.dex */
    public static final class e implements ObjectEncoder<LogRequest> {

        /* renamed from: a  reason: collision with root package name */
        public static final e f8063a = new e();
        public static final FieldDescriptor b = FieldDescriptor.of("requestTimeMs");
        public static final FieldDescriptor c = FieldDescriptor.of("requestUptimeMs");
        public static final FieldDescriptor d = FieldDescriptor.of("clientInfo");
        public static final FieldDescriptor e = FieldDescriptor.of("logSource");
        public static final FieldDescriptor f = FieldDescriptor.of("logSourceName");
        public static final FieldDescriptor g = FieldDescriptor.of("logEvent");
        public static final FieldDescriptor h = FieldDescriptor.of("qosTier");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(LogRequest logRequest, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, logRequest.getRequestTimeMs());
            objectEncoderContext.add(c, logRequest.getRequestUptimeMs());
            objectEncoderContext.add(d, logRequest.getClientInfo());
            objectEncoderContext.add(e, logRequest.getLogSource());
            objectEncoderContext.add(f, logRequest.getLogSourceName());
            objectEncoderContext.add(g, logRequest.getLogEvents());
            objectEncoderContext.add(h, logRequest.getQosTier());
        }
    }

    /* loaded from: classes6.dex */
    public static final class f implements ObjectEncoder<NetworkConnectionInfo> {

        /* renamed from: a  reason: collision with root package name */
        public static final f f8064a = new f();
        public static final FieldDescriptor b = FieldDescriptor.of("networkType");
        public static final FieldDescriptor c = FieldDescriptor.of("mobileSubtype");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(NetworkConnectionInfo networkConnectionInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, networkConnectionInfo.getNetworkType());
            objectEncoderContext.add(c, networkConnectionInfo.getMobileSubtype());
        }
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> encoderConfig) {
        b bVar = b.f8060a;
        encoderConfig.registerEncoder(BatchedLogRequest.class, bVar);
        encoderConfig.registerEncoder(com.google.android.datatransport.cct.internal.b.class, bVar);
        e eVar = e.f8063a;
        encoderConfig.registerEncoder(LogRequest.class, eVar);
        encoderConfig.registerEncoder(com.google.android.datatransport.cct.internal.e.class, eVar);
        c cVar = c.f8061a;
        encoderConfig.registerEncoder(ClientInfo.class, cVar);
        encoderConfig.registerEncoder(com.google.android.datatransport.cct.internal.c.class, cVar);
        a aVar = a.f8059a;
        encoderConfig.registerEncoder(AndroidClientInfo.class, aVar);
        encoderConfig.registerEncoder(com.google.android.datatransport.cct.internal.a.class, aVar);
        d dVar = d.f8062a;
        encoderConfig.registerEncoder(LogEvent.class, dVar);
        encoderConfig.registerEncoder(com.google.android.datatransport.cct.internal.d.class, dVar);
        f fVar = f.f8064a;
        encoderConfig.registerEncoder(NetworkConnectionInfo.class, fVar);
        encoderConfig.registerEncoder(g.class, fVar);
    }
}
