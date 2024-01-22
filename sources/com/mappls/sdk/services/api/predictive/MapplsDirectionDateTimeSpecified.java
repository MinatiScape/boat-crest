package com.mappls.sdk.services.api.predictive;

import java.text.SimpleDateFormat;
import java.util.Date;
/* loaded from: classes7.dex */
public class MapplsDirectionDateTimeSpecified implements MapplsDirectionDateTime {

    /* renamed from: a  reason: collision with root package name */
    public Integer f13254a;
    public Long b;

    public MapplsDirectionDateTimeSpecified(Integer num, Long l) {
        this.f13254a = num;
        this.b = l;
    }

    @Override // com.mappls.sdk.services.api.predictive.MapplsDirectionDateTime
    public String dateTime() {
        return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").format(new Date(this.b.longValue()));
    }

    @Override // com.mappls.sdk.services.api.predictive.MapplsDirectionDateTime
    public Integer type() {
        return this.f13254a;
    }
}
