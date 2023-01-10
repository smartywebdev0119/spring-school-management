package pl.rstrzalkowski.syllabus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class CourseController {

//    private final SubjectService subjectService;
//    private final GradeService gradeService;
//    private final PostService postService;
//    private final ActivityService activityService;
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public Course createCourse(@Valid @RequestBody CreateCourseDTO dto) {
//        return subjectService.create(dto);
//    }
//
//    @GetMapping("/{id}")
//    public Course getCourseById(@PathVariable("id") Long id) {
//        return subjectService.getById(id);
//    }
//
//    @GetMapping
//    public List<Course> getActiveCourses() {
//        return subjectService.getAllActive();
//    }
//
//    @GetMapping("/archived")
//    public List<Course> getArchivedCourses() {
//        return subjectService.getAllArchived();
//    }
//
//    @GetMapping("/{id}/posts")
//    public List<Post> getCoursePosts(@PathVariable("id") Long id) {
//        return postService.getByCourseId(id);
//    }
//
//    @GetMapping("/{id}/activities")
//    public List<Activity> getCourseActivities(@PathVariable("id") Long id) {
//        return activityService.getByCourseId(id);
//    }
//
//    @GetMapping("/{id}/students")
//    public List<Student> getCourseStudents(@PathVariable("id") Long id) {
//        return new ArrayList<>(subjectService.getStudentsByCourseId(id));
//    }
//
//    @GetMapping("/{id}/teachers")
//    public List<Teacher> getCourseTeachers(@PathVariable("id") Long id) {
//        return new ArrayList<>(subjectService.getTeachersByCourseId(id));
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PutMapping("/{id}/posts")
//    public void addPost(@PathVariable("id") Long id, @Valid @RequestBody CreatePostDTO dto) {
//        postService.create(id, dto);
//    }
//
//    @ResponseStatus(HttpStatus.CREATED)
//    @PutMapping("/{id}/activities")
//    public void addActivity(@PathVariable("id") Long id, @Valid @RequestBody CreateActivityDTO dto) {
//        activityService.create(id, dto);
//    }
//
//    @PutMapping("/{id}")
//    public Course updateCourse(@PathVariable("id") Long id, @Valid @RequestBody UpdateCourseDTO dto) {
//        return subjectService.update(id, dto);
//    }
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable("id") Long id) {
//        subjectService.deleteById(id);
//    }
}
