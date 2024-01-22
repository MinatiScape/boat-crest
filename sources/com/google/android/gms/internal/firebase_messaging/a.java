package com.google.android.gms.internal.firebase_messaging;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import java.io.IOException;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes7.dex */
public final class a implements ObjectEncoder<MessagingClientEvent> {

    /* renamed from: a  reason: collision with root package name */
    public static final a f8641a = new a();
    public static final FieldDescriptor b;
    public static final FieldDescriptor c;
    public static final FieldDescriptor d;
    public static final FieldDescriptor e;
    public static final FieldDescriptor f;
    public static final FieldDescriptor g;
    public static final FieldDescriptor h;
    public static final FieldDescriptor i;
    public static final FieldDescriptor j;
    public static final FieldDescriptor k;
    public static final FieldDescriptor l;
    public static final FieldDescriptor m;
    public static final FieldDescriptor n;
    public static final FieldDescriptor o;
    public static final FieldDescriptor p;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("projectNumber");
        zzv zzvVar = new zzv();
        zzvVar.zza(1);
        b = builder.withProperty(zzvVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder(MqttServiceConstants.MESSAGE_ID);
        zzv zzvVar2 = new zzv();
        zzvVar2.zza(2);
        c = builder2.withProperty(zzvVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("instanceId");
        zzv zzvVar3 = new zzv();
        zzvVar3.zza(3);
        d = builder3.withProperty(zzvVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("messageType");
        zzv zzvVar4 = new zzv();
        zzvVar4.zza(4);
        e = builder4.withProperty(zzvVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("sdkPlatform");
        zzv zzvVar5 = new zzv();
        zzvVar5.zza(5);
        f = builder5.withProperty(zzvVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("packageName");
        zzv zzvVar6 = new zzv();
        zzvVar6.zza(6);
        g = builder6.withProperty(zzvVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("collapseKey");
        zzv zzvVar7 = new zzv();
        zzvVar7.zza(7);
        h = builder7.withProperty(zzvVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("priority");
        zzv zzvVar8 = new zzv();
        zzvVar8.zza(8);
        i = builder8.withProperty(zzvVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("ttl");
        zzv zzvVar9 = new zzv();
        zzvVar9.zza(9);
        j = builder9.withProperty(zzvVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("topic");
        zzv zzvVar10 = new zzv();
        zzvVar10.zza(10);
        k = builder10.withProperty(zzvVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("bulkId");
        zzv zzvVar11 = new zzv();
        zzvVar11.zza(11);
        l = builder11.withProperty(zzvVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("event");
        zzv zzvVar12 = new zzv();
        zzvVar12.zza(12);
        m = builder12.withProperty(zzvVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("analyticsLabel");
        zzv zzvVar13 = new zzv();
        zzvVar13.zza(13);
        n = builder13.withProperty(zzvVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("campaignId");
        zzv zzvVar14 = new zzv();
        zzvVar14.zza(14);
        o = builder14.withProperty(zzvVar14.zzb()).build();
        FieldDescriptor.Builder builder15 = FieldDescriptor.builder("composerLabel");
        zzv zzvVar15 = new zzv();
        zzvVar15.zza(15);
        p = builder15.withProperty(zzvVar15.zzb()).build();
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) throws IOException {
        MessagingClientEvent messagingClientEvent = (MessagingClientEvent) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add(b, messagingClientEvent.getProjectNumber());
        objectEncoderContext.add(c, messagingClientEvent.getMessageId());
        objectEncoderContext.add(d, messagingClientEvent.getInstanceId());
        objectEncoderContext.add(e, messagingClientEvent.getMessageType());
        objectEncoderContext.add(f, messagingClientEvent.getSdkPlatform());
        objectEncoderContext.add(g, messagingClientEvent.getPackageName());
        objectEncoderContext.add(h, messagingClientEvent.getCollapseKey());
        objectEncoderContext.add(i, messagingClientEvent.getPriority());
        objectEncoderContext.add(j, messagingClientEvent.getTtl());
        objectEncoderContext.add(k, messagingClientEvent.getTopic());
        objectEncoderContext.add(l, messagingClientEvent.getBulkId());
        objectEncoderContext.add(m, messagingClientEvent.getEvent());
        objectEncoderContext.add(n, messagingClientEvent.getAnalyticsLabel());
        objectEncoderContext.add(o, messagingClientEvent.getCampaignId());
        objectEncoderContext.add(p, messagingClientEvent.getComposerLabel());
    }
}
