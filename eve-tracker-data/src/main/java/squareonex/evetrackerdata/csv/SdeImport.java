package squareonex.evetrackerdata.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import squareonex.evetrackerdata.csv.readers.ActivityReader;
import squareonex.evetrackerdata.model.Activity;
import squareonex.evetrackerdata.model.Item;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SdeImport {
    public List<Activity> readActivities() {
        try (FileReader reader = new FileReader("eve-tracker-data\\src\\main\\resources\\activities.csv")) {
            CsvToBean<Activity> csvToBean = new CsvToBeanBuilder<Activity>(reader)
                    .withType(Activity.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withVerifier(new ActivityVerifier())
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Item> readItems() {
        try (FileReader reader = new FileReader("eve-tracker-data\\src\\main\\resources\\invTypes.csv")) {
            CsvToBean<Item> csvToBean = new CsvToBeanBuilder<Item>(reader)
                    .withType(Item.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withVerifier(new ItemVerifier())
                    .build();
            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Blueprint> readBlueprints(){
        try {
            return blueprintReader.readAll();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
