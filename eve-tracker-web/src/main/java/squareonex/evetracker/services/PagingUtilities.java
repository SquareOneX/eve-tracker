package squareonex.evetracker.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import squareonex.evetrackerdata.model.Item;

import java.util.List;
import java.util.stream.IntStream;

public abstract class PagingUtilities {
    public static Model addPageableDataToModel(Model model, PageableService service, int pageIndex, int size) {
        PageRequest requestedPage = PageRequest.of((pageIndex - 1), size);
        Page page = service.findPaginated(requestedPage);
        model.addAttribute("data", page);

        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return model;
    }
}
