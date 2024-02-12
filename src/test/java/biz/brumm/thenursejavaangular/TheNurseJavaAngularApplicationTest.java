package biz.brumm.thenursejavaangular;

import biz.brumm.thenursejavaangular.entity.IUserRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TheNurseJavaAngularApplicationTest {

    private TheNurseJavaAngularApplication app = new TheNurseJavaAngularApplication();

    @BeforeEach
    void setUp() {

    }

    @Test
    void init() {
        IUserRepository repo = null;
        var erg =app.init(repo);
        assertNull(erg);

    }
}