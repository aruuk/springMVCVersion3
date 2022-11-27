package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Lesson;

import java.io.IOException;
import java.util.List;

@Repository
public interface LessonRepository {

    void saveLesson(Long courseId, Lesson lesson);

    void updateLesson(Long id, Lesson lesson) ;

    void deleteLesson(Long id);

    List<Lesson> getLessons(Long courseId);

    Lesson getLessonById(Long id);

}
