package com.project.TaskSync.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Tasks {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
	private UUID id;
	
	@NotNull
	private String title;
	
	
	private String description;
	private boolean completed=false;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	@Column(name = "is_deleted")
	private boolean deleted = false;
 
	
	@Enumerated(EnumType.STRING)
	private Status sync_status;
	
	private String serverId;
	
	private LocalDateTime last_synced_at;
	
	
	@PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
        updated_at = created_at;
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }
}
