/********************************************************************************
 * Copyright (c) 2023 T-Systems International GmbH
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ********************************************************************************/

package org.connector.e2etestservice.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import org.connector.e2etestservice.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class EdcTemplateFactory {

    @SneakyThrows
    public ObjectNode generateDummyEdcRequestObject(String templatePath) {
        String readValueAsTree = getSchemaFromFile(templatePath);
        String jsonString = String.format(readValueAsTree);

        return (ObjectNode) new ObjectMapper().readTree(jsonString);
    }

    @SneakyThrows
    public ObjectNode generateDynamicDummyEdcRequestObject(String templatePath,
                                                           String[] dynamicVariable) {
        String readValueAsTree = getSchemaFromFile(templatePath);
        String jsonString = String.format(readValueAsTree, (Object[])dynamicVariable);
        return (ObjectNode) new ObjectMapper().readTree(jsonString);
    }

    @SneakyThrows
    private String getSchemaFromFile(String schemaFile) {
        JsonParser createParser = null;
        String schema = null;
        try {
            MappingJsonFactory jf = new MappingJsonFactory();
            InputStream jsonFile = this.getClass().getResourceAsStream(schemaFile);

            if (jsonFile == null) {
                // this is how we load file within editor (eg eclipse)
                jsonFile = this.getClass().getClassLoader().getResourceAsStream(schemaFile);
            }
            createParser = jf.createParser(jsonFile);
            schema = createParser.readValueAsTree().toString();
            if (schema == null) {
                throw new ServiceException("The schema for EDC request template is null " + schemaFile);
            }

            return schema;
        } finally {
            if (createParser != null)
                createParser.close();
        }
    }
}
