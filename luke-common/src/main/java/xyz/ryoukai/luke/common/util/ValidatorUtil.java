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

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *
 * </p> 
 *
 * @author ryoukai
 * @date 2018/4/15 21:53
 * @since V1.0
 */
public class ValidatorUtil {
    private static final Validator VALIDATOR;

    static {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        VALIDATOR = vf.getValidator();
    }

    /**
     * 参数校验，throw IllegalArgumentException
     * @param object
     */
    public static void validate(Object object) {
        if (object != null) {
            List<String> errors = violation(object);
            if (errors.size() > 0) {
                StringBuilder builder = new StringBuilder();
                errors.forEach(msg -> builder.append(msg).append("；"));
                throw new IllegalArgumentException("参数错误："+builder.toString());
            }
        }
    }

    private static List<String> violation(Object object) {
        List<String> msgs = new ArrayList<>();
        Set<ConstraintViolation<Object>> set = VALIDATOR.validate(object);
        if (!CollectionUtils.isEmpty(set)) {
            for (ConstraintViolation<Object> cvo : set) {
                msgs.add(cvo.getMessage());
            }
        }
        return msgs;
    }
}
