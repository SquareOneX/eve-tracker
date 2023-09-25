package squareonex.evetrackerdata.csv.readers;

import java.io.FileNotFoundException;
import java.util.List;

interface Reader<T> {
    List<T> readAll() throws FileNotFoundException;
}
