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

import java.io.Serializable;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubClient;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubClient.SubscriptionPath;

public class SubscriptionPathOptionValue implements Serializable {
  private final SubscriptionPath subscriptionPath;

  public SubscriptionPathOptionValue(String input) {
    SubscriptionPath parsedResult = null;
    try {
      parsedResult = PubsubClient.subscriptionPathFromPath(input);
    } catch (IllegalStateException e) {
      throw new IllegalArgumentException(
          String.format(
              "error parsing '%s' into %s: %s", input, SubscriptionPath.class, e.getMessage()));
    }
    this.subscriptionPath = parsedResult;
  }

  public SubscriptionPath getValue() {
    return subscriptionPath;
  }
}
