package android.app.demompchart;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private LineChart lineChart;
    private LineDataSet lineDataSet;
    private LineDataSet lineDataSet2;
    private LineData lineData;
    private int xIndex = 0;
    private Timer timer;
    private int cnt = 0;
    private TextView textX;
    private TextView textY;
    Button btn;
    private boolean indx = false;
    ArrayList<Entry_2> dataValue = new ArrayList<>();
    Button btnSave, btnNew, btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = findViewById(R.id.lineChart);

        textX = findViewById(R.id.textX);
        textY = findViewById(R.id.textY);
        btn = findViewById(R.id.btnLoad);

        btnSave = findViewById(R.id.save);
        btnNew = findViewById(R.id.newTest);
        btnOpen = findViewById(R.id.Open);

        lineDataSet = new LineDataSet(new ArrayList<>(), "Sine1 Wave");
        lineDataSet2 = new LineDataSet(new ArrayList<>(), "Sine2 Wave");

        lineData = new LineData(lineDataSet, lineDataSet2);

        initChart(lineDataSet, lineDataSet2, lineData, lineChart);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile("DataSaved_2.bin");
            }
        });

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile("DataSaved_2.bin");
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                btn.setText("Resume");
                Analytic();
                return true;
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setText("Stop");
                btn.setEnabled(false);

                lineData.removeDataSet(2);

                updateChart();
                startPlotting();
            }
        });
        startPlotting();
    }

    private void startPlotting() {
        cnt = 0;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (cnt == 1234) {
                    timer.cancel();
                    runOnUiThread(() -> btn.setEnabled(true));
                    runOnUiThread(() -> btn.setText("Resume"));
                } else {
                    if(!indx)
                        runOnUiThread(() -> addEntry(lineDataSet,lineDataSet2));
                    else runOnUiThread(() -> addEntry(lineDataSet2,lineDataSet));
                    cnt++;
                }
            }
        }, 0, 1); // Vẽ mỗi 10ms
    }

    private void Analytic() {
        lineDataSet2.setVisible(false);
        lineDataSet.setVisible(false);

        lineDataSet.setForm(Legend.LegendForm.NONE);
        lineDataSet.setLabel("");
        lineDataSet2.setForm(Legend.LegendForm.NONE);
        lineDataSet2.setLabel("");

        ArrayList<Entry> dataEntryTmp = new ArrayList<>(dataValue);

        LineDataSet lineDataSet3 = new LineDataSet(dataEntryTmp, "Sine3 Wave");

        lineDataSet3.setColor(Color.GREEN);
        lineDataSet3.setDrawCircles(true);
        lineDataSet3.setCircleColor(Color.GREEN);
        lineDataSet3.setCircleRadius(4f); // Điều chỉnh kích thước vòng tròn (mặc định là 4f)
        lineDataSet3.setCircleHoleRadius(0f); // Điều chỉnh kích thước của lỗ tròn bên trong (mặc định là 2f)

        lineData.addDataSet(lineDataSet3);

        updateChart();
    }

    private void addEntry(LineDataSet lineSet, LineDataSet lineSetTemp) {
        float yValue = (float) Math.sin(Math.toRadians(xIndex++));
        dataValue.add(new Entry_2(xIndex, yValue));

        lineSetTemp.setVisible(false);
        lineSet.setVisible(true);

        lineSet.addEntry(new Entry(xIndex, yValue));

        if (lineSet.getEntryCount() >= 501) {
            lineSetTemp.addEntry(new Entry(xIndex, yValue));
        }

        textX.setText(lineDataSet.getEntryCount() + "");
        textY.setText(lineDataSet2.getEntryCount() + "");

        updateChart();

        if (lineSet.getEntryCount() == 1000) {
            indx = !indx;
            lineSet.clear();
            textX.setText(lineDataSet.getEntryCount() + "");
            textY.setText(lineDataSet2.getEntryCount() + "");
            lineSet.setForm(Legend.LegendForm.NONE);
            lineSet.setLabel("");
            lineSetTemp.setForm(Legend.LegendForm.SQUARE);
            lineSetTemp.setLabel("Sin2");

            lineSetTemp.setVisible(true);
            lineSet.setVisible(false);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    private void initChart(LineDataSet lineSet1, LineDataSet lineSet2, LineData line, LineChart chart) {
        lineSet2.setDrawCircles(true); // Ẩn các vòng tròn trên điểm
        lineSet2.setCircleColor(Color.BLUE);
        lineSet2.setColor(Color.BLUE);

        lineSet1.setDrawCircles(true); // Ẩn các vòng tròn trên điểm
        lineSet1.setColor(Color.RED);
        lineSet1.setCircleColor(Color.RED);

        lineSet2.setForm(Legend.LegendForm.NONE);
        lineSet2.setLabel("");

        lineSet1.setForm(Legend.LegendForm.SQUARE);
        lineSet1.setLabel("Sin1");

        lineSet1.setDrawValues(false);
        lineSet2.setDrawValues(false);

        chart.setData(line);

        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.setDrawGridBackground(false);

        Legend legend = chart.getLegend();
        legend.setTextColor(Color.WHITE);

        // Configure X axis to auto-scroll
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
//        xAxis.setLabelCount(100);
//        xAxis.setDrawLabels(false);

        // Configure Y axis to show only on the left
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextColor(Color.WHITE);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false); // Disable right y-axis

        chart.setAutoScaleMinMaxEnabled(true);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                float x = (e.getX() / 1000);
                float y = e.getY();

                textX.setText("X: " + String.format("%.3f", x));
                textY.setText("Y: " + String.format("%.2f", y));
            }

            @Override
            public void onNothingSelected() {
                textX.setText("X: ");
                textY.setText("Y: ");
            }
        });
    }

    private void updateChart() {
        lineDataSet.notifyDataSetChanged();
        lineDataSet2.notifyDataSetChanged();

        lineData.notifyDataChanged();

        lineChart.notifyDataSetChanged();

        lineChart.setVisibleXRangeMaximum(500);
        lineChart.setVisibleXRangeMinimum(500);

        lineChart.moveViewToX(xIndex - 1);

        lineChart.invalidate(); // Cập nhật biểu đồ
    }

    private void writeFile(String file_name){
        try{
            FileOutputStream fileOutStream = openFileOutput(file_name, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutStream);

            objectOutputStream.writeObject(dataValue);

            textX.setText("OK");
            objectOutputStream.close();
            fileOutStream.close();

        }catch(Exception e){
            Log.e("Error", e.toString());
            textX.setText("NOT OK");
        }
    }

    private void readFile(String file_name){
        try {
            FileInputStream fileInputStream = openFileInput(file_name);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            ArrayList<Entry_2> dataRead = (ArrayList<Entry_2>) objectInputStream.readObject();
            Log.d("Thong bao", "Opened succesfull");


            objectInputStream.close();
            fileInputStream.close();

            lineDataSet2.setVisible(false);
            lineDataSet.setVisible(false);

            lineDataSet.setForm(Legend.LegendForm.NONE);
            lineDataSet.setLabel("");
            lineDataSet2.setForm(Legend.LegendForm.NONE);
            lineDataSet2.setLabel("");

            ArrayList<Entry> dataEntryTmp = new ArrayList<>(dataRead);
            textX.setText(dataEntryTmp.size()+"");

            LineDataSet lineDataSet3 = new LineDataSet(dataEntryTmp, "Sine3 Wave");

            lineDataSet3.setColor(Color.GREEN);
            lineDataSet3.setDrawCircles(true);
            lineDataSet3.setCircleColor(Color.GREEN);
            lineDataSet3.setCircleRadius(4f); // Điều chỉnh kích thước vòng tròn (mặc định là 4f)
            lineDataSet3.setCircleHoleRadius(0f); // Điều chỉnh kích thước của lỗ tròn bên trong (mặc định là 2f)

            lineData.addDataSet(lineDataSet3);

            updateChart();
        }catch(Exception e){
            Log.e("Error", e.toString());
        }
    }
}
