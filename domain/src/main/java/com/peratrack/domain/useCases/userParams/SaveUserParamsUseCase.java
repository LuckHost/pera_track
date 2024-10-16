package com.peratrack.domain.useCases.userParams;

import com.peratrack.domain.models.UserParams;
import com.peratrack.domain.repositories.UserParamsRepoInterface;

import javax.inject.Inject;

public class SaveUserParamsUseCase {
    private final UserParamsRepoInterface userParamRepo;

    @Inject
    public SaveUserParamsUseCase(
            UserParamsRepoInterface userParamRepo
    ) {
        this.userParamRepo = userParamRepo;
    }

    public void execute(UserParams params) {
        userParamRepo.saveParams(params);
    }
}
