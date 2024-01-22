package com.google.firebase.crashlytics.internal.model;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.io.IOException;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes10.dex */
public final class AutoCrashlyticsReportEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoCrashlyticsReportEncoder();

    /* loaded from: classes10.dex */
    public static final class a implements ObjectEncoder<CrashlyticsReport.ApplicationExitInfo> {

        /* renamed from: a  reason: collision with root package name */
        public static final a f11170a = new a();
        public static final FieldDescriptor b = FieldDescriptor.of("pid");
        public static final FieldDescriptor c = FieldDescriptor.of("processName");
        public static final FieldDescriptor d = FieldDescriptor.of("reasonCode");
        public static final FieldDescriptor e = FieldDescriptor.of("importance");
        public static final FieldDescriptor f = FieldDescriptor.of("pss");
        public static final FieldDescriptor g = FieldDescriptor.of("rss");
        public static final FieldDescriptor h = FieldDescriptor.of("timestamp");
        public static final FieldDescriptor i = FieldDescriptor.of("traceFile");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.ApplicationExitInfo applicationExitInfo, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, applicationExitInfo.getPid());
            objectEncoderContext.add(c, applicationExitInfo.getProcessName());
            objectEncoderContext.add(d, applicationExitInfo.getReasonCode());
            objectEncoderContext.add(e, applicationExitInfo.getImportance());
            objectEncoderContext.add(f, applicationExitInfo.getPss());
            objectEncoderContext.add(g, applicationExitInfo.getRss());
            objectEncoderContext.add(h, applicationExitInfo.getTimestamp());
            objectEncoderContext.add(i, applicationExitInfo.getTraceFile());
        }
    }

    /* loaded from: classes10.dex */
    public static final class b implements ObjectEncoder<CrashlyticsReport.CustomAttribute> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f11171a = new b();
        public static final FieldDescriptor b = FieldDescriptor.of(Constants.KEY_KEY);
        public static final FieldDescriptor c = FieldDescriptor.of("value");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.CustomAttribute customAttribute, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, customAttribute.getKey());
            objectEncoderContext.add(c, customAttribute.getValue());
        }
    }

    /* loaded from: classes10.dex */
    public static final class c implements ObjectEncoder<CrashlyticsReport> {

        /* renamed from: a  reason: collision with root package name */
        public static final c f11172a = new c();
        public static final FieldDescriptor b = FieldDescriptor.of(RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
        public static final FieldDescriptor c = FieldDescriptor.of("gmpAppId");
        public static final FieldDescriptor d = FieldDescriptor.of("platform");
        public static final FieldDescriptor e = FieldDescriptor.of("installationUuid");
        public static final FieldDescriptor f = FieldDescriptor.of("buildVersion");
        public static final FieldDescriptor g = FieldDescriptor.of("displayVersion");
        public static final FieldDescriptor h = FieldDescriptor.of("session");
        public static final FieldDescriptor i = FieldDescriptor.of("ndkPayload");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport crashlyticsReport, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, crashlyticsReport.getSdkVersion());
            objectEncoderContext.add(c, crashlyticsReport.getGmpAppId());
            objectEncoderContext.add(d, crashlyticsReport.getPlatform());
            objectEncoderContext.add(e, crashlyticsReport.getInstallationUuid());
            objectEncoderContext.add(f, crashlyticsReport.getBuildVersion());
            objectEncoderContext.add(g, crashlyticsReport.getDisplayVersion());
            objectEncoderContext.add(h, crashlyticsReport.getSession());
            objectEncoderContext.add(i, crashlyticsReport.getNdkPayload());
        }
    }

    /* loaded from: classes10.dex */
    public static final class d implements ObjectEncoder<CrashlyticsReport.FilesPayload> {

        /* renamed from: a  reason: collision with root package name */
        public static final d f11173a = new d();
        public static final FieldDescriptor b = FieldDescriptor.of("files");
        public static final FieldDescriptor c = FieldDescriptor.of("orgId");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.FilesPayload filesPayload, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, filesPayload.getFiles());
            objectEncoderContext.add(c, filesPayload.getOrgId());
        }
    }

    /* loaded from: classes10.dex */
    public static final class e implements ObjectEncoder<CrashlyticsReport.FilesPayload.File> {

        /* renamed from: a  reason: collision with root package name */
        public static final e f11174a = new e();
        public static final FieldDescriptor b = FieldDescriptor.of("filename");
        public static final FieldDescriptor c = FieldDescriptor.of("contents");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.FilesPayload.File file, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, file.getFilename());
            objectEncoderContext.add(c, file.getContents());
        }
    }

    /* loaded from: classes10.dex */
    public static final class f implements ObjectEncoder<CrashlyticsReport.Session.Application> {

        /* renamed from: a  reason: collision with root package name */
        public static final f f11175a = new f();
        public static final FieldDescriptor b = FieldDescriptor.of("identifier");
        public static final FieldDescriptor c = FieldDescriptor.of("version");
        public static final FieldDescriptor d = FieldDescriptor.of("displayVersion");
        public static final FieldDescriptor e = FieldDescriptor.of("organization");
        public static final FieldDescriptor f = FieldDescriptor.of("installationUuid");
        public static final FieldDescriptor g = FieldDescriptor.of("developmentPlatform");
        public static final FieldDescriptor h = FieldDescriptor.of("developmentPlatformVersion");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Application application, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, application.getIdentifier());
            objectEncoderContext.add(c, application.getVersion());
            objectEncoderContext.add(d, application.getDisplayVersion());
            objectEncoderContext.add(e, application.getOrganization());
            objectEncoderContext.add(f, application.getInstallationUuid());
            objectEncoderContext.add(g, application.getDevelopmentPlatform());
            objectEncoderContext.add(h, application.getDevelopmentPlatformVersion());
        }
    }

    /* loaded from: classes10.dex */
    public static final class g implements ObjectEncoder<CrashlyticsReport.Session.Application.Organization> {

        /* renamed from: a  reason: collision with root package name */
        public static final g f11176a = new g();
        public static final FieldDescriptor b = FieldDescriptor.of("clsId");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Application.Organization organization, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, organization.getClsId());
        }
    }

    /* loaded from: classes10.dex */
    public static final class h implements ObjectEncoder<CrashlyticsReport.Session.Device> {

        /* renamed from: a  reason: collision with root package name */
        public static final h f11177a = new h();
        public static final FieldDescriptor b = FieldDescriptor.of("arch");
        public static final FieldDescriptor c = FieldDescriptor.of("model");
        public static final FieldDescriptor d = FieldDescriptor.of("cores");
        public static final FieldDescriptor e = FieldDescriptor.of("ram");
        public static final FieldDescriptor f = FieldDescriptor.of("diskSpace");
        public static final FieldDescriptor g = FieldDescriptor.of("simulator");
        public static final FieldDescriptor h = FieldDescriptor.of("state");
        public static final FieldDescriptor i = FieldDescriptor.of("manufacturer");
        public static final FieldDescriptor j = FieldDescriptor.of("modelClass");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Device device, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, device.getArch());
            objectEncoderContext.add(c, device.getModel());
            objectEncoderContext.add(d, device.getCores());
            objectEncoderContext.add(e, device.getRam());
            objectEncoderContext.add(f, device.getDiskSpace());
            objectEncoderContext.add(g, device.isSimulator());
            objectEncoderContext.add(h, device.getState());
            objectEncoderContext.add(i, device.getManufacturer());
            objectEncoderContext.add(j, device.getModelClass());
        }
    }

    /* loaded from: classes10.dex */
    public static final class i implements ObjectEncoder<CrashlyticsReport.Session> {

        /* renamed from: a  reason: collision with root package name */
        public static final i f11178a = new i();
        public static final FieldDescriptor b = FieldDescriptor.of("generator");
        public static final FieldDescriptor c = FieldDescriptor.of("identifier");
        public static final FieldDescriptor d = FieldDescriptor.of("startedAt");
        public static final FieldDescriptor e = FieldDescriptor.of("endedAt");
        public static final FieldDescriptor f = FieldDescriptor.of("crashed");
        public static final FieldDescriptor g = FieldDescriptor.of("app");
        public static final FieldDescriptor h = FieldDescriptor.of("user");
        public static final FieldDescriptor i = FieldDescriptor.of("os");
        public static final FieldDescriptor j = FieldDescriptor.of("device");
        public static final FieldDescriptor k = FieldDescriptor.of("events");
        public static final FieldDescriptor l = FieldDescriptor.of("generatorType");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session session, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, session.getGenerator());
            objectEncoderContext.add(c, session.getIdentifierUtf8Bytes());
            objectEncoderContext.add(d, session.getStartedAt());
            objectEncoderContext.add(e, session.getEndedAt());
            objectEncoderContext.add(f, session.isCrashed());
            objectEncoderContext.add(g, session.getApp());
            objectEncoderContext.add(h, session.getUser());
            objectEncoderContext.add(i, session.getOs());
            objectEncoderContext.add(j, session.getDevice());
            objectEncoderContext.add(k, session.getEvents());
            objectEncoderContext.add(l, session.getGeneratorType());
        }
    }

    /* loaded from: classes10.dex */
    public static final class j implements ObjectEncoder<CrashlyticsReport.Session.Event.Application> {

        /* renamed from: a  reason: collision with root package name */
        public static final j f11179a = new j();
        public static final FieldDescriptor b = FieldDescriptor.of("execution");
        public static final FieldDescriptor c = FieldDescriptor.of("customAttributes");
        public static final FieldDescriptor d = FieldDescriptor.of("internalKeys");
        public static final FieldDescriptor e = FieldDescriptor.of("background");
        public static final FieldDescriptor f = FieldDescriptor.of("uiOrientation");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event.Application application, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, application.getExecution());
            objectEncoderContext.add(c, application.getCustomAttributes());
            objectEncoderContext.add(d, application.getInternalKeys());
            objectEncoderContext.add(e, application.getBackground());
            objectEncoderContext.add(f, application.getUiOrientation());
        }
    }

    /* loaded from: classes10.dex */
    public static final class k implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> {

        /* renamed from: a  reason: collision with root package name */
        public static final k f11180a = new k();
        public static final FieldDescriptor b = FieldDescriptor.of("baseAddress");
        public static final FieldDescriptor c = FieldDescriptor.of("size");
        public static final FieldDescriptor d = FieldDescriptor.of(AppMeasurementSdk.ConditionalUserProperty.NAME);
        public static final FieldDescriptor e = FieldDescriptor.of("uuid");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage binaryImage, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, binaryImage.getBaseAddress());
            objectEncoderContext.add(c, binaryImage.getSize());
            objectEncoderContext.add(d, binaryImage.getName());
            objectEncoderContext.add(e, binaryImage.getUuidUtf8Bytes());
        }
    }

    /* loaded from: classes10.dex */
    public static final class l implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution> {

        /* renamed from: a  reason: collision with root package name */
        public static final l f11181a = new l();
        public static final FieldDescriptor b = FieldDescriptor.of("threads");
        public static final FieldDescriptor c = FieldDescriptor.of(MqttServiceConstants.TRACE_EXCEPTION);
        public static final FieldDescriptor d = FieldDescriptor.of("appExitInfo");
        public static final FieldDescriptor e = FieldDescriptor.of("signal");
        public static final FieldDescriptor f = FieldDescriptor.of("binaries");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event.Application.Execution execution, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, execution.getThreads());
            objectEncoderContext.add(c, execution.getException());
            objectEncoderContext.add(d, execution.getAppExitInfo());
            objectEncoderContext.add(e, execution.getSignal());
            objectEncoderContext.add(f, execution.getBinaries());
        }
    }

    /* loaded from: classes10.dex */
    public static final class m implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Exception> {

        /* renamed from: a  reason: collision with root package name */
        public static final m f11182a = new m();
        public static final FieldDescriptor b = FieldDescriptor.of("type");
        public static final FieldDescriptor c = FieldDescriptor.of("reason");
        public static final FieldDescriptor d = FieldDescriptor.of("frames");
        public static final FieldDescriptor e = FieldDescriptor.of("causedBy");
        public static final FieldDescriptor f = FieldDescriptor.of("overflowCount");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Exception exception, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, exception.getType());
            objectEncoderContext.add(c, exception.getReason());
            objectEncoderContext.add(d, exception.getFrames());
            objectEncoderContext.add(e, exception.getCausedBy());
            objectEncoderContext.add(f, exception.getOverflowCount());
        }
    }

    /* loaded from: classes10.dex */
    public static final class n implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Signal> {

        /* renamed from: a  reason: collision with root package name */
        public static final n f11183a = new n();
        public static final FieldDescriptor b = FieldDescriptor.of(AppMeasurementSdk.ConditionalUserProperty.NAME);
        public static final FieldDescriptor c = FieldDescriptor.of("code");
        public static final FieldDescriptor d = FieldDescriptor.of("address");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Signal signal, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, signal.getName());
            objectEncoderContext.add(c, signal.getCode());
            objectEncoderContext.add(d, signal.getAddress());
        }
    }

    /* loaded from: classes10.dex */
    public static final class o implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread> {

        /* renamed from: a  reason: collision with root package name */
        public static final o f11184a = new o();
        public static final FieldDescriptor b = FieldDescriptor.of(AppMeasurementSdk.ConditionalUserProperty.NAME);
        public static final FieldDescriptor c = FieldDescriptor.of("importance");
        public static final FieldDescriptor d = FieldDescriptor.of("frames");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread thread, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, thread.getName());
            objectEncoderContext.add(c, thread.getImportance());
            objectEncoderContext.add(d, thread.getFrames());
        }
    }

    /* loaded from: classes10.dex */
    public static final class p implements ObjectEncoder<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> {

        /* renamed from: a  reason: collision with root package name */
        public static final p f11185a = new p();
        public static final FieldDescriptor b = FieldDescriptor.of("pc");
        public static final FieldDescriptor c = FieldDescriptor.of("symbol");
        public static final FieldDescriptor d = FieldDescriptor.of("file");
        public static final FieldDescriptor e = FieldDescriptor.of(TypedValues.CycleType.S_WAVE_OFFSET);
        public static final FieldDescriptor f = FieldDescriptor.of("importance");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame frame, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, frame.getPc());
            objectEncoderContext.add(c, frame.getSymbol());
            objectEncoderContext.add(d, frame.getFile());
            objectEncoderContext.add(e, frame.getOffset());
            objectEncoderContext.add(f, frame.getImportance());
        }
    }

    /* loaded from: classes10.dex */
    public static final class q implements ObjectEncoder<CrashlyticsReport.Session.Event.Device> {

        /* renamed from: a  reason: collision with root package name */
        public static final q f11186a = new q();
        public static final FieldDescriptor b = FieldDescriptor.of(DeviceKey.BatteryLevel);
        public static final FieldDescriptor c = FieldDescriptor.of("batteryVelocity");
        public static final FieldDescriptor d = FieldDescriptor.of("proximityOn");
        public static final FieldDescriptor e = FieldDescriptor.of(Constants.KEY_ORIENTATION);
        public static final FieldDescriptor f = FieldDescriptor.of("ramUsed");
        public static final FieldDescriptor g = FieldDescriptor.of("diskUsed");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event.Device device, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, device.getBatteryLevel());
            objectEncoderContext.add(c, device.getBatteryVelocity());
            objectEncoderContext.add(d, device.isProximityOn());
            objectEncoderContext.add(e, device.getOrientation());
            objectEncoderContext.add(f, device.getRamUsed());
            objectEncoderContext.add(g, device.getDiskUsed());
        }
    }

    /* loaded from: classes10.dex */
    public static final class r implements ObjectEncoder<CrashlyticsReport.Session.Event> {

        /* renamed from: a  reason: collision with root package name */
        public static final r f11187a = new r();
        public static final FieldDescriptor b = FieldDescriptor.of("timestamp");
        public static final FieldDescriptor c = FieldDescriptor.of("type");
        public static final FieldDescriptor d = FieldDescriptor.of("app");
        public static final FieldDescriptor e = FieldDescriptor.of("device");
        public static final FieldDescriptor f = FieldDescriptor.of("log");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event event, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, event.getTimestamp());
            objectEncoderContext.add(c, event.getType());
            objectEncoderContext.add(d, event.getApp());
            objectEncoderContext.add(e, event.getDevice());
            objectEncoderContext.add(f, event.getLog());
        }
    }

    /* loaded from: classes10.dex */
    public static final class s implements ObjectEncoder<CrashlyticsReport.Session.Event.Log> {

        /* renamed from: a  reason: collision with root package name */
        public static final s f11188a = new s();
        public static final FieldDescriptor b = FieldDescriptor.of("content");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.Event.Log log, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, log.getContent());
        }
    }

    /* loaded from: classes10.dex */
    public static final class t implements ObjectEncoder<CrashlyticsReport.Session.OperatingSystem> {

        /* renamed from: a  reason: collision with root package name */
        public static final t f11189a = new t();
        public static final FieldDescriptor b = FieldDescriptor.of("platform");
        public static final FieldDescriptor c = FieldDescriptor.of("version");
        public static final FieldDescriptor d = FieldDescriptor.of("buildVersion");
        public static final FieldDescriptor e = FieldDescriptor.of("jailbroken");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.OperatingSystem operatingSystem, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, operatingSystem.getPlatform());
            objectEncoderContext.add(c, operatingSystem.getVersion());
            objectEncoderContext.add(d, operatingSystem.getBuildVersion());
            objectEncoderContext.add(e, operatingSystem.isJailbroken());
        }
    }

    /* loaded from: classes10.dex */
    public static final class u implements ObjectEncoder<CrashlyticsReport.Session.User> {

        /* renamed from: a  reason: collision with root package name */
        public static final u f11190a = new u();
        public static final FieldDescriptor b = FieldDescriptor.of("identifier");

        @Override // com.google.firebase.encoders.ObjectEncoder
        /* renamed from: a */
        public void encode(CrashlyticsReport.Session.User user, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(b, user.getIdentifier());
        }
    }

    @Override // com.google.firebase.encoders.config.Configurator
    public void configure(EncoderConfig<?> encoderConfig) {
        c cVar = c.f11172a;
        encoderConfig.registerEncoder(CrashlyticsReport.class, cVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.a.class, cVar);
        i iVar = i.f11178a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.class, iVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.f.class, iVar);
        f fVar = f.f11175a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Application.class, fVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.g.class, fVar);
        g gVar = g.f11176a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Application.Organization.class, gVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.h.class, gVar);
        u uVar = u.f11190a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.User.class, uVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.u.class, uVar);
        t tVar = t.f11189a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.OperatingSystem.class, tVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.t.class, tVar);
        h hVar = h.f11177a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Device.class, hVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.i.class, hVar);
        r rVar = r.f11187a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.class, rVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.j.class, rVar);
        j jVar = j.f11179a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.class, jVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.k.class, jVar);
        l lVar = l.f11181a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.class, lVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.l.class, lVar);
        o oVar = o.f11184a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.class, oVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.p.class, oVar);
        p pVar = p.f11185a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.class, pVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.q.class, pVar);
        m mVar = m.f11182a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Exception.class, mVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.n.class, mVar);
        a aVar = a.f11170a;
        encoderConfig.registerEncoder(CrashlyticsReport.ApplicationExitInfo.class, aVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.b.class, aVar);
        n nVar = n.f11183a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.Signal.class, nVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.o.class, nVar);
        k kVar = k.f11180a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.class, kVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.m.class, kVar);
        b bVar = b.f11171a;
        encoderConfig.registerEncoder(CrashlyticsReport.CustomAttribute.class, bVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.c.class, bVar);
        q qVar = q.f11186a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Device.class, qVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.r.class, qVar);
        s sVar = s.f11188a;
        encoderConfig.registerEncoder(CrashlyticsReport.Session.Event.Log.class, sVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.s.class, sVar);
        d dVar = d.f11173a;
        encoderConfig.registerEncoder(CrashlyticsReport.FilesPayload.class, dVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.d.class, dVar);
        e eVar = e.f11174a;
        encoderConfig.registerEncoder(CrashlyticsReport.FilesPayload.File.class, eVar);
        encoderConfig.registerEncoder(com.google.firebase.crashlytics.internal.model.e.class, eVar);
    }
}
