package com.zhang.practice.spring.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @ClassName UseServiceImpl
 * @Description:
 * @Author: zhangzh
 * @Date 2018/9/22 18:35
 */
@Service
@Transactional
public class UseServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public User save(User user) {
        user = userRepository.save(user);
        applicationEventPublisher.publishEvent(user);
        return user;
    }
}
