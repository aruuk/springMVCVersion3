package peaksoft.service;

import peaksoft.model.Lesson;

import java.io.IOException;
import java.util.List;

public interface LessonService {

    void saveLesson(Long courseId, Lesson lesson) ;

    void updateLesson(Long id, Lesson lesson) ;

    void deleteLesson(Long id);

    List<Lesson> getLessons(Long courseId);

    Lesson getLessonById(Long id);
}
