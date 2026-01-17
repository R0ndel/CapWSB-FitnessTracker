package pl.wsb.fitnesstracker.training.internal;

import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.training.api.TrainingDto;

import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
class TrainingController {

    private final TrainingServiceImpl trainingService;
    private final TrainingMapper trainingMapper;

    TrainingController(
            TrainingServiceImpl trainingService,
            TrainingMapper trainingMapper) {
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
    }

    /**
     * Retrieves all trainings.
     */
    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.findAllTrainings()
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }

    /**
     * Retrieves trainings for given user.
     */
    @GetMapping("/user/{userId}")
    public List<TrainingDto> getTrainingsForUser(@PathVariable Long userId) {
        return trainingService.findTrainingsByUser(userId)
                .stream()
                .map(trainingMapper::toDto)
                .toList();
    }
}
