/*
 *
 *  The DbUnit Database Testing Framework
 *  Copyright (C)2002-2004, DbUnit.org
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package org.dbunit.assertion;

import org.dbunit.dataset.Column;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.filter.DefaultColumnFilter;

import junit.framework.TestCase;

/**
 * @author gommma (gommma AT users.sourceforge.net)
 * @author Last changed by: $Author: gommma $
 * @version $Revision: 850 $ $Date: 2008-10-31 19:39:59 +0100 (p, 31 okt 2008) $
 * @since 2.4.0
 */
public class DefaultFailureHandlerTest extends TestCase 
{
    
    public void testGetColumn() throws Exception
    {
        Column[] cols = new Column[]{
                new Column("COL1", DataType.UNKNOWN),
                new Column("COL2", DataType.UNKNOWN)
        };
        DefaultTable table = new DefaultTable("MY_TABLE", cols);
        table.addRow(new Object[] {"value1", "value2"});
        
        // Filter COL1
        ITable tableFiltered = DefaultColumnFilter.excludedColumnsTable(table, new String[]{"COL1"});
        
        DefaultFailureHandler failureHandler = new DefaultFailureHandler(cols);
        String info = failureHandler.getAdditionalInfo(tableFiltered, tableFiltered, 0, "COL1");
        
        String expectedInfo = "Additional row info: ('COL1': expected=<value1>, actual=<value1>) ('COL2': expected=<value2>, actual=<value2>)";
        assertEquals(expectedInfo, info);
    }

}
