package com.yicj.study.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyKeyList<T> extends ArrayList<T> {
    private Map<String, T> data = new HashMap<>() ;
    public boolean add(String id ,T t) {
        data.put(id, t) ;
        return super.add(t);
    }
    public Map<String,T> getData(){
        return data ;
    }
    public Set<String> getKeys(){
        return data.keySet() ;
    }
}
