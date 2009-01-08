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

package com.samskivert.depot.expression;

import java.util.Collection;

import com.samskivert.depot.PersistentRecord;
import com.samskivert.depot.impl.ExpressionVisitor;

/**
 * An expression that unambiguously identifies a field of a class, e.g. GameRecord.itemId.
 */
public class ColumnExp
    implements SQLExpression
{
    /** The name of the column we reference. */
    public final String name;

    /** Converts an array of column expressions to an array of just the bare names. */
    public static String[] toNames (ColumnExp[] columns)
    {
        String[] names = new String[columns.length];
        for (int ii = 0; ii < names.length; ii++) {
            names[ii] = columns[ii].name;
        }
        return names;
    }

    public ColumnExp (Class<? extends PersistentRecord> pClass, String field)
    {
        super();
        _pClass = pClass;
        this.name = field;
    }

    // from SQLExpression
    public void accept (ExpressionVisitor builder)
    {
        builder.visit(this);
    }

    // from SQLExpression
    public void addClasses (Collection<Class<? extends PersistentRecord>> classSet)
    {
        classSet.add(_pClass);
    }

    public Class<? extends PersistentRecord> getPersistentClass ()
    {
        return _pClass;
    }

    public String getField ()
    {
        return name;
    }

    @Override // from Object
    public String toString ()
    {
        return "\"" + name + "\""; // TODO: qualify with record name and be uber verbose?
    }

    /** The table that hosts the column we reference, or null. */
    protected final Class<? extends PersistentRecord> _pClass;
}
