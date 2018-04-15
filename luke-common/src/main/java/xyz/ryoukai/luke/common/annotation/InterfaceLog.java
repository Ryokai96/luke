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
package xyz.ryoukai.luke.common.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 打印方法开始和结束时的方法状态日志，用于对外服务接口
 * 格式：{} 方法开始执行,参数{}|======|开始时间：{}
 *      {} 方法执行结束,返回值{}|======|结束时间：{}
 * 日志级别为INFO
 * </p>
 *
 * @author ryoukai
 * @date 2018/4/15 19:02
 * @since V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InterfaceLog {
}
