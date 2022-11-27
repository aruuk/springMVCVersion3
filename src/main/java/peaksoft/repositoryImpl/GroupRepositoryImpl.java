package peaksoft.repositoryImpl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveGroup(Group group, Long id) {
        Course course = entityManager.find(Course.class, id);
        course.addGroup(group);
        group.addCourse(course);
        entityManager.merge(course);
    }

    @Override
    public List<Group> getAllGroup(Long id) {
        return entityManager.createQuery("select g from Group g where "
                + "g.company.id =: id",Group.class )
                .setParameter("id", id).getResultList();
    }

    @Override
    public Group getByIdGroup(Long id) {
        return entityManager.find(Group.class, id);
    }


    @Override
    public void updateGroup(Long id, Group group) {
        Group group1 = getByIdGroup(id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setImage(group.getImage());
        entityManager.merge(group1);
    }

    @Override
    public void deleteGroup(Long id) {
        Group group = entityManager.find(Group.class, id);
        for (Course c : group.getCourses()) {
            c.getGroups().remove(group);
            group.minus();
        }
        group.setCourses(null);
        entityManager.remove(group);
    }

    @Override
    public void addGroupByCourseId(Long id, Long courseId, Group group) {
        Course course = entityManager.find(Course.class, courseId);
        group.addCourse(course);
        course.addGroup(group);
        entityManager.merge(course);
    }

    @Override
    public List<Group> getAllGroupByCourseId(Long courseId) {
        return entityManager.find(Course.class, courseId).getGroups();
    }

    @SneakyThrows
    @Override
    public void assignGroupToCourse(Long courseId, Long groupId){
        Group group = entityManager.find(Group.class, groupId);
        Course course = entityManager.find(Course.class, courseId);
        if (course.getGroups()!=null){
            for (Group g : course.getGroups()) {
                if (Objects.equals(g.getId(), groupId)) {
                    throw new IOException("This group already exists!");
                }
            }
        }
        group.addCourse(course);
        course.addGroup(group);
        entityManager.merge(group);
        entityManager.merge(course);
    }
}
