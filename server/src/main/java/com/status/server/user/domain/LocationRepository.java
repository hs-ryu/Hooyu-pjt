package com.status.server.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByUserId(Long userPK);
    Boolean existsByUserId(Long userPK);
    void deleteByUserId(Long userPK);

//    @Query(value = "select * from location where latitude between :lat - 0.04 and :lat + 0.04 and longitude between :lon - 0.04 and :lon + 0.04", nativeQuery = true)
//    public List<Location> selectSQLBylatlon(@Param(value = "lat") BigDecimal lat,@Param(value = "lon") BigDecimal lon);

    //event
    @Query(value = "select * from location where latitude between :lat - 10 and :lat + 10 and longitude between :lon - 10 and :lon + 10", nativeQuery = true)
    public List<Location> selectSQLBylatlon(@Param(value = "lat") BigDecimal lat, @Param(value = "lon") BigDecimal lon);

}