package format;

import java.util.List;

public interface Format<T> {
    String allToFormat(List<T> items);
    List<T> allFromFormat(String data);
    String toFormat(T item);
    T fromFormat(String data);
    String getExtension();
}
