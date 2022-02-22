package com.robot.apocalypse.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.robot.apocalypse.db.SurvivorEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SurvivorRepository extends JpaRepository<SurvivorEntity, Long> {
}
