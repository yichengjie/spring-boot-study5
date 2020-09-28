package com.yicj.study.config;

import com.codahale.metrics.MetricRegistry;
import lombok.Data;
import java.util.List;

@Data
public class ListManager  {
    private List<Object> list ;

    public ListManager(MetricRegistry metrics) {
        
    }
}
