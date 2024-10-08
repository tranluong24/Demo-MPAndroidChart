package android.app.demompchart;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class Chart {
    private final ArrayList<Entry> dataValues;
    private final LineDataSet lineDataset;
    private final LineData lineData;
    private final LineChart lineChart;
    public Chart(LineChart lineChart){
        dataValues = new ArrayList<>();
        lineDataset = new LineDataSet(dataValues, "Line Chart 1");
        lineData = new LineData(lineDataset);
        this.lineChart = lineChart;
        this.lineChart.setData(lineData);
    }

    public void initChart(){
        lineDataset.setDrawCircles(true);
        lineDataset.setCircleColor(Color.RED);
        lineDataset.setColor(Color.TRANSPARENT);
        lineChart.setAutoScaleMinMaxEnabled(true);

    }

    public void addEntry(float x, float y){
        dataValues.add(new Entry(x,y));
    }

    public void updateChart(){
        lineDataset.notifyDataSetChanged();

        lineData.notifyDataChanged();

        lineChart.notifyDataSetChanged();

        lineChart.setVisibleXRangeMaximum(500);
        lineChart.setVisibleXRangeMinimum(500);
        lineChart.moveViewToX(lineDataset.getEntryCount());

        lineChart.invalidate();
    }
}
