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
package xyz.ryoukai.luke.common.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * DTO 对象基类
 * </p> 
 *
 * @author ryoukai
 * @date 2018/4/15 20:42
 * @since V1.0
 */
@Getter
@Setter
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 8293419037777157455L;

    private Integer version;
}
