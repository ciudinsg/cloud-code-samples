/*
 * Copyright 2023 Google LLC
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

package com.cloudcode.dataflow.example.options;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.api.services.bigquery.model.DatasetReference;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Parses Pipeline option value into a {@link DatasetReference}. */
public class DatasetReferenceOptionValue implements Serializable {

  // For parsing the format used to parse a String into a dataset reference.
  // "{project_id}:{dataset_id}" or
  // "{project_id}.{dataset_id}"
  private static final Pattern DATASET_PATTERN =
      Pattern.compile("^(?<PROJECT>[^\\.:]+)[\\.:](?<DATASET>[^\\.:]+)$");

  private final DatasetReference datasetReference;

  DatasetReferenceOptionValue(String input) {
    Matcher m = DATASET_PATTERN.matcher(input);
    checkArgument(
        m.matches(),
        "input does not match BigQuery dataset pattern, "
            + "expected 'project_id.dataset_id' or 'project_id:dataset_id, got: %s",
        input);
    this.datasetReference =
        new DatasetReference().setProjectId(m.group("PROJECT")).setDatasetId(m.group("DATASET"));
  }

  public DatasetReference getValue() {
    return datasetReference;
  }
}
