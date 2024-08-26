package android.app.demompchart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.app.demompchart.Chart;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;
    private LineDataSet lineDataSet;
    private LineDataSet lineDataSet2;
    private LineDataSet lineDataSet3;
    private LineData lineData;
    private int xIndex = 0;
    private Timer timer;
    private int cnt = 0;
    private TextView textX;
    private TextView textY;
    Button btn;
    private int indx = 0;
    private int dem = 0;
    ArrayList<Entry> dataValuesTemp;
    ArrayList<Entry> dataValue = new ArrayList<>();
    Chart chartLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = findViewById(R.id.lineChart);
        chartLine= new Chart(lineChart);
        chartLine.initChart();
        startPlotting();


        textX = findViewById(R.id.textX);
        textY = findViewById(R.id.textY);
        btn = findViewById(R.id.btnLoad);

//        lineDataSet = new LineDataSet(new ArrayList<>(), "Sine1 Wave");
//        lineDataSet2 = new LineDataSet(new ArrayList<>(), "Sine2 Wave");
//        lineDataSet3 = new LineDataSet(new ArrayList<>(), "Sine3 Wave");
//
//        lineDataSet2.setDrawCircles(true); // Ẩn các vòng tròn trên điểm
//        lineDataSet.setDrawCircles(true); // Ẩn các vòng tròn trên điểm
//        lineDataSet2.setCircleColor(Color.BLUE);
//        lineDataSet2.setColor(Color.BLUE);
//        lineDataSet.setColor(Color.RED);
//        lineDataSet.setCircleColor(Color.RED);
//
//        lineDataSet2.setForm(Legend.LegendForm.NONE);
//        lineDataSet2.setLabel("");
//        lineDataSet.setForm(Legend.LegendForm.SQUARE);
//        lineDataSet.setLabel("Sin1");
//
//        lineDataSet.setDrawValues(false);
//        lineDataSet2.setDrawValues(false);
//
//        lineData = new LineData(lineDataSet, lineDataSet2);
//        lineChart.setData(lineData);
//
//        lineChart.setTouchEnabled(true);
//        lineChart.setDragEnabled(true);
//        lineChart.setScaleEnabled(true);
//        lineChart.setPinchZoom(true);
//        lineChart.setDrawGridBackground(false);
//
//        Legend legend = lineChart.getLegend();
//        legend.setTextColor(Color.WHITE);
//
//        // Configure X axis to auto-scroll
//        XAxis xAxis = lineChart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setGranularity(1f);
//        xAxis.setTextColor(Color.WHITE);
////        xAxis.setLabelCount(100);
////        xAxis.setDrawLabels(false);
//
//        // Configure Y axis to show only on the left
//        YAxis leftAxis = lineChart.getAxisLeft();
//        leftAxis.setDrawGridLines(false);
//        leftAxis.setTextColor(Color.WHITE);
////        leftAxis.setDrawLabels(false);
//
//        YAxis rightAxis = lineChart.getAxisRight();
//        rightAxis.setEnabled(false); // Disable right y-axis
//
//        lineChart.setAutoScaleMinMaxEnabled(true);
//
//        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//                float x = (e.getX() / 1000);
//                float y = e.getY();
//
//                textX.setText("X: " + String.format("%.3f", x));
//                textY.setText("Y: " + String.format("%.2f", y));
//            }
//
//            @Override
//            public void onNothingSelected() {
//                textX.setText("X: ");
//                textY.setText("Y: ");
//            }
//        });
//
//        btn.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                timer.cancel();
//                lineDataSet2.setVisible(false);
//                lineDataSet.setVisible(false);
//
//                lineDataSet.setForm(Legend.LegendForm.NONE);
//                lineDataSet.setLabel("");
//                lineDataSet2.setForm(Legend.LegendForm.NONE);
//                lineDataSet2.setLabel("");
//
//                lineDataSet3.clear();
//
//                dataValuesTemp = new ArrayList<>(dataValue);
//
//                lineDataSet3.setValues(dataValuesTemp);
//
//                lineDataSet3.setColor(Color.GREEN);
//                lineDataSet3.setDrawCircles(true);
//                lineDataSet3.setCircleColor(Color.GREEN);
//                lineDataSet3.setCircleRadius(4f); // Điều chỉnh kích thước vòng tròn (mặc định là 4f)
//                lineDataSet3.setCircleHoleRadius(0f); // Điều chỉnh kích thước của lỗ tròn bên trong (mặc định là 2f)
//
//                lineData.addDataSet(lineDataSet3);
//
//                lineData.notifyDataChanged();
//
//                lineChart.notifyDataSetChanged();
//
//                lineChart.setVisibleXRangeMaximum(500);
//                lineChart.setVisibleXRangeMinimum(500);
//                lineChart.moveViewToX(xIndex - 1);
//                lineChart.invalidate();
//                btn.setText("Resume");
//                return true;
//            }
//        });
//
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btn.setText("Stop");
//                lineDataSet3.clear();
//                lineData.removeDataSet(lineDataSet3);
//
//                lineData.notifyDataChanged();
//
//                lineChart.notifyDataSetChanged();
//                lineChart.setVisibleXRangeMaximum(500);
//                lineChart.setVisibleXRangeMinimum(500);
//                lineChart.moveViewToX(xIndex - 1);
//                lineChart.invalidate();
//                startPlotting();
//            }
//        });
//
//        // Khởi động vẽ liên tục
//        startPlotting();
    }

    private void startPlotting() {
        cnt = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (cnt++ == 1001) {
                    timer.cancel();
                } else {
                    float yValue = (float) Math.sin(Math.toRadians(xIndex++));
                    runOnUiThread(() -> addEntry(""));
                }
            }
        }, 0, 10); // Vẽ mỗi 100ms
    }

    private void addEntry(String a){
        float yValue = (float) Math.sin(Math.toRadians(xIndex++));
        chartLine.addEntry(xIndex,yValue);
        chartLine.updateChart();
    }

    private void addEntry() {
        float yValue = (float) Math.sin(Math.toRadians(xIndex));
        dataValue.add(new Entry(xIndex, yValue));

        if (indx == 0) {
            lineDataSet2.setVisible(false);
            lineDataSet.setVisible(true);
            lineDataSet.addEntry(new Entry(xIndex, yValue));
            if (lineDataSet.getEntryCount() >= 501) {
                lineDataSet2.addEntry(new Entry(xIndex, yValue));
            }
        } else if (indx == 1) {
            lineDataSet.setVisible(false);
            lineDataSet2.setVisible(true);
            lineDataSet2.addEntry(new Entry(xIndex, yValue));
            if (lineDataSet2.getEntryCount() >= 501) {
                lineDataSet.addEntry(new Entry(xIndex, yValue));
            }
        }
        xIndex += 1;

        textX.setText(lineDataSet.getEntryCount() + "");
        textY.setText(lineDataSet2.getEntryCount() + "");

        if (lineDataSet2.getEntryCount() >= 1000 || lineDataSet.getEntryCount() >= 1000) {
            if (indx == 0) {
                indx = 1;
                lineDataSet.clear();
//                lineDataSet.setForm(Legend.LegendForm.NONE);
//                lineDataSet.setLabel("");
//                lineDataSet2.setForm(Legend.LegendForm.SQUARE);
//                lineDataSet2.setLabel("Sin2");

            } else if (indx == 1) {
                indx = 0;
                lineDataSet2.clear();
//                lineDataSet2.setForm(Legend.LegendForm.NONE);
//                lineDataSet2.setLabel("");
//                lineDataSet.setForm(Legend.LegendForm.SQUARE);
//                lineDataSet.setLabel("Sin1");

            }
        }

        lineDataSet.notifyDataSetChanged();
        lineDataSet2.notifyDataSetChanged();

        lineData.notifyDataChanged();

        lineChart.notifyDataSetChanged();

        lineChart.setVisibleXRangeMaximum(500);
        lineChart.setVisibleXRangeMinimum(500);

        lineChart.moveViewToX(xIndex - 1);

        lineChart.invalidate(); // Cập nhật biểu đồ
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
