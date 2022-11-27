package peaksoft.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;


@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void saveGroup(Group group, Long id) {
        groupRepository.saveGroup(group, id);
    }

    @Override
    public List<Group> getAllGroup(Long id) {
        return groupRepository.getAllGroup(id);
    }

    @Override
    public Group getById(Long id) {
        return groupRepository.getByIdGroup(id);
    }

    @Override
    public void updateGroup(Long id, Group group) {
        groupRepository.updateGroup(id, group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteGroup(id);
    }

    @Override
    public void addGroupByCourseId(Long id, Long courseId, Group group){
        groupRepository.addGroupByCourseId(id, courseId, group);
    }

    @Override
    public List<Group> getAllGroupByCourseId(Long courseId) {
        return groupRepository.getAllGroupByCourseId(courseId);
    }

    @Override
    public void assignGroupToCourse(Long courseId, Long groupId){
        groupRepository.assignGroupToCourse(courseId, groupId);
    }


}