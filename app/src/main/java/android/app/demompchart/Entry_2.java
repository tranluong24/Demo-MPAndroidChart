package android.app.demompchart;

import com.github.mikephil.charting.data.Entry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Entry_2 extends Entry implements Serializable {

    private static final long serialVersionUID = 1L; // Thêm serialVersionUID cho an toàn
    private float xValue;
    private float yValue;

    // Constructor
    public Entry_2(float x, float y) {
        super(x, y);
        this.xValue = x;
        this.yValue = y;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();  // Ghi các thuộc tính của Entry_2
        oos.writeFloat(getX());    // Ghi giá trị x
        oos.writeFloat(getY());    // Ghi giá trị y
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();   // Đọc các thuộc tính của Entry_2
        float x = ois.readFloat(); // Đọc giá trị x
        float y = ois.readFloat(); // Đọc giá trị y
        // Khôi phục giá trị cho Entry
        setX(x);
        setY(y);
    }
}
