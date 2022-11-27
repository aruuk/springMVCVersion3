package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Group;

import java.util.List;

@Repository
public interface GroupRepository {

    void saveGroup(Group group, Long id);

    List<Group> getAllGroup(Long id);

    Group getByIdGroup(Long id);

    void updateGroup(Long id, Group group);

    void deleteGroup(Long id);

    void addGroupByCourseId(Long id, Long courseId, Group group);

    List<Group> getAllGroupByCourseId(Long courseId);

    void assignGroupToCourse(Long courseId, Long groupId);
}
