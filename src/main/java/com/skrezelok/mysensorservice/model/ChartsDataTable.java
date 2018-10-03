package com.skrezelok.mysensorservice.model;

import java.util.ArrayList;
import java.util.List;

public class ChartsDataTable {
    private List<Column> cols;
    private List<Row> rows;



    public ChartsDataTable() {
        this.cols = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    public void addColumn(String type, String id, String label) {
        this.cols.add(new Column(type, id, label));
    }

    public Row createRow() {
        Row row = new Row();
        this.rows.add(row);
        return row;
    }

    public List<Column> getCols() {
        return cols;
    }

    public void setCols(List<Column> cols) {
        this.cols = cols;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    private class Column {
        private String type;
        private String id;
        private String label;

        public Column(String type, String id, String label) {
            this.type = type;
            this.id = id;
            this.label = label;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public class Row {
        private List<Cell> c;

        public Row() {
            c = new ArrayList<>();
        }

        public Row addCell(Object v, String f){
            this.c.add(new Cell(v, f));
            return this;
        }

        private class Cell {
            private Object v; // value of cell
            private String f; // formatted value of cell

            public Cell(Object v, String f) {
                this.v = v;
                this.f = f;
            }

            public Object getV() {
                return v;
            }

            public void setV(Object v) {
                this.v = v;
            }

            public String getF() {
                return f;
            }

            public void setF(String f) {
                this.f = f;
            }

        }
    }
}
