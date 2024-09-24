package com.peratrack.domain.UseCases;

import com.peratrack.domain.models.Receipt;

import java.util.List;

public class GetAllReceiptsUseCase {
    public List<Receipt> execute() {
        /* TODO */

        return List.of(
                new Receipt(),
                new Receipt(),
                new Receipt()
        );
    }
}
