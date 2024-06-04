package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Rune;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RuneRepository extends CrudRepository<Rune, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO rune (name, row_type, group_name, description, long_description, image) " +
            "VALUES (:name, :row, :group_name, :description, :long_description, :image)", nativeQuery = true)
    int insertRune(String name, String row, String group_name, String description, String long_description, byte[] image);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO rune (name, row_type, group_name, description, long_description) " +
            "VALUES (:name, :row, :group_name, :description, :long_description)", nativeQuery = true)
    int insertRuneWithoutImage(String name, String row, String group_name, String description, String long_description);
}
