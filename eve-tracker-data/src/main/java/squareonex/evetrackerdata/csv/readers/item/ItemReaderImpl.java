package squareonex.evetrackerdata.csv.readers.item;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import squareonex.evetrackerdata.csv.readers.ItemReader;
import squareonex.evetrackerdata.model.Item;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ItemReaderImpl implements ItemReader {
    @Override
    public Map<Long, Item> readAll() throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource("invTypes.csv");
        FileReader reader = new FileReader(resource.getPath());
        CsvToBean<ItemDTO> csvToBean = new CsvToBeanBuilder<ItemDTO>(reader)
                .withType(ItemDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .withVerifier(new ItemVerifier())
                .build();

        Map<Long, Item> items = new HashMap<>();
        for (ItemDTO dto : csvToBean) {
            Item item = new Item();
            item.setId(dto.getId());
            item.setName(dto.getName());
            item.setPublished(dto.getPublished());

            items.put(dto.getId(), item);
        }

        return items;
    }
}
