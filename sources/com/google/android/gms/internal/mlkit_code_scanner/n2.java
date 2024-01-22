package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;
/* loaded from: classes8.dex */
public final class n2 implements ObjectEncoder {
    public static final FieldDescriptor A;
    public static final FieldDescriptor A0;
    public static final FieldDescriptor B;
    public static final FieldDescriptor B0;
    public static final FieldDescriptor C;
    public static final FieldDescriptor C0;
    public static final FieldDescriptor D;
    public static final FieldDescriptor D0;
    public static final FieldDescriptor E;
    public static final FieldDescriptor E0;
    public static final FieldDescriptor F;
    public static final FieldDescriptor F0;
    public static final FieldDescriptor G;
    public static final FieldDescriptor G0;
    public static final FieldDescriptor H;
    public static final FieldDescriptor H0;
    public static final FieldDescriptor I;
    public static final FieldDescriptor I0;
    public static final FieldDescriptor J;
    public static final FieldDescriptor J0;
    public static final FieldDescriptor K;
    public static final FieldDescriptor K0;
    public static final FieldDescriptor L;
    public static final FieldDescriptor L0;
    public static final FieldDescriptor M;
    public static final FieldDescriptor M0;
    public static final FieldDescriptor N;
    public static final FieldDescriptor N0;
    public static final FieldDescriptor O;
    public static final FieldDescriptor O0;
    public static final FieldDescriptor P;
    public static final FieldDescriptor P0;
    public static final FieldDescriptor Q;
    public static final FieldDescriptor Q0;
    public static final FieldDescriptor R;
    public static final FieldDescriptor R0;
    public static final FieldDescriptor S;
    public static final FieldDescriptor S0;
    public static final FieldDescriptor T;
    public static final FieldDescriptor T0;
    public static final FieldDescriptor U;
    public static final FieldDescriptor U0;
    public static final FieldDescriptor V;
    public static final FieldDescriptor V0;
    public static final FieldDescriptor W;
    public static final FieldDescriptor W0;
    public static final FieldDescriptor X;
    public static final FieldDescriptor X0;
    public static final FieldDescriptor Y;
    public static final FieldDescriptor Y0;
    public static final FieldDescriptor Z;
    public static final FieldDescriptor Z0;

