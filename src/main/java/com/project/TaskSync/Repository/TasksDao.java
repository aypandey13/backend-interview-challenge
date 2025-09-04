package com.project.TaskSync.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.TaskSync.Entity.Status;
import com.project.TaskSync.Entity.Tasks;

public interface TasksDao extends JpaRepository<Tasks, UUID> {
	 List<Tasks> findByDeletedFalse();
	 List<Tasks> findBySync_status(Status sync_status);
}
