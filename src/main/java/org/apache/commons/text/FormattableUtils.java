/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.text;

import java.util.Formattable;
import java.util.Formatter;

/**
 * Provides utilities for working with the {@code Formattable} interface.
 *
 * <p>The {@link Formattable} interface provides basic control over formatting
 * when using a {@code Formatter}. It is primarily concerned with numeric precision
 * and padding, and is not designed to allow generalized alternate formats.</p>
 *
 * @since 1.0
 */
public class FormattableUtils {

    /**
     * A format that simply outputs the value as a string.
     */
    private static final String SIMPLEST_FORMAT = "%s";

    /**
     * Handles the common {@link Formattable} operations of truncate-pad-append,
     * with no ellipsis on precision overflow, and padding width underflow with spaces.
     *
     * @param seq  the string to handle, not null
     * @param formatter  the destination formatter, not null
     * @param flags  the flags for formatting, see {@code Formattable}
     * @param width  the width of the output, see {@code Formattable}
     * @param precision  the precision of the output, see {@code Formattable}
     * @return The {@code formatter} instance, not null
     */
    public static Formatter append(final CharSequence seq, final Formatter formatter, final int flags, final int width,
                                   final int precision) {
        return append(seq, formatter, flags, width, precision, ' ', null);
    }

    /**
     * Handles the common {@link Formattable} operations of truncate-pad-append,
     * with no ellipsis on precision overflow.
     *
     * @param seq  the string to handle, not null
     * @param formatter  the destination formatter, not null
     * @param flags  the flags for formatting, see {@code Formattable}
     * @param width  the width of the output, see {@code Formattable}
     * @param precision  the precision of the output, see {@code Formattable}
     * @param padChar  the pad character to use
     * @return The {@code formatter} instance, not null
     */
    public static Formatter append(final CharSequence seq, final Formatter formatter, final int flags, final int width,
                                   final int precision, final char padChar) {
        return append(seq, formatter, flags, width, precision, padChar, null);
    }

    /**
     * Handles the common {@link Formattable} operations of truncate-pad-append,
     * including optional ellipsis for truncation and configurable padding.
     * <p>
     * Delegates the formatting logic to an internal helper class {@code TruncatePadFormatter}
     * that applies precision handling, padding, and formatting output.
     * </p>
     *
     * @param seq  the string to handle, not null
     * @param formatter  the destination formatter, not null
     * @param flags  the flags for formatting, see {@code Formattable}
     * @param width  the width of the output, see {@code Formattable}
     * @param precision  the precision of the output, see {@code Formattable}
     * @param padChar  the pad character to use
     * @param truncateEllipsis  the ellipsis to use when precision dictates truncation,
     *                          null or empty causes a hard truncation
     * @return The {@code formatter} instance, not null
     * @throws IllegalArgumentException if {@code truncateEllipsis.length() > precision},
     *         given that {@code truncateEllipsis} is not null and {@code precision >= 0}
     */
    public static Formatter append(final CharSequence seq, final Formatter formatter, final int flags, final int width,
                                   final int precision, final char padChar, final CharSequence truncateEllipsis) {
        return new TruncatePadFormatter(seq, formatter, flags, width, precision, padChar, truncateEllipsis).format();
    }

    /**
     * Handles the common {@link Formattable} operations of truncate-pad-append,
     * padding width underflow with spaces.
     *
     * @param seq  the string to handle, not null
     * @param formatter  the destination formatter, not null
     * @param flags  the flags for formatting, see {@code Formattable}
     * @param width  the width of the output, see {@code Formattable}
     * @param precision  the precision of the output, see {@code Formattable}
     * @param ellipsis  the ellipsis to use when precision dictates truncation,
     *                  null or empty causes a hard truncation
     * @return The {@code formatter} instance, not null
     * @throws IllegalArgumentException if {@code ellipsis.length() > precision},
     *         given that {@code ellipsis} is not null and {@code precision >= 0}
     */
    public static Formatter append(final CharSequence seq, final Formatter formatter, final int flags, final int width,
                                   final int precision, final CharSequence ellipsis) {
        return append(seq, formatter, flags, width, precision, ' ', ellipsis);
    }

    /**
     * Gets the default formatted representation of the specified
     * {@code Formattable}.
     *
     * @param formattable  the instance to convert to a string, not null
     * @return The resulting string, not null
     */
    public static String toString(final Formattable formattable) {
        return String.format(SIMPLEST_FORMAT, formattable);
    }

    /**
     * {@code FormattableUtils} instances should NOT be constructed in
     * standard programming. Instead, the methods of the class should be invoked
     * statically.
     *
     * <p>This constructor is public to permit tools that require a JavaBean
     * instance to operate.</p>
     */
    public FormattableUtils() {
        // Intentionally empty.
        // Exists only for frameworks or tests that require a public no-arg constructor.
    }
}
