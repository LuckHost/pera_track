package com.peratrack.domain.useCases.receiptsRepo;

import com.peratrack.domain.models.Receipt;
import com.peratrack.domain.models.UserParams;
import com.peratrack.domain.repositories.LocalReceiptsRepoInterface;
import com.peratrack.domain.repositories.MailboxRepoInterface;
import com.peratrack.domain.repositories.UserParamsRepoInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

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

        // get local saved users params
        UserParams params = paramsRepo.getParams();

        // fetch receipts from mail
        mailboxRepo.fetchAllReceipts(params.login, params.password)
                .subscribe(new SingleObserver<List<Receipt>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Receipt> receipts) {
                        result.addAll(receipts);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


        result.addAll(localRepo.getAllReceipts());
        return result;
    }
}
