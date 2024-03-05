package squareonex.evetrackerdata.csv.readers;

import squareonex.evetrackerdata.model.Item;

import java.io.FileNotFoundException;
import java.util.Map;

public interface ItemReader {
    Map<Long, Item> readAll() throws FileNotFoundException;
}
