package peaksoft.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Lesson;
import peaksoft.repository.LessonRepository;
import peaksoft.service.LessonService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        lessonRepository.saveLesson(courseId, lesson);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        lessonRepository.updateLesson(id, lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteLesson(id);
    }

    @Override
    public List<Lesson> getLessons(Long courseId) {
        return lessonRepository.getLessons(courseId);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.getLessonById(id);
    }

}
