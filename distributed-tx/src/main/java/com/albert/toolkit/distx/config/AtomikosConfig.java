package com.albert.toolkit.distx.config;


import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.UserTransaction;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class AtomikosConfig {

    @Bean(name = "userTransaction")
    @SneakyThrows(Exception.class)
    public UserTransaction userTransaction() {
        final UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(20000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager")
    @SneakyThrows(Exception.class)
    public TransactionManager atomikosTransactionManager() {
        final UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @SneakyThrows(Throwable.class)
    public PlatformTransactionManager transactionManager(
            @Qualifier("atomikosTransactionManager") TransactionManager atomikosTransactionManager,
            @Qualifier("userTransaction") UserTransaction userTransaction) {
        return new JtaTransactionManager(userTransaction(), atomikosTransactionManager());
    }
}
