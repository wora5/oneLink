package com.plusitsolution.testone.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.plusitsolution.testone.entity.StudentEntity;

@Repository
public interface StudentRepo extends ElasticsearchRepository<StudentEntity,String>{

}