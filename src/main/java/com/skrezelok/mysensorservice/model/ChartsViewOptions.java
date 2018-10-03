package com.skrezelok.mysensorservice.model;

import java.util.HashMap;
import java.util.Map;

public class ChartsViewOptions {
    private Map<Integer, Series> series;
    private Map<Integer, VAxe> vAxes;

    public ChartsViewOptions() {
        this.series = new HashMap<>();
        this.vAxes = new HashMap<>();
    }

    public void addSeries (int seriesIndex, int axesIndex){
        this.series.put(seriesIndex, new Series(axesIndex));
    }

    public void addAxe (int axesIndex, String title, String textPosition) {
        this.vAxes.put(axesIndex, new VAxe(title, textPosition));
    }

    public Map<Integer, Series> getSeries() {
        return series;
    }

    public void setSeries(Map<Integer, Series> series) {
        this.series = series;
    }

    public Map<Integer, VAxe> getvAxes() {
        return vAxes;
    }

    public void setvAxes(Map<Integer, VAxe> vAxes) {
        this.vAxes = vAxes;
    }

    public class Series {
        private int targetAxisIndex;

        public Series(int targetAxisIndex) {
            this.targetAxisIndex = targetAxisIndex;
        }

        public int getTargetAxisIndex() {
            return targetAxisIndex;
        }

        public void setTargetAxisIndex(int targetAxisIndex) {
            this.targetAxisIndex = targetAxisIndex;
        }
    }

    public class VAxe {
        private String title;
        private String textPosition;

        public VAxe(String title, String textPosition) {
            this.title = title;
            this.textPosition = textPosition;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTextPosition() {
            return textPosition;
        }

        public void setTextPosition(String textPosition) {
            this.textPosition = textPosition;
        }
    }
}
