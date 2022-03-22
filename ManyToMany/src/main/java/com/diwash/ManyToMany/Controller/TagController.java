package com.diwash.ManyToMany.Controller;

import com.diwash.ManyToMany.Models.Course;
import com.diwash.ManyToMany.Models.Tag;
import com.diwash.ManyToMany.exception.ResourceNotFoundException;
import com.diwash.ManyToMany.repository.CourseRepository;
import com.diwash.ManyToMany.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = new ArrayList<Tag>();

        tagRepository.findAll().forEach(tags::add);

        if (tags.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/tutorials/{tutorialId}/tags")
    public ResponseEntity<List<Tag>> getAllTagsByCourseId(@PathVariable(value = "courseId") Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + courseId);
        }

        List<Tag> tags = tagRepository.findTagsByCoursesId(courseId);
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTagsById(@PathVariable(value = "id") Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));

        return new ResponseEntity<>(tag, HttpStatus.OK);
    }

    @GetMapping("/tags/{tagId}/courses")
    public ResponseEntity<List<Course>> getAllCoursesByTagId(@PathVariable(value = "tagId") Long tagId) {
        if (!tagRepository.existsById(tagId)) {
            throw new ResourceNotFoundException("Not found Tag  with id = " + tagId);
        }

        List<Course> tutorials = courseRepository.findCoursesByTagsId(tagId);
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @PostMapping("/courses/{tutorialId}/tags")
    public ResponseEntity<Tag> addTag(@PathVariable(value = "tutorialId") Long courseId, @RequestBody Tag tagRequest) {
        Tag tag = courseRepository.findById(courseId).map(tutorial -> {
            long tagId = tagRequest.getId();

            // tag is existed
            if (tagId != 0L) {
                Tag _tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
                tutorial.addTag(_tag);
                courseRepository.save(tutorial);
                return _tag;
            }

            // add and create new Tag
            tutorial.addTag(tagRequest);
            return tagRepository.save(tagRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + courseId));

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable("id") long id, @RequestBody Tag tagRequest) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TagId " + id + "not found"));

        tag.setName(tagRequest.getName());

        return new ResponseEntity<>(tagRepository.save(tag), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{courseId}/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagFromTutorial(@PathVariable(value = "courseId") Long courseId, @PathVariable(value = "tagId") Long tagId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + courseId));

        course.removeTag(tagId);
        courseRepository.save(course);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
        tagRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
