package com.peratrack.domain.repositories;

import com.peratrack.domain.models.UserParams;

public interface UserParamsRepoInterface {
    void saveParams(UserParams params);
    void deleteParams();
    UserParams getParams();
}
