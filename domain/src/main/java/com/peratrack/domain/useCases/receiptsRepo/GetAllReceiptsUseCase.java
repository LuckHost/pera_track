package com.peratrack.domain.useCases.receiptsRepo;

import com.peratrack.domain.models.Receipt;
import com.peratrack.domain.models.UserParams;
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface;
import com.peratrack.domain.repositories.MailboxRepoInterface;
import com.peratrack.domain.repositories.UserParamsRepoInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class GetAllReceiptsUseCase {
    private final LocalReceiptsRepoInterface localRepo;
    private final MailboxRepoInterface mailboxRepo;
    private final UserParamsRepoInterface paramsRepo;

    @Inject
    public GetAllReceiptsUseCase(
            LocalReceiptsRepoInterface localRepo,
            MailboxRepoInterface mailboxRepo,
            UserParamsRepoInterface paramsRepo
    ) {
        this.localRepo = localRepo;
        this.mailboxRepo = mailboxRepo;
        this.paramsRepo = paramsRepo;
    }

    public List<Receipt> execute() {
        List<Receipt> result = new ArrayList<>();

        UserParams params = paramsRepo.getParams();

        List<Receipt> mailResponse =
                mailboxRepo.fetchAllReceipts(params.login, params.password);
        result.addAll(mailResponse);

        result.addAll(localRepo.getAllReceipts());
        return result;
    }
}
