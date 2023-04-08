package format;

import java.util.ArrayList;
import java.util.List;

public abstract class FormatTXT<T> implements Format<T>{
    protected String extension;
    protected String format;
    protected String regex;

    public String getRegex() {
        return regex;
    }

    @Override
    public String getExtension() {
        return extension;
    }

}
