package peaksoft.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;
import peaksoft.model.Task;
import peaksoft.repository.TaskRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveTask(Task task, Long id) {
        Lesson lesson = entityManager.find(Lesson.class, id);
        lesson.addTask(task);
        task.setLesson(lesson);
        entityManager.persist(task);
    }

    @Override
    public void updateTask(Long id, Task task) {
        Task task1 = entityManager.find(Task.class, id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        entityManager.merge(task1);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = entityManager.find(Task.class, id);
        entityManager.remove(task);
    }

    @Override
    public List<Task> getTasks(Long id) {
        return entityManager.createQuery("select t from Task t where " +
                "t.lesson.id =: id", Task.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class, id);
    }
}
