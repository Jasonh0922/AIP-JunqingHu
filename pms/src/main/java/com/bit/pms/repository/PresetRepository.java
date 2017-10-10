package com.bit.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bit.pms.model.Preset;

public interface PresetRepository extends JpaRepository<Preset, Long> {
	Preset findByFlag(String flag);

	Preset findByFlagAndStatus(String flag, String status);

	@Query("update Preset p set p.status='1' where p.id=:id")
	@Modifying
	void updateStatusById(@Param("id") Long id);
}
