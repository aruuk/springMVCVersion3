package peaksoft.service;

import peaksoft.model.Group;

import java.io.IOException;
import java.util.List;

public interface GroupService {

    List<Group> getAllGroupByCourseId(Long courseId);

    void saveGroup(Group group, Long id);

    List<Group> getAllGroup(Long id);

    Group getById(Long id);

    void updateGroup(Long id, Group group);

    void deleteGroup(Long id);

    void addGroupByCourseId(Long id, Long courseId, Group group);

    void assignGroupToCourse(Long courseId, Long groupId);

}
