package com.kenjdb.core.dao;

import com.kenjerdb.core.dao.KenjerDatabase;
import com.kenjerdb.core.dao.KenjerTable;
import org.junit.jupiter.api.Test;

public class KenjerDatabaseTest {

    @Test
    public void testDatabase() {
        KenjerDatabase database = KenjerDatabase.getDefault();
//        database = new KenjerDatabase.Configurer(
        KenjerTable customer = new KenjerTable.Configurer()
                .database(database)
                .name("customer")
                .configure();
        KenjerTable account = new KenjerTable.Configurer()
                .database(database)
                .name("account")
                .configure();
    }
}
