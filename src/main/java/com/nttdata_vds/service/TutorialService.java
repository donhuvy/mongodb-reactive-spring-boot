package com.nttdata_vds.service;

import com.nttdata_vds.model.Tutorial;
import com.nttdata_vds.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Course service.
 */
@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    /**
     * Get all courses.
     *
     * @return
     */
    public Flux<Tutorial> findAll() {
        return tutorialRepository.findAll();
    }

    /**
     * Find by title of courses.
     *
     * @param title
     * @return
     */
    public Flux<Tutorial> findByTitleContaining(String title) {
        return tutorialRepository.findByTitleContaining(title);
    }

    /**
     * Find by its id.
     *
     * @param id
     * @return
     */
    public Mono<Tutorial> findById(String id) {
        return tutorialRepository.findById(id);
    }

    /**
     * Persit information to database management system.
     *
     * @param tutorial
     * @return
     */
    public Mono<Tutorial> save(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    /**
     * Edit information of a specific course.
     *
     * @param id
     * @param tutorial
     * @return
     */
    public Mono<Tutorial> update(String id, Tutorial tutorial) {
        return tutorialRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        tutorial.setId(id);
                        return tutorialRepository.save(tutorial);
                    }
                    return Mono.empty();
                });
    }

    /**
     * Delete a specific course by its id.
     *
     * @param id
     * @return
     */
    public Mono<Void> deleteById(String id) {
        return tutorialRepository.deleteById(id);
    }

    /**
     * Delete all courses.
     *
     * @return
     */
    public Mono<Void> deleteAll() {
        return tutorialRepository.deleteAll();
    }

    /**
     * Find all courses what was published.
     *
     * @param isPublished
     * @return
     */
    public Flux<Tutorial> findByPublished(boolean isPublished) {
        return tutorialRepository.findByPublished(isPublished);
    }
}
