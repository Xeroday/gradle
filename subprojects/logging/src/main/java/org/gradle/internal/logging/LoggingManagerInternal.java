/*
 * Copyright 2016 the original author or authors.
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
package org.gradle.internal.logging;

import org.gradle.api.logging.LogLevel;
import org.gradle.api.logging.LoggingManager;

/**
 * Provides access to and control over the logging system. Log manager represents some 'scope', and log managers can be nested in a stack.
 */
public interface LoggingManagerInternal extends LoggingManager, StandardOutputCapture, LoggingOutputInternal {
    /**
     * Makes this log manager active, replacing the currently active log manager, if any. Applies any settings defined by this log manager. Initialises the logging system when there is no log manager currently active.
     *
     * <p>While a log manager is active, any changes made to the settings will take effect immediately. When a log manager is not active, changes to its settings will apply only once it is made active by calling {@link #start()}.</p>
     */
    LoggingManagerInternal start();

    /**
     * Stops logging, restoring the log manger that was active when {@link #start()} was called on this manager. Shuts down the logging system when there was no log manager active prior to starting this one.
     */
    LoggingManagerInternal stop();

    /**
     * Consumes logging from System.out, System.err and Java util logging.
     */
    LoggingManagerInternal captureSystemSources();

    LoggingManagerInternal captureStandardOutput(LogLevel level);

    LoggingManagerInternal captureStandardError(LogLevel level);

    LoggingManagerInternal setLevelInternal(LogLevel logLevel);
}
