package squareonex.evetracker.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Provides paginated search results
 * @param <T> Pojo or Entity that of the search
 */
public interface PageableService<T> {
    /**
     * Search for all Objects and return them in a PageImpl using the provided Pageable
     * @param pageable pagination information
     * @return page
     */
    Page<T> findPaginated(Pageable pageable);
}
