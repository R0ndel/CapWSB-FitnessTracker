package pl.wsb.fitnesstracker.training.api;

import java.util.List;
import java.util.Optional;

public interface TrainingProvider {

    Optional<Training> getTraining(Long trainingId);

    /**
     * Retrieves all trainings.
     *
     * @return list of all trainings
     */
    List<Training> findAllTrainings();

    /**
     * Retrieves trainings for given user ID.
     *
     * @param userId ID of the user
     * @return list of trainings for the user
     */
    List<Training> findTrainingsByUser(Long userId);
}
