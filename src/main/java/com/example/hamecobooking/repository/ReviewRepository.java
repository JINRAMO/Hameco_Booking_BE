package com.example.hamecobooking.repository;

import com.example.hamecobooking.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {
    List<ReviewEntity> findByStore_StoreId(Long storeId);
    Optional<ReviewEntity> findByReviewIdAndUser_UserId(Long reviewId, Long userId);
    List<ReviewEntity> findByUser_UserId(Long id);
}
