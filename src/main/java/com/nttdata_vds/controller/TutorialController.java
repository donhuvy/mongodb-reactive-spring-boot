package com.nttdata_vds.controller;

import com.nttdata_vds.model.Tutorial;
import com.nttdata_vds.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Courses controller.
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
        if (title == null)
        {
            return tutorialService.findAll();
        }
        else
        {
            return tutorialService.findByTitleContaining(title);
        }
    }

    /**
     * Get a specific course by its id.
     *
     * @param id
     * @return
     */
    @GetMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> getTutorialById(@PathVariable("id") String id) {
        return tutorialService.findById(id);
    }

    /**
     * Add a new course.
     *
     * @param tutorial
     * @return
     */
    @PostMapping("/tutorials")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialService.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
    }

    /**
     * Edit an existing course.
     *
     * @param id
     * @param tutorial
     * @return
     */
    @PutMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Tutorial> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
        return tutorialService.update(id, tutorial);
    }

    /**
     * Delete a specific course by its id.
     *
     * @param id
     * @return
     */
    @DeleteMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTutorial(@PathVariable("id") String id) {
        return tutorialService.deleteById(id);
    }

    /**
     * Delete all courses.
     *
     * @return
     */
    @DeleteMapping("/tutorials")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllTutorials() {
        return tutorialService.deleteAll();
    }

    /**
     * Set all course what have published status equal true.
     *
     * @return
     */
    @GetMapping("/tutorials/published")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Tutorial> findByPublished() {
        return tutorialService.findByPublished(true);
    }
}
