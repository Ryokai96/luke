/*
 * Copyright 2018 ryoukai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.ryoukai.luke.common.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * JavaBean拷贝工具类
 * </p> 
 *
 * @author ryoukai
 * @date 2018/4/15 21:25
 * @since V1.0
 */
public class BeanCopierUtil {
    /**
     * beanCopier缓存
     * (A拷贝到B,确定一个beanCopier)
     */
    private static Map<Class<?>, Map<Class<?>, BeanCopier>> beanCopierMap = new ConcurrentHashMap<>();

    /**
     * 拷贝方法
     * @param sourceBean
     * @param targetBean
     * @param <S>
     * @param <T>
     */
    public static <S, T> void copy(S sourceBean, T targetBean) {
        @SuppressWarnings("unchecked")
        Class<S> sourceClass = (Class<S>) sourceBean.getClass();
        @SuppressWarnings("unchecked")
        Class<T> targetClass = (Class<T>) targetBean.getClass();

        BeanCopier beanCopier = getBeanCopier(sourceClass, targetClass);
        beanCopier.copy(sourceBean, targetBean, null);
    }

    /**
     * 转换方法
     * @param sourceBean 原对象
     * @param targetClass 目标类
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T convert(S sourceBean, Class<T> targetClass) {
        try {
            assert sourceBean != null;
            T targetBean = targetClass.newInstance();
            copy(sourceBean, targetBean);
            return targetBean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <S, T> BeanCopier getBeanCopier(Class<S> sourceClass, Class<T> targetClass) {
        Map<Class<?>, BeanCopier> map = beanCopierMap.get(sourceClass);
        if (CollectionUtils.isEmpty(map)) {
            BeanCopier newBeanCopier = BeanCopier.create(sourceClass, targetClass, false);
            Map<Class<?>, BeanCopier> newMap = new ConcurrentHashMap<>();
            newMap.put(targetClass, newBeanCopier);
            beanCopierMap.put(sourceClass, newMap);
            return newBeanCopier;
        }

        BeanCopier beanCopier = map.get(targetClass);
        if (beanCopier == null) {
            BeanCopier newBeanCopier = BeanCopier.create(sourceClass, targetClass, false);
            map.put(targetClass, newBeanCopier);

            return newBeanCopier;
        }

        return beanCopier;
    }
}
