package com.peratrack.domain.repositories;


import com.peratrack.domain.models.Receipt;

import java.util.List;

public interface LocalReceiptsRepoInterface {
    void saveReceipt(Receipt receipt);
    void deleteReceipt(Receipt receipt);
    List<Receipt> getAllReceipts();
}
