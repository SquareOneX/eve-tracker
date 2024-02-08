package squareonex.evetracker.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageableService<T> {
    Page<T> findPaginated(Pageable pageable);
}
