package com.project.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.app.model.Historymodel;

public interface Historyrepo extends MongoRepository<Historymodel, String> {

}
