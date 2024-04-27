package org.menekseyuncu.storemanagementsystem.common;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class BaseEntity {

    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        if (this.updatedAt == null) {
            this.updatedAt = LocalDateTime.now();
        }
    }
}