    /* renamed from: a  reason: collision with root package name */
    public static final n2 f9056a = new n2();
    public static final FieldDescriptor a0;
    public static final FieldDescriptor a1;
    public static final FieldDescriptor b;
    public static final FieldDescriptor b0;
    public static final FieldDescriptor b1;
    public static final FieldDescriptor c;
    public static final FieldDescriptor c0;
    public static final FieldDescriptor c1;
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
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        b = builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("eventName");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(2);
        c = builder2.withProperty(zzadVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isThickClient");
        zzad zzadVar3 = new zzad();
        zzadVar3.zza(37);
        d = builder3.withProperty(zzadVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("clientType");
        zzad zzadVar4 = new zzad();
        zzadVar4.zza(61);
        e = builder4.withProperty(zzadVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("modelDownloadLogEvent");
        zzad zzadVar5 = new zzad();
        zzadVar5.zza(3);
        f = builder5.withProperty(zzadVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("customModelLoadLogEvent");
        zzad zzadVar6 = new zzad();
        zzadVar6.zza(20);
        g = builder6.withProperty(zzadVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("customModelInferenceLogEvent");
        zzad zzadVar7 = new zzad();
        zzadVar7.zza(4);
        h = builder7.withProperty(zzadVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("customModelCreateLogEvent");
        zzad zzadVar8 = new zzad();
        zzadVar8.zza(29);
        i = builder8.withProperty(zzadVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("onDeviceFaceDetectionLogEvent");
        zzad zzadVar9 = new zzad();
        zzadVar9.zza(5);
        j = builder9.withProperty(zzadVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("onDeviceFaceLoadLogEvent");
        zzad zzadVar10 = new zzad();
        zzadVar10.zza(59);
        k = builder10.withProperty(zzadVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("onDeviceTextDetectionLogEvent");
        zzad zzadVar11 = new zzad();
        zzadVar11.zza(6);
        l = builder11.withProperty(zzadVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("onDeviceTextDetectionLoadLogEvent");
        zzad zzadVar12 = new zzad();
        zzadVar12.zza(79);
        m = builder12.withProperty(zzadVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("onDeviceBarcodeDetectionLogEvent");
        zzad zzadVar13 = new zzad();
        zzadVar13.zza(7);
        n = builder13.withProperty(zzadVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("onDeviceBarcodeLoadLogEvent");
        zzad zzadVar14 = new zzad();
        zzadVar14.zza(58);
        o = builder14.withProperty(zzadVar14.zzb()).build();
        FieldDescriptor.Builder builder15 = FieldDescriptor.builder("onDeviceImageLabelCreateLogEvent");
        zzad zzadVar15 = new zzad();
        zzadVar15.zza(48);
        p = builder15.withProperty(zzadVar15.zzb()).build();
        FieldDescriptor.Builder builder16 = FieldDescriptor.builder("onDeviceImageLabelLoadLogEvent");
        zzad zzadVar16 = new zzad();
        zzadVar16.zza(49);
        q = builder16.withProperty(zzadVar16.zzb()).build();
        FieldDescriptor.Builder builder17 = FieldDescriptor.builder("onDeviceImageLabelDetectionLogEvent");
        zzad zzadVar17 = new zzad();
        zzadVar17.zza(18);
        r = builder17.withProperty(zzadVar17.zzb()).build();
        FieldDescriptor.Builder builder18 = FieldDescriptor.builder("onDeviceObjectCreateLogEvent");
        zzad zzadVar18 = new zzad();
        zzadVar18.zza(26);
        s = builder18.withProperty(zzadVar18.zzb()).build();
        FieldDescriptor.Builder builder19 = FieldDescriptor.builder("onDeviceObjectLoadLogEvent");
        zzad zzadVar19 = new zzad();
        zzadVar19.zza(27);
        t = builder19.withProperty(zzadVar19.zzb()).build();
        FieldDescriptor.Builder builder20 = FieldDescriptor.builder("onDeviceObjectInferenceLogEvent");
        zzad zzadVar20 = new zzad();
        zzadVar20.zza(28);
        u = builder20.withProperty(zzadVar20.zzb()).build();
        FieldDescriptor.Builder builder21 = FieldDescriptor.builder("onDevicePoseDetectionLogEvent");
        zzad zzadVar21 = new zzad();
        zzadVar21.zza(44);
        v = builder21.withProperty(zzadVar21.zzb()).build();
        FieldDescriptor.Builder builder22 = FieldDescriptor.builder("onDeviceSegmentationLogEvent");
        zzad zzadVar22 = new zzad();
        zzadVar22.zza(45);
        w = builder22.withProperty(zzadVar22.zzb()).build();
        FieldDescriptor.Builder builder23 = FieldDescriptor.builder("onDeviceSmartReplyLogEvent");
        zzad zzadVar23 = new zzad();
        zzadVar23.zza(19);
        x = builder23.withProperty(zzadVar23.zzb()).build();
        FieldDescriptor.Builder builder24 = FieldDescriptor.builder("onDeviceLanguageIdentificationLogEvent");
        zzad zzadVar24 = new zzad();
        zzadVar24.zza(21);
        y = builder24.withProperty(zzadVar24.zzb()).build();
        FieldDescriptor.Builder builder25 = FieldDescriptor.builder("onDeviceTranslationLogEvent");
        zzad zzadVar25 = new zzad();
        zzadVar25.zza(22);
        z = builder25.withProperty(zzadVar25.zzb()).build();
        FieldDescriptor.Builder builder26 = FieldDescriptor.builder("cloudFaceDetectionLogEvent");
        zzad zzadVar26 = new zzad();
        zzadVar26.zza(8);
        A = builder26.withProperty(zzadVar26.zzb()).build();
        FieldDescriptor.Builder builder27 = FieldDescriptor.builder("cloudCropHintDetectionLogEvent");
        zzad zzadVar27 = new zzad();
        zzadVar27.zza(9);
        B = builder27.withProperty(zzadVar27.zzb()).build();
        FieldDescriptor.Builder builder28 = FieldDescriptor.builder("cloudDocumentTextDetectionLogEvent");
        zzad zzadVar28 = new zzad();
        zzadVar28.zza(10);
        C = builder28.withProperty(zzadVar28.zzb()).build();
        FieldDescriptor.Builder builder29 = FieldDescriptor.builder("cloudImagePropertiesDetectionLogEvent");
        zzad zzadVar29 = new zzad();
        zzadVar29.zza(11);
        D = builder29.withProperty(zzadVar29.zzb()).build();
        FieldDescriptor.Builder builder30 = FieldDescriptor.builder("cloudImageLabelDetectionLogEvent");
        zzad zzadVar30 = new zzad();
        zzadVar30.zza(12);
        E = builder30.withProperty(zzadVar30.zzb()).build();
        FieldDescriptor.Builder builder31 = FieldDescriptor.builder("cloudLandmarkDetectionLogEvent");
        zzad zzadVar31 = new zzad();
        zzadVar31.zza(13);
        F = builder31.withProperty(zzadVar31.zzb()).build();
        FieldDescriptor.Builder builder32 = FieldDescriptor.builder("cloudLogoDetectionLogEvent");
        zzad zzadVar32 = new zzad();
        zzadVar32.zza(14);
        G = builder32.withProperty(zzadVar32.zzb()).build();
        FieldDescriptor.Builder builder33 = FieldDescriptor.builder("cloudSafeSearchDetectionLogEvent");
        zzad zzadVar33 = new zzad();
        zzadVar33.zza(15);
        H = builder33.withProperty(zzadVar33.zzb()).build();
        FieldDescriptor.Builder builder34 = FieldDescriptor.builder("cloudTextDetectionLogEvent");
        zzad zzadVar34 = new zzad();
        zzadVar34.zza(16);
        I = builder34.withProperty(zzadVar34.zzb()).build();
        FieldDescriptor.Builder builder35 = FieldDescriptor.builder("cloudWebSearchDetectionLogEvent");
        zzad zzadVar35 = new zzad();
        zzadVar35.zza(17);
        J = builder35.withProperty(zzadVar35.zzb()).build();
        FieldDescriptor.Builder builder36 = FieldDescriptor.builder("automlImageLabelingCreateLogEvent");
        zzad zzadVar36 = new zzad();
        zzadVar36.zza(23);
        K = builder36.withProperty(zzadVar36.zzb()).build();
        FieldDescriptor.Builder builder37 = FieldDescriptor.builder("automlImageLabelingLoadLogEvent");
        zzad zzadVar37 = new zzad();
        zzadVar37.zza(24);
        L = builder37.withProperty(zzadVar37.zzb()).build();
        FieldDescriptor.Builder builder38 = FieldDescriptor.builder("automlImageLabelingInferenceLogEvent");
        zzad zzadVar38 = new zzad();
        zzadVar38.zza(25);
        M = builder38.withProperty(zzadVar38.zzb()).build();
        FieldDescriptor.Builder builder39 = FieldDescriptor.builder("isModelDownloadedLogEvent");
        zzad zzadVar39 = new zzad();
        zzadVar39.zza(39);
        N = builder39.withProperty(zzadVar39.zzb()).build();
        FieldDescriptor.Builder builder40 = FieldDescriptor.builder("deleteModelLogEvent");
        zzad zzadVar40 = new zzad();
        zzadVar40.zza(40);
        O = builder40.withProperty(zzadVar40.zzb()).build();
        FieldDescriptor.Builder builder41 = FieldDescriptor.builder("aggregatedAutomlImageLabelingInferenceLogEvent");
        zzad zzadVar41 = new zzad();
        zzadVar41.zza(30);
        P = builder41.withProperty(zzadVar41.zzb()).build();
        FieldDescriptor.Builder builder42 = FieldDescriptor.builder("aggregatedCustomModelInferenceLogEvent");
        zzad zzadVar42 = new zzad();
        zzadVar42.zza(31);
        Q = builder42.withProperty(zzadVar42.zzb()).build();
        FieldDescriptor.Builder builder43 = FieldDescriptor.builder("aggregatedOnDeviceFaceDetectionLogEvent");
        zzad zzadVar43 = new zzad();
        zzadVar43.zza(32);
        R = builder43.withProperty(zzadVar43.zzb()).build();
        FieldDescriptor.Builder builder44 = FieldDescriptor.builder("aggregatedOnDeviceBarcodeDetectionLogEvent");
        zzad zzadVar44 = new zzad();
        zzadVar44.zza(33);
        S = builder44.withProperty(zzadVar44.zzb()).build();
        FieldDescriptor.Builder builder45 = FieldDescriptor.builder("aggregatedOnDeviceImageLabelDetectionLogEvent");
        zzad zzadVar45 = new zzad();
        zzadVar45.zza(34);
        T = builder45.withProperty(zzadVar45.zzb()).build();
        FieldDescriptor.Builder builder46 = FieldDescriptor.builder("aggregatedOnDeviceObjectInferenceLogEvent");
        zzad zzadVar46 = new zzad();
        zzadVar46.zza(35);
        U = builder46.withProperty(zzadVar46.zzb()).build();
        FieldDescriptor.Builder builder47 = FieldDescriptor.builder("aggregatedOnDeviceTextDetectionLogEvent");
        zzad zzadVar47 = new zzad();
        zzadVar47.zza(36);
        V = builder47.withProperty(zzadVar47.zzb()).build();
        FieldDescriptor.Builder builder48 = FieldDescriptor.builder("aggregatedOnDevicePoseDetectionLogEvent");
        zzad zzadVar48 = new zzad();
        zzadVar48.zza(46);
        W = builder48.withProperty(zzadVar48.zzb()).build();
        FieldDescriptor.Builder builder49 = FieldDescriptor.builder("aggregatedOnDeviceSegmentationLogEvent");
        zzad zzadVar49 = new zzad();
        zzadVar49.zza(47);
        X = builder49.withProperty(zzadVar49.zzb()).build();
        FieldDescriptor.Builder builder50 = FieldDescriptor.builder("pipelineAccelerationInferenceEvents");
        zzad zzadVar50 = new zzad();
        zzadVar50.zza(69);
        Y = builder50.withProperty(zzadVar50.zzb()).build();
        FieldDescriptor.Builder builder51 = FieldDescriptor.builder("remoteConfigLogEvent");
        zzad zzadVar51 = new zzad();
        zzadVar51.zza(42);
        Z = builder51.withProperty(zzadVar51.zzb()).build();
        FieldDescriptor.Builder builder52 = FieldDescriptor.builder("inputImageConstructionLogEvent");
        zzad zzadVar52 = new zzad();
        zzadVar52.zza(50);
        a0 = builder52.withProperty(zzadVar52.zzb()).build();
        FieldDescriptor.Builder builder53 = FieldDescriptor.builder("leakedHandleEvent");
        zzad zzadVar53 = new zzad();
        zzadVar53.zza(51);
        b0 = builder53.withProperty(zzadVar53.zzb()).build();
        FieldDescriptor.Builder builder54 = FieldDescriptor.builder("cameraSourceLogEvent");
        zzad zzadVar54 = new zzad();
        zzadVar54.zza(52);
        c0 = builder54.withProperty(zzadVar54.zzb()).build();
        FieldDescriptor.Builder builder55 = FieldDescriptor.builder("imageLabelOptionalModuleLogEvent");
        zzad zzadVar55 = new zzad();
        zzadVar55.zza(53);
        d0 = builder55.withProperty(zzadVar55.zzb()).build();
        FieldDescriptor.Builder builder56 = FieldDescriptor.builder("languageIdentificationOptionalModuleLogEvent");
        zzad zzadVar56 = new zzad();
        zzadVar56.zza(54);
        e0 = builder56.withProperty(zzadVar56.zzb()).build();
        FieldDescriptor.Builder builder57 = FieldDescriptor.builder("faceDetectionOptionalModuleLogEvent");
        zzad zzadVar57 = new zzad();
        zzadVar57.zza(60);
        f0 = builder57.withProperty(zzadVar57.zzb()).build();
        FieldDescriptor.Builder builder58 = FieldDescriptor.builder("documentDetectionOptionalModuleLogEvent");
        zzad zzadVar58 = new zzad();
        zzadVar58.zza(85);
        g0 = builder58.withProperty(zzadVar58.zzb()).build();
        FieldDescriptor.Builder builder59 = FieldDescriptor.builder("documentCroppingOptionalModuleLogEvent");
        zzad zzadVar59 = new zzad();
        zzadVar59.zza(86);
        h0 = builder59.withProperty(zzadVar59.zzb()).build();
        FieldDescriptor.Builder builder60 = FieldDescriptor.builder("documentEnhancementOptionalModuleLogEvent");
        zzad zzadVar60 = new zzad();
        zzadVar60.zza(87);
        i0 = builder60.withProperty(zzadVar60.zzb()).build();
        FieldDescriptor.Builder builder61 = FieldDescriptor.builder("nlClassifierOptionalModuleLogEvent");
        zzad zzadVar61 = new zzad();
        zzadVar61.zza(55);
        j0 = builder61.withProperty(zzadVar61.zzb()).build();
        FieldDescriptor.Builder builder62 = FieldDescriptor.builder("nlClassifierClientLibraryLogEvent");
        zzad zzadVar62 = new zzad();
        zzadVar62.zza(56);
        k0 = builder62.withProperty(zzadVar62.zzb()).build();
        FieldDescriptor.Builder builder63 = FieldDescriptor.builder("accelerationAllowlistLogEvent");
        zzad zzadVar63 = new zzad();
        zzadVar63.zza(57);
        l0 = builder63.withProperty(zzadVar63.zzb()).build();
        FieldDescriptor.Builder builder64 = FieldDescriptor.builder("toxicityDetectionCreateEvent");
        zzad zzadVar64 = new zzad();
        zzadVar64.zza(62);
        m0 = builder64.withProperty(zzadVar64.zzb()).build();
        FieldDescriptor.Builder builder65 = FieldDescriptor.builder("toxicityDetectionLoadEvent");
        zzad zzadVar65 = new zzad();
        zzadVar65.zza(63);
        n0 = builder65.withProperty(zzadVar65.zzb()).build();
        FieldDescriptor.Builder builder66 = FieldDescriptor.builder("toxicityDetectionInferenceEvent");
        zzad zzadVar66 = new zzad();
        zzadVar66.zza(64);
        o0 = builder66.withProperty(zzadVar66.zzb()).build();
        FieldDescriptor.Builder builder67 = FieldDescriptor.builder("barcodeDetectionOptionalModuleLogEvent");
        zzad zzadVar67 = new zzad();
        zzadVar67.zza(65);
        p0 = builder67.withProperty(zzadVar67.zzb()).build();
        FieldDescriptor.Builder builder68 = FieldDescriptor.builder("customImageLabelOptionalModuleLogEvent");
        zzad zzadVar68 = new zzad();
        zzadVar68.zza(66);
        q0 = builder68.withProperty(zzadVar68.zzb()).build();
        FieldDescriptor.Builder builder69 = FieldDescriptor.builder("codeScannerScanApiEvent");
        zzad zzadVar69 = new zzad();
        zzadVar69.zza(67);
        r0 = builder69.withProperty(zzadVar69.zzb()).build();
        FieldDescriptor.Builder builder70 = FieldDescriptor.builder("codeScannerOptionalModuleEvent");
        zzad zzadVar70 = new zzad();
        zzadVar70.zza(68);
        s0 = builder70.withProperty(zzadVar70.zzb()).build();
        FieldDescriptor.Builder builder71 = FieldDescriptor.builder("onDeviceExplicitContentCreateLogEvent");
        zzad zzadVar71 = new zzad();
        zzadVar71.zza(70);
        t0 = builder71.withProperty(zzadVar71.zzb()).build();
        FieldDescriptor.Builder builder72 = FieldDescriptor.builder("onDeviceExplicitContentLoadLogEvent");
        zzad zzadVar72 = new zzad();
        zzadVar72.zza(71);
        u0 = builder72.withProperty(zzadVar72.zzb()).build();
        FieldDescriptor.Builder builder73 = FieldDescriptor.builder("onDeviceExplicitContentInferenceLogEvent");
        zzad zzadVar73 = new zzad();
        zzadVar73.zza(72);
        v0 = builder73.withProperty(zzadVar73.zzb()).build();
        FieldDescriptor.Builder builder74 = FieldDescriptor.builder("aggregatedOnDeviceExplicitContentLogEvent");
        zzad zzadVar74 = new zzad();
        zzadVar74.zza(73);
        w0 = builder74.withProperty(zzadVar74.zzb()).build();
        FieldDescriptor.Builder builder75 = FieldDescriptor.builder("onDeviceFaceMeshCreateLogEvent");
        zzad zzadVar75 = new zzad();
        zzadVar75.zza(74);
        x0 = builder75.withProperty(zzadVar75.zzb()).build();
        FieldDescriptor.Builder builder76 = FieldDescriptor.builder("onDeviceFaceMeshLoadLogEvent");
        zzad zzadVar76 = new zzad();
        zzadVar76.zza(75);
        y0 = builder76.withProperty(zzadVar76.zzb()).build();
        FieldDescriptor.Builder builder77 = FieldDescriptor.builder("onDeviceFaceMeshLogEvent");
        zzad zzadVar77 = new zzad();
        zzadVar77.zza(76);
        z0 = builder77.withProperty(zzadVar77.zzb()).build();
        FieldDescriptor.Builder builder78 = FieldDescriptor.builder("aggregatedOnDeviceFaceMeshLogEvent");
        zzad zzadVar78 = new zzad();
        zzadVar78.zza(77);
        A0 = builder78.withProperty(zzadVar78.zzb()).build();
        FieldDescriptor.Builder builder79 = FieldDescriptor.builder("smartReplyOptionalModuleLogEvent");
        zzad zzadVar79 = new zzad();
        zzadVar79.zza(78);
        B0 = builder79.withProperty(zzadVar79.zzb()).build();
        FieldDescriptor.Builder builder80 = FieldDescriptor.builder("textDetectionOptionalModuleLogEvent");
        zzad zzadVar80 = new zzad();
        zzadVar80.zza(80);
        C0 = builder80.withProperty(zzadVar80.zzb()).build();
        FieldDescriptor.Builder builder81 = FieldDescriptor.builder("onDeviceImageQualityAnalysisCreateLogEvent");
        zzad zzadVar81 = new zzad();
        zzadVar81.zza(81);
        D0 = builder81.withProperty(zzadVar81.zzb()).build();
        FieldDescriptor.Builder builder82 = FieldDescriptor.builder("onDeviceImageQualityAnalysisLoadLogEvent");
        zzad zzadVar82 = new zzad();
        zzadVar82.zza(82);
        E0 = builder82.withProperty(zzadVar82.zzb()).build();
        FieldDescriptor.Builder builder83 = FieldDescriptor.builder("onDeviceImageQualityAnalysisLogEvent");
        zzad zzadVar83 = new zzad();
        zzadVar83.zza(83);
        F0 = builder83.withProperty(zzadVar83.zzb()).build();
        FieldDescriptor.Builder builder84 = FieldDescriptor.builder("aggregatedOnDeviceImageQualityAnalysisLogEvent");
        zzad zzadVar84 = new zzad();
        zzadVar84.zza(84);
        G0 = builder84.withProperty(zzadVar84.zzb()).build();
        FieldDescriptor.Builder builder85 = FieldDescriptor.builder("imageQualityAnalysisOptionalModuleLogEvent");
        zzad zzadVar85 = new zzad();
        zzadVar85.zza(88);
        H0 = builder85.withProperty(zzadVar85.zzb()).build();
        FieldDescriptor.Builder builder86 = FieldDescriptor.builder("imageCaptioningOptionalModuleLogEvent");
        zzad zzadVar86 = new zzad();
        zzadVar86.zza(89);
        I0 = builder86.withProperty(zzadVar86.zzb()).build();
        FieldDescriptor.Builder builder87 = FieldDescriptor.builder("onDeviceImageCaptioningCreateLogEvent");
        zzad zzadVar87 = new zzad();
        zzadVar87.zza(90);
        J0 = builder87.withProperty(zzadVar87.zzb()).build();
        FieldDescriptor.Builder builder88 = FieldDescriptor.builder("onDeviceImageCaptioningLoadLogEvent");
        zzad zzadVar88 = new zzad();
        zzadVar88.zza(91);
        K0 = builder88.withProperty(zzadVar88.zzb()).build();
        FieldDescriptor.Builder builder89 = FieldDescriptor.builder("onDeviceImageCaptioningInferenceLogEvent");
        zzad zzadVar89 = new zzad();
        zzadVar89.zza(92);
        L0 = builder89.withProperty(zzadVar89.zzb()).build();
        FieldDescriptor.Builder builder90 = FieldDescriptor.builder("aggregatedOnDeviceImageCaptioningInferenceLogEvent");
        zzad zzadVar90 = new zzad();
        zzadVar90.zza(93);
        M0 = builder90.withProperty(zzadVar90.zzb()).build();
        FieldDescriptor.Builder builder91 = FieldDescriptor.builder("onDeviceDocumentDetectionCreateLogEvent");
        zzad zzadVar91 = new zzad();
        zzadVar91.zza(94);
        N0 = builder91.withProperty(zzadVar91.zzb()).build();
        FieldDescriptor.Builder builder92 = FieldDescriptor.builder("onDeviceDocumentDetectionLoadLogEvent");
        zzad zzadVar92 = new zzad();
        zzadVar92.zza(95);
        O0 = builder92.withProperty(zzadVar92.zzb()).build();
        FieldDescriptor.Builder builder93 = FieldDescriptor.builder("onDeviceDocumentDetectionLogEvent");
        zzad zzadVar93 = new zzad();
        zzadVar93.zza(96);
        P0 = builder93.withProperty(zzadVar93.zzb()).build();
        FieldDescriptor.Builder builder94 = FieldDescriptor.builder("aggregatedOnDeviceDocumentDetectionLogEvent");
        zzad zzadVar94 = new zzad();
        zzadVar94.zza(97);
        Q0 = builder94.withProperty(zzadVar94.zzb()).build();
        FieldDescriptor.Builder builder95 = FieldDescriptor.builder("onDeviceDocumentCroppingCreateLogEvent");
        zzad zzadVar95 = new zzad();
        zzadVar95.zza(98);
        R0 = builder95.withProperty(zzadVar95.zzb()).build();
        FieldDescriptor.Builder builder96 = FieldDescriptor.builder("onDeviceDocumentCroppingLoadLogEvent");
        zzad zzadVar96 = new zzad();
        zzadVar96.zza(99);
        S0 = builder96.withProperty(zzadVar96.zzb()).build();
        FieldDescriptor.Builder builder97 = FieldDescriptor.builder("onDeviceDocumentCroppingLogEvent");
        zzad zzadVar97 = new zzad();
        zzadVar97.zza(100);
        T0 = builder97.withProperty(zzadVar97.zzb()).build();
        FieldDescriptor.Builder builder98 = FieldDescriptor.builder("aggregatedOnDeviceDocumentCroppingLogEvent");
        zzad zzadVar98 = new zzad();
        zzadVar98.zza(101);
        U0 = builder98.withProperty(zzadVar98.zzb()).build();
        FieldDescriptor.Builder builder99 = FieldDescriptor.builder("onDeviceDocumentEnhancementCreateLogEvent");
        zzad zzadVar99 = new zzad();
        zzadVar99.zza(102);
        V0 = builder99.withProperty(zzadVar99.zzb()).build();
        FieldDescriptor.Builder builder100 = FieldDescriptor.builder("onDeviceDocumentEnhancementLoadLogEvent");
        zzad zzadVar100 = new zzad();
        zzadVar100.zza(103);
        W0 = builder100.withProperty(zzadVar100.zzb()).build();
        FieldDescriptor.Builder builder101 = FieldDescriptor.builder("onDeviceDocumentEnhancementLogEvent");
        zzad zzadVar101 = new zzad();
        zzadVar101.zza(104);
        X0 = builder101.withProperty(zzadVar101.zzb()).build();
        FieldDescriptor.Builder builder102 = FieldDescriptor.builder("aggregatedOnDeviceDocumentEnhancementLogEvent");
        zzad zzadVar102 = new zzad();
        zzadVar102.zza(105);
        Y0 = builder102.withProperty(zzadVar102.zzb()).build();
        FieldDescriptor.Builder builder103 = FieldDescriptor.builder("scannerAutoZoomEvent");
        zzad zzadVar103 = new zzad();
        zzadVar103.zza(106);
        Z0 = builder103.withProperty(zzadVar103.zzb()).build();
        FieldDescriptor.Builder builder104 = FieldDescriptor.builder("lowLightAutoExposureComputationEvent");
        zzad zzadVar104 = new zzad();
        zzadVar104.zza(107);
        a1 = builder104.withProperty(zzadVar104.zzb()).build();
        FieldDescriptor.Builder builder105 = FieldDescriptor.builder("lowLightFrameProcessEvent");
        zzad zzadVar105 = new zzad();
        zzadVar105.zza(108);
        b1 = builder105.withProperty(zzadVar105.zzb()).build();
        FieldDescriptor.Builder builder106 = FieldDescriptor.builder("lowLightSceneDetectionEvent");
        zzad zzadVar106 = new zzad();
        zzadVar106.zza(109);
        c1 = builder106.withProperty(zzadVar106.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        zzke zzkeVar = (zzke) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, zzkeVar.zzc());
        objectEncoderContext.add(c, zzkeVar.zzb());
        objectEncoderContext.add(d, (Object) null);
        objectEncoderContext.add(e, (Object) null);
        objectEncoderContext.add(f, (Object) null);
        objectEncoderContext.add(g, (Object) null);
        objectEncoderContext.add(h, (Object) null);
        objectEncoderContext.add(i, (Object) null);
        objectEncoderContext.add(j, (Object) null);
        objectEncoderContext.add(k, (Object) null);
        objectEncoderContext.add(l, (Object) null);
        objectEncoderContext.add(m, (Object) null);
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
        objectEncoderContext.add(V, (Object) null);
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
        objectEncoderContext.add(r0, zzkeVar.zza());
        objectEncoderContext.add(s0, (Object) null);
        objectEncoderContext.add(t0, (Object) null);
        objectEncoderContext.add(u0, (Object) null);
        objectEncoderContext.add(v0, (Object) null);
        objectEncoderContext.add(w0, (Object) null);
        objectEncoderContext.add(x0, (Object) null);
        objectEncoderContext.add(y0, (Object) null);
        objectEncoderContext.add(z0, (Object) null);
        objectEncoderContext.add(A0, (Object) null);
        objectEncoderContext.add(B0, (Object) null);
        objectEncoderContext.add(C0, (Object) null);
        objectEncoderContext.add(D0, (Object) null);
        objectEncoderContext.add(E0, (Object) null);
        objectEncoderContext.add(F0, (Object) null);
        objectEncoderContext.add(G0, (Object) null);
        objectEncoderContext.add(H0, (Object) null);
        objectEncoderContext.add(I0, (Object) null);
        objectEncoderContext.add(J0, (Object) null);
        objectEncoderContext.add(K0, (Object) null);
        objectEncoderContext.add(L0, (Object) null);
        objectEncoderContext.add(M0, (Object) null);
        objectEncoderContext.add(N0, (Object) null);
        objectEncoderContext.add(O0, (Object) null);
        objectEncoderContext.add(P0, (Object) null);
        objectEncoderContext.add(Q0, (Object) null);
        objectEncoderContext.add(R0, (Object) null);
        objectEncoderContext.add(S0, (Object) null);
        objectEncoderContext.add(T0, (Object) null);
        objectEncoderContext.add(U0, (Object) null);
        objectEncoderContext.add(V0, (Object) null);
        objectEncoderContext.add(W0, (Object) null);
        objectEncoderContext.add(X0, (Object) null);
        objectEncoderContext.add(Y0, (Object) null);
        objectEncoderContext.add(Z0, (Object) null);
        objectEncoderContext.add(a1, (Object) null);
        objectEncoderContext.add(b1, (Object) null);
        objectEncoderContext.add(c1, (Object) null);
    }
}
