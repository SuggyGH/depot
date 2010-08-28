//
// $Id$
//
// Depot library - a Java relational persistence library
// Copyright (C) 2006-2008 Michael Bayne and Pär Winzell
//
// This library is free software; you can redistribute it and/or modify it
// under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License, or
// (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package com.samskivert.depot.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to specify that a full text index is to be included in the generated DDL
 * for a table.
 */
@Target(value={})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface FullTextIndex
{
    public enum Configuration {
        /** For searching generic strings; makes no linguistic/semantic assumptions. */
        Simple,
        /** For English text search; does basic stemming. */
        English
    }

    /**
     * An identifier for this index, unique with the scope of the record.
     */
    public String name ();

    /**
     * An array of the field names that should be indexed.
     */
    public String[] fields ();

    /**
     * What parser/dictionary to use for this index.
     */
    public Configuration configuration () default Configuration.English;
}