package android.app.demompchart;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Map;

public class Chart {

    ArrayList<Entry> dataValues;
    LineDataSet lineDataset;
    LineData lineData;
    LineChart lineChart;

    public Chart(){
        dataValues = new ArrayList<>();
        lineDataSet = new LineDataSet(dataValues, "Line Chart 1");
    }

    public void initChart(){
        dataValues.add(new Entry(0,0));
    }
}
