package com.plusitsolution.testone.repository;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.plusitsolution.testone.entity.OnelinkEntity;

@Repository
public interface oneLinkRepo extends ElasticsearchRepository<OnelinkEntity,String>{
	public List<OnelinkEntity> findAll();
	public OnelinkEntity findByNameStartsWith(String name);
	public OnelinkEntity findByName(String name);
	public OnelinkEntity findByGenerateLink(String generateLink);
	public OnelinkEntity deleteByName(String name);
	public OnelinkEntity deleteByGenerateLink(String generateLink);
}