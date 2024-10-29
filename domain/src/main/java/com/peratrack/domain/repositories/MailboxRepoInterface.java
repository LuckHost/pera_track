package com.peratrack.domain.repositories;

import com.peratrack.domain.models.Receipt;

import java.util.List;

import io.reactivex.rxjava3.core.Single;


public interface MailboxRepoInterface {
    Single<List<Receipt>> fetchAllReceipts(String username, String password);
}
