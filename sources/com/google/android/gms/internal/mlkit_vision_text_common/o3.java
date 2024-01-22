package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class o3 implements ObjectEncoder {
    public static final FieldDescriptor A;
    public static final FieldDescriptor B;
    public static final FieldDescriptor C;
    public static final FieldDescriptor D;
    public static final FieldDescriptor E;
    public static final FieldDescriptor F;
    public static final FieldDescriptor G;
    public static final FieldDescriptor H;
    public static final FieldDescriptor I;
    public static final FieldDescriptor J;
    public static final FieldDescriptor K;
    public static final FieldDescriptor L;
    public static final FieldDescriptor M;
    public static final FieldDescriptor N;
    public static final FieldDescriptor O;
    public static final FieldDescriptor P;
    public static final FieldDescriptor Q;
    public static final FieldDescriptor R;
    public static final FieldDescriptor S;
    public static final FieldDescriptor T;
    public static final FieldDescriptor U;
    public static final FieldDescriptor V;
    public static final FieldDescriptor W;
    public static final FieldDescriptor X;
    public static final FieldDescriptor Y;
    public static final FieldDescriptor Z;

    /* renamed from: a  reason: collision with root package name */
    public static final o3 f9869a = new o3();
    public static final FieldDescriptor a0;
    public static final FieldDescriptor b;
    public static final FieldDescriptor b0;
    public static final FieldDescriptor c;
    public static final FieldDescriptor c0;
    public static final FieldDescriptor d;
    public static final FieldDescriptor d0;
    public static final FieldDescriptor e;
    public static final FieldDescriptor e0;
    public static final FieldDescriptor f;
    public static final FieldDescriptor f0;
    public static final FieldDescriptor g;
    public static final FieldDescriptor g0;
    public static final FieldDescriptor h;
    public static final FieldDescriptor h0;
    public static final FieldDescriptor i;
    public static final FieldDescriptor i0;
    public static final FieldDescriptor j;
    public static final FieldDescriptor j0;
    public static final FieldDescriptor k;
    public static final FieldDescriptor k0;
    public static final FieldDescriptor l;
    public static final FieldDescriptor l0;
    public static final FieldDescriptor m;
    public static final FieldDescriptor m0;
    public static final FieldDescriptor n;
    public static final FieldDescriptor n0;
    public static final FieldDescriptor o;
    public static final FieldDescriptor o0;
    public static final FieldDescriptor p;
    public static final FieldDescriptor p0;
    public static final FieldDescriptor q;
    public static final FieldDescriptor q0;
    public static final FieldDescriptor r;
    public static final FieldDescriptor r0;
    public static final FieldDescriptor s;
    public static final FieldDescriptor s0;
    public static final FieldDescriptor t;
    public static final FieldDescriptor t0;
    public static final FieldDescriptor u;
    public static final FieldDescriptor u0;
    public static final FieldDescriptor v;
    public static final FieldDescriptor v0;
    public static final FieldDescriptor w;
    public static final FieldDescriptor w0;
    public static final FieldDescriptor x;
    public static final FieldDescriptor x0;
    public static final FieldDescriptor y;
    public static final FieldDescriptor y0;
    public static final FieldDescriptor z;
    public static final FieldDescriptor z0;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("systemInfo");
        zzcv zzcvVar = new zzcv();
        zzcvVar.zza(1);
        b = builder.withProperty(zzcvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("eventName");
        zzcv zzcvVar2 = new zzcv();
        zzcvVar2.zza(2);
        c = builder2.withProperty(zzcvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isThickClient");
        zzcv zzcvVar3 = new zzcv();
        zzcvVar3.zza(37);
        d = builder3.withProperty(zzcvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("clientType");
        zzcv zzcvVar4 = new zzcv();
        zzcvVar4.zza(61);
        e = builder4.withProperty(zzcvVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("modelDownloadLogEvent");
        zzcv zzcvVar5 = new zzcv();
        zzcvVar5.zza(3);
        f = builder5.withProperty(zzcvVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("customModelLoadLogEvent");
        zzcv zzcvVar6 = new zzcv();
        zzcvVar6.zza(20);
        g = builder6.withProperty(zzcvVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("customModelInferenceLogEvent");
        zzcv zzcvVar7 = new zzcv();
        zzcvVar7.zza(4);
        h = builder7.withProperty(zzcvVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("customModelCreateLogEvent");
        zzcv zzcvVar8 = new zzcv();
        zzcvVar8.zza(29);
        i = builder8.withProperty(zzcvVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("onDeviceFaceDetectionLogEvent");
        zzcv zzcvVar9 = new zzcv();
        zzcvVar9.zza(5);
        j = builder9.withProperty(zzcvVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("onDeviceFaceLoadLogEvent");
        zzcv zzcvVar10 = new zzcv();
        zzcvVar10.zza(59);
        k = builder10.withProperty(zzcvVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("onDeviceTextDetectionLogEvent");
        zzcv zzcvVar11 = new zzcv();
        zzcvVar11.zza(6);
        l = builder11.withProperty(zzcvVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("onDeviceTextDetectionLoadLogEvent");
        zzcv zzcvVar12 = new zzcv();
        zzcvVar12.zza(79);
        m = builder12.withProperty(zzcvVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("onDeviceBarcodeDetectionLogEvent");
        zzcv zzcvVar13 = new zzcv();
        zzcvVar13.zza(7);
        n = builder13.withProperty(zzcvVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("onDeviceBarcodeLoadLogEvent");
        zzcv zzcvVar14 = new zzcv();
        zzcvVar14.zza(58);
        o = builder14.withProperty(zzcvVar14.zzb()).build();
        FieldDescriptor.Builder builder15 = FieldDescriptor.builder("onDeviceImageLabelCreateLogEvent");
        zzcv zzcvVar15 = new zzcv();
        zzcvVar15.zza(48);
        p = builder15.withProperty(zzcvVar15.zzb()).build();
        FieldDescriptor.Builder builder16 = FieldDescriptor.builder("onDeviceImageLabelLoadLogEvent");
        zzcv zzcvVar16 = new zzcv();
        zzcvVar16.zza(49);
        q = builder16.withProperty(zzcvVar16.zzb()).build();
        FieldDescriptor.Builder builder17 = FieldDescriptor.builder("onDeviceImageLabelDetectionLogEvent");
        zzcv zzcvVar17 = new zzcv();
        zzcvVar17.zza(18);
        r = builder17.withProperty(zzcvVar17.zzb()).build();
        FieldDescriptor.Builder builder18 = FieldDescriptor.builder("onDeviceObjectCreateLogEvent");
        zzcv zzcvVar18 = new zzcv();
        zzcvVar18.zza(26);
        s = builder18.withProperty(zzcvVar18.zzb()).build();
        FieldDescriptor.Builder builder19 = FieldDescriptor.builder("onDeviceObjectLoadLogEvent");
        zzcv zzcvVar19 = new zzcv();
        zzcvVar19.zza(27);
        t = builder19.withProperty(zzcvVar19.zzb()).build();
        FieldDescriptor.Builder builder20 = FieldDescriptor.builder("onDeviceObjectInferenceLogEvent");
        zzcv zzcvVar20 = new zzcv();
        zzcvVar20.zza(28);
        u = builder20.withProperty(zzcvVar20.zzb()).build();
        FieldDescriptor.Builder builder21 = FieldDescriptor.builder("onDevicePoseDetectionLogEvent");
        zzcv zzcvVar21 = new zzcv();
        zzcvVar21.zza(44);
        v = builder21.withProperty(zzcvVar21.zzb()).build();
        FieldDescriptor.Builder builder22 = FieldDescriptor.builder("onDeviceSegmentationLogEvent");
        zzcv zzcvVar22 = new zzcv();
        zzcvVar22.zza(45);
        w = builder22.withProperty(zzcvVar22.zzb()).build();
        FieldDescriptor.Builder builder23 = FieldDescriptor.builder("onDeviceSmartReplyLogEvent");
        zzcv zzcvVar23 = new zzcv();
        zzcvVar23.zza(19);
        x = builder23.withProperty(zzcvVar23.zzb()).build();
        FieldDescriptor.Builder builder24 = FieldDescriptor.builder("onDeviceLanguageIdentificationLogEvent");
        zzcv zzcvVar24 = new zzcv();
        zzcvVar24.zza(21);
        y = builder24.withProperty(zzcvVar24.zzb()).build();
        FieldDescriptor.Builder builder25 = FieldDescriptor.builder("onDeviceTranslationLogEvent");
        zzcv zzcvVar25 = new zzcv();
        zzcvVar25.zza(22);
        z = builder25.withProperty(zzcvVar25.zzb()).build();
        FieldDescriptor.Builder builder26 = FieldDescriptor.builder("cloudFaceDetectionLogEvent");
        zzcv zzcvVar26 = new zzcv();
        zzcvVar26.zza(8);
        A = builder26.withProperty(zzcvVar26.zzb()).build();
        FieldDescriptor.Builder builder27 = FieldDescriptor.builder("cloudCropHintDetectionLogEvent");
        zzcv zzcvVar27 = new zzcv();
        zzcvVar27.zza(9);
        B = builder27.withProperty(zzcvVar27.zzb()).build();
        FieldDescriptor.Builder builder28 = FieldDescriptor.builder("cloudDocumentTextDetectionLogEvent");
        zzcv zzcvVar28 = new zzcv();
        zzcvVar28.zza(10);
        C = builder28.withProperty(zzcvVar28.zzb()).build();
        FieldDescriptor.Builder builder29 = FieldDescriptor.builder("cloudImagePropertiesDetectionLogEvent");
        zzcv zzcvVar29 = new zzcv();
        zzcvVar29.zza(11);
        D = builder29.withProperty(zzcvVar29.zzb()).build();
        FieldDescriptor.Builder builder30 = FieldDescriptor.builder("cloudImageLabelDetectionLogEvent");
        zzcv zzcvVar30 = new zzcv();
        zzcvVar30.zza(12);
        E = builder30.withProperty(zzcvVar30.zzb()).build();
        FieldDescriptor.Builder builder31 = FieldDescriptor.builder("cloudLandmarkDetectionLogEvent");
        zzcv zzcvVar31 = new zzcv();
        zzcvVar31.zza(13);
        F = builder31.withProperty(zzcvVar31.zzb()).build();
        FieldDescriptor.Builder builder32 = FieldDescriptor.builder("cloudLogoDetectionLogEvent");
        zzcv zzcvVar32 = new zzcv();
        zzcvVar32.zza(14);
        G = builder32.withProperty(zzcvVar32.zzb()).build();
        FieldDescriptor.Builder builder33 = FieldDescriptor.builder("cloudSafeSearchDetectionLogEvent");
        zzcv zzcvVar33 = new zzcv();
        zzcvVar33.zza(15);
        H = builder33.withProperty(zzcvVar33.zzb()).build();
        FieldDescriptor.Builder builder34 = FieldDescriptor.builder("cloudTextDetectionLogEvent");
        zzcv zzcvVar34 = new zzcv();
        zzcvVar34.zza(16);
        I = builder34.withProperty(zzcvVar34.zzb()).build();
        FieldDescriptor.Builder builder35 = FieldDescriptor.builder("cloudWebSearchDetectionLogEvent");
        zzcv zzcvVar35 = new zzcv();
        zzcvVar35.zza(17);
        J = builder35.withProperty(zzcvVar35.zzb()).build();
        FieldDescriptor.Builder builder36 = FieldDescriptor.builder("automlImageLabelingCreateLogEvent");
        zzcv zzcvVar36 = new zzcv();
        zzcvVar36.zza(23);
        K = builder36.withProperty(zzcvVar36.zzb()).build();
        FieldDescriptor.Builder builder37 = FieldDescriptor.builder("automlImageLabelingLoadLogEvent");
        zzcv zzcvVar37 = new zzcv();
        zzcvVar37.zza(24);
        L = builder37.withProperty(zzcvVar37.zzb()).build();
        FieldDescriptor.Builder builder38 = FieldDescriptor.builder("automlImageLabelingInferenceLogEvent");
        zzcv zzcvVar38 = new zzcv();
        zzcvVar38.zza(25);
        M = builder38.withProperty(zzcvVar38.zzb()).build();
        FieldDescriptor.Builder builder39 = FieldDescriptor.builder("isModelDownloadedLogEvent");
        zzcv zzcvVar39 = new zzcv();
        zzcvVar39.zza(39);
        N = builder39.withProperty(zzcvVar39.zzb()).build();
        FieldDescriptor.Builder builder40 = FieldDescriptor.builder("deleteModelLogEvent");
        zzcv zzcvVar40 = new zzcv();
        zzcvVar40.zza(40);
        O = builder40.withProperty(zzcvVar40.zzb()).build();
        FieldDescriptor.Builder builder41 = FieldDescriptor.builder("aggregatedAutomlImageLabelingInferenceLogEvent");
        zzcv zzcvVar41 = new zzcv();
        zzcvVar41.zza(30);
        P = builder41.withProperty(zzcvVar41.zzb()).build();
        FieldDescriptor.Builder builder42 = FieldDescriptor.builder("aggregatedCustomModelInferenceLogEvent");
        zzcv zzcvVar42 = new zzcv();
        zzcvVar42.zza(31);
        Q = builder42.withProperty(zzcvVar42.zzb()).build();
        FieldDescriptor.Builder builder43 = FieldDescriptor.builder("aggregatedOnDeviceFaceDetectionLogEvent");
        zzcv zzcvVar43 = new zzcv();
        zzcvVar43.zza(32);
        R = builder43.withProperty(zzcvVar43.zzb()).build();
        FieldDescriptor.Builder builder44 = FieldDescriptor.builder("aggregatedOnDeviceBarcodeDetectionLogEvent");
        zzcv zzcvVar44 = new zzcv();
        zzcvVar44.zza(33);
        S = builder44.withProperty(zzcvVar44.zzb()).build();
        FieldDescriptor.Builder builder45 = FieldDescriptor.builder("aggregatedOnDeviceImageLabelDetectionLogEvent");
        zzcv zzcvVar45 = new zzcv();
        zzcvVar45.zza(34);
        T = builder45.withProperty(zzcvVar45.zzb()).build();
        FieldDescriptor.Builder builder46 = FieldDescriptor.builder("aggregatedOnDeviceObjectInferenceLogEvent");
        zzcv zzcvVar46 = new zzcv();
        zzcvVar46.zza(35);
        U = builder46.withProperty(zzcvVar46.zzb()).build();
        FieldDescriptor.Builder builder47 = FieldDescriptor.builder("aggregatedOnDeviceTextDetectionLogEvent");
        zzcv zzcvVar47 = new zzcv();
        zzcvVar47.zza(36);
        V = builder47.withProperty(zzcvVar47.zzb()).build();
        FieldDescriptor.Builder builder48 = FieldDescriptor.builder("aggregatedOnDevicePoseDetectionLogEvent");
        zzcv zzcvVar48 = new zzcv();
        zzcvVar48.zza(46);
        W = builder48.withProperty(zzcvVar48.zzb()).build();
        FieldDescriptor.Builder builder49 = FieldDescriptor.builder("aggregatedOnDeviceSegmentationLogEvent");
        zzcv zzcvVar49 = new zzcv();
        zzcvVar49.zza(47);
        X = builder49.withProperty(zzcvVar49.zzb()).build();
        FieldDescriptor.Builder builder50 = FieldDescriptor.builder("pipelineAccelerationInferenceEvents");
        zzcv zzcvVar50 = new zzcv();
        zzcvVar50.zza(69);
        Y = builder50.withProperty(zzcvVar50.zzb()).build();
        FieldDescriptor.Builder builder51 = FieldDescriptor.builder("remoteConfigLogEvent");
        zzcv zzcvVar51 = new zzcv();
        zzcvVar51.zza(42);
        Z = builder51.withProperty(zzcvVar51.zzb()).build();
        FieldDescriptor.Builder builder52 = FieldDescriptor.builder("inputImageConstructionLogEvent");
        zzcv zzcvVar52 = new zzcv();
        zzcvVar52.zza(50);
        a0 = builder52.withProperty(zzcvVar52.zzb()).build();
        FieldDescriptor.Builder builder53 = FieldDescriptor.builder("leakedHandleEvent");
        zzcv zzcvVar53 = new zzcv();
        zzcvVar53.zza(51);
        b0 = builder53.withProperty(zzcvVar53.zzb()).build();
        FieldDescriptor.Builder builder54 = FieldDescriptor.builder("cameraSourceLogEvent");
        zzcv zzcvVar54 = new zzcv();
        zzcvVar54.zza(52);
        c0 = builder54.withProperty(zzcvVar54.zzb()).build();
        FieldDescriptor.Builder builder55 = FieldDescriptor.builder("imageLabelOptionalModuleLogEvent");
        zzcv zzcvVar55 = new zzcv();
        zzcvVar55.zza(53);
        d0 = builder55.withProperty(zzcvVar55.zzb()).build();
        FieldDescriptor.Builder builder56 = FieldDescriptor.builder("languageIdentificationOptionalModuleLogEvent");
        zzcv zzcvVar56 = new zzcv();
        zzcvVar56.zza(54);
        e0 = builder56.withProperty(zzcvVar56.zzb()).build();
        FieldDescriptor.Builder builder57 = FieldDescriptor.builder("faceDetectionOptionalModuleLogEvent");
        zzcv zzcvVar57 = new zzcv();
        zzcvVar57.zza(60);
        f0 = builder57.withProperty(zzcvVar57.zzb()).build();
        FieldDescriptor.Builder builder58 = FieldDescriptor.builder("nlClassifierOptionalModuleLogEvent");
        zzcv zzcvVar58 = new zzcv();
        zzcvVar58.zza(55);
        g0 = builder58.withProperty(zzcvVar58.zzb()).build();
        FieldDescriptor.Builder builder59 = FieldDescriptor.builder("nlClassifierClientLibraryLogEvent");
        zzcv zzcvVar59 = new zzcv();
        zzcvVar59.zza(56);
        h0 = builder59.withProperty(zzcvVar59.zzb()).build();
        FieldDescriptor.Builder builder60 = FieldDescriptor.builder("accelerationAllowlistLogEvent");
        zzcv zzcvVar60 = new zzcv();
        zzcvVar60.zza(57);
        i0 = builder60.withProperty(zzcvVar60.zzb()).build();
        FieldDescriptor.Builder builder61 = FieldDescriptor.builder("toxicityDetectionCreateEvent");
        zzcv zzcvVar61 = new zzcv();
        zzcvVar61.zza(62);
        j0 = builder61.withProperty(zzcvVar61.zzb()).build();
        FieldDescriptor.Builder builder62 = FieldDescriptor.builder("toxicityDetectionLoadEvent");
        zzcv zzcvVar62 = new zzcv();
        zzcvVar62.zza(63);
        k0 = builder62.withProperty(zzcvVar62.zzb()).build();
        FieldDescriptor.Builder builder63 = FieldDescriptor.builder("toxicityDetectionInferenceEvent");
        zzcv zzcvVar63 = new zzcv();
        zzcvVar63.zza(64);
        l0 = builder63.withProperty(zzcvVar63.zzb()).build();
        FieldDescriptor.Builder builder64 = FieldDescriptor.builder("barcodeDetectionOptionalModuleLogEvent");
        zzcv zzcvVar64 = new zzcv();
        zzcvVar64.zza(65);
        m0 = builder64.withProperty(zzcvVar64.zzb()).build();
        FieldDescriptor.Builder builder65 = FieldDescriptor.builder("customImageLabelOptionalModuleLogEvent");
        zzcv zzcvVar65 = new zzcv();
        zzcvVar65.zza(66);
        n0 = builder65.withProperty(zzcvVar65.zzb()).build();
        FieldDescriptor.Builder builder66 = FieldDescriptor.builder("codeScannerScanApiEvent");
        zzcv zzcvVar66 = new zzcv();
        zzcvVar66.zza(67);
        o0 = builder66.withProperty(zzcvVar66.zzb()).build();
        FieldDescriptor.Builder builder67 = FieldDescriptor.builder("codeScannerOptionalModuleEvent");
        zzcv zzcvVar67 = new zzcv();
        zzcvVar67.zza(68);
        p0 = builder67.withProperty(zzcvVar67.zzb()).build();
        FieldDescriptor.Builder builder68 = FieldDescriptor.builder("onDeviceExplicitContentCreateLogEvent");
        zzcv zzcvVar68 = new zzcv();
        zzcvVar68.zza(70);
        q0 = builder68.withProperty(zzcvVar68.zzb()).build();
        FieldDescriptor.Builder builder69 = FieldDescriptor.builder("onDeviceExplicitContentLoadLogEvent");
        zzcv zzcvVar69 = new zzcv();
        zzcvVar69.zza(71);
        r0 = builder69.withProperty(zzcvVar69.zzb()).build();
        FieldDescriptor.Builder builder70 = FieldDescriptor.builder("onDeviceExplicitContentInferenceLogEvent");
        zzcv zzcvVar70 = new zzcv();
        zzcvVar70.zza(72);
        s0 = builder70.withProperty(zzcvVar70.zzb()).build();
        FieldDescriptor.Builder builder71 = FieldDescriptor.builder("aggregatedOnDeviceExplicitContentLogEvent");
        zzcv zzcvVar71 = new zzcv();
        zzcvVar71.zza(73);
        t0 = builder71.withProperty(zzcvVar71.zzb()).build();
        FieldDescriptor.Builder builder72 = FieldDescriptor.builder("onDeviceFaceMeshCreateLogEvent");
        zzcv zzcvVar72 = new zzcv();
        zzcvVar72.zza(74);
        u0 = builder72.withProperty(zzcvVar72.zzb()).build();
        FieldDescriptor.Builder builder73 = FieldDescriptor.builder("onDeviceFaceMeshLoadLogEvent");
        zzcv zzcvVar73 = new zzcv();
        zzcvVar73.zza(75);
        v0 = builder73.withProperty(zzcvVar73.zzb()).build();
        FieldDescriptor.Builder builder74 = FieldDescriptor.builder("onDeviceFaceMeshLogEvent");
        zzcv zzcvVar74 = new zzcv();
        zzcvVar74.zza(76);
        w0 = builder74.withProperty(zzcvVar74.zzb()).build();
        FieldDescriptor.Builder builder75 = FieldDescriptor.builder("aggregatedOnDeviceFaceMeshLogEvent");
        zzcv zzcvVar75 = new zzcv();
        zzcvVar75.zza(77);
        x0 = builder75.withProperty(zzcvVar75.zzb()).build();
        FieldDescriptor.Builder builder76 = FieldDescriptor.builder("smartReplyOptionalModuleLogEvent");
        zzcv zzcvVar76 = new zzcv();
        zzcvVar76.zza(78);
        y0 = builder76.withProperty(zzcvVar76.zzb()).build();
        FieldDescriptor.Builder builder77 = FieldDescriptor.builder("textDetectionOptionalModuleLogEvent");
        zzcv zzcvVar77 = new zzcv();
        zzcvVar77.zza(80);
        z0 = builder77.withProperty(zzcvVar77.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzkw zzkwVar = (zzkw) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzkwVar.zzf());
        objectEncoderContext.add(c, zzkwVar.zzc());
        objectEncoderContext.add(d, (Object) null);
        objectEncoderContext.add(e, zzkwVar.zzb());
        objectEncoderContext.add(f, (Object) null);
        objectEncoderContext.add(g, (Object) null);
        objectEncoderContext.add(h, (Object) null);
        objectEncoderContext.add(i, (Object) null);
        objectEncoderContext.add(j, (Object) null);
        objectEncoderContext.add(k, (Object) null);
        objectEncoderContext.add(l, zzkwVar.zze());
        objectEncoderContext.add(m, zzkwVar.zzd());
        objectEncoderContext.add(n, (Object) null);
        objectEncoderContext.add(o, (Object) null);
        objectEncoderContext.add(p, (Object) null);
        objectEncoderContext.add(q, (Object) null);
        objectEncoderContext.add(r, (Object) null);
        objectEncoderContext.add(s, (Object) null);
        objectEncoderContext.add(t, (Object) null);
        objectEncoderContext.add(u, (Object) null);
        objectEncoderContext.add(v, (Object) null);
        objectEncoderContext.add(w, (Object) null);
        objectEncoderContext.add(x, (Object) null);
        objectEncoderContext.add(y, (Object) null);
        objectEncoderContext.add(z, (Object) null);
        objectEncoderContext.add(A, (Object) null);
        objectEncoderContext.add(B, (Object) null);
        objectEncoderContext.add(C, (Object) null);
        objectEncoderContext.add(D, (Object) null);
        objectEncoderContext.add(E, (Object) null);
        objectEncoderContext.add(F, (Object) null);
        objectEncoderContext.add(G, (Object) null);
        objectEncoderContext.add(H, (Object) null);
        objectEncoderContext.add(I, (Object) null);
        objectEncoderContext.add(J, (Object) null);
        objectEncoderContext.add(K, (Object) null);
        objectEncoderContext.add(L, (Object) null);
        objectEncoderContext.add(M, (Object) null);
        objectEncoderContext.add(N, (Object) null);
        objectEncoderContext.add(O, (Object) null);
        objectEncoderContext.add(P, (Object) null);
        objectEncoderContext.add(Q, (Object) null);
        objectEncoderContext.add(R, (Object) null);
        objectEncoderContext.add(S, (Object) null);
        objectEncoderContext.add(T, (Object) null);
        objectEncoderContext.add(U, (Object) null);
        objectEncoderContext.add(V, zzkwVar.zza());
        objectEncoderContext.add(W, (Object) null);
        objectEncoderContext.add(X, (Object) null);
        objectEncoderContext.add(Y, (Object) null);
        objectEncoderContext.add(Z, (Object) null);
        objectEncoderContext.add(a0, (Object) null);
        objectEncoderContext.add(b0, (Object) null);
        objectEncoderContext.add(c0, (Object) null);
        objectEncoderContext.add(d0, (Object) null);
        objectEncoderContext.add(e0, (Object) null);
        objectEncoderContext.add(f0, (Object) null);
        objectEncoderContext.add(g0, (Object) null);
        objectEncoderContext.add(h0, (Object) null);
        objectEncoderContext.add(i0, (Object) null);
        objectEncoderContext.add(j0, (Object) null);
        objectEncoderContext.add(k0, (Object) null);
        objectEncoderContext.add(l0, (Object) null);
        objectEncoderContext.add(m0, (Object) null);
        objectEncoderContext.add(n0, (Object) null);
        objectEncoderContext.add(o0, (Object) null);
        objectEncoderContext.add(p0, (Object) null);
        objectEncoderContext.add(q0, (Object) null);
        objectEncoderContext.add(r0, (Object) null);
        objectEncoderContext.add(s0, (Object) null);
        objectEncoderContext.add(t0, (Object) null);
        objectEncoderContext.add(u0, (Object) null);
        objectEncoderContext.add(v0, (Object) null);
        objectEncoderContext.add(w0, (Object) null);
        objectEncoderContext.add(x0, (Object) null);
        objectEncoderContext.add(y0, (Object) null);
        objectEncoderContext.add(z0, (Object) null);
    }
}