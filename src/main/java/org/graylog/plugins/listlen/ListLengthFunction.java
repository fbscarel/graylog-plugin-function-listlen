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

import org.graylog.plugins.pipelineprocessor.EvaluationContext;
import org.graylog.plugins.pipelineprocessor.ast.functions.AbstractFunction;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionArgs;
import org.graylog.plugins.pipelineprocessor.ast.functions.FunctionDescriptor;
import org.graylog.plugins.pipelineprocessor.ast.functions.ParameterDescriptor;

import java.lang.Integer;
import java.util.List;

public class ListLengthFunction extends AbstractFunction<Integer> {

  public static final String NAME = "list_length";
  private static final String LIST_ARG = "list";

  private final ParameterDescriptor<List, List> listParam = ParameterDescriptor
            .type(LIST_ARG, List.class)
            .description("Target list to be processed.")
            .build();

  @Override
  public Integer evaluate(FunctionArgs functionArgs, EvaluationContext evaluationContext) {
    List<?> target = listParam.required(functionArgs, evaluationContext);
    if (target == null) {
      return 0;
    }

    return target.size();
  }

  @Override
  public FunctionDescriptor<Integer> descriptor() {
    return FunctionDescriptor.<Integer>builder()
      .name(NAME)
      .description("Returns the length of a list")
      .params(listParam)
      .returnType(Integer.class)
      .build();
  }
}
