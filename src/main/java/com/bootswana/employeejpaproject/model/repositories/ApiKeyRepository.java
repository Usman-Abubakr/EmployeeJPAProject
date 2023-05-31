package com.bootswana.employeejpaproject.model.repositories;

import com.bootswana.employeejpaproject.model.dtos.ApiKeyDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApiKeyRepository extends JpaRepository<ApiKeyDTO, String> {

    @Query(value = "SELECT k.* FROM employees.api_keys k", nativeQuery = true)
    List<ApiKeyDTO> getAllApiKeys();

    @Query(value = "SELECT k.access_level FROM employees.api_keys k WHERE k.api_key = :apiKey", nativeQuery = true)
    Integer getApiAccessLevel(String apiKey);


//    @Modifying
//    @Query(value = "INSERT INTO employees.api_keys (api_key, access_level) VALUES (:apiKey, :accessLevel);", nativeQuery = true)
//    void setApiKey(String apiKey, int accessLevel);



}