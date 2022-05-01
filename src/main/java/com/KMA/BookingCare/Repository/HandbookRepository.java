package com.KMA.BookingCare.Repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.KMA.BookingCare.Entity.HandbookEntity;
import com.KMA.BookingCare.Entity.UserEntity;

public interface HandbookRepository extends JpaRepository<HandbookEntity, Long> {
	List<HandbookEntity> findAllByStatus(Integer status);
	List<HandbookEntity> findAllByStatus(Integer status,Pageable pageable);
	List<HandbookEntity> findAllByStatusAndUserId(Integer status, Long id,Pageable pageable);
	@Transactional
	@Modifying
	@Query(value = "UPDATE handbook  SET status = 0 WHERE id in :ids", nativeQuery = true)
	Integer updateByStatus(@Param("ids") List<String> ids);
	
	HandbookEntity findOneById(Long id);

	@Query(value = "SELECT * FROM bookingcare.handbook as u where title like CONCAT('%',:title,'%')  and status =1 ;", nativeQuery = true)
	List<HandbookEntity> findAllByTitleAndStatus(@Param("title") String title);
	
	@Query(value = "SELECT * FROM bookingcare.handbook as u where title like CONCAT('%',:title,'%') and specialized_id =:specializedId and status =1 ;", nativeQuery = true)
	List<HandbookEntity> findAllByTitleAndSpecializedId(@Param("title") String title,@Param("specializedId") Long specializedId);
	
	List<HandbookEntity> findAllBySpecializedIdAndStatus(Long SpecializedId, Integer status);
	
	@Query(value = "SELECT * FROM bookingcare.handbook h where h.status =1 ORDER BY RAND() LIMIT 4;",nativeQuery = true)
	List<HandbookEntity> findRandomHandbook();
	
	@Query(value = "SELECT * FROM bookingcare.handbook h  where h.status =1 AND title   like CONCAT('%',:title,'%') "
			+ "AND  ( (:specializedId IS NOT NULL AND specialized_id =:specializedId) || :specializedId IS NULL)"
			+ "AND ( (:userId IS NOT NULL AND user_id =:userId) || :userId IS NULL)",nativeQuery = true)
	List<HandbookEntity> searchHandbookAndPageable(@Param("title") String title,@Param("specializedId") Long specializedId,@Param("userId") Long userId,Pageable page);
}
