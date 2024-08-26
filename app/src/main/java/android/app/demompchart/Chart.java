package android.app.demompchart;

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
        lineChart.setAutoScaleMinMaxEnabled(true);
        lineChart.setVisibleXRangeMaximum(500);
        lineChart.setVisibleXRangeMinimum(500);
    }

    public void addEntry(float x, float y){
        dataValues.add(new Entry(x,y));
    }

    public void updateChart(){
        lineDataset.notifyDataSetChanged();
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.moveViewToX(lineDataset.getEntryCount());
        lineChart.invalidate();
    }
}
