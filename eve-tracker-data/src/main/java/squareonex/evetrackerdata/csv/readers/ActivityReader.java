package squareonex.evetrackerdata.csv.readers;

import squareonex.evetrackerdata.model.Activity;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface ActivityReader {
    Map<Integer, Activity> readAll() throws FileNotFoundException;

}
