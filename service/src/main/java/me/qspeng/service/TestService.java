package me.qspeng.service;

import me.qspeng.model.TestUser;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public TestUser testUser() {
        return new TestUser("124", "test");
    }
}
