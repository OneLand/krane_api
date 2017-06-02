package com.gachaland.api.jpa.repository;

import com.gachaland.api.jpa.model.TestModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chris.j3 on 2017. 5. 25..
 */
public interface TestModelRepository extends JpaRepository<TestModel, Long> {
}
