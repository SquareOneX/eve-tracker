package squareonex.evetrackerdata.services.map;

import squareonex.evetrackerdata.model.Job;
import squareonex.evetrackerdata.services.JobService;

import java.util.Set;

public class JobServiceImpl extends AbstractMapService<Job, Long> implements JobService {
    private Long key = 0L;

    @Override
    public Set<Job> findAll() {
        return super.findAll();
    }

    @Override
    public Job findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Job save(Job object) {
        if (object.getId() == null){
            while (map.containsKey(key))
                key++;
            object.setId(key);
            return super.save(key, object);
        }else {
            return super.save(object.getId(), object);
        }
    }

    @Override
    public void delete(Job object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
