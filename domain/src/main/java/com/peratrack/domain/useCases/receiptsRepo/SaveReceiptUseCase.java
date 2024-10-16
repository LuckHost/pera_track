package com.peratrack.domain.useCases.receiptsRepo;

import com.peratrack.domain.models.Receipt;
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface;

import javax.inject.Inject;

public class SaveReceiptUseCase {
    private final LocalReceiptsRepoInterface localRepo;

    @Inject
    public SaveReceiptUseCase(
            LocalReceiptsRepoInterface localRepo
    ) {
        this.localRepo = localRepo;
    }

    public void execute(Receipt receipt) {
        /* TODO */
        localRepo.saveReceipt(receipt);
    }
}
