package squareonex.evetracker.converters.text;

import jakarta.annotation.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import squareonex.evetrackerdata.model.Transaction;
import squareonex.evetrackerdata.repositories.ItemRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

@Component
public class StringToTransaction implements Converter<String, Transaction> {

    private final ItemRepository itemRepository;

    public StringToTransaction(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Nullable
    @Synchronized
    public Transaction convert(String source) {
        if (source == null)
            return null;
        else if (source.equals(""))
            return new Transaction();
        final Scanner scanner = new Scanner(source);
        scanner.useDelimiter("\\t");
        scanner.useLocale(Locale.ENGLISH);

        final LocalDateTime localDateTime = LocalDateTime.parse(scanner.next().trim(), DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

        final int quantity = scanner.nextInt();

        final String itemName = scanner.next().trim();

        final String pricePerItem = scanner.next().trim().replace(".", "");
        final double pricePerItemValue = Double.parseDouble(pricePerItem.substring(0, pricePerItem.length() - 4));

        final String totalPrice = scanner.next().trim().replace(".", "");
        final double totalPriceValue = Double.parseDouble(totalPrice.substring(0, totalPrice.length() - 4));

        final String playerName = scanner.next().trim();

        final String location = scanner.next().trim();

        System.out.println(localDateTime);
        System.out.println(quantity);
        System.out.println(itemName);
        System.out.println(pricePerItemValue);
        System.out.println(totalPriceValue);
        System.out.println(playerName);
        System.out.println(location);

        final Transaction transaction = new Transaction();
        transaction.setDate(localDateTime);
        transaction.setQuantity(quantity);

        itemRepository.findByNameIgnoreCase(itemName).ifPresentOrElse(transaction::setItem, () -> {
            throw new IllegalArgumentException("Item with name=" + itemName + " not found");}
        );

        transaction.setPrice((float) totalPriceValue);

        transaction.setIsBuy(totalPriceValue < 0);

        return transaction;
    }
}
