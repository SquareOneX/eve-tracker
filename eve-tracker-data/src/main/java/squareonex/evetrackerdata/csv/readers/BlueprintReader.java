package squareonex.evetrackerdata.csv.readers;

import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Blueprint;
import squareonex.evetrackerdata.model.Item;

import java.io.FileNotFoundException;
import java.util.Map;

public interface BlueprintReader {
    Map<Long, Blueprint> readAll(Map<Long, Item> items, Map<Integer, Activity> activities) throws FileNotFoundException;
}
