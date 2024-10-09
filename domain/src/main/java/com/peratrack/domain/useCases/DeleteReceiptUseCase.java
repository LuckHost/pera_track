package com.peratrack.domain.useCases;

import com.peratrack.domain.models.Receipt;
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface;

import javax.inject.Inject;

public class DeleteReceiptUseCase {
    private final LocalReceiptsRepoInterface localRepo;

    @Inject
    public DeleteReceiptUseCase(
            LocalReceiptsRepoInterface localRepo
    ) {
        this.localRepo = localRepo;
    }

    public void execute(Receipt receipt) {
        /* TODO */
        localRepo.deleteReceipt(receipt);
    }
}
