//package com.zhang.practice.spring.transaction;
//
//import org.springframework.context.PayloadApplicationEvent;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.event.TransactionPhase;
//import org.springframework.transaction.event.TransactionalEventListener;
//
///**
// * @ClassName UserTransactionListener
// * @Description:
// * @Author: zhangzh
// * @Date 2018/9/22 18:44
// */
//@Component
//public class UserTransactionListener {
//
//    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
//    public void before(PayloadApplicationEvent<User> even) {
//        System.out.println("before commit, id:" + even.getPayload().getId());
//    }
//
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void afterCommit(PayloadApplicationEvent<User> even) {
//        System.out.println("after commit, id:" + even.getPayload().getId());
//    }
//
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
//    public void afterCompletion(PayloadApplicationEvent<User> even) {
//        System.out.println("after completion, id:" + even.getPayload().getId());
//    }
//
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
//    public void afterRollback(PayloadApplicationEvent<User> even) {
//        System.out.println("after rollback, id:" + even.getPayload().getId());
//    }
//}
