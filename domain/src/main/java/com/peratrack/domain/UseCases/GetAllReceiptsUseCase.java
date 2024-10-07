package com.peratrack.domain.UseCases;

import com.peratrack.domain.models.Receipt;
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface;
import com.peratrack.domain.repositories.MailboxRepoInterface;

import java.util.List;

import javax.inject.Inject;


public class GetAllReceiptsUseCase {
    private final LocalReceiptsRepoInterface localRepo;
    private final MailboxRepoInterface mailboxRepo;

    @Inject
    public GetAllReceiptsUseCase(
            LocalReceiptsRepoInterface localRepo,
            MailboxRepoInterface mailboxRepo
    ) {
        this.localRepo = localRepo;
        this.mailboxRepo = mailboxRepo;
    }

    public List<Receipt> execute() {
        /* TODO */

        return localRepo.getAllReceipts();
    }
}
