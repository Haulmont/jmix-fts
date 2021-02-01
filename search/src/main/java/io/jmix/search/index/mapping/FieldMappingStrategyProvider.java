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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("search_FieldMappingStrategyProvider")
public class FieldMappingStrategyProvider {

    protected final Map<Class<? extends FieldMappingStrategy>, FieldMappingStrategy> registry;

    @Autowired
    public FieldMappingStrategyProvider(List<FieldMappingStrategy> fieldMappingStrategies) {
        registry = fieldMappingStrategies.stream().collect(Collectors.toMap(FieldMappingStrategy::getClass, Function.identity()));
    }

    public FieldMappingStrategy getFieldMappingStrategyByClass(Class<? extends FieldMappingStrategy> strategyClass) {
        return registry.get(strategyClass);
    }
}