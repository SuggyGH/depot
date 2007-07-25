//
// $Id$
//
// samskivert library - useful routines for java programs
// Copyright (C) 2006-2007 Michael Bayne, Pär Winzell
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

package com.samskivert.jdbc.depot;

import java.sql.Connection;
import java.sql.SQLException;

import com.samskivert.jdbc.DatabaseLiaison;
import com.samskivert.jdbc.LiaisonRegistry;
import com.samskivert.jdbc.depot.annotation.GeneratedValue;

/**
 * Generates primary keys using an identity column.
 */
public class IdentityKeyGenerator extends KeyGenerator
{
    public IdentityKeyGenerator (GeneratedValue gv, String table, String column)
    {
        super(gv, table, column);
    }

    // from interface KeyGenerator
    public boolean isPostFactum ()
    {
        return true;
    }

    // from interface KeyGenerator
    public void init (Connection conn, DatabaseLiaison liaison)
        throws SQLException
    {
        liaison.initializeGenerator(conn, _table, _column, _initialValue, _allocationSize);
    }

    // from interface KeyGenerator
    public int nextGeneratedValue (Connection conn, DatabaseLiaison liaison)
        throws SQLException
    {
        return liaison.lastInsertedId(conn, _table, _column);
    }
}
