package ch.robinglauser.winnetou;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin on 21.03.2015.
 */
class TableModel extends AbstractTableModel {

    ArrayList wordsList;
    String headerList[] = new String[]{ "IP","Hostname"};

    public TableModel(ArrayList list) {
        wordsList = list;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }


    @Override
    public int getRowCount() {
        return wordsList.size();
    }

    // this method is called to set the value of each cell
    @Override
    public Object getValueAt(int row, int column) {
        HostEntry entity = null;
        entity = (HostEntry) wordsList.get(row);

        switch (column) {
            case 0:
                return entity.getIpAddress();
            case 1:
                return entity.getHostname();
            default:
                return "";
        }
    }


//This method will be used to display the name of columns

    public String getColumnName(int col) {
        return headerList[col];
    }
}