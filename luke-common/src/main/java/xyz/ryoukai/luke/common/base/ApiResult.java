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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;
import xyz.ryoukai.luke.common.constant.ResultCodeEnum;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 接口返回结果包装类
 * </p> 
 *
 * @author ryoukai
 * @date 2018/4/15 19:19
 * @since V1.0
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -7288352643271247677L;

    /**
     * 调用是否成功
     */
    private boolean success;
    /**
     * 调用结果状态码
     */
    private String code;
    /**
     * 调用结果信息
     */
    private String message;
    /**
     * 调用成功时响应数据
     */
    private T data;

    public static <T> ApiResult<T> success() {
        return success(null);
    }

    public static <T> ApiResult<T> success(T data) {
        return ApiResult.<T>builder()
                .success(true)
                .code(ResultCodeEnum.SUCCESS.getCode())
                .message(ResultCodeEnum.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiResult<T> error() {
        return error(null);
    }

    public static <T> ApiResult<T> error(String message) {
        return error(null, message);
    }

    public static <T> ApiResult<T> error(String code, String message) {
        return ApiResult.<T>builder()
                .success(false)
                .code(StringUtils.isEmpty(code) ? ResultCodeEnum.FAILURE.getCode() : code)
                .message(Objects.isNull(message) ? ResultCodeEnum.FAILURE.getMessage() : message)
                .data(null)
                .build();
    }

    private static <T> ApiResultBuilder<T> builder() {
        return new ApiResultBuilder<>();
    }

    @ToString
    private static class ApiResultBuilder<T> {
        private boolean success;
        private String code;
        private String message;
        private T data;

        private ApiResultBuilder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        private ApiResultBuilder<T> code(String code) {
            this.code = code;
            return this;
        }

        private ApiResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        private ApiResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        private ApiResult<T> build() {
            return new ApiResult<>(success, code, message, data);
        }
    }
}
