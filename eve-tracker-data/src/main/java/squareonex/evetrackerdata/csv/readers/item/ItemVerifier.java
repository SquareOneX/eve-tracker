package squareonex.evetrackerdata.csv.readers.item;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;
import squareonex.evetrackerdata.model.Item;

public class ItemVerifier implements BeanVerifier<Item> {
    @Override
    public boolean verifyBean(Item item) throws CsvConstraintViolationException {
        return item.getPublished();
    }
}
