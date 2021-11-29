/*
 * Copyright (C) 2020 Graylog, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */
package org.graylog.plugins.listlen;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import org.graylog2.plugin.PluginConfigBean;
import org.graylog2.plugin.PluginModule;
import org.graylog.plugins.pipelineprocessor.ast.functions.Function;
import java.util.Collections;
import java.util.Set;

public class ListLengthFunctionModule extends PluginModule {
  @Override
  public Set<? extends PluginConfigBean> getConfigBeans() {
    return Collections.emptySet();
  }

  @Override
  protected void configure() {
    addMessageProcessorFunction(ListLengthFunction.NAME, ListLengthFunction.class);
  }

  protected void addMessageProcessorFunction(String name, Class<? extends Function<?>> functionClass) {
    addMessageProcessorFunction(binder(), name, functionClass);
  }

  public static MapBinder<String, Function<?>> processorFunctionBinder(Binder binder) {
    return MapBinder.newMapBinder(binder, TypeLiteral.get(String.class), new TypeLiteral<Function<?>>() {});
  }

  public static void addMessageProcessorFunction(Binder binder, String name, Class<? extends Function<?>> functionClass) {
    processorFunctionBinder(binder).addBinding(name).to(functionClass);
  }
}
