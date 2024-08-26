package android.app.demompchart;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.Map;

public class Chart {

    ArrayList<Entry> dataValues;

    public Chart(){
        dataValues = new ArrayList<>();
    }

    public void initChart(){
        dataValues.add(new Entry(0,0));
    }
}
