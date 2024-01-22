package com.coveiot.android.leonardo.sensai.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes5.dex */
public class SensAIFilter implements Serializable {
    private List<SensAIFilterOptions> filters;
    private String name;

    public SensAIFilter(String str, List<SensAIFilterOptions> list) {
        this.name = str;
        this.filters = list;
    }

    public List<SensAIFilterOptions> getFilters() {
        return this.filters;
    }

    public String getName() {
        return this.name;
    }

    public void setFilters(List<SensAIFilterOptions> list) {
        this.filters = list;
    }

    public void setName(String str) {
        this.name = str;
    }
}
