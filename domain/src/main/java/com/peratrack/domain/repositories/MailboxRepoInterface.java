package com.peratrack.domain.repositories;

import com.peratrack.domain.models.Receipt;

import java.util.List;

public interface MailboxRepoInterface {
    List<Receipt> fetchAllReceipts(String username, String password);
}
