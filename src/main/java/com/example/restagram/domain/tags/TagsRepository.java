package com.example.restagram.domain.tags;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagsRepository extends JpaRepository<Tags, Long> {
    Optional<Tags> findByTagName(String tagName);
}
