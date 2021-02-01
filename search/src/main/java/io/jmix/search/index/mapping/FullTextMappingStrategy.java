/*
 * Copyright 2020 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jmix.search.index.mapping;

import io.jmix.core.metamodel.model.MetaPropertyPath;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("search_FullTextMappingStrategy")
public class FullTextMappingStrategy extends EntityPropertyMappingStrategy {

    public FullTextMappingStrategy(AutoMapFieldMapperResolver autoMapFieldMapperResolver) {
        super(autoMapFieldMapperResolver);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public boolean isSupported(MetaPropertyPath propertyPath) {
        if(propertyPath.getRange().isDatatype()) {
            return String.class.equals(propertyPath.getRange().asDatatype().getJavaClass());
        }
        return false;
    }

    @Override
    public FieldConfiguration createFieldConfiguration(MetaPropertyPath propertyPath, Map<String, Object> parameters) {
        return new NativeFieldConfiguration(new TextMapper().createJsonConfiguration(parameters));
    }

    @Override
    public ValueMapper getValueMapper(MetaPropertyPath propertyPath) {
        return null; //todo
    }
}