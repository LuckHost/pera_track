package com.peratrack.domain.useCases.userParams;

import com.peratrack.domain.repositories.UserParamsRepoInterface;
import javax.inject.Inject;

public class DeleteUserParamsUseCase {
    private final UserParamsRepoInterface userParamRepo;

    @Inject
    public DeleteUserParamsUseCase(
            UserParamsRepoInterface userParamRepo
    ) {
        this.userParamRepo = userParamRepo;
    }

    public void execute() {
        userParamRepo.deleteParams();
    }
}
