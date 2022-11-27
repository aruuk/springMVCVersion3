package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;

import java.util.List;

@Repository
public interface CourseRepository {

    List<Course> getAllCourses(Long id);

    void saveCourses(Course course, Long id);

    void updateCourse(Long id, Course course) ;

    void deleteCourse(Long id);

    Course getCourseById(Long id);

}
