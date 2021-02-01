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

package io.jmix.search;

import io.jmix.core.CoreConfiguration;
import io.jmix.core.annotation.JmixModule;
import io.jmix.data.DataConfiguration;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@ConfigurationPropertiesScan
@JmixModule(dependsOn = {CoreConfiguration.class, DataConfiguration.class})
@EnableTransactionManagement
@EnableScheduling
public class SearchConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SearchConfiguration.class);

    @Value("${jmix.search.elasticsearch.host}")
    protected String elasticsearchHost;
    @Value("${jmix.search.elasticsearch.port}")
    protected int elasticsearchPort;

    @Bean("search_RestHighLevelClient")
    public RestHighLevelClient elasticSearchClient() { //todo credentials
        log.info("[IVGA] Build ES Client for '{}:{}'", elasticsearchHost, elasticsearchPort);
        return new RestHighLevelClient(RestClient.builder(new HttpHost(elasticsearchHost, elasticsearchPort)));
    }
}