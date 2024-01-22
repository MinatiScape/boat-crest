package com.polidea.rxandroidble2.internal.util;

import bleshadow.dagger.internal.DaggerGenerated;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.QualifierMetadata;
import bleshadow.dagger.internal.ScopeMetadata;
@DaggerGenerated
@ScopeMetadata
@QualifierMetadata
/* loaded from: classes12.dex */
public final class ScanRecordParser_Factory implements Factory<ScanRecordParser> {

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final ScanRecordParser_Factory f13524a = new ScanRecordParser_Factory();
    }

    public static ScanRecordParser_Factory create() {
        return a.f13524a;
    }

    public static ScanRecordParser newInstance() {
        return new ScanRecordParser();
    }

    @Override // bleshadow.javax.inject.Provider
    public ScanRecordParser get() {
        return newInstance();
    }
}
