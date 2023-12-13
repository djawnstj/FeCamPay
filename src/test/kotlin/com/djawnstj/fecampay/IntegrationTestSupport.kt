package com.djawnstj.fecampay

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
abstract class IntegrationTestSupport {

    @Autowired
    protected lateinit var em: EntityManager

}