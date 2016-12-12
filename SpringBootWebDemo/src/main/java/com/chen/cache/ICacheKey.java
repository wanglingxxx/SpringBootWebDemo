package com.chen.cache;

/**
 * Created by Adm on 2016/12/12.
 */
public interface ICacheKey<T> {
    public String getCacheKey(T t);
}
